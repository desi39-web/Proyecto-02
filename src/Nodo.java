public class Nodo<T> {

    private T elemento;
    private Nodo<T> padre;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;
    private int altura;

    // Crear un nuevo Nodo
    public Nodo(T elemento) {
        this.elemento = elemento;
        this.padre = null;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1; 
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public Nodo<T> getPadre() {
        return padre;
    }

    public void setPadre(Nodo<T> padre) {
        this.padre = padre;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getBalance() {
        int alturaIzquierda = (izquierdo == null) ? 0 : izquierdo.altura;
        int alturaDerecha = (derecho == null) ? 0 : derecho.altura;
        return alturaIzquierda - alturaDerecha; 
    }

    /**
     * Compara si este nodo es igual a otro 
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Nodo<?> otroNodo)) {
            return false;
        }
        return this.elemento.equals(otroNodo.elemento);
    }

    @Override
    public String toString() {
        return "| " + this.elemento + " | ";
    }
}
