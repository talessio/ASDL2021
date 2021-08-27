package it.unicam.cs.asdl2021.totalproject2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 *
 * @author Template: Luca Tesei
 *
 */
class BinaryHeapMinPriorityQueueTest {

    // TODO implementare tutti i test non ancora implementati

    BinaryHeapMinPriorityQueue h = new BinaryHeapMinPriorityQueue();
    GraphNode<String> node1 = new GraphNode<>("1");
    GraphNode<String> node2 = new GraphNode<>("2");
    GraphNode<String> node3 = new GraphNode<>("3");
    GraphNode<String> node4 = new GraphNode<>("4");
    GraphNode<String> node5 = new GraphNode<>("5");

    @Test
    final void testBinaryHeapMinPriorityQueue() {
        assertTrue(this.h.isEmpty());
    }

    @Test
    final void testInsert() {
        assertThrows(NullPointerException.class, () -> h.insert(null));
        node1.setPriority(1);
        node2.setPriority(3);
        node3.setPriority(5);
        node4.setPriority(10);
        node5.setPriority(22);
        h.insert(node2);
        assertEquals(1, h.size());
        h.insert(node1);
        assertEquals(2, h.size());
        assertEquals(0, node1.getHandle());
        assertEquals(1, node2.getHandle());
        h.insert(node5);
        h.insert(node4);
        h.insert(node3);
        assertEquals(5, h.size());
        assertEquals(0, node1.getHandle());
        assertEquals(1, node2.getHandle());
        assertEquals(2, node5.getHandle());
    }

    @Test
    final void testMinimum() {
        assertThrows(NoSuchElementException.class, () -> h.minimum());
        node2.setPriority(90);
        h.insert(node2);
        node3.setPriority(22);
        h.insert(node3);
        node4.setPriority(45);
        h.insert(node4);
        node5.setPriority(4);
        h.insert(node5);
        PriorityQueueElement min = h.minimum();
        assertEquals(4.0, min.getPriority());
        assertEquals(min, node5);
        assertEquals(0.0, min.getHandle());
        assertTrue(node5.getHandle() == 0);
        assertFalse(node5.getHandle() == 1);
        node1.setPriority(3.0);
        h.insert(node1);
        PriorityQueueElement min2 = h.minimum();
        assertEquals(3.0, min2.getPriority());
        assertEquals(min2, node1);
        assertEquals(0.0, min2.getHandle());
    }

    @Test
    final void testExtractMinimum() {
        assertThrows(NoSuchElementException.class, () -> h.extractMinimum());
        node2.setPriority(90);
        h.insert(node2);
        node3.setPriority(22);
        h.insert(node3);
        node4.setPriority(45);
        h.insert(node4);
        node5.setPriority(4);
        h.insert(node5);

        PriorityQueueElement min = h.minimum();
        assertEquals(4.0, min.getPriority());
        assertEquals(min, node5);
        assertEquals(0.0, min.getHandle());
        assertEquals(node5, h.extractMinimum());
        assertEquals(h.minimum(), node3);
        h.extractMinimum();
        assertEquals(h.minimum(), node4);
    }

    @Test
    final void testDecreasePriority() {
        node1.setPriority(2);
        node2.setPriority(1);
        node3.setPriority(4);
        node4.setPriority(3);
        h.insert(node1);
        h.insert(node3);
        h.insert(node4);
        h.insert(node2);
        assertEquals(1, node2.getPriority());
        assertEquals(2, node1.getPriority());
        assertEquals(node2, h.minimum());
        h.decreasePriority(node1, 0);
        assertEquals(0, node1.getPriority());
        assertEquals(1, node2.getPriority());
        assertTrue(h.minimum().equals(node1));
    }

    @Test
    final void testIsEmpty() {
        assertTrue(h.isEmpty());
        h.insert(node1);
        assertFalse(h.isEmpty());
        h.clear();
        assertTrue(h.isEmpty());
    }

    @Test
    final void testSize() {
        assertEquals(0, this.h.size());
        this.h.insert(node1);
        assertEquals(1, this.h.size());
        this.h.insert(node2);
        assertEquals(2, this.h.size());
        this.h.insert(node3);
        assertEquals(3, this.h.size());
        this.h.extractMinimum();
        assertEquals(2, this.h.size());
        this.h.extractMinimum();
        assertEquals(1, this.h.size());
    }

    @Test
    final void testClear() {
        h.insert(node2);
        h.insert(node1);
        h.insert(node5);
        assertEquals(3, this.h.size());
        this.h.clear();
        assertTrue(this.h.isEmpty());
        assertEquals(0, this.h.size());
    }

}
