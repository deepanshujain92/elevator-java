

public class EventBarrier extends AbstractEventBarrier {

	private volatile boolean isSignaled;
	private int _numWorkers;
	private int numWaiters;
	
	public EventBarrier(int numWorkers) {
		super(numWorkers);
		isSignaled = false;
		_numWorkers = numWorkers;
		numWaiters = 0;
		
	}

	@Override
	public synchronized void arrive() throws InterruptedException {
		// TODO Auto-generated method stub
		if (isSignaled) {
			// no need to wait, just continue
			// numWaiters++;
		}
		else {
			numWaiters++;
			//wait
			while (!isSignaled) {
				this.wait();
			}
		}
	}

	@Override
	public synchronized void raise() throws InterruptedException {
		// TODO Auto-generated method stub
		isSignaled = true;
		notifyAll();
		// wait for all workers to complete?
		while (waiters() > 0) {
			this.wait(); // wakes up when workers have completed 
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
