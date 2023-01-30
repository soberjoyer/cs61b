package flik;

public class HorribleSteve {
    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; i++, j++) {
            if (!Flik.isSameNumber(i, j)) {
                System.out.println(String.format("i:%d not same as j:%d ??", i, j));
                break;
            }
        }
        System.out.println("i is " + i);
    }
}
