import java.util.Scanner;

public class Tienda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Máximo de productos por sección y número de secciones
        // Estas variables estan todas en Mayusculas y las definimos
        final int SECCIONES = 2;
        final int MAX_PRODUCTOS = 10;

        // Matrices para manejar datos por secciones
        String[][] nombres = new String[SECCIONES][MAX_PRODUCTOS];
        int[][] cantidades = new int[SECCIONES][MAX_PRODUCTOS];
        double[][] precios = new double[SECCIONES][MAX_PRODUCTOS];
        double[][] totales = new double[SECCIONES][MAX_PRODUCTOS];

        // Control de cuántos productos hay en cada sección
        int[] contador = new int[SECCIONES];

        System.out.println("=== SISTEMA DE INVENTARIO ===");

        // Registrar al menos 5 productos en la sección 0
        for (int i = 0; i < 5; i++) {
            System.out.println("\nProducto #" + (i + 1));
            System.out.print("Nombre: ");
            nombres[0][i] = sc.nextLine();

            System.out.print("Cantidad: ");
            cantidades[0][i] = sc.nextInt();

            System.out.print("Precio por Unidad: ");
            precios[0][i] = sc.nextDouble();

            sc.nextLine(); // limpiar buffer
            totales[0][i] = cantidades[0][i] * precios[0][i];
            contador[0]++;
        }

        // Aqui se imprime el menú de opciones con un do después de que se halla ingresado 
        // por lo menos 5 productos, donde permite al usuario terminar el inventario, salir
        // o añadir más productos
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Mostrar reporte inventario");
            System.out.println("2. Agregar nuevo producto");
            System.out.println("3. Actualizar cantidad de producto");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    mostrarReporte(nombres, cantidades, precios, totales, contador);
                    break;

                case 2:
                    System.out.print("Seleccione sección (0-" + (SECCIONES - 1) + "): ");
                    int sec = sc.nextInt();
                    sc.nextLine();
                    if (contador[sec] < MAX_PRODUCTOS) {
                        int idx = contador[sec];
                        System.out.print("Nombre: ");
                        nombres[sec][idx] = sc.nextLine();

                        System.out.print("Cantidad: ");
                        cantidades[sec][idx] = sc.nextInt();

                        System.out.print("Precio unitario: ");
                        precios[sec][idx] = sc.nextDouble();
                        sc.nextLine();

                        totales[sec][idx] = cantidades[sec][idx] * precios[sec][idx];
                        contador[sec]++;
                        System.out.println("✅ Producto agregado correctamente.");
                    } else {
                        System.out.println("⚠️ No hay espacio en esta sección.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese sección del producto: ");
                    sec = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre del producto a actualizar: ");
                    String buscado = sc.nextLine();

                    boolean encontrado = false;
                    for (int i = 0; i < contador[sec]; i++) {
                        if (nombres[sec][i].equalsIgnoreCase(buscado)) {
                            System.out.print("Nueva cantidad: ");
                            cantidades[sec][i] = sc.nextInt();
                            sc.nextLine();
                            totales[sec][i] = cantidades[sec][i] * precios[sec][i];
                            System.out.println("✅ Cantidad actualizada.");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("⚠️ Producto no encontrado en esa sección.");
                    }
                    break;

                case 0:
                    System.out.println("👋 Saliendo del sistema...");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // Método para mostrar el inventario completo
    public static void mostrarReporte(String[][] nombres, int[][] cantidades,
                                      double[][] precios, double[][] totales,
                                      int[] contador) {
        double inventarioTotal = 0;
        System.out.println("\n=== REPORTE INVENTARIO ===");
        for (int sec = 0; sec < nombres.length; sec++) {
            System.out.println("\nSección " + sec + ":");
            System.out.printf("%-15s %-10s %-12s %-12s\n", "Nombre", "Cantidad", "Precio U.", "Total");
            for (int i = 0; i < contador[sec]; i++) {
                System.out.printf("%-15s %-10d %-12.2f %-12.2f\n",
                        nombres[sec][i], cantidades[sec][i], precios[sec][i], totales[sec][i]);
                inventarioTotal += totales[sec][i];
            }
        }
        System.out.println("\nValor total del inventario: $" + inventarioTotal);
    }
}