public class OddEven {
    private final int limit;
    int count = 1;

    OddEven(int limit){
        this.limit=limit;
    }

    public synchronized void printOdd(){
        while(count<=limit){

            while(count%2==0){
                try {
                    wait();

                    if(count>limit){
                        return;
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }

            if(count%2==1){
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                notifyAll();
            }
        }
    }

    public synchronized  void printEven(){
        while(count<=limit){

            while(count%2==1){
                try{
                    wait();

                    if(count>limit){
                        return;
                    }
                } catch (Exception e){
                    Thread.currentThread().interrupt();
                }
            }

            if(count%2==0){

                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                notifyAll();
                
            }
            
        }
    }

    public static void main(String[] args) {
        OddEven obj = new OddEven(20);

        Thread t1 = new Thread(() -> obj.printOdd());
        Thread t2 = new Thread(() -> obj.printEven());

        t1.start();
        t2.start();
    }
}
