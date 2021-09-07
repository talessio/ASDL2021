package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author Template: Luca Tesei
 */
class FloydWarshallAllPairsShortestPathComputerTest {

    // TODO implementare tutti i test non ancora implementati

    Graph<String> graph = null;

    GraphNode<String> a = new GraphNode<>("1");
    GraphNode<String> b = new GraphNode<>("2");
    GraphNode<String> c = new GraphNode<>("3");
    GraphNode<String> d = new GraphNode<>("4");
    GraphNode<String> e = new GraphNode<>("5");
    GraphNode<String> g = new GraphNode<>("6");
    GraphNode<String> h = new GraphNode<>("7");
    GraphNode<String> i = new GraphNode<>("8");
    GraphNode<String> n = new GraphNode<>("9");
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
    GraphEdge<String> heNegCicle = new GraphEdge<String>(h, e, true, -100);
    GraphEdge<String> gd = new GraphEdge<String>(g, d, true, -1.7);
    GraphEdge<String> dh = new GraphEdge<String>(d, h, true, 3.9);
    GraphEdge<String> nd = new GraphEdge<String>(n, d, true, 0);
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
        FloydWarshallAllPairsShortestPathComputer<String> comp =
                new FloydWarshallAllPairsShortestPathComputer<>(graph);
        assertThrows(IllegalStateException.class, () -> comp.getShortestPath(a, h));
        comp.computeShortestPaths();
        ArrayList<GraphEdge<String>> bitestList = new ArrayList<GraphEdge<String>>();
        bitestList.add(bc);
        bitestList.add(ci);
        assertEquals(bitestList, comp.getShortestPath(b, i));

//        ArrayList<GraphEdge<String>> ahtestList = new ArrayList<GraphEdge<String>>();
//        ahtestList.add(ab);
//        ahtestList.add(bc);
//        ahtestList.add(ce);
//        ahtestList.add(eh);
//        assertEquals(ahtestList, comp.getShortestPath(a, h));

//        assertThrows(IllegalStateException.class, () -> comp.computeShortestPaths());
    }

    @Test
    final void testIsComputed() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetGraph() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetShortestPath() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetShortestPathCost() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testPrintPath() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetCostMatrix() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetPredecessorMatrix() {
        fail("Not yet implemented"); // TODO
    }

}
