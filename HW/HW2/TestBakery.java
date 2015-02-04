import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class TestBakery implements Runnable {

    public int me;
    public static final int countToThis = 100;
    public static final int noOfExperiments = 100;
    public static volatile int count = 0;
    // assume large number of threads, where N is any large number like 100
    public static final int N = 100;
    public static Bakery lock = new Bakery(N);

    public TestBakery(int newMe) {
        me = newMe;
    }

    public void run() {
        int i = 0;
        while (i < countToThis) {
	    lock.lock(me);
            count++;
	    lock.unlock(me);
            i++;
        }
    }

    public static void main(String args[]) {  
        int wrong = 0;
        long startTime = System.nanoTime();
        
        for (int i = 0; i < noOfExperiments; i++) {
        	count = 0;
        	
        	for (int j = 0; j < N; j++) {
        		Thread ti = new Thread(new TestBakery(j));
        		ti.start();
        		try {
        			ti.join();
        		}
        		catch (InterruptedException e) {}
        	}
        	
        
            if (count != N * countToThis) { 
                System.out.println("Wrong : " + count);
                wrong++;
            } 


        }
        
        long endTime = System.nanoTime();
        System.out.println("That took " + (endTime - startTime)/1000000 + " milliseconds"); 
        System.out.println("Mistakes:  " + wrong + "/" + noOfExperiments);
    }

}