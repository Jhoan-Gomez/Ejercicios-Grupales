# Ejercicios en Java


## Introducción
Nuestro objetivo es la creación de un programa interactivo en Java donde se registre datos de los productos desde nombre, cantidad y precio y posteriormente emita una factura indicando al usuario que compro.

## Librerías
Tenemos que importar primero un Scanner, el cual nos permite obtener datos primitivos.


## Constantes y Matrices
En este ejemplo podemos ver que es necesario definir correctamente con un valor las constantes, las cuales deben estar en Mayúsculas asi cómo es necesario la creación de matrices utilizando un String para los nombres, int para cantidades así como double para todo lo relacionado con precios ya que vamos a trabajar con decimales.
```
        final int SECCIONES = 2;
        final int MAX_PRODUCTOS = 10;
        String[][] nombres = new String[SECCIONES][MAX_PRODUCTOS];
        int[][] cantidades = new int[SECCIONES][MAX_PRODUCTOS];
        double[][] precios = new double[SECCIONES][MAX_PRODUCTOS];
        double[][] totales = new double[SECCIONES][MAX_PRODUCTOS];
```
## Uso del For
El "For" es un bucle que se repite 5 veces, podemos que ver que la i es igual a cero inicialmente pero cada que se realiza el proceso para la toma de datos de un producto aumenta en 1. Coloca como limite que debe ser menor a 5.
```
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
```
## Menú de Opciones Disponbiles y el Switch
Vamos a utilizar una variable llamada "opcion", la cual no es permitiría elegir la siguiente acción utilizando un Switch que es una estructura de control a través de Case dependiendo de la opción que eliga, la cual esta registrada en un número
```
int opcion;
        do {
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Mostrar reporte inventario");
            System.out.println("2. Agregar nuevo producto");
            System.out.println("3. Actualizar cantidad de producto");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
```
## Utilizando el Case (Primero y Segundo)
El primer case que vamos a utilizar muestra el reporte inicial con los datos del producto. Adicionalmente, realiza un break para parar el proceso.
```
case 1:
                    // Mostrar inventario completo
                    mostrarReporte(nombres, cantidades, precios, totales, contador);
                    break;
```
El segundo case se utiliza para agregar un nuevo producto, este un poco más complicado que el anterior donde utiliza un sistema de secciones. Ahora vamos utilizar dos If.

En el cual si cumple la condición de que la Sección sea menor que 0 o (Representado como || en el código Java) que el número ingresado sea mayor o igual a la constante (En esta caso es SECCIONES y representando en Mayúsculas)

Por ejemplo, si ```SECCIONES = 3```, las secciones válidas son 0, 1, 2.
## Analizando el Case (Tercero y Cero)
Toman en referencia al Caso 3, aplicariamos If con concidiciones similares al anterior para el Case 3, donde utilizaremos un boolean llamado "encontrado" el cual puede ser false o true para poder identificar si el producto que ingreso el usuario inicialmente coincide con el quiere cambiar ahora. Si se realiza un cambio de cantidad correctamente "encontrado" será considerado como true
```
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
```
Por último, el Caso 0 es simplemente un break con un mensaje diciendo que está saliendo del sistema. Adicionalmente también vamos a usar un While, donde si la opción no es 0 va repertir el mismo menú hasta que el usuario decida salir finalmente.
```
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
```
## Mostrar Reporte
Necesitamos crear un public static void para mostrarle el reporte al usuario con información que recolectamos finalmente. Utilizamos "%-15s" y valores similares para poder ordenar los datos en una forma de tabla. Principalmente vamos a utilizar los valores de sec y i para mostrar los datos en cada columna y fila en su respectivo campo.
```
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
```
