/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.interfaz;

import javax.swing.*;
import java.awt.*;
import libreria.modelo.Usuario;

/**
 * @file PanelHistorial.java
 * @brief Panel para mostrar el historial de préstamos de un usuario.
 * 
 * Contiene un área de texto que muestra el historial cuando se presiona el botón "Ver historial".
 * Usa try-catch para evitar fallas inesperadas en la interfaz.
 * 
 * @author Juan Félix
 */
public class PanelHistorial extends JPanel {
    private JTextArea areaHistorial;

    /**
     * Constructor que inicializa el panel con un botón y un área de texto para el historial.
     * 
     * @param usuario Usuario cuyo historial se mostrará.
     */
    public PanelHistorial(Usuario usuario) {
        setLayout(new BorderLayout());

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false); // Solo lectura

        JButton btnVerHistorial = new JButton("Ver historial");
        setBackground(new Color(236, 240, 241)); // Gris claro
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnVerHistorial.setBackground(new Color(155, 89, 182)); // Morado
        btnVerHistorial.setForeground(Color.WHITE);
        btnVerHistorial.setFocusPainted(false);
        btnVerHistorial.setFont(new Font("Arial", Font.BOLD, 13));
        btnVerHistorial.setPreferredSize(new Dimension(150, 35)); 


        // Acción para mostrar el historial del usuario en el área de texto
        btnVerHistorial.addActionListener(e -> {
            try {
                areaHistorial.setText(""); // Limpia antes de mostrar
                areaHistorial.append("Historial de préstamos de " + usuario.getNombre() + ":\n");
                areaHistorial.append(usuario.mostrarHistorial());
            } catch (Exception ex) {
                // Mostrar error sin cerrar la app
                JOptionPane.showMessageDialog(this,
                        "Error al mostrar el historial:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(new JScrollPane(areaHistorial), BorderLayout.CENTER);
        add(btnVerHistorial, BorderLayout.SOUTH);
    }
}
