/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.modelo;

import libreria.estructuras.ListaEnlazada;

/**
 *
 * @author Juan Félix
 */
public class Usuario {
    private String nombre; 
    private ListaEnlazada<String> historial; //Almacena el historial de préstamos (titulos de libros)
    
    public Usuario(String nombre){
        this.nombre = nombre;
        this.historial = new ListaEnlazada<>();
    }
    
    //Getters
    public String getNombre(){
        return this.nombre;
    }
    public ListaEnlazada<String> getHistorial(){
        return this.historial; 
    }
    
    //Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    //Agregar un libro al historial 
    public void agregarPrestamo(String tituloLibro){
        historial.agregar(tituloLibro); 
    }
    
    //Mostrar el historial como String
    public String mostrarHistorial(){
        if (historial.estaVacia()){
            return "No hay prestamos registrados: ";
        }
        return historial.toString();  
    }
    
    @Override
    public String toString(){
        return "Nombre: " + nombre;
    }
    
}
