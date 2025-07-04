/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.utilidades;

/**
 *
 * @author Juan Félix
 */
import libreria.modelo.Usuario;

import java.io.*;

public class ArchivoDeUsuarios{

    // Guardar historial del usuario
    public static void guardarHistorial(Usuario usuario) {
        String nombreArchivo = "historial_" + usuario.getNombre() + ".txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            pw.print(usuario.mostrarHistorial()); // mostrarHistorial devuelve String
        } catch (IOException e) {
            System.out.println("Error al guardar historial: " + e.getMessage());
        }
    }

    // Cargar historial del usuario
    public static void cargarHistorial(Usuario usuario) {
        String nombreArchivo = "historial_" + usuario.getNombre() + ".txt";
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    // Se espera formato "- Título", quitamos el guión si existe
                    String titulo = linea.replaceFirst("- ", "").trim();
                    usuario.agregarPrestamo(titulo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar historial: " + e.getMessage());
        }
    }
}
