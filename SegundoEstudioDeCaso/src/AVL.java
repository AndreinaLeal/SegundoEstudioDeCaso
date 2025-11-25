import java.io.PrintStream;

public class AVL {
    NodoAVL raiz;
    private PrintStream out = System.out;

    public boolean buscar(int llave) {
        NodoAVL actual = raiz;
        while (actual != null) {
            if (llave == actual.llave) return true;
            if (llave < actual.llave) actual = actual.izquierda;
            else actual = actual.derecha;
        }
        return false;
    }

    public void insertar(int llave) {
        raiz = insertarRec(raiz, llave);
    }

    private NodoAVL insertarRec(NodoAVL nodo, int llave) {
        if (nodo == null) return new NodoAVL(llave);

        if (llave < nodo.llave)
            nodo.izquierda = insertarRec(nodo.izquierda, llave);
        else if (llave > nodo.llave)
            nodo.derecha = insertarRec(nodo.derecha, llave);
        else
            return nodo;

        nodo.actualizarAltura();
        return balancear(nodo, llave);
    }

    private NodoAVL balancear(NodoAVL nodo, int llaveInsertada) {
        int fb = nodo.obtenerFactorBalance();

        if (fb > 1 && llaveInsertada < nodo.izquierda.llave) {
            out.println("[LL] Rotación simple derecha sobre " + nodo.llave);
            return nodo.rotarDerecha();
        }

        if (fb < -1 && llaveInsertada > nodo.derecha.llave) {
            out.println("[RR] Rotación simple izquierda sobre " + nodo.llave);
            return nodo.rotarIzquierda();
        }

        if (fb > 1 && llaveInsertada > nodo.izquierda.llave) {
            out.println("[LR] Rotación doble izquierda-derecha sobre " + nodo.llave);
            return nodo.rotarIzquierdaDerecha();
        }

        if (fb < -1 && llaveInsertada < nodo.derecha.llave) {
            out.println("[RL] Rotación doble derecha-izquierda sobre " + nodo.llave);
            return nodo.rotarDerechaIzquierda();
        }

        return nodo;
    }

    public void eliminar(int llave) {
        raiz = eliminarRec(raiz, llave);
    }

    private NodoAVL eliminarRec(NodoAVL nodo, int llave) {
        if (nodo == null) return null;

        if (llave < nodo.llave)
            nodo.izquierda = eliminarRec(nodo.izquierda, llave);
        else if (llave > nodo.llave)
            nodo.derecha = eliminarRec(nodo.derecha, llave);
        else {

            if (nodo.izquierda == null || nodo.derecha == null) {
                nodo = (nodo.izquierda != null) ? nodo.izquierda : nodo.derecha;
            } else {
                NodoAVL sucesor = obtenerMinimo(nodo.derecha);
                nodo.llave = sucesor.llave;
                nodo.derecha = eliminarRec(nodo.derecha, sucesor.llave);
            }
        }

        if (nodo == null) return null;

        nodo.actualizarAltura();
        return balancearEliminar(nodo);
    }

    private NodoAVL obtenerMinimo(NodoAVL nodo) {
        while (nodo.izquierda != null) nodo = nodo.izquierda;
        return nodo;
    }

    private NodoAVL balancearEliminar(NodoAVL nodo) {
        int fb = nodo.obtenerFactorBalance();

        if (fb > 1 && nodo.izquierda.obtenerFactorBalance() >= 0)
            return nodo.rotarDerecha();

        if (fb > 1 && nodo.izquierda.obtenerFactorBalance() < 0)
            return nodo.rotarIzquierdaDerecha();

        if (fb < -1 && nodo.derecha.obtenerFactorBalance() <= 0)
            return nodo.rotarIzquierda();

        if (fb < -1 && nodo.derecha.obtenerFactorBalance() > 0)
            return nodo.rotarDerechaIzquierda();

        return nodo;
    }

    public void imprimirEnOrden() {
        imprimirEnOrdenRec(raiz);
        System.out.println();
    }

    private void imprimirEnOrdenRec(NodoAVL nodo) {
        if (nodo != null) {
            imprimirEnOrdenRec(nodo.izquierda);
            System.out.print(nodo.llave + " ");
            imprimirEnOrdenRec(nodo.derecha);
        }
    }

    public void imprimirDetalles() {
        imprimirDetallesRec(raiz, 0);
    }

    private void imprimirDetallesRec(NodoAVL nodo, int nivel) {
        if (nodo != null) {
            imprimirDetallesRec(nodo.derecha, nivel + 1);
            System.out.println("    ".repeat(nivel) + nodo.llave + " (h=" + nodo.altura + ", fb=" + nodo.obtenerFactorBalance() + ")");
            imprimirDetallesRec(nodo.izquierda, nivel + 1);
        }
    }
}
