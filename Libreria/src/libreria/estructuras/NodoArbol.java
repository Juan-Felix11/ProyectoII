package libreria.estructuras;
//2. ETAPA PROYECTO: Ahora crearemos una clase Nodo para el arbol

import libreria.modelo.Libro;


//@author Keylor
public class NodoArbol{//clase nodo para el arbol
  private Libro libro;//atributo libro de tipo Libro
  private NodoArbol izquierda;//nodo hijo referencia izquierda
  private NodoArbol derecha;//nodo hijo referencia derecha

  public NodoArbol(Libro libro){
    this.libro = libro;
    this.izquierda = null;//apunta a null inicialmente (no hay otros libros nodo por la izquierda)
    this.derecha = null;//apunta a null inicialmente (no hay otros libros nodo por la derecha)
  }
  //Para acceder al contenido de Libro de un nodo, navegar y modificar los hijos (ramas izquierda y derecha), ponemos getters y stters
  //setters
  public void setLibro(Libro libro){
    this.libro = libro;
  }
  public void setIzquierda(NodoArbol izquierda){
    this.izquierda = izquierda;
  }
  public void setDerecha(NodoArbol derecha){
    this.derecha = derecha;
  }
  //getters
  public Libro getLibro(){
    return libro;
  }
  public NodoArbol getIzquierda(){
    return izquierda;
  }  
  public NodoArbol getDerecha(){
    return derecha;
  }
}