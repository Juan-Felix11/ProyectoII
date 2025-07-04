/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.interfaz;

import javax.swing.*;
import java.awt.*;
import libreria.estructuras.Arbol;

/**
 * @file PanelLibros.java
 * @brief Panel para mostrar en pantalla la lista de libros disponibles en el árbol.
 * 
 * Contiene un área de texto que muestra los libros cuando se presiona el botón "Mostrar libros".
 * Se protege con try-catch para evitar fallas inesperadas.
 * 
 * @author Juan Félix
 */
public class PanelLibros extends JPanel {
    private JTextArea areaLibros;

    /**
     * Constructor que inicializa el panel con un botón y un área de texto.
     * 
     * @param arbolLibros Árbol binario que contiene los libros para mostrar.
     */
    public PanelLibros(Arbol arbolLibros) {
        setLayout(new BorderLayout());

        areaLibros = new JTextArea();
        areaLibros.setEditable(false); // Solo lectura

        JButton btnMostrar = new JButton("Mostrar libros");

        // Acción para mostrar los libros disponibles en el área de texto
        btnMostrar.addActionListener(e -> {
            try {
                areaLibros.setText(""); // Limpia el área antes de mostrar
                areaLibros.append("Mostrando libros disponibles:\n");
                areaLibros.append(arbolLibros.mostrarInOrden());
            } catch (Exception ex) {
                // Mostrar mensaje de error sin detener la app
                JOptionPane.showMessageDialog(this,
                        "Error al mostrar los libros:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(new JScrollPane(areaLibros), BorderLayout.CENTER);
        add(btnMostrar, BorderLayout.SOUTH);
    }
}
