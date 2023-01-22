package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class StuffNode {
        private StuffNode pre;
        private T item;
        private StuffNode next;

        private StuffNode(StuffNode p, T i, StuffNode n) {
            pre = p;
            item = i;
            next = n;
            // System.out.println(size);
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private StuffNode sentinel;
    private int size;

    /**
     * Creates an empty LinkedListDeque.
     */
    public LinkedListDeque() {
        //?
        sentinel = new StuffNode(null, null, null);
        size = 0;
    }

    //** public LinkedListDeque(T item) {
    //sentinel = new StuffNode(617, null);
    //sentinel.next = new StuffNode(x, null);
    //size = 1;
    //}

    @Override
    public void addFirst(T item) {
        if (this.isEmpty()) {
            sentinel.next = new StuffNode(sentinel, item, sentinel);
            sentinel.pre = sentinel.next;
        } else {
            StuffNode newFirst = new StuffNode(sentinel, item, sentinel.next);
            sentinel.next = newFirst;
            newFirst.next.pre = newFirst;
        }
        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        if (this.isEmpty()) {
            sentinel.pre = new StuffNode(sentinel, item, sentinel);
            sentinel.next = sentinel.pre;
        } else {
            StuffNode oldLast = sentinel.pre;
            sentinel.pre = new StuffNode(sentinel.pre, item, sentinel);
            oldLast.next = sentinel.pre;
        }
        size = size + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StuffNode p = sentinel.next;

        /* Advance p to the end of the list. */
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        StuffNode first = sentinel.next;
        if (first == null) {
            return null;
        }
        if (first.next == sentinel) {
            sentinel.next = null;
            sentinel.pre = null;
        } else {
            StuffNode newFirst = first.next;
            sentinel.next = newFirst;
            newFirst.pre = sentinel;
        }
        size = size - 1;
        return first.item;
    }

    @Override
    public T removeLast() {
        StuffNode last = sentinel.pre;

        if (last == null) {
            return null;
        }
        if (last.pre == sentinel) {
            sentinel.pre = null;
            sentinel.next = null;
        } else {
            StuffNode newLast = last.pre;
            sentinel.pre = newLast;
            newLast.next = sentinel;
        }
        size = size - 1;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (index == 0) {
            if (this.isEmpty()) {
                return null;
            }
            return sentinel.next.item;
        }
        StuffNode goal = sentinel.next;
        for (int i = 1; i <= index; i++) {
            if (goal.next != null) {
                goal = goal.next;
            }
            //return null;
        }
        return goal.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int curPos;
        LinkedListIterator() {
            curPos = 0;
        }
        @Override
        public boolean hasNext() {
            return curPos < size;
        }
        @Override
        public T next() {
            T returnItem = get(curPos);
            curPos += 1;
            return returnItem;
        }
    }

    private T getRecursiveHelper(int index, StuffNode p) {
        if (index == 0) {
            if (p != null) {
                return p.item;
            }
            return null;
        }
        if (p.next != null) {
            return getRecursiveHelper(index - 1, p.next);
        }
        return null;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (other.get(i) != this.get(i)) {
                return false;
            }
        }
        return true;
    }

}

