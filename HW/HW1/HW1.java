// Concurrent Programming Homework 1
// Problem 1
// Jonathan Song

// Write a Java Program to create 128 threads with unique PID's and use Thread.join to block the
// main thread until all 128 threads finish execution.

// Each thread should increment a single global counter variable common to all threads (volatile variable)
// with the initial value set to 0. Print the value of this counter variable after all of the 128 variables
// finish execution.

// creates Runnable Object with Runnable interface
public class HW1 implements Runnable {

    //me is used as a thread id
    public int me;

    //count is a shared variable
    // this will be the single global counter variable common to all threads
    public static volatile int count = 0;
    
    // will re-initialize thread id me as newMe when called
    public HW1(int newMe) {
        me = newMe;
        System.out.println("The ID of this thread is: " + me);
    }
    
    // provides run() method
    // run() method will have thread iterate through count up to countToThis = 1000
    // each thread should increment the shared variable of count
    public void run() {
        count++;
    }

    public static void main(String args[]) {
    	// create 128 threads with for loop
    	for (int i = 0; i < 128; i++) {
    		Thread ti = new Thread(new HW1(i));
    		ti.start();
    		try {
    			ti.join();
    		}
    		catch (InterruptedException e) {}
    	}
    	
    	System.out.println("The value of the global variable count is: " + count);
    	
    }
    
    
}
