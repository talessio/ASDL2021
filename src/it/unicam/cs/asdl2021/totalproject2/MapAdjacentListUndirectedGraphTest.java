package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * @author Template: Luca Tesei
 */
class MapAdjacentListUndirectedGraphTest {

    @Test
    final void testNodeCount() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphNode<String> node3 = new GraphNode<>("3");
        assertEquals(0, map.nodeCount());
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        assertEquals(3, map.nodeCount());
        map.removeNode(node1);
        assertEquals(2, map.nodeCount());
        map.removeNode(node2);
        assertEquals(1, map.nodeCount());
        map.removeNode(node3);
        assertEquals(0, map.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphNode<String> node3 = new GraphNode<>("3");
        GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, false);
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node3, false);
        GraphEdge<String> edge3 = new GraphEdge<>(node3, node1, false);
        assertEquals(0, map.edgeCount());
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
        assertEquals(3, map.edgeCount());
        map.removeEdge(edge1);
        assertEquals(2, map.edgeCount());
        map.removeEdge(edge2);
        assertEquals(1, map.edgeCount());
        map.removeEdge(edge3);
        assertEquals(0, map.edgeCount());
    }

    @Test
    final void testClear() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphNode<String> node3 = new GraphNode<>("3");
        GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, false);
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node3, false);
        GraphEdge<String> edge3 = new GraphEdge<>(node3, node1, false);
        assertEquals(0, map.edgeCount());
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
        map.clear();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    final void testIsDirected() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        assertFalse(map.isDirected());
    }

    @Test
    final void testGetNodes() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphNode<String> node3 = new GraphNode<>("3");
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        HashSet<GraphNode<String>> set = new HashSet<>();
        set.add(node1);
        set.add(node2);
        set.add(node3);
        assertEquals(set, map.getNodes());
        assertEquals(3, map.getNodes().size());
        map.clear();
        assertEquals(0, map.getNodes().size());
    }

    @Test
    final void testAddNode() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphNode<String> node3 = new GraphNode<>("3");
        assertTrue(map.addNode(node1));
        assertTrue(map.addNode(node2));
        assertTrue(map.addNode(node3));
        assertEquals(3, map.nodeCount());
        assertFalse(map.addNode(node1));
        assertFalse(map.addNode(node2));
        assertFalse(map.addNode(node3));
        assertEquals(3, map.nodeCount());
        assertEquals(node1, map.getNodeOf("1"));
        assertEquals(node2, map.getNodeOf("2"));
        assertEquals(node3, map.getNodeOf("3"));
        assertEquals(0, map.getEdgesOf(node1).size());
        assertEquals(0, map.getEdgesOf(node2).size());
        assertEquals(0, map.getEdgesOf(node3).size());
        assertThrows(NullPointerException.class, () -> map.addNode(null));
    }

    @Test
    final void testRemoveNode() {
        // TODO questo è più lungo e difficile
    }

    @Test
    final void testContainsNode() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetNodeOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetNodeIndexOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetNodeAtIndex() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetEdge() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetEdgeAtNodeIndexes() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetAdjacentNodesOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetPredecessorNodesOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetEdges() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testAddEdge() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphNode<String> node3 = new GraphNode<>("3");
        GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, false);
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node3, false);
        GraphEdge<String> edge3 = new GraphEdge<>(node3, node1, false);
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
        assertEquals(edge1, map.getEdge(node1, node2));
        assertEquals(edge1, map.getEdge(node2, node1));
        assertEquals(edge2, map.getEdge(node2, node3));
        assertEquals(edge2, map.getEdge(node3, node2));
        assertEquals(edge3, map.getEdge(node3, node1));
        assertEquals(edge3, map.getEdge(node1, node3));
        assertEquals(2, map.getEdgesOf(node1).size());
        assertEquals(2, map.getEdgesOf(node2).size());
        assertEquals(2, map.getEdgesOf(node3).size());

        assertThrows(NullPointerException.class, () -> map.addEdge(null));
    }

    @Test
    final void testRemoveEdge() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testContainsEdge() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetEdgesOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetIngoingEdgesOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testMapAdjacentListUndirectedGraph() {
        fail("Not yet implemented"); // TODO
    }

}
