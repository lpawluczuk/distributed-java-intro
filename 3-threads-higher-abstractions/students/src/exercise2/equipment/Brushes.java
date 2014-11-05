package exercise2.equipment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Brushes {
    
    private BlockingQueue queue = new LinkedBlockingQueue();
    
    public Brushes() {
        queue.offer("regular");
        queue.offer("triangular");
        queue.offer("spectacular");
    }
    
    public String takeBrush() throws InterruptedException {
        return (String) queue.take();
    }
    
    public void returnBrush(String brush) {
        queue.offer(brush);
    }
}
