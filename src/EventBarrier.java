
public class EventBarrier extends AbstractEventBarrier {

	private volatile boolean _isSignaled;
	private int _numWorkers;
	
	public EventBarrier(int numWorkers) {
		super(_numWorkers);
		_isSignaled = false;
		_numWorkers = numWorkers;
	}

	@Override
	public void arrive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raise() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void complete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int waiters() {
		// TODO Auto-generated method stub
		return 0;
	}
}
