package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.List;


/**
 * Gli oggetti di questa classe sono calcolatori di cammini minimi con sorgente
 * singola su un certo grafo orientato e pesato dato. Il grafo su cui lavorare
 * deve essere passato quando l'oggetto calcolatore viene costruito e non può
 * contenere archi con pesi negativi. Il calcolatore implementa il classico
 * algoritmo di Dijkstra per i cammini minimi con sorgente singola utilizzando
 * una coda con priorità che estrae l'elemento con priorità minima e aggiorna le
 * priorità con l'operazione decreasePriority in tempo logaritmico (coda
 * realizzata con uno heap binario). In questo caso il tempo di esecuzione
 * dell'algoritmo di Dijkstra è {@code O(n log m)} dove {@code n} è il numero di
 * nodi del grafo e {@code m} è il numero di archi.
 *
 * @param <L> il tipo delle etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class DijkstraShortestPathComputer<L> implements SingleSourceShortestPathComputer<L> {

    // TODO inserire le variabili istanza necessarie
    Graph<L> graph;
    BinaryHeapMinPriorityQueue<GraphNode<L>> heap;
    private GraphNode<L> lastSource;

    /**
     * Crea un calcolatore di cammini minimi a sorgente singola per un grafo
     * diretto e pesato privo di pesi negativi.
     *
     * @param graph il grafo su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException     se il grafo passato è nullo
     * @throws IllegalArgumentException se il grafo passato è vuoto
     * @throws IllegalArgumentException se il grafo passato non è orientato
     * @throws IllegalArgumentException se il grafo passato non è pesato,
     *                                  cioè esiste almeno un arco il cui
     *                                  peso è {@code Double.NaN}
     * @throws IllegalArgumentException se il grafo passato contiene almeno
     *                                  un peso negativo
     */
    public DijkstraShortestPathComputer(Graph<L> graph) {
        //TODO implementare
        if (graph == null)
            throw new NullPointerException();
        if (graph.isEmpty() || !graph.isDirected())
            throw new IllegalArgumentException();
        for (GraphEdge<L> currentEdge : graph.getEdges()) {
            if (Double.isNaN(currentEdge.getWeight()) ||
                    currentEdge.getWeight() < 0)
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
        for (GraphEdge<L> currentEdge : graph.getEdges()) {
            if (Double.isNaN(currentEdge.getWeight()) ||
                    currentEdge.getWeight() < 0)
                throw new IllegalStateException();
        }
        this.lastSource = sourceNode;
        for (GraphNode<L> currentNode : this.graph.getNodes()) {
            currentNode.setColor(GraphNode.COLOR_WHITE);
            currentNode.setPriority(Double.MAX_VALUE);
            this.heap.insert(currentNode);
        }
        sourceNode.setPrevious(null);
        if (sourceNode.getPriority() > 0)
            this.heap.decreasePriority(sourceNode, 0);

        GraphNode<L> node;
        for (int i = 0; i < this.graph.nodeCount(); i++) {
            node = (GraphNode<L>) this.heap.extractMinimum();
            node.setColor(GraphNode.COLOR_BLACK);
            for (GraphNode<L> adjacentNode : this.graph.getAdjacentNodesOf(node)) {
                if (adjacentNode.getColor() == GraphNode.COLOR_BLACK)
                    continue;
                adjacentNode.setColor(GraphNode.COLOR_GREY);
                double newPriorityMaybe = node.getPriority() + this.graph.getEdge(node, adjacentNode).getWeight();
                if (newPriorityMaybe < adjacentNode.getPriority()) {
                    this.heap.decreasePriority(adjacentNode, newPriorityMaybe);
                    adjacentNode.setPrevious(node);
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
        if (targetNode.equals(this.lastSource))
            return null;

        List<GraphEdge<L>> minWalk = new ArrayList<>();
        //TODO restituisce cammino minimo del nodo in input

        GraphNode<L> nodeInTheMiddle = targetNode;
        for (int i = 0; i < this.graph.nodeCount(); i++) {
            minWalk.add(this.graph.getEdge(nodeInTheMiddle.getPrevious(), nodeInTheMiddle));
            nodeInTheMiddle = targetNode.getPrevious();
            if (nodeInTheMiddle.equals(this.lastSource)) {
                return minWalk;
            }
        }
        return minWalk;
    }

    // TODO inserire eventuali altri metodi accessori
}
