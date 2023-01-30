package flik;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class Test4flik {
    @Test
    public void bigNumTest() {
        int i = 0;
        for (int j = 0; j < 500; i++, j++) {
            System.out.println(i);
            assertTrue(Flik.isSameNumber(i, j));
        }
    }
}

