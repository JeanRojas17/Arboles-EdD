public class ArbolB {
    static class B {
        private final int gradoMinimo;
        private Nodo raiz;

        private static class Nodo {
            int cantidadClaves;
            int[] claves;
            Nodo[] hijos;
            boolean hoja;

            Nodo(int gradoMinimo, boolean hoja) {
                this.hoja = hoja;
                this.claves = new int[2 * gradoMinimo - 1];
                this.hijos = new Nodo[2 * gradoMinimo];
            }
        }

        B(int gradoMinimo) {
            if (gradoMinimo < 2) {
                throw new IllegalArgumentException("El grado minimo debe ser al menos 2");
            }
            this.gradoMinimo = gradoMinimo;
            this.raiz = new Nodo(gradoMinimo, true);
        }

        boolean buscar(int clave) {
            return buscar(raiz, clave);
        }

        private boolean buscar(Nodo nodo, int clave) {
            int i = 0;
            while (i < nodo.cantidadClaves && clave > nodo.claves[i]) {
                i++;
            }

            if (i < nodo.cantidadClaves && clave == nodo.claves[i]) {
                return true;
            }

            if (nodo.hoja) {
                return false;
            }

            return buscar(nodo.hijos[i], clave);
        }

        void insertar(int clave) {
            if (raiz.cantidadClaves == 2 * gradoMinimo - 1) {
                Nodo nuevaRaiz = new Nodo(gradoMinimo, false);
                nuevaRaiz.hijos[0] = raiz;
                dividirHijo(nuevaRaiz, 0, raiz);
                raiz = nuevaRaiz;
            }
            insertarNoLleno(raiz, clave);
        }

        private void insertarNoLleno(Nodo nodo, int clave) {
            int i = nodo.cantidadClaves - 1;

            if (nodo.hoja) {
                while (i >= 0 && clave < nodo.claves[i]) {
                    nodo.claves[i + 1] = nodo.claves[i];
                    i--;
                }
                nodo.claves[i + 1] = clave;
                nodo.cantidadClaves++;
                return;
            }

            while (i >= 0 && clave < nodo.claves[i]) {
                i--;
            }
            i++;

            if (nodo.hijos[i].cantidadClaves == 2 * gradoMinimo - 1) {
                dividirHijo(nodo, i, nodo.hijos[i]);
                if (clave > nodo.claves[i]) {
                    i++;
                }
            }
            insertarNoLleno(nodo.hijos[i], clave);
        }

        private void dividirHijo(Nodo padre, int indiceHijo, Nodo hijoLleno) {
            Nodo nuevoHijo = new Nodo(gradoMinimo, hijoLleno.hoja);
            nuevoHijo.cantidadClaves = gradoMinimo - 1;

            for (int j = 0; j < gradoMinimo - 1; j++) {
                nuevoHijo.claves[j] = hijoLleno.claves[j + gradoMinimo];
            }

            if (!hijoLleno.hoja) {
                for (int j = 0; j < gradoMinimo; j++) {
                    nuevoHijo.hijos[j] = hijoLleno.hijos[j + gradoMinimo];
                }
            }

            hijoLleno.cantidadClaves = gradoMinimo - 1;

            for (int j = padre.cantidadClaves; j >= indiceHijo + 1; j--) {
                padre.hijos[j + 1] = padre.hijos[j];
            }
            padre.hijos[indiceHijo + 1] = nuevoHijo;

            for (int j = padre.cantidadClaves - 1; j >= indiceHijo; j--) {
                padre.claves[j + 1] = padre.claves[j];
            }
            padre.claves[indiceHijo] = hijoLleno.claves[gradoMinimo - 1];
            padre.cantidadClaves++;
        }

        void imprimirInorden() {
            imprimirInorden(raiz);
            System.out.println();
        }

        private void imprimirInorden(Nodo nodo) {
            int i;
            for (i = 0; i < nodo.cantidadClaves; i++) {
                if (!nodo.hoja) {
                    imprimirInorden(nodo.hijos[i]);
                }
                System.out.print(nodo.claves[i] + " ");
            }

            if (!nodo.hoja) {
                imprimirInorden(nodo.hijos[i]);
            }
        }

        void imprimirEstructura() {
            imprimirEstructura(raiz, 0);
        }

        private void imprimirEstructura(Nodo nodo, int nivel) {
            System.out.print("Nivel " + nivel + ": ");
            for (int i = 0; i < nodo.cantidadClaves; i++) {
                System.out.print(nodo.claves[i] + " ");
            }
            System.out.println();

            if (!nodo.hoja) {
                for (int i = 0; i <= nodo.cantidadClaves; i++) {
                    imprimirEstructura(nodo.hijos[i], nivel + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        B arbol = new B(2);
        int[] valores = {10, 20, 5, 6, 12, 30, 7, 17, 3, 4, 2};

        for (int valor : valores) {
            arbol.insertar(valor);
        }

        System.out.print("Arbol B en orden: ");
        arbol.imprimirInorden();

        System.out.println("\nEstructura por niveles:");
        arbol.imprimirEstructura();

        System.out.println("\nContiene 12? " + arbol.buscar(12));
        System.out.println("Contiene 99? " + arbol.buscar(99));
    }
}