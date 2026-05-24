import java.util.ArrayDeque;
import java.util.Queue;

public class ProducerConsumer {

    private Queue<Integer> sharedQueue;
    private int bufferSize;

    public ProducerConsumer(int size) {
        this.bufferSize = size;
        this.sharedQueue = new ArrayDeque<>();
    }

    public synchronized void produce(int item) throws Exception{
        while(sharedQueue.size()==bufferSize){
            System.out.println("Buffer is full, item can't be added");
            wait();
        }

        sharedQueue.add(item);
        System.err.println("Item added is " + item);
        notifyAll();

    }

    public synchronized void consume() throws Exception{
        while(sharedQueue.size()==0){
            System.out.println("No item in buffer");
            wait();
        }
        int temp = sharedQueue.poll();
        System.err.println("Item consumed is " + temp);
        notifyAll();
    }

    

    public static void main(String[] args) {

        ProducerConsumer obj = new ProducerConsumer(3);
        Thread producerThread = new Thread(() -> {
            try {
                for(int i = 0; i<8; i++){
                    obj.produce(i);
                }
                
            } catch (Exception e) {

            }
        });

        Thread consumerThread  = new Thread(() -> {
            try {
                for(int i=0; i<8; i++){
                    obj.consume();
                }
                
            } catch (Exception e) {
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
