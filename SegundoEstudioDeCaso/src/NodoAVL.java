public class NodoAVL {
    int llave;
    int altura;
    NodoAVL izquierda;
    NodoAVL derecha;

    public NodoAVL(int llave) {
        this.llave = llave;
        this.altura = 1;
    }

    public static int obtenerAltura(NodoAVL n) {
        return (n == null) ? 0 : n.altura;
    }

    public void actualizarAltura() {
        this.altura = 1 + Math.max(obtenerAltura(this.izquierda), obtenerAltura(this.derecha));
    }

    public int obtenerFactorBalance() {
        return obtenerAltura(this.izquierda) - obtenerAltura(this.derecha);
    }

    // Rotación simple derecha (LL)
    public NodoAVL rotarDerecha() {
        NodoAVL nuevaRaiz = this.izquierda;
        NodoAVL T2 = nuevaRaiz.derecha;

        nuevaRaiz.derecha = this;
        this.izquierda = T2;

        this.actualizarAltura();
        nuevaRaiz.actualizarAltura();

        return nuevaRaiz;
    }

    // Rotación simple izquierda (RR)
    public NodoAVL rotarIzquierda() {
        NodoAVL nuevaRaiz = this.derecha;
        NodoAVL T2 = nuevaRaiz.izquierda;

        nuevaRaiz.izquierda = this;
        this.derecha = T2;

        this.actualizarAltura();
        nuevaRaiz.actualizarAltura();

        return nuevaRaiz;
    }

    // LR
    public NodoAVL rotarIzquierdaDerecha() {
        this.izquierda = this.izquierda.rotarIzquierda();
        return this.rotarDerecha();
    }

    // RL
    public NodoAVL rotarDerechaIzquierda() {
        this.derecha = this.derecha.rotarDerecha();
        return this.rotarIzquierda();
    }
}
