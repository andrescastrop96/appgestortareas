//Sistema de Archivos
//Caso de Uso: Un sistema de archivos donde los archivos se organizan por su nombre. 
//Un árbol binario puede ayudar a mantener la estructura de directorios y facilitar la búsqueda de archivos.
class NodoArchivo {
    String nombre;
    NodoArchivo izquierdo;
    NodoArchivo derecho;

    public NodoArchivo(String nombre) {
        this.nombre = nombre;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinario8 {
    NodoArchivo raiz;
    java.util.Scanner scanner;

    public ArbolBinario8() {
        this.raiz = null;
        this.scanner = new java.util.Scanner(System.in);
    }

    public void insertar(String nombre) {
        if (raiz == null) {
            raiz = new NodoArchivo(nombre);
        } else {
            insertarRecursivo(raiz, nombre);
        }
    }

    private void insertarRecursivo(NodoArchivo nodo, String nombre) {
        if (nombre.compareTo(nodo.nombre) < 0) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoArchivo(nombre);
            } else {
                insertarRecursivo(nodo.izquierdo, nombre);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoArchivo(nombre);
            } else {
                insertarRecursivo(nodo.derecho, nombre);
            }
        }
    }

    public boolean buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private boolean buscarRecursivo(NodoArchivo nodo, String nombre) {
        if (nodo == null) {
            return false;
        }
        if (nombre.equals(nodo.nombre)) {
            return true;
        }
        return nombre.compareTo(nodo.nombre) < 0
            ? buscarRecursivo(nodo.izquierdo, nombre)
            : buscarRecursivo(nodo.derecho, nombre);
    }

    public void inorden(NodoArchivo nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.println(nodo.nombre);
            inorden(nodo.derecho);
        }
    }
    
    private void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE ARCHIVOS =====");
        System.out.println("1. Agregar nuevo archivo");
        System.out.println("2. Buscar archivo");
        System.out.println("3. Mostrar todos los archivos");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private int obtenerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            System.out.print("Seleccione una opción: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }
    
    private void agregarArchivo() {
        System.out.print("Ingrese el nombre del archivo: ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre del archivo no puede estar vacío.");
            return;
        }
        
        insertar(nombre);
        System.out.println("Archivo agregado correctamente.");
    }
    
    private void buscarArchivo() {
        System.out.print("Ingrese el nombre del archivo a buscar: ");
        String nombre = scanner.nextLine().trim();
        
        boolean encontrado = buscar(nombre);
        if (encontrado) {
            System.out.println("El archivo '" + nombre + "' fue encontrado.");
        } else {
            System.out.println("El archivo '" + nombre + "' no existe en el sistema.");
        }
    }
    
    private void mostrarArchivos() {
        if (raiz == null) {
            System.out.println("No hay archivos en el sistema.");
        } else {
            System.out.println("Listado de archivos:");
            inorden(raiz);
        }
    }
    
    public void ejecutarSistema() {
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcion();
            
            if (opcion == 1) {
                agregarArchivo();
            } else if (opcion == 2) {
                buscarArchivo();
            } else if (opcion == 3) {
                mostrarArchivos();
            } else if (opcion == 4) {
                System.out.println("Saliendo del sistema de archivos...");
                continuar = false;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void main(String[] args) {
        ArbolBinario8 arbolArchivos = new ArbolBinario8();
        
        // Inserción de archivos de ejemplo
        arbolArchivos.insertar("documento.txt");
        arbolArchivos.insertar("imagen.png");
        arbolArchivos.insertar("musica.mp3");
        
        // Iniciar el sistema interactivo
        arbolArchivos.ejecutarSistema();
    }
}