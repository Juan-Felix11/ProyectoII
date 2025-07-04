/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.utilidades;

/**
 *
 * @author Juan Félix
 */
import libreria.estructuras.Arbol;
import libreria.modelo.Libro;

import java.io.*;

public class ArchivoDeLibros {

    private static final String ARCHIVO_LIBROS = "libros.txt";

    // Guardar libros
    public static void guardarLibros(Arbol arbol) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_LIBROS))) {
            guardarRecursivo(arbol.getRaiz(), pw);
        } catch (IOException e) {
            System.out.println("Error al guardar libros: " + e.getMessage());
        }
    }

    private static void guardarRecursivo(libreria.estructuras.NodoArbol nodo, PrintWriter pw) {
        if (nodo != null) {
            Libro libro = nodo.getLibro();
            pw.println(libro.getTitulo() + ";" + libro.getAutor() + ";" + libro.getCategoria() + ";" + libro.estaPrestado());
            guardarRecursivo(nodo.getIzquierda(), pw);
            guardarRecursivo(nodo.getDerecha(), pw);
        }
    }

    // Cargar libros
    public static void cargarLibros(Arbol arbol) {
        File archivo = new File(ARCHIVO_LIBROS);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    Libro libro = new Libro(partes[0], partes[1], partes[2]);
                    if (Boolean.parseBoolean(partes[3])) {
                        libro.prestar();
                    }
                    arbol.insertar(libro);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar libros: " + e.getMessage());
        }
    }
}
