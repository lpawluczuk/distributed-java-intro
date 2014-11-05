package exercise2.equipment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Paints {
    
    private BlockingQueue queue = new LinkedBlockingQueue();
    
    public Paints() {
        queue.offer("red");
        queue.offer("green");
        queue.offer("blue");
    }
    
    public String takePaint() throws InterruptedException {
        return (String) queue.take();
    }
    
    public void returnPaint(String paint) {
        queue.offer(paint);
    }
}
