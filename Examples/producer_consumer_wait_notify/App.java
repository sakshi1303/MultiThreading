package producer_consumer_wait_notify;

import producer_consumer_wait_notify.Processor;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		final Processor processor = new Processor();
	
		Thread t1= new Thread(new Runnable(){
			
			public void run(){
				try {
					//System.out.println("producer thread");
					processor.produce();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		});
		
		Thread t2= new Thread(new Runnable(){
			
			public void run(){
				try {
					//System.out.println("consumer thread");
					processor.consume();
				} catch (InterruptedException e) {
			
					e.printStackTrace();
				}
			}
			
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();

	}


}

