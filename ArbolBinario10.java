//Análisis de Datos
//Caso de Uso: En un sistema de análisis de datos, donde se necesita almacenar y buscar rápidamente valores numéricos, como estadísticas de ventas.
import java.util.Scanner;

class NodoDatos {
    int valor;
    NodoDatos izquierdo;
    NodoDatos derecho;

    public NodoDatos(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolDatos {
    NodoDatos raiz;
    Scanner scanner;

    public ArbolDatos() {
        this.raiz = null;
        this.scanner = new Scanner(System.in);
    }

    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new NodoDatos(valor);
        } else {
            insertarRecursivo(raiz, valor);
        }
    }

    private void insertarRecursivo(NodoDatos nodo, int valor) {
        if (valor < nodo.valor) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoDatos(valor);
            } else {
                insertarRecursivo(nodo.izquierdo, valor);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoDatos(valor);
            } else {
                insertarRecursivo(nodo.derecho, valor);
            }
        }
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(NodoDatos nodo, int valor) {
        if (nodo == null) {
            return false;
        }
        if (valor == nodo.valor) {
            return true;
        }
        return valor < nodo.valor
            ? buscarRecursivo(nodo.izquierdo, valor)
            : buscarRecursivo(nodo.derecho, valor);
    }

    public void inorden(NodoDatos nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inorden(nodo.derecho);
        }
    }
    
    private int obtenerEnteroValido() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            System.out.print("Ingrese nuevamente: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
    
    public void mostrarMenu() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n===== SISTEMA DE ANÁLISIS DE DATOS =====");
            System.out.println("1. Agregar nuevo dato");
            System.out.println("2. Buscar dato");
            System.out.println("3. Mostrar todos los datos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = obtenerEnteroValido();
            
            if (opcion == 1) {
                agregarDato();
            } else if (opcion == 2) {
                buscarDato();
            } else if (opcion == 3) {
                mostrarDatos();
            } else if (opcion == 4) {
                System.out.println("Saliendo del sistema de análisis de datos...");
                continuar = false;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private void agregarDato() {
        System.out.print("Ingrese el valor numérico a agregar: ");
        int valor = obtenerEnteroValido();
        
        insertar(valor);
        System.out.println("Dato agregado correctamente.");
    }
    
    private void buscarDato() {
        System.out.print("Ingrese el valor a buscar: ");
        int valor = obtenerEnteroValido();
        
        boolean encontrado = buscar(valor);
        if (encontrado) {
            System.out.println("El valor " + valor + " fue encontrado en el árbol.");
        } else {
            System.out.println("El valor " + valor + " no existe en el árbol.");
        }
    }
    
    private void mostrarDatos() {
        if (raiz == null) {
            System.out.println("No hay datos almacenados.");
        } else {
            System.out.println("Datos almacenados (en orden):");
            inorden(raiz);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArbolDatos arbolEstadisticas = new ArbolDatos();
        
        // Inserción de datos de ejemplo
        arbolEstadisticas.insertar(150);
        arbolEstadisticas.insertar(100);
        arbolEstadisticas.insertar(200);
        arbolEstadisticas.insertar(75);
        arbolEstadisticas.insertar(125);
        arbolEstadisticas.insertar(175);
        arbolEstadisticas.insertar(225);
        
        // Iniciar el sistema interactivo
        arbolEstadisticas.mostrarMenu();
    }
}