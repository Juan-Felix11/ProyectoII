/**
 * @file Arbol.java
 * @brief Árbol binario para organizar libros por categoría.
 *
 * En esta clase se insertan, recorren y buscan libros por categoría o por prefijo.
 * Está pensada para integrarse con el sistema de biblioteca del proyecto, y se recomienda
 * que quien use estos métodos (desde consola o GUI) los envuelva con try-catch por si hay
 * errores o datos inválidos que puedan venir del usuario.
 *
 * @author Keylor
 */
package libreria.estructuras;
import java.text.Normalizer;

import libreria.modelo.Libro;

/**
 * Clase Árbol. Guarda libros en orden alfabético según su categoría.
 */
public class Arbol {
    private NodoArbol raiz; // Nodo raíz del árbol, empieza como null

    /** Constructor vacío: el árbol empieza sin nodos. */
    public Arbol() {
        this.raiz = null;
    }

    /**
     * Compara dos libros por su categoría, ignorando mayúsculas.
     * @param libro1 primer libro
     * @param libro2 segundo libro
     * @return un número negativo, cero o positivo dependiendo del orden
     */
    private int comparar(Libro libro1, Libro libro2) {
        return libro1.getCategoria().compareToIgnoreCase(libro2.getCategoria());
    }

    /**
     * Método para insertar un libro en el árbol.
     * @param libro el libro que queremos agregar
     */
    public void insertar(Libro libro) {
        raiz = insertarFormaRecursiva(raiz, libro);
    }

    /**
     * Inserta el libro recursivamente según la categoría.
     * @param actual nodo actual en el recorrido
     * @param libro libro nuevo a insertar
     * @return nodo actualizado con el libro agregado
     */
    private NodoArbol insertarFormaRecursiva(NodoArbol actual, Libro libro) {
        if (actual == null) return new NodoArbol(libro);

        int comparacion = comparar(libro, actual.getLibro());

        if (comparacion < 0) {
            actual.setIzquierda(insertarFormaRecursiva(actual.getIzquierda(), libro));
        } else if (comparacion > 0) {
            actual.setDerecha(insertarFormaRecursiva(actual.getDerecha(), libro));
        } else {
            System.out.println("Ya hay un libro con esa categoría.");
        }

        return actual;
    }

    /**
     * Recorre el árbol en orden y devuelve los libros en texto.
     * @return string con los libros ordenados por categoría
     */
    public String mostrarInOrden() {
        StringBuilder sb = new StringBuilder();
        mostrarInOrdenRecursivo(raiz, sb);
        return sb.toString();
    }

    /**
     * Método recursivo que recorre el árbol in-order.
     * @param actual nodo actual
     * @param sb acumulador del texto
     */
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

    /**
     * Busca un libro por categoría exacta.
     * @param categoriaBuscada texto de la categoría
     * @return el libro si se encuentra, null si no
     */
    public Libro buscarExacto(String categoriaBuscada) {
        return buscarExactoRecursivo(raiz, categoriaBuscada.toLowerCase());
    }

    /**
     * Búsqueda recursiva por categoría exacta.
     * @param actual nodo que se está revisando
     * @param categoriaBuscada texto a buscar
     * @return el libro si lo encuentra o null
     */
    private Libro buscarExactoRecursivo(NodoArbol actual, String categoriaBuscada) {
        if (actual == null) return null;

        String categoriaActual = normalizar(actual.getLibro().getCategoria());
        String categoriaBuscar = normalizar(categoriaBuscada);

        int comparacion = categoriaBuscar.compareTo(categoriaActual);

        if (comparacion == 0) return actual.getLibro();
        else if (comparacion < 0) return buscarExactoRecursivo(actual.getIzquierda(), categoriaBuscada);
        else return buscarExactoRecursivo(actual.getDerecha(), categoriaBuscada);
    }

    /**
     * Busca todos los libros que empiecen con cierto prefijo.
     * @param prefijo texto inicial de la categoría
     * @return lista con los libros que coinciden
     */
    public ListaEnlazada<Libro> buscarPorPrefijo(String prefijo) {
        ListaEnlazada<Libro> resultado = new ListaEnlazada<>();
        buscarPorPrefijoRecursivo(raiz, prefijo.toLowerCase(), resultado);
        return resultado;
    }

    /**
     * Método recursivo para buscar por prefijo.
     * @param actual nodo actual
     * @param prefijo texto buscado
     * @param resultado lista donde se guardan los libros encontrados
     */
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

    /**
     * Busca un libro que tenga ese título exacto.
     * @param titulo nombre del libro a buscar
     * @return libro si se encuentra, si no retorna null
     */
    public Libro buscarExactoPorTitulo(String titulo) {
        return buscarExactoPorTituloRecursivo(raiz, titulo);
    }

    /**
     * Búsqueda recursiva por título.
     * @param actual nodo actual
     * @param titulo texto del título a buscar
     * @return libro encontrado o null
     */
    private Libro buscarExactoPorTituloRecursivo(NodoArbol actual, String titulo) {
        if (actual == null) return null;

        String actualTitulo = normalizar(actual.getLibro().getTitulo());
        String tituloBuscar = normalizar(titulo);

        if (actualTitulo.equals(tituloBuscar)) return actual.getLibro();

        Libro encontrado = buscarExactoPorTituloRecursivo(actual.getIzquierda(), titulo);
        if (encontrado == null) {
            encontrado = buscarExactoPorTituloRecursivo(actual.getDerecha(), titulo);
        }
        return encontrado;
    }

    /**
     * Este método quita tildes y convierte texto a minúscula.
     * @param texto texto a limpiar
     * @return texto limpio
     */
    public static String normalizar(String texto) {
        texto = texto.toLowerCase();
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return texto;
    }

    /**
     * Devuelve el nodo raíz del árbol.
     * @return nodo raíz
     */
    public NodoArbol getRaiz() {
        return this.raiz;
    }

    /**
     * Dice si el árbol está vacío o no.
     * @return true si no hay nodos en el árbol
     */
    public boolean estaVacio() {
        return raiz == null;
    }
}
