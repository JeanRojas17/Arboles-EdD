public class ArbolBinarioBusqueda {
    static class BinarioBusqueda {
        private Nodo raiz;

        private static class Nodo {
            int valor;
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
            }
            return nodo;
        }

        boolean contiene(int valor) {
            return contiene(raiz, valor);
        }

        private boolean contiene(Nodo nodo, int valor) {
            if (nodo == null) {
                return false;
            }
            if (valor == nodo.valor) {
                return true;
            }
            return valor < nodo.valor
                    ? contiene(nodo.izquierdo, valor)
                    : contiene(nodo.derecho, valor);
        }

        void eliminar(int valor) {
            raiz = eliminar(raiz, valor);
        }

        private Nodo eliminar(Nodo nodo, int valor) {
            if (nodo == null) {
                return null;
            }

            if (valor < nodo.valor) {
                nodo.izquierdo = eliminar(nodo.izquierdo, valor);
            } else if (valor > nodo.valor) {
                nodo.derecho = eliminar(nodo.derecho, valor);
            } else {
                if (nodo.izquierdo == null) {
                    return nodo.derecho;
                }
                if (nodo.derecho == null) {
                    return nodo.izquierdo;
                }

                Nodo sucesor = minimo(nodo.derecho);
                nodo.valor = sucesor.valor;
                nodo.derecho = eliminar(nodo.derecho, sucesor.valor);
            }
            return nodo;
        }

        private Nodo minimo(Nodo nodo) {
            while (nodo.izquierdo != null) {
                nodo = nodo.izquierdo;
            }
            return nodo;
        }

        void imprimirInorden() {
            imprimirInorden(raiz);
            System.out.println();
        }

        private void imprimirInorden(Nodo nodo) {
            if (nodo == null) {
                return;
            }
            imprimirInorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            imprimirInorden(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        BinarioBusqueda arbol = new BinarioBusqueda();
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 65};

        for (int valor : valores) {
            arbol.insertar(valor);
        }

        System.out.print("Arbol binario de busqueda en orden: ");
        arbol.imprimirInorden();

        System.out.println("Contiene 40? " + arbol.contiene(40));
        System.out.println("Contiene 90? " + arbol.contiene(90));

        arbol.eliminar(70);
        System.out.print("Despues de eliminar 70: ");
        arbol.imprimirInorden();
    }
}