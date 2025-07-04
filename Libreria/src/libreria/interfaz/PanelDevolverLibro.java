/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.interfaz;

import javax.swing.*;
import java.awt.*;
import libreria.modelo.Usuario;
import libreria.modelo.Libro;
import libreria.estructuras.Arbol;
import libreria.utilidades.ArchivoDeUsuarios;
/**
 * @file PanelDevolverLibro.java
 * @brief Panel para que un usuario devuelva libros prestados.
 * 
 * Permite al usuario ver los libros prestados y devolver uno por título.
 * Maneja errores con try-catch para evitar caídas inesperadas.
 * 
 * @author Juan Félix
 */
public class PanelDevolverLibro extends JPanel {
    private JTextArea areaMensajes;
    private JTextField campoTitulo;

    /**
     * Constructor que crea el panel con campo para título, área de mensajes y botones.
     * 
     * @param arbolLibros Árbol que contiene los libros para buscar el título.
     * @param usuario Usuario que realiza la devolución.
     */
    public PanelDevolverLibro(Arbol arbolLibros, Usuario usuario) {
        setLayout(new BorderLayout());

        // Panel superior para campo de texto para ingresar título a devolver
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(new JLabel("Título a devolver:"), BorderLayout.WEST);
        campoTitulo = new JTextField();
        panelSuperior.add(campoTitulo, BorderLayout.CENTER);
        setBackground(new Color(236, 240, 241)); // Gris claro
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // Área de mensajes informativos o de error
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false); // Solo lectura

        // Botones para ver libros prestados y para devolver un libro
        JButton btnVerPrestados = new JButton("Ver libros prestados");
        JButton btnDevolver = new JButton("Devolver libro");
        
        btnVerPrestados.setBackground(new Color(52, 152, 219)); // Azul
        btnVerPrestados.setForeground(Color.WHITE);
        btnVerPrestados.setFocusPainted(false);
        btnVerPrestados.setFont(new Font("Arial", Font.BOLD, 13));

        btnDevolver.setBackground(new Color(231, 76, 60)); // Rojo
        btnDevolver.setForeground(Color.WHITE);
        btnDevolver.setFocusPainted(false);
        btnDevolver.setFont(new Font("Arial", Font.BOLD, 13));

        

        // Acción para mostrar los libros prestados por el usuario
        btnVerPrestados.addActionListener(e -> {
            try {
                areaMensajes.setText("");
                areaMensajes.append("Libros prestados por " + usuario.getNombre() + ":\n");
                areaMensajes.append(usuario.mostrarHistorial());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al mostrar libros prestados:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para devolver un libro por título
        btnDevolver.addActionListener(e -> {
            try {
                String titulo = campoTitulo.getText().trim();
                if (titulo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el título.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Buscar libro en el árbol por título exacto
                Libro libro = arbolLibros.buscarExactoPorTitulo(titulo);
                if (libro != null && libro.estaPrestado()) {
                    libro.devolver();
                    usuario.getHistorial().eliminar(titulo);
                    ArchivoDeUsuarios.guardarHistorial(usuario);
                    areaMensajes.append("Libro devuelto: " + titulo + "\n");
                } else {
                    areaMensajes.append("El libro no está registrado como prestado o no existe.\n");
                }
                areaMensajes.append("\nLibros prestados por " + usuario.getNombre() + ":\n");
                areaMensajes.append(usuario.mostrarHistorial());
                

                campoTitulo.setText(""); // Limpiar campo de texto
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al devolver el libro:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel inferior que contiene los botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        panelBotones.add(btnVerPrestados);
        panelBotones.add(btnDevolver);

        // Añadir componentes al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
