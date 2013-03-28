import java.util.logging.Logger;

public class Elevator2 extends AbstractElevator implements Runnable {

	private EventBarrier[] UpCalls;
	private EventBarrier[] DownCalls;
	private EventBarrier[] ExitBarriers;

	private int currentFloor;
	private boolean goingUp;
	private int currentOccupancy;
	private EventBarrier currEntryBarrier;
	boolean doorsOpen;
	private boolean dropOffComplete;
	private Logger logger;

	public Elevator2(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// if elevatorID even, start going up, if odd, start going down
		if (_elevatorId % 2 == 0) {
			currentFloor = 1;
			goingUp = true;
		} else {
			currentFloor = _numFloors;
			goingUp = false;
		}

		doorsOpen = false;
		currentOccupancy = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	//this is simplistic, just a different idea
	@Override
	public void OpenDoors(int floor) {
		doorsOpen = true;
		currEntryBarrier = ExitBarriers[floor];
		currEntryBarrier.raise(); // doors open, riders exiting
		// returns when all waiting riders are off
		if (goingUp) {
			currEntryBarrier = UpCalls[floor];
			currEntryBarrier.raise();
		}
		else {
			currEntryBarrier = DownCalls[floor];
			currEntryBarrier.raise();
		}
		// now everyone is on the elevator
	}

	@Override
	public void CloseDoors() {
		doorsOpen = false;
	}

	@Override
	public void VisitFloor(int floor) {
		OpenDoors(floor);
		CloseDoors();
		dropOffComplete = true;
	}
	
	public boolean isDropOffComplete() {
		return dropOffComplete;
	}
	
	@Override
	public boolean Enter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void RequestFloor(int floor) {
		currEntryBarrier = ExitBarriers[floor];
		currEntryBarrier.arrive();

	}

}
