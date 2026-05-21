public class ArbolAVL {
    static class AVL {
        private Nodo raiz;

        private static class Nodo {
            int valor;
            int altura = 1;
            Nodo izquierdo;
            Nodo derecho;

            Nodo(int valor) {
                this.valor = valor;
            }
        }

        void insertar(int valor) {
            raiz = insertar(raiz, valor);
        }

        private Nodo insertar(Nodo nodo, int valor) {
            if (nodo == null) {
                return new Nodo(valor);
            }

            if (valor < nodo.valor) {
                nodo.izquierdo = insertar(nodo.izquierdo, valor);
            } else if (valor > nodo.valor) {
                nodo.derecho = insertar(nodo.derecho, valor);
            } else {
                return nodo;
            }

            actualizarAltura(nodo);
            return rebalancear(nodo);
        }

        boolean contiene(int valor) {
            Nodo actual = raiz;
            while (actual != null) {
                if (valor == actual.valor) {
                    return true;
                }
                actual = valor < actual.valor ? actual.izquierdo : actual.derecho;
            }
            return false;
        }

        private Nodo rebalancear(Nodo nodo) {
            int balance = factorBalance(nodo);

            if (balance > 1) {
                if (factorBalance(nodo.izquierdo) < 0) {
                    nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
                }
                return rotarDerecha(nodo);
            }

            if (balance < -1) {
                if (factorBalance(nodo.derecho) > 0) {
                    nodo.derecho = rotarDerecha(nodo.derecho);
                }
                return rotarIzquierda(nodo);
            }

            return nodo;
        }

        private Nodo rotarDerecha(Nodo y) {
            Nodo x = y.izquierdo;
            Nodo subarbolMovido = x.derecho;

            x.derecho = y;
            y.izquierdo = subarbolMovido;

            actualizarAltura(y);
            actualizarAltura(x);
            return x;
        }

        private Nodo rotarIzquierda(Nodo x) {
            Nodo y = x.derecho;
            Nodo subarbolMovido = y.izquierdo;

            y.izquierdo = x;
            x.derecho = subarbolMovido;

            actualizarAltura(x);
            actualizarAltura(y);
            return y;
        }

        private void actualizarAltura(Nodo nodo) {
            nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        }

        private int altura(Nodo nodo) {
            return nodo == null ? 0 : nodo.altura;
        }

        private int factorBalance(Nodo nodo) {
            return nodo == null ? 0 : altura(nodo.izquierdo) - altura(nodo.derecho);
        }

        void imprimirInordenConBalance() {
            imprimirInordenConBalance(raiz);
            System.out.println();
        }

        private void imprimirInordenConBalance(Nodo nodo) {
            if (nodo == null) {
                return;
            }
            imprimirInordenConBalance(nodo.izquierdo);
            System.out.print(nodo.valor + "(b=" + factorBalance(nodo) + ") ");
            imprimirInordenConBalance(nodo.derecho);
        }

        int altura() {
            return altura(raiz);
        }
    }

    public static void main(String[] args) {
        AVL arbol = new AVL();
        int[] valores = {30, 20, 10, 25, 28, 40, 50, 45, 60};

        for (int valor : valores) {
            arbol.insertar(valor);
        }

        System.out.print("AVL en orden con factor de balance: ");
        arbol.imprimirInordenConBalance();
        System.out.println("Altura del arbol: " + arbol.altura());
        System.out.println("Contiene 45? " + arbol.contiene(45));
    }
}