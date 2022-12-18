package deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    /** Creates an empty list. */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public void insert(T x, int position) {
        T[] newItems = (T[]) new Object[items.length + 1];

        System.arraycopy(items, 0, newItems, 0, position);
        newItems[position] = x;

        System.arraycopy(items, position, newItems, position + 1, items.length - position);
        items = newItems;
    }
    /**BEFORE addLast('Z'): (e g h f c a b d) */
    /********************** (0 1 2 3 4 5 6 7) */
    /** nextLast = 3 */
    /**AFTER addLast('Z'):  (f c a b d e g h Z) */
    /********************** (0 1 2 3 4 5 6 7 8) */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, nextLast, newItems, 0, items.length - nextLast);
        System.arraycopy(items, 0, newItems, items.length - nextLast, nextLast);
        items = newItems;
        nextLast = size;
        nextFirst = capacity - 1;
    }
    @Override
    public void addFirst(T item) {
        insert(item, nextFirst);
        if (nextFirst != 0){
            nextFirst = nextFirst - 1;
        } else {
            nextFirst = items.length - 1;
        }
    }

    @Override
    public void addLast(T item) {
        if (size == items.length){
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size = size + 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
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
    public T remove(int index){
        if (items[index] != null){
            T value = items[index];
            items[index] = null;
            size = size - 1;
            return value;
        }
        return null;
    }
    @Override
    public T removeFirst() {
        if ((size < items.length / 4) && (size > 4)) {

            resize(items.length / 4);
        }
        int CurrFirst = (nextFirst + 1) % items.length;
        return remove(CurrFirst);
    }

    @Override
    public T removeLast() {
        if ((size < items.length / 4) && (size > 4)) {

            resize(items.length / 4);
        }
        int currLast;
        if (nextLast == 0) {
            currLast = items.length - 1;
        } else {
            currLast = nextLast - 1;
        }
        return remove(currLast);
    }

    @Override
    public T get(int index) {
        return items[index];
    }
}
