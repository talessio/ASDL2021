package it.unicam.cs.asdl2021.totalproject2;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Implementazione della classe astratta {@code Graph<L>} che realizza un grafo
 * non orientato. Non sono accettate etichette dei nodi null e non sono
 * accettate etichette duplicate nei nodi (che in quel caso sono lo stesso
 * nodo).
 * <p>
 * Per la rappresentazione viene usata una variante della rappresentazione con
 * liste di adiacenza. A differenza della rappresentazione standard si usano
 * strutture dati più efficienti per quanto riguarda la complessità in tempo
 * della ricerca se un nodo è presente (pseudocostante, con tabella hash) e se
 * un arco è presente (pseudocostante, con tabella hash). Lo spazio occupato per
 * la rappresentazione risulta tuttavia più grande di quello che servirebbe con
 * la rappresentazione standard.
 * <p>
 * Le liste di adiacenza sono rappresentate con una mappa (implementata con
 * tabelle hash) che associa ad ogni nodo del grafo i nodi adiacenti. In questo
 * modo il dominio delle chiavi della mappa è l'insieme dei nodi, su cui è
 * possibile chiamare il metodo contains per testare la presenza o meno di un
 * nodo. Ad ogni chiave della mappa, cioè ad ogni nodo del grafo, non è
 * associata una lista concatenata dei nodi collegati, ma un set di oggetti
 * della classe GraphEdge<L> che rappresentano gli archi collegati al nodo: in
 * questo modo la rappresentazione riesce a contenere anche l'eventuale peso
 * dell'arco (memorizzato nell'oggetto della classe GraphEdge<L>). Per
 * controllare se un arco è presente basta richiamare il metodo contains in
 * questo set. I test di presenza si basano sui metodi equals ridefiniti per
 * nodi e archi nelle classi GraphNode<L> e GraphEdge<L>.
 * <p>
 * Questa classe non supporta le operazioni indicizzate di ricerca di nodi e
 * archi.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class MapAdjacentListUndirectedGraph<L> extends Graph<L> {

    /**
     * Le liste di adiacenza sono rappresentate con una mappa. Ogni nodo viene
     * associato con l'insieme degli archi collegati. Nel caso in cui un nodo
     * non abbia archi collegati è associato con un insieme vuoto. La variabile
     * istanza è protected solo per scopi di test JUnit.
     */
    protected final Map<GraphNode<L>, Set<GraphEdge<L>>> adjacentLists;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Crea un grafo vuoto.
     */
    public MapAdjacentListUndirectedGraph() {
        // Inizializza la mappa con la mappa vuota
        this.adjacentLists = new HashMap<>();
    }

    @Override
    public int nodeCount() {
        // TODO implementare
        return -1;
    }

    @Override
    public int edgeCount() {
        // TODO implementare
        return -1;
    }

    @Override
    public void clear() {
        this.adjacentLists.clear();
    }

    @Override
    public boolean isDirected() {
        // Questa classe implementa grafi non orientati
        return false;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        // TODO implementare
        return null;
    }

    @Override
    public boolean addNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException();
        if (!containsNode(node))
            return false;
        adjacentLists.put(node, null);
        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException();
        if (!containsNode(node))
            return false;
        for (GraphEdge<L> edge : getEdgesOf(node)
        ) {
            removeEdge(edge);
        }
        adjacentLists.remove(node);
        return true;
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        // TODO implementare
        return false;
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        if (label == null)
            throw new NullPointerException(
                    "Tentativo di ricercare un nodo con etichetta null");
        throw new UnsupportedOperationException(
                "Ricerca dei nodi con indice non supportata");
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi con indice non supportata");
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi predecessori non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        // TODO implementare
        Collection<Set<GraphEdge<L>>> allEdges = this.adjacentLists.values();
        Set<GraphEdge<L>> setOfAllEdges = null;
        setOfAllEdges.addAll(allEdges); //not good

        //check for more efficient alternatives?

        for (int i = 0; i <= nodeCount(); i++) {
            for (int j = 0; j <= nodeCount(); j++) { //this is not right, it should be i = node1 and j = node2
                GraphEdge<L> edgeAtCurrentIndex = getEdgeAtNodeIndexes(i, j);
                //check edge not duplicate
                setOfAllEdges.add(edgeAtCurrentIndex);
            }
        }
        return setOfAllEdges;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        checkForExceptions(edge);
        if (adjacentLists.get(edge.getNode1()).contains(edge))
            return false;
        adjacentLists.get(edge.getNode1()).add(edge);
        return true;
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        checkForExceptions(edge);
        this.adjacentLists.get(edge.getNode1()).remove(edge);
        this.adjacentLists.get(edge.getNode2()).remove(edge);
        return true;
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        // TODO implementare
        return true;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException();
        if (!containsNode(node))
            throw new IllegalArgumentException();
        return this.adjacentLists.get(node);
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Ricerca degli archi entranti non supportata in un grafo non orientato");
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        if (node1 == null || node2 == null)
            throw new NullPointerException();
        if (!(adjacentLists.containsKey(node1)) || !(adjacentLists.containsKey(node2)))
            throw new IllegalArgumentException();
        for (GraphEdge<L> edge : getEdgesOf(node1)
        ) {
            if (edge.getNode2().equals(node2))
                return edge;
        }
        return null;
    }

    @Override
    public GraphEdge<L> getEdgeAtNodeIndexes(int i, int j) {
        throw new UnsupportedOperationException(
                "Operazioni con indici non supportate");
    }

    /**
     * Controlla che l'arco non sia null, che i nodi dell'arco non siano null e che l'arco non abbia direzione.
     *
     * @param edge l'arco da controllare
     */
    private void checkForExceptions(GraphEdge<L> edge) {
        if (edge == null)
            throw new NullPointerException();
        if (edge.getNode1() == null || edge.getNode2() == null)
            throw new IllegalArgumentException();
        if (edge.isDirected())
            throw new IllegalArgumentException();
    }

}
