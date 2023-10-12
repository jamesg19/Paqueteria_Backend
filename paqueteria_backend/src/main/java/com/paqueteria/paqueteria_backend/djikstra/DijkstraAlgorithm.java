package com.paqueteria.paqueteria_backend.djikstra;

public class DijkstraAlgorithm {
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}
