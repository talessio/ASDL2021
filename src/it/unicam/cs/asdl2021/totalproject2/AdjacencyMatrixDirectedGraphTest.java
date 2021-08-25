package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        graph.addNode(node1);
        graph.addNode(node2);
        assertEquals(0, graph.edgeCount());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        assertEquals(2, graph.edgeCount());
        graph.addNode(node3);
        graph.addEdge(edge3);
        assertEquals(3, graph.edgeCount());
    }

    @Test
    final void testClear() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testIsDirected() {
        assertTrue(graph.isDirected());
    }

    @Test
    final void testGetNodes() {
        Set<GraphNode<String>> graphNodeSet = new HashSet<>();
        assertEquals(graphNodeSet, graph.getNodes());
        graph.addNode(node1);
        graph.addNode(node2);
        graphNodeSet.add(node1);
        graphNodeSet.add(node2);
        assertEquals(graphNodeSet, graph.getNodes());
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
        Set<GraphEdge<String>> graphEdgeSet = new HashSet<>();
        assertEquals(graphEdgeSet, graph.getEdges());
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graphEdgeSet.add(edge1);
        graphEdgeSet.add(edge2);
        assertEquals(graphEdgeSet, graph.getEdges());
    }

    @Test
    final void testAddEdge() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testRemoveEdge() {
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        assertEquals(2, graph.getEdges().size());
        graph.removeEdge(edge1);
        assertEquals(1, graph.getEdges().size());
        graph.removeEdge(edge2);
        assertEquals(0, graph.getEdges().size());
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
