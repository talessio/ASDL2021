package it.unicam.cs.asdl2021.totalproject2;

//import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author Template: Luca Tesei
 */
class DijkstraShortestPathComputerTest {

    Graph<String> graph = null;

    @Test
    final void testThrownExceptions() {
        assertThrows(NullPointerException.class, () -> new DijkstraShortestPathComputer<>(graph));
        graph = new MapAdjacentListUndirectedGraph<>();
        assertThrows(IllegalArgumentException.class, () -> new DijkstraShortestPathComputer<>(graph));
        GraphNode<String> node1 = new GraphNode<String>("1");
        GraphNode<String> node2 = new GraphNode<String>("2");
        GraphEdge<String> edge1 = new GraphEdge<String>(node1, node2, false);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge1);
        assertThrows(IllegalArgumentException.class, () -> new DijkstraShortestPathComputer<>(graph));
        graph = new AdjacencyMatrixDirectedGraph<>();
        graph.addNode(node1);
        graph.addNode(node2);
        edge1 = new GraphEdge<String>(node1, node2, true);
        graph.addEdge(edge1);
        assertThrows(IllegalArgumentException.class, () -> new DijkstraShortestPathComputer<>(graph));
    }

    @Test
    final void testLastSourceAndIsComputed() {
        GraphNode<String> node1 = new GraphNode<String>("1");
        GraphNode<String> node2 = new GraphNode<String>("2");
        GraphNode<String> node3 = new GraphNode<String>("3");
        GraphNode<String> node4 = new GraphNode<String>("4");
        GraphEdge<String> edge1 = new GraphEdge<String>(node1, node2, true, 0.89);
        GraphEdge<String> edge2 = new GraphEdge<String>(node1, node3, true, 564.9);
        GraphEdge<String> edge3 = new GraphEdge<String>(node2, node4, true, 94.65);
        GraphEdge<String> edge4 = new GraphEdge<String>(node3, node2, true, 9.465);
        GraphEdge<String> edge5 = new GraphEdge<String>(node4, node1, true, 9.465);
        graph = new AdjacencyMatrixDirectedGraph<>();
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        DijkstraShortestPathComputer<String> comp = new DijkstraShortestPathComputer<String>(graph);
        assertFalse(comp.isComputed());
        assertThrows(IllegalStateException.class, () -> comp.getLastSource());
        comp.computeShortestPathsFrom(node3);
        assertEquals(node3, comp.getLastSource());
        assertTrue(comp.isComputed());
    }

    @Test
    final void testGetShortestPathTo1() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, true, 7.03);
        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<String>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<String>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<String>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<String>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<String>(nv, ny, true, 4.07);
        g.addEdge(evy);
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(g);
        GraphNode<String> nsTest = new GraphNode<String>("s");
        c.computeShortestPathsFrom(nsTest);
        List<GraphEdge<String>> pathTest = new ArrayList<GraphEdge<String>>();
        assertTrue(c.getShortestPathTo(nsTest).equals(pathTest));
        GraphNode<String> nuTest = new GraphNode<String>("u");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        GraphEdge<String> esxTest = new GraphEdge<String>(nsTest, nxTest, true, 5.12);
        pathTest.add(esxTest);
//        assertTrue(c.getShortestPathTo(nxTest).equals(pathTest));
        assertEquals(pathTest, c.getShortestPathTo(nxTest));
        GraphEdge<String> exuTest = new GraphEdge<String>(nxTest, nuTest, true, 3.04);
        pathTest.add(exuTest);
        assertEquals(c.getShortestPathTo(nuTest), pathTest);
        GraphNode<String> nvTest = new GraphNode<String>("v");
        GraphEdge<String> euvTest = new GraphEdge<String>(nuTest, nvTest, true, 1.0);
        pathTest.add(euvTest);
        assertEquals(c.getShortestPathTo(nvTest), pathTest);
        pathTest.clear();
        pathTest.add(esxTest);
        GraphNode<String> nyTest = new GraphNode<String>("y");
        GraphEdge<String> exyTest = new GraphEdge<String>(nxTest, nyTest, true, 2.0);
        pathTest.add(exyTest);
        assertEquals(c.getShortestPathTo(nyTest), pathTest);
    }

    @Test
    final void testGetShortestPathTo2() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, true, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, true, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, true, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, true, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, true, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, true, 7.03);
        g.addEdge(eys);
        GraphNode<String> nv = new GraphNode<String>("v");
        g.addNode(nv);
        GraphEdge<String> euv = new GraphEdge<String>(nu, nv, true, 1.0);
        g.addEdge(euv);
        GraphEdge<String> exv = new GraphEdge<String>(nx, nv, true, 9.05);
        g.addEdge(exv);
        GraphEdge<String> eyv = new GraphEdge<String>(ny, nv, true, 6.0);
        g.addEdge(eyv);
        GraphEdge<String> evy = new GraphEdge<String>(nv, ny, true, 4.07);
        g.addEdge(evy);
        GraphNode<String> np = new GraphNode<String>("p");
        g.addNode(np);
        GraphEdge<String> epv = new GraphEdge<String>(np, nv, true, 1.0);
        g.addEdge(epv);
        // p è collegato a v, ma non è raggiungibile da s
        DijkstraShortestPathComputer<String> c = new DijkstraShortestPathComputer<String>(g);
        GraphNode<String> nsTest = new GraphNode<String>("s");
        c.computeShortestPathsFrom(nsTest);
        GraphNode<String> npTest = new GraphNode<String>("p");
//        assertTrue(c.getShortestPathTo(npTest) == null);
        assertNull(c.getShortestPathTo(npTest));
    }
}
