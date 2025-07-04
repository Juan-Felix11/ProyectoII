package libreria.utilidades;

/**
 * @file ArchivoDeLibros.java
 * @brief Clase encargada de guardar y cargar libros desde un archivo.
 *
 * Esta clase se encarga de escribir los libros almacenados en el árbol
 * hacia un archivo de texto, y también permite cargarlos nuevamente.
 * Usa manejo de errores con try-catch para evitar caídas del sistema.
 *
 * @author Keylor
 */

import libreria.estructuras.Arbol;
import libreria.estructuras.NodoArbol;
import libreria.modelo.Libro;
import java.io.*;

public class ArchivoDeLibros {

    private static final String ARCHIVO_LIBROS = "libros.txt"; // Nombre del archivo

    /**
     * Guarda todos los libros del árbol en un archivo de texto.
     * Cada línea representa un libro con formato: titulo;autor;categoria;prestado
     * @param arbol Árbol que contiene los libros
     */
    public static void guardarLibros(Arbol arbol) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_LIBROS))) {
            guardarRecursivo(arbol.getRaiz(), pw);
        } catch (IOException e) {
            System.out.println("Error al guardar libros: " + e.getMessage());
        }
    }

    /**
     * Método recursivo que recorre el árbol en in-order y guarda los libros.
     * @param nodo Nodo actual
     * @param pw PrintWriter para escribir al archivo
     */
    private static void guardarRecursivo(NodoArbol nodo, PrintWriter pw) {
        if (nodo != null) {
            Libro libro = nodo.getLibro();
            pw.println(libro.getTitulo() + ";" + libro.getAutor() + ";" + libro.getCategoria() + ";" + libro.estaPrestado());
            guardarRecursivo(nodo.getIzquierda(), pw);
            guardarRecursivo(nodo.getDerecha(), pw);
        }
    }

    /**
     * Carga los libros desde un archivo y los inserta en el árbol.
     * Si el archivo no existe o hay error de lectura, se maneja con try-catch.
     * @param arbol Árbol donde se insertarán los libros
     */
    public static void cargarLibros(Arbol arbol) {
        File archivo = new File(ARCHIVO_LIBROS);
        if (!archivo.exists()) return; // Si no hay archivo, no hay nada que cargar

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    Libro libro = new Libro(partes[0], partes[1], partes[2]);
                    if (Boolean.parseBoolean(partes[3])) {
                        libro.prestar(); // Si estaba prestado, lo marcamos igual
                    }
                    arbol.insertar(libro);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar libros: " + e.getMessage());
        }
    }
} 
