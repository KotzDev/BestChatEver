package Exempel;

/**
 * Created by sydney.wojnach on 2015-01-27.
 * Detta är från
 */
public class ThreadDemo extends Thread implements Runnable {

    public ThreadDemo() throws InterruptedException {
        start();
        join();     // Vi väntar på att tråden blir färdig innan vi går vidare i progammet.
    }

    public void run()
    {
        System.out.println("hello world (Thread)");
    }

    public static void main(String[] args) throws InterruptedException {

        new ThreadDemo();
        System.out.println("Hellow world (Main)");
    }
}
