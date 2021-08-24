package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Template: Luca Tesei
 */
class AdjacencyMatrixDirectedGraphTest {

    // TODO implementare tutti i test non ancora implementati

    AdjacencyMatrixDirectedGraph<String> graph = new AdjacencyMatrixDirectedGraph<>();
    GraphNode<String> node1 = new GraphNode<>("1");
    GraphNode<String> node2 = new GraphNode<>("2");
    GraphNode<String> node3 = new GraphNode<>("3");
    GraphNode<String> node4 = new GraphNode<>("4");
    GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, true);
    GraphEdge<String> edge2 = new GraphEdge<>(node2, node1, true);
    GraphEdge<String> edge3 = new GraphEdge<>(node3, node1, true);

    @Test
    final void testNodeCount() {
        assertEquals(0, graph.nodeCount());
        graph.addNode(node1);
        graph.addNode(node2);
        assertNotEquals(0, graph.nodeCount());
        assertEquals(2, graph.nodeCount());
        graph.addNode(node3);
        assertEquals(3, graph.nodeCount());
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
        fail("Not yet implemented"); // TODO
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
        fail("Not yet implemented"); // TODO
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
    final void testAdjacencyMatrixDirectedGraph() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testSize() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testIsEmpty() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testGetDegreeOf() {
        fail("Not yet implemented"); // TODO
    }

}
