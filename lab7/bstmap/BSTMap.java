package bstmap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class BSTMap<K extends Comparable<K> , V> implements Map61B{
    private BSTNode root;
    private class BSTNode {
        private K key;
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

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        } else {
            root = null;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return containsKey(root, key);
    }

    private boolean containsKey(BSTNode x, Object key) {
        K k = (K) key;
        if (k == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return false;
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            return containsKey(x.left, k);
        } else if (cmp > 0) {
            return containsKey(x.right, k);
        } else return true;

    }

    @Override
    public Object get(Object key) {
        return get(root, key);
    }

    private Object get(BSTNode x, Object key) {
        K k = (K) key;
        if (k == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return  null;
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, k);
        } else if (cmp > 0) {
            return get(x.right, k);
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

    public void delete(Object key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }
    private BSTNode delete(BSTNode x, Object key) {
        K k = (K) key;
        if (x == null) {
            return null;
        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            BSTNode t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).key;
    }
    private  BSTNode min(BSTNode x) {
        if (x.left == null) {
            return x;
        }
        else return min(x.left);
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        //assert check();
    }

    private BSTNode deleteMin(BSTNode x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void put(Object key, Object value) {
        K k = (K) key;
        V val = (V) value;
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(root, key, val);
    }

    private BSTNode put(BSTNode x, Object key, Object val) {
        K k = (K) key;
        V v = (V) val;
        if (x == null) {
            return new BSTNode(k, v, 1);
        }
        int cmp = k.compareTo(x.key);
        if  (cmp < 0) {
            x.left  = put(x.left, key, val);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, val);
        }
        else {
            x.val = v;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }


    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    public void printInOrder(){
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
