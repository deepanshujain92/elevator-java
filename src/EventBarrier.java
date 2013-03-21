
public class EventBarrier extends AbstractEventBarrier {

	private volatile boolean isSignaled;
	private int _numWorkers;
	
	public EventBarrier(int numWorkers) {
		super(numWorkers);
		isSignaled = false;
		_numWorkers = numWorkers;
		
	}

	@Override
	public synchronized void arrive() throws InterruptedException {
		// TODO Auto-generated method stub
		if (isSignaled) {
			// no need to wait, just continue
			// numWaiters++ or numWaiters--
		}
		else {
			//numWaiters++ or numWaiters--
			//wait
			while (!isSignaled) {
				this.wait();
			}
		}
	}

	@Override
	public synchronized void raise() throws InterruptedException {
		// TODO Auto-generated method stub
		// notifyAll();
		// wait for all workers to complete?

	}

	@Override
	public synchronized void complete() throws InterruptedException {
		// TODO Auto-generated method stub
		//notify the producer that you have ... the bridge
		//wait for all workers to complete
		
	}

	@Override
	public int waiters() {
		// TODO Auto-generated method stub
		return 0;
	}
}
