// Concurrent Programming Homework 1
// Problem 2
// Jonathan Song

// Write a Java Program to create 128 threads with unique PID's and use Thread.join to block the
// main thread until all 128 threads finish execution.

// Each thread should increment a single global counter variable common to all threads (volatile variable)
// with the initial value set to 0. Print the value of this counter variable after all of the 128 variables
// finish execution.

// This modifies the code from the first problem so that it now uses locking to protect the counter variable
// "count".

// include library for locking
import java.util.concurrent.locks.ReentrantLock;

// creates Runnable Object with Runnable interface
public class HW1_2 implements Runnable {

    //me is used as a thread id
    public int me;

    //count is a shared variable
    // this will be the single global counter variable common to all threads
    public static volatile int count = 0;
    // new variable for locking
    public static final ReentrantLock lock = new ReentrantLock();
    
    // will re-initialize thread id me as newMe when called
    public HW1_2(int newMe) {
        me = newMe;
        System.out.println("The ID of this thread is: " + me);
    }
    
    // provides run() method
    // run() method will have thread iterate through count up to countToThis = 1000
    // each thread should increment the shared variable of count
    // uses locking technique to provide exclusive access of particular thread to the shared resource of count
    /* Idiom for using locks:
     * 
     Lock l = ...;
     l.lock();
     try {
         // access the resource protected by this lock
     } finally {
         l.unlock();
     }
     */
    public void run() {
    	lock.lock();
        count++;
        lock.unlock();
    }

    public static void main(String args[]) {
    	// create 128 threads with for loop
    	for (int i = 0; i < 128; i++) {
    		Thread ti = new Thread(new HW1_2(i));
    		ti.start();
    		try {
    			ti.join();
    		}
    		catch (InterruptedException e) {}
    	}
    	
    	System.out.println("The value of the global variable count is: " + count);
    	
    }
    
    
}
