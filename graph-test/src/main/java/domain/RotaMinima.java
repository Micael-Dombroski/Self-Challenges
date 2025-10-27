package domain;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import java.util.List;

public class RotaMinima {
    public static List<String> acharRotaMinima(List<Aresta> arestas, String origem, String destino) {
        Graph<String, DefaultWeightedEdge> grafo = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        arestas.forEach(a -> {
            if(!grafo.containsVertex(a.getOrigem())) {
                grafo.addVertex(a.getOrigem());
            }
            if(!grafo.containsVertex(a.getDestino())) {
                grafo.addVertex(a.getDestino());
            }
            grafo.setEdgeWeight(grafo.addEdge(a.getOrigem(), a.getDestino()), a.getPeso());
        });

        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);
        List<String> caminhoMinimo = dijkstra.getPath(origem, destino).getVertexList();
        return caminhoMinimo;
    }
}