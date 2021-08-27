package it.unicam.cs.asdl2021.totalproject2;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Implementazione di una coda con priorità tramite heap binario. Gli oggetti
 * inseriti in coda implementano l'interface PriorityQueueElement che permette
 * di gestire la priorità e una handle dell'elemento. La handle è fondamentale
 * per realizzare in tempo logaritmico l'operazione di decreasePriority che,
 * senza la handle, dovrebbe cercare l'elemento all'interno dello heap e poi
 * aggiornare la sua posizione. Nel caso di heap binario rappresentato con una
 * ArrayList la handle è semplicemente l'indice dove si trova l'elemento
 * nell'ArrayList. Tale campo naturalmente va tenuto aggiornato se l'elemento
 * viene spostato in un'altra posizione.
 *
 * @author Template: Luca Tesei
 *
 * @param <E>
 *             il tipo degli elementi che vengono inseriti in coda.
 *
 */
public class BinaryHeapMinPriorityQueue {

    /*
     * ArrayList per la rappresentazione dello heap. Vengono usate tutte le
     * posizioni (la radice dello heap è quindi in posizione 0).
     */
    private ArrayList<PriorityQueueElement> heap;

    // TODO implementare: inserire eventuali altre variabili istanza

    /**
     * Crea una coda con priorità vuota.
     *
     */
    public BinaryHeapMinPriorityQueue() {
        this.heap = new ArrayList<>();
    }

    /**
     * Add an element to this min-priority queue. The current priority
     * associated with the element will be used to place it in the correct
     * position in the heap. The handle of the element will also be set
     * accordingly.
     *
     * @param element
     *                    the new element to add
     * @throws NullPointerException
     *                                  if the element passed is null
     */
    public void insert(PriorityQueueElement element) {
        if (element == null)
            throw new NullPointerException();

        this.heap.add(element);
        element.setHandle(this.heap.size()-1);
//        this.heap.get(this.heap.size() - 1).setHandle(this.heap.size() - 1);
        heapify(element.getHandle());
    }


    /**
     * Returns the current minimum element of this min-priority queue without
     * extracting it. This operation does not affect the heap.
     *
     * @return the current minimum element of this min-priority queue
     *
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement minimum() {
        if (this.heap.isEmpty())
            throw new NoSuchElementException();
        return this.heap.get(0);
    }

    /**
     * Extract the current minimum element from this min-priority queue. The
     * ternary heap will be updated accordingly.
     *
     * @return the current minimum element
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement extractMinimum() {
        if (this.heap.isEmpty())
            throw new NoSuchElementException();

        PriorityQueueElement result = this.minimum();
        this.heap.remove(0);
        int i = 0;
        for (PriorityQueueElement currentElement : this.heap) {
            currentElement.setHandle(currentElement.getHandle() - 1);
            this.heap.set(i, currentElement);
            i++;
        }
        return result;
    }

    /**
     * Decrease the priority associated to an element of this min-priority
     * queue. The position of the element in the heap must be changed
     * accordingly. The changed element may become the minimum element. The
     * handle of the element will also be changed accordingly.
     *
     * @param element
     *                        the element whose priority will be decreased, it
     *                        must currently be inside this min-priority queue
     * @param newPriority
     *                        the new priority to assign to the element
     *
     * @throws NoSuchElementException
     *                                      if the element is not currently
     *                                      present in this min-priority queue
     * @throws IllegalArgumentException
     *                                      if the specified newPriority is not
     *                                      strictly less than the current
     *                                      priority of the element
     */
    public void decreasePriority(PriorityQueueElement element, double newPriority) {
        if (!this.heap.contains(element))
            throw new NoSuchElementException();
        if (newPriority >= element.getPriority())
            throw new IllegalArgumentException();
        element.setPriority(newPriority);
        heapify(element.getHandle());
    }

    /**
     * Determines if this priority queue is empty.
     *
     * @return true if this priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    /**
     * Return the current size of this queue.
     *
     * @return the number of elements currently in this queue.
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Erase all the elements from this min-priority queue. After this operation
     * this min-priority queue is empty.
     */
    public void clear() {
        this.heap.clear();
    }

    /**
     * Takes the element whose index is <code>i</code> and moves it until its priority is higher
     * than that of its parents and lower than that of its child.
     *
     * @param i the index of the element to move
     */
    private void heapify(int i) {
        int tmp = i;
        int child1 = i * 2 + 1;
        int child2 = i * 2 + 2;
        int parent = (i - 1) / 2;
        if (child1 < this.size() && this.heap.get(child1).getPriority() < this.heap.get(tmp).getPriority())
            tmp = child1;
        if (child2 < this.size() && this.heap.get(child2).getPriority() < this.heap.get(tmp).getPriority())
            tmp = child2;
        if (parent >= 0 && this.heap.get(parent).getPriority() > this.heap.get(tmp).getPriority())
            tmp = parent;
        if (tmp != i) {
            swap(i, tmp);
            this.heapify(tmp);
        }
    }

    /**
     * Swaps one element with its parent.
     * @param elementIndex1 child element
     * @param elementIndex2 parent element
     */
    private void swap(int elementIndex1, int elementIndex2) {
        PriorityQueueElement tmp = this.heap.get(elementIndex1);
//        this.heap.get(elementIndex1).setHandle(elementIndex1);
//        this.heap.get(elementIndex2).setHandle(elementIndex2);

        int tmpHandle = this.heap.get(elementIndex1).getHandle();
        int e2Handle = this.heap.get(elementIndex2).getHandle();
        this.heap.get(elementIndex1).setHandle(e2Handle);
        this.heap.get(elementIndex2).setHandle(tmpHandle);

        this.heap.set(elementIndex1, this.heap.get(elementIndex2));
        this.heap.set(elementIndex2, tmp);
    }

    // TODO inserire eventuali altri metodi privati per scopi di implementazione

}
