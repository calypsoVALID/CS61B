import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private final Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node();
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public class Node {
        private T value;
        private Node next;
        private Node prev;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        size += 1;
        Node newNode = new Node();
        newNode.value = x;
        sentinel.next.prev = newNode;
        newNode.next = sentinel.next;
        sentinel.next = newNode;
        newNode.prev = sentinel;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        size += 1;
        Node newNode = new Node();
        newNode.value = x;
        sentinel.prev.next = newNode;
        newNode.prev = sentinel.prev;
        sentinel.prev = newNode;
        newNode.next = sentinel;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node currentNode = sentinel.next;
        while (currentNode != sentinel) {
            returnList.add(currentNode.value);
            currentNode = currentNode.next;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            T returnValue = sentinel.next.value;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            return returnValue;
        }
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            T returnValue = sentinel.prev.value;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            return returnValue;
        }
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {//O(n)
        if (size == 0 || index < 0 || index >= size) {
            return null;
        } else {
            Node currentNode = sentinel.next;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode.value;
        }

        //        尝试优化为O(n/2)
        //        if (size == 0 || index < 0 || index >= size) {
        //            return null;
        //        } else if (index <= (size / 2)) {
        //            Node currentNode = sentinel.next;
        //            for (int i = 0; i < index; i++) {
        //                currentNode = currentNode.next;
        //            }
        //            return currentNode.value;
        //        } else {
        //            Node currentNode = sentinel.prev;
        //            for (int i = 0; i < size - index - 1; i++) {
        //                currentNode = currentNode.prev;
        //            }
        //            return currentNode.value;
        //        }
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        }
        Node currentNode = sentinel.next;
        return helperGetRecursive(currentNode, index);
    }

    public T helperGetRecursive(Node node, int temp) {
        if (temp == 0) {
            return node.value;
        } else {
            node = node.next;
            return helperGetRecursive(node, temp - 1);
        }
    }
}
