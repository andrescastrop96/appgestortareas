package com.example.temabinario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TemaBinarioApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TemaBinarioApplication.class, args);
    }
    
    // Método utilitario para convertir texto a binario
    public static String convertirTextoBinario(String texto) {
        StringBuilder binario = new StringBuilder();
        for (char caracter : texto.toCharArray()) {
            String bin = Integer.toBinaryString(caracter);
            // Asegurar que cada carácter tenga 8 bits
            while (bin.length() < 8) {
                bin = "0" + bin;
            }
            binario.append(bin).append(" ");
        }
        return binario.toString().trim();
    }
    
    // Método utilitario para convertir binario a texto
    public static String convertirBinarioTexto(String binario) {
        StringBuilder texto = new StringBuilder();
        String[] bytes = binario.split(" ");
        for (String byteStr : bytes) {
            int decimal = Integer.parseInt(byteStr, 2);
            texto.append((char) decimal);
        }
        return texto.toString();
    }
    
    @Bean
    public WebMvcConfigurer configuracion() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
            }
        };
    }
}

class TemaBinario {
    private String nombre;
    private TemaBinario izquierdo;
    private TemaBinario derecho;
    
    // Constructor
    public TemaBinario(String nombre) {
        this.nombre = nombre;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public TemaBinario getIzquierdo() {
        return izquierdo;
    }
    
    public void setIzquierdo(TemaBinario izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    public TemaBinario getDerecho() {
        return derecho;
    }
    
    public void setDerecho(TemaBinario derecho) {
        this.derecho = derecho;
    }
    
    // Método para recorrer el árbol en preorden
    public void recorridoPreorden() {
        // Visitar nodo actual
        System.out.println(this.nombre);
        
        // Recorrer subárbol izquierdo
        if (this.izquierdo != null) {
            this.izquierdo.recorridoPreorden();
        }
        
        // Recorrer subárbol derecho
        if (this.derecho != null) {
            this.derecho.recorridoPreorden();
        }
    }
    
    // Método para obtener el recorrido preorden como lista
    public List<String> obtenerRecorridoPreorden() {
        List<String> resultado = new ArrayList<>();
        obtenerRecorridoPreordenRecursivo(resultado);
        return resultado;
    }
    
    private void obtenerRecorridoPreordenRecursivo(List<String> resultado) {
        // Visitar nodo actual
        resultado.add(this.nombre);
        
        // Recorrer subárbol izquierdo
        if (this.izquierdo != null) {
            this.izquierdo.obtenerRecorridoPreordenRecursivo(resultado);
        }
        
        // Recorrer subárbol derecho
        if (this.derecho != null) {
            this.derecho.obtenerRecorridoPreordenRecursivo(resultado);
        }
    }
    
    // Método para agregar un subtema
    public void agregarSubtema(String nombreSubtema, boolean esIzquierdo) {
        if (esIzquierdo) {
            if (this.izquierdo == null) {
                this.izquierdo = new TemaBinario(nombreSubtema);
            } else {
                System.out.println("Ya existe un subtema izquierdo: " + this.izquierdo.getNombre());
            }
        } else {
            if (this.derecho == null) {
                this.derecho = new TemaBinario(nombreSubtema);
            } else {
                System.out.println("Ya existe un subtema derecho: " + this.derecho.getNombre());
            }
        }
    }
    
    // Método de prueba en consola (sin JavaFX)
    public static void main(String[] args) {
        System.out.println("=== Prueba de Árbol Binario ===");
        probarArbol();
        
        System.out.println("\n=== Prueba de Lista de Sesiones ===");
        probarListaSesiones();
        
        System.out.println("\n=== Prueba de Historial de Actividades ===");
        probarHistorialActividad();
        
        System.out.println("\n=== Prueba de Notificaciones ===");
        probarNotificaciones();
    }
    
    private static void probarArbol() {
        // Crear un árbol de temas de estudio
        TemaBinario raiz = new TemaBinario("Programación");
        raiz.agregarSubtema("Estructuras de Datos", true);
        raiz.agregarSubtema("Algoritmos", false);
        
        raiz.getIzquierdo().agregarSubtema("Listas", true);
        raiz.getIzquierdo().agregarSubtema("Árboles", false);
        
        raiz.getDerecho().agregarSubtema("Ordenamiento", true);
        raiz.getDerecho().agregarSubtema("Búsqueda", false);
        
        // Recorrer el árbol en preorden
        System.out.println("Recorrido en preorden del árbol de temas:");
        raiz.recorridoPreorden();
    }
    
    private static void probarListaSesiones() {
        // Probar la lista de sesiones
        System.out.println("\nPrueba de lista de sesiones:");
        ListaSesiones sesiones = new ListaSesiones();
        sesiones.insertarAlInicio("2023-10-01", 60, "Estudio de árboles binarios");
        sesiones.insertarAlInicio("2023-10-02", 45, "Repaso de listas enlazadas");
        sesiones.insertarAlInicio("2023-10-03", 90, "Práctica de algoritmos");
        sesiones.listarSesiones();
        
        System.out.println("\nDespués de eliminar la sesión en índice 1:");
        sesiones.eliminarPorIndice(1);
        sesiones.listarSesiones();
        
        System.out.println("\nDespués de insertar al final:");
        sesiones.insertarAlFinal("2023-10-04", 75, "Estudio de grafos");
        sesiones.listarSesiones();
    }
    
    private static void probarHistorialActividad() {
        // Probar la pila de historial
        System.out.println("\nPrueba de historial de actividades:");
        HistorialActividad historial = new HistorialActividad();
        historial.push("Inicio de sesión", "2023-10-03T08:00:00");
        historial.push("Consulta de material", "2023-10-03T08:15:00");
        historial.push("Realización de ejercicio", "2023-10-03T08:30:00");
        historial.imprimirHistorial();
        
        HistorialActividad.Actividad ultimaActividad = historial.pop();
        System.out.println("\nÚltima actividad eliminada: " + ultimaActividad.getDescripcion() + " - " + 
                          ultimaActividad.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Historial actualizado:");
        historial.imprimirHistorial();
    }
    
    private static void probarNotificaciones() {
        // Probar la cola de notificaciones
        System.out.println("\nPrueba de cola de notificaciones:");
        ColaNotificaciones notificaciones = new ColaNotificaciones();
        notificaciones.agregar("Nuevo material disponible", "2023-10-03T09:00:00");
        notificaciones.agregar("Recordatorio de entrega", "2023-10-03T09:15:00");
        notificaciones.agregar("Mensaje del profesor", "2023-10-03T09:30:00");
        
        System.out.println("Siguiente notificación: " + notificaciones.verSiguiente().getMensaje());
        
        ColaNotificaciones.Notificacion notificacion = notificaciones.atender();
        System.out.println("Notificación atendida: " + notificacion.getMensaje() + " - " + 
                          notificacion.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        System.out.println("Nueva siguiente notificación: " + notificaciones.verSiguiente().getMensaje());
    }
}

class ListaSesiones {
    private Nodo cabeza;
    
    private class Nodo {
        private String fecha;
        private int duracion;
        private String descripcion;
        private Nodo siguiente;
        
        public Nodo(String fecha, int duracion, String descripcion) {
            this.fecha = fecha;
            this.duracion = duracion;
            this.descripcion = descripcion;
            this.siguiente = null;
        }
    }
    
    public ListaSesiones() {
        this.cabeza = null;
    }
    
    // Insertar al inicio
    public void insertarAlInicio(String fecha, int duracion, String descripcion) {
        Nodo nuevoNodo = new Nodo(fecha, duracion, descripcion);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }
    
    // Insertar al final
    public void insertarAlFinal(String fecha, int duracion, String descripcion) {
        Nodo nuevoNodo = new Nodo(fecha, duracion, descripcion);
        
        if (cabeza == null) {
            cabeza = nuevoNodo;
            return;
        }
        
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        
        actual.siguiente = nuevoNodo;
    }
    
    // Eliminar por índice
    public boolean eliminarPorIndice(int indice) {
        if (cabeza == null) {
            return false;
        }
        
        if (indice == 0) {
            cabeza = cabeza.siguiente;
            return true;
        }
        
        Nodo actual = cabeza;
        int contador = 0;
        
        while (actual.siguiente != null && contador < indice - 1) {
            actual = actual.siguiente;
            contador++;
        }
        
        if (actual.siguiente == null) {
            return false;
        }
        
        actual.siguiente = actual.siguiente.siguiente;
        return true;
    }
    
    // Listar sesiones
    public void listarSesiones() {
        Nodo actual = cabeza;
        int numeroSesion = 1;
        
        while (actual != null) {
            System.out.println("Sesión " + numeroSesion + " - Fecha: " + actual.fecha + 
                               ", Duración: " + actual.duracion + 
                               " minutos, Descripción: " + actual.descripcion);
            actual = actual.siguiente;
            numeroSesion++;
        }
    }
    
    // Obtener todas las sesiones como lista
    public List<SesionDTO> obtenerTodasLasSesiones() {
        List<SesionDTO> sesiones = new ArrayList<>();
        Nodo actual = cabeza;
        int numeroSesion = 0;
        
        while (actual != null) {
            sesiones.add(new SesionDTO(numeroSesion, actual.fecha, actual.duracion, actual.descripcion));
            actual = actual.siguiente;
            numeroSesion++;
        }
        
        return sesiones;
    }
    
    // DTO para representar una sesión
    public static class SesionDTO {
        private int indice;
        private String fecha;
        private int duracion;
        private String descripcion;
        
        public SesionDTO(int indice, String fecha, int duracion, String descripcion) {
            this.indice = indice;
            this.fecha = fecha;
            this.duracion = duracion;
            this.descripcion = descripcion;
        }
        
        public int getIndice() {
            return indice;
        }
        
        public String getFecha() {
            return fecha;
        }
        
        public int getDuracion() {
            return duracion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
}

class HistorialActividad {
    private Nodo tope;
    
    public static class Actividad {
        private String descripcion;
        private LocalDateTime timestamp;
        
        public Actividad(String descripcion, LocalDateTime timestamp) {
            this.descripcion = descripcion;
            this.timestamp = timestamp;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
    
    private class Nodo {
        private Actividad actividad;
        private Nodo siguiente;
        
        public Nodo(Actividad actividad) {
            this.actividad = actividad;
            this.siguiente = null;
        }
    }
    
    public HistorialActividad() {
        this.tope = null;
    }
    
    // Push: agregar actividad a la pila
    public void push(String descripcion, String timestampStr) {
        LocalDateTime timestamp = LocalDateTime.parse(timestampStr);
        Actividad nuevaActividad = new Actividad(descripcion, timestamp);
        Nodo nuevoNodo = new Nodo(nuevaActividad);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
    }
    
    // Pop: eliminar y devolver la actividad más reciente
    public Actividad pop() {
        if (tope == null) {
            return null;
        }
        
        Actividad actividad = tope.actividad;
        tope = tope.siguiente;
        return actividad;
    }
    
    // Peek: ver la actividad más reciente sin eliminarla
    public Actividad peek() {
        if (tope == null) {
            return null;
        }
        
        return tope.actividad;
    }
    
    // Obtener todas las actividades como lista
    public List<ActividadDTO> obtenerTodasLasActividades() {
        List<ActividadDTO> actividades = new ArrayList<>();
        Nodo actual = tope;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        while (actual != null) {
            actividades.add(new ActividadDTO(
                actual.actividad.getDescripcion(),
                actual.actividad.getTimestamp().format(formatter)
            ));
            actual = actual.siguiente;
        }
        
        return actividades;
    }
    
    // Imprimir historial completo
    public void imprimirHistorial() {
        Nodo actual = tope;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Historial de actividades (más reciente primero):");
        while (actual != null) {
            System.out.println("Actividad: " + actual.actividad.getDescripcion() + 
                               ", Timestamp: " + actual.actividad.getTimestamp().format(formatter));
            actual = actual.siguiente;
        }
    }
    
    // DTO para representar una actividad
    public static class ActividadDTO {
        private String descripcion;
        private String timestamp;
        
        public ActividadDTO(String descripcion, String timestamp) {
            this.descripcion = descripcion;
            this.timestamp = timestamp;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        public String getTimestamp() {
            return timestamp;
        }
    }
}

class ColaNotificaciones {
    private Nodo frente;
    private Nodo final_;
    
    public static class Notificacion {
        private String mensaje;
        private LocalDateTime timestamp;
        
        public Notificacion(String mensaje, LocalDateTime timestamp) {
            this.mensaje = mensaje;
            this.timestamp = timestamp;
        }
        
        public String getMensaje() {
            return mensaje;
        }
        
        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
    
    private class Nodo {
        private Notificacion notificacion;
        private Nodo siguiente;
        
        public Nodo(Notificacion notificacion) {
            this.notificacion = notificacion;
            this.siguiente = null;
        }
    }
    
    public ColaNotificaciones() {
        this.frente = null;
        this.final_ = null;
    }
    
    // Agregar notificación a la cola
    public void agregar(String mensaje, String timestampStr) {
        LocalDateTime timestamp = LocalDateTime.parse(timestampStr);
        Notificacion nuevaNotificacion = new Notificacion(mensaje, timestamp);
        Nodo nuevoNodo = new Nodo(nuevaNotificacion);
        
        if (final_ == null) {
            frente = nuevoNodo;
            final_ = nuevoNodo;
        } else {
            final_.siguiente = nuevoNodo;
            final_ = nuevoNodo;
        }
    }
    
    // Atender (remover) la siguiente notificación
    public Notificacion atender() {
        if (frente == null) {
            return null;
        }
        
        Notificacion notificacion = frente.notificacion;
        frente = frente.siguiente;
        
        if (frente == null) {
            final_ = null;
        }
        
        return notificacion;
    }
    
    // Ver la siguiente notificación sin removerla
    public Notificacion verSiguiente() {
        if (frente == null) {
            return null;
        }
        
        return frente.notificacion;
    }
    
    // Verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }
    
    // DTO para representar una notificación
    public static class NotificacionDTO {
        private String mensaje;
        private String timestamp;
        
        public NotificacionDTO(String mensaje, String timestamp) {
            this.mensaje = mensaje;
            this.timestamp = timestamp;
        }
        
        public String getMensaje() {
            return mensaje;
        }
        
        public String getTimestamp() {
            return timestamp;
        }
    }
}

@Controller
@RequestMapping("/sesiones")
class SesionesController {
    private final ListaSesiones sesiones = new ListaSesiones();
    
    @GetMapping
    public String mostrarSesiones(Model model) {
        model.addAttribute("sesiones", sesiones.obtenerTodasLasSesiones());
        model.addAttribute("nuevaSesion", new SesionRequest());
        return "sesiones";
    }
    
    @PostMapping("/agregar")
    public String agregarSesion(@ModelAttribute SesionRequest request) {
        sesiones.insertarAlFinal(request.getFecha(), request.getDuracion(), request.getDescripcion());
        return "redirect:/sesiones";
    }
    
    @GetMapping("/eliminar/{indice}")
    public String eliminarSesion(@PathVariable int indice) {
        sesiones.eliminarPorIndice(indice);
        return "redirect:/sesiones";
    }
    
    // Clase para recibir los datos de la sesión
    static class SesionRequest {
        private String fecha;
        private int duracion;
        private String descripcion;
        
        public String getFecha() {
            return fecha;
        }
        
        public void setFecha(String fecha) {
            this.fecha = fecha;
        }
        
        public int getDuracion() {
            return duracion;
        }
        
        public void setDuracion(int duracion) {
            this.duracion = duracion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }
}

@Controller
@RequestMapping("/temas")
class TemasController {
    private TemaBinario raiz;
    
    @GetMapping
    public String mostrarTemas(Model model) {
        model.addAttribute("temaRaiz", new NombreRequest());
        model.addAttribute("subtema", new SubtemaRequest());
        
        if (raiz != null) {
            model.addAttribute("recorrido", raiz.obtenerRecorridoPreorden());
        } else {
            model.addAttribute("recorrido", new ArrayList<String>());
        }
        
        return "temas";
    }
    
    @PostMapping("/crear-raiz")
    public String crearRaiz(@ModelAttribute NombreRequest request) {
        raiz = new TemaBinario(request.getNombre());
        return "redirect:/temas";
    }
    
    @PostMapping("/agregar-subtema")
    public String agregarSubtema(@ModelAttribute SubtemaRequest request) {
        if (raiz == null) {
            return "redirect:/temas";
        }
        
        // Buscar el tema padre
        TemaBinario temaPadre = buscarTema(raiz, request.getNombrePadre());
        if (temaPadre != null) {
            // Agregar el subtema
            temaPadre.agregarSubtema(request.getNombre(), request.isEsIzquierdo());
        }
        
        return "redirect:/temas";
    }
    
    // Método auxiliar para buscar un tema por nombre
    private TemaBinario buscarTema(TemaBinario nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        
        if (nodo.getNombre().equals(nombre)) {
            return nodo;
        }
        
        TemaBinario encontradoIzquierdo = buscarTema(nodo.getIzquierdo(), nombre);
        if (encontradoIzquierdo != null) {
            return encontradoIzquierdo;
        }
        
        return buscarTema(nodo.getDerecho(), nombre);
    }
    
    // Clase para recibir el nombre
    static class NombreRequest {
        private String nombre;
        
        public String getNombre() {
            return nombre;
        }
        
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }
    
    // Clase para recibir los datos del subtema
    static class SubtemaRequest {
        private String nombrePadre;
        private String nombre;
        private boolean esIzquierdo;
        
        public String getNombrePadre() {
            return nombrePadre;
        }
        
        public void setNombrePadre(String nombrePadre) {
            this.nombrePadre = nombrePadre;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        
        public boolean isEsIzquierdo() {
            return esIzquierdo;
        }
        
        public void setEsIzquierdo(boolean esIzquierdo) {
            this.esIzquierdo = esIzquierdo;
        }
    }
}

@Controller
@RequestMapping("/historial")
class HistorialController {
    private final HistorialActividad historial = new HistorialActividad();
    
    @GetMapping
    public String mostrarHistorial(Model model) {
        model.addAttribute("actividades", historial.obtenerTodasLasActividades());
        model.addAttribute("nuevaActividad", new ActividadRequest());
        return "historial";
    }
    
    @PostMapping("/agregar")
    public String agregarActividad(@ModelAttribute ActividadRequest request) {
        historial.push(request.getDescripcion(), request.getTimestamp());
        return "redirect:/historial";
    }
    
    @GetMapping("/eliminar")
    public String eliminarUltimaActividad() {
        historial.pop();
        return "redirect:/historial";
    }
    
    // Clase para recibir los datos de la actividad
    static class ActividadRequest {
        private String descripcion;
        private String timestamp;
        
        public String getDescripcion() {
            return descripcion;
        }
        
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getTimestamp() {
            return timestamp;
        }
        
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}

@Controller
@RequestMapping("/notificaciones")
class NotificacionesController {
    private final ColaNotificaciones notificaciones = new ColaNotificaciones();
    
    @GetMapping
    public String mostrarNotificaciones(Model model) {
        ColaNotificaciones.Notificacion siguiente = notificaciones.verSiguiente();
        
        if (siguiente != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            model.addAttribute("siguiente", new ColaNotificaciones.NotificacionDTO(
                siguiente.getMensaje(),
                siguiente.getTimestamp().format(formatter)
            ));
        }
        
        model.addAttribute("estaVacia", notificaciones.estaVacia());
        model.addAttribute("nuevaNotificacion", new NotificacionRequest());
        return "notificaciones";
    }
    
    @PostMapping("/agregar")
    public String agregarNotificacion(@ModelAttribute NotificacionRequest request) {
        notificaciones.agregar(request.getMensaje(), request.getTimestamp());
        return "redirect:/notificaciones";
    }
    
    @GetMapping("/atender")
    public String atenderNotificacion() {
        notificaciones.atender();
        return "redirect:/notificaciones";
    }
    
    // Clase para recibir los datos de la notificación
    static class NotificacionRequest {
        private String mensaje;
        private String timestamp;
        
        public String getMensaje() {
            return mensaje;
        }
        
        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
        
        public String getTimestamp() {
            return timestamp;
        }
        
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}

@Controller
@RequestMapping("/convertidor")
class ConvertidorController {
    @GetMapping
    public String mostrarConvertidor(Model model) {
        model.addAttribute("textoOriginal", "");
        model.addAttribute("textoBinario", "");
        return "convertidor";
    }
    
    @PostMapping
    public String convertirTexto(@RequestParam String texto, Model model) {
        String binario = TemaBinarioApplication.convertirTextoBinario(texto);
        model.addAttribute("textoOriginal", texto);
        model.addAttribute("textoBinario", binario);
        return "convertidor";
    }
}

@Controller
class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("resultado", "");
        return "index";
    }
    
    @PostMapping("/procesar")
    public String procesarBinario(@RequestParam String numeroBinario, Model model) {
        String resultado = TemaBinarioApplication.convertirBinarioTexto(numeroBinario);
        model.addAttribute("resultado", resultado);
        return "index";
    }
}
