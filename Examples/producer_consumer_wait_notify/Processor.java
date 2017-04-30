package producer_consumer_wait_notify;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
	
	private LinkedList<Integer> list= new LinkedList<Integer>();
	public final int LIMIT=10;
	private Object lock=new Object();

	public void produce() throws InterruptedException{
		
		int value=1;
		while(true){
			
			synchronized (lock) {
				
				while(list.size()==LIMIT){
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
			
		}
	}
	
	public void consume() throws InterruptedException{
		
		Random random=new Random();
		//Thread.sleep(random.nextInt(1000));
		
		while(true)
		{
			synchronized (lock) {
				
				while(list.size()==0){
					//System.out.println("size="+list.size());
					lock.wait();
				}
				System.out.print("List size is: "+ list.size());
				int value=list.removeFirst();
				System.out.println("; value is: "+ value);
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(1000));
		}
		
	}
}
