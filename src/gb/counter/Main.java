package gb.counter;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock();
        Counter counter = new Counter();

        int interCount = 100000;
        int threadCount = 100;

        for (int i = 0; i < threadCount; i++) {
            (new Thread(new CounterThread(reentrantLock, counter, interCount))).start();
        }
        Thread.sleep(2000);


        System.out.println(counter.getI());
        if (interCount * threadCount == counter.getI()) {
            System.out.println("все ок");
        } else {
            System.out.println("Что то пошло не так :( ");
        }


    }
}
