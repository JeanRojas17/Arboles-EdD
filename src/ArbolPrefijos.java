import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ArbolPrefijos {
    static class Prefijos {
        private final Nodo raiz = new Nodo();

        private static class Nodo {
            Map<Character, Nodo> hijos = new TreeMap<>();
            boolean finDePalabra;
        }

        void insertar(String palabra) {
            Nodo actual = raiz;
            for (char caracter : palabra.toCharArray()) {
                actual = actual.hijos.computeIfAbsent(caracter, clave -> new Nodo());
            }
            actual.finDePalabra = true;
        }

        boolean buscar(String palabra) {
            Nodo nodo = buscarNodo(palabra);
            return nodo != null && nodo.finDePalabra;
        }

        boolean empiezaCon(String prefijo) {
            return buscarNodo(prefijo) != null;
        }

        List<String> autocompletar(String prefijo) {
            List<String> resultados = new ArrayList<>();
            Nodo nodoPrefijo = buscarNodo(prefijo);
            if (nodoPrefijo != null) {
                recolectar(nodoPrefijo, new StringBuilder(prefijo), resultados);
            }
            return resultados;
        }

        private Nodo buscarNodo(String texto) {
            Nodo actual = raiz;
            for (char caracter : texto.toCharArray()) {
                actual = actual.hijos.get(caracter);
                if (actual == null) {
                    return null;
                }
            }
            return actual;
        }

        private void recolectar(Nodo nodo, StringBuilder palabra, List<String> resultados) {
            if (nodo.finDePalabra) {
                resultados.add(palabra.toString());
            }

            for (Map.Entry<Character, Nodo> entrada : nodo.hijos.entrySet()) {
                palabra.append(entrada.getKey());
                recolectar(entrada.getValue(), palabra, resultados);
                palabra.deleteCharAt(palabra.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Prefijos arbol = new Prefijos();
        String[] palabras = {"casa", "casco", "casero", "carro", "carta", "dato", "datos"};

        for (String palabra : palabras) {
            arbol.insertar(palabra);
        }

        System.out.println("Existe 'casa'? " + arbol.buscar("casa"));
        System.out.println("Existe 'cas'? " + arbol.buscar("cas"));
        System.out.println("Hay palabras con prefijo 'cas'? " + arbol.empiezaCon("cas"));
        System.out.println("Autocompletar 'ca': " + arbol.autocompletar("ca"));
        System.out.println("Autocompletar 'dat': " + arbol.autocompletar("dat"));
    }
}