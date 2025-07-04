/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.interfaz;

/**
 *
 * @author Juan Félix
 */import javax.swing.*;
import java.awt.*;
import libreria.estructuras.Arbol;
import libreria.modelo.Usuario;

public class VentanaPrincipal extends JFrame {
    private Arbol arbolLibros;
    private Usuario usuario;

    public VentanaPrincipal(Arbol arbolLibros, Usuario usuario) {
        this.arbolLibros = arbolLibros;
        this.usuario = usuario;

        setTitle("Gestor de Biblioteca");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3));

        JButton btnLibros = new JButton("Ver libros");
        JButton btnHistorial = new JButton("Historial");
        JButton btnPrestamos = new JButton("Préstamos");
        JButton btnAgregarLibro = new JButton("Agregar libro");
        JButton btnDevolver = new JButton("Devolver libro");
        
        panelBotones.add(btnLibros);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnPrestamos);
        panelBotones.add(btnAgregarLibro);
        panelBotones.add(btnDevolver);

        add(panelBotones, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        add(panelCentral, BorderLayout.CENTER);

        // Crear paneles
        PanelLibros panelLibros = new PanelLibros(arbolLibros);
        PanelHistorial panelHistorial = new PanelHistorial(usuario);
        PanelPrestamos panelPrestamos = new PanelPrestamos(arbolLibros, usuario);
        PanelAgregarLibro panelAgregar = new PanelAgregarLibro(arbolLibros);
        PanelDevolverLibro panelDevolver = new PanelDevolverLibro(arbolLibros, usuario);

        // Acciones
        btnLibros.addActionListener(e -> setContentPaneConBotones(panelLibros, panelBotones));
        btnHistorial.addActionListener(e -> setContentPaneConBotones(panelHistorial, panelBotones));
        btnPrestamos.addActionListener(e -> setContentPaneConBotones(panelPrestamos, panelBotones));
        btnAgregarLibro.addActionListener(e -> setContentPaneConBotones(panelAgregar, panelBotones));
        btnDevolver.addActionListener(e -> setContentPaneConBotones(panelDevolver, panelBotones));
    }

    private void setContentPaneConBotones(JPanel panel, JPanel panelBotones) {
        getContentPane().removeAll();
        add(panelBotones, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
