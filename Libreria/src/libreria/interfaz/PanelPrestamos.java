/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.interfaz;

import javax.swing.*;
import java.awt.*;
import libreria.estructuras.Arbol;
import libreria.modelo.Usuario;
import libreria.modelo.Libro;

/**
 * @file PanelPrestamos.java
 * @brief Panel que permite al usuario prestar un libro según la categoría indicada.
 * 
 * El usuario escribe la categoría, el sistema busca el libro en el árbol y, si está disponible,
 * lo marca como prestado y lo agrega al historial del usuario.
 * 
 * Maneja errores con try-catch para que la interfaz no se bloquee ante excepciones.
 * 
 * @author Juan Félix
 */
public class PanelPrestamos extends JPanel {
    private JTextField campoCategoria;
    private JTextArea areaMensajes;

    /**
     * Constructor que arma el panel con campo de categoría, botón para prestar y área de mensajes.
     *
     * @param arbolLibros Árbol que contiene los libros.
     * @param usuario Usuario que está realizando el préstamo.
     */
    public PanelPrestamos(Arbol arbolLibros, Usuario usuario) {
        setLayout(new BorderLayout());

        // Panel superior con etiqueta y campo de texto para ingresar categoría
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(new JLabel("Categoría:"), BorderLayout.WEST);

        campoCategoria = new JTextField();
        panelSuperior.add(campoCategoria, BorderLayout.CENTER);
        setBackground(new Color(236, 240, 241)); // Gris claro
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Área donde se muestran mensajes informativos o de error
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false); // Solo lectura

        // Botón que inicia el intento de préstamo
        JButton btnPrestar = new JButton("Prestar libro");
        
        btnPrestar.setBackground(new Color(46, 204, 113)); // Verde
        btnPrestar.setForeground(Color.WHITE);
        btnPrestar.setFocusPainted(false);
        btnPrestar.setFont(new Font("Arial", Font.BOLD, 13));


        // Acción del botón: buscar libro por categoría y prestarlo si está disponible
        btnPrestar.addActionListener(e -> {
            String categoria = campoCategoria.getText();

            // Validar que se haya ingresado una categoría
            if (categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la categoría.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Buscar libro en el árbol por categoría exacta
                Libro libro = arbolLibros.buscarExacto(categoria);

                if (libro != null) {
                    if (!libro.estaPrestado()) {
                        // Marcar como prestado y agregar al historial del usuario
                        libro.prestar();
                        usuario.agregarPrestamo(libro.getTitulo());
                        areaMensajes.append("Libro prestado: " + libro.getTitulo() + "\n");
                    } else {
                        areaMensajes.append("El libro ya está prestado.\n");
                    }
                } else {
                    areaMensajes.append("No se encontró libro en esa categoría.\n");
                }
            } catch (Exception ex) {
                // Captura cualquier error inesperado sin cerrar la aplicación
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error al procesar el préstamo:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar los componentes al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);
        add(btnPrestar, BorderLayout.SOUTH);
    }
}
