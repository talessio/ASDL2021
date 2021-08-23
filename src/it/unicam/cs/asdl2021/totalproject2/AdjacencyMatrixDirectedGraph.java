package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Classe che implementa un grafo orientato tramite matrice di adiacenza. Non
 * sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * <p>
 * I nodi sono indicizzati da 0 a nodeCoount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * <p>
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato in fase di inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 * <p>
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco orientato e contiene un oggetto della classe
 * GraphEdge<L> se lo sono. Tale oggetto rappresenta l'arco.
 * <p>
 * Questa classe non supporta la cancellazione di nodi, ma supporta la
 * cancellazione di archi e tutti i metodi che usano indici, utilizzando
 * l'indice assegnato a ogni nodo in fase di inserimento.
 *
 * @author Template: Luca Tesei
 */
public class AdjacencyMatrixDirectedGraph<L> extends Graph<L> {
    /*
     * Le seguenti variabili istanza sono protected al solo scopo di agevolare
     * il JUnit testing
     */

    // Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
    // matrice di adiacenza
    protected Map<GraphNode<L>, Integer> nodesIndex;

    // Matrice di adiacenza, gli elementi sono null o oggetti della classe
    // GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
    // dimensione gradualmente ad ogni inserimento di un nuovo nodo.
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixDirectedGraph() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public int nodeCount() {
        return this.nodesIndex.keySet().size();
    }

    @Override
    public int edgeCount() {
        int var = 0;
        for (int i = 0; i < nodeCount(); i++) {
            for (int j = 0; j < nodeCount(); j++) {
                GraphEdge<L> currentEdge = getEdgeAtNodeIndexes(i, j);
                if (currentEdge != null)
                    var++;
            }
        }
        return var;
    }

    @Override
    public void clear() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public boolean isDirected() {
        // Questa classe implementa un grafo orientato
        return true;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        return this.nodesIndex.keySet();
    }

    @Override
    public boolean addNode(GraphNode<L> node) {
        if (node != null) {
            throw new NullPointerException();
        }
        if (this.nodesIndex.containsKey(node)) {
            return false;
        }
        int value = nodeCount() + 1;
        this.nodesIndex.put(node, value);
        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Remove di nodi non supportata");
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException();
        if (this.nodesIndex.containsKey(node))
            return true;
        else
            return false;
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        if (label == null)
            throw new NullPointerException();
        for (GraphNode<L> node : this.nodesIndex.keySet()) {
            if (node.getLabel().equals(label))
                return node;
        }
        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        if (label == null)
            throw new NullPointerException();
        if (this.getNodeOf(label) == null) {
            throw new IllegalArgumentException();
        }
        int index = this.nodesIndex.get(this.getNodeOf(label));
        return index;
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        if (i > this.nodeCount() - 1)
            throw new IndexOutOfBoundsException();
        for (GraphNode<L> currentNode : this.nodesIndex.keySet()) {
            if (this.nodesIndex.get(currentNode) == i)
                return currentNode;
        }
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException();
        if (!this.containsNode(node))
            throw new IllegalArgumentException();
        Set<GraphNode<L>> itsAdjNodes = null;
        for (GraphEdge<L> currentEdge : this.getEdgesOf(node)) {
            itsAdjNodes.add(currentEdge.getNode2());
        }
        return itsAdjNodes;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException();
        if (!this.containsNode(node))
            throw new IllegalArgumentException();
        Set<GraphNode<L>> itsPredNodes = null;
        for (GraphNode<L> currentNode : this.nodesIndex.keySet()) {
            for (GraphEdge<L> currentEdge : getEdgesOf(currentNode)) {
                if (currentEdge.getNode2() == node && !itsPredNodes.contains(currentNode))
                    itsPredNodes.add(currentNode);
            }
        }
        return itsPredNodes;
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        // TODO implementare
        return null;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        // TODO implementare
        return false;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphEdge<L> getEdgeAtNodeIndexes(int i, int j) {
        // TODO implementare
        return null;
    }

    // TODO inserire eventuali metodi privati per fini di implementazione
}
