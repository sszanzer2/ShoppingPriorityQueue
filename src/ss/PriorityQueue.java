package ss;

import java.util.ArrayList;
import java.util.List;

//PriorityQueue class implements a priority queue using a binary heap
public class PriorityQueue<T extends Comparable<T>> implements Queue<T>{
    private List<T> heap;

    // Constructor initializes an empty heap
    public PriorityQueue() {
        this.heap = new ArrayList<>();
    }

    // Enqueue operation to add an element to the priority queue
    @Override
    public void enqueue(T element) {
        heap.add(element);
        heapifyUp(); // Restore heap property
    }

    // Dequeue operation to remove and return the highest-priority element
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        T highestPriority = heap.get(0);
        int lastIndex = heap.size() - 1;
        // Move the last element of the array to the zeroth position
        heap.set(0, heap.get(lastIndex));
        // Delete from the back of the queue
        heap.remove(lastIndex);
        heapifyDown(); // Restore heap property
        return highestPriority;
    }
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Restore heap property after an insertion
    private void heapifyUp() {
        int currentIndex = heap.size() - 1;

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;

            if (heap.get(currentIndex).compareTo(heap.get(parentIndex)) < 0) {
                swap(currentIndex, parentIndex);
                currentIndex = parentIndex; // Move up in the heap
            } else {
                break;
            }
        }
    }

    // Restore heap property after a removal
    private void heapifyDown() {
        int currentIndex = 0;
        int size = heap.size();

        while (true) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
            int smallestChildIndex = currentIndex;

            if (leftChildIndex < size && heap.get(leftChildIndex).compareTo(heap.get(smallestChildIndex)) < 0) {
                smallestChildIndex = leftChildIndex;
            }
            if (rightChildIndex < size && heap.get(rightChildIndex).compareTo(heap.get(smallestChildIndex)) < 0) {
                smallestChildIndex = rightChildIndex;
            }

            if (smallestChildIndex != currentIndex) {
                swap(currentIndex, smallestChildIndex);
                currentIndex = smallestChildIndex; // Move down in the heap
            } else {
                break;
            }
        }
    }
    
    // Utility method to swap elements in the heap
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

	@Override
	public int size() {
		return heap.size();
	}
}
