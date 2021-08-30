package it.unicam.cs.asdl2021.totalproject2;

import java.util.List;

/**
 * Implementazione dell'algoritmo di Bellman-Ford per il calcolo di cammini
 * minimi a sorgente singola in un grafo pesato che può contenere anche pesi
 * negativi, ma non cicli di peso negativo.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class BellmanFordShortestPathComputer<L>
        implements SingleSourceShortestPathComputer<L> {

    // TODO inserire le variabili istanza necessarie

    /**
     * Crea un calcolatore di cammini minimi a sorgente singola per un grafo
     * orientato e pesato.
     *
     * @param graph il grafo su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException     se il grafo passato è nullo
     * @throws IllegalArgumentException se il grafo passato è vuoto
     * @throws IllegalArgumentException se il grafo passato non è diretto
     * @throws IllegalArgumentException se il grafo passato non è pesato,
     *                                  cioè esiste almeno un arco il cui
     *                                  peso è {@code Double.NaN}.
     */
    public BellmanFordShortestPathComputer(Graph<L> graph) {
        // TODO implementare
    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {
        // TODO implementare

    }

    @Override
    public boolean isComputed() {
        // TODO implementare
        return false;
    }

    @Override
    public GraphNode<L> getLastSource() {
        // TODO implementare
        return null;
    }

    @Override
    public Graph<L> getGraph() {
        // TODO implementare
        return null;
    }

    @Override
    public List<GraphEdge<L>> getShortestPathTo(GraphNode<L> targetNode) {
        // TODO implementare
        return null;
    }

    // TODO inserire eventuali altri metodi privati per scopi di implementazione

}
