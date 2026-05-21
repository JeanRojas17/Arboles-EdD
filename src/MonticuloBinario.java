import java.util.ArrayList;
import java.util.List;

public class MonticuloBinario {
    static class MonticuloMinimo {
        private final List<Integer> monticulo = new ArrayList<>();

        void insertar(int valor) {
            monticulo.add(valor);
            subir(monticulo.size() - 1);
        }

        int consultarMinimo() {
            if (monticulo.isEmpty()) {
                throw new IllegalStateException("El monticulo esta vacio");
            }
            return monticulo.get(0);
        }

        int extraerMinimo() {
            if (monticulo.isEmpty()) {
                throw new IllegalStateException("El monticulo esta vacio");
            }

            int minimo = monticulo.get(0);
            int ultimo = monticulo.remove(monticulo.size() - 1);

            if (!monticulo.isEmpty()) {
                monticulo.set(0, ultimo);
                bajar(0);
            }

            return minimo;
        }

        private void subir(int indice) {
            while (indice > 0) {
                int padre = (indice - 1) / 2;
                if (monticulo.get(padre) <= monticulo.get(indice)) {
                    break;
                }
                intercambiar(padre, indice);
                indice = padre;
            }
        }

        private void bajar(int indice) {
            while (true) {
                int izquierdo = 2 * indice + 1;
                int derecho = 2 * indice + 2;
                int menor = indice;

                if (izquierdo < monticulo.size() && monticulo.get(izquierdo) < monticulo.get(menor)) {
                    menor = izquierdo;
                }
                if (derecho < monticulo.size() && monticulo.get(derecho) < monticulo.get(menor)) {
                    menor = derecho;
                }
                if (menor == indice) {
                    break;
                }

                intercambiar(indice, menor);
                indice = menor;
            }
        }

        private void intercambiar(int i, int j) {
            int temporal = monticulo.get(i);
            monticulo.set(i, monticulo.get(j));
            monticulo.set(j, temporal);
        }

        void imprimirArregloInterno() {
            System.out.println(monticulo);
        }
    }

    public static void main(String[] args) {
        MonticuloMinimo monticulo = new MonticuloMinimo();
        int[] prioridades = {5, 3, 8, 1, 2, 7, 4};

        for (int prioridad : prioridades) {
            monticulo.insertar(prioridad);
        }

        System.out.print("Representacion interna del monticulo minimo: ");
        monticulo.imprimirArregloInterno();

        System.out.println("Elemento con mayor prioridad: " + monticulo.consultarMinimo());
        System.out.print("Extraccion ordenada: ");
        while (true) {
            try {
                System.out.print(monticulo.extraerMinimo() + " ");
            } catch (IllegalStateException terminado) {
                break;
            }
        }
        System.out.println();
    }
}