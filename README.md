This file include:
1. Producer/Consumer Using Threads

Create a Java class ProducerConsumer that has two nested static classes, Producer and Consumer, and a static StringBuilder instance sb.

Class Producer is a Runnable whose run() method loops 20 times. For each iteration, the method:

1.acquires a lock on sb 2.while sb is not empty, calls sb.wait() to wait for the Consumer to empty sb 3.sets sb to a string that represents the current time in milliseconds 4.calls sb.notify() to notify that a new string is available 5.releases the lock on sb 6.sleeps for 2 seconds to simulate a slow producer

Class Consumer is also a Runnable. Its run() method also loops 20 times. For each iteration, the method: 1.acquires a lock on sb 2.while sb is empty, calls sb.wait() to wait for the Producer to set sb. 3.prints the sb string to the console 4.empties sb by setting its length to 0 5.calls sb.notify() to notify that the buffer is empty 6.releases the lock on sb Create a main() method that creates and starts two threads with the Runnables. It then waits for both threads to terminate using Thread.join().

2.  ProducerConsumerPool
In this problem, you will implement a producer-consumer system using a thread pool to manage the execution of the producer and the consumer tasks. You will also use a concurrent queue to implement the buffer between the producer and the consumer.

Implement a class ProducerConsumerPool with two static inner classes, Consumer and Producer, and a main method.

The Consumer class implements java.lang.Runnable. Its constructor takes an instance of java.util.concurrent.BlockingQueue<Integer>. The run() method loops forever, taking int values from the queue and printing "Consumer: %s %d\n" with the current thread name and the int value. The BlockingQueue.take() method blocks until the queue is not empty. If the call to queue.take() is interrupted, it prints "Consumer %s interrupted\n" with the name of the current thread and breaks out of the loop
The Producer class implements java.lang.Runnable. Its constructor takes an instance of java.util.concurrent.BlockingQueue<Integer>, and three ints: start, end, and skip. The run() method loops from start, up to and including end, skipping by skip, putting the loop index onto the queue and printing "Producer: %s %d\n" with the current thread name and the loop index value. The queue.put() method blocks until the queue is not full. If the call to queue.put() is interrupted, it prints "Producer %s interrupted\n" with the name of the current thread and breaks out of the loop.
The main() method creates a FixedThreadPool ExecutorService with three (3) threads, and an ArrayBlockingQueue with capacity two (2). It also creates a Consumer, a Producer with start=1, end=10, and skip=2, and a Producer with start=0, end=10, and skip=2, and executes them with the FixedThreadPool. It then sleeps for five (5) seconds before calling executor.shutdownNow() and reporting the number of tasks that were not terminated and the number of items remaining on the queue.
