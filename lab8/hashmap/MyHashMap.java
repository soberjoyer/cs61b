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
    /* Instance Variables */

    private static final int INIT_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int initialSize;
    private double maxLoad;
    private Collection<Node>[] buckets;
    private int size = 0;

    private Set<K> keys;

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

    /** Constructors */
    public MyHashMap() {
        this(INIT_SIZE, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.maxLoad = maxLoad;
        keys = new HashSet<>();

        if (initialSize < 1 || maxLoad <= 0.0) {
            throw new IllegalArgumentException();
        }
        buckets = createTable(initialSize);
        for (int i = 0; i < initialSize; i++) {
            buckets[i] = createBucket();
        }
    }


    @Override
    public void clear() {
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }
        keySet().clear();
        this.size = 0;
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
        return keySet().contains((K)key);
    }

    private V getHelper(K key, Collection<Node> bucket) {
        if (bucket != null) {
            for (Node node : bucket) {
                if (key.equals(node.key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        int i = hash(key);
        if (i >= buckets.length) {
            return null;
        } else {
            return getHelper(key, buckets[i]);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    private void resize() {
        MyHashMap<K, V> newMap = new MyHashMap<>(initialSize * 2); //ini写了bucket.lengths
        Set<K> keys = keySet();
        for (K key : keys) {
            newMap.put(key, get(key));
        }
        initialSize = newMap.initialSize; //一直没有把resize后的的initialSize和buckets改了。
        buckets = newMap.buckets;
    }


    @Override
    public void put(K key, V value) {
        int h = hash(key);
        Node newNode = createNode(key, value);
        if (!containsKey(key)) {
            this.size += 1;
            buckets[h].add(newNode);
            keySet().add(key);
        } else {
            for (Node node : buckets[h]) {
                if (key.equals(node.key)) {
                    node.value = value;
                    return;
                }
            }
        }
        if (size() / initialSize > maxLoad) {
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
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
        return new ArrayList<>();
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
