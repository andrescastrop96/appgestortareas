//Operaciones de inserción, búsqueda y eliminación
class NodoArbol3 {
    int valor;
    NodoArbol3 izquierdo;
    NodoArbol3 derecho;

    public NodoArbol3(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinarioBusqueda3 {
    NodoArbol3 raiz;

    public ArbolBinarioBusqueda3() {
        this.raiz = null;
    }

    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new NodoArbol3(valor);
        } else {
            insertarRecursivo(raiz, valor);
        }
    }

    private void insertarRecursivo(NodoArbol3 nodo, int valor) {
        if (valor < nodo.valor) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = new NodoArbol3(valor);
            } else {
                insertarRecursivo(nodo.izquierdo, valor);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = new NodoArbol3(valor);
            } else {
                insertarRecursivo(nodo.derecho, valor);
            }
        }
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(NodoArbol3 nodo, int valor) {
        if (nodo == null) {
            return false; // No encontrado
        }
        if (valor == nodo.valor) {
            return true; // Encontrado
        }
        return valor < nodo.valor 
            ? buscarRecursivo(nodo.izquierdo, valor) 
            : buscarRecursivo(nodo.derecho, valor);
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private NodoArbol3 eliminarRecursivo(NodoArbol3 nodo, int valor) {
        if (nodo == null) {
            return null; // No se encontró el nodo
        }
        if (valor < nodo.valor) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, valor);
        } else {
            // Nodo encontrado
            if (nodo.izquierdo == null) return nodo.derecho;
            else if (nodo.derecho == null) return nodo.izquierdo;

            // Nodo con dos hijos: obtener el mínimo en el subárbol derecho
            nodo.valor = minValue(nodo.derecho);

            // Eliminar el nodo mínimo del subárbol derecho
            nodo.derecho = eliminarRecursivo(nodo.derecho, nodo.valor);
        }
        return nodo;
    }

    private int minValue(NodoArbol3 nodo) {
        int min = nodo.valor;
        while (nodo.izquierdo != null) {
            min = nodo.izquierdo.valor;
            nodo = nodo.izquierdo;
        }
        return min;
    }

    public void inorden(NodoArbol3 nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inorden(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        ArbolBinarioBusqueda3 arbol = new ArbolBinarioBusqueda3();
        
        // Inserción de nuevos números
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        arbol.insertar(15);
        arbol.insertar(25);
        arbol.insertar(35);
        
        System.out.println("Recorrido en orden de los números en el árbol:");
        arbol.inorden(arbol.raiz); // Muestra los números ordenados
        System.out.println("\n");

        // Solicitar al usuario que ingrese un número para buscar
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Ingrese un número para buscar en el árbol: ");
        int numeroBuscado = scanner.nextInt();
        
        // Realizar la búsqueda y mostrar el resultado
        boolean encontrado = arbol.buscar(numeroBuscado);
        if (encontrado) {
            System.out.println("El número " + numeroBuscado + " SÍ se encuentra en el árbol.");
        } else {
            System.out.println("El número " + numeroBuscado + " NO se encuentra en el árbol.");
        }
        
        // Ejemplo de eliminación
        System.out.print("\n¿Desea eliminar algún número? (1: Sí / 0: No): ");
        int opcion = scanner.nextInt();
        
        if (opcion == 1) {
            System.out.print("Ingrese el número que desea eliminar: ");
            int numeroEliminar = scanner.nextInt();
            arbol.eliminar(numeroEliminar);
            
            System.out.println("Recorrido en orden después de eliminar " + numeroEliminar + ":");
            arbol.inorden(arbol.raiz);
            System.out.println();
        }
        
        scanner.close();
    }
}
//Inserción: