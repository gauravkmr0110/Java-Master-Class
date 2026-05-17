// Method 1: Extending the Thread class

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running by extending the Thread class: " + Thread.currentThread().getName());
    }
}


// Method 2: Implementing the Runnable interface

class MyRunnableThread implements Runnable{
    @Override
    public void run(){
        System.err.println("This is running using Runnable " + Thread.currentThread().getName());
    }
}

public class ThreadCreation{
    public static void main(String[] args) {
        Thread t1 = new MyThread();

        // pass runnable object 

        Thread t2 = new Thread(new MyRunnableThread());

        t1.start();
        t2.start();
    }
}

// Blocked state release monitor lock 

// waiting usinfg wait() method release monitor lock

// timed waiting using slee() don't release monitor lock

