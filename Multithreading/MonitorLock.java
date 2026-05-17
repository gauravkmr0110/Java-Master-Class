
/*
    Every java object has monitor lock and it belongs to object, used by synchronized keyword
 */


public class MonitorLock {

    public synchronized void task1(){
        try {
            System.err.println("inside task1");
           
            Thread.sleep(10000);

            System.out.println("Waiting");
            
        } catch (Exception e) {

        }
    }

    public void task2(){
        System.err.println("task2 before synchronized");

        synchronized (this) {
            System.err.println("task2 inside synchronized");
        }
    }

    public void task3(){
        System.out.println("task3");
    }

    public static void main(String[] args) {
        MonitorLock obj = new MonitorLock();

        Thread t1 = new Thread(() -> obj.task1());
        Thread t2 = new Thread(() -> obj.task2());

        Thread t3 = new Thread(() -> obj.task3());

        t1.start();
        t2.start();
        t3.start();

        // all three are acting on same object, t2 needs to wait for 10 seconds 

        MonitorLock obj2 = new MonitorLock();

        Thread t4 = new Thread(() -> obj2.task2());
        t4.start();

        // both t4 and t1 can run simulataneoulsy t4 doesn't need to wait for 10 sec here since t4 is on diff object
    }
    
}
