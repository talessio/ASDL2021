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

    Graph<L> graph;
    BinaryHeapMinPriorityQueue<GraphNode<L>> heap;
    private GraphNode<L> lastSource;

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
        if (graph == null)
            throw new NullPointerException();
        if (graph.isEmpty() || !graph.isDirected())
            throw new IllegalArgumentException();
        for (GraphEdge<L> currentEdge : graph.getEdges()) {
            if (Double.isNaN(currentEdge.getWeight()))
                throw new IllegalArgumentException();
        }
        this.graph = graph;
        this.lastSource = null;
        this.heap = new BinaryHeapMinPriorityQueue<>();
    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {
        if (sourceNode == null)
            throw new NullPointerException();
        if (!this.graph.containsNode(sourceNode))
            throw new IllegalArgumentException();

        this.lastSource = sourceNode;
        for (GraphNode<L> node : this.graph.getNodes()) {
            node.setPriority(Double.POSITIVE_INFINITY);
            node.setColor(GraphNode.COLOR_WHITE);
            this.heap.insert(node);
        }
        if (sourceNode.getPriority() > 0)
            this.heap.decreasePriority(sourceNode, 0);
        sourceNode.setPrevious(null);
        sourceNode.setColor(GraphNode.COLOR_GREY);

        GraphNode<L> currentNode = sourceNode;

        double potentialNewPriority;
        boolean hasImproved = false;
        for (int i = 0; i < this.graph.nodeCount() - 1; i++) { //graph iteration
//            for (GraphNode<L> currentNode : this.graph.getNodes()) {
            for (int j = 0; j < this.graph.nodeCount(); j++) { //visita del nodo
                  //TODO fix deve partire da sorgente
                for (GraphNode<L> adjacentNode : this.graph.getAdjacentNodesOf(currentNode)) {
                    //adjacent nodes priority update
                    potentialNewPriority = currentNode.getPriority() +
                            this.graph.getEdge(currentNode, adjacentNode).getWeight();
                    if (potentialNewPriority < adjacentNode.getPriority()) {
                        this.heap.decreasePriority(adjacentNode, potentialNewPriority);
                        adjacentNode.setPrevious(currentNode);
                        hasImproved = true;
                    }
                }
                currentNode.setColor(GraphNode.COLOR_GREY);
                for (GraphNode<L> nextNode : this.graph.getNodes()) {
                    if (nextNode.getColor() == GraphNode.COLOR_WHITE) {
                        currentNode = nextNode; //TODO mette a posto
                    }
                }
            }
            for (GraphNode<L> n : this.graph.getNodes()) {
                n.setColor(GraphNode.COLOR_WHITE);
            }
            if (hasImproved)
                hasImproved = false;
        }

        //check for negative infinite loop
        for (int i = 0; i < this.graph.nodeCount() - 1; i++) { //graph iteration
            for (GraphNode<L> nodeToCheck : this.graph.getNodes()) { //node relaxation
                for (GraphNode<L> adjacentNode : this.graph.getAdjacentNodesOf(nodeToCheck)) {
                    potentialNewPriority = nodeToCheck.getPriority() +
                            this.graph.getEdge(nodeToCheck, adjacentNode).getWeight();
                    if (potentialNewPriority < adjacentNode.getPriority()) {
                        //scopro ciclo infinito
                        //(priorità dei nodi continua a diminuire)
                        throw new IllegalStateException();
                    }
                }
            }
        }


    }

    @Override
    public boolean isComputed() {
        return this.lastSource != null;
    }

    @Override
    public GraphNode<L> getLastSource() {
        if (lastSource == null)
            throw new IllegalStateException();
        return lastSource;
    }

    @Override
    public Graph<L> getGraph() {
        return this.graph;
    }

    @Override
    public List<GraphEdge<L>> getShortestPathTo(GraphNode<L> targetNode) {
        if (targetNode == null)
            throw new NullPointerException();
        if (!this.graph.containsNode(targetNode))
            throw new IllegalArgumentException();
        if (!this.isComputed())
            throw new IllegalStateException();

        // TODO implementare
        return null;
    }

    // TODO inserire eventuali altri metodi privati per scopi di implementazione

}
