package edu.northeastern.cs_5004;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerPool {

	public static class Consumer implements Runnable{
		private static BlockingQueue<Integer> blockingQueue;
		String currentThread;
		Consumer(BlockingQueue<Integer>blockingQueue){
			this.blockingQueue = blockingQueue;
		}
		@Override
		public void run() {
			while(true){
				try{
					Integer value = blockingQueue.take();
					System.out.println("Consumer: " + currentThread + " consuming value: " + value);
					
				} catch(InterruptedException e) {
					System.out.println("Consumer: " + currentThread + " interrupted. ");
					break;
				}
			}
			
		
	}
	public static class Producer implements Runnable{
		private int start;
		private int end;
		private int skip;
		String currentThread;
		private BlockingQueue<Integer> blockingQueue;
		Producer(BlockingQueue<Integer> blockingQueue, int start, int end, int skip){
			this.start = start;
			this.end = end;
			this.skip = skip;
			this.blockingQueue = blockingQueue;
			
		}

		@Override
		public void run() {
			try{
				while(!Thread.interrupted()){
					for(int i = start; i < end; i+=skip){
						blockingQueue.put(i);
						System.out.println("Producer: " + currentThread + " producing value " + i);
					}
				}
					
			} catch (InterruptedException e){
				System.out.println("Producer " +  currentThread + " interrupted");
			}
			
		}
	}

/**
 * 	Test thread pool for 3 threads and 10 tasks
 * @param args
 * @throws InterruptedException
 */

	public static void main(String[] args) throws InterruptedException {
		
		//create a fixed pool of 3 threads
		ExecutorService executor = Executors.newFixedThreadPool(3);
		//initialize the queue to allow its internal array to store maximum of 2 elements
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
		Consumer consumer = new Consumer(blockingQueue);
		consumer.currentThread = "consumer";
		Thread con = new Thread(consumer);
		
		// a Producer with start=0, end=10, and skip=2
		Producer pro1 = new Producer(blockingQueue, 0, 10, 2);
		pro1.currentThread = "producer1";
		Thread p2 = new Thread(pro1);
		
		// a Producer with start=1, end=10, and skip=2
		Producer pro2 = new Producer(blockingQueue, 1, 10, 2);
		pro2.currentThread = "producer2";
		Thread p1 = new Thread(pro2);
		
		
		executor.execute(con);
		executor.execute(p1);
		executor.execute(p2);
		
	    //sleeps for five (5) seconds 
		Thread.sleep(5000);
		
		executor.shutdownNow();
		//report the number of tasks that were not terminated and the number of items 
		//remaining on the queue
		System.out.println("The number of Items remaining on the queue is: " + blockingQueue.size());
	}
 }
}
