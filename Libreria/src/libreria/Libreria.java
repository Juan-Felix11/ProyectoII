package libreria;

//Archivo Libreria.java
//Este va a ser el archivo principal para ejecutar el sistema y probar el funcionamiento básico del árbol binario con consola

import java.util.Scanner;

public class Libreria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //para leer desde teclado
        ArbolDeLibros arbol = new ArbolDeLibros(); //creamos el árbol que vamos a usar

        int opcion;
        do {
            System.out.println("\n--- Menu Biblioteca ---");
            System.out.println("1. Insertar libro");
            System.out.println("2. Mostrar libros (in-order)");
            System.out.println("3. Buscar libro por categoria exacta");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción invalida. Intente de nuevo.");
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el titulo del libro: ");
                    String titulo = sc.nextLine();
                    System.out.print("Ingrese el autor del libro: ");
                    String autor = sc.nextLine();
                    System.out.print("Ingrese la categoria del libro: ");
                    String categoria = sc.nextLine();

                    Libro nuevo = new Libro(categoria, autor, titulo); //Creamos el objeto Libro
                    arbol.insertar(nuevo); //Insertamos en el árbol
                    System.out.println("Libro insertado correctamente.");
                    break;

                case 2:
                    arbol.mostrarInOrden(); //mostrar en orden por categoría
                    break;

                case 3:
                    System.out.print("Ingrese la categoria exacta a buscar: ");
                    String buscada = sc.nextLine();
                    Libro resultado = arbol.buscarExacto(buscada);
                    if (resultado != null) {
                        System.out.println("\nLibro encontrado:");
                        System.out.println("Categoria: " + resultado.getCategoria());
                        System.out.println("Titulo: " + resultado.getTitulo());
                        System.out.println("Autor: " + resultado.getAutor());
                    } else {
                        System.out.println("No se encontro ningun libro con esa categoria.");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }

        } while (opcion != 4);

        sc.close();
    }
}

