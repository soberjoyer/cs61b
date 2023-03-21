package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K> , V> implements Map61B<K, V>{
    private BSTNode root;
    private class BSTNode {
        private final K key;
        private V val;

        private BSTNode left, right;
        private int size;

        public BSTNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            root = null;
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return containsKey(root, key);
    }

    private boolean containsKey(BSTNode x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return false;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return containsKey(x.left, key);
        } else if (cmp > 0) {
            return containsKey(x.right, key);
        } else return true;

    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return  null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else return x.val;

    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode x) {
        if (x == null) {
            return 0;
        } else return x.size;
    }


    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode x, K key, V val) {
        if (x == null) {
            return new BSTNode(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if  (cmp < 0) {
            x.left  = put(x.left, key, val);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, val);
        }
        else {
            x.val = val;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupported operation");
    }


    public V remove(K key) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    public void printInOrder(){
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
