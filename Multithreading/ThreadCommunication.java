/* Inter thread communication */

// wait(), notify(), notifyAll() work on object's monitor lock 

// These methods must be called inside a synchronized block/method.

class SharedResource{

    boolean itemAvaialble = false;

    public synchronized void addItem(){
        itemAvaialble = true;
        System.err.println("Item is added by " + Thread.currentThread().getName());
        notify();
    }

    public synchronized void consumeItem(){

        System.out.println("Consume Item invoked by " + Thread.currentThread().getName());
        while(!itemAvaialble){
            try {
                System.out.println("Thread " + Thread.currentThread().getName() +" is waiting now");
                wait(); // releases the monitor lock
                System.out.println("Wait over for thread " + Thread.currentThread().getName());
            } catch (Exception e) {
                // handle exception
            }
        }

        System.out.println("Item consumed by " + Thread.currentThread().getName());
        itemAvaialble = false;
    }


}

// producer class 

class Producer implements Runnable{

    SharedResource sharedResource;

    Producer(SharedResource resource){
        this.sharedResource = resource;
    }

    @Override
    public void run(){
        System.out.println("Producer thread " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000); 
        } catch (Exception e) {
            // handle exception
        }

        sharedResource.addItem();
    }

}


// consumer class 

class Consumer implements Runnable{

    SharedResource sharedResource;

    Consumer(SharedResource resource){
        this.sharedResource = resource;
    }

    @Override
    public void run(){
        System.err.println("Consumer Thread " + Thread.currentThread().getName());

        sharedResource.consumeItem();
    }
}

public class ThreadCommunication {
    
    public static void main(String[] args) {
        
        SharedResource sharedResource = new SharedResource();

        Thread producerThread = new Thread( new Producer(sharedResource));

        Thread consumerThread = new Thread(new Consumer(sharedResource));

        producerThread.start();
        consumerThread.start();

        System.out.println(Thread.currentThread().getName());
    }
    
}
