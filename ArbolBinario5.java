//Sistema de Bibliotecas
//Caso de Uso: Un sistema para gestionar libros en una biblioteca, donde los libros se organizan por su código ISBN.
import java.util.InputMismatchException;
import java.util.Scanner;

class NodoLibro {
    String isbn;
    String titulo;
    String autor;
    NodoLibro izquierdo;
    NodoLibro derecho;

    public NodoLibro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBiblioteca {
    NodoLibro raiz;
    Scanner scanner;

    public ArbolBiblioteca() {
        this.raiz = null;
        this.scanner = new Scanner(System.in);
    }

    public void insertar(String isbn, String titulo, String autor) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede ser nulo o vacío");
        }
        
        if (raiz == null) {
            raiz = new NodoLibro(isbn, titulo, autor);
        } else {
            insertarRecursivo(raiz, isbn, titulo, autor);
        }
    }

    private void insertarRecursivo(NodoLibro nodo, String isbn, String titulo, String autor) {
        if (isbn.compareTo(nodo.isbn) < 0) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoLibro(isbn, titulo, autor);
            } else {
                insertarRecursivo(nodo.izquierdo, isbn, titulo, autor);
            }
        } else if (isbn.compareTo(nodo.isbn) > 0) {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoLibro(isbn, titulo, autor);
            } else {
                insertarRecursivo(nodo.derecho, isbn, titulo, autor);
            }
        } else {
            // Si el ISBN ya existe, actualizamos la información
            nodo.titulo = titulo;
            nodo.autor = autor;
        }
    }

    public NodoLibro buscar(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return null;
        }
        return buscarRecursivo(raiz, isbn);
    }

    private NodoLibro buscarRecursivo(NodoLibro nodo, String isbn) {
        if (nodo == null) {
            return null; // No encontrado
        }
        if (isbn.equals(nodo.isbn)) {
            return nodo; // Encontrado
        }
        return isbn.compareTo(nodo.isbn) < 0
            ? buscarRecursivo(nodo.izquierdo, isbn)
            : buscarRecursivo(nodo.derecho, isbn);
    }

    public void inorden(NodoLibro nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.println("ISBN: " + nodo.isbn + " | Título: " + nodo.titulo + " | Autor: " + nodo.autor);
            inorden(nodo.derecho);
        }
    }

    public void mostrarMenu() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE BIBLIOTECA =====");
            System.out.println("1. Agregar nuevo libro");
            System.out.println("2. Buscar libro por ISBN");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = obtenerOpcionValida();
            
            switch (opcion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    buscarLibro();
                    break;
                case 3:
                    mostrarLibros();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private int obtenerOpcionValida() {
        int opcion = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                entradaValida = true;
            } else {
                System.out.println("Error: Por favor ingrese un número válido.");
                System.out.print("Seleccione una opción: ");
                scanner.next(); // Descartar entrada inválida
            }
            scanner.nextLine(); // Limpiar buffer
        }
        
        return opcion;
    }
    
    private void agregarLibro() {
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        
        if (isbn == null || isbn.isEmpty()) {
            System.out.println("Error: El ISBN no puede estar vacío.");
            return;
        }
        
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        
        if (isbn == null || isbn.isEmpty()) {
            System.out.println("Error al agregar el libro: El ISBN no puede ser nulo o vacío");
        } else {
            insertar(isbn, titulo, autor);
            System.out.println("Libro agregado correctamente.");
        }
    }
    
    private void buscarLibro() {
        System.out.print("Ingrese el ISBN del libro a buscar: ");
        String isbn = scanner.nextLine();
        
        NodoLibro libro = buscar(isbn);
        if (libro != null) {
            System.out.println("Libro encontrado:");
            System.out.println("ISBN: " + libro.isbn);
            System.out.println("Título: " + libro.titulo);
            System.out.println("Autor: " + libro.autor);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
    
    private void mostrarLibros() {
        if (raiz == null) {
            System.out.println("La biblioteca está vacía.");
        } else {
            System.out.println("Listado de libros:");
            inorden(raiz);
        }
    }

    public static void main(String[] args) {
        ArbolBiblioteca arbolLibros = new ArbolBiblioteca();
        
        // Datos de ejemplo pre-cargados
        arbolLibros.insertar("978-3-16-148410-0", "El Principito", "Antoine de Saint-Exupéry");
        arbolLibros.insertar("978-1-56619-909-4", "Cien años de soledad", "Gabriel García Márquez");
        arbolLibros.insertar("978-0-262-03384-8", "Don Quijote de la Mancha", "Miguel de Cervantes");
        
        // Iniciar el menú interactivo
        arbolLibros.mostrarMenu();
    }
}