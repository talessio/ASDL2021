package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author Template: Luca Tesei
 */
class BellmanFordShortestPathComputerTest {

    // TODO implementare tutti i test non ancora implementati

    Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
    Graph<String> undGraph = new MapAdjacentListUndirectedGraph<>();
    GraphNode<String> a = new GraphNode<String>("a");
    GraphNode<String> b = new GraphNode<String>("b");
    GraphNode<String> c = new GraphNode<String>("c");
    GraphNode<String> d = new GraphNode<String>("d");
    GraphNode<String> e = new GraphNode<String>("e");
    GraphNode<String> g = new GraphNode<String>("g");
    GraphNode<String> h = new GraphNode<String>("h");
    GraphNode<String> i = new GraphNode<String>("i");
    GraphNode<String> n = new GraphNode<String>("n");
    GraphEdge<String> ab = new GraphEdge<String>(a, b, true, 2.2);
    GraphEdge<String> ah = new GraphEdge<String>(a, h, true, 20.0);
    GraphEdge<String> bc = new GraphEdge<String>(b, c, true, 4.0);
    GraphEdge<String> cb = new GraphEdge<String>(c, b, true, -1.0);
    GraphEdge<String> ci = new GraphEdge<String>(c, i, true, 8.95);
    GraphEdge<String> ce = new GraphEdge<String>(c, e, true, 3.4);
    GraphEdge<String> eh = new GraphEdge<String>(e, h, true, 5.0);
    GraphEdge<String> hi = new GraphEdge<String>(h, i, true, 30.5);
    GraphEdge<String> dh = new GraphEdge<String>(d, h, true, 3.9);
    GraphEdge<String> hg = new GraphEdge<String>(h, g, true, -0.1);
    GraphEdge<String> gd = new GraphEdge<String>(g, d, true, -1.7);
    GraphEdge<String> nd = new GraphEdge<String>(n, d, true, 0.0);

    GraphEdge<String> undirND = new GraphEdge<String>(n, d, false, 0.0);
    GraphEdge<String> NaNAH = new GraphEdge<String>(a, h, true, Double.NaN);


    @Test
    final void testBellmanFordShortestPathComputer() {
        assertThrows(NullPointerException.class, () -> new BellmanFordShortestPathComputer<String>(null));
        undGraph.addNode(n);
        undGraph.addNode(d);
        undGraph.addEdge(undirND);
        assertThrows(IllegalArgumentException.class, () -> new BellmanFordShortestPathComputer<String>(undGraph));
        graph.addNode(a);
        graph.addNode(h);
        graph.addEdge(NaNAH);
        assertThrows(IllegalArgumentException.class, () -> new BellmanFordShortestPathComputer<String>(graph));
        graph = new AdjacencyMatrixDirectedGraph<>();
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
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ci);
        graph.addEdge(ce);
        graph.addEdge(eh);
        graph.addEdge(hi);
        graph.addEdge(dh);
        graph.addEdge(hg);
        graph.addEdge(gd);
        graph.addEdge(nd);
        BellmanFordShortestPathComputer<String> computer = new BellmanFordShortestPathComputer<String>(graph);
        assertEquals(graph, computer.getGraph());
        assertThrows(IllegalStateException.class, () -> computer.getLastSource());
    }

    @Test
    final void testComputeShortestPathsFrom() {
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);
        graph.addNode(n);
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ci);
        graph.addEdge(ce);
        graph.addEdge(eh);
        graph.addEdge(hi);
        graph.addEdge(dh);
        graph.addEdge(hg);
        graph.addEdge(gd);
        graph.addEdge(nd);
        BellmanFordShortestPathComputer<String> computer = new BellmanFordShortestPathComputer<String>(graph);
        assertThrows(NullPointerException.class, () -> computer.computeShortestPathsFrom(null));
        assertThrows(IllegalArgumentException.class, () -> computer.computeShortestPathsFrom(a));
        graph.addNode(a);
        graph.addEdge(ab);
        graph.addEdge(ah);
        cb.setWeight(-10);
        assertThrows(IllegalStateException.class, () -> computer.computeShortestPathsFrom(a));
        cb.setWeight(-1);
        computer.computeShortestPathsFrom(a);
        hg.setWeight(-5);
        assertThrows(IllegalStateException.class, () -> computer.computeShortestPathsFrom(h));
        hg.setWeight(-0.1);
        computer.computeShortestPathsFrom(a);
        assertEquals(graph.getNodeOf("a").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("h").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("i").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("g").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("n").getColor(), GraphNode.COLOR_WHITE);
        computer.computeShortestPathsFrom(n);
        assertEquals(graph.getNodeOf("n").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("d").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("h").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("g").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("i").getColor(), GraphNode.COLOR_BLACK);
        assertEquals(graph.getNodeOf("a").getColor(), GraphNode.COLOR_WHITE);
        assertEquals(graph.getNodeOf("b").getColor(), GraphNode.COLOR_WHITE);
        assertEquals(graph.getNodeOf("c").getColor(), GraphNode.COLOR_WHITE);
        assertEquals(graph.getNodeOf("e").getColor(), GraphNode.COLOR_WHITE);
    }

    @Test
    final void testIsComputed() {
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
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ci);
        graph.addEdge(ce);
        graph.addEdge(eh);
        graph.addEdge(hi);
        graph.addEdge(dh);
        graph.addEdge(hg);
        graph.addEdge(gd);
        graph.addEdge(nd);
        BellmanFordShortestPathComputer<String> computer = new BellmanFordShortestPathComputer<String>(graph);
        assertFalse(computer.isComputed());
        computer.computeShortestPathsFrom(a);
        assertTrue(computer.isComputed());
        computer = new BellmanFordShortestPathComputer<String>(graph);
        assertFalse(computer.isComputed());
    }

    @Test
    final void testGetLastSource() {
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
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ci);
        graph.addEdge(ce);
        graph.addEdge(eh);
        graph.addEdge(hi);
        graph.addEdge(dh);
        graph.addEdge(hg);
        graph.addEdge(gd);
        graph.addEdge(nd);
        BellmanFordShortestPathComputer<String> computer = new BellmanFordShortestPathComputer<String>(graph);
        assertThrows(IllegalStateException.class, () -> computer.getLastSource());
        computer.computeShortestPathsFrom(a);
        assertEquals(computer.getLastSource(), a);
    }

    @Test
    final void testGetGraph() {
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
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ci);
        graph.addEdge(ce);
        graph.addEdge(eh);
        graph.addEdge(hi);
        graph.addEdge(dh);
        graph.addEdge(hg);
        graph.addEdge(gd);
        graph.addEdge(nd);
        BellmanFordShortestPathComputer<String> computer = new BellmanFordShortestPathComputer<String>(graph);
        assertEquals(graph, computer.getGraph());
    }

    @Test
    final void testGetShortestPathTo() {

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
        graph.addEdge(bc);
        graph.addEdge(cb);
        graph.addEdge(ci);
        graph.addEdge(ce);
        graph.addEdge(eh);
        graph.addEdge(hi);
        graph.addEdge(dh);
        graph.addEdge(hg);
        graph.addEdge(gd);
        graph.addEdge(nd);
        GraphNode<String> node1 = new GraphNode<>("label1");
        ArrayList<GraphEdge<String>> expPath = new ArrayList<>();
        BellmanFordShortestPathComputer<String> computer = new BellmanFordShortestPathComputer<String>(graph);
        assertThrows(IllegalStateException.class, () -> computer.getShortestPathTo(c));
        computer.computeShortestPathsFrom(a);
        assertThrows(NullPointerException.class, () -> computer.getShortestPathTo(null));
        assertThrows(IllegalArgumentException.class, () -> computer.getShortestPathTo(node1));

        assertNull(computer.getShortestPathTo(n));
        expPath.add(ab);
        expPath.add(bc);
        expPath.add(ce);
        expPath.add(eh);
        assertEquals(expPath, computer.getShortestPathTo(h));
        expPath.add(hg);
        assertEquals(expPath, computer.getShortestPathTo(g));
        expPath.add(gd);
        assertEquals(expPath, computer.getShortestPathTo(d));
        expPath.clear();
        expPath.add(ab);
        expPath.add(bc);
        expPath.add(ci);
        assertEquals(expPath, computer.getShortestPathTo(i));
    }

}
