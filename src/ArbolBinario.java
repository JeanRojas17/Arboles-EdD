import java.util.ArrayDeque;
import java.util.Queue;

public class ArbolBinario {
    static class Nodo {
        int valor;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    static void preorden(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.valor + " ");
        preorden(nodo.izquierdo);
        preorden(nodo.derecho);
    }

    static void inorden(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.izquierdo);
        System.out.print(nodo.valor + " ");
        inorden(nodo.derecho);
    }

    static void postorden(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        postorden(nodo.izquierdo);
        postorden(nodo.derecho);
        System.out.print(nodo.valor + " ");
    }

    static void porNiveles(Nodo raiz) {
        if (raiz == null) {
            return;
        }

        Queue<Nodo> cola = new ArrayDeque<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo actual = cola.remove();
            System.out.print(actual.valor + " ");
            if (actual.izquierdo != null) {
                cola.add(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.add(actual.derecho);
            }
        }
    }

    static int altura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    static int contarHojas(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public static void main(String[] args) {
        Nodo raiz = new Nodo(10);
        raiz.izquierdo = new Nodo(5);
        raiz.derecho = new Nodo(18);
        raiz.izquierdo.izquierdo = new Nodo(2);
        raiz.izquierdo.derecho = new Nodo(7);
        raiz.derecho.izquierdo = new Nodo(15);
        raiz.derecho.derecho = new Nodo(20);

        System.out.print("Preorden: ");
        preorden(raiz);

        System.out.print("\nInorden: ");
        inorden(raiz);

        System.out.print("\nPostorden: ");
        postorden(raiz);

        System.out.print("\nPor niveles: ");
        porNiveles(raiz);

        System.out.println("\nAltura: " + altura(raiz));
        System.out.println("Cantidad de hojas: " + contarHojas(raiz));
    }
}