
public class Minstrel implements Runnable {
	
	private EventBarrier barrier;
	
	public Minstrel(EventBarrier bridge) {
		barrier = bridge;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		try {
			barrier.arrive();
			Thread.sleep(10);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			barrier.complete();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
