
public class GateKeeper implements Runnable {

	private EventBarrier barrier;
	
	public GateKeeper(EventBarrier bridge) {
		barrier = bridge;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			barrier.signal();
		//	barrier.notify();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		barrier.notifyAll(); // wake up sleeping minstrels
	}

}
