import java.util.concurrent.locks.ReentrantLock;

class SharedResource{
    static boolean isAvailable =false;

    public void producer(ReentrantLock lock){
        try {
            lock.lock();
            System.err.println("lock acquired by thread "+ Thread.currentThread().getName());
            isAvailable=true;
            Thread.sleep(5000);
            
        } catch (Exception e) {
        }
        finally{
            System.out.println(Thread.currentThread().getName() + " in finally block");
            lock.unlock();
            System.out.println("lock released by thread "+ Thread.currentThread().getName());
        }
    }

    public void consumer(ReentrantLock lock){
        try {
            lock.lock();
            System.err.println("lock acquired by thread "+ Thread.currentThread().getName());
            isAvailable=false;
            Thread.sleep(5000);
            
        } catch (Exception e) {
        }
        finally{
            System.out.println(Thread.currentThread().getName() + " in finally block");
            lock.unlock();
            System.out.println("lock released by thread "+ Thread.currentThread().getName());
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        SharedResource resource1 = new SharedResource();
        SharedResource resource2 = new SharedResource();

        Thread t1 = new Thread(()-> resource1.producer(lock));
        Thread t2 = new Thread(()->resource2.consumer(lock));

        t1.start();
        t2.start();
    }
}
