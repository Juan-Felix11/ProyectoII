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
import libreria.modelo.Usuario;

public class PanelHistorial extends JPanel {
    private JTextArea areaHistorial;

    public PanelHistorial(Usuario usuario) {
        setLayout(new BorderLayout());

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);

        JButton btnVerHistorial = new JButton("Ver historial");

        btnVerHistorial.addActionListener(e -> {
            areaHistorial.setText("");
            areaHistorial.append("Historial de préstamos de " + usuario.getNombre() + ":\n");
            areaHistorial.append(usuario.mostrarHistorial());
        });

        add(new JScrollPane(areaHistorial), BorderLayout.CENTER);
        add(btnVerHistorial, BorderLayout.SOUTH);
    }
}
