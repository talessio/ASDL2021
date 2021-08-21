package it.unicam.cs.asdl2021.totalproject2;

import java.util.*;

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
        return this.adjacentLists.keySet().size();
    }

    @Override
    public int edgeCount() {
        return getEdges().size();
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
        return this.adjacentLists.keySet();
    }

    @Override
    public boolean addNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException();
        if (containsNode(node))
            return false;
        this.adjacentLists.put(node, new HashSet<>()); //!!!!!!!!!!!!!!!!!!!!!!(ヌルポの原因)
        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException();
        if (!containsNode(node))
            return false;
//            GraphEdge<L> edge = iter.next();
//            if(edge.getNode1().equals(node) || edge.getNode2().equals(node))
//                removeEdge(edge);
//        }
//        for (GraphEdge<L> edge : getEdgesOf(node)) {    //errore qui, su più metodi
//            if (edge.getNode1().equals(node) || edge.getNode2().equals(node)) {
//                removeEdge(edge);
//            }
//        }
        for (GraphNode<L> currentAdjNode : getAdjacentNodesOf(node)) { //scorro i nodi adiacenti
            for (GraphEdge<L> currentAdjNodesEdge : getEdgesOf(currentAdjNode)) { //scorro i set di archi dei nodi adiacenti
                for (GraphEdge<L> currentEdge : getEdgesOf(node)) { //scorro gli archi di node
                    if (currentEdge.equals(currentAdjNodesEdge)) { //confronto gli archi di node con gli archi degli adiacenti
                        removeEdge(currentAdjNodesEdge); //rimuovo l'arco dai set di archi dei nodi adiacenti
                    }
                }
            }
        }
        for (GraphEdge<L> edgyEdgEdg : getEdgesOf(node)) {
            removeEdge(edgyEdgEdg);
        }
        this.adjacentLists.remove(node);
        return true;
    }

    @Override
    public boolean containsNode(GraphNode<L> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        return this.adjacentLists.containsKey(node);
    }

    @Override
    public GraphNode<L> getNodeOf(L label) {
        if (label == null)
            throw new NullPointerException();
        for (GraphNode<L> node : this.adjacentLists.keySet()) {
            if (node.getLabel().equals(label))
                return node;
        }
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
        Set<GraphNode<L>> adjacentNodes = new HashSet<>();
        for (GraphEdge<L> currentEdge : this.adjacentLists.get(node)) {
            if (!currentEdge.getNode1().equals(node)) {
                adjacentNodes.add(currentEdge.getNode1());
            } else if (!currentEdge.getNode2().equals(node)) {
                adjacentNodes.add(currentEdge.getNode2());
            }
        }
        return adjacentNodes;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi predecessori non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        Set<GraphEdge<L>> setOfAllEdges = new HashSet<>();
        for (GraphNode<L> node : this.adjacentLists.keySet()) {
            setOfAllEdges.addAll(getEdgesOf(node));
        }
        return setOfAllEdges;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if (edge == null)
            throw new NullPointerException();
        if (edge.getNode1() == null || edge.getNode2() == null)
            throw new IllegalArgumentException();
        if (edge.isDirected())
            throw new IllegalArgumentException();
        if (containsEdge(edge))
            return false;
        this.adjacentLists.get(edge.getNode1()).add(edge);
        this.adjacentLists.get(edge.getNode2()).add(edge);
        return true;
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        if (edge == null)
            throw new NullPointerException();
        if (!containsNode(edge.getNode1()) || !containsNode(edge.getNode2()))
            throw new IllegalArgumentException();
        if (!containsEdge(edge))
            throw new IllegalArgumentException();
        this.adjacentLists.get(edge.getNode1()).remove(edge);
        this.adjacentLists.get(edge.getNode2()).remove(edge);
        return true;
    }

    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if (edge == null) {
            throw new NullPointerException();
        }
        if (edge.getNode1() == null || edge.getNode2() == null)
            throw new IllegalArgumentException();
        for (GraphNode<L> currentNode : getNodes()) {
            for (GraphEdge<L> currentEdge : getEdgesOf(currentNode)) {
                if (currentEdge.equals(edge)) {
                    return true;
                }
            }
        }
        return false;
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
        if (!(containsNode(node1) && containsNode(node2)))
            throw new IllegalArgumentException();
        for (GraphEdge<L> edge : getEdgesOf(node1)) {
            if ((edge.getNode1().equals(node1) && edge.getNode2().equals(node2)) ||
                    edge.getNode1().equals(node2) && edge.getNode2().equals(node1))
                return edge;
        }
        return null;
    }

    @Override
    public GraphEdge<L> getEdgeAtNodeIndexes(int i, int j) {
        throw new UnsupportedOperationException(
                "Operazioni con indici non supportate");
    }

}
