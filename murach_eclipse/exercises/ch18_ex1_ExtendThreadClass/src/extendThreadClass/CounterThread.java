package extendThreadClass;

public class CounterThread extends Thread {
	private final int startingValue;
	
	public CounterThread(int startingValue) {
		this.startingValue = startingValue;
	}
	
	public void run() {
		System.out.println(this.getName() + " started.");
		for (int i = startingValue; i <= 10; i += 2) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			} 
		}
		System.out.println(this.getName() + " finished.");
	}
}
