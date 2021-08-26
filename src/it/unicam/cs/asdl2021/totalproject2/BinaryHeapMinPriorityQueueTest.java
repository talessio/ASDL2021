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
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testInsert() {
        assertThrows(NullPointerException.class, () -> h.insert(null));
        node1.setPriority(0);
        node2.setPriority(1);
        node3.setPriority(2);
        node4.setPriority(3);
        node5.setPriority(4);
        h.insert(node2);
        assertEquals(1, h.size());
        h.insert(node1);
        assertEquals(2, h.size());
        assertEquals(1, node1.getHandle());
        assertEquals(0, node2.getHandle());
        h.insert(node5);
        assertEquals(3, h.size());
        assertEquals(1, node1.getHandle());
        assertEquals(0, node2.getHandle());
        assertEquals(2, node5.getHandle());
    }

    @Test
    final void testMinimum() {
        assertThrows(NoSuchElementException.class, () -> h.minimum());
        node1.setPriority(3);
        node2.setPriority(90);
        node3.setPriority(22);
        node4.setPriority(45);
        node5.setPriority(4);
        h.insert(node2);
        h.insert(node3);
        h.insert(node4);
        h.insert(node5);
        PriorityQueueElement min = h.minimum();
        assertEquals(4, min.getPriority());
        assertEquals(min, node5);
        assertEquals(0, min.getHandle());
        h.insert(node1);
        PriorityQueueElement min2 = h.minimum();
        assertEquals(3, min2.getPriority());
        assertEquals(min2, node1);
        assertEquals(0, min2.getHandle());
    }

    @Test
    final void testExtractMinimum() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    final void testDecreasePriority() {
        fail("Not yet implemented"); // TODO
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
        this.h.clear();
        assertTrue(this.h.isEmpty());
        assertEquals(0, this.h.size());
    }

}
