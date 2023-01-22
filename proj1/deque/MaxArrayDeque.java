package deque;

import jh61b.junit.In;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> cpt;

    public MaxArrayDeque(Comparator<T> c){
        super();
        cpt = c;
    }

    public T max(){
        if (isEmpty()){
            return null;
        }
        T maxItem = get(0);
        for (T i : this){
            if (cpt.compare(i, maxItem) > 0){
                maxItem = i;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c){
        if (isEmpty()){
            return null;
        }
        T maxItem = get(0);
        for (T i : this){
            if (c.compare(i, maxItem) > 0){
                maxItem = i;
            }
        }
        return maxItem;
    }

    public static void main(String[] args) {
        Comparator<Integer> cpt = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        Comparator<Integer> cpt2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        MaxArrayDeque mad1 = new MaxArrayDeque(cpt);
        int n = 99;

        for (int i = n; i >= 0; i--) {
            mad1.addFirst(i);
        }

        System.out.println(mad1.max());
        System.out.println(mad1.max(cpt2));
    }
}
