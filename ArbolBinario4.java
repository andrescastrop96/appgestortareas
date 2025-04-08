//Gestión de Inventarios
//Caso de Uso: Un sistema de gestión de inventarios en una tienda que necesita organizar y buscar productos eficientemente por su código.//
import java.util.Scanner;

class NodoInventario {
    int codigo;
    String nombre;
    double precio;
    NodoInventario izquierdo;
    NodoInventario derecho;

    public NodoInventario(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinarioBusqueda {
    NodoInventario raiz;
    Scanner scanner;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.scanner = new Scanner(System.in);
    }

    public void insertar(int codigo, String nombre, double precio) {
        if (raiz == null) {
            raiz = new NodoInventario(codigo, nombre, precio);
        } else {
            insertarRecursivo(raiz, codigo, nombre, precio);
        }
    }

    private void insertarRecursivo(NodoInventario nodo, int codigo, String nombre, double precio) {
        if (codigo < nodo.codigo) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoInventario(codigo, nombre, precio);
            } else {
                insertarRecursivo(nodo.izquierdo, codigo, nombre, precio);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoInventario(codigo, nombre, precio);
            } else {
                insertarRecursivo(nodo.derecho, codigo, nombre, precio);
            }
        }
    }

    public NodoInventario buscar(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private NodoInventario buscarRecursivo(NodoInventario nodo, int codigo) {
        if (nodo == null) {
            return null; // No encontrado
        }
        if (codigo == nodo.codigo) {
            return nodo; // Encontrado
        }
        return codigo < nodo.codigo
            ? buscarRecursivo(nodo.izquierdo, codigo)
            : buscarRecursivo(nodo.derecho, codigo);
    }

    public void inorden(NodoInventario nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.println("Código: " + nodo.codigo + " | Producto: " + nodo.nombre + " | Precio: $" + nodo.precio);
            inorden(nodo.derecho);
        }
    }

    public void mostrarMenu() {
        int opcion = 0;
        
        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE INVENTARIO =====");
            System.out.println("1. Agregar nuevo producto");
            System.out.println("2. Buscar producto por código");
            System.out.println("3. Mostrar todos los productos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1:
                        agregarProducto();
                        break;
                    case 2:
                        buscarProducto();
                        break;
                    case 3:
                        mostrarProductos();
                        break;
                    case 4:
                        System.out.println("¡Gracias por usar el sistema de inventario!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer
                opcion = 0;
            }
            
        } while (opcion != 4);
    }
    
    private void agregarProducto() {
        try {
            System.out.println("\n--- AGREGAR NUEVO PRODUCTO ---");
            System.out.print("Ingrese el código del producto: ");
            int codigo = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            if (buscar(codigo) != null) {
                System.out.println("Error: Ya existe un producto con ese código.");
                return;
            }
            
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Ingrese el precio del producto: $");
            double precio = scanner.nextDouble();
            
            insertar(codigo, nombre, precio);
            System.out.println("¡Producto agregado exitosamente!");
        } catch (Exception e) {
            System.out.println("Error al ingresar los datos. Intente nuevamente.");
            scanner.nextLine(); // Limpiar buffer
        }
    }
    
    private void buscarProducto() {
        try {
            System.out.println("\n--- BUSCAR PRODUCTO ---");
            System.out.print("Ingrese el código del producto a buscar: ");
            int codigo = scanner.nextInt();
            
            NodoInventario producto = buscar(codigo);
            
            if (producto != null) {
                System.out.println("\nProducto encontrado:");
                System.out.println("Código: " + producto.codigo);
                System.out.println("Nombre: " + producto.nombre);
                System.out.println("Precio: $" + producto.precio);
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al ingresar los datos. Intente nuevamente.");
            scanner.nextLine(); // Limpiar buffer
        }
    }
    
    private void mostrarProductos() {
        System.out.println("\n--- LISTADO DE PRODUCTOS ---");
        if (raiz == null) {
            System.out.println("El inventario está vacío.");
        } else {
            inorden(raiz);
        }
    }

    public static void main(String[] args) {
        ArbolBinarioBusqueda arbolInventario = new ArbolBinarioBusqueda();
        
        // Productos de ejemplo pre-cargados
        arbolInventario.insertar(101, "Teclado Mecánico", 59.99);
        arbolInventario.insertar(105, "Monitor 24\"", 149.99);
        arbolInventario.insertar(103, "Mouse Inalámbrico", 29.99);
        arbolInventario.insertar(102, "Auriculares Bluetooth", 79.99);
        arbolInventario.insertar(104, "Webcam HD", 45.99);
        
        // Iniciar el menú interactivo
        arbolInventario.mostrarMenu();
    }
}