import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProducerConsumer {
	
	//instantiate a static StringBuilder instance sb
	public static StringBuilder sb = new StringBuilder();
	public static String timeStamp;
	private Producer producer;
	private Consumer consumer;
	
	ProducerConsumer(){
		this.producer = new Producer();
		this.consumer = new Consumer();
	}
	
	public static class Producer implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0; i < 20; i++){
				//lock on sb
				synchronized(sb){
					//while sb is not empty
					while(sb.length() != 0){
						//calls sb.wait()
						try {
							sb.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//sets sb to a string that represents the current time in milliseconds
					timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());

					sb.append(timeStamp);
                    //calls sb.notify() to notify that a new string is available
					sb.notify();
					//sleeps for 2 seconds to simulate a slow producer
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
			
		}
				
	}
	public static class Consumer implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			    for(int i = 0; i < 20; i++){
			    	//lock on sb
			    	synchronized(sb){
			    	//while sb is empty
					while(sb.length() == 0){
						    //calls sb.wait() to wait for the Producer to set sb
							try {
								sb.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					    //prints the sb string to the console
						System.out.println(sb.toString());
						//empties sb by setting its length to 0
						sb.setLength(0);
						//calls sb.notify() to notify that the buffer is empty
						sb.notify();
					}
				}			
		}
	}
		
	public static void main(String[] args) throws InterruptedException{
		ProducerConsumer pc = new ProducerConsumer();
		Thread t1 = new Thread(pc.producer,"Producer");

		Thread t2 = new Thread(pc.consumer,"Consumer");
		//both loops run concurrently
        t1.start();
        t2.start();
        //wait for a thread to complete its work
        t1.join();
        t2.join();

     
    }
		
		
}
		

