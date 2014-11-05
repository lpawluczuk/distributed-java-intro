package exercise2;

import common.Counter;
import java.util.concurrent.locks.ReentrantLock;

public class LockingCounter implements Counter {

    private long counter = 0L;
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void increment() {
        reentrantLock.lock();
        try {
            counter++;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public long getValue() {
        return counter;
    }
}
