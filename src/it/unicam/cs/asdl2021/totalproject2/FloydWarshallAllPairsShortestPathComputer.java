package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'algoritmo di Floyd-Warshall per il calcolo di cammini
 * minimi tra tutte le coppie di nodi in un grafo pesato che può contenere anche
 * pesi negativi, ma non cicli di peso negativo.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class FloydWarshallAllPairsShortestPathComputer<L> {

    /*
     * Il grafo su cui opera questo calcolatore.
     */
    private Graph<L> graph;

    /*
     * Matrice dei costi dei cammini minimi. L'elemento in posizione i,j
     * corrisponde al costo di un cammino minimo tra il nodo i e il nodo j, dove
     * i e j sono gli interi associati ai nodi nel grafo (si richiede quindi che
     * la classe che implementa il grafo supporti le operazioni con indici).
     */
    private double[][] costMatrix;

    /*
     * Matrice dei predecessori. L'elemento in posizione i,j è -1 se non esiste
     * nessun cammino tra i e j oppure corrisponde all'indice di un nodo che
     * precede il nodo j in un qualche cammino minimo da i a j. Si intende che i
     * e j sono gli indici associati ai nodi nel grafo (si richiede quindi che
     * la classe che implementa il grafo supporti le operazioni con indici).
     */
    private int[][] predecessorMatrix;

    private boolean isComputed;

    /**
     * Crea un calcolatore di cammini minimi fra tutte le coppie di nodi per un
     * grafo orientato e pesato. Non esegue il calcolo, che viene eseguito
     * invocando successivamente il metodo computeShortestPaths().
     *
     * @param g il grafo su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException     se il grafo passato è nullo
     * @throws IllegalArgumentException se il grafo passato è vuoto
     * @throws IllegalArgumentException se il grafo passato non è orientato
     * @throws IllegalArgumentException se il grafo passato non è pesato,
     *                                  cioè esiste almeno un arco il cui
     *                                  peso è {@code Double.NaN}
     */
    public FloydWarshallAllPairsShortestPathComputer(Graph<L> g) {
        if (g == null)
            throw new NullPointerException();
        if (g.isEmpty() || !g.isDirected())
            throw new IllegalArgumentException();
        for (GraphEdge<L> eachEdge : g.getEdges())
            if (Double.isNaN(eachEdge.getWeight()))
                throw new IllegalArgumentException();

        this.graph = g;
        this.costMatrix = new double[graph.nodeCount()][graph.nodeCount()];
        this.predecessorMatrix = new int[graph.nodeCount()][graph.nodeCount()];
        this.isComputed = false;
        for (int i = 0; i < this.graph.nodeCount(); i++) {
            for (int j = 0; j < this.graph.nodeCount(); j++) {
                if (i == j) {
                    costMatrix[i][j] = 0.0;
                    predecessorMatrix[i][j] = i;
                } else if (this.graph.getEdgeAtNodeIndexes(i, j) == null) {
                    costMatrix[i][j] = Double.POSITIVE_INFINITY;
                    predecessorMatrix[i][j] = -1;
                } else {
                    costMatrix[i][j] = this.graph.getEdgeAtNodeIndexes(i, j).getWeight();
                    predecessorMatrix[i][j] = i;
                }
            }
        }
    }

    /**
     * Esegue il calcolo per la matrice dei costi dei cammini minimi e per la
     * matrice dei predecessori così come specificato dall'algoritmo di
     * Floyd-Warshall.
     *
     * @throws IllegalStateException se il calcolo non può essere effettuato
     *                               per via dei valori dei pesi del grafo,
     *                               ad esempio se il grafo contiene cicli
     *                               di peso negativo.
     */
    public void computeShortestPaths() {
        boolean hasImproved = false;
        for (int k = 0; k < this.graph.nodeCount(); k++) {
            for (int i = 0; i < this.graph.nodeCount(); i++) {
                for (int j = 0; j < this.graph.nodeCount(); j++) {
                    if (i == j && costMatrix[i][j] < 0.0)
                        throw new IllegalStateException();
                    if (costMatrix[i][j] <= (costMatrix[i][k] + costMatrix[k][j]))
                        continue;
                    else costMatrix[i][j] = (costMatrix[i][k] + costMatrix[k][j]);
//                    predecessorMatrix[i][j] = predecessorMatrix[i][k];
                    predecessorMatrix[i][j] = k;
                    if (!hasImproved)
                        hasImproved = true;
                }
            }
        }
        for (int k = 0; k < this.graph.nodeCount(); k++)
            for (int i = 0; i < this.graph.nodeCount(); i++)
                for (int j = 0; j < this.graph.nodeCount(); j++)
                    if (i == j && costMatrix[i][j] < 0)
                        throw new IllegalStateException();
        this.isComputed = true;
    }

    /**
     * Determina se è stata invocatala procedura di calcolo dei cammini minimi.
     *
     * @return true se i cammini minimi sono stati calcolati, false altrimenti
     */
    public boolean isComputed() {
        return isComputed;
    }

    /**
     * Restituisce il grafo su cui opera questo calcolatore.
     *
     * @return il grafo su cui opera questo calcolatore
     */
    public Graph<L> getGraph() {
        return this.graph;
    }

    /**
     * Restituisce una lista di archi da un nodo sorgente a un nodo target. Tale
     * lista corrisponde a un cammino minimo tra i due nodi nel grafo gestito da
     * questo calcolatore.
     *
     * @param sourceNode il nodo di partenza del cammino minimo da
     *                   restituire
     * @param targetNode il nodo di arrivo del cammino minimo da restituire
     * @return la lista di archi corrispondente al cammino minimo; la lista è
     * vuota se il nodo sorgente è il nodo target. Viene restituito
     * {@code null} se il nodo target non è raggiungibile dal nodo
     * sorgente
     * @throws NullPointerException     se almeno uno dei nodi passati è
     *                                  nullo
     * @throws IllegalArgumentException se almeno uno dei nodi passati non
     *                                  esiste
     * @throws IllegalStateException    se non è stato eseguito il calcolo
     *                                  dei cammini minimi
     */
    public List<GraphEdge<L>> getShortestPath(GraphNode<L> sourceNode, GraphNode<L> targetNode) {
        if (sourceNode == null || targetNode == null)
            throw new NullPointerException();
        if (!this.graph.containsNode(sourceNode) ||
                !this.graph.containsNode(targetNode))
            throw new IllegalArgumentException();
        if (!this.isComputed)
            throw new IllegalStateException();

        List<GraphEdge<L>> result = new ArrayList<>();
        if (sourceNode.equals(targetNode))
            return result;

        int i = this.graph.getNodeIndexOf(sourceNode.getLabel());
        int j = this.graph.getNodeIndexOf(targetNode.getLabel());
        GraphEdge<L> edge;
        if (this.costMatrix[i][j] == Double.POSITIVE_INFINITY) {
            System.out.println("Il nodo " + j + " non e' raggiungibile partendo da " + i);
            return null;
        }

        //TODO fix this thing tf
//        while (i != j) {
        for (int counter = 0; counter < this.graph.nodeCount(); counter++) {
            if (i == j)
                break;
//            j = predecessorMatrix[i][j];
            if (predecessorMatrix[i][j] == -1)
                return null;
            else {
                edge = this.graph.getEdgeAtNodeIndexes(predecessorMatrix[i][j], j);
            }
            if (edge == null) {
                System.out.println("edge è risultato null");
                return null;
            } else {
                result.add(0, edge);
            }
            j = predecessorMatrix[i][j];
        }
        return result;
    }

    /**
     * Restituisce il costo di un cammino minimo da un nodo sorgente a un nodo
     * target.
     *
     * @param sourceNode il nodo di partenza del cammino minimo
     * @param targetNode il nodo di arrivo del cammino minimo
     * @return il coso di un cammino minimo tra il nodo sorgente e il nodo
     * target. Viene restituito {@code Double.POSITIVE_INFINITY} se il
     * nodo target non è raggiungibile dal nodo sorgente, mentre viene
     * restituito zero se il nodo sorgente è il nodo target.
     * @throws NullPointerException     se almeno uno dei nodi passati è
     *                                  nullo
     * @throws IllegalArgumentException se almeno uno dei nodi passati non
     *                                  esiste
     * @throws IllegalStateException    se non è stato eseguito il calcolo
     *                                  dei cammini minimi
     */
    public double getShortestPathCost(GraphNode<L> sourceNode, GraphNode<L> targetNode) {
        if (sourceNode == null || targetNode == null)
            throw new NullPointerException();
        if (!this.graph.containsNode(sourceNode) ||
                !this.graph.containsNode(targetNode))
            throw new IllegalArgumentException();
        if (!this.isComputed)
            throw new IllegalStateException();

        double result = 0.0;

        if (sourceNode.equals(targetNode))
            return result;

        int i = this.graph.getNodeIndexOf(sourceNode.getLabel());
        int j = this.graph.getNodeIndexOf(targetNode.getLabel());
        GraphEdge<L> edge;

        for (int counter = 0; counter < this.graph.nodeCount(); counter++) {
            if (i == j)
                break;
            if (predecessorMatrix[i][j] == -1)
                return Double.POSITIVE_INFINITY;
            edge = this.graph.getEdgeAtNodeIndexes(predecessorMatrix[i][j], j);
            if (edge == null) {
                System.out.println("edge è risultato null");
                return Double.POSITIVE_INFINITY;
            } else
                result += edge.getWeight();
            j = predecessorMatrix[i][j];
        }

//        while (i != j) {
//            if (predecessorMatrix[i][j] == -1)
//                return Double.POSITIVE_INFINITY;
//            edge = this.graph.getEdgeAtNodeIndexes(i, j);
//            result += edge.getWeight();
//            i = predecessorMatrix[i][j];
//        }
        return result;
    }

    /**
     * Genera una stringa di descrizione di un path riportando i nodi
     * attraversati e i pesi degli archi. Nel caso di cammino vuoto genera solo
     * la stringa {@code "[ ]"}.
     *
     * @param path un cammino minimo
     * @return una stringa di descrizione del cammino minimo
     * @throws NullPointerException se il cammino passato è nullo
     */
    public String printPath(List<GraphEdge<L>> path) {
        if (path == null)
            throw new NullPointerException(
                    "Richiesta di stampare un path nullo");
        if (path.isEmpty())
            return "[ ]";
        // Costruisco la stringa
        StringBuffer s = new StringBuffer();
        s.append("[ " + path.get(0).getNode1().toString());
        for (int i = 0; i < path.size(); i++)
            s.append(" -- " + path.get(i).getWeight() + " --> "
                    + path.get(i).getNode2().toString());
        s.append(" ]");
        return s.toString();
    }

    /**
     * @return the costMatrix
     */
    public double[][] getCostMatrix() {
        return costMatrix;
    }

    /**
     * @return the predecessorMatrix
     */
    public int[][] getPredecessorMatrix() {
        return predecessorMatrix;
    }

    // TODO inserire eventuali metodi privati per fini di implementazione

}
