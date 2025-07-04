/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.modelo;

import libreria.estructuras.ListaEnlazada;
import libreria.estructuras.NodoLista;

/**
 * @file Usuario.java
 * @brief Representa a un usuario del sistema con nombre e historial de préstamos.
 * 
 * Cada usuario tiene una lista enlazada de títulos de libros prestados.
 * 
 * @author Juan Félix
 */
public class Usuario {
    private String nombre; 
    private ListaEnlazada<String> historial; // Almacena el historial de préstamos (títulos de libros)

    /**
     * Constructor que crea un usuario con nombre y lista vacía de historial.
     * 
     * @param nombre Nombre del usuario.
     */
    public Usuario(String nombre){
        this.nombre = nombre;
        this.historial = new ListaEnlazada<>();
    }

    // Getters

    /**
     * Devuelve el nombre del usuario.
     * 
     * @return Nombre del usuario.
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Devuelve la lista enlazada con el historial de préstamos.
     * 
     * @return ListaEnlazada con los títulos prestados.
     */
    public ListaEnlazada<String> getHistorial(){
        return this.historial; 
    }

    // Setters

    /**
     * Cambia el nombre del usuario.
     * 
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Agrega un título de libro al historial de préstamos del usuario.
     * 
     * @param tituloLibro Título del libro prestado.
     */
    public void agregarPrestamo(String tituloLibro){
        historial.agregar(tituloLibro); 
    }

    /**
     * Muestra el historial de préstamos del usuario en formato de texto.
     * 
     * @return String con los títulos prestados o mensaje si no hay.
     */
   public String mostrarHistorial() {
    StringBuilder sb = new StringBuilder();
    NodoLista<String> actual = historial.getCabeza();

    if (actual == null) {
        sb.append("- No hay préstamos registrados.\n");
    } else {
        while (actual != null) {
            sb.append("- ").append(actual.getDato()).append("\n");
            actual = actual.getSiguiente();
        }
    }

    return sb.toString();
}


    /**
     * Representación del usuario como texto.
     * 
     * @return Nombre del usuario.
     */
    @Override
    public String toString(){
        return "Nombre: " + nombre;
    }
}
