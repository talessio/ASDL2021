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
        //es TODO el dia que estoy intentando hacer que esta mierda funcione
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphNode<String> node3 = new GraphNode<>("3");
        GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, false);
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node3, false);
        GraphEdge<String> edge3 = new GraphEdge<>(node3, node1, false);
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
        assertEquals(1, map.getEdgesOf(node2).size());
        map.removeNode(node3);
        assertEquals(0, map.getEdgesOf(node2).size());
    }

    @Test
    final void testContainsNode() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        map.addNode(node1);
        assertThrows(NullPointerException.class, () -> map.containsNode(null));
        assertTrue(map.containsNode(node1));
        assertFalse(map.containsNode(node2));
    }

    @Test
    final void testGetNodeOf() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        assertThrows(NullPointerException.class, () -> map.getNodeOf(null));
        GraphNode<String> node1 = new GraphNode<>("primo");
        map.addNode(node1);
        assertEquals(node1, map.getNodeOf("primo"));
        assertNull(map.getNodeOf("1"));
    }

    @Test
    final void testGetNodeIndexOf() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        assertThrows(UnsupportedOperationException.class, () -> map.getNodeIndexOf("etichetta"));
    }

    @Test
    final void testGetNodeAtIndex() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        assertThrows(UnsupportedOperationException.class, () -> map.getNodeAtIndex(1));
    }

    @Test
    final void testGetEdge() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphNode<String> node3 = new GraphNode<>("3");
        GraphNode<String> node4 = new GraphNode<>("4");
        map.addNode(node1);
        map.addNode(node2);
        map.addNode(node3);
        GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, false);
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node3, false);
        GraphEdge<String> edge3 = new GraphEdge<>(node3, node1, false);
        assertThrows(NullPointerException.class, () -> map.getEdge(node1, null));
        assertThrows(NullPointerException.class, () -> map.getEdge(null, null));
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
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        assertThrows(UnsupportedOperationException.class, () -> map.getEdgeAtNodeIndexes(1, 2));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetPredecessorNodesOf() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        assertThrows(UnsupportedOperationException.class, () -> map.getPredecessorNodesOf(node1));
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
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        assertThrows(NullPointerException.class, () -> map.getEdgesOf(null));
        assertThrows(IllegalArgumentException.class, () -> map.getEdgesOf(node1));
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
        Set<GraphEdge<String>> set = new HashSet<>();
        set.add(edge1);
        set.add(edge3);
        assertEquals(set, map.getEdgesOf(node1));
        map.removeNode(node2);
        set.remove(edge1);
        assertEquals(set, map.getEdgesOf(node1));
        set.remove(edge3);

        assertEquals(set, map.getEdgesOf(node1));
    }

    @Test
    final void testGetIngoingEdgesOf() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("whatever");
        assertThrows(UnsupportedOperationException.class, () -> map.getIngoingEdgesOf(node1));
    }

    @Test
    final void testMapAdjacentListUndirectedGraph() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        GraphNode<String> node1 = new GraphNode<>("1");
        map.addNode(node1);
        assertFalse(map.isEmpty());
        assertFalse(map.isDirected());
    }

}
