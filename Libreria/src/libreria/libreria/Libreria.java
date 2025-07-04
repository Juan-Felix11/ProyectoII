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
import java.awt.*;

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
        Arbol arbol = new Arbol();

        // ✅ Cargar libros desde archivo
        ArchivoDeLibros.cargarLibros(arbol);

        if (arbol.estaVacio()) {
            arbol.insertar(new Libro("Cien años de soledad", "Gabriel García Márquez", "Novela"));
            arbol.insertar(new Libro("El principito", "Antoine de Saint-Exupéry", "Fábula"));
            arbol.insertar(new Libro("1984", "George Orwell", "Distopía"));
        }

        String nombre = "Invitado"; // valor por defecto

        try {
            JPanel panel = new JPanel(new BorderLayout(5, 5));
            panel.setBackground(new Color(236, 240, 241));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel label = new JLabel("¡Bienvenido a la Biblioteca!");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setForeground(new Color(52, 73, 94));

            JTextField textField = new JTextField();
            textField.setBackground(Color.WHITE);
            textField.setForeground(new Color(44, 62, 80));

            panel.add(label, BorderLayout.NORTH);
            panel.add(textField, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese su nombre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION && !textField.getText().trim().isEmpty()) {
                nombre = textField.getText().trim();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al capturar el nombre. Se usará 'Invitado'.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al pedir nombre: " + e.getMessage());
        }

        Usuario usuario = new Usuario(nombre);

        // ✅ Cargar historial
        ArchivoDeUsuarios.cargarHistorial(usuario);

        // ✅ Mostrar GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal(arbol, usuario).setVisible(true);
        });

        // ✅ Guardar datos al cerrar
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ArchivoDeLibros.guardarLibros(arbol);
            ArchivoDeUsuarios.guardarHistorial(usuario);
        }));
     }
}