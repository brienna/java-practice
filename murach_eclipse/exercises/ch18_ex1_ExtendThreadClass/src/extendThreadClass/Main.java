package extendThreadClass;

public class Main {

    // Use these constants as the starting values for the two counter threads
    public static final int ODD = 1;
    public static final int EVEN = 2;

    public static void main(String[] args) {
        System.out.println("Main started.");
        
        CounterThread ctOdd = new CounterThread(ODD);
        CounterThread ctEven = new CounterThread(EVEN);
        ctOdd.start();
        ctEven.start();
        
        System.out.println("Main finished.");
    }

}
