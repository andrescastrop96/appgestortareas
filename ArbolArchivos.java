import java.util.ArrayList;
import java.util.List;

public class ArbolArchivos {
    private final String nombre;
    private final boolean esArchivo;
    private final List<ArbolArchivos> hijos;

    public ArbolArchivos(String nombre, boolean esArchivo) {
        this.nombre = nombre;
        this.esArchivo = esArchivo;
        this.hijos = new ArrayList<>();
    }

    public void agregarHijo(ArbolArchivos hijo) {
        if (!esArchivo) {
            hijos.add(hijo);
        }
    }

    public void mostrarEstructura(String indentacion) {
        System.out.println(indentacion + (esArchivo ? "📄 " : "📁 ") + nombre);
        for (ArbolArchivos hijo : hijos) {
            hijo.mostrarEstructura(indentacion + "  ");
        }
    }

    public static void main(String[] args) {
        // Crear estructura de ejemplo
        ArbolArchivos raiz = new ArbolArchivos("MiCarpeta", false);

        ArbolArchivos docs = new ArbolArchivos("Documentos", false);
        docs.agregarHijo(new ArbolArchivos("carta.doc", true));
        docs.agregarHijo(new ArbolArchivos("cv.pdf", true));

        ArbolArchivos fotos = new ArbolArchivos("Fotos", false);
        fotos.agregarHijo(new ArbolArchivos("vacaciones.jpg", true));
        fotos.agregarHijo(new ArbolArchivos("familia.png", true));

        raiz.agregarHijo(docs);
        raiz.agregarHijo(fotos);

        // Mostrar estructura
        System.out.println("Estructura de archivos:");
        raiz.mostrarEstructura("");
    }
}
