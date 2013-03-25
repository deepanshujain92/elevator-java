

public class EventBarrier extends AbstractEventBarrier {

	private volatile boolean isSignaled;
	private int _numWorkers;
	private int numWaiters;
	
	public EventBarrier() {
		super();
		isSignaled = false;
		//_numWorkers = numWorkers;
		numWaiters = 0;
		
	}

	@Override
	public synchronized void arrive() {
		
		numWaiters++; //whether or not the thread blocks
		
		if (isSignaled) {
			// no need to wait, just continue
			return;
		}
		else {
			
			//wait
			while (!isSignaled) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public synchronized void raise(){
		// TODO Auto-generated method stub
		isSignaled = true;
		notifyAll();
		// wait for all workers to complete?
		while (waiters() > 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // wakes up when workers have completed 
		}
		isSignaled = false;
		notifyAll(); // lets completed threads know to continue
	}

	@Override
	public synchronized void complete() throws InterruptedException {
		// TODO Auto-generated method stub
		//notify the producer that you have ... the bridge
		numWaiters--;
		notifyAll();
		//wait for all workers to complete
		while (isSignaled) {
			this.wait();
		}	
	}

	@Override
	public int waiters() {
		// TODO Auto-generated method stub
		return numWaiters;
	}
}
