import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clase principal para ejecutar el programa
public class ArbolBinario12 {
 public static void main(String[] args) {
     ArbolEstudiantes12 arbol = new ArbolEstudiantes12(); // Crea una instancia del árbol de estudiantes
     
     // Agregar estudiantes predeterminados
     arbol.insertarEstudiante("Ana");
     arbol.insertarEstudiante("Sofia");
     arbol.insertarEstudiante("Camila");
     arbol.insertarEstudiante("Andres");
     
     Scanner scanner = new Scanner(System.in);
     int opcion;

     do {
         // Menú de opciones
         System.out.println("\nDirectorio de Estudiantes");
         System.out.println("1. Insertar estudiante");
         System.out.println("2. Eliminar estudiante");
         System.out.println("3. Mostrar estudiantes (Inorden)");
         System.out.println("4. Mostrar estudiantes (Preorden)");
         System.out.println("5. Mostrar estudiantes (Postorden)");
         System.out.println("6. Obtener listado de estudiantes");
         System.out.println("7. Salir");
         System.out.print("Elige una opción: ");
         opcion = scanner.nextInt();
         scanner.nextLine(); // Limpiar el buffer

         switch (opcion) {
             case 1:
                 System.out.print("Ingresa el nombre del estudiante a insertar: ");
                 String nombreInsertar = scanner.nextLine();
                 arbol.insertarEstudiante(nombreInsertar);
                 System.out.println("Estudiante agregado.");
                 break;
             case 2:
                 System.out.print("Ingresa el nombre del estudiante a eliminar: ");
                 String nombreEliminar = scanner.nextLine();
                 arbol.eliminarEstudiante(nombreEliminar);
                 System.out.println("Estudiante eliminado si existía.");
                 break;
             case 3:
                 System.out.println("Estudiantes en orden (Inorden):");
                 arbol.inOrden(arbol.raiz);
                 System.out.println();
                 break;
             case 4:
                 System.out.println("Estudiantes en orden (Preorden):");
                 arbol.preOrden(arbol.raiz);
                 System.out.println();
                 break;
             case 5:
                 System.out.println("Estudiantes en orden (Postorden):");
                 arbol.postOrden(arbol.raiz);
                 System.out.println();
                 break;
             case 6:
                 System.out.println("Listado de estudiantes ordenados alfabéticamente:");
                 List<String> listaEstudiantes = arbol.obtenerListadoEstudiantes();
                 if (listaEstudiantes.isEmpty()) {
                     System.out.println("No hay estudiantes registrados.");
                 } else {
                     for (int i = 0; i < listaEstudiantes.size(); i++) {
                         System.out.println((i + 1) + ". " + listaEstudiantes.get(i));
                     }
                 }
                 break;
             case 7:
                 System.out.println("¡Gracias por usar el directorio!");
                 break;
             default:
                 System.out.println("Opción no válida. Intenta de nuevo.");
         }
     } while (opcion != 7); // Continúa hasta que el usuario elija salir

     scanner.close(); // Cierra el scanner
 }
}

//Clase que representa un nodo en el árbol binario
class Estudiante12 {
 String nombre; // Nombre del estudiante
 Estudiante12 izquierda, derecha; // Referencias a los nodos hijos

 // Constructor para inicializar un nodo estudiante
 public Estudiante12(String nombre) {
     this.nombre = nombre; // Asigna el nombre al estudiante
     izquierda = derecha = null; // Inicializa los hijos como nulos
 }
}

//Clase que representa el árbol de estudiantes
class ArbolEstudiantes12 {
 Estudiante12 raiz; // Referencia a la raíz del árbol

 // Método para insertar un nuevo estudiante
 void insertarEstudiante(String nombre) {
     raiz = insertarRec(raiz, nombre); // Llama al método recursivo para insertar
 }

 // Método recursivo para insertar un nuevo nodo
 Estudiante12 insertarRec(Estudiante12 raiz, String nombre) {
     // Si la raíz es nula, hemos encontrado el lugar para el nuevo nodo
     if (raiz == null) {
         return new Estudiante12(nombre); // Crea un nuevo nodo estudiante
     }
     // Si el nombre a insertar es menor, va al subárbol izquierdo
     if (nombre.compareTo(raiz.nombre) < 0) {
         raiz.izquierda = insertarRec(raiz.izquierda, nombre);
     } 
     // Si el nombre a insertar es mayor, va al subárbol derecho
     else if (nombre.compareTo(raiz.nombre) > 0) {
         raiz.derecha = insertarRec(raiz.derecha, nombre);
     }
     // Retorna la raíz (sin cambios)
     return raiz;
 }

 // Método para eliminar un estudiante
 void eliminarEstudiante(String nombre) {
     raiz = eliminarRec(raiz, nombre); // Llama al método recursivo para eliminar
 }

 // Método recursivo para eliminar un nodo
 Estudiante12 eliminarRec(Estudiante12 raiz, String nombre) {
     // Si la raíz es nula, el nombre no está en el árbol
     if (raiz == null) {
         return raiz; // Retorna nulo
     }
     // Busca el nodo en el subárbol izquierdo
     if (nombre.compareTo(raiz.nombre) < 0) {
         raiz.izquierda = eliminarRec(raiz.izquierda, nombre);
     } 
     // Busca el nodo en el subárbol derecho
     else if (nombre.compareTo(raiz.nombre) > 0) {
         raiz.derecha = eliminarRec(raiz.derecha, nombre);
     } 
     // Si encontramos el nodo a eliminar
     else {
         // Caso 1: Nodo con solo un hijo o sin hijos
         if (raiz.izquierda == null) {
             return raiz.derecha; // Retorna el hijo derecho
         } else if (raiz.derecha == null) {
             return raiz.izquierda; // Retorna el hijo izquierdo
         }

         // Caso 2: Nodo con dos hijos
         // Encuentra el sucesor inorden (el más pequeño en el subárbol derecho)
         raiz.nombre = minNombre(raiz.derecha); // Copia el nombre mínimo
         // Elimina el sucesor inorden
         raiz.derecha = eliminarRec(raiz.derecha, raiz.nombre);
     }
     // Retorna la raíz actualizada
     return raiz;
 }

 // Método para encontrar el nombre mínimo en un subárbol
 String minNombre(Estudiante12 raiz) {
     String min = raiz.nombre; // Inicializa el mínimo con el nombre del nodo actual
     // Recorre hacia la izquierda para encontrar el nombre más pequeño
     while (raiz.izquierda != null) {
         min = raiz.izquierda.nombre; // Actualiza el mínimo
         raiz = raiz.izquierda; // Avanza hacia la izquierda
     }
     return min; // Retorna el nombre mínimo encontrado
 }

 // Método para recorrer el árbol en orden (izquierda, raíz, derecha)
 void inOrden(Estudiante12 raiz) {
     if (raiz != null) {
         inOrden(raiz.izquierda); // Recorre el subárbol izquierdo
         System.out.println(raiz.nombre); // Visita el nodo actual con salto de línea
         inOrden(raiz.derecha); // Recorre el subárbol derecho
     }
 }

 // Método para recorrer el árbol en preorden (raíz, izquierda, derecha)
 void preOrden(Estudiante12 raiz) {
     if (raiz != null) {
         System.out.println(raiz.nombre); // Visita el nodo actual con salto de línea
         preOrden(raiz.izquierda); // Recorre el subárbol izquierdo
         preOrden(raiz.derecha); // Recorre el subárbol derecho
     }
 }

 // Método para recorrer el árbol en postorden (izquierda, derecha, raíz)
 void postOrden(Estudiante12 raiz) {
     if (raiz != null) {
         postOrden(raiz.izquierda); // Recorre el subárbol izquierdo
         postOrden(raiz.derecha); // Recorre el subárbol derecho
         System.out.println(raiz.nombre); // Visita el nodo actual con salto de línea
     }
 }
 
 // Método para obtener un listado ordenado de estudiantes
 List<String> obtenerListadoEstudiantes() {
     List<String> lista = new ArrayList<>();
     recolectarEstudiantes(raiz, lista);
     return lista;
 }
 
 // Método auxiliar para recolectar estudiantes en orden
 private void recolectarEstudiantes(Estudiante12 nodo, List<String> lista) {
     if (nodo != null) {
         recolectarEstudiantes(nodo.izquierda, lista);
         lista.add(nodo.nombre);
         recolectarEstudiantes(nodo.derecha, lista);
     }
 }
}