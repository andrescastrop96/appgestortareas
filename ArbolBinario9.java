//Sistema de Recomendaciones
//Caso de Uso: Un sistema de recomendaciones de productos que utiliza un árbol binario para almacenar productos por su ID, permitiendo búsquedas rápidas y recomendaciones basadas en los productos cercanos en el árbol.
import java.util.Scanner;

class NodoProducto {
    int id;
    String nombre;
    String categoria;
    double precio;
    NodoProducto izquierdo;
    NodoProducto derecho;

    public NodoProducto(int id, String nombre, String categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolRecomendaciones {
    NodoProducto raiz;
    Scanner scanner;

    public ArbolRecomendaciones() {
        this.raiz = null;
        this.scanner = new Scanner(System.in);
    }

    public void insertar(int id, String nombre, String categoria, double precio) {
        if (raiz == null) {
            raiz = new NodoProducto(id, nombre, categoria, precio);
        } else {
            insertarRecursivo(raiz, id, nombre, categoria, precio);
        }
    }

    private void insertarRecursivo(NodoProducto nodo, int id, String nombre, String categoria, double precio) {
        if (id < nodo.id) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoProducto(id, nombre, categoria, precio);
            } else {
                insertarRecursivo(nodo.izquierdo, id, nombre, categoria, precio);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoProducto(id, nombre, categoria, precio);
            } else {
                insertarRecursivo(nodo.derecho, id, nombre, categoria, precio);
            }
        }
    }

    public NodoProducto buscar(int id) {
        return buscarRecursivo(raiz, id);
    }

    private NodoProducto buscarRecursivo(NodoProducto nodo, int id) {
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

    public void inorden(NodoProducto nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.println("ID: " + nodo.id + " | Producto: " + nodo.nombre + 
                              " | Categoría: " + nodo.categoria + " | Precio: $" + nodo.precio);
            inorden(nodo.derecho);
        }
    }
    
    private void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE RECOMENDACIONES DE PRODUCTOS =====");
        System.out.println("1. Agregar nuevo producto");
        System.out.println("2. Buscar producto por ID");
        System.out.println("3. Mostrar todos los productos");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
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
    
    private double obtenerDoubleValido() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            System.out.print("Ingrese nuevamente: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
    
    private void agregarProducto() {
        System.out.print("Ingrese el ID del producto: ");
        int id = obtenerEnteroValido();
        
        if (buscar(id) != null) {
            System.out.println("Error: Ya existe un producto con ese ID.");
            return;
        }
        
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        
        System.out.print("Ingrese la categoría del producto: ");
        String categoria = scanner.nextLine().trim();
        
        System.out.print("Ingrese el precio del producto: ");
        double precio = obtenerDoubleValido();
        
        insertar(id, nombre, categoria, precio);
        System.out.println("Producto agregado correctamente.");
    }
    
    private void buscarProducto() {
        System.out.print("Ingrese el ID del producto a buscar: ");
        int id = obtenerEnteroValido();
        
        NodoProducto producto = buscar(id);
        if (producto != null) {
            System.out.println("\nProducto encontrado:");
            System.out.println("ID: " + producto.id);
            System.out.println("Nombre: " + producto.nombre);
            System.out.println("Categoría: " + producto.categoria);
            System.out.println("Precio: $" + producto.precio);
            
            System.out.println("\nProductos recomendados basados en este producto:");
            mostrarRecomendaciones(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
    
    private void mostrarRecomendaciones(NodoProducto producto) {
        // Implementación simple: recomendar productos de la misma categoría
        mostrarProductosPorCategoria(raiz, producto.categoria, producto.id);
    }
    
    private void mostrarProductosPorCategoria(NodoProducto nodo, String categoria, int idOriginal) {
        if (nodo != null) {
            mostrarProductosPorCategoria(nodo.izquierdo, categoria, idOriginal);
            
            if (nodo.id != idOriginal && nodo.categoria.equals(categoria)) {
                System.out.println("ID: " + nodo.id + " | Producto: " + nodo.nombre + 
                                  " | Precio: $" + nodo.precio);
            }
            
            mostrarProductosPorCategoria(nodo.derecho, categoria, idOriginal);
        }
    }
    
    private void mostrarProductos() {
        if (raiz == null) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("Listado de productos:");
            inorden(raiz);
        }
    }
    
    public void ejecutarSistema() {
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcion = obtenerEnteroValido();
            
            if (opcion == 1) {
                agregarProducto();
            } else if (opcion == 2) {
                buscarProducto();
            } else if (opcion == 3) {
                mostrarProductos();
            } else if (opcion == 4) {
                System.out.println("Saliendo del sistema de recomendaciones...");
                continuar = false;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void main(String[] args) {
        ArbolRecomendaciones arbolProductos = new ArbolRecomendaciones();
        
        // Inserción de productos de ejemplo
        arbolProductos.insertar(1001, "Laptop HP", "Electrónica", 899.99);
        arbolProductos.insertar(1003, "Smartphone Samsung", "Electrónica", 699.99);
        arbolProductos.insertar(1002, "Tablet iPad", "Electrónica", 499.99);
        arbolProductos.insertar(1004, "Auriculares Sony", "Accesorios", 149.99);
        arbolProductos.insertar(1005, "Teclado Mecánico", "Accesorios", 89.99);
        
        // Iniciar el sistema interactivo
        arbolProductos.ejecutarSistema();
    }
}