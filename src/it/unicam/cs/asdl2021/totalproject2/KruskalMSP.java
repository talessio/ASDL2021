package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe singoletto che implementa l'algoritmo di Kruskal per trovare un
 * Minimum Spanning Tree di un grafo non orientato, pesato e con pesi non
 * negativi.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class KruskalMSP<L> {

    /*
     * Struttura dati per rappresentare gli insiemi disgiunti utilizzata
     * dall'algoritmo di Kruskal.
     */
    private ArrayList<HashSet<GraphNode<L>>> disjointSets;

    // TODO implementare: inserire eventuali altre variabili istanza

    private Set<GraphEdge<L>> msp;

    /**
     * Costruisce un calcolatore di un albero di copertura minimo che usa
     * l'algoritmo di Kruskal su un grafo non orientato e pesato.
     */
    public KruskalMSP() {
        this.disjointSets = new ArrayList<HashSet<GraphNode<L>>>();
        // TODO implementare: completare con eventuali altre inizializzazioni

    }

    /**
     * Utilizza l'algoritmo goloso di Kruskal per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non
     * negativi. L'albero restituito non è radicato, quindi è rappresentato
     * semplicemente con un sottoinsieme degli archi del grafo.
     *
     * @param g un grafo non orientato, pesato, con pesi non negativi
     * @return l'insieme degli archi del grafo g che costituiscono l'albero di
     * copertura minimo trovato
     * @throw NullPointerException se il grafo g è null
     * @throw IllegalArgumentException se il grafo g è orientato, non pesato o
     * con pesi negativi
     */
    public Set<GraphEdge<L>> computeMSP(Graph<L> g) {
        // TODO implementare
        if (g == null)
            throw new NullPointerException();
        if (g.isDirected())
            throw new IllegalArgumentException();
        double minWeight = Double.MAX_VALUE;
        GraphEdge<L> minEdge = null;
        Set<GraphEdge<L>> edgeSet = g.getEdges();
        if (edgeSet.isEmpty())
            return this.msp;

        for (int i = 0; i < g.edgeCount(); i++) {
            for (GraphEdge<L> eachEdge : edgeSet) {
                if (!eachEdge.hasWeight() ||
                        eachEdge.getWeight() < 0)
                    throw new IllegalArgumentException();
                if (eachEdge.getWeight() < eachEdge.getWeight()) {
                    minWeight = eachEdge.getWeight();
                    minEdge = eachEdge;
                    edgeSet.remove(eachEdge);
                }
            }
            if (minEdge == null)
                return this.msp;
            if (minEdge.getNode1().getColor() == GraphNode.COLOR_WHITE &&
                    minEdge.getNode2().getColor() == GraphNode.COLOR_WHITE) {
                bothWhite(minEdge);
            } else if (minEdge.getNode1().getColor() == GraphNode.COLOR_BLACK &&
                    minEdge.getNode2().getColor() == GraphNode.COLOR_BLACK) {
                bothBlack(minEdge);
            } else
                blackNWhite(minEdge);
        }
        return this.msp;
    }

    /**
     * Handles case in which both nodes are black. If both belong to the same Set already they are disregarded.
     * If one belongs to one Set and one to another, the two Sets are joined.
     * @param edge the edge whose nodes belong to one or more Sets.
     */
    private void bothBlack(GraphEdge<L> edge) {
        for (HashSet<GraphNode<L>> currentSet : this.disjointSets) {
            if (currentSet.contains(edge.getNode1())) { //uno è già nero
                if (currentSet.contains(edge.getNode2()))
                    break;
                else for (HashSet<GraphNode<L>> otherSet : this.disjointSets) {
                    if (currentSet.equals(otherSet))
                        continue;
                    else if (otherSet.contains(edge.getNode2())) { //entrambi neri
                        currentSet.addAll(otherSet);
                        this.disjointSets.remove(otherSet); //ATTENZIONE rimozione
                        this.msp.add(edge);
                    }
                }
            }
        }
    }

    /**
     * Handles case in which both nodes are white by joining them into a single Set.
     * @param edge the edge whose nodes will be added to a Set
     */
    private void bothWhite(GraphEdge<L> edge) {
        HashSet<GraphNode<L>> newSet = new HashSet<>();
        newSet.add(edge.getNode1());
        newSet.add(edge.getNode2());
        edge.getNode1().setColor(GraphNode.COLOR_BLACK);
        edge.getNode2().setColor(GraphNode.COLOR_BLACK);
        this.disjointSets.add(newSet);
        this.msp.add(edge);
    }

    /**
     * Handles case in which one node is black and the other is white.
     * The white node joins the Set where the black node is housed.
     * @param edge the edge whose white node will be added to the black node's Set
     */
    private void blackNWhite(GraphEdge<L> edge) {
        GraphNode<L> blackNode, whiteNode;
        if (edge.getNode1().getColor() == GraphNode.COLOR_BLACK) {
            blackNode = edge.getNode1();
            whiteNode = edge.getNode2();
        } else {
            blackNode = edge.getNode2();
            whiteNode = edge.getNode1();
        }
        for (HashSet<GraphNode<L>> currentSet : this.disjointSets) {
            if (currentSet.contains(blackNode)) {
                currentSet.add(whiteNode);
                whiteNode.setColor(GraphNode.COLOR_BLACK);
                this.msp.add(edge);
            }
        }
    }
}
