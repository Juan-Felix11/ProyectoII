/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria;

/**
 *
 * @author Juan Félix
 */
public class Libro {
    //Atributos del objeto libro 
    private String titulo;
    private String autor;
    private String categoria;
    private boolean estadoPrestamo;
    
    
    //Constructor del libro 
    public Libro(String titulo, String autor, String categoria){
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria; 
        this.estadoPrestamo = false; //Por defecto el libro se va a encontrar disponible. 
    }
    
    //Getters 
    public String getTitulo(){
        return this.titulo;
    }
    public String getAutor(){
        return this.autor;
    }
    public String getCategoria(){
        return this.categoria;
    }
    public boolean getEstadoPrestamo(){
        return this.estadoPrestamo;
    }
    
    //Setters 
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public void setEstadoPrestamo(boolean estadoPrestamo){
        this.estadoPrestamo = estadoPrestamo;   
    }
    
    //Marcar libro como prestado
    public void prestar(){
        this.estadoPrestamo = true;
    }
    
    //Marcar libro como devuelto 
    public void devolver(){
        this.estadoPrestamo = false;
    }
    
    @Override
    public String toString(){
        return String.format("Titulo: %s  | Autor: %s  | Categoria: %s  | Estado: %s",
                titulo, autor, categoria, estadoPrestamo ? "Prestado" : "Disponible");
    }
    
    //Método para comparar libros por titulo (útil para usar con el arbol binario)
    public int compararPorTitulo(Libro otro){
        return this.titulo.compareToIgnoreCase(otro.getTitulo());
    }
}
