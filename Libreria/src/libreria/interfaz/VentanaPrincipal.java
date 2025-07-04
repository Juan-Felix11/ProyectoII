/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.interfaz;

import javax.swing.*;
import java.awt.*;
import libreria.estructuras.Arbol;
import libreria.modelo.Usuario;

/**
 * @file VentanaPrincipal.java
 * @brief Interfaz principal del sistema de biblioteca.
 * 
 * Esta ventana muestra los botones para navegar entre distintas funciones:
 * ver libros, historial, préstamos, agregar libros y devolver libros.
 * Usa `setContentPaneConBotones()` para cambiar el panel central según lo que el usuario desee hacer.
 * 
 * @author Juan Félix
 */
public class VentanaPrincipal extends JFrame {
    private Arbol arbolLibros;
    private Usuario usuario;

    /**
     * Constructor de la ventana principal.
     * Inicializa los botones y los paneles asociados a cada funcionalidad.
     *
     * @param arbolLibros Árbol binario que contiene los libros.
     * @param usuario Usuario actual que interactúa con la biblioteca.
     */
    public VentanaPrincipal(Arbol arbolLibros, Usuario usuario) {
        this.arbolLibros = arbolLibros;
        this.usuario = usuario;

        setTitle("Gestor de Biblioteca");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Panel con los botones de navegación
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 5)); // 5 botones, uno por funcionalidad

        // Botones para cada acción
        JButton btnLibros = new JButton("Ver libros");
        JButton btnHistorial = new JButton("Historial");
        JButton btnPrestamos = new JButton("Préstamos");
        JButton btnAgregarLibro = new JButton("Agregar libro");
        JButton btnDevolver = new JButton("Devolver libro");

        // Agregar los botones al panel superior
        panelBotones.add(btnLibros);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnPrestamos);
        panelBotones.add(btnAgregarLibro);
        panelBotones.add(btnDevolver);

        // Colocar panel de botones en la parte superior de la ventana
        add(panelBotones, BorderLayout.NORTH);

        // Panel central vacío al iniciar (se llena cuando se presiona un botón)
        JPanel panelCentral = new JPanel();
        add(panelCentral, BorderLayout.CENTER);

        // Crear paneles correspondientes a cada botón
        PanelLibros panelLibros = new PanelLibros(arbolLibros);
        PanelHistorial panelHistorial = new PanelHistorial(usuario);
        PanelPrestamos panelPrestamos = new PanelPrestamos(arbolLibros, usuario);
        PanelAgregarLibro panelAgregar = new PanelAgregarLibro(arbolLibros);
        PanelDevolverLibro panelDevolver = new PanelDevolverLibro(arbolLibros, usuario);

        // Asignar las acciones a cada botón para mostrar el panel correspondiente
        btnLibros.addActionListener(e -> setContentPaneConBotones(panelLibros, panelBotones));
        btnHistorial.addActionListener(e -> setContentPaneConBotones(panelHistorial, panelBotones));
        btnPrestamos.addActionListener(e -> setContentPaneConBotones(panelPrestamos, panelBotones));
        btnAgregarLibro.addActionListener(e -> setContentPaneConBotones(panelAgregar, panelBotones));
        btnDevolver.addActionListener(e -> setContentPaneConBotones(panelDevolver, panelBotones));
    
        btnLibros.setBackground(new Color(52, 152, 219));      // Azul
        btnHistorial.setBackground(new Color(155, 89, 182));   // Morado
        btnPrestamos.setBackground(new Color(241, 196, 15));   // Amarillo
        btnAgregarLibro.setBackground(new Color(46, 204, 113)); // Verde
        btnDevolver.setBackground(new Color(231, 76, 60));    // Rojo
        
        btnLibros.setForeground(Color.WHITE);
        btnHistorial.setForeground(Color.WHITE);
        btnPrestamos.setForeground(Color.BLACK);
        btnAgregarLibro.setForeground(Color.WHITE);
        btnDevolver.setForeground(Color.WHITE);

        btnLibros.setFocusPainted(false);
        btnHistorial.setFocusPainted(false);
        btnPrestamos.setFocusPainted(false);
        btnAgregarLibro.setFocusPainted(false);
        btnDevolver.setFocusPainted(false);
        
    
    }
    
    /**
     * Método auxiliar para cambiar el panel central y mantener los botones en la parte superior.
     * 
     * @param panel Panel que se desea mostrar en el centro de la ventana.
     * @param panelBotones Panel que contiene los botones de navegación.
     */
    private void setContentPaneConBotones(JPanel panel, JPanel panelBotones) {
        getContentPane().removeAll(); // Quitar todo lo que hay en la ventana
        add(panelBotones, BorderLayout.NORTH); // Volver a colocar los botones
        add(panel, BorderLayout.CENTER); // Agregar el nuevo panel seleccionado
        revalidate(); // Volver a calcular el diseño
        repaint();    // Volver a dibujar la ventana
    }
}
