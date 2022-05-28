package dominio;

import java.util.*;

    /**
     * Clase que representa un grafo dirigido.
     * @param <V> Tipo de los vertices del grafo.
     * @author Daniel Fernández López
     */
    public class Grafo<V> {


        //Lista de adyacencia.
        private Map<V, Set<V>> adjacencyList = new HashMap<>();

        /******************************************************************
         * Añade el vértice `v` al grafo.
         * Complejidad: O(1)
         *
         * @param v vértice a añadir.
         * @return ` true` si no estaba anteriormente y ` false` en caso contrario.
         * ******************************************************************/
        public boolean addVertex(V v) {
            if (adjacencyList.containsKey(v)) {
                System.out.println("El vértice ya existe");
                return false;
            } else {
                adjacencyList.put(v, new HashSet());
                System.out.println("El vértice se ha añadido correctamente");
                return true;
            }
        }

        /******************************************************************
         * Añade un arco entre los vértices ` v1` y ` v2` al grafo.
         * En caso de que no exista alguno de los vértices, lo añade también.
         * Complejidad: O(n)
         *
         * @param v1 el origen del arco.
         * @param v2 el destino del arco.
         * @return ` true` si no existía el arco y ` false` en caso contrario.
         * ******************************************************************/
        public boolean addEdge(V v1, V v2) {
            //Camino de V1 a V2
            Set<V> aristas = adjacencyList.get(v1);
            if (!containsVertex(v1) || aristas.contains(v2)) {
                System.out.println("La arista que une los vértices " + v1 + " y " + v2 + " ya existe");
                return false;
            } else {
                aristas.add(v2);
                aristas = adjacencyList.get(v2);
                System.out.println("La arista se ha añadido correctamente");
            }
            if (!containsVertex(v2) || aristas.contains(v1)) {
                System.out.println("La arista que une los vértices " + v1 + " y " + v2 + " ya existe");
                return false;
            } else {
                aristas.add(v1);
                aristas = adjacencyList.get(v1);
                System.out.println("La arista se ha añadido correctamente");
            }
            return true;
        }

        /******************************************************************
         * Obtiene el conjunto de vértices adyacentes a ` v`.
         * Complejidad: O(1)
         *
         * @param v vértice del que se obtienen los adyacentes.
         * @return conjunto de vértices adyacentes.
         ******************************************************************/
        public Set<V> obtainAdjacents(V v) throws Exception {
            //Comprueba si el vertice está en la lista de adyacencia.
            if (adjacencyList.get(v) != null) {
                throw new Exception("El vértice no existe");
            } else { //Devuelve el conjunto de vértices adyacentes.
                return adjacencyList.get(v);
            }
        }

        /******************************************************************
         * Método ` toString()` reescrito para la clase ` Grafo. java`.
         * Muestra el grafo en forma de lista de adyacencia.
         * Complejidad: O(n)
         *
         * @return una cadena de caracteres con la lista de adyacencia.
         ******************************************************************/
        @Override
        public String toString() {
            String lista = "";
            for (V v : adjacencyList.keySet()) {
                lista += (v.toString() + " está conectado con: " + adjacencyList.get(v).toString() + "\n");
            }
            return lista;
        }

        /******************************************************************
         * Comprueba si el grafo contiene el vértice dado.
         * Complejidad: O(1)
         *
         * @param v vértice para el que se realiza la comprobació n.
         * @return ` true` si ` v` es un vértice del grafo.
         ******************************************************************/
        public boolean containsVertex(V v) {
            if (adjacencyList.keySet().contains(v)) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Obtiene, en caso de que exista, el camino má s corto entre
         * ` v1` y ` v2`. En caso contrario, devuelve ` null`.
         * Complejidad: O(n)
         *
         * @param v1 el vértice origen.
         * @param v2 el vértice destino.
         * @return lista con la secuencia de vértices del camino má s corto entre ` v1` y ` v2`
         */
        public List<V> shortestPath(V v1, V v2) {
            //Inicializamos las estructuras de datos donde se almacenarán los vértices contenidos en el camino más corto:
            LinkedList<V> cola = new LinkedList<V>();
            ArrayList<V> camino = new ArrayList<>();

            //Comprobamos si los vertices existen:
            if (!containsVertex(v1) || !containsVertex(v2)) {
                return null;
            } else {
                cola.add(v1);
                //Inicializamos las estructuras de datos para calcular el camino del grafo:
                HashMap<V, V> verticeInicio = new HashMap<>();
                HashMap<V, Integer> distancia = new HashMap<>();
                HashMap<V, Boolean> verticesVisitados = new HashMap<>();

                //Recorremos la lista de adyacencia definiendo los valores que tomarán las estructuras de datos definidas:
                for (V v : adjacencyList.keySet()) {
                    distancia.put(v, Integer.MAX_VALUE);
                    verticeInicio.put(v, null);
                    verticesVisitados.put(v, false);
                }
                //Inicializacmos el primer vertice con distancia 0:
                distancia.put(v1, 0);
                //Añadimos el vertice inicial al Map de vertices visitados:
                verticesVisitados.put(v1, true);

                //Búsqueda del camino más corto:
                while (!cola.isEmpty()) {
                    V actual = cola.poll();
                    //Comprobamos si el vertice actual es el destino:
                    for (V v : adjacencyList.get(actual)) {
                        if (distancia.get(v) > distancia.get(actual) + 1) {
                            distancia.put(v, distancia.get(actual) + 1);
                            verticeInicio.put(v, actual);
                        }
                        if (!verticesVisitados.get(v)) {
                            verticesVisitados.put(v, true);
                            cola.add(v);
                        }
                    }
                }
                //Inicializamos un vértice auxiliar para encontrar nuestro vértic destino a partir de sus aristas:
                V aux = v2;
                while (aux != null) {
                    camino.add(aux);
                    aux = verticeInicio.get(aux);
                }
                //Devolvemos el camino más corto:
                Collections.reverse(camino);
                return camino;
            }
        }
    }


