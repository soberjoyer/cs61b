import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteredList<T> implements Iterable<T>{
    private List<T> L;
    private Predicate<T> filter;

    public FilteredList (List<T> L, Predicate<T> filter) {
        this.L = L;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilteredListIterator();
    }


    private class FilteredListIterator implements Iterator<T> {
        private int index;

        public  FilteredListIterator(){
            index = 0;
            moveIndex();

        }
        @Override
        public boolean hasNext() {
            return index < L.size();
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            //step 1: extract the item to output;
            T item = L.get(index);
            //step 2 prepare for the next time we call next
            moveIndex();

            // step 3 return this item
        }

        /**
         *
         */
        private void moveIndex() {
            while (hasNext() && !filter.test(L.get(index))) {
                index += 1;
            }
        }
    }
}