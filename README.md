<div align="center">

# Trabajo Escrito Investigativo: Árboles en Estructuras de Datos

**Integrantes - Grupo 411**  
Jean Paul Rojas Herrera  
Michael Dowglas Lenis Chaguendo

**Docente**  
Guillermo Alberto Idrobo Campo

</div>

## Tabla de contenidos

1. [Concepto de árbol](#concepto-de-árbol)
2. [Terminología básica](#terminología-básica)
3. [Propiedades importantes](#propiedades-importantes)
4. [Recorridos de árboles](#recorridos-de-árboles)  
    4.1. [Recorridos en profundidad](#recorridos-en-profundidad)  
    4.2. [Recorrido en anchura](#recorrido-en-anchura)
5. [Árbol general o n-ario](#árbol-general-o-n-ario)
6. [Árbol binario](#árbol-binario)
7. [Árbol binario de búsqueda](#árbol-binario-de-búsqueda)
8. [Árbol AVL](#árbol-avl)
9. [Árbol rojo-negro](#árbol-rojo-negro)
10. [Montículo binario](#montículo-binario)
11. [Árbol de prefijos](#árbol-de-prefijos)
12. [Árbol B](#árbol-b)
13. [Comparación general](#comparación-general)
14. [Complejidades aproximadas](#complejidades-aproximadas)
15. [Importancia de los árboles en programación](#importancia-de-los-árboles-en-programación)
16. [Instrucciones de uso para ejecutar y compilar los archivos](#instrucciones-de-uso-para-ejecutar-y-compilar-los-archivos)

## Concepto de árbol

Un árbol es una estructura compuesta por nodos conectados mediante enlaces o aristas. Existe un nodo principal llamado raíz, desde el cual se accede al resto de la estructura. Cada nodo puede tener cero o más hijos. Cuando un nodo no tiene hijos, se llama hoja.

Formalmente, un árbol puede entenderse como una estructura recursiva:

- Un árbol vacío no contiene nodos.
- Un árbol no vacío tiene una raíz.
- Cada hijo de la raíz es, a su vez, la raíz de otro árbol llamado subárbol.

Esta definición es importante porque muchas operaciones sobre árboles se implementan de forma recursiva. Por ejemplo, para calcular la altura de un árbol se calcula primero la altura de sus subárboles. Para buscar un elemento en un árbol binario de búsqueda, se decide si continuar por el subárbol izquierdo o por el derecho. Para recorrer un árbol, se visita un nodo y luego se aplican las mismas reglas a sus hijos.

Según el **NIST Dictionary of Algorithms and Data Structures**, un árbol suele representarse con la raíz en la parte superior y las hojas en la parte inferior, aunque en la naturaleza ocurra al revés. Esa convención visual ayuda a entender la relación jerárquica entre nodos.

## Terminología básica

`Nodo:` unidad básica de almacenamiento dentro del árbol. Puede contener un dato, una clave, referencias a hijos y, en algunos casos, información adicional como altura, color o prioridad.

`Raíz:` nodo principal del árbol. No tiene padre.

`Padre:` nodo que tiene enlaces hacia otros nodos.

`Hijo:` nodo descendiente directo de otro nodo.

`Hermanos:` nodos que comparten el mismo padre.

`Hoja:` nodo que no tiene hijos.

`Nodo interno:` nodo que tiene al menos un hijo.

`Arista:` conexión entre un nodo padre y un nodo hijo.

`Camino:` secuencia de nodos conectados desde un origen hasta un destino.

`Profundidad:` cantidad de aristas desde la raíz hasta un nodo.

`Nivel:` posición del nodo dentro del árbol. A veces se usa nivel 0 para la raíz; otras veces nivel 1. Lo importante es mantener una convención.

`Altura de un nodo:` longitud del camino más largo desde ese nodo hasta una hoja.

`Altura del árbol:` altura de la raíz.

`Grado de un nodo:` cantidad de hijos que tiene.

`Grado del árbol:` máximo grado entre todos sus nodos.

`Subárbol:` árbol formado por un nodo y todos sus descendientes.

## Propiedades importantes

La eficiencia de un árbol depende mucho de su altura. En una estructura lineal, como una lista enlazada, buscar un elemento puede requerir revisar elemento por elemento. En un árbol bien organizado y balanceado, la búsqueda puede reducirse a una cantidad mucho menor de pasos.

Por ejemplo, en un árbol binario de búsqueda balanceado con `n` elementos, la altura suele ser proporcional a `log2(n)`. Eso significa que, si hay 1.000.000 de elementos, no se necesitan un millón de comparaciones para encontrar un dato; en el caso ideal se necesitan alrededor de 20 niveles de decisión.

Sin embargo, si el árbol se desbalancea, puede degenerar en una lista. Si se insertan valores ordenados en un árbol binario de búsqueda simple, como `1, 2, 3, 4, 5`, el árbol puede quedar inclinado hacia un solo lado. En ese caso, su altura se vuelve `n` y las operaciones pierden eficiencia.

Por eso existen árboles balanceados como `AVL`, `rojo-negro` y `árbol B`: su propósito es controlar la altura.

## Recorridos de árboles

Recorrer un árbol significa visitar sus nodos siguiendo un orden. Los recorridos son esenciales para imprimir datos, evaluar expresiones, copiar árboles, serializar estructuras, buscar información o liberar memoria.

- ### Recorridos en profundidad

Los recorridos en profundidad exploran una rama antes de pasar a otra.

**Preorden:** visita primero la raíz, luego el subárbol izquierdo y luego el subárbol derecho.  
Orden: raíz, izquierda, derecha.  
Uso común: copiar árboles, generar representaciones prefijas, recorrer jerarquías desde lo general hacia lo específico.

**Inorden:** visita primero el subárbol izquierdo, luego la raíz y luego el subárbol derecho.  
Orden: izquierda, raíz, derecha.  
Uso común: en un árbol binario de búsqueda, produce los datos ordenados de menor a mayor.

**Postorden:** visita primero los subárboles y al final la raíz.  
Orden: izquierda, derecha, raíz.  
Uso común: eliminar árboles, evaluar expresiones en notación postfija, procesar dependencias antes del nodo que depende de ellas.

- ### Recorrido en anchura

El recorrido por niveles, también llamado búsqueda en anchura o BFS, visita primero la raíz, luego sus hijos, luego sus nietos, y así sucesivamente. Normalmente se implementa con una cola.

Este recorrido es útil cuando interesa analizar el árbol nivel por nivel, por ejemplo, para imprimir una estructura jerárquica, encontrar el nodo más cercano que cumple una condición o calcular distancias mínimas en árboles no ponderados.

**Código relacionado:** [`src/ArbolBinario.java`](src/ArbolBinario.java)

## Árbol general o n-ario

Un árbol general es aquel en el que cada nodo puede tener cualquier cantidad de hijos. También se conoce como árbol n-ario cuando se permite hasta `n` hijos por nodo, aunque en la práctica muchas implementaciones usan listas dinámicas para permitir una cantidad variable.

Este tipo de árbol es adecuado para representar jerarquías flexibles:

- Carpetas y archivos.
- Organigramas empresariales.
- Categorías y subcategorías.
- Menús de navegación.
- Árboles sintácticos abstractos.

La ventaja principal es su flexibilidad. La desventaja es que algunas operaciones son menos directas que en árboles binarios, porque un nodo puede tener muchos hijos y se debe recorrer una lista de descendientes.

**Código relacionado:** [`src/ArbolGeneral.java`](src/ArbolGeneral.java)

## Árbol binario

Un árbol binario es un árbol donde cada nodo tiene como máximo dos hijos: hijo izquierdo e hijo derecho. Esta restricción facilita la implementación de recorridos, búsqueda, inserción y algoritmos recursivos.

No todo árbol binario está ordenado. Un árbol binario simple solo limita la cantidad de hijos; no impone una regla sobre qué valores deben ir a la izquierda o a la derecha.

Algunos conceptos importantes:

**Árbol binario lleno:** cada nodo tiene 0 o 2 hijos.

**Árbol binario completo:** todos los niveles están llenos excepto posiblemente el último, que se llena de izquierda a derecha.

**Árbol binario perfecto:** todos los nodos internos tienen dos hijos y todas las hojas están al mismo nivel.

**Árbol binario degenerado:** cada nodo tiene un solo hijo, por lo que se comporta como una lista enlazada.

Los árboles binarios son la base de otros tipos más especializados, como árboles binarios de búsqueda, montículos binarios, AVL y rojo-negro.

**Código relacionado:** [`src/ArbolBinario.java`](src/ArbolBinario.java)

## Árbol binario de búsqueda

Un árbol binario de búsqueda es un árbol binario que cumple una propiedad de orden:

- Los valores menores que un nodo se ubican en su subárbol izquierdo.
- Los valores mayores que un nodo se ubican en su subárbol derecho.
- Esta regla se aplica recursivamente a todos los nodos.

Gracias a esta propiedad, la búsqueda se parece a una búsqueda binaria. En cada comparación se descarta una parte del árbol. Si el valor buscado es menor que el nodo actual, se continúa por la izquierda. Si es mayor, se continúa por la derecha.

### Operaciones principales

**Búsqueda:** compara la clave con el nodo actual y avanza hacia la izquierda o derecha. Su complejidad depende de la altura: `O(h)`.

**Inserción:** busca la posición correcta respetando la regla de orden y agrega el nuevo nodo como hoja. También cuesta `O(h)`.

**Eliminación:** tiene tres casos:

- Si el nodo es hoja, se elimina directamente.
- Si tiene un solo hijo, el hijo reemplaza al nodo.
- Si tiene dos hijos, se reemplaza por su sucesor inorden, normalmente el menor valor del subárbol derecho.

**Recorrido inorden:** devuelve los valores ordenados.

### Ventajas y desventajas

La ventaja del árbol binario de búsqueda es que permite mantener datos ordenados y buscar de forma eficiente si el árbol está balanceado. Su debilidad es que no se balancea automáticamente. Si se insertan datos ya ordenados, puede volverse una lista y sus operaciones pasan de `O(log n)` a `O(n)`.

**Código relacionado:** [`src/ArbolBinarioBusqueda.java`](src/ArbolBinarioBusqueda.java)

## Árbol AVL

Un árbol AVL es un árbol binario de búsqueda auto-balanceado. Fue propuesto por Adelson-Velsky y Landis. Su regla principal es que, para cada nodo, la diferencia de altura entre el subárbol izquierdo y el derecho no puede ser mayor que 1.

Esa diferencia se llama factor de balance:

```text
factor de balance = altura(izquierdo) - altura(derecho)
```

Un nodo AVL válido debe tener factor de balance `-1`, `0` o `1`. Si después de insertar o eliminar un nodo el factor sale de ese rango, el árbol aplica rotaciones.

### Rotaciones AVL

**Rotación simple a la derecha:** se usa cuando el desbalance ocurre por la izquierda de la izquierda.

**Rotación simple a la izquierda:** se usa cuando el desbalance ocurre por la derecha de la derecha.

**Rotación doble izquierda-derecha:** se usa cuando el desbalance ocurre por la derecha del hijo izquierdo.

**Rotación doble derecha-izquierda:** se usa cuando el desbalance ocurre por la izquierda del hijo derecho.

Las rotaciones reorganizan nodos sin romper la propiedad de búsqueda. El objetivo es reducir la altura y mantener el orden.

### Importancia

Los AVL son más estrictos que los árboles rojo-negro en cuanto al balance. Esto hace que las búsquedas sean muy rápidas, porque la altura se mantiene baja. A cambio, pueden requerir más rotaciones durante inserciones y eliminaciones.

Son útiles cuando se hacen muchas búsquedas y relativamente menos modificaciones.

**Código relacionado:** [`src/ArbolAVL.java`](src/ArbolAVL.java)

## Árbol rojo-negro

Un árbol rojo-negro es otro tipo de árbol binario de búsqueda auto-balanceado. Cada nodo tiene un color: rojo o negro. El color no es información del dato del usuario, sino un mecanismo interno para mantener el equilibrio.

Sus reglas principales son:

- Cada nodo es rojo o negro.
- La raíz es negra.
- Las hojas nulas se consideran negras.
- Un nodo rojo no puede tener un hijo rojo.
- Todos los caminos desde un nodo hasta sus hojas nulas deben tener la misma cantidad de nodos negros.

Estas reglas garantizan que el árbol no se desbalancee demasiado. El NIST describe el árbol rojo-negro como un árbol casi balanceado que usa un bit adicional por nodo para conservar el equilibrio. También indica que su altura está limitada por una expresión proporcional a `2 log2(n + 1)`.

### Comparación con AVL

Los AVL mantienen un balance más estricto; por eso pueden ser mejores cuando predominan las búsquedas. Los rojo-negro permiten un balance un poco más flexible; por eso suelen ser convenientes cuando hay muchas inserciones y eliminaciones.

En Java, la clase `TreeMap` está implementada como un árbol rojo-negro y garantiza costo logarítmico para operaciones como `get`, `put`, `containsKey` y `remove`, según su documentación oficial.

**Código relacionado:** [`src/ArbolRojoNegro.java`](src/ArbolRojoNegro.java)

## Montículo binario

Un montículo binario es un árbol binario completo que cumple una propiedad de prioridad. Existen dos variantes principales:

**Montículo mínimo:** el valor de cada nodo es menor o igual que el de sus hijos. El mínimo queda en la raíz.

**Montículo máximo:** el valor de cada nodo es mayor o igual que el de sus hijos. El máximo queda en la raíz.

Aunque se puede visualizar como un árbol, normalmente se implementa con un arreglo o `ArrayList`. Esto es posible porque el montículo binario es completo. Si un nodo está en la posición `i`, entonces:

```text
padre = (i - 1) / 2
hijo izquierdo = 2 * i + 1
hijo derecho = 2 * i + 2
```

### Operaciones

**Insertar:** se agrega el elemento al final y se sube mientras viole la propiedad del montículo. Complejidad `O(log n)`.

**Consultar raíz:** devuelve el mínimo o máximo sin eliminarlo. Complejidad `O(1)`.

**Extraer raíz:** elimina el mínimo o máximo, reemplaza la raíz por el último elemento y lo baja hasta restaurar el orden. Complejidad `O(log n)`.

### Aplicaciones

- Colas de prioridad.
- Algoritmo de Dijkstra.
- Algoritmo de Prim.
- Planificación de procesos.
- Ordenamiento por montículo.
- Sistemas donde siempre se necesita procesar el elemento más urgente.

**Código relacionado:** [`src/MonticuloBinario.java`](src/MonticuloBinario.java)

## Árbol de prefijos

Un árbol de prefijos es un árbol especializado en almacenar cadenas de texto. Cada arista o nodo representa un carácter, y los caminos desde la raíz forman palabras o prefijos.

Por ejemplo, si se almacenan las palabras `casa`, `casco` y `carro`, todas comparten el prefijo `ca`. El árbol de prefijos evita repetir completamente ese prefijo y ramifica solo cuando las palabras se diferencian.

### Ventajas

- Búsqueda de palabras en `O(m)`, donde `m` es la longitud de la palabra.
- Búsqueda eficiente por prefijo.
- Autocompletado.
- Correctores ortográficos.
- Diccionarios.
- Filtros de palabras.
- Enrutamiento de direcciones o patrones.

### Desventajas

Puede consumir más memoria que otras estructuras, porque cada nodo puede tener referencias a muchos caracteres. Para reducir este costo se usan variantes como árboles de prefijos comprimidos, árboles radix o árboles ternarios de búsqueda.

**Código relacionado:** [`src/ArbolPrefijos.java`](src/ArbolPrefijos.java)

## Árbol B

Un árbol B es un árbol de búsqueda balanceado de múltiples caminos. A diferencia de un árbol binario de búsqueda, un nodo de árbol B puede almacenar varias claves y tener varios hijos. Esto reduce la altura del árbol y lo hace especialmente útil para almacenamiento en disco y bases de datos.

La idea clave es que acceder a disco es mucho más costoso que comparar valores en memoria. Por eso conviene que cada nodo contenga muchas claves, de forma que cada lectura de bloque traiga bastante información útil.

### Características generales

- Todos los nodos hoja se encuentran al mismo nivel.
- Cada nodo puede tener varias claves ordenadas.
- Un nodo con `k` claves puede tener hasta `k + 1` hijos.
- La estructura se mantiene balanceada mediante divisiones de nodos.
- Las operaciones de búsqueda, inserción y eliminación tienen complejidad `O(log n)`.

### Aplicaciones

- Índices de bases de datos.
- Sistemas de archivos.
- Motores de almacenamiento.
- Estructuras donde los datos no caben completamente en memoria principal.

OpenDSA explica que los árboles B son importantes porque sus nodos pueden coincidir con bloques de disco, lo que reduce la cantidad de accesos necesarios para encontrar información.

**Código relacionado:** [`src/ArbolB.java`](src/ArbolB.java)

## Comparación general

| Tipo de árbol | Regla principal | Mejor uso | Riesgo o costo |
|---|---|---|---|
| Árbol general | Cada nodo puede tener varios hijos | Jerarquías flexibles | Búsqueda puede requerir recorrer muchos nodos |
| Árbol binario | Máximo dos hijos por nodo | Base para estructuras más específicas | No necesariamente está ordenado |
| Árbol binario de búsqueda | Izquierda menor, derecha mayor | Diccionarios ordenados simples | Puede desbalancearse |
| AVL | Árbol binario de búsqueda con diferencia de alturas máxima de 1 | Muchas búsquedas | Más rotaciones al modificar |
| Rojo-negro | Árbol binario de búsqueda balanceado por colores | Mapas y conjuntos ordenados | Menos estricto que AVL |
| Montículo binario | Prioridad en la raíz | Colas de prioridad | No sirve para búsqueda ordenada general |
| Árbol de prefijos | Rutas por caracteres | Prefijos y autocompletado | Puede consumir mucha memoria |
| Árbol B | Nodos con muchas claves | Bases de datos y disco | Implementación más compleja |

## Complejidades aproximadas

| Estructura | Búsqueda | Inserción | Eliminación | Observación |
|---|---|---|---|---|
| Árbol general | O(n) | Depende del caso | Depende del caso | No tiene orden obligatorio |
| Árbol binario simple | O(n) | O(n) | O(n) | Depende del recorrido |
| Árbol binario de búsqueda balanceado | O(log n) | O(log n) | O(log n) | Si mantiene baja altura |
| Árbol binario de búsqueda desbalanceado | O(n) | O(n) | O(n) | Puede degenerar en lista |
| AVL | O(log n) | O(log n) | O(log n) | Balance estricto |
| Rojo-negro | O(log n) | O(log n) | O(log n) | Balance práctico |
| Montículo binario | O(n) | O(log n) | O(log n) para extraer raíz | Excelente para mínimo/máximo |
| Árbol de prefijos | O(m) | O(m) | O(m) | `m` es longitud de la palabra |
| Árbol B | O(log n) | O(log n) | O(log n) | Optimizado para bloques |

## Importancia de los árboles en programación

Los árboles son importantes porque permiten modelar problemas que no son lineales. Muchas relaciones reales son jerárquicas: una universidad tiene facultades, las facultades tienen programas, los programas tienen asignaturas; un sistema de archivos tiene carpetas y archivos; una expresión matemática tiene operadores y operandos.

También son importantes por eficiencia. En un conjunto ordenado, una estructura lineal puede requerir recorrer todos los elementos. Un árbol balanceado reduce el número de comparaciones. En un sistema de base de datos, un índice basado en árbol B puede evitar revisar millones de registros. En un buscador de palabras, un árbol de prefijos puede encontrar todos los términos que comparten un prefijo sin revisar todo el diccionario.

En resumen, los árboles son una herramienta de organización y de optimización. Permiten que los programas representen relaciones complejas y ejecuten operaciones de búsqueda, inserción, eliminación o priorización con buen rendimiento.

## Instrucciones de uso para ejecutar y compilar los archivos

Los códigos Java se encuentran en la carpeta [`src`](src). A continuación se explican dos formas de clonar, abrir y ejecutar el proyecto: usando **Visual Studio Code** o **NetBeans 8.2**.

### Opción 1: Visual Studio Code

1. Abrir Visual Studio Code.
2. Seleccionar **Source Control** en la barra lateral izquierda.
3. Presionar la opción **Clone Repository**.
4. Pegar la URL del repositorio donde se encuentra el trabajo.
5. Elegir una carpeta del computador donde se guardará el proyecto.
6. Cuando Visual Studio Code pregunte si se desea abrir el repositorio clonado, seleccionar **Open**.
7. Verificar que dentro del proyecto aparezca la carpeta [`src`](src).
8. Abrir el archivo Java que se quiera ejecutar, por ejemplo:
   - [`src/ArbolGeneral.java`](src/ArbolGeneral.java)
   - [`src/ArbolBinario.java`](src/ArbolBinario.java)
   - [`src/ArbolBinarioBusqueda.java`](src/ArbolBinarioBusqueda.java)
   - [`src/ArbolAVL.java`](src/ArbolAVL.java)
   - [`src/ArbolRojoNegro.java`](src/ArbolRojoNegro.java)
   - [`src/MonticuloBinario.java`](src/MonticuloBinario.java)
   - [`src/ArbolPrefijos.java`](src/ArbolPrefijos.java)
   - [`src/ArbolB.java`](src/ArbolB.java)
9. Presionar el botón **Run** que aparece sobre el método `main`, o usar la opción **Run Java** si está instalada la extensión de Java.

Si se desea ejecutar desde la terminal integrada de Visual Studio Code, se pueden usar estos comandos:

```powershell
javac src\*.java
java -cp src ArbolGeneral
```

Para ejecutar otro ejemplo, se cambia `ArbolGeneral` por el nombre de la clase deseada.

### Opción 2: NetBeans 8.2

1. Abrir NetBeans 8.2.
2. Seleccionar **Team > Git > Clone**.
3. En **Repository URL**, pegar la URL del repositorio donde se encuentra el trabajo.
4. Completar los datos solicitados si el repositorio requiere usuario o contraseña.
5. En **Destination**, elegir la carpeta donde se guardará el proyecto clonado.
6. Presionar **Next** y seleccionar la rama principal del repositorio.
7. Presionar **Finish** para clonar el proyecto.
8. Si NetBeans no reconoce automáticamente el proyecto como una aplicación Java, crear uno nuevo desde **File > New Project > Java > Java Application**.
9. Desmarcar la opción **Create Main Class**, porque los archivos ya tienen sus propias clases con método `main`.
10. Copiar los archivos `.java` de la carpeta [`src`](src) dentro de **Source Packages** del proyecto creado.
11. Abrir la clase que se quiera ejecutar.
12. Hacer clic derecho dentro del archivo y seleccionar **Run File**.

En NetBeans 8.2 se puede ejecutar una clase específica haciendo clic derecho sobre el archivo, por ejemplo `ArbolB.java`, y seleccionando **Run File**. Cada archivo es independiente y contiene su propio método `main`.