package libreria.libreria;

// Este va a ser el archivo principal para ejecutar el sistema
// y probar el funcionamiento básico del árbol binario con consola e interfaz gráfica.

import libreria.modelo.Usuario;
import libreria.modelo.Libro;
import libreria.estructuras.Arbol;
import libreria.interfaz.VentanaPrincipal;
import libreria.utilidades.ArchivoDeLibros;
import libreria.utilidades.ArchivoDeUsuarios;

import javax.swing.*;

/**
 * @file Libreria.java
 * @brief Clase principal que inicia el sistema de biblioteca.
 * 
 * Se encarga de cargar los libros desde archivo, crear un usuario, cargar su historial
 * y mostrar la interfaz gráfica. Al cerrarse, guarda los datos.
 */
public class Libreria {

    /**
     * Método principal del sistema.
     * Aquí se inicializa el árbol de libros, se cargan los datos desde archivos,
     * se solicita el nombre del usuario, y se lanza la interfaz gráfica.
     * Al cerrar, se guarda todo nuevamente.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Arbol arbol = new Arbol(); // Árbol binario para guardar los libros

        try {
            // Intentar cargar libros desde archivo
            ArchivoDeLibros.cargarLibros(arbol);
        } catch (Exception e) {
            System.out.println("Error al cargar los libros: " + e.getMessage());
        }

        // Si el árbol está vacío (no hay archivo o está vacío), agregar algunos libros de prueba
        if (arbol.estaVacio()) {
            arbol.insertar(new Libro("Cien años de soledad", "Gabriel García Márquez", "Novela"));
            arbol.insertar(new Libro("El principito", "Antoine de Saint-Exupéry", "Fábula"));
            arbol.insertar(new Libro("1984", "George Orwell", "Distopía"));
        }

        String nombre;
        try {
            // Pedir nombre del usuario con JOptionPane
            nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre:", "Bienvenido", JOptionPane.PLAIN_MESSAGE);
            if (nombre == null || nombre.trim().isEmpty()) {
                nombre = "Invitado";
            }
        } catch (Exception e) {
            // Si ocurre algún error con JOptionPane, usar nombre por defecto
            System.out.println("Error al leer nombre del usuario, se usará 'Invitado'.");
            nombre = "Invitado";
        }

        Usuario usuario = new Usuario(nombre); // Crear objeto usuario

        try {
            // Cargar historial desde archivo (si existe)
            ArchivoDeUsuarios.cargarHistorial(usuario);
        } catch (Exception e) {
            System.out.println("Error al cargar historial del usuario: " + e.getMessage());
        }

        // Mostrar la interfaz gráfica de la biblioteca
        try {
            javax.swing.SwingUtilities.invokeLater(() -> {
                new VentanaPrincipal(arbol, usuario).setVisible(true);
            });
        } catch (Exception e) {
            System.out.println("Error al iniciar la interfaz gráfica: " + e.getMessage());
        }

        // Guardar libros e historial automáticamente al cerrar el programa
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ArchivoDeLibros.guardarLibros(arbol);
            } catch (Exception e) {
                System.out.println("Error al guardar los libros: " + e.getMessage());
            }

            try {
                ArchivoDeUsuarios.guardarHistorial(usuario);
            } catch (Exception e) {
                System.out.println("Error al guardar el historial del usuario: " + e.getMessage());
            }
        }));
    }
}
