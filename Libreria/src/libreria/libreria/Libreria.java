package libreria.libreria;
//Este va a ser el archivo principal para ejecutar el sistema y probar el funcionamiento básico del árbol binario con consola

import libreria.modelo.Usuario;
import libreria.modelo.Libro;
import libreria.estructuras.Arbol;
import libreria.interfaz.VentanaPrincipal;
import libreria.utilidades.ArchivoDeLibros;
import libreria.utilidades.ArchivoDeUsuarios;

import javax.swing.*;

public class Libreria{
    public static void main(String[] args) {
        Arbol arbol = new Arbol();

        // ✅ Cargar libros desde archivo
        ArchivoDeLibros.cargarLibros(arbol);

        // ✅ Si el archivo no tiene libros, agregar algunos de prueba
        if (arbol.estaVacio()) {
            arbol.insertar(new Libro("Cien años de soledad", "Gabriel García Márquez", "Novela"));
            arbol.insertar(new Libro("El principito", "Antoine de Saint-Exupéry", "Fábula"));
            arbol.insertar(new Libro("1984", "George Orwell", "Distopía"));
        }

        // ✅ Pedir nombre al usuario
        String nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre:", "Bienvenido", JOptionPane.PLAIN_MESSAGE);
        if (nombre == null || nombre.trim().isEmpty()) {
            nombre = "Invitado";
        }
        Usuario usuario = new Usuario(nombre);

        // ✅ Cargar historial del usuario
        ArchivoDeUsuarios.cargarHistorial(usuario);

        // ✅ Mostrar GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal(arbol, usuario).setVisible(true);
        });

        // ✅ Guardar libros e historial al cerrar
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ArchivoDeLibros.guardarLibros(arbol);
            ArchivoDeUsuarios.guardarHistorial(usuario);
        }));
    }
}
