/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.modelo;

/**
 * @file Libro.java
 * @brief Representa un libro con título, autor, categoría y estado de préstamo.
 * 
 * Incluye métodos para prestar, devolver y comparar libros por título.
 * Se usa como base para el árbol binario de la librería.
 * 
 * @author Juan Félix
 */
public class Libro {
    // Atributos del objeto libro
    private String titulo;
    private String autor;
    private String categoria;
    private boolean estadoPrestamo;

    /**
     * Constructor que inicializa un libro como disponible.
     * 
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     * @param categoria Categoría o género del libro.
     */
    public Libro(String titulo, String autor, String categoria){
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria; 
        this.estadoPrestamo = false; // Por defecto, el libro está disponible
    }

    // Getters

    /**
     * Obtiene el título del libro.
     * @return Título del libro.
     */
    public String getTitulo(){
        return this.titulo;
    }

    /**
     * Obtiene el autor del libro.
     * @return Autor del libro.
     */
    public String getAutor(){
        return this.autor;
    }

    /**
     * Obtiene la categoría del libro.
     * @return Categoría del libro.
     */
    public String getCategoria(){
        return this.categoria;
    }

    /**
     * Devuelve el estado de préstamo del libro.
     * @return true si está prestado, false si está disponible.
     */
    public boolean getEstadoPrestamo(){
        return this.estadoPrestamo;
    }

    // Setters

    /**
     * Establece un nuevo título.
     * @param titulo Título del libro.
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Establece un nuevo autor.
     * @param autor Autor del libro.
     */
    public void setAutor(String autor){
        this.autor = autor;
    }

    /**
     * Establece una nueva categoría.
     * @param categoria Categoría del libro.
     */
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    /**
     * Cambia el estado de préstamo.
     * @param estadoPrestamo true si el libro está prestado.
     */
    public void setEstadoPrestamo(boolean estadoPrestamo){
        this.estadoPrestamo = estadoPrestamo;   
    }

    /**
     * Indica si el libro está prestado.
     * @return true si está prestado, false si no.
     */
    public boolean estaPrestado() {
        return this.estadoPrestamo;
    }

    /**
     * Marca el libro como prestado.
     */
    public void prestar(){
        this.estadoPrestamo = true;
    }

    /**
     * Marca el libro como devuelto.
     */
    public void devolver(){
        this.estadoPrestamo = false;
    }

    /**
     * Representación en texto del libro.
     * @return Cadena con título, autor, categoría y estado.
     */
    @Override
    public String toString(){
        return String.format("Titulo: %s  | Autor: %s  | Categoria: %s  | Estado: %s",
                titulo, autor, categoria, estadoPrestamo ? "Prestado" : "Disponible");
    }

    /**
     * Compara dos libros alfabéticamente por título, ignorando mayúsculas.
     * 
     * @param otro Otro libro a comparar.
     * @return Valor negativo si el actual va antes, 0 si es igual, positivo si va después.
     */
    public int compararPorTitulo(Libro otro){
        return this.titulo.compareToIgnoreCase(otro.getTitulo());
    }
}
