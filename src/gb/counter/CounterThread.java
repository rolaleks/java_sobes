package gb.counter;

import java.util.concurrent.locks.ReentrantLock;

public class CounterThread implements Runnable {

    private ReentrantLock reentrantLock;
    private Counter counter;
    private int interCount;

    public CounterThread(ReentrantLock reentrantLock, Counter counter, int interCount) {
        this.reentrantLock = reentrantLock;
        this.counter = counter;
        this.interCount = interCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < interCount; i++) {
            reentrantLock.lock();
            counter.increase();
            reentrantLock.unlock();
        }
    }
}
