//Motor de Búsqueda
//Caso de Uso: Un motor de búsqueda que utiliza un árbol binario para almacenar y buscar palabras clave o términos de búsqueda.
class NodoPalabra {
    String palabra;
    String definicion; // Agregado para almacenar definición de la palabra
    int frecuencia; // Contador de frecuencia de búsqueda
    NodoPalabra izquierdo;
    NodoPalabra derecho;

    public NodoPalabra(String palabra, String definicion) {
        this.palabra = palabra;
        this.definicion = definicion;
        this.frecuencia = 1;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBusquedaPalabras {
    NodoPalabra raiz;
    java.util.Scanner scanner;

    public ArbolBusquedaPalabras() {
        this.raiz = null;
        this.scanner = new java.util.Scanner(System.in);
    }

    public void insertar(String palabra, String definicion) {
        if (palabra == null || palabra.isEmpty()) {
            System.out.println("Error: La palabra no puede estar vacía.");
            return;
        }
        
        if (raiz == null) {
            raiz = new NodoPalabra(palabra, definicion);
        } else {
            insertarRecursivo(raiz, palabra, definicion);
        }
    }

    private void insertarRecursivo(NodoPalabra nodo, String palabra, String definicion) {
        int comparacion = palabra.compareToIgnoreCase(nodo.palabra);
        
        if (comparacion == 0) {
            // La palabra ya existe, actualizamos su definición y frecuencia
            nodo.definicion = definicion;
            nodo.frecuencia++;
            return;
        }
        
        if (comparacion < 0) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoPalabra(palabra, definicion);
            } else {
                insertarRecursivo(nodo.izquierdo, palabra, definicion);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoPalabra(palabra, definicion);
            } else {
                insertarRecursivo(nodo.derecho, palabra, definicion);
            }
        }
    }

    public NodoPalabra buscar(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return null;
        }
        return buscarRecursivo(raiz, palabra);
    }

    private NodoPalabra buscarRecursivo(NodoPalabra nodo, String palabra) {
        if (nodo == null) {
            return null; // No encontrado
        }
        
        int comparacion = palabra.compareToIgnoreCase(nodo.palabra);
        
        if (comparacion == 0) {
            nodo.frecuencia++; // Incrementar frecuencia al encontrar
            return nodo; // Encontrado
        }
        
        return comparacion < 0
            ? buscarRecursivo(nodo.izquierdo, palabra)
            : buscarRecursivo(nodo.derecho, palabra);
    }

    public void inorden(NodoPalabra nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.println(nodo.palabra + " - " + nodo.definicion + " (Búsquedas: " + nodo.frecuencia + ")");
            inorden(nodo.derecho);
        }
    }
    
    public void ejecutarBuscador() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n===== MOTOR DE BÚSQUEDA =====");
            System.out.println("1. Agregar nueva palabra");
            System.out.println("2. Buscar palabra");
            System.out.println("3. Mostrar todas las palabras");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = obtenerOpcionNumerica();
            
            if (opcion == 1) {
                agregarPalabra();
            } else if (opcion == 2) {
                buscarPalabra();
            } else if (opcion == 3) {
                mostrarPalabras();
            } else if (opcion == 4) {
                System.out.println("Saliendo del buscador...");
                continuar = false;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    
    private int obtenerOpcionNumerica() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            System.out.print("Seleccione una opción: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }
    
    private void agregarPalabra() {
        System.out.print("Ingrese la palabra: ");
        String palabra = scanner.nextLine().trim();
        
        if (palabra.isEmpty()) {
            System.out.println("Error: La palabra no puede estar vacía.");
            return;
        }
        
        System.out.print("Ingrese la definición: ");
        String definicion = scanner.nextLine().trim();
        
        insertar(palabra, definicion);
        System.out.println("Palabra agregada correctamente.");
    }
    
    private void buscarPalabra() {
        System.out.print("Ingrese la palabra a buscar: ");
        String palabra = scanner.nextLine().trim();
        
        NodoPalabra resultado = buscar(palabra);
        if (resultado != null) {
            System.out.println("Palabra encontrada:");
            System.out.println("Término: " + resultado.palabra);
            System.out.println("Definición: " + resultado.definicion);
            System.out.println("Frecuencia de búsqueda: " + resultado.frecuencia);
        } else {
            System.out.println("Palabra no encontrada en el diccionario.");
        }
    }
    
    private void mostrarPalabras() {
        if (raiz == null) {
            System.out.println("El diccionario está vacío.");
        } else {
            System.out.println("Listado de palabras:");
            inorden(raiz);
        }
    }

    public static void main(String[] args) {
        ArbolBusquedaPalabras arbolPalabras = new ArbolBusquedaPalabras();
        
        // Inserción de palabras clave con definiciones
        arbolPalabras.insertar("gato", "Mamífero carnívoro de la familia de los félidos");
        arbolPalabras.insertar("perro", "Mamífero doméstico de la familia de los cánidos");
        arbolPalabras.insertar("elefante", "Mamífero paquidermo de gran tamaño");
        arbolPalabras.insertar("león", "Mamífero carnívoro de la familia de los félidos");
        
        // Iniciar el buscador interactivo
        arbolPalabras.ejecutarBuscador();
    }
}