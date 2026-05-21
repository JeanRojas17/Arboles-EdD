import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ArbolGeneral {
    static class Nodo {
        String valor;
        List<Nodo> hijos = new ArrayList<>();

        Nodo(String valor) {
            this.valor = valor;
        }

        Nodo agregarHijo(String valorHijo) {
            Nodo hijo = new Nodo(valorHijo);
            hijos.add(hijo);
            return hijo;
        }
    }

    static void imprimirProfundidad(Nodo nodo, String sangria) {
        if (nodo == null) {
            return;
        }

        System.out.println(sangria + "- " + nodo.valor);
        for (Nodo hijo : nodo.hijos) {
            imprimirProfundidad(hijo, sangria + "  ");
        }
    }

    static void imprimirPorNiveles(Nodo raiz) {
        if (raiz == null) {
            return;
        }

        Queue<Nodo> cola = new ArrayDeque<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo actual = cola.remove();
            System.out.print(actual.valor + " ");
            cola.addAll(actual.hijos);
        }
        System.out.println();
    }

    static int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int total = 1;
        for (Nodo hijo : nodo.hijos) {
            total += contarNodos(hijo);
        }
        return total;
    }

    static int altura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int mayorAlturaHijo = 0;
        for (Nodo hijo : nodo.hijos) {
            mayorAlturaHijo = Math.max(mayorAlturaHijo, altura(hijo));
        }
        return 1 + mayorAlturaHijo;
    }

    public static void main(String[] args) {
        Nodo universidad = new Nodo("Universidad");
        Nodo ingenieria = universidad.agregarHijo("Facultad de Ingenieria");
        Nodo administracion = universidad.agregarHijo("Facultad de Administracion");

        Nodo sistemas = ingenieria.agregarHijo("Ingenieria de Sistemas");
        ingenieria.agregarHijo("Ingenieria Industrial");
        administracion.agregarHijo("Contaduria");
        administracion.agregarHijo("Mercadeo");

        sistemas.agregarHijo("Estructura de Datos");
        sistemas.agregarHijo("Bases de Datos");
        sistemas.agregarHijo("Programacion Web");

        System.out.println("Recorrido en profundidad:");
        imprimirProfundidad(universidad, "");

        System.out.println("\nRecorrido por niveles:");
        imprimirPorNiveles(universidad);

        System.out.println("\nCantidad de nodos: " + contarNodos(universidad));
        System.out.println("Altura en niveles: " + altura(universidad));
    }
}