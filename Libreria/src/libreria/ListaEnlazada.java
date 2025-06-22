/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria;

/**
 *
 * @author Juan Félix
 */
public class ListaEnlazada<T> {
    //Atributo del primer objeto 
    private NodoLista<T> cabeza;
    
    //Constructor vacío 
    public ListaEnlazada(){
        this.cabeza = null;
    }
    
    public void agregar(T dato){
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if (cabeza == null){
            cabeza = nuevo;
        } else{
            NodoLista<T> actual = cabeza;
            while (actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        
    }
    
    //Método para ver si la lista esta vacia
    public boolean estaVacia(){
        return cabeza == null;
    }
    
    //Método para eliminar un nodo de la lista 
    public void eliminar(T dato){
        if (cabeza == null) return;
        
        if (cabeza.getDato().equals(dato)){
            cabeza = cabeza.getSiguiente();
            return;
        }
        NodoLista<T> actual = cabeza;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(dato)){
            actual = actual.getSiguiente();   
        }
        if (actual.getSiguiente() != null){
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
        }
    }
    
    
    
    
}
