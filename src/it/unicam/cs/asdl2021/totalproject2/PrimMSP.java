package it.unicam.cs.asdl2021.totalproject2;

/**
 * Classe singoletto che implementa l'algoritmo di Prim per trovare un Minimum
 * Spanning Tree di un grafo non orientato, pesato e con pesi non negativi.
 * <p>
 * L'algoritmo usa una coda di min priorità tra i nodi implementata dalla classe
 * TernaryHeapMinPriorityQueue. I nodi vengono visti come PriorityQueueElement
 * poiché la classe GraphNode<L> implementa questa interfaccia. Si noti che
 * nell'esecuzione dell'algoritmo è necessario utilizzare l'operazione di
 * decreasePriority.
 *
 * @param <L> etichette dei nodi del grafo
 * @author Template: Luca Tesei
 */
public class PrimMSP<L> {

    /*
     * Coda di priorità che va usata dall'algoritmo. La variabile istanza è
     * protected solo per scopi di testing JUnit.
     */
    protected BinaryHeapMinPriorityQueue queue;

    /**
     * Crea un nuovo algoritmo e inizializza la coda di priorità con una coda
     * vuota.
     */
    public PrimMSP() {
        this.queue = new BinaryHeapMinPriorityQueue();
    }

    /**
     * Utilizza l'algoritmo goloso di Prim per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non negativi.
     * Dopo l'esecuzione del metodo nei nodi del grafo il campo previous deve
     * contenere un puntatore a un nodo in accordo all'albero di copertura
     * minimo calcolato, la cui radice è il nodo sorgente passato.
     *
     * @param g un grafo non orientato, pesato, con pesi non negativi
     * @param s il nodo del grafo g sorgente, cioè da cui parte il calcolo
     *          dell'albero di copertura minimo. Tale nodo sarà la radice
     *          dell'albero di copertura trovato
     * @throw NullPointerException se il grafo g o il nodo sorgente s sono nulli
     * @throw IllegalArgumentException se il nodo sorgente s non esiste in g
     * @throw IllegalArgumentException se il grafo g è orientato, non pesato o
     * con pesi negativi
     */
    public void computeMSP(Graph<L> g, GraphNode<L> s) {
        // TODO implementare
        if (g == null || s == null)
            throw new NullPointerException();
        if (g.isDirected() || !g.containsNode(s))
            throw new IllegalArgumentException();
        for (GraphEdge<L> edge : g.getEdges())
            if (!edge.hasWeight() || edge.getWeight() < 0)
                throw new IllegalArgumentException();
        for (GraphNode<L> node : g.getNodes())
            queue.insert(node);
        GraphNode<L> node = s;
//        for (int i = 0; i < g.nodeCount(); i++) {
//            for (GraphEdge<L> edge:
//                 ) {
//
//            }
//        }
    }

    // TODO implementare: inserire eventuali metodi privati per rendere
    // l'implementazione più modulare

}
