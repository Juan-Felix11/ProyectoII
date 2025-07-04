/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.estructuras;
import libreria.modelo.Libro;

import java.text.Normalizer;
/**
 *
 * @author Juan Félix
 */
public class Arbol {
    private NodoArbol raiz;

    public Arbol() {
        this.raiz = null;
    }

    private int comparar(Libro libro1, Libro libro2) {
        return libro1.getCategoria().compareToIgnoreCase(libro2.getCategoria());
    }

    public void insertar(Libro libro) {
        raiz = insertarFormaRecursiva(raiz, libro);
    }

    private NodoArbol insertarFormaRecursiva(NodoArbol actual, Libro libro) {
        if (actual == null) {
            return new NodoArbol(libro);
        }

        int comparacion = comparar(libro, actual.getLibro());

        if (comparacion < 0) {
            actual.setIzquierda(insertarFormaRecursiva(actual.getIzquierda(), libro));
        } else if (comparacion > 0) {
            actual.setDerecha(insertarFormaRecursiva(actual.getDerecha(), libro));
        } else {
            System.out.println("El libro ya existe en el árbol por categoría.");
        }

        return actual;
    }

   public String mostrarInOrden() {
    StringBuilder sb = new StringBuilder();
    mostrarInOrdenRecursivo(raiz, sb);
    return sb.toString();
}

private void mostrarInOrdenRecursivo(NodoArbol actual, StringBuilder sb) {
    if (actual != null) {
        mostrarInOrdenRecursivo(actual.getIzquierda(), sb);
        sb.append("Categoría: ").append(actual.getLibro().getCategoria())
          .append(" | Título: ").append(actual.getLibro().getTitulo())
          .append(" | Autor: ").append(actual.getLibro().getAutor())
          .append("\n");
        mostrarInOrdenRecursivo(actual.getDerecha(), sb);
    }
}


    public Libro buscarExacto(String categoriaBuscada) {
        return buscarExactoRecursivo(raiz, categoriaBuscada.toLowerCase());
    }

   private Libro buscarExactoRecursivo(NodoArbol actual, String categoriaBuscada) {
    if (actual == null) return null;

    String categoriaActual = normalizar(actual.getLibro().getCategoria());
    String categoriaBuscar = normalizar(categoriaBuscada);

    int comparacion = categoriaBuscar.compareTo(categoriaActual);

    if (comparacion == 0) return actual.getLibro();
    else if (comparacion < 0) return buscarExactoRecursivo(actual.getIzquierda(), categoriaBuscada);
    else return buscarExactoRecursivo(actual.getDerecha(), categoriaBuscada);
}


    // 🚨 Adaptación: usar ListaEnlazada
    public ListaEnlazada<Libro> buscarPorPrefijo(String prefijo) {
        ListaEnlazada<Libro> resultado = new ListaEnlazada<>();
        buscarPorPrefijoRecursivo(raiz, prefijo.toLowerCase(), resultado);
        return resultado;
    }

    private void buscarPorPrefijoRecursivo(NodoArbol actual, String prefijo, ListaEnlazada<Libro> resultado) {
    if (actual == null) return;

    String categoria = normalizar(actual.getLibro().getCategoria());
    String prefijoNormalizado = normalizar(prefijo);

    if (categoria.startsWith(prefijoNormalizado)) {
        resultado.agregar(actual.getLibro());
        buscarPorPrefijoRecursivo(actual.getIzquierda(), prefijo, resultado);
        buscarPorPrefijoRecursivo(actual.getDerecha(), prefijo, resultado);
    } else {
        if (categoria.compareTo(prefijoNormalizado) > 0) {
            buscarPorPrefijoRecursivo(actual.getIzquierda(), prefijo, resultado);
        } else {
            buscarPorPrefijoRecursivo(actual.getDerecha(), prefijo, resultado);
            }
        }
    }

    
   public static String normalizar(String texto) {
       texto = texto.toLowerCase(); // pasar a minúsculas
       texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
       texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); // quitar tildes
       return texto;
    } 
    
    
    public NodoArbol getRaiz(){
        return this.raiz;
    }
    
    public boolean estaVacio() {
        return raiz == null;
    }
}
