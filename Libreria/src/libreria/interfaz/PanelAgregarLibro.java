/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.interfaz;

/**
 *
 * @author Juan Félix
 */
import javax.swing.*;
import java.awt.*;
import libreria.estructuras.Arbol;
import libreria.modelo.Libro;

public class PanelAgregarLibro extends JPanel {
    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JTextField campoCategoria;
    private JTextArea areaMensajes;

    public PanelAgregarLibro(Arbol arbolLibros) {
        setLayout(new BorderLayout());

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
        areaMensajes.setEditable(false);

        JButton btnAgregar = new JButton("Agregar libro");

        btnAgregar.addActionListener(e -> {
            String titulo = campoTitulo.getText();
            String autor = campoAutor.getText();
            String categoria = campoCategoria.getText();

            if (titulo.isEmpty() || autor.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Libro nuevoLibro = new Libro(titulo, autor, categoria);
            arbolLibros.insertar(nuevoLibro);

            areaMensajes.append("Libro agregado: " + titulo + "\n");

            // Limpiar campos
            campoTitulo.setText("");
            campoAutor.setText("");
            campoCategoria.setText("");
        });

        add(panelCampos, BorderLayout.NORTH);
        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);
        add(btnAgregar, BorderLayout.SOUTH);
    }
}
