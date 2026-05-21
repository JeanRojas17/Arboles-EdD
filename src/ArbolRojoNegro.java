import java.util.Map;
import java.util.TreeMap;

public class ArbolRojoNegro {
    public static void main(String[] args) {
        TreeMap<Integer, String> estudiantes = new TreeMap<>();

        estudiantes.put(108, "Laura");
        estudiantes.put(103, "Carlos");
        estudiantes.put(115, "Ana");
        estudiantes.put(101, "Sofia");
        estudiantes.put(110, "Miguel");

        System.out.println("TreeMap usa un arbol rojo-negro para mantener las claves ordenadas:");
        for (Map.Entry<Integer, String> entrada : estudiantes.entrySet()) {
            System.out.println(entrada.getKey() + " -> " + entrada.getValue());
        }

        System.out.println("\nBusqueda por clave 110: " + estudiantes.get(110));
        System.out.println("Primera clave: " + estudiantes.firstKey());
        System.out.println("Ultima clave: " + estudiantes.lastKey());
        System.out.println("Clave menor o igual que 109: " + estudiantes.floorKey(109));
        System.out.println("Clave mayor o igual que 109: " + estudiantes.ceilingKey(109));

        estudiantes.remove(103);
        System.out.println("\nDespues de eliminar la clave 103: " + estudiantes);
    }
}