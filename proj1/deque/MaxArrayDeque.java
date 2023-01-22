package deque;

import jh61b.junit.In;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cpt;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cpt = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (T i : this) {
            if (cpt.compare(i, maxItem) > 0) {
                maxItem = i;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (T i : this) {
            if (c.compare(i, maxItem) > 0) {
                maxItem = i;
            }
        }
        return maxItem;
    }
}
