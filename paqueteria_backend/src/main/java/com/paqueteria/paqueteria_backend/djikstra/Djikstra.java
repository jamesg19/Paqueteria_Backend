package com.paqueteria.paqueteria_backend.djikstra;

import com.paqueteria.paqueteria_backend.entidad.Ruta;
import com.paqueteria.paqueteria_backend.entidad.Sucursal;
import com.paqueteria.paqueteria_backend.repositorio.SucursalRepositorio;
import com.paqueteria.paqueteria_backend.servicio.SucursalServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
@Getter
@Setter
public class Djikstra {

    private boolean huboCambio;
    private int[][] graph;
    private List<Sucursal> rutaSucursales;
    private List<Sucursal> sucursales;
    private double distanciaRecorrida=0;
    

    public Djikstra(){
        System.out.print("Inicializando el algoritmo de djikstra");
        this.huboCambio = false;        
    }

    public boolean getHuboCambio(){
        return this.huboCambio;
    }

    public void setHuboCambio(boolean hayCambio){
        this.huboCambio = hayCambio;
    }   
    
    public List<Sucursal> buscarMejorRuta(List<Sucursal> sucursales,List<Ruta> rutas, int origen, int destino){
        //Aqui al ya tener todo se puede guardar el grafico y utilizar  if (huboCambio)
        this.sucursales = sucursales;
        crearGrafico(this.sucursales, rutas);


        int valOrigenGrafico = -1;
        int valDestinoGrafico = -1;
        int val = 0;
        for (Sucursal sucursal : this.sucursales) {
            if (sucursal.getIdSucursal() == origen) {
                valOrigenGrafico = val;
            }
            if (sucursal.getIdSucursal() == destino) {
                valDestinoGrafico = val;
            }
            if(valOrigenGrafico != -1 && valDestinoGrafico != -1){
                break;
            }
            val += 1;
        }
        int[] parent = dijkstra(this.graph,valOrigenGrafico,valDestinoGrafico);
        this.rutaSucursales = new ArrayList<>(); 
        //printPath(parent,valDestinoGrafico);
        validarSucursales(parent,valDestinoGrafico);

        return this.rutaSucursales;
    }
        

    public void crearGrafico(List<Sucursal> sucursales,List<Ruta> rutas){        
        int cantidadSucursales = sucursales.size();

        this.graph = new int[cantidadSucursales][cantidadSucursales];

        int val1 = 0;
        for (Sucursal sucursal : sucursales) {
            //System.out.println("Id Sucursal: " + sucursal.getIdSucursal());            
            int val2 = 0;
            for (Sucursal sucursal2 : sucursales) {
                int peso = 0;
                for (Ruta ruta : rutas) {
                    if (ruta.getOrigen().getIdSucursal() == sucursal.getIdSucursal() && ruta.getDestino().getIdSucursal() == sucursal2.getIdSucursal()) {
                        peso = (int)Math.round(ruta.getDistancia());
                        break;
                    }
                }
                this.graph[val1][val2] = peso;
                val2 +=1;
            }            
            val1 +=1;
        }                
    }

    private void validarSucursales(int[] parent, int target){
        if (target == -1) {
            return;
        }
        validarSucursales(parent, parent[target]);        
        this.rutaSucursales.add(this.sucursales.get(target));
    }




    public int[] dijkstra(int[][] graph, int source, int target) {
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
        //printPath(parent, target);
        // La distancia más corta desde S hasta T es almacenada en distance[T]
        distanciaRecorrida=distance[target];
        System.out.println("La ruta más corta desde S hasta T es: " + distance[target]);
        return parent;
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
