package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key can not be null");
        }
        int index = Math.floorMod(key.hashCode(), buckets.length);

        // 遍历buckets的每个node如果key有相同，则更新value
        for (Node node: buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        // 如果没有则新建
        buckets[index].add(new Node(key, value));
        size++;

        if ((double) size / buckets.length > loadFactor) {
            resize(buckets.length * 2);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Collection<Node>[] oldBuckets = buckets;
        buckets = (Collection<Node>[]) new Collection[newSize];

        // 初始化新buckets
        for (int i = 0; i < newSize; i++) {
            buckets[i] = createBucket();
        }

        size = 0;// 重修

        // 重新put所有旧节点
        for (Collection<Node> bucket : oldBuckets) {
            for (Node node : bucket) {
                put(node.key, node.value);
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("key can not be null");
        }
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node: buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node: buckets[index]) {
            if (node.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        for (Collection<Node> bucket : buckets) {
            bucket.clear();
        }
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("key can not be null");
        }

        int index = Math.floorMod(key.hashCode(), buckets.length);
        Iterator<Node> iter = buckets[index].iterator();

        while (iter.hasNext()) {
            Node node = iter.next();
            if (node.key.equals(key)) {
                V value = node.value;
                iter.remove();
                size--;
                return value;
            }
        }

        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Collection<Node>[] buckets;
    private int size;
    private double loadFactor;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity, double loadFactor) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Capacity must be >= 1");
        }
        if (loadFactor <= 0) {
            throw new IllegalArgumentException("Load factor must be > 0");
        }
        this.loadFactor = loadFactor;
        this.buckets = (Collection<Node>[]) new Collection[initialCapacity];// 创建桶数组
        this.size = 0;

        // 用createBucket()初始化每个桶
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
