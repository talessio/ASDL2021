package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Template: Luca Tesei
 */
class FloydWarshallAllPairsShortestPathComputerTest {

    // TODO implementare tutti i test non ancora implementati

    Graph<String> graph = null;

    GraphNode<String> a = new GraphNode<>("a");
    GraphNode<String> b = new GraphNode<>("b");
    GraphNode<String> c = new GraphNode<>("c");
    GraphNode<String> d = new GraphNode<>("d");
    GraphNode<String> e = new GraphNode<>("e");
    GraphNode<String> g = new GraphNode<>("g");
    GraphNode<String> h = new GraphNode<>("h");
    GraphNode<String> i = new GraphNode<>("i");
    GraphNode<String> n = new GraphNode<>("n");
    GraphEdge<String> ab = new GraphEdge<String>(a, b, true, 2.2);
    GraphEdge<String> ah = new GraphEdge<String>(a, h, true, 20);
    GraphEdge<String> ba = new GraphEdge<String>(b, a, true, 1);
    GraphEdge<String> bc = new GraphEdge<String>(b, c, true, 4);
    GraphEdge<String> cb = new GraphEdge<String>(c, b, true, -1);
    GraphEdge<String> ce = new GraphEdge<String>(c, e, true, 3.4);
    GraphEdge<String> ci = new GraphEdge<String>(c, i, true, 8.95);
    GraphEdge<String> eh = new GraphEdge<String>(e, h, true, 5);
    GraphEdge<String> hg = new GraphEdge<String>(h, g, true, -0.1);
    GraphEdge<String> hi = new GraphEdge<String>(h, i, true, 30.5);
    GraphEdge<String> gd = new GraphEdge<String>(g, d, true, -1.7);
    GraphEdge<String> dh = new GraphEdge<String>(d, h, true, 3.9);
    GraphEdge<String> nd = new GraphEdge<String>(n, d, true, 0);
    GraphEdge<String> negCicle = new GraphEdge<String>(h, e, true, -100);
    GraphEdge<String> undirected = new GraphEdge<String>(b, n, false);
    GraphEdge<String> noWeight = new GraphEdge<String>(d, b, true);

    @Test
    final void testFloydWarshallAllPairsShortestPathComputer() {
        assertThrows(NullPointerException.class, () -> new FloydWarshallAllPairsShortestPathComputer<String>(graph));
        graph = new MapAdjacentListUndirectedGraph<String>();
        assertThrows(IllegalArgumentException.class, () -> new FloydWarshallAllPairsShortestPathComputer<String>(graph));
        graph.addNode(b);
        graph.addNode(n);
        graph.addEdge(undirected);
        assertThrows(IllegalArgumentException.class, () -> new FloydWarshallAllPairsShortestPathComputer<String>(graph));
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(e);
        graph.addNode(d);
        graph.addEdge(ab);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(noWeight);
        assertThrows(IllegalArgumentException.class, () -> new FloydWarshallAllPairsShortestPathComputer<String>(graph));
        graph.removeEdge(noWeight);
        new FloydWarshallAllPairsShortestPathComputer<String>(graph);
    }

    @Test
    final void testComputeShortestPaths() {
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        FloydWarshallAllPairsShortestPathComputer<String> comp = new FloydWarshallAllPairsShortestPathComputer<>(graph);
        assertThrows(IllegalStateException.class, () -> comp.getShortestPath(a, h));

        comp.computeShortestPaths();
        ArrayList<GraphEdge<String>> bitestList = new ArrayList<GraphEdge<String>>();
        bitestList.add(bc);
        bitestList.add(ci);
        assertEquals(bitestList, comp.getShortestPath(b, i));

        graph.addEdge(negCicle);
        assertThrows(IllegalStateException.class, () -> comp.computeShortestPaths());
    }

    @Test
    final void testIsComputed() {
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        FloydWarshallAllPairsShortestPathComputer<String> comp =
                new FloydWarshallAllPairsShortestPathComputer<>(graph);
        assertFalse(comp.isComputed());
        comp.computeShortestPaths();
        assertTrue(comp.isComputed());
    }

    @Test
    final void testGetGraph() {
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        FloydWarshallAllPairsShortestPathComputer<String> comp =
                new FloydWarshallAllPairsShortestPathComputer<>(graph);
        assertEquals(graph, comp.getGraph());
        comp.computeShortestPaths();
        assertEquals(graph, comp.getGraph());
    }

    @Test
    final void testGetShortestPath() {
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        FloydWarshallAllPairsShortestPathComputer<String> comp =
                new FloydWarshallAllPairsShortestPathComputer<>(graph);
        ArrayList<GraphEdge<String>> ahtestList = new ArrayList<GraphEdge<String>>();
        ahtestList.add(ab);
        ahtestList.add(bc);
        ahtestList.add(ce);
        ahtestList.add(eh);
        comp.computeShortestPaths();
        assertEquals(ahtestList, comp.getShortestPath(a, h));
    }

    @Test
    final void testGetShortestPathCost() {
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        FloydWarshallAllPairsShortestPathComputer<String> comp =
                new FloydWarshallAllPairsShortestPathComputer<>(graph);
        comp.computeShortestPaths();
        assertEquals(14.6, comp.getShortestPathCost(a, h));
        assertEquals(0, comp.getShortestPathCost(c, a));
        assertEquals(2.2, comp.getShortestPathCost(g, h));
    }

    @Test
    final void testPrintPath() {
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        FloydWarshallAllPairsShortestPathComputer<String> comp =
                new FloydWarshallAllPairsShortestPathComputer<>(graph);
        ArrayList<GraphEdge<String>> path = new ArrayList<GraphEdge<String>>();
        assertThrows(NullPointerException.class, () -> comp.printPath(null));
        String test = "[ ]";
        assertEquals(test, comp.printPath(path));
        path.add(ab);
        path.add(bc);
        path.add(ce);
        path.add(eh);
        String test2 = "[ Nodo[ a ] -- 2.2 --> Nodo[ b ] -- 4.0 --> Nodo[ c ]" +
                " -- 3.4 --> Nodo[ e ] -- 5.0 --> Nodo[ h ] ]";
        assertEquals(test2, comp.printPath(path));
    }

    @Test
    final void testGetCostMatrix() {
        double[][] testMatrix = {
//                A -- B -- C -- D --- E --- G --- H --- I ---- N
                {0.0, 2.2, 6.2, 12.8, 9.6, 14.5, 14.6, 15.15, Double.POSITIVE_INFINITY},
                {1.0, 0.0, 4.0, 10.6, 7.4, 12.3, 12.4, 12.95, Double.POSITIVE_INFINITY},
                {0.0, -1.0, 0.0, 6.6, 3.4, 8.3, 8.4, 8.95, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                        0.0, Double.POSITIVE_INFINITY, 3.8, 3.9, 34.4, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                        3.2, 0.0, 4.9, 5.0, 35.5, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                        -1.7, Double.POSITIVE_INFINITY, 0.0, 2.2, 32.7, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                        -1.8, Double.POSITIVE_INFINITY, -0.1, 0.0, 30.5, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                        Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                        Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
                        0.0, Double.POSITIVE_INFINITY, 3.8, 3.9, 34.4, 0.0}
        };
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        FloydWarshallAllPairsShortestPathComputer<String> comp =
                new FloydWarshallAllPairsShortestPathComputer<>(graph);
        comp.computeShortestPaths();
        assertEquals(Arrays.deepToString(testMatrix), Arrays.deepToString(comp.getCostMatrix()));

    }

    @Test
    final void testGetPredecessorMatrix() {
        int[][] testMatrix = {
//               A, B, C, D, E, G, H, I, N
                {0, 0, 1, 5, 2, 6, 4, 2, -1},
                {1, 1, 1, 5, 2, 6, 4, 2, -1},
                {1, 2, 2, 5, 2, 6, 4, 2, -1},
                {-1, -1, -1, 3, -1, 6, 3, 6, -1},
                {-1, -1, -1, 5, 4, 6, 4, 6, -1},
                {-1, -1, -1, 5, -1, 5, 3, 6, -1},
                {-1, -1, -1, 5, -1, 6, 6, 6, -1},
                {-1, -1, -1, -1, -1, -1, -1, 7, -1},
                {-1, -1, -1, 8, -1, 6, 3, 6, 8}
        };
        graph = new AdjacencyMatrixDirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(ba);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        FloydWarshallAllPairsShortestPathComputer<String> comp =
                new FloydWarshallAllPairsShortestPathComputer<>(graph);
        comp.computeShortestPaths();
        assertEquals(Arrays.deepToString(testMatrix), Arrays.deepToString(comp.getPredecessorMatrix()));
    }

}
