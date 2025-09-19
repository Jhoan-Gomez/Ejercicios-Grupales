import java.util.Scanner;

public class Tienda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Constantes: número de secciones y productos máximos por sección
        final int SECCIONES = 2;
        final int MAX_PRODUCTOS = 10;

        // Matrices para almacenar datos: nombres, cantidades, precios y totales
        String[][] nombres = new String[SECCIONES][MAX_PRODUCTOS];
        int[][] cantidades = new int[SECCIONES][MAX_PRODUCTOS];
        double[][] precios = new double[SECCIONES][MAX_PRODUCTOS];
        double[][] totales = new double[SECCIONES][MAX_PRODUCTOS];

        // Array para llevar control de cuántos productos hay en cada sección
        int[] contador = new int[SECCIONES];

        System.out.println("=== SISTEMA DE INVENTARIO ===");

        // Registrar al menos 5 productos iniciales en la sección 0
        for (int i = 0; i < 5; i++) {
            System.out.println("\nProducto #" + (i + 1));

            // Pedir nombre
            System.out.print("Nombre: ");
            nombres[0][i] = sc.nextLine();

            // Pedir cantidad
            System.out.print("Cantidad: ");
            cantidades[0][i] = sc.nextInt();

            // Pedir precio unitario
            System.out.print("Precio por Unidad: ");
            precios[0][i] = sc.nextDouble();

            sc.nextLine(); // Limpiar buffer del scanner

            // Calcular el total de este producto (cantidad * precio)
            totales[0][i] = cantidades[0][i] * precios[0][i];

            // Aumentar contador de productos en sección 0
            contador[0]++;
        }

        // Menú interactivo para gestionar inventario
        int opcion;
        do {
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Mostrar reporte inventario");
            System.out.println("2. Agregar nuevo producto");
            System.out.println("3. Actualizar cantidad de producto");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    // Mostrar inventario completo
                    mostrarReporte(nombres, cantidades, precios, totales, contador);
                    break;

                case 2:
                    // Agregar un producto a una sección
                    System.out.print("Seleccione sección (0-" + (SECCIONES - 1) + "): ");
                    int sec = sc.nextInt();
                    sc.nextLine();

                    // Validar sección ingresada
                    if (sec < 0 || sec >= SECCIONES) {
                        System.out.println("Sección inválida.");
                        break;
                    }

                    // Verificar que haya espacio en la sección
                    if (contador[sec] < MAX_PRODUCTOS) {
                        int idx = contador[sec];

                        // Ingresar nuevo producto
                        System.out.print("Nombre: ");
                        String nuevoNombre = sc.nextLine();

                        // Validar que no se repita el nombre en esa sección
                        boolean existe = false;
                        for (int i = 0; i < contador[sec]; i++) {
                            if (nombres[sec][i].equalsIgnoreCase(nuevoNombre)) {
                                existe = true;
                                break;
                            }
                        }

                        if (existe) {
                            System.out.println("El producto ya existe en esta sección.");
                        } else {
                            // Guardar datos del nuevo producto
                            nombres[sec][idx] = nuevoNombre;

                            System.out.print("Cantidad: ");
                            cantidades[sec][idx] = sc.nextInt();

                            System.out.print("Precio unitario: ");
                            precios[sec][idx] = sc.nextDouble();
                            sc.nextLine();

                            // Calcular total
                            totales[sec][idx] = cantidades[sec][idx] * precios[sec][idx];
                            contador[sec]++;

                            System.out.println("Producto agregado correctamente.");
                        }
                    } else {
                        System.out.println("No hay espacio en esta sección.");
                    }
                    break;

                case 3:
                    // Actualizar cantidad de un producto ya registrado
                    System.out.print("Ingrese sección del producto: ");
                    sec = sc.nextInt();
                    sc.nextLine();

                    // Validar sección
                    if (sec < 0 || sec >= SECCIONES) {
                        System.out.println("Sección inválida.");
                        break;
                    }

                    System.out.print("Nombre del producto a actualizar: ");
                    String buscado = sc.nextLine();

                    boolean encontrado = false;
                    for (int i = 0; i < contador[sec]; i++) {
                        // Buscar producto por nombre
                        if (nombres[sec][i].equalsIgnoreCase(buscado)) {
                            System.out.print("Nueva cantidad: ");
                            cantidades[sec][i] = sc.nextInt();
                            sc.nextLine();

                            // Recalcular total
                            totales[sec][i] = cantidades[sec][i] * precios[sec][i];
                            System.out.println("Cantidad actualizada.");
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Producto no encontrado en esa sección.");
                    }
                    break;

                case 0:
                    // Salida del programa
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0); // repetir menú hasta que se elija salir

        sc.close(); // cerrar scanner
    }

    // Método que imprime el reporte del inventario completo
    public static void mostrarReporte(String[][] nombres, int[][] cantidades,
                                      double[][] precios, double[][] totales,
                                      int[] contador) {
        double inventarioTotal = 0;
        System.out.println("\n--- Reporte del Inventario --");

        // Recorrer todas las secciones
        for (int sec = 0; sec < nombres.length; sec++) {
            System.out.println("\nSección " + sec + ":");
            System.out.printf("%-15s %-10s %-12s %-12s\n", "Nombre", "Cantidad", "Precio U.", "Total");

            // Mostrar productos de la sección actual
            for (int i = 0; i < contador[sec]; i++) {
                System.out.printf("%-15s %-10d %-12.2f %-12.2f\n",
                        nombres[sec][i], cantidades[sec][i], precios[sec][i], totales[sec][i]);
                inventarioTotal += totales[sec][i];
            }
        }

        // Mostrar el valor total de todo el inventario
        System.out.println("\nValor total del inventario: $" + inventarioTotal);
    }
}
