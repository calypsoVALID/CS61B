package deque;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


public class ArrayDeque61B<T> implements Deque61B<T> {
    // constructor
    int size;
    int nextFirst;
    int nextLast;
    T[] items;

    public T[] resize() {
        return null;
    }

    public ArrayDeque61B() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (T[]) new Object[8];
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize();
        } else {
            items[nextFirst] = x;
            size += 1;
            nextFirst = Math.floorMod(nextFirst - 1, items.length);
        }
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize();
        } else {
            items[nextLast] = x;
            size += 1;
            nextLast = Math.floorMod(nextLast + 1, items.length);
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(items[Math.floorMod(nextFirst + 1 + i, items.length)]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            nextFirst = Math.floorMod(nextFirst + 1, items.length);
            T item = items[nextFirst];
            items[nextFirst] = null;
            return item;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            nextLast = Math.floorMod(nextLast - 1, items.length);
            T item = items[nextLast];
            items[nextLast] = null;
            return item;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return items[Math.floorMod(nextFirst + index, items.length)];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
