# ProducerConsumer
<<<<<<< HEAD
# -PalindromeServer
# -PalindromeServer
# -PalindromeServer
# -PalindromeServer
# -PalindromeServer
# -PalindromeServer
# -PalindromeServer
=======
Producer/Consumer Using Threads

Create a Java class ProducerConsumer that has two nested static classes, Producer and Consumer, and a static StringBuilder instance sb.

Class Producer is a Runnable whose run() method loops 20 times. For each iteration, the method:

1.acquires a lock on sb 2.while sb is not empty, calls sb.wait() to wait for the Consumer to empty sb 3.sets sb to a string that represents the current time in milliseconds 4.calls sb.notify() to notify that a new string is available 5.releases the lock on sb 6.sleeps for 2 seconds to simulate a slow producer

Class Consumer is also a Runnable. Its run() method also loops 20 times. For each iteration, the method: 1.acquires a lock on sb 2.while sb is empty, calls sb.wait() to wait for the Producer to set sb. 3.prints the sb string to the console 4.empties sb by setting its length to 0 5.calls sb.notify() to notify that the buffer is empty 6.releases the lock on sb Create a main() method that creates and starts two threads with the Runnables. It then waits for both threads to terminate using Thread.join().
>>>>>>> 17ddf0834f91b3489d39b18eae1bf6470fbfa60e
# -PalindromeServer
