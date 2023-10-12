package com.paqueteria.paqueteria_backend.djikstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.paqueteria.paqueteria_backend.entidad.Sucursales;

public class Djikstra {

    public Djikstra(){
        System.out.print("Inicializando el algoritmo de djikstra");
        this.probarCositas();
    }

    public int[][] crearGrafico(List<Sucursales> sucursales){
        /*Como aun no tengo la base de datos ni los datos me los voy a fumar */
        

        int cantidadSucursales = sucursales.size();
        
        int[][] graph = new int[cantidadSucursales][cantidadSucursales]; 

        int val1 = 0; // Me dio hueva cambiar todo y se quedo asi :v total lo hice desde que me dijeron que lo hiciera :v
        for (Sucursales sucursal : sucursales) {
            System.out.println("Id Sucursal: " + sucursal.getId());
            //List<Ruta> rutasSucursal1 = getRutasPorIdOrigen();  Asi deberia de quedar pero aun no esta la clase ni la tabla tons???
            int val2 = 0;
            /*
            for (Sucursales sucursal2 : sucursales) {                
                for (Ruta ruta : rutasSucursal1) {
                    if (ruta.getDestino() == sucursal2.getId()) {
                        graph[val1][val2] = ruta.getPeso();
                        break;
                    }
                }
                val2 +=1;
            }
            */
            val1 +=1;
        }

        return graph;
        //Deberia de chonar cuando este todo listo :v y sino pos se arregla 
    }
    
    
    public String probarCositas(){

        int[][] graph = {
            {0, 4, 2, 0, 0, 0},
            {4, 0, 1, 5,0, 0},
            {2, 1, 0, 8, 10, 0},
            {0, 5, 8, 0, 2, 6},
            {0, 0, 10, 2, 0, 2},
            {0, 0, 0, 6, 2, 0}
        };

        int source = 0; // Nodo S
        int target = 4; // Nodo T
        return dijkstra(graph, source, target);

    }

    public String dijkstra(int[][] graph, int source, int target) {
        int V = graph.length;
        System.out.println("V: "+V);
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
        printPath(parent, target);
        // La distancia más corta desde S hasta T es almacenada en distance[T]
        System.out.println("La ruta más corta desde S hasta T es: " + distance[target]);
        return "La ruta más corta desde S hasta T es: " + distance[target];
    }
    // Método para imprimir el camino desde el nodo de origen hasta el nodo de destino
    public void printPath(int[] parent, int target) {
        if (target == -1) {
            return;
        }
        printPath(parent, parent[target]);
        //System.out.print((int)('0' + target) + " ");        
        System.out.print(target+"->");
    }
}
