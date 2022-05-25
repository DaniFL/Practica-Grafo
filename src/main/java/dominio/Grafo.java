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
         *
         * @ param v vértice a añadir.
         * @ return ` true` si no estaba anteriormente y ` false` en caso contrario.
         * ******************************************************************/
//Complejidad O(n^2):
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
         *
         * @ param v1 el origen del arco.
         * @ param v2 el destino del arco.
         * @ return ` true` si no existía el arco y ` false` en caso contrario.
         * ******************************************************************/
        //Complejidad O(n^2): --> Comprobar que no se repita el arco.
        public boolean addEdge(V v1, V v2) {
            //Camino de V1 a V2
            Set<V> aristas = adjacencyList.get(v1);
            if (!containsVertex(v1) || aristas.contains(v2)) {
                System.out.println("La arista que une los vértices" + v1 + " y " + v2 + " ya existe");
                return false;
            } else {
                aristas.add(v2);
                aristas = adjacencyList.get(v2);
                System.out.println("La arista se ha añadido correctamente");
            }
            if (!containsVertex(v2) || aristas.contains(v1)) {
                System.out.println("La arista que une los vértices" + v1 + " y " + v2 + " ya existe");
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
         *
         * @ param v vértice del que se obtienen los adyacentes.
         * @ return conjunto de vértices adyacentes.
         ******************************************************************/
        //Complejidad O(n^2):
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
         * @ return una cadena de caracteres con la lista de adyacencia.
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
         *
         * @ param v vértice para el que se realiza la comprobació n.
         * @ return ` true` si ` v` es un vértice del grafo.
         ******************************************************************/
        //Complejidad O(n^2):
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
         *
         * @ param v1 el vértice origen.
         * @ param v2 el vértice destino.
         * @ return lista con la secuencia de vértices del camino má s corto entre ` v1` y ` v2`
         */
        public List<V> shortestPath(V v1, V v2) {
            if (!containsVertex(v1) || !containsVertex(v2)) {
                return null;
            }
            //boolean encontrado = false;
            LinkedList<V> cola = new LinkedList<V>();
            ArrayList<V> camino = new ArrayList<>();
            Set<V> visitados = new HashSet<>();

            camino.add(v1);
            for (V v : adjacencyList.keySet()) {
                cola.add(v);
            }
            boolean encontrado = false;

            while (!cola.isEmpty() && !encontrado) {
                V actual = cola.pop();
                camino.add(actual);
                visitados.add(actual);

                if (actual.equals(v2)) {
                    encontrado = true;
                } else {
                    for (V v : adjacencyList.get(actual)) {
                        if (camino.contains(v)) {
                            continue;
                        } else {
                            cola.add(v);
                        }
                    }
                }
            }
            if (camino.get(camino.size() - 1) == v2) {
                ArrayList<V> caminofinal = new ArrayList<>();
                caminofinal.add(camino.get(camino.size() - 1));
                for (int i = 1; i < camino.size(); i++) {
                    caminofinal.add(camino.get(camino.size() - (i + 1)));
                    if (camino.get(i) == v1) {
                camino.remove(camino.size() - (i + 1));
                i--;
            }
        }
    }
                Collections.reverse(caminofinal);
                return caminofinal;

                System.out.println("El camino más corto entre " + v1 + " y " + v2 + " es: " + camino.toString());
                return camino;
            }
            return null;
        }
    }

