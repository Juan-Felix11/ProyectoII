package libreria.estructuras;
/**
 * @file ArbolDeLibros.java
 * @brief Clase que representa un árbol binario de búsqueda de libros ordenados por categoría.
 *
 * Esta clase permite insertar libros, recorrerlos en orden (in-order), buscar por categoría exacta
 * y buscar por prefijo. Está pensada para integrarse con el sistema de biblioteca.
 *
 * @author Keylor
 * @version 1.0
 */
//3.ETAPA DEL PROYECTO: ahora necesitamos crear un Arbol binario de busqueda, a partir de lo que tenemos ya creado con clase Nodo y Libro
import libreria.modelo.Libro;
import java.util.*; //importamos librería para utilizar listas y colecciones

//Importamos la clase Libro creada por mi compañero, que tiene atributos como titulo, autor y categoria, y también sus métodos.
//Este Libro será la base de datos para el árbol.
/**
 * Clase que representa el árbol binario de búsqueda para organizar libros por categoría.
 */
public class ArbolDeLibros { //creamos clase ArbolDeLibros que representa el árbol binario de búsqueda
  private NodoArbol raiz; ///< Nodo raíz del árbol binario 
  // Nodo raíz del árbol, inicializa con null cuando está vacío

      /**
     * Constructor que inicializa el árbol vacío.
     */
  public ArbolDeLibros() { //constructor que inicializa el árbol como vacío
    this.raiz = null; // árbol vacío al inicio
  }

  // Método para comparar libros solo por categoría (como dijo el profesor)
  // Devuelve un número negativo, cero o positivo dependiendo de la comparación entre las categorías
      /**
     * Compara dos libros por su categoría (sin distinción entre mayúsculas y minúsculas).
     *
     * @param libro1 Primer libro a comparar.
     * @param libro2 Segundo libro a comparar.
     * @return Valor negativo si libro1 < libro2, positivo si >, 0 si iguales.
     */
  private int comparar(Libro libro1, Libro libro2) {
    return libro1.getCategoria().compareToIgnoreCase(libro2.getCategoria()); //compara alfabéticamente sin distinguir mayúsculas
  }

  // Método público para insertar un libro en el árbol
  // Este método se puede llamar desde fuera para agregar libros disponibles
      /**
     * Inserta un libro en el árbol binario.
     *
     * @param libro Libro que se desea insertar.
     */
  public void insertar(Libro libro) {
    raiz = insertarFormaRecursiva(raiz, libro); //llamamos a la versión recursiva que se encarga de colocarlo en la posición adecuada
  }

  // Método recursivo para insertar un libro dentro del árbol
  // Este método usa la comparación de categoría para decidir si va a la izquierda o derecha
      /**
     * Inserta un libro de forma recursiva.
     *
     * @param actual Nodo actual del recorrido.
     * @param libro Libro a insertar.
     * @return Nodo actualizado después de insertar.
     */
  private NodoArbol insertarFormaRecursiva(NodoArbol actual, Libro libro) {
    if (actual == null) return new NodoArbol(libro); //si llegamos a un nodo vacío, insertamos el nuevo nodo aquí

    int comparacion = comparar(libro, actual.getLibro()); //comparamos las categorías del libro a insertar y el actual

    if (comparacion < 0) { //si el libro va antes (alfabéticamente) va a la izquierda
      actual.setIzquierda(insertarFormaRecursiva(actual.getIzquierda(), libro));
    } else if (comparacion > 0) { //si el libro va después, va a la derecha
      actual.setDerecha(insertarFormaRecursiva(actual.getDerecha(), libro));
    } else { //si es igual, no lo insertamos porque ya existe esa categoría
      System.out.println("El libro ya existe en el arbol por categoria.");
    }

    return actual; //retornamos el nodo actual para mantener el árbol correctamente enlazado
  }

  // Método nuevo para mostrar los libros en orden ascendente por categoría
  // Esto se logra con un recorrido in-order: izquierda - nodo actual - derecha

    /**
     * Muestra los libros ordenados por categoría (in-order).
     */
  public void mostrarInOrden() {
    System.out.println("Mostrando libros disponibles en orden por categoria:");
    mostrarInOrdenRecursivo(raiz); //iniciamos el recorrido desde la raíz
  }

  // Este método recorre el árbol en orden (in-order)
  // Va a la izquierda primero, luego imprime el nodo actual, luego va a la derecha
  // Esto asegura que los libros se muestren ordenados por categoría


    /**
     * Recorre el árbol en orden (in-order) e imprime los libros.
     *
     * @param actual Nodo actual durante el recorrido.
     */
  private void mostrarInOrdenRecursivo(NodoArbol actual) {
    if (actual != null) {
      mostrarInOrdenRecursivo(actual.getIzquierda()); //llamada recursiva a la izquierda (categorías menores alfabéticamente)
      //imprimimos los datos del libro actual (título, autor y categoría)
      System.out.println("Categoria: " + actual.getLibro().getCategoria() +
                         " | Titulo: " + actual.getLibro().getTitulo() +
                         " | Autor: " + actual.getLibro().getAutor());
      mostrarInOrdenRecursivo(actual.getDerecha()); //llamada recursiva a la derecha (categorías mayores)
    }
  }

  // Buscar libro por categoría exacta
  // Este método permite encontrar un libro que coincida exactamente con la categoría buscada

      /**
     * Busca un libro por categoría exacta.
     *
     * @param categoria Categoría exacta a buscar.
     * @return Libro encontrado o null si no se encuentra.
     */
  public Libro buscarExacto(String categoriaBuscada) {
    return buscarExactoRecursivo(raiz, categoriaBuscada.toLowerCase()); //iniciamos desde la raíz y convertimos a minúsculas para evitar errores de mayúsculas
  }

  // Método recursivo para buscar un libro exacto por categoría

      /**
     * Método recursivo para buscar un libro por categoría exacta.
     *
     * @param actual Nodo actual del recorrido.
     * @param categoria Categoría a buscar.
     * @return Libro si se encuentra, null si no.
     */
  private Libro buscarExactoRecursivo(NodoArbol actual, String categoriaBuscada) {
    if (actual == null) return null; //si llegamos a un nodo vacío, el libro no está en el árbol

    String categoriaActual = actual.getLibro().getCategoria().toLowerCase(); //obtenemos la categoría actual y la convertimos a minúsculas
    int comparacion = categoriaBuscada.compareTo(categoriaActual); //comparamos la categoría buscada con la actual

    if (comparacion == 0) return actual.getLibro(); //si son iguales, encontramos el libro
    else if (comparacion < 0)
      return buscarExactoRecursivo(actual.getIzquierda(), categoriaBuscada); //si la buscada es menor, vamos a la izquierda
    else
      return buscarExactoRecursivo(actual.getDerecha(), categoriaBuscada); //si es mayor, vamos a la derecha
  }

  // Buscar libros por prefijo de categoría
  // Este método retorna una lista de libros cuya categoría empieza con el texto indicado

      /**
     * Busca libros cuya categoría comience con un prefijo.
     *
     * @param prefijo Texto inicial de la categoría.
     * @return Lista de libros que coinciden con el prefijo.
     */
  public List<Libro> buscarPorPrefijo(String prefijo) {
    List<Libro> resultado = new ArrayList<>(); //lista que va a contener todos los libros encontrados
    buscarPorPrefijoRecursivo(raiz, prefijo.toLowerCase(), resultado); //iniciamos búsqueda desde la raíz
    return resultado; //retornamos la lista de coincidencias
  }

  // Método recursivo para buscar libros que empiecen con un prefijo

      /**
     * Método recursivo para buscar libros por prefijo de categoría.
     *
     * @param actual Nodo actual del recorrido.
     * @param prefijo Prefijo buscado.
     * @param lista Lista de resultados.
     */
  private void buscarPorPrefijoRecursivo(NodoArbol actual, String prefijo, List<Libro> resultado) {
    if (actual == null) return; //si llegamos a un nodo vacío, no hay nada que buscar

    String categoria = actual.getLibro().getCategoria().toLowerCase(); //convertimos la categoría actual a minúsculas

    if (categoria.startsWith(prefijo)) { //si la categoría empieza con el prefijo buscado
      resultado.add(actual.getLibro()); //agregamos este libro al resultado
      buscarPorPrefijoRecursivo(actual.getIzquierda(), prefijo, resultado); //seguimos buscando en ambos lados del árbol
      buscarPorPrefijoRecursivo(actual.getDerecha(), prefijo, resultado);
    } else {
      if (categoria.compareTo(prefijo) > 0)
        buscarPorPrefijoRecursivo(actual.getIzquierda(), prefijo, resultado); //si la categoría actual es mayor que el prefijo, vamos a la izquierda
      else
        buscarPorPrefijoRecursivo(actual.getDerecha(), prefijo, resultado); //si es menor, vamos a la derecha
    }
  }
}
