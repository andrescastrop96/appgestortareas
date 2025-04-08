import java.util.Scanner;

public class formulario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pregunta 1: Nombre
        System.out.print("¿Cuál es tu nombre? ");
        String nombre = scanner.next();

        // Pregunta 2: Edad
        System.out.print("¿Cuál es tu edad? ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Pregunta 3: Correo Electrónico
        System.out.print("¿Cuál es tu dirección de correo electrónico? ");
        String email = scanner.nextLine();

        // Pregunta 4: Número de Teléfono
        System.out.print("¿Cuál es tu número de teléfono? ");
        String telefono = scanner.nextLine();

        // Pregunta 5: Dirección
        System.out.print("¿Cuál es tu dirección? ");
        String direccion = scanner.nextLine();

        // Pregunta 6: Ocupación
        System.out.print("¿Cuál es tu ocupación? ");
        String ocupacion = scanner.next();

        // Pregunta 7: Nivel Educativo
        System.out.print("¿Cuál es tu nivel educativo? ");
        String nivelEducativo = scanner.next();

        // Pregunta 8: Estado Civil
        System.out.print("¿Cuál es tu estado civil? ");
        String estadoCivil = scanner.next();

        // Pregunta 9: Género
        System.out.print("¿Cuál es tu género? ");
        String genero = scanner.next();

        // Pregunta 10: País de Residencia
        System.out.print("¿Cuál es tu país de residencia? ");
        String pais = scanner.next();

        // Pregunta 11: Ciudad
        System.out.print("¿En qué ciudad vives? ");
        String ciudad = scanner.next();

        // Pregunta 12: Número de Idiomas
        System.out.print("¿Cuántos idiomas hablas? ");
        int idiomas = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Pregunta 13: Hobbie Favorito
        System.out.print("¿Cuál es tu hobbie favorito? ");
        String hobbie = scanner.next();

        // Pregunta 14: Mascotas
        System.out.print("¿Tienes alguna mascota? (Sí/No) ");
        String mascota = scanner.next();

        // Pregunta 15: Color Favorito
        System.out.print("¿Cuál es tu color favorito? ");
        String colorFavorito = scanner.next();

    }
}
// Mostrar las respuestas (opcional)
/*
 * System.out.println("\n--- Respuestas del Formulario ---");
 * System.out.println("Nombre: " + nombre);
 * System.out.println("Edad: " + edad);
 * System.out.println("Correo Electrónico: " + email);
 * System.out.println("Teléfono: " + telefono);
 * System.out.println("Dirección: " + direccion);
 * System.out.println("Ocupación: " + ocupacion);
 * System.out.println("Nivel Educativo: " + nivelEducativo);
 * System.out.println("Estado Civil: " + estadoCivil);
 * System.out.println("Género: " + genero);
 * System.out.println("País de Residencia: " + pais);
 * System.out.println("Ciudad: " + ciudad);
 * System.out.println("Idiomas que Hablas: " + idiomas);
 * System.out.println("Hobbie Favorito: " + hobbie);
 * System.out.println("Mascota: " + mascota);
 * System.out.println("Color Favorito: " + colorFavorito);
 * }
 * }
 */
