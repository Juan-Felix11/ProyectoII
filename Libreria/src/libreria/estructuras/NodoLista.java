/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.estructuras;

/**
 * @file NodoLista.java
 * @brief Nodo genérico para una lista enlazada simple.
 * 
 * Contiene un dato genérico y referencia al siguiente nodo.
 * Permite crear estructuras de listas enlazadas.
 * 
 * @param <T> Tipo genérico del dato almacenado en el nodo.
 * 
 * @author Juan Félix
 */
public class NodoLista<T> {
    // Atributos del nodo
    private T dato;
    private NodoLista<T> siguiente;

    /**
     * Constructor que inicializa el nodo con el dato dado y siguiente nulo.
     * 
     * @param dato Dato de tipo T que se almacena en el nodo.
     */
    public NodoLista(T dato){
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     * 
     * @return El dato de tipo T almacenado en el nodo.
     */
    public T getDato(){
        return this.dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     * 
     * @return NodoLista<T> que es el siguiente nodo en la lista.
     */
    public NodoLista<T> getSiguiente(){
        return this.siguiente;
    }

    /**
     * Establece un nuevo dato en el nodo.
     * 
     * @param dato Nuevo dato de tipo T para almacenar en el nodo.
     */
    public void setDato(T dato){
        this.dato = dato; 
    }

    /**
     * Establece la referencia al siguiente nodo.
     * 
     * @param siguiente NodoLista<T> que será el siguiente nodo.
     */
    public void setSiguiente(NodoLista<T> siguiente){
        this.siguiente = siguiente; 
    }

    
}
