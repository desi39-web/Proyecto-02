import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Lector {

    public static String leerArchivo(String ruta) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(" ");
            }
        } catch (IOException e) {
            System.err.println("No se puede leer el archivo: " + ruta);
        }
        return contenido.toString();
    }

    public static String[] obtenerPalabras(String ruta) {
        String texto = leerArchivo(ruta).toLowerCase();
        return texto.replaceAll("[^a-zñáéíóú\\s]", "").split("\\s+");
    }

    public static int contarFrecuencia(String[] palabras, String terminoBusqueda) {
        int contador = 0;
        for (String palabra : palabras) {
            if (palabra.equals(terminoBusqueda.toLowerCase())) {
                contador++;
            }
        }
        return contador;
    }
}
