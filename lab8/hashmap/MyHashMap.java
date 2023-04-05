package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author hachi
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    double maxLoad;
    int size;

    @Override
    public void clear() {
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }
        size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        } else {
            return Math.floorMod(key.hashCode(), buckets.length);
        }
    }

    @Override
    public boolean containsKey(K key) {
        int i = hash(key);
        if (i < buckets.length && buckets[i] != null) {
            for (Node node : buckets[i]) {
                if (key.equals(node.key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        if (i < buckets.length && buckets[i] != null) {
            for (Node node : buckets[i]) {
                if (key.equals(node.key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        MyHashMap<K, V> newMap = new MyHashMap<>(buckets.length * 2);
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                for (Node node : bucket) {
                    newMap.put(node.key, node.value);
                }
            }
        }
        }
    @Override
    public void put(K key, V value) {
        int h = hash(key);
        Node newNode = new Node(key, value);
        if (buckets[h] == null) {
            buckets[h] = createBucket();
            buckets[h].add(createNode(key, value));
        } else {
            for (Node node : buckets[h]) {
                if (key.equals(node.key)) {
                    node.value = value;
                    return;
                }
            }
            buckets[h].add(newNode);
        }
        size += 1;
        if (size() > buckets.length * maxLoad) {
            resize();
        }
    }



    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                for (Node node : bucket) {
                    if (node.key != null) {
                        keys.add(node.key);
                    }
                }
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

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
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        if (initialSize < 1 || maxLoad <= 0.0) {
            throw new IllegalArgumentException();
        }
        buckets = createTable(initialSize);
        size = 0;
        this.maxLoad = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
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

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }



    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
