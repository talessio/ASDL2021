package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Template: Luca Tesei
 */
class MapAdjacentListUndirectedGraphTest {

    // TODO FIX removeNode() and getEdgesOf()

    MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
    GraphNode<String> node1 = new GraphNode<>("1");
    GraphNode<String> node2 = new GraphNode<>("2");
    GraphNode<String> node3 = new GraphNode<>("3");
    GraphNode<String> node4 = new GraphNode<>("4");
    GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, false);
    GraphEdge<String> edge2 = new GraphEdge<>(node2, node3, false);
    GraphEdge<String> edge3 = new GraphEdge<>(node3, node1, false);

    @Test
    final void testNodeCount() {
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
        assertEquals(0, map.edgeCount());
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
        assertEquals(3, map.edgeCount());
        map.removeEdge(edge1);
        assertEquals(2, map.edgeCount()); //TODO fix assertion failed
        map.removeEdge(edge2);
        assertEquals(1, map.edgeCount());
        map.removeEdge(edge3);
        assertEquals(0, map.edgeCount());
    }

    @Test
    final void testClear() {
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

        map.addNode(node1);
        map.addNode(node2);
        assertFalse(map.removeNode(node3));
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);

        assertEquals(2, map.getEdgesOf(node2).size());
        map.removeNode(node1);      // errore qui, non ho idea del perché
        assertFalse(map.removeNode(node1));
        assertFalse(map.containsEdge(edge1));
        assertTrue(map.containsEdge(edge2)); //assertion failed
        assertFalse(map.containsEdge(edge3));
        assertEquals(1, map.getEdgesOf(node2).size());
        map.removeNode(node3);
        assertEquals(0, map.getEdgesOf(node2).size());
    }

    @Test
    final void testContainsNode() {
        map.addNode(node1);
        assertThrows(NullPointerException.class, () -> map.containsNode(null));
        assertTrue(map.containsNode(node1));
        assertFalse(map.containsNode(node2));
    }

    @Test
    final void testGetNodeOf() {
        assertThrows(NullPointerException.class, () -> map.getNodeOf(null));
        map.addNode(node1);
        assertEquals(node1, map.getNodeOf("1"));
        map.removeNode(node1);
        assertNull(map.getNodeOf("1"));
    }

    @Test
    final void testGetNodeIndexOf() {
        assertThrows(UnsupportedOperationException.class, () -> map.getNodeIndexOf("etichetta"));
    }

    @Test
    final void testGetNodeAtIndex() {
        assertThrows(UnsupportedOperationException.class, () -> map.getNodeAtIndex(1));
    }

    @Test
    final void testGetEdge() {
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);

        assertThrows(NullPointerException.class, () -> map.getEdge(null, null));
        assertThrows(NullPointerException.class, () -> map.getEdge(node1, null));
        assertThrows(IllegalArgumentException.class, () -> map.getEdge(node2, node4));

        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
        map.addNode(node4);
        assertNull(map.getEdge(node1, node4));
        assertNull(map.getEdge(node2, node4));
        assertEquals(edge3, map.getEdge(node1, node3));
        assertEquals(edge1, map.getEdge(node1, node2));
        assertEquals(edge2, map.getEdge(node3, node2));
    }

    @Test
    final void testGetEdgeAtNodeIndexes() {
        assertThrows(UnsupportedOperationException.class, () -> map.getEdgeAtNodeIndexes(1, 2));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
        assertEquals(2, map.getAdjacentNodesOf(node1).size());
        assertTrue(map.getAdjacentNodesOf(node1).contains(node2));
        assertTrue(map.getAdjacentNodesOf(node1).contains(node3));
    }

    @Test
    final void testGetPredecessorNodesOf() {
        assertThrows(UnsupportedOperationException.class, () -> map.getPredecessorNodesOf(node1));
    }

    @Test
    final void testGetEdges() {

        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        assertEquals(0, map.getEdges().size());
        map.addEdge(edge1);
        map.addEdge(edge2);
        assertEquals(2, map.getEdges().size());
    }

    @Test
    final void testAddEdge() {

        GraphEdge<String> edge4 = new GraphEdge<>(node1, node4, true);
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        map.addNode(node4);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
        assertThrows(IllegalArgumentException.class, () -> map.addEdge(edge4));
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
        assertThrows(NullPointerException.class, () -> map.removeEdge(null));
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        assertThrows(IllegalArgumentException.class, () -> map.removeEdge(edge1));
        assertThrows(IllegalArgumentException.class, () -> map.removeEdge(edge2));
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
        assertFalse(map.isEmpty());
        assertEquals(2, map.getEdgesOf(node1).size());
        assertEquals(2, map.getEdgesOf(node2).size());
        assertEquals(2, map.getEdgesOf(node3).size());
        map.removeEdge(edge1);
        map.removeEdge(edge2);
        assertTrue(map.getEdgesOf(node2).isEmpty());
        map.removeEdge(edge3);
        assertTrue(map.getEdgesOf(node1).isEmpty());
        assertTrue(map.getEdgesOf(node3).isEmpty());
    }

    @Test
    final void testContainsEdge() {
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        assertTrue(map.containsEdge(edge1));
        assertTrue(map.containsEdge(edge2));
        assertFalse(map.containsEdge(edge3));
    }

    @Test
    final void testGetEdgesOf() {
        assertThrows(NullPointerException.class, () -> map.getEdgesOf(null));
        assertThrows(IllegalArgumentException.class, () -> map.getEdgesOf(node1));

        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        map.addEdge(edge1);
        map.addEdge(edge2);
        map.addEdge(edge3);
//        Set<GraphEdge<String>> set = new HashSet<>();
//        set.add(edge1);
//        set.add(edge3);
//        assertEquals(set, map.getEdgesOf(node1));
//        map.removeNode(node2); //TODO fix ConcurrentModificationException
//        set.remove(edge1);
//        assertEquals(set, map.getEdgesOf(node1));
//        set.remove(edge3);

        assertEquals(2, map.getEdgesOf(node1).size());
        map.removeEdge(edge1);
        map.removeEdge(edge2);
        assertEquals(1, map.getEdgesOf(node1).size());
        map.removeEdge(edge3);
        assertEquals(0, map.getEdgesOf(node1).size());
        assertTrue(map.getEdgesOf(node1).isEmpty());
    }

    @Test
    final void testGetIngoingEdgesOf() {
        assertThrows(UnsupportedOperationException.class, () -> map.getIngoingEdgesOf(node1));
    }

    @Test
    final void testMapAdjacentListUndirectedGraph() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        map.addNode(node1);
        assertFalse(map.isEmpty());
        assertFalse(map.isDirected());
    }
}