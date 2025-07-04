/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.interfaz;

import javax.swing.*;
import java.awt.*;
import libreria.estructuras.Arbol;
import libreria.modelo.Libro;

/**
 * @file PanelAgregarLibro.java
 * @brief Panel que permite agregar un nuevo libro al árbol de libros.
 * 
 * Contiene campos para título, autor y categoría, y un botón para agregar el libro.
 * Utiliza try-catch para manejar errores y validar entradas.
 * 
 * @author Juan Félix
 */
public class PanelAgregarLibro extends JPanel {
    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JTextField campoCategoria;
    private JTextArea areaMensajes;

    /**
     * Constructor que inicializa los campos y el botón para agregar libros.
     * 
     * @param arbolLibros Árbol donde se insertarán los nuevos libros.
     */
    public PanelAgregarLibro(Arbol arbolLibros) {
        setLayout(new BorderLayout());

        // Panel con etiquetas y campos para título, autor y categoría
        JPanel panelCampos = new JPanel(new GridLayout(3, 2));

        panelCampos.add(new JLabel("Título:"));
        campoTitulo = new JTextField();
        panelCampos.add(campoTitulo);

        panelCampos.add(new JLabel("Autor:"));
        campoAutor = new JTextField();
        panelCampos.add(campoAutor);

        panelCampos.add(new JLabel("Categoría:"));
        campoCategoria = new JTextField();
        panelCampos.add(campoCategoria);

        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false); // Solo lectura para mensajes

        JButton btnAgregar = new JButton("Agregar libro");

        // Acción para agregar un libro nuevo al árbol
        btnAgregar.addActionListener(e -> {
            try {
                String titulo = campoTitulo.getText().trim();
                String autor = campoAutor.getText().trim();
                String categoria = campoCategoria.getText().trim();

                // Validar que ningún campo esté vacío
                if (titulo.isEmpty() || autor.isEmpty() || categoria.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Libro nuevoLibro = new Libro(titulo, autor, categoria);
                arbolLibros.insertar(nuevoLibro);

                areaMensajes.append("Libro agregado: " + titulo + "\n");

                // Limpiar campos después de agregar
                campoTitulo.setText("");
                campoAutor.setText("");
                campoCategoria.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al agregar el libro:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panelCampos, BorderLayout.NORTH);
        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);
        add(btnAgregar, BorderLayout.SOUTH);
    }
}
