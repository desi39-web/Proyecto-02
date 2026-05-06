public class Documento implements Comparable<Documento> {
    private String ruta;
    private double prioridad;

    public Documento(String ruta, double prioridad) {
        this.ruta = ruta;
        this.prioridad = prioridad;
    }

    public String getRuta() {
        return ruta;
    }

    public double getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(double prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Documento otro) {
        if (this.prioridad < otro.prioridad) return -1;
        if (this.prioridad > otro.prioridad) return 1;
        return this.ruta.compareTo(otro.ruta); 
    }

    @Override
    public String toString() {
        return "Archivo: " + ruta + " | Relevancia: " + String.format("%.4f", prioridad);
    }
}
