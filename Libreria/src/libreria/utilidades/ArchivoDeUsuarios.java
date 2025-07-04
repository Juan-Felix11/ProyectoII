/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.utilidades;

import libreria.modelo.Usuario;
import libreria.estructuras.ListaEnlazada;
import libreria.estructuras.NodoLista;
import java.io.*;

/**
 * @file ArchivoDeUsuarios.java
 * @brief Clase utilitaria para manejar el guardado y carga del historial de préstamos de cada usuario.
 * 
 * Esta clase ofrece métodos estáticos para guardar y cargar el historial de libros prestados por un usuario
 * en archivos de texto individuales, basados en el nombre del usuario.
 * 
 * @author Juan Félix
 */
public class ArchivoDeUsuarios {

    /**
     * Guarda el historial de préstamos de un usuario en un archivo de texto.
     * El archivo se llama "historial_{nombre}.txt", usando el nombre del usuario.
     *
     * @param usuario Objeto Usuario del cual se desea guardar el historial.
     */
  public static void guardarHistorial(Usuario usuario) {
    try (PrintWriter writer = new PrintWriter(new FileWriter("historial_" + usuario.getNombre() + ".txt", false))) {
        NodoLista actual = usuario.getHistorial().getCabeza();
        if (actual == null) {
            writer.println("No hay préstamos registrados.");
        } else {
            while (actual != null) {
                writer.println(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
    } catch (IOException e) {
        System.out.println("Error al guardar historial: " + e.getMessage());
    }
}


    /**
     * Carga el historial de préstamos desde un archivo de texto y lo asigna al usuario.
     * Si el archivo no existe, simplemente se omite.
     *
     * @param usuario Objeto Usuario al que se le asignará el historial cargado.
     */
    public static void cargarHistorial(Usuario usuario) {
        String nombreArchivo = "historial_" + usuario.getNombre() + ".txt";
        File archivo = new File(nombreArchivo);

        // Si el archivo no existe, no se hace nada
        if (!archivo.exists()) return;

        // Se intenta leer el archivo línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Se ignoran líneas vacías o en blanco
                if (!linea.trim().isEmpty()) {
                    // Se asume que cada línea tiene el formato "- Título"
                    // Eliminamos el guion y los espacios para quedarnos solo con el título
                    String titulo = linea.replaceFirst("- ", "").trim();
                    
                    // Se agrega al historial del usuario
                    usuario.agregarPrestamo(titulo);
                }
            }
        } catch (IOException e) {
            // Si algo falla al leer, se informa
            System.out.println("Error al cargar historial: " + e.getMessage());
        }
    }
}
