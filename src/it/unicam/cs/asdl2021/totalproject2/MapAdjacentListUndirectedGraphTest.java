package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Template: Luca Tesei
 */
class MapAdjacentListUndirectedGraphTest {

    // TODO implementare tutti i test non ancora implementati

    @Test
    final void testNodeCount() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testEdgeCount() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testClear() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testIsDirected() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetNodes() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testAddNode() {
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        map.addNode(node1);
        map.addNode(node2);
        assertEquals(node1, map.getNodeOf("1"));
        assertEquals(node2, map.getNodeOf("2"));
        assertThrows(NullPointerException.class, () -> map.addNode(null));
    }

    @Test
    final void testRemoveNode() {
        fail("Not yet implemented"); // TODO
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
        //TODO
        MapAdjacentListUndirectedGraph<String> map = new MapAdjacentListUndirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("1");
        GraphNode<String> node2 = new GraphNode<>("2");
        GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, false);
        map.addNode(node1);
        map.addNode(node2);
        map.addEdge(edge1);
        assertEquals(edge1, map.getEdge(node1, node2));
        assertEquals(edge1, map.getEdge(node2, node1));
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
