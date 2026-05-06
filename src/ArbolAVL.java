public class ArbolAVL<T extends Comparable<T>> {
    private Nodo<T> raiz;
    private int elementos;

    public ArbolAVL() {
        this.raiz = null;
        this.elementos = 0;
    }

     public void agregar(T elemento) {
        this.raiz = agregar(this.raiz, elemento);
    }

    public int getTamaño() {
        return elementos;
    }

    public Object[] obtenerTop10() {
        int tamañoEfectivo = (elementos < 10) ? elementos : 10;
        Object[] top10 = new Object[tamañoEfectivo];
        int[] indice = {0}; 
        llenarTop10(this.raiz, top10, indice);
        return top10;
    }

    private void llenarTop10(Nodo<T> nodo, Object[] arreglo, int[] indice) {
        if (nodo == null || indice[0] >= arreglo.length) {
            return;
        }
        
        llenarTop10(nodo.getDerecho(), arreglo, indice);

        if (indice[0] < arreglo.length) {
            arreglo[indice[0]] = nodo.getElemento();
            indice[0]++;
        }

        llenarTop10(nodo.getIzquierdo(), arreglo, indice);
    }
     
    private Nodo<T> agregar(Nodo<T> actual, T elemento) {
         if (actual == null) {
            elementos++;
            return new Nodo<>(elemento);
        }

        if (elemento.compareTo(actual.getElemento()) < 0) {
            actual.setIzquierdo(agregar(actual.getIzquierdo(), elemento));
            actual.getIzquierdo().setPadre(actual);
        } else if (elemento.compareTo(actual.getElemento()) > 0) {
            actual.setDerecho(agregar(actual.getDerecho(), elemento));
            actual.getDerecho().setPadre(actual);
        } else {
            return actual;
        }

        actual.setAltura(1 + Math.max(getAltura(actual.getIzquierdo()), getAltura(actual.getDerecho())));

        return balancear(actual);
    }

    private Nodo<T> balancear(Nodo<T> nodo) {
        int balance = nodo.getBalance();

        if (balance > 1 && nodo.getIzquierdo().getBalance() >= 0) {
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && nodo.getDerecho().getBalance() <= 0) {
            return rotacionIzquierda(nodo);
        }

        if (balance > 1 && nodo.getIzquierdo().getBalance() < 0) {
            nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && nodo.getDerecho().getBalance() > 0) {
            nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

       private Nodo<T> rotacionDerecha(Nodo<T> y) {
        Nodo<T> x = y.getIzquierdo();
        Nodo<T> T2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(T2);

        if (T2 != null) T2.setPadre(y);
        x.setPadre(y.getPadre());
        y.setPadre(x);

        y.setAltura(1 + Math.max(getAltura(y.getIzquierdo()), getAltura(y.getDerecho())));
        x.setAltura(1 + Math.max(getAltura(x.getIzquierdo()), getAltura(x.getDerecho())));

        return x;
    }

    private Nodo<T> rotacionIzquierda(Nodo<T> x) {
        Nodo<T> y = x.getDerecho();
        Nodo<T> T2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(T2);

        if (T2 != null) T2.setPadre(x);
        y.setPadre(x.getPadre());
        x.setPadre(y);

        x.setAltura(1 + Math.max(getAltura(x.getIzquierdo()), getAltura(x.getDerecho())));
        y.setAltura(1 + Math.max(getAltura(y.getIzquierdo()), getAltura(y.getDerecho())));

        return y;
    }

    private int getAltura(Nodo<T> n) {
        return (n == null) ? 0 : n.getAltura();
    }
}
