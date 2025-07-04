/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.estructuras;

/**
 * @file ListaEnlazada.java
 * @brief Implementación genérica de una lista enlazada simple.
 * 
 * Permite agregar, eliminar y consultar elementos.
 * Utiliza nodos de tipo NodoLista<T>.
 * 
 * @param <T> Tipo genérico de los datos almacenados en la lista.
 * 
 * @author Juan Félix
 */
public class ListaEnlazada<T> {
    // Referencia al primer nodo de la lista
    private NodoLista<T> cabeza;

    /**
     * Constructor que inicializa una lista vacía.
     */
    public ListaEnlazada(){
        this.cabeza = null;
    }

    /**
     * Agrega un nuevo dato al final de la lista.
     * 
     * @param dato Dato de tipo T a agregar.
     */
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

    /**
     * Verifica si la lista está vacía.
     * 
     * @return true si la lista no tiene elementos, false en caso contrario.
     */
    public boolean estaVacia(){
        return cabeza == null;
    }

    /**
     * Elimina el primer nodo que contiene el dato especificado.
     * 
     * @param dato Dato a eliminar de la lista.
     */
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

    /**
     * Devuelve una representación en texto de todos los elementos de la lista.
     * 
     * @return String con cada dato precedido por "- " o mensaje si está vacía.
     */
    @Override
    public String toString() {
        if (cabeza == null) {
            return "Sin préstamos registrados.";
        }

        StringBuilder sb = new StringBuilder();
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            sb.append("- ").append(actual.getDato()).append("\n");
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }
}
