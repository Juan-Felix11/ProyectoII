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

public class PanelLibros extends JPanel {
    private JTextArea areaLibros;

    public PanelLibros(Arbol arbolLibros) {
        setLayout(new BorderLayout());

        areaLibros = new JTextArea();
        areaLibros.setEditable(false);

        JButton btnMostrar = new JButton("Mostrar libros");

       btnMostrar.addActionListener(e -> {
    areaLibros.setText(""); // limpia
    areaLibros.append("Mostrando libros disponibles:\n");
    areaLibros.append(arbolLibros.mostrarInOrden());
    });

        add(new JScrollPane(areaLibros), BorderLayout.CENTER);
        add(btnMostrar, BorderLayout.SOUTH);
    }
}
