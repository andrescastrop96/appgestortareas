//Caso de Uso: Sistema de Gestión de Directorio Telefónico
//Funcionalidades
//Agregar Contactos: Permitir agregar nuevos contactos al árbol.
//Buscar Contactos: Buscar un contacto por su nombre.
//Eliminar Contactos: Eliminar un contacto del árbol.
//Recorridos:
//Recorrido en orden (inorden).
//Recorrido preorden.
//Recorrido postorden.
//Mostrar Todos los Contactos: Imprimir todos los contactos en orden alfabético.
import java.util.Scanner;

class NodoContacto {
    String nombre;
    String telefono;
    NodoContacto izquierdo;
    NodoContacto derecho;

    public NodoContacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinario11 {
    NodoContacto raiz;
    Scanner scanner;

    public ArbolBinario11() {
        this.raiz = null;
        this.scanner = new Scanner(System.in);
    }

    // Método para insertar un nuevo contacto
    public void insertar(String nombre, String telefono) {
        raiz = insertarRecursivo(raiz, nombre, telefono);
    }

    private NodoContacto insertarRecursivo(NodoContacto nodo, String nombre, String telefono) {
        if (nodo == null) {
            return new NodoContacto(nombre, telefono);
        }
        if (nombre.compareTo(nodo.nombre) < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, nombre, telefono);
        } else if (nombre.compareTo(nodo.nombre) > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, nombre, telefono);
        } else {
            // Si el nombre ya existe, actualiza el teléfono
            nodo.telefono = telefono;
        }
        return nodo;
    }

    // Método para buscar un contacto por nombre
    public String buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private String buscarRecursivo(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null; // No encontrado
        }
        if (nombre.equals(nodo.nombre)) {
            return nodo.telefono; // Contacto encontrado
        }
        return nombre.compareTo(nodo.nombre) < 0
                ? buscarRecursivo(nodo.izquierdo, nombre)
                : buscarRecursivo(nodo.derecho, nombre);
    }

    // Método para eliminar un contacto
    public void eliminar(String nombre) {
        raiz = eliminarRecursivo(raiz, nombre);
    }

    private NodoContacto eliminarRecursivo(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null; // No encontrado
        }
        if (nombre.compareTo(nodo.nombre) < 0) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, nombre);
        } else if (nombre.compareTo(nodo.nombre) > 0) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, nombre);
        } else {
            // Nodo encontrado
            if (nodo.izquierdo == null) {
                return nodo.derecho; // Reemplaza con el hijo derecho
            } else if (nodo.derecho == null) {
                return nodo.izquierdo; // Reemplaza con el hijo izquierdo
            }
            // Nodo con dos hijos: encuentra el mínimo en el subárbol derecho
            nodo.nombre = encontrarMinimo(nodo.derecho).nombre;
            nodo.telefono = encontrarMinimo(nodo.derecho).telefono;
            nodo.derecho = eliminarRecursivo(nodo.derecho, nodo.nombre);
        }
        return nodo;
    }

    private NodoContacto encontrarMinimo(NodoContacto nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    // Recorrido en orden
    public void inorden(NodoContacto nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.println(nodo.nombre + ": " + nodo.telefono);
            inorden(nodo.derecho);
        }
    }

    // Recorrido preorden
    public void preorden(NodoContacto nodo) {
        if (nodo != null) {
            System.out.println(nodo.nombre + ": " + nodo.telefono);
            preorden(nodo.izquierdo);
            preorden(nodo.derecho);
        }
    }

    // Recorrido postorden
    public void postorden(NodoContacto nodo) {
        if (nodo != null) {
            postorden(nodo.izquierdo);
            postorden(nodo.derecho);
            System.out.println(nodo.nombre + ": " + nodo.telefono);
        }
    }

    public void mostrarMenu() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("Opciones:");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Mostrar contactos (inorden)");
            System.out.println("5. Mostrar contactos (preorden)");
            System.out.println("6. Mostrar contactos (postorden)");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                System.out.print("Nombre: ");
                String nombreAgregar = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefonoAgregar = scanner.nextLine();
                insertar(nombreAgregar, telefonoAgregar);
            } else if (opcion.equals("2")) {
                System.out.print("Nombre a buscar: ");
                String nombreBuscar = scanner.nextLine();
                String telefonoEncontrado = buscar(nombreBuscar);
                if (telefonoEncontrado != null) {
                    System.out.println("Teléfono de " + nombreBuscar + ": " + telefonoEncontrado);
                } else {
                    System.out.println("Contacto no encontrado.");
                }
            } else if (opcion.equals("3")) {
                System.out.print("Nombre a eliminar: ");
                String nombreEliminar = scanner.nextLine();
                eliminar(nombreEliminar);
            } else if (opcion.equals("4")) {
                System.out.println("Contactos (inorden):");
                inorden(raiz);
            } else if (opcion.equals("5")) {
                System.out.println("Contactos (preorden):");
                preorden(raiz);
            } else if (opcion.equals("6")) {
                System.out.println("Contactos (postorden):");
                postorden(raiz);
            } else if (opcion.equals("0")) {
                System.out.println("Saliendo...");
                continuar = false;
            } else {
                System.out.println("Opción no válida.");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArbolBinario11 directorio = new ArbolBinario11();
        
        // Agregar algunos contactos de ejemplo
        directorio.insertar("Juan Pérez", "555-1234");
        directorio.insertar("María López", "555-5678");
        directorio.insertar("Carlos Gómez", "555-9012");
        
        // Iniciar el sistema interactivo
        directorio.mostrarMenu();
        
        directorio.scanner.close();
    }
}