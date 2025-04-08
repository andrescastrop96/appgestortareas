//Sistema de Gestión de Estudiantes
//Caso de Uso: En una universidad, se necesita un sistema para gestionar los registros de los estudiantes, donde cada estudiante tiene un ID único. 
//Los árboles binarios permiten realizar inserciones y búsquedas rápidas de estudiantes.
import java.util.Scanner;

class NodoEstudiante {
    int id;
    String nombre;
    String carrera;
    double promedio;
    NodoEstudiante izquierdo;
    NodoEstudiante derecho;

    public NodoEstudiante(int id, String nombre, String carrera, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.promedio = promedio;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolEstudiantes {
    NodoEstudiante raiz;
    Scanner scanner;

    public ArbolEstudiantes() {
        this.raiz = null;
        this.scanner = new Scanner(System.in);
    }

    public void insertar(int id, String nombre, String carrera, double promedio) {
        if (raiz == null) {
            raiz = new NodoEstudiante(id, nombre, carrera, promedio);
        } else {
            insertarRecursivo(raiz, id, nombre, carrera, promedio);
        }
    }

    private void insertarRecursivo(NodoEstudiante nodo, int id, String nombre, String carrera, double promedio) {
        if (id < nodo.id) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoEstudiante(id, nombre, carrera, promedio);
            } else {
                insertarRecursivo(nodo.izquierdo, id, nombre, carrera, promedio);
            }
        } else if (id > nodo.id) {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoEstudiante(id, nombre, carrera, promedio);
            } else {
                insertarRecursivo(nodo.derecho, id, nombre, carrera, promedio);
            }
        } else {
            // Si el ID ya existe, actualizamos la información
            nodo.nombre = nombre;
            nodo.carrera = carrera;
            nodo.promedio = promedio;
        }
    }

    public NodoEstudiante buscar(int id) {
        return buscarRecursivo(raiz, id);
    }

    private NodoEstudiante buscarRecursivo(NodoEstudiante nodo, int id) {
        if (nodo == null) {
            return null;
        }
        if (id == nodo.id) {
            return nodo;
        }
        return id < nodo.id
            ? buscarRecursivo(nodo.izquierdo, id)
            : buscarRecursivo(nodo.derecho, id);
    }

    public void inorden(NodoEstudiante nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.println("ID: " + nodo.id + " | Nombre: " + nodo.nombre + 
                              " | Carrera: " + nodo.carrera + " | Promedio: " + nodo.promedio);
            inorden(nodo.derecho);
        }
    }
    
    public void mostrarMenu() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE ESTUDIANTES =====");
            System.out.println("1. Registrar nuevo estudiante");
            System.out.println("2. Buscar estudiante por ID");
            System.out.println("3. Mostrar todos los estudiantes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = obtenerEnteroValido();
            
            if (opcion == 1) {
                registrarEstudiante();
            } else if (opcion == 2) {
                buscarEstudiante();
            } else if (opcion == 3) {
                mostrarEstudiantes();
            } else if (opcion == 4) {
                System.out.println("Saliendo del sistema...");
                continuar = false;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private int obtenerEnteroValido() {
        int valor = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                entradaValida = true;
            } else {
                System.out.println("Error: Por favor ingrese un número válido.");
                System.out.print("Seleccione una opción: ");
                scanner.next(); // Descartar entrada inválida
            }
            scanner.nextLine(); // Limpiar buffer
        }
        
        return valor;
    }
    
    private double obtenerDoubleValido() {
        double valor = 0.0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                entradaValida = true;
            } else {
                System.out.println("Error: Por favor ingrese un número válido.");
                System.out.print("Ingrese nuevamente: ");
                scanner.next(); // Descartar entrada inválida
            }
            scanner.nextLine(); // Limpiar buffer
        }
        
        return valor;
    }
    
    private void registrarEstudiante() {
        System.out.print("Ingrese el ID del estudiante: ");
        int id = obtenerEnteroValido();
        
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese la carrera del estudiante: ");
        String carrera = scanner.nextLine();
        
        System.out.print("Ingrese el promedio del estudiante: ");
        double promedio = obtenerDoubleValido();
        
        insertar(id, nombre, carrera, promedio);
        System.out.println("Estudiante registrado correctamente.");
    }
    
    private void buscarEstudiante() {
        System.out.print("Ingrese el ID del estudiante a buscar: ");
        int id = obtenerEnteroValido();
        
        NodoEstudiante estudiante = buscar(id);
        if (estudiante != null) {
            System.out.println("Estudiante encontrado:");
            System.out.println("ID: " + estudiante.id);
            System.out.println("Nombre: " + estudiante.nombre);
            System.out.println("Carrera: " + estudiante.carrera);
            System.out.println("Promedio: " + estudiante.promedio);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
    
    private void mostrarEstudiantes() {
        if (raiz == null) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("Listado de estudiantes:");
            inorden(raiz);
        }
    }

    public static void main(String[] args) {
        ArbolEstudiantes arbolEstudiantes = new ArbolEstudiantes();
        
        // Datos de ejemplo pre-cargados
        arbolEstudiantes.insertar(1001, "Alice Johnson", "Ingeniería Informática", 9.2);
        arbolEstudiantes.insertar(1003, "Bob Smith", "Medicina", 8.7);
        arbolEstudiantes.insertar(1002, "Charlie Brown", "Arquitectura", 7.9);
        arbolEstudiantes.insertar(1005, "Diana Miller", "Derecho", 8.5);
        arbolEstudiantes.insertar(1004, "Edward Davis", "Economía", 7.6);
        
        // Iniciar el sistema interactivo
        arbolEstudiantes.mostrarMenu();
    }
}