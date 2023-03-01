import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorOfIterators implements Iterator<Integer> {
    private LinkedList<Iterator<Integer>> list;

    public IteratorOfIterators(List<Iterator<Integer>> a) {
        list = new LinkedList<>();
        for (Iterator<Integer> iterator : a) {
            if (iterator.hasNext()) {
                list.addLast(iterator);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        //step 1: extract the item to output;
        Iterator<Integer> nextIterator = list.removeFirst();
        int ans = nextIterator.next();
        //step 2 prepare for the next time we call next
        //把第一个放到最后
        if (nextIterator.hasNext()) {
            list.addLast(nextIterator);
        }

        // step 3 return this item
        return ans;
    }
}
