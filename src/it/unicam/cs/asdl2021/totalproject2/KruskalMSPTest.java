package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * @author Template: Luca Tesei
 */
class KruskalMSPTest {

    GraphNode<String> a = new GraphNode<String>("a");
    GraphNode<String> b = new GraphNode<String>("b");
    GraphNode<String> c = new GraphNode<String>("c");
    GraphNode<String> d = new GraphNode<String>("d");
    GraphNode<String> e = new GraphNode<String>("e");
    GraphNode<String> g = new GraphNode<String>("g");
    GraphNode<String> h = new GraphNode<String>("h");
    GraphNode<String> i = new GraphNode<String>("i");
    GraphNode<String> n = new GraphNode<String>("n");
    GraphNode<String> z = new GraphNode<String>("z");
    GraphEdge<String> ab = new GraphEdge<String>(a, b, false, 1.0);
    GraphEdge<String> ah = new GraphEdge<String>(a, h, false, 20);
    GraphEdge<String> bc = new GraphEdge<String>(b, c, false, 4.0);
    GraphEdge<String> ce = new GraphEdge<String>(c, e, false, 3.4);
    GraphEdge<String> ci = new GraphEdge<String>(c, i, false, 8.95);
    GraphEdge<String> eh = new GraphEdge<String>(e, h, false, 5);
    GraphEdge<String> hg = new GraphEdge<String>(h, g, false, 0.1);
    GraphEdge<String> hi = new GraphEdge<String>(h, i, false, 30.5);
    GraphEdge<String> gd = new GraphEdge<String>(g, d, false, 1.7);
    GraphEdge<String> dh = new GraphEdge<String>(d, h, false, 3.9);
    GraphEdge<String> nd = new GraphEdge<String>(n, d, false, 0);
    GraphEdge<String> iz = new GraphEdge<String>(i, z, false, 5);
    GraphEdge<String> directed = new GraphEdge<String>(b, n, true, 4.20);
    GraphEdge<String> noWeight = new GraphEdge<String>(d, b, false);
    GraphEdge<String> negWeight = new GraphEdge<String>(z, e, false, -69);

    @Test
    final void testThrownExceptions() {
        KruskalMSP<String> comp = new KruskalMSP<String>();
        Graph<String> directedGraph = new AdjacencyMatrixDirectedGraph<String>();
        directedGraph.addNode(b);
        directedGraph.addNode(n);
        directedGraph.addEdge(directed);
        assertThrows(IllegalArgumentException.class, () -> comp.computeMSP(directedGraph));
        MapAdjacentListUndirectedGraph<String> nullGraph = null;
        assertThrows(NullPointerException.class, () -> comp.computeMSP(nullGraph));
        MapAdjacentListUndirectedGraph<String> graph = new MapAdjacentListUndirectedGraph<String>();
        Set<String> testSet = new HashSet<String>();
        assertTrue(comp.computeMSP(graph).isEmpty());
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addNode(z);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(bc);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        graph.addEdge(iz);
        graph.addEdge(noWeight);
        assertThrows(IllegalArgumentException.class, () -> comp.computeMSP(graph));
        graph.removeEdge(noWeight);
        graph.addEdge(negWeight);
        assertThrows(IllegalArgumentException.class, () -> comp.computeMSP(graph));
        graph.removeEdge(negWeight);
        comp.computeMSP(graph);
    }

    @Test
    final void testMSP() {
        MapAdjacentListUndirectedGraph<String> graph = new MapAdjacentListUndirectedGraph<String>();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addNode(z);
        graph.addEdge(ab);
        graph.addEdge(ah);
        graph.addEdge(bc);
        graph.addEdge(ce);
        graph.addEdge(ci);
        graph.addEdge(eh);
        graph.addEdge(hg);
        graph.addEdge(hi);
        graph.addEdge(gd);
        graph.addEdge(dh);
        graph.addEdge(nd);
        graph.addEdge(iz);
        Set<GraphEdge<String>> testSet = new HashSet<>();
        testSet.add(ab);
        testSet.add(bc);
        testSet.add(ce);
        testSet.add(ci);
        testSet.add(iz);
        testSet.add(eh);
        testSet.add(hg);
        testSet.add(gd);
        testSet.add(nd);
        KruskalMSP<String> comp = new KruskalMSP<String>();
        assertEquals(testSet, comp.computeMSP(graph));
    }

    @Test
    final void testComputeMSP() {
        Graph<String> gr = new MapAdjacentListUndirectedGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<String>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode(d);
        GraphNode<String> e = new GraphNode<String>("e");
        gr.addNode(e);
        GraphNode<String> f = new GraphNode<String>("f");
        gr.addNode(f);
        GraphNode<String> g = new GraphNode<String>("g");
        gr.addNode(g);
        GraphNode<String> h = new GraphNode<String>("h");
        gr.addNode(h);
        GraphNode<String> i = new GraphNode<String>("i");
        gr.addNode(i);
        gr.addEdge(new GraphEdge<String>(a, b, false, 4));
        gr.addEdge(new GraphEdge<String>(a, h, false, 8.5));
        gr.addEdge(new GraphEdge<String>(b, h, false, 11));
        gr.addEdge(new GraphEdge<String>(b, c, false, 8));
        gr.addEdge(new GraphEdge<String>(c, i, false, 2));
        gr.addEdge(new GraphEdge<String>(c, d, false, 7));
        gr.addEdge(new GraphEdge<String>(c, f, false, 4));
        gr.addEdge(new GraphEdge<String>(d, f, false, 14));
        gr.addEdge(new GraphEdge<String>(d, e, false, 9));
        gr.addEdge(new GraphEdge<String>(e, f, false, 10));
        gr.addEdge(new GraphEdge<String>(f, g, false, 2));
        gr.addEdge(new GraphEdge<String>(g, i, false, 6));
        gr.addEdge(new GraphEdge<String>(g, h, false, 1));
        gr.addEdge(new GraphEdge<String>(h, i, false, 7));
        KruskalMSP<String> alg = new KruskalMSP<String>();
        Set<GraphEdge<String>> result = new HashSet<GraphEdge<String>>();
        result.add(new GraphEdge<String>(a, b, false, 4));
        result.add(new GraphEdge<String>(b, c, false, 8));
        result.add(new GraphEdge<String>(c, i, false, 2));
        result.add(new GraphEdge<String>(c, d, false, 7));
        result.add(new GraphEdge<String>(c, f, false, 4));
        result.add(new GraphEdge<String>(d, e, false, 9));
        result.add(new GraphEdge<String>(f, g, false, 2));
        result.add(new GraphEdge<String>(g, h, false, 1));
        assertEquals(alg.computeMSP(gr), result);
    }
}
