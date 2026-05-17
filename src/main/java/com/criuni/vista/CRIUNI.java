package com.criuni.vista;

import com.criuni.controller.*;
import com.criuni.model.Alumno;
import com.criuni.model.Libro;
import com.criuni.model.Prestamo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CRIUNI {
    private Scanner scanner;
    private ControladorLibro libroControlador;
    private ControladorAlumno alumnoControlador;
    private ControladorPrestamo prestamoControlador;

    public CRIUNI(ControladorAlumno alumnoControlador, ControladorLibro libroControlador, ControladorPrestamo prestamoControlador) {
        scanner = new Scanner(System.in);
        this.libroControlador = libroControlador;
        this.alumnoControlador = alumnoControlador;
        this.prestamoControlador = prestamoControlador;
    }

    public void inicio() {
        while (true) {
            mostrarMensaje("\n==================================================");
            mostrarMensaje("       SISTEMA DE GESTIÓN BIBLIOTECARIA CRIUNI    ");
            mostrarMensaje("==================================================");
            mostrarMensaje("1. Gestión de Alumnos");
            mostrarMensaje("2. Gestión de Libros");
            mostrarMensaje("3. Gestión de Préstamos");
            mostrarMensaje("4. Salir");
            mostrarMensaje("──────────────────────────────────────────────────");
            mostrarMensaje("Seleccione una opción: ");

            int opcion = respuesta();
            switch (opcion) {
                case 1:
                    submenuAlumnos();
                    break;
                case 2:
                    submenuLibros();
                    break;
                case 3:
                    submenuPrestamos();
                    break;
                case 4:
                    mostrarMensaje("\n¡Gracias por utilizar el sistema CRIUNI! Adiós.");
                    return;
                default:
                    mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void submenuAlumnos() {
        while (true) {
            mostrarMensaje("\n┌──────────────────────────────────────────────────┐");
            mostrarMensaje("│                GESTIÓN DE ALUMNOS                │");
            mostrarMensaje("├──────────────────────────────────────────────────┤");
            mostrarMensaje("│ 1. Registrar Alumno                              │");
            mostrarMensaje("│ 2. Listar Alumnos / Ver Ficha                    │");
            mostrarMensaje("│ 3. Modificar Alumno                              │");
            mostrarMensaje("│ 4. Dar de Baja Alumno (Desactivar)               │");
            mostrarMensaje("│ 5. Volver al Menú Principal                      │");
            mostrarMensaje("└──────────────────────────────────────────────────┘");
            mostrarMensaje("Seleccione una opción: ");

            int opcion = respuesta();
            switch (opcion) {
                case 1:
                    menuCrearAlumno();
                    break;
                case 2:
                    menuListarAlumnos();
                    break;
                case 3:
                    menuModificarAlumno();
                    break;
                case 4:
                    menuDarBajaAlumno();
                    break;
                case 5:
                    return;
                default:
                    mostrarMensaje("Opción inválida.");
            }
        }
    }

    private void submenuLibros() {
        while (true) {
            mostrarMensaje("\n┌──────────────────────────────────────────────────┐");
            mostrarMensaje("│                 GESTIÓN DE LIBROS                │");
            mostrarMensaje("├──────────────────────────────────────────────────┤");
            mostrarMensaje("│ 1. Registrar Libro / Agregar Stock               │");
            mostrarMensaje("│ 2. Listar Libros / Ficha y Estado Disponibilidad │");
            mostrarMensaje("│ 3. Modificar Información de Libro                │");
            mostrarMensaje("│ 4. Retirar Libro de Stock (Baja)                 │");
            mostrarMensaje("│ 5. Volver al Menú Principal                      │");
            mostrarMensaje("└──────────────────────────────────────────────────┘");
            mostrarMensaje("Seleccione una opción: ");

            int opcion = respuesta();
            switch (opcion) {
                case 1:
                    menuCrearLibro();
                    break;
                case 2:
                    menuListarLibros();
                    break;
                case 3:
                    menuModificarLibro();
                    break;
                case 4:
                    menuDarBajaLibro();
                    break;
                case 5:
                    return;
                default:
                    mostrarMensaje("Opción inválida.");
            }
        }
    }

    private void submenuPrestamos() {
        while (true) {
            mostrarMensaje("\n┌──────────────────────────────────────────────────┐");
            mostrarMensaje("│               GESTIÓN DE PRÉSTAMOS               │");
            mostrarMensaje("├──────────────────────────────────────────────────┤");
            mostrarMensaje("│ 1. Realizar Préstamo                             │");
            mostrarMensaje("│ 2. Registrar Devolución                          │");
            mostrarMensaje("│ 3. Ver Informe de Deudores                       │");
            mostrarMensaje("│ 4. Volver al Menú Principal                      │");
            mostrarMensaje("└──────────────────────────────────────────────────┘");
            mostrarMensaje("Seleccione una opción: ");

            int opcion = respuesta();
            switch (opcion) {
                case 1:
                    menuPrestarLibro();
                    break;
                case 2:
                    menuDevolverPrestamo();
                    break;
                case 3:
                    menuInformes();
                    break;
                case 4:
                    return;
                default:
                    mostrarMensaje("Opción inválida.");
            }
        }
    }

    public void mostrarMensajeFinal(String mensaje) {
        System.out.println("\n" + "=".repeat(mensaje.length() + 4));
        System.out.println("  " + mensaje);
        System.out.println("=".repeat(mensaje.length() + 4));
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int respuesta() {
        while (true) {
            String respuesta = scanner.nextLine().trim();
            try {
                return Integer.parseInt(respuesta);
            } catch (NumberFormatException e) {
                mostrarMensaje("Respuesta inválida. Ingrese un número válido:");
            }
        }
    }

    public void menuCrearAlumno() {
        boolean completado = false;
        while (!completado) {
            mostrarMensaje("\n--- REGISTRO DE NUEVO ALUMNO ---");
            String nombre = pedir("Ingrese nombre completo (Ej: Juan Benito Perez López):");
            int ci = alumnoControlador.verificarEntero(pedir("Ingrese número de cedula sin puntos (Ej: 4123456):"));
            String email = alumnoControlador.verificarEmail(pedir("Ingrese email (Ej: juana@dominio.TLD):"));
            int telefono = alumnoControlador.verificarEntero(pedir("Ingrese número de telefono (Ej: 0985123456):"));
            String facultad = pedir("Ingrese facultad (Ej: Medicina):");
            LocalDate fechaNacimiento = alumnoControlador.fechaNacimiento();

            mostrarMensaje("\n==================================================");
            mostrarMensaje("       RESUMEN DE DATOS DEL ALUMNO A REGISTRAR    ");
            mostrarMensaje("==================================================");
            mostrarMensaje(" • Nombre Completo: " + nombre);
            mostrarMensaje(" • Cédula de Identidad (CI): " + ci);
            mostrarMensaje(" • Correo Electrónico: " + email);
            mostrarMensaje(" • Teléfono de Contacto: " + telefono);
            mostrarMensaje(" • Facultad: " + facultad);
            mostrarMensaje(" • Fecha de Nacimiento: " + fechaNacimiento);
            mostrarMensaje("==================================================");
            mostrarMensaje("¿Desea confirmar el registro del alumno?");
            mostrarMensaje("1. Sí, confirmar y registrar");
            mostrarMensaje("2. No, volver a rellenar datos");
            mostrarMensaje("3. Cancelar registro y salir");
            mostrarMensaje("Seleccione una opción: ");

            int opcion = respuesta();
            if (opcion == 1) {
                Alumno alumno = new Alumno(nombre, ci, email, telefono, facultad);
                alumno.setFechaNacimiento(fechaNacimiento);
                alumnoControlador.crearAlumno(alumno);
                completado = true;
            } else if (opcion == 3) {
                mostrarMensajeFinal("Registro cancelado.");
                completado = true;
            }
        }
    }

    public void menuCrearLibro() {
        boolean completado = false;
        while (!completado) {
            mostrarMensaje("\n--- REGISTRO DE NUEVO LIBRO ---");
            String titulo = pedir("Ingrese titulo (Ej: El principito):");
            String autor = pedir("Ingrese autor (Ej: Antoine de Saint-Exupéry):");
            String editorial = pedir("Ingrese editorial (Ej: Susaetia):");
            String isbn = pedir("Ingrese ISBN (Ej: 9788467739879):");
            int anho = libroControlador.verificarEntero(pedir("Ingrese año de publicación (Ej: 2014):"));
            int cantidad = libroControlador.verificarEntero(pedir("Ingrese cantidad (Ej: 12):"));

            mostrarMensaje("\n==================================================");
            mostrarMensaje("       RESUMEN DE DATOS DEL LIBRO A REGISTRAR     ");
            mostrarMensaje("==================================================");
            mostrarMensaje(" • Título: " + titulo);
            mostrarMensaje(" • Autor: " + autor);
            mostrarMensaje(" • Editorial: " + editorial);
            mostrarMensaje(" • ISBN (Clave única): " + isbn);
            mostrarMensaje(" • Año de Publicación: " + anho);
            mostrarMensaje(" • Cantidad inicial en stock: " + cantidad);
            mostrarMensaje("==================================================");
            mostrarMensaje("¿Desea confirmar el registro del libro?");
            mostrarMensaje("1. Sí, confirmar y registrar");
            mostrarMensaje("2. No, volver a rellenar datos");
            mostrarMensaje("3. Cancelar registro y salir");
            mostrarMensaje("Seleccione una opción: ");

            int opcion = respuesta();
            if (opcion == 1) {
                Libro libro = new Libro(titulo, autor, editorial, isbn, anho, cantidad);
                boolean exitoOperacion = libroControlador.crearLibro(libro);
                if (exitoOperacion) {
                    completado = true;
                }
            } else if (opcion == 3) {
                mostrarMensajeFinal("Registro cancelado.");
                completado = true;
            }
        }
    }

    private void menuListarAlumnos() {
        Map<Integer, Alumno> alumnos = alumnoControlador.mostrarTodos();
        if (alumnos.isEmpty()) return;

        Alumno alumno = seleccionarOpcion(alumnos, "Alumno");
        if (alumno == null) return;

        // Ficha Interactiva del Alumno Seleccionado
        mostrarMensaje("\n==================================================");
        mostrarMensaje("     DETALLE DEL ALUMNO: " + alumno.getNombreCompleto().toUpperCase());
        mostrarMensaje("==================================================");
        mostrarMensaje(" • CI: " + alumno.getCi());
        mostrarMensaje(" • Email: " + alumno.getEmail());
        mostrarMensaje(" • Teléfono: " + alumno.getTelefono());
        mostrarMensaje(" • Facultad: " + alumno.getFacultad());
        mostrarMensaje(" • Fecha de Nacimiento: " + alumno.getFechaNacimiento());
        mostrarMensaje(" • Estado del Perfil: " + (alumno.isActivo() ? "Activo" : "Inactivo (Baja)"));
        mostrarMensaje("──────────────────────────────────────────────────");
        mostrarMensaje("               HISTORIAL DE PRÉSTAMOS             ");
        mostrarMensaje("──────────────────────────────────────────────────");

        List<Prestamo> prestamos = prestamoControlador.obtenerPrestamosPorAlumno(alumno.getCi());
        if (prestamos.isEmpty()) {
            mostrarMensaje("Info: El alumno no registra solicitudes de préstamo.");
        } else {
            long multaTotal = 0;
            for (Prestamo p : prestamos) {
                mostrarMensaje("\n ➔ Préstamo ID: " + p.getIdPrestamo());
                mostrarMensaje("   • Retirada: " + p.getFechaPrestamo() + " | Fecha Límite: " + p.getFechaDevolucion());

                if (p.estaDevuelto()) {
                    mostrarMensaje("   • Estado: DEVUELTO (el " + p.getFechaDevuelto() + ")");
                } else {
                    boolean vencido = LocalDate.now().isAfter(p.getFechaDevolucion());
                    if (vencido) {
                        long multa = prestamoControlador.calcularMulta(p);
                        multaTotal += multa;
                        mostrarMensaje("   • Estado: VENCIDO");
                        mostrarMensaje("   • Sanción actual acumulada: " + multa + " Gs.");
                    } else {
                        mostrarMensaje("   • Estado: ⏳ PENDIENTE (Dentro de fecha)");
                    }
                }

                mostrarMensaje("   • Libros solicitados:");
                p.getLibros().forEach(l -> mostrarMensaje("     - " + l.getTitulo() + " (ISBN: " + l.getIsbn() + ")"));
            }

            mostrarMensaje("\n──────────────────────────────────────────────────");
            if (multaTotal > 0) {
                mostrarMensaje(" DEUDA MONETARIA ACUMULADA: " + multaTotal + " Gs.");
            } else {
                mostrarMensaje(" El alumno no registra multas pendientes.");
            }
        }
        mostrarMensaje("==================================================");
        mostrarMensaje("Presione Enter para continuar...");
        scanner.nextLine();
    }

    private void menuListarLibros() {
        Map<String, Libro> libros = libroControlador.mostrarTodosActivo();
        if (libros.isEmpty()) return;

        Libro libro = seleccionarOpcion(libros, "Libro");
        if (libro == null) return;

        // Ficha Interactiva del Libro Seleccionado
        mostrarMensaje("\n==================================================");
        mostrarMensaje("               DETALLE DE CATÁLOGO                ");
        mostrarMensaje("==================================================");
        mostrarMensaje(" • Título: " + libro.getTitulo());
        mostrarMensaje(" • Autor: " + libro.getAutor());
        mostrarMensaje(" • Editorial: " + libro.getEditorial());
        mostrarMensaje(" • ISBN: " + libro.getIsbn());
        mostrarMensaje(" • Año de Publicación: " + libro.getAnhoPublicacion());
        mostrarMensaje(" • Stock Total Disponible: " + libro.getCantidad() + " unidades");
        mostrarMensaje("──────────────────────────────────────────────────");
        mostrarMensaje("              DISPONIBILIDAD Y PRÉSTAMOS          ");
        mostrarMensaje("──────────────────────────────────────────────────");

        List<Prestamo> prestamosActivos = prestamoControlador.obtenerPrestamosActivosPorLibro(libro.getIsbn());
        if (prestamosActivos.isEmpty()) {
            mostrarMensaje(" Todas las copias de este libro están en los estantes.");
        } else {
            mostrarMensaje(" Copias prestadas en circulación: " + prestamosActivos.size());
            prestamosActivos.forEach(p -> {
                mostrarMensaje("   - Prestado a: " + p.getAlumno().getNombreCompleto() +
                        " | Devolución: " + p.getFechaDevolucion() +
                        " (ID Préstamo: " + p.getIdPrestamo() + ")");
            });
        }
        mostrarMensaje("==================================================");
        mostrarMensaje("Presione Enter para continuar...");
        scanner.nextLine();
    }

    private void menuModificarAlumno() {
        Map<Integer, Alumno> alumnos = alumnoControlador.mostrarTodosActivo();
        if (alumnos.isEmpty()) return;

        Alumno alumno = seleccionarOpcion(alumnos, "Alumno a Modificar");
        if (alumno == null) return;

        mostrarMensaje("\n¿Qué desea modificar de " + alumno.getNombreCompleto() + "?");
        mostrarMensaje("1. Nombre completo");
        mostrarMensaje("2. Facultad");
        mostrarMensaje("3. Email");
        mostrarMensaje("4. Teléfono");
        mostrarMensaje("5. Cancelar");
        mostrarMensaje("Seleccione una opción: ");

        int opcion = respuesta();
        switch (opcion) {
            case 1:
                alumnoControlador.modificarNombre(alumno.getCi());
                break;
            case 2:
                alumnoControlador.modificarFacultad(alumno.getCi());
                break;
            case 3:
                alumnoControlador.modificarEmail(alumno.getCi());
                break;
            case 4:
                alumnoControlador.modificarNumeroTelefono(alumno.getCi());
                break;
            case 5:
                break;
            default:
                mostrarMensaje("Opción inválida.");
        }
    }

    private void menuDarBajaAlumno() {
        Map<Integer, Alumno> alumnos = alumnoControlador.mostrarTodosActivo();
        if (alumnos.isEmpty()) return;

        Alumno alumno = seleccionarOpcion(alumnos, "Alumno a Dar de Baja");
        if (alumno != null) {
            alumnoControlador.borrar(alumno.getCi());
        }
    }

    private void menuModificarLibro() {
        Map<String, Libro> libros = libroControlador.mostrarTodosActivo();
        if (libros.isEmpty()) return;

        Libro libro = seleccionarOpcion(libros, "Libro a Modificar");
        if (libro == null) return;

        mostrarMensaje("\n¿Qué desea modificar de " + libro.getTitulo() + "?");
        mostrarMensaje("1. Título");
        mostrarMensaje("2. Autor");
        mostrarMensaje("3. Editorial");
        mostrarMensaje("4. Año de publicación");
        mostrarMensaje("5. Cancelar");
        mostrarMensaje("Seleccione una opción: ");

        int opcion = respuesta();
        switch (opcion) {
            case 1:
                libroControlador.modificarTitulo(libro.getIsbn());
                break;
            case 2:
                libroControlador.modificarAutor(libro.getIsbn());
                break;
            case 3:
                libroControlador.modificarEditorial(libro.getIsbn());
                break;
            case 4:
                libroControlador.modificarAnhoPublicacion(libro.getIsbn());
                break;
            case 5:
                break;
            default:
                mostrarMensaje("Opción inválida.");
        }
    }

    private void menuDarBajaLibro() {
        Map<String, Libro> libros = libroControlador.mostrarTodosActivo();
        if (libros.isEmpty()) return;

        Libro libro = seleccionarOpcion(libros, "Libro a Retirar Stock");
        if (libro != null) {
            libroControlador.borrar(libro.getIsbn());
        }
    }

    private void menuPrestarLibro() {
        int ci = alumnoControlador.verificarEntero(pedir("Ingrese documento del alumno:"));
        Alumno alumno = alumnoControlador.buscar(ci).orElse(null);
        if (alumno == null) {
            mostrarMensaje("Alumno no encontrado.");
            return;
        }
        if (!alumno.isActivo()) {
            mostrarMensaje("El alumno seleccionado se encuentra dado de baja.");
            return;
        }

        mostrarMensaje("\nAlumno encontrado: " + alumno.getNombreCompleto());

        List<Libro> canasta = new ArrayList<>();
        boolean continuarAgregando = true;

        while (continuarAgregando) {
            Map<String, Libro> librosDisponibles = libroControlador.mostrarTodosActivo();
            if (librosDisponibles.isEmpty()) {
                mostrarMensaje("No hay libros disponibles en el catálogo.");
                break;
            }
            Map<String, Libro> filtradosPorStock = new HashMap<>();
            librosDisponibles.forEach((isbn, libro) -> {
                long copiasEnCanasta = canasta.stream().filter(l -> l.getIsbn().equals(isbn)).count();
                if (libro.getCantidad() - copiasEnCanasta > 0) {
                    filtradosPorStock.put(isbn, libro);
                }
            });

            if (filtradosPorStock.isEmpty()) {
                mostrarMensaje("\nNo hay más libros en el catálogo con stock remanente para prestar.");
                break;
            }

            mostrarMensaje("\n--- SELECCIONE UN LIBRO PARA LA CANASTA ---");
            Libro libroSeleccionado = seleccionarOpcion(filtradosPorStock, "Libro");

            if (libroSeleccionado != null) {
                boolean yaExisteEnCanasta = canasta.stream().anyMatch(l -> l.getIsbn().equals(libroSeleccionado.getIsbn()));
                if (yaExisteEnCanasta) {
                    mostrarMensaje("El libro '" + libroSeleccionado.getTitulo() + "' ya fue agregado a esta solicitud.");
                } else {
                    canasta.add(libroSeleccionado);
                    mostrarMensaje("Agregado a la canasta: " + libroSeleccionado.getTitulo());
                }
            } else {
                mostrarMensaje("Selección de libro omitida.");
            }

            if (canasta.isEmpty()) {
                mostrarMensaje("\nLa canasta está vacía.");
                mostrarMensaje("1. Intentar agregar un libro de nuevo");
                mostrarMensaje("2. Cancelar proceso de préstamo");
                mostrarMensaje("Seleccione una opción: ");
                int opcionVacia = respuesta();
                if (opcionVacia != 1) {
                    return;
                }
            } else {
                // Mostrar estado actual de la canasta
                mostrarMensaje("\nLibros en la canasta actual (" + canasta.size() + "):");
                for (int i = 0; i < canasta.size(); i++) {
                    mostrarMensaje("   " + (i + 1) + ". " + canasta.get(i).getTitulo() + " (ISBN: " + canasta.get(i).getIsbn() + ")");
                }

                mostrarMensaje("\n¿Qué desea hacer?");
                mostrarMensaje("1. Añadir otro libro a este préstamo");
                mostrarMensaje("2. Finalizar selección y confirmar el préstamo");
                mostrarMensaje("3. Cancelar préstamo y vaciar canasta");
                mostrarMensaje("Seleccione una opción: ");

                int opcionFlujo = respuesta();
                if (opcionFlujo == 2) {
                    continuarAgregando = false;
                } else if (opcionFlujo == 3) {
                    mostrarMensajeFinal("Proceso de préstamo cancelado voluntariamente.");
                    return;
                }
            }
        }

        if (canasta.isEmpty()) {
            mostrarMensajeFinal("Canasta vacía. Préstamo cancelado.");
            return;
        }

        LocalDate fechaPrestamo = LocalDate.now();
        int diasSancionados = prestamoControlador.verificarEntero(pedir("En cuantos días será devuelto?"));
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(diasSancionados);

        mostrarMensaje("\n==================================================");
        mostrarMensaje("       RESUMEN DE CONFIRMACIÓN DE PRÉSTAMO MÚLTIPLE ");
        mostrarMensaje("==================================================");
        mostrarMensaje(" • Beneficiario: " + alumno.getNombreCompleto() + " (CI: " + alumno.getCi() + ")");
        mostrarMensaje(" • Fecha de Salida: " + fechaPrestamo);
        mostrarMensaje(" • Fecha Límite de Entrega: " + fechaDevolucion);
        mostrarMensaje(" • Libros a Llevar (" + canasta.size() + "):");
        for (int i = 0; i < canasta.size(); i++) {
            mostrarMensaje("   ➔ [" + (i+1) + "] " + canasta.get(i).getTitulo() + " (ISBN: " + canasta.get(i).getIsbn() + ")");
        }
        mostrarMensaje("==================================================");
        mostrarMensaje("¿Desea confirmar este préstamo múltiple?");
        mostrarMensaje("1. Sí, confirmar y registrar");
        mostrarMensaje("2. No, cancelar operación");
        mostrarMensaje("Seleccione una opción: ");

        int confirmacion = respuesta();
        if (confirmacion == 1) {
            Prestamo nuevoPrestamo = new Prestamo(alumno, fechaPrestamo, fechaDevolucion);
            nuevoPrestamo.getLibros().addAll(canasta);
            prestamoControlador.prestar(nuevoPrestamo);
        } else {
            mostrarMensajeFinal("Operación cancelada. No se registraron cambios.");
        }
    }

    private void menuDevolverPrestamo() {
        Map<Integer, Prestamo> prestamosActivos = prestamoControlador.mostrarTodosActivo();
        if (prestamosActivos.isEmpty()) {
            return;
        }
        Prestamo prestamo = seleccionarOpcion(prestamosActivos, "Préstamo Pendiente");
        if (prestamo != null) {
            prestamoControlador.devolverPrestamo(prestamo.getIdPrestamo());
        }
    }

    private void menuInformes() {
        Map<String, Map<?, ?>> informe = prestamoControlador.informe();
        if (informe.isEmpty()) {
            return;
        }
        mostrarMensaje("\n==================================================");
        mostrarMensaje("               INFORME GENERAL DE LA BIBLIOTECA   ");
        mostrarMensaje("==================================================");
        informe.forEach((seccion, datos) -> {
            mostrarMensaje("\n* " + seccion.toUpperCase());
            if (datos.isEmpty()) {
                mostrarMensaje("   (No hay registros en esta sección)");
            } else {
                datos.forEach((clave, valor) -> {
                    mostrarMensaje("   • " + clave + " ➔ " + valor);
                });
            }
        });
        mostrarMensaje("==================================================");
        mostrarMensaje("Presione Enter para regresar...");
        scanner.nextLine();
    }

    private <K, T> T seleccionarOpcion(Map<K, T> mapaRepo, String nombreEntidad) {
        if (mapaRepo == null || mapaRepo.isEmpty()) {
            mostrarMensaje("┌──────────────────────────────────────────────────┐");
            mostrarMensaje("│  No hay " + nombreEntidad + " disponibles.                 │");
            mostrarMensaje("└──────────────────────────────────────────────────┘");
            return null;
        }
        List<Map.Entry<K, T>> listaEntries = new ArrayList<>(mapaRepo.entrySet());
        int total = listaEntries.size();
        int anchoOpcion = 6;
        int anchoClave = 20;
        int anchoValor = 50;
        mostrarMensaje("┌" + "─".repeat(anchoOpcion + 2) + "┬" + "─".repeat(anchoClave + 2) + "┬" + "─".repeat(anchoValor + 2) + "┐");
        mostrarMensaje(String.format("│ %-" + anchoOpcion + "s │ %-" + anchoClave + "s │ %-" + anchoValor + "s │",
                "OPCIÓN", "CLAVE/ID", nombreEntidad.toUpperCase()));
        mostrarMensaje("├" + "─".repeat(anchoOpcion + 2) + "┼" + "─".repeat(anchoClave + 2) + "┼" + "─".repeat(anchoValor + 2) + "┤");
        for (int i = 0; i < total; i++) {
            Map.Entry<K, T> entrada = listaEntries.get(i);
            K clave = entrada.getKey();
            T valor = entrada.getValue();

            String strClave = (clave != null) ? clave.toString() : "N/A";
            String strValor = (valor != null) ? valor.toString() : "N/A";

            if (strValor.length() > anchoValor) {
                strValor = strValor.substring(0, anchoValor - 3) + "...";
            }
            if (strClave.length() > anchoClave) {
                strClave = strClave.substring(0, anchoClave - 3) + "...";
            }

            mostrarMensaje(String.format("│ %" + anchoOpcion + "d │ %-20s │ %-50s │",
                    (i + 1), strClave, strValor));
        }
        mostrarMensaje("└" + "─".repeat(anchoOpcion + 2) + "┴" + "─".repeat(anchoClave + 2) + "┴" + "─".repeat(anchoValor + 2) + "┘");
        mostrarMensaje("");
        while (true) {
            mostrarMensaje("Seleccione el número de la opción (1-" + total + ") o escriba '0' para cancelar: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equals("0")) {
                return null;
            }
            try {
                int opcion = Integer.parseInt(entrada);
                if (opcion >= 1 && opcion <= total) {
                    return listaEntries.get(opcion - 1).getValue();
                } else {
                    mostrarMensaje("Opción fuera de rango. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Entrada inválida. Por favor ingrese un número.");
            }
        }
    }

    public String pedir(String mensaje) {
        String entrada;
        while (true) {
            mostrarMensaje(mensaje);
            if (scanner.hasNextLine()) {
                entrada = scanner.nextLine();
                if (entrada.trim().isEmpty()) {
                    mostrarMensaje("Entrada inválida, no puede estar vacío. Intente de nuevo:");
                } else {
                    return entrada.trim();
                }
            } else {
                return "";
            }
        }
    }
}