
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
ReadWriteLock allows multiple readers or one writer at a time.
Used when reads are frequent and writes are rare.
Provided by Java using ReentrantReadWriteLock.
readLock() can be acquired by multiple threads simultaneously.
writeLock() is exclusive; only one thread can hold it.
Readers are blocked when a writer holds the lock.
Writers are blocked while readers are active.
Improves concurrency compared to normal synchronized or ReentrantLock.
Common use cases: caching, configuration data, in-memory databases.
Important methods: readLock().lock(), writeLock().lock(), and corresponding unlock().
Read lock: shared lock scanario, Write lock: exclusive lock scanario
 */

class SharedResource{
    static int counter = 0;

    public void readerThread(ReadWriteLock lock){

        try {
            lock.readLock().lock();;
            System.err.println("lock acquired by thread "+ Thread.currentThread().getName());
            System.err.println("counter = "+counter);
            Thread.sleep(5000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        finally{
            System.err.println("lock released by thread " + Thread.currentThread().getName());
            lock.readLock().unlock();
        }

    }

    public void writterThread(ReadWriteLock lock){
        try {
            lock.writeLock().lock();
            System.err.println("lock acquired by thread "+ Thread.currentThread().getName());
            counter++;
            System.err.println("counter = "+counter);
            Thread.sleep(5000);

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        finally{
            System.err.println("lock released by thread " + Thread.currentThread().getName());
            lock.writeLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        // we will create multiple read threads and one write 
        SharedResource resource1 = new SharedResource();
        SharedResource resource2 = new SharedResource();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        Thread t1 = new Thread(()-> resource1.readerThread(lock),"thread1");
        Thread t2 = new Thread(()-> resource2.readerThread(lock),"thread2");
        Thread t3 = new Thread(()-> resource1.readerThread(lock),"thread3");
        Thread t4 = new Thread(()-> resource1.writterThread(lock),"thread4");
        Thread t5 = new Thread(()-> resource2.writterThread(lock),"thread5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        
    }
}
