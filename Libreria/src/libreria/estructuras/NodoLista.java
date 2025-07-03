/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.estructuras;

/**
 *
 * @author Juan Félix
 */
public class NodoLista<T> {
    //Atributos del nodo 
    private T dato;
    private NodoLista<T> siguiente;
    
    //Constructor de nodos 
    public NodoLista(T dato){
    this.dato = dato;
    this.siguiente = null;
    }
    
    //Getters
    public T getDato(){
        return this.dato;
    }
    public NodoLista<T> getSiguiente(){
        return this.siguiente;
    }
    
    //Setters 
    public void setDato(T dato){
        this.dato = dato; 
    }
    public void setSiguiente(NodoLista<T> siguiente){
        this.siguiente = siguiente; 
    }
      
    
    
            
    
    
}
