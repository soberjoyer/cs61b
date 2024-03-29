/** Performs some basic Array list tests. */
package deque;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/** b01) AD-basic: add/get (0.0/1.333)
 b03) AD-basic: fill up, empty, fill up again. (0.0/1.333)
 b04) AD-basic: multiple ADs. (0.0/1.333)
 b06) AD-basic: negative size. (0.0/1.333)
 */

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        assertEquals(1, ad1.size());
        assertFalse("lld1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {


        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", ad1.isEmpty());

        ad1.addFirst(0);
        System.out.print(ad1.get(0));

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {


        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }


        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }

    }

    @Test
    public void OrderTest(){
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addFirst("c");
        ad1.addLast("d");
        ad1.addLast("e");
        ad1.addFirst("f");
        ad1.addLast("g");
        ad1.addLast("h");
        ad1.addLast("Z");

        ad1.printDeque();

    }

    @Test
    public void NextFirstTest(){
        //if nexfFirst = 0
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);

        //ad1.removeLast();
        //ad1.addLast(7);
        assertEquals(Integer.valueOf(1), ad1.get(0));

    }

    @Test
    public void removeBeforeAdd(){
        //if nexfFirst = 0
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.removeFirst();
        ad1.addFirst(1);

        //ad1.removeLast();
        //ad1.addLast(7);
        assertEquals(Integer.valueOf(1), ad1.get(0));


    }

    @Test
    public void testGet2() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        input.addLast(0);
        input.addLast(1);
        input.addLast(2);
        input.removeFirst();
        int result = input.get(1);
        assertEquals(2, result);

    }

    @Test
    public void testGet3() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        input.addFirst(0);
        input.addLast(1);
        input.addFirst(2);
        input.addFirst(3);
        input.removeFirst();
        input.removeFirst();
        input.addLast(6);
        input.removeFirst();
        input.addLast(8);
        input.removeLast();
        input.removeFirst();
        int result = input.get(0);
        assertEquals(6, result);
    }

    @Test
    public void equalTest(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
        input.addLast(0);
        input.addLast(1);
        input.addLast(2);
        input.addLast(3);
        System.out.println(input);

        ArrayDeque<Integer> input2 = new ArrayDeque<>();
        input2.addLast(2);
        input2.addLast(3);
        input2.addFirst(1);
        input2.addFirst(0);

        System.out.println(input2);
        System.out.println(input.equals(input2));

    }

    @Test
    public void differentTypeEqualTest(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
        input.addLast(0);
        input.addLast(1);
        input.addLast(2);
        input.addLast(3);
        System.out.println(input);

        LinkedListDeque<Integer> input2 = new LinkedListDeque<>();
        input2.addLast(0);
        input2.addLast(1);
        input2.addLast(2);
        input2.addLast(3);

        System.out.println(input2);

        System.out.println(input.equals(input2));

    }


}
