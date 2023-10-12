package org.example;

import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        /**
         * S=0
         * B=1
         * C=2
         * D=3
         * E=4
         * T=5
         */
        //S,B,C,D,E,T
        int[][] graph = {
                {0, 4, 0, 0, 0, 0},
                {4, 0, 0, 5,0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 5, 0, 0, 2, 6},
                {0, 0, 0, 2, 0, 2},
                {0, 0, 0, 6, 2, 0}
        };

        int source = 0; // Nodo S
        int target = 3; // Nodo T
        dijkstra(graph, source, target);
    }
    public static void dijkstra(int[][] graph, int source, int target) {
        int V = graph.length;
        //System.out.println("V: "+V);
        int[] distance = new int[V];
        int[] parent = new int[V];  // Array para almacenar los nodos predecesores

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[source] = 0;

        PriorityQueue<DijkstraAlgorithm.Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new DijkstraAlgorithm.Edge(source, 0));

        while (!pq.isEmpty()) {
            DijkstraAlgorithm.Edge current = pq.poll();
            int u = current.destination;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    parent[v] = u;  // Almacena el nodo predecesor
                    pq.offer(new DijkstraAlgorithm.Edge(v, distance[v]));
                }
            }
        }

        // La distancia más corta desde S hasta T es almacenada en distance[T]
        System.out.println("****La ruta más corta desde S hasta T es: " + distance[target]);

        printPath(parent, target);
    }
    // Método para imprimir el camino desde el nodo de origen hasta el nodo de destino
    public static void printPath(int[] parent, int target) {
        if (target == -1) {
            return;
        }
        printPath(parent, parent[target]);
        System.out.print((char)('A' + target) + " ");
        System.out.print(target+"->");

    }
}