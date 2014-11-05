package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Brushes {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int available = 3;

    public void takeBrush() throws InterruptedException {
        lock.lock();
        try {
            if (available == 0) {
                condition.await();
            }
            available -= 1;
        } finally {
            lock.unlock();
        }
    }

    public void returnBrush() {
        lock.lock();
        try {
            available += 1;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
