# ProyectoII
El siguiente repositorio se estara utilizando para el desarrollo del segundo proyecto de programación I.

# Sistema de Gestión de Biblioteca

Este proyecto corresponde a una aplicación de escritorio desarrollada en el lenguaje de programación Java. Su objetivo es simular el funcionamiento de una biblioteca digital simple, en la que un usuario puede visualizar, prestar, devolver y registrar libros mediante una interfaz gráfica. El sistema está estructurado con base en árboles binarios de búsqueda y listas enlazadas, incorporando además persistencia de datos por medio de archivos de texto.

Autores:  
- Keylor Arley Castro  
- Juan Félix Mata

---

## Instrucciones de ejecución

### Requisitos previos

Para ejecutar correctamente este proyecto se requiere lo siguiente:

- Tener instalado Java 8 o superior.
- Un entorno de desarrollo compatible con Java (VS code, NetBeans, IntelliJ IDEA o Eclipse).
- Un sistema operativo compatible (Windows, macOS o alguna distribución Linux).

### Pasos para ejecutar el sistema

1. Descargar o clonar el proyecto completo.
2. Abrir el proyecto desde el entorno de desarrollo de su preferencia.
3. Compilar todos los archivos del paquete.
4. Ejecutar la clase principal ubicada en:  
   `libreria.libreria.Libreria.java`

Al ejecutarse, la aplicación desplegará una ventana con botones que permiten al usuario interactuar con las diferentes funcionalidades del sistema.

---

## Descripción del uso del sistema

### Ingreso del usuario

Al iniciar el programa, se solicita el nombre del usuario. Si se deja vacío o se cancela la ventana de entrada, el sistema asignará por defecto el nombre "Invitado". Posteriormente, el sistema intentará cargar el historial de libros prestados previamente por ese usuario desde un archivo de texto específico.

### Interfaz gráfica

La aplicación presenta una interfaz gráfica con las siguientes opciones accesibles desde botones en la parte superior de la ventana:

- **Ver libros**: Muestra en pantalla todos los libros registrados en el sistema, organizados en orden alfabético (inorden) a partir del árbol binario.
- **Agregar libro**: Permite al usuario registrar un nuevo libro proporcionando su título, autor y categoría.
- **Préstamos**: Solicita al usuario una categoría. Si existe un libro no prestado en esa categoría, se presta automáticamente y se registra en el historial del usuario.
- **Historial**: Muestra los libros que el usuario ha prestado, según el historial cargado desde el archivo correspondiente.
- **Devolver libro**: Permite al usuario ingresar el título de un libro y devolverlo, si se encuentra actualmente registrado como prestado.

---

## Estructura del sistema

El sistema está compuesto por las siguientes clases distribuidas en paquetes:

### Paquete `libreria.libreria`

- `Libreria.java`: Clase principal del proyecto. Se encarga de cargar datos, solicitar información al usuario, iniciar la interfaz gráfica y guardar los datos al cerrar.

### Paquete `libreria.modelo`

- `Libro.java`: Representa un libro con atributos como título, autor, categoría y estado de préstamo.
- `Usuario.java`: Contiene el nombre del usuario y su historial de préstamos implementado mediante una lista enlazada.

### Paquete `libreria.estructuras`

- `NodoArbol.java`: Nodo del árbol binario que almacena un libro y tiene referencias a sus hijos izquierdo y derecho.
- `Arbol.java`: Implementación del árbol binario de búsqueda para insertar y buscar libros.
- `NodoLista.java`: Nodo genérico para lista enlazada.
- `ListaEnlazada.java`: Lista enlazada genérica utilizada para almacenar el historial de préstamos.

### Paquete `libreria.utilidades`

- `ArchivoDeLibros.java`: Guarda y carga el conjunto de libros desde un archivo de texto plano.
- `ArchivoDeUsuarios.java`: Guarda y carga el historial de cada usuario en un archivo independiente por nombre.

### Paquete `libreria.interfaz`

- `VentanaPrincipal.java`: Interfaz principal con los botones que redirigen a los distintos paneles.
- `PanelLibros.java`: Muestra todos los libros.
- `PanelAgregarLibro.java`: Permite agregar un libro nuevo.
- `PanelPrestamos.java`: Administra el préstamo de libros según categoría.
- `PanelHistorial.java`: Muestra el historial del usuario.
- `PanelDevolverLibro.java`: Permite devolver un libro ingresando el título.

---

## Manejo de errores

El sistema incorpora bloques `try-catch` para manejar adecuadamente los errores más comunes. Por ejemplo:

- Si se presenta un error al cargar o guardar archivos, se muestra un mensaje de error claro al usuario, pero el programa continúa funcionando.
- En caso de ingreso inválido por parte del usuario (campos vacíos, datos inexistentes), se notifican mediante mensajes emergentes sin afectar la ejecución del sistema.

Este enfoque permite una experiencia de uso más robusta y evita caídas inesperadas.

---

## Documentación interna

Todo el código está comentado utilizando el formato estándar de **Doxygen**, incluyendo:

- Descripción de cada clase.
- Explicación de cada método, con detalles sobre:
  - Parámetros de entrada.
  - Valores de retorno (cuando aplica).
  - Comportamiento del método.
- Comentarios internos para aclarar el propósito de bloques específicos de código.

La documentación está escrita en un lenguaje claro, adecuado para otros programadores o docentes que revisen el proyecto.

---

## Archivos generados

Durante la ejecución, el sistema genera dos tipos de archivos:

- **libros.txt**: Contiene la información de todos los libros registrados, uno por línea.
- **historial_<nombre_usuario>.txt**: Archivo individual para cada usuario, donde se registran los libros que ha prestado.

Estos archivos permiten persistir los datos entre una ejecución y otra.

---

## Observaciones finales

Este proyecto fue desarrollado con el objetivo de aplicar conceptos fundamentales de estructuras de datos, lógica de programación orientada a objetos, manejo de archivos y diseño de interfaces gráficas en Java. Está diseñado para ser claro, funcional y fácil de extender.


