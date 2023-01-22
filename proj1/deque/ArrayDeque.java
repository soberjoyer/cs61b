package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    /** Creates an empty list. */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int checkChange(int num){
        if (num < 0){
            return num + items.length;
        } else if (num >= items.length){
            return num - items.length;
        } else {
            return num;
        }
    }


    /**BEFORE addLast('Z'): (e g h f c a b d) */
    /********************** (0 1 2 3 4 5 6 7) */
    /** nextLast = 3 */
    /**AFTER addLast('Z'):  (f c a b d e g h Z) */
    /********************** (0 1 2 3 4 5 6 7 8) */
    private void resizeUp(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, nextLast, newItems, 0, items.length - nextLast);
        System.arraycopy(items, 0, newItems, items.length - nextLast, nextLast);
        items = newItems;
        nextLast = size;
        nextFirst = capacity - 1;
    }

    private void resizeDown(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, currFirst(), newItems, 0, size);
        items = newItems;
        nextLast = size;
        nextFirst = capacity - 1;

    }
    @Override
    public void addFirst(T item) {
        if (size == items.length){
            resizeUp(size * 2);
        }
        items[nextFirst] = item;
        size = size + 1;
        if (nextFirst != 0){
            nextFirst = nextFirst - 1;
        } else {
            nextFirst = items.length - 1;
        }
    }

    @Override
    public void addLast(T item) {
        if (size == items.length){
            resizeUp(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size = size + 1;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i++){
            if (items[i] != null){
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }
    private T remove(int index){
        if (this.isEmpty()){
            return null;
        }else if (items[index] != null){
            T value = items[index];
            items[index] = null;
            size = size - 1;
            return value;
        }
        return null;
    }

    private void doResize(int size, int itemsLength){
        if ((size < itemsLength / 4) && (size > 100)){
                resizeDown(itemsLength / 4);
        } else if ((size <= 100) && (size > 4) && (size < itemsLength / 10)){
            resizeDown(itemsLength / 10);
        }
    }

    private int currFirst(){
        return (nextFirst + 1) % items.length;
    }
    @Override
    public T removeFirst() {
        doResize(size, items.length);
        //问题在于如果first是null的话，下面这些东西都是不应该改变的。
        //以及在整个list刚开始加入的时候，remove不应该有动作。
        //iff first是有东西的，nextFirst才需要改变。
        int RemovedIndex = currFirst();
        T returned = remove(RemovedIndex);
        if (returned != null) {
            nextFirst = RemovedIndex;
        }
        return returned;
    }

    private int currLast(){
        if (nextLast == 0) {
            return items.length - 1;
        } else {
            return nextLast - 1;
        }
    }
    @Override
    public T removeLast() {
        doResize(size, items.length);

        int RemovedIndex = currLast();
        T returned = remove(RemovedIndex);
        if (returned != null) {
            nextLast = RemovedIndex;
        }
        return returned;
    }
    /**get（index)指的是以当下的first所在的（位置+index） */
    @Override
    public T get(int index) {
        if (index >= size){
            return  null;
        }
        return items[checkChange(index + nextFirst + 1)];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T>{
        private int curPos;

        public ArrayIterator(){
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

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (o.getClass() != this.getClass()){
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (other.size() != this.size()){
            return false;
        }
        int curPos = 0;
        while (curPos < size){
            if (other.get(curPos) != this.get(curPos)){
                return false;
            }
            curPos += 1;
        }
        return true;
    }
    public static void main(String[] args) {
        //* Creates a list of one integer, namely 10 */
        ArrayDeque<Integer> javaA = new ArrayDeque<>();
        javaA.addFirst(5);
        javaA.addFirst(23);
        javaA.addLast(17);

        for (int i : javaA) {
            System.out.println(i);
        }

        ArrayDeque<Integer> javaA2 = new ArrayDeque<>();
        javaA2.addFirst(5);
        javaA2.addFirst(23);
        javaA2.addLast(17);

        System.out.println(javaA.equals(javaA2));
    }
}
