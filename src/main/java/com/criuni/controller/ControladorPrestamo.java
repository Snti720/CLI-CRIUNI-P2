package com.criuni.controller;

import com.criuni.model.Alumno;
import com.criuni.model.Libro;
import com.criuni.model.Prestamo;
import com.criuni.repository.RepositorioPrestamo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ControladorPrestamo extends ControladorCRUDBase<Integer, Prestamo> {
    private Map<Integer, List<Integer>> indexacionPrestamoAlumnos;
    private LocalDate hoy;

    public ControladorPrestamo(RepositorioPrestamo repoPrestamo){
        this.repo = repoPrestamo;
        this.indexacionPrestamoAlumnos = new HashMap<>();
        this.hoy = LocalDate.now();
    }

    public void prestar(Prestamo prestamo){
        int idAlumno = prestamo.getAlumno().getClave();
        if (!debeLibro(idAlumno)){
            for (Libro libro: prestamo.getLibros()){
                libro.restarLibros(1);
            }
            repo.agregar(prestamo);
            indexacionPrestamoAlumnos.putIfAbsent(idAlumno, new ArrayList<>());
            indexacionPrestamoAlumnos.get(idAlumno).add(prestamo.getIdPrestamo());
            vista.mostrarMensajeFinal("Préstamo registrado exitosamente con ID: " + prestamo.getIdPrestamo());
        } else {
            vista.mostrarMensajeFinal("ALERTA: Préstamo denegado. El alumno tiene préstamos vencidos con ID: " + mostrarDeudas(idAlumno));
        }
    }

    public void devolverPrestamo(int idPrestamo) {
        Prestamo prestamo = mostrar(idPrestamo);
        if (prestamo == null) {
            vista.mostrarMensajeFinal("No se encontró ningún préstamo con el ID: " + idPrestamo);
            return;
        }
        if (prestamo.estaDevuelto()) {
            vista.mostrarMensajeFinal("Este préstamo ya fue devuelto anteriormente.");
            return;
        }
        for (Libro libro: prestamo.getLibros()){
            libro.agregarLibros(1);
        }
        prestamo.setEstadoDevolucion(true);
        prestamo.setFechaDevuelto(LocalDate.now());

        // Cálculo automático de multa por retraso
        String mensajeRetorno = "Préstamo ID " + idPrestamo + " devuelto con éxito.";
        if (estaVencido(prestamo)) {
            long multa = calcularMulta(prestamo);
            mensajeRetorno += "\nALERTA DE MULTA POR DEMORA! \n   El préstamo estaba vencido desde el " + prestamo.getFechaDevolucion() +
                    ". \n   Sanción calculada: " + multa + " Gs.";
        }
        vista.mostrarMensajeFinal(mensajeRetorno);
    }

    private boolean debeLibro(int idAlumno){
        if (!indexacionPrestamoAlumnos.containsKey(idAlumno)){
            return false;
        } else {
            List<Integer> listaPrestamosAlumno = indexacionPrestamoAlumnos.get(idAlumno);
            for (int idPrestamo : listaPrestamosAlumno){
                Prestamo prestamo = repo.obtenerTodos().get(idPrestamo);
                if (estaVencido(prestamo) && !prestamo.estaDevuelto()) {
                    return true;
                }
            }
            return false;
        }
    }

    private String mostrarDeudas(int idAlumno){
        StringBuilder idPrestamosFormateado = new StringBuilder();
        List<Integer> listaPrestamosAlumno = indexacionPrestamoAlumnos.get(idAlumno);
        int deudasContadas = 0;
        for (int idPrestamo : listaPrestamosAlumno){
            Prestamo prestamo = repo.obtenerTodos().get(idPrestamo);
            if (estaVencido(prestamo) && !prestamo.estaDevuelto()){
                idPrestamosFormateado.append(idPrestamo).append(", ");
                deudasContadas++;
            }
        }
        if (deudasContadas > 0) {
            idPrestamosFormateado.setLength(idPrestamosFormateado.length() - 2);
        }
        return idPrestamosFormateado.toString();
    }

    private boolean estaVencido(Prestamo prestamo){
        return LocalDate.now().isAfter(prestamo.getFechaDevolucion());
    }

    public Map<String, Map<?, ?>> informe(){
        Map<String, Map<?, ?>> informe = new HashMap<>();
        Map<String, Integer> deudor = new HashMap<>();
        Map<Integer, Alumno> vencidos = new HashMap<>();

        if (repo.obtenerTodos().isEmpty()){
            vista.mostrarMensajeFinal("Aún no hay préstamos registrados para analizar.");
            return Collections.emptyMap();
        }

        repo.obtenerTodos().forEach((idPrestamo, prestamo) -> {
            if (!prestamo.estaDevuelto()){
                deudor.put(prestamo.getAlumno().getNombreCompleto(), prestamo.getLibros().size());
                if (estaVencido(prestamo)){
                    vencidos.put(idPrestamo, prestamo.getAlumno());
                }
            }
        });

        informe.put("Préstamos Activos", deudor);
        informe.put("Préstamos Vencidos", vencidos);
        return informe;
    }

    public long calcularMulta(Prestamo prestamo){
        LocalDate fechaFin = prestamo.estaDevuelto() ? prestamo.getFechaDevuelto() : LocalDate.now();
        long diasRetraso = ChronoUnit.DAYS.between(prestamo.getFechaDevolucion(), fechaFin);
        if (diasRetraso <= 0) return 0;
        double semanasSancionadas = Math.ceil(diasRetraso / 7.0);
        return (long) Math.ceil(semanasSancionadas * 2000);
    }

    public List<Prestamo> obtenerPrestamosPorAlumno(int idAlumno) {
        List<Prestamo> resultado = new ArrayList<>();
        List<Integer> ids = indexacionPrestamoAlumnos.get(idAlumno);
        if (ids != null) {
            for (int id : ids) {
                Prestamo p = repo.obtenerTodos().get(id);
                if (p != null) {
                    resultado.add(p);
                }
            }
        }
        return resultado;
    }

    public List<Prestamo> obtenerPrestamosActivosPorLibro(String isbn) {
        List<Prestamo> resultado = new ArrayList<>();
        repo.obtenerTodos().values().forEach(p -> {
            if (!p.estaDevuelto()) {
                boolean contieneLibro = p.getLibros().stream().anyMatch(l -> l.getIsbn().equals(isbn));
                if (contieneLibro) {
                    resultado.add(p);
                }
            }
        });
        return resultado;
    }

    @Override
    protected boolean isActivo(Prestamo prestamo){
        // Un préstamo está 'activo' (pendiente) si NO ha sido devuelto aún.
        return !prestamo.estaDevuelto();
    }
}
