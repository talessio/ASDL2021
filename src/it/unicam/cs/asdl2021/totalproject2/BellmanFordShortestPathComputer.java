package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
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

    //TODO fix Nullpo

    private Graph<L> graph;
    private BinaryHeapMinPriorityQueue<GraphNode<L>> heap;
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

        bellmanFord(sourceNode);
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

        this.computeShortestPathsFrom(lastSource);

        List<GraphEdge<L>> minWalk = new ArrayList<>();
        if (targetNode.equals(this.lastSource))
            return minWalk;

        GraphNode<L> n = targetNode;

        while (n.getPrevious() != null) {
            minWalk.add(0, this.graph.getEdge(n.getPrevious(), n));
            if (n.getPrevious().equals(this.lastSource))
                return minWalk;
            n = n.getPrevious();
        }
        return null;
    }

    // TODO inserire eventuali altri metodi privati per scopi di implementazione


    private void bellmanFord(GraphNode<L> sourceNode) {
        GraphNode<L> currentNode;
        GraphNode<L> oldNode = null;
        boolean hasImproved = false;
        int i;
        for (i = 0; i < this.graph.nodeCount(); i++) { //iterazione completa grafo + 1
            currentNode = sourceNode;
            for (int j = 0; j < this.graph.nodeCount(); j++) { //visita di ogni nodo
                for (GraphNode<L> newNode : this.graph.getNodes()) { //scelta del prossimo nodo da visitare
                    if (newNode.getColor() == GraphNode.COLOR_GREY) {
                        currentNode = newNode;
                        break;
                    }
                }
                if (currentNode.equals(oldNode))
                    break;
                if (!hasImproved)
                    hasImproved = checkNewPriority(currentNode); //migliora la priorità dei nodi adiacenti
                else checkNewPriority(currentNode);
                currentNode.setColor(GraphNode.COLOR_BLACK); //il nodo è ora visitato
                oldNode = currentNode;
            }
            if (hasImproved) {
                if (i == (this.graph.nodeCount() - 1)) //se i ha fatto un ciclo in eccesso e priority è comunque migliorata
                    throw new IllegalStateException();
                else //se i non è ancora all'ultimo ciclo e ha migliorato la priority
                    hasImproved = false;
            } else //se i non è ancora all'ultimo ciclo ma non migliora la priority
                break;
            for (GraphNode<L> n : this.graph.getNodes()) {
                n.setColor(GraphNode.COLOR_WHITE);
            }
        }
    }

    private boolean checkNewPriority(GraphNode<L> node) {
        double potentialNewPriority;
        boolean flag = false;
        for (GraphNode<L> adjacentNode : this.graph.getAdjacentNodesOf(node)) {
            //adjacent nodes priority update
            if (adjacentNode.getColor() != GraphNode.COLOR_BLACK) {
                adjacentNode.setColor(GraphNode.COLOR_GREY);
            }
            potentialNewPriority = node.getPriority() + this.graph.getEdge(node, adjacentNode).getWeight();
            if (potentialNewPriority < adjacentNode.getPriority()) {
                this.heap.decreasePriority(adjacentNode, potentialNewPriority);
                adjacentNode.setPrevious(node);
                flag = true;
            }
        }
        return flag;
    }
}
