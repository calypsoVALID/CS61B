package deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;


public class ArrayDeque61B<T> implements Deque61B<T> {
    // constructor
    int size;
    int nextFirst;
    int nextLast;
    T[] items;
    private final int ResizeUpFactor = 2;
    private final int ResizeDownFactor = 4;


    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
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
            resize(items.length * ResizeUpFactor);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * ResizeUpFactor);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = Math.floorMod(nextLast + 1, items.length);
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

    private boolean shouldShrink() {
        return items.length >= 16 && size < items.length / ResizeDownFactor;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            nextFirst = Math.floorMod(nextFirst + 1, items.length);
            T item = items[nextFirst];
            items[nextFirst] = null;
            size--;
            if (shouldShrink()) {
                resize(items.length / ResizeDownFactor);
            }
            return item;
        }
    }


    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            nextLast = Math.floorMod(nextLast - 1, items.length);
            T item = items[nextLast];
            items[nextLast] = null;
            size--;
            if (shouldShrink()) {
                resize(items.length / ResizeDownFactor);
            }
            return item;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return items[Math.floorMod(nextFirst + index + 1, items.length)];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
