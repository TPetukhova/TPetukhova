package lesson2;

public class TestRunnableTanya {

        static Runnable r1 = () -> System.out.println("I am running!");

    public static void main(String[] args) {
        r1.run();
    }

}
