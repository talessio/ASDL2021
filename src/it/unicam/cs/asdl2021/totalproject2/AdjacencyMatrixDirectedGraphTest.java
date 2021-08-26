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
    MapAdjacentListUndirectedGraph<String> undirectedGraph = new MapAdjacentListUndirectedGraph<>();
    GraphNode<String> node1 = new GraphNode<>("1");
    GraphNode<String> node2 = new GraphNode<>("2");
    GraphNode<String> node3 = new GraphNode<>("3");
    GraphNode<String> node4 = new GraphNode<>("4");
    GraphNode<String> node5 = new GraphNode<>("5");
    GraphEdge<String> edge1 = new GraphEdge<>(node1, node2, true);
    GraphEdge<String> edge2 = new GraphEdge<>(node2, node1, true);
    GraphEdge<String> edge3 = new GraphEdge<>(node3, node1, true);
    GraphEdge<String> edge4 = new GraphEdge<>(node3, node5, false);

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
        assertEquals(0, graph.nodesIndex.size());
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        assertEquals(3, graph.nodesIndex.size());
        graph.clear();
        assertEquals(0, graph.nodesIndex.size());
        assertEquals(0, graph.matrix.size());
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
        Set<GraphNode<String>> graphNodeSet = new HashSet<>();
        assertThrows(NullPointerException.class, () -> graph.addNode(null));
        graph.addNode(node1);
        assertFalse(graph.addNode(node1));
        graph.addNode(node2);
        graph.addNode(node3);
        graphNodeSet.add(node1);
        graphNodeSet.add(node2);
        graphNodeSet.add(node3);
        assertEquals(graphNodeSet, graph.getNodes());
    }

    @Test
    final void testRemoveNode() {
        graph.addNode(node1);
        assertThrows(UnsupportedOperationException.class, () -> graph.removeNode(node1));
    }

    @Test
    final void testContainsNode() {
        graph.addNode(node1);
        assertThrows(NullPointerException.class, () -> graph.addNode(null));
        assertTrue(graph.containsNode(node1));
        assertFalse(graph.containsNode(node3));
    }

    @Test
    final void testGetNodeOf() {
        graph.addNode(node4);
        graph.addNode(node3);
        assertThrows(NullPointerException.class, () -> graph.getNodeOf(null));
        assertEquals(node4, graph.getNodeOf("4"));
        assertEquals(node3, graph.getNodeOf("3"));
    }

    @Test
    final void testGetNodeIndexOf() {
        graph.addNode(node4);
        graph.addNode(node3);
        assertNull(graph.getNodeOf("5"));
        assertThrows(NullPointerException.class, () -> graph.getNodeIndexOf(null));
        assertEquals(0, graph.getNodeIndexOf("4"));
        assertEquals(1, graph.getNodeIndexOf("3"));
    }

    @Test
    final void testGetNodeAtIndex() {
        graph.addNode(node1);
        graph.addNode(node2);

        assertEquals(node1, graph.getNodeAtIndex(0));
        assertEquals(node2, graph.getNodeAtIndex(1));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.getNodeAtIndex(2));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.getNodeAtIndex(-1));
    }

    @Test
    final void testGetEdge() {
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        assertThrows(IllegalArgumentException.class, () -> graph.getEdge(node3, node1));
        graph.addNode(node3);
        graph.addEdge(edge3);
        assertThrows(NullPointerException.class, () -> graph.getEdge(null, node2));
        assertNotEquals(graph.getEdge(node1, node2), graph.getEdge(node2, node1)); //TODO fix this
        assertEquals(edge1, graph.getEdge(node1, node2));
        assertEquals(edge2, graph.getEdge(node2, node1));
    }

    @Test
    final void testGetEdgeAtNodeIndexes() {
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        assertEquals(edge1, graph.getEdgeAtNodeIndexes(0, 1));
        assertEquals(edge2, graph.getEdgeAtNodeIndexes(1, 0));
        assertNotEquals(edge2, graph.getEdgeAtNodeIndexes(0, 1));
        assertNull(graph.getEdgeAtNodeIndexes(1, 2));
        assertNull(graph.getEdgeAtNodeIndexes(2, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.getEdgeAtNodeIndexes(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.getEdgeAtNodeIndexes(1, 3));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        assertThrows(NullPointerException.class, () -> graph.getAdjacentNodesOf(null));
        assertThrows(IllegalArgumentException.class, () -> graph.getAdjacentNodesOf(node4));
        Set<GraphNode<String>> setAdjN1 = new HashSet<>();
        setAdjN1.add(node2);
        assertEquals(setAdjN1, graph.getAdjacentNodesOf(node1));
    }

    @Test
    final void testGetPredecessorNodesOf() {
        Set<GraphNode<String>> graphNodesSet = new HashSet<>();
        graph.addNode(node1);
        graph.addNode(node2);
        assertThrows(IllegalArgumentException.class, () -> graph.getPredecessorNodesOf(node3));
        graph.addNode(node3);
        assertThrows(NullPointerException.class, () -> graph.getPredecessorNodesOf(null));
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graphNodesSet.add(node2);
        graphNodesSet.add(node3);
        assertEquals(graphNodesSet, graph.getPredecessorNodesOf(node1));
        assertEquals(0, graph.getPredecessorNodesOf(node3).size());
    }

    @Test
    final void testGetEdges() {
        Set<GraphEdge<String>> graphEdgeSet = new HashSet<>();
        assertEquals(graphEdgeSet, graph.getEdges());
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graphEdgeSet.add(edge1);
        graphEdgeSet.add(edge2);
        graphEdgeSet.add(edge3);
        assertEquals(graphEdgeSet, graph.getEdges());
    }

    @Test
    final void testAddEdge() {
        assertThrows(NullPointerException.class, () -> graph.addEdge(null));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge1));
        GraphEdge<String> undirectedEdge = new GraphEdge<>(node1, node4, false);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(undirectedEdge));
        graph.addNode(node1);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge1));
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        assertFalse(graph.addEdge(edge1));
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        assertEquals(3, graph.getEdges().size());
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
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        assertThrows(NullPointerException.class, () -> graph.containsEdge(null));
        assertThrows(IllegalArgumentException.class, () -> graph.containsEdge(edge3));
        graph.addNode(node3);
        assertTrue(graph.containsEdge(edge1));
        assertTrue(graph.containsEdge(edge2));
        assertFalse(graph.containsEdge(edge3));
        graph.removeEdge(edge1);
        assertFalse(graph.containsEdge(edge1));
    }

    @Test
    final void testGetEdgesOf() {
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        assertThrows(NullPointerException.class, () -> graph.getEdgesOf(null));
        assertThrows(IllegalArgumentException.class, () -> graph.getEdgesOf(node3));
        Set<GraphEdge<String>> edgesOfN1 = new HashSet<>();
        edgesOfN1.add(edge1);
        assertEquals(edgesOfN1, graph.getEdgesOf(node1));
    }

    @Test
    final void testGetIngoingEdgesOf() {
        undirectedGraph.addNode(node3);
        undirectedGraph.addNode(node5);
        undirectedGraph.addEdge(edge4);
        assertThrows(UnsupportedOperationException.class, () -> undirectedGraph.getIngoingEdgesOf(node5));
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        assertThrows(IllegalArgumentException.class, () -> graph.getIngoingEdgesOf(node4));
        assertThrows(NullPointerException.class, () -> graph.getIngoingEdgesOf(null));
        assertEquals(2, graph.getIngoingEdgesOf(node1).size());
        assertEquals(1, graph.getIngoingEdgesOf(node2).size());
        assertEquals(0, graph.getIngoingEdgesOf(node3).size());
    }

    @Test
    final void testAdjacencyMatrixDirectedGraph() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testSize() {
        assertEquals(0, graph.size());
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        assertEquals(3, graph.size());
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        assertEquals(6, graph.size());
        graph.removeEdge(edge1);
        graph.removeEdge(edge2);
        assertEquals(4, graph.size());
    }

    @Test
    final void testIsEmpty() {
        assertTrue(graph.isEmpty());
        graph.addNode(node1);
        assertFalse(graph.isEmpty());
    }

    @Test
    final void testGetDegreeOf() {
        assertThrows(NullPointerException.class, () -> graph.getDegreeOf(null));
        assertThrows(IllegalArgumentException.class, () -> graph.getDegreeOf(node1));
        graph.addNode(node1);
        assertEquals(0, graph.getDegreeOf(node1));
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        assertEquals(3, graph.getDegreeOf(node1));
        assertEquals(2, graph.getDegreeOf(node2));
        assertEquals(1, graph.getDegreeOf(node3));
    }

}
