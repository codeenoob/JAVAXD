package test.demo1;

import java.util.Date;
import java.lang.Exception;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-23 <br>
 */
public class ThreadDemo1 implements Runnable {
    private Thread t;

    private static ThreadLocal<String> demoString = new ThreadLocal<>();

    private static int seq = 0;

    private int seq$;

    private ThreadDemo1() throws Exception {
        seq$ = seq++;
        System.out.println("Create Thread Demo: " + seq$);
    }

    public static void main(String[] args) throws Exception {
        ThreadDemo1 demo1 = new ThreadDemo1();
        demo1.start();
        Thread.sleep(1000);
        ThreadDemo1 demo2 = new ThreadDemo1();
        demo2.start();
        Thread.sleep(1000);
        ThreadDemo1 demo3 = new ThreadDemo1();
        demo3.start();
    }

    @Override
    public void run() {
        demoString.set(String.valueOf(System.currentTimeMillis()));
        try {
            Thread.sleep(1000);
            System.out.println(seq$ + " | " + demoString.get());
            Thread.sleep(1000);
            System.out.println(seq$ + " | " + demoString.get());
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread (this, String.valueOf(seq$));
            t.start ();
        }
    }
}
