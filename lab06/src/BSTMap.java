import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    // 定义节点类（内部类）
    public class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    private Node root;
    private int size;


    // constructor
    public BSTMap() {
        root = null;
        size = 0;
    }


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
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value); // new node
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value); // insert left child tree
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);// insert right child tree
        } else {
            node.value = value;// overwrite the old value exist key
        }

        return node;
    }


    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    // helper methods
    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }


    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
       return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return contains(node.left, key);
        } else {
            return contains(node.right, key);
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }


    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        collectKeys(root, set);
        return set;
    }

    private void collectKeys(Node node, Set<K> set) {
        if (node == null) {
            return;
        }

        collectKeys(node.left, set);
        set.add(node.key);
        collectKeys(node.right, set);
    }
    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        root = remove(root, key);
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;// 如果节点为空，返回null
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key); // 如果key小于当前节点的key,则去左子树删除
        } else if (cmp > 0) {
            node.right = remove(node.right, key); // 如果 key 大于当前节点的 key，则去右子树删除
        } else {
            // 找到要删除的节点了

            // 情况1：当前节点没有左子树，直接返回右子树
            if (node.left == null) {
                return node.right;
            }

            // 情况2：当前节点没有右子树，直接返回左子树
            if (node.right == null) {
                return node.left;
            }

            // 情况3：当前节点有两个子树
            // 找到右子树中最小的节点（后继节点），用它来替换当前节点
            node.key = min(node.right).key;
            node.value = min(node.right).value;
            node.right = remove(node.right, node.key);
        }
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);// 递归访问左子树
        System.out.print("(" + node.key + ":" + node.value + ")");// 打印当前节点的键值对
        printInOrder(node.right);// 递归访问右子树
    }
}
