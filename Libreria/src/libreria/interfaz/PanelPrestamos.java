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
import libreria.modelo.Usuario;
import libreria.modelo.Libro;

public class PanelPrestamos extends JPanel {
    private JTextField campoCategoria;
    private JTextArea areaMensajes;

    public PanelPrestamos(Arbol arbolLibros, Usuario usuario) {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(new JLabel("Categoría:"), BorderLayout.WEST);
        campoCategoria = new JTextField();
        panelSuperior.add(campoCategoria, BorderLayout.CENTER);

        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);

        JButton btnPrestar = new JButton("Prestar libro");

        btnPrestar.addActionListener(e -> {
            String categoria = campoCategoria.getText();
            if (categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la categoría.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Libro libro = arbolLibros.buscarExacto(categoria);
            if (libro != null) {
                if (!libro.estaPrestado()) {
                    libro.prestar();
                    usuario.agregarPrestamo(libro.getTitulo());
                    areaMensajes.append("Libro prestado: " + libro.getTitulo() + "\n");
                } else {
                    areaMensajes.append("El libro ya está prestado.\n");
                }
            } else {
                areaMensajes.append("No se encontró libro en esa categoría.\n");
            }
        });

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);
        add(btnPrestar, BorderLayout.SOUTH);
    }
}
