import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Elevator extends AbstractElevator implements Runnable {

	private EventBarrier[] UpCalls;
	private EventBarrier[] DownCalls;
	private EventBarrier[][] ExitBarriers;

	private int currentFloor;
	private boolean goingUp;
	private int currentOccupancy;
	private EventBarrier currEntryBarrier;
	private boolean doorsOpen;
	private Logger logger;
	private boolean areRidersDone;
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		//if elevatorID even, start going up, if odd, start going down
		if (_elevatorId % 2 == 0){
			currentFloor = 1;
			goingUp = true;
		}
		else {
			currentFloor = _numFloors;
			goingUp = false;
		}
		
		doorsOpen = false;
		currentOccupancy = 0;
	}

	@Override
	public void OpenDoors() {
		// TODO Auto-generated method stub
		doorsOpen = true;
		ExitBarriers[currentFloor][_elevatorId].raise();
		currEntryBarrier.raise();
	}

	@Override
	public void CloseDoors() {
		// TODO Auto-generated method stub
		doorsOpen = false;

	}

	@Override
	public void VisitFloor(int floor) {
		currentFloor = floor;
		if (goingUp){
			currEntryBarrier = UpCalls[currentFloor];
		}
		else {
			currEntryBarrier = DownCalls[currentFloor];
		}
		logger.log(Level.INFO, "Elevator at floor {0}", floor);
		OpenDoors();
		CloseDoors();
	}

	@Override
	public boolean Enter() {
		//don't enter if elevator full
		if(currentOccupancy == _maxOccupancyThreshold){
			return false;
		}
		//if not full, enter and increase occupancy, and let barrier know you've crossed
		else { 
			currentOccupancy++;
			try {
				currEntryBarrier.complete();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
			return true;
		}
	}

	@Override
	public void Exit() {
		//reduce elevator occupancy
		currentOccupancy--;
		//let barrier know you've crossed
		try {
			ExitBarriers[currentFloor][_elevatorId].complete();
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}

	}

	@Override
	public void RequestFloor(int floor) {
		// TODO Auto-generated method stub
		ExitBarriers[floor][_elevatorId].arrive();


		logger.log(Level.INFO, "Floor {0} requested in Elevator", floor);
		
		Exit();
	}
	
	public boolean areDoorsOpen(){
		return doorsOpen;
	}
	
	public void setBarriers(EventBarrier[] Up, EventBarrier[] Down, EventBarrier[][] Exits){
		UpCalls = Up;
		DownCalls = Down;
		ExitBarriers = Exits;
	}
	
	@Override
	public void run(){
		// logic for knowing which floor to visit next
		// opendoor, closedoor
		// eventbarriers in elevator and in building
		// logger.log(Level.INFO, "Current floor is ", currentFloor)
		int nextFloor = 0;
		
		while (!areRidersDone){
			if (goingUp && currentFloor < _numFloors | currentFloor == 0){
				goingUp = true;
				nextFloor = currentFloor + 1;
			}
			else {
				goingUp = false;
				nextFloor = currentFloor - 1;
			}
			VisitFloor(nextFloor);
		}
	}

	public void setLogger(Logger l) {
		logger = l;
	}
	
	
	
	/**
	 * Tells you whether riders have finished
	 * @return
	 */
	public boolean areRidersDone(){
		return areRidersDone;
	}
	
	public void setAreRidersDone(boolean areTheyDone){
		areRidersDone = areTheyDone;
	}
	
	public int distFromFloor(int fromFloor) {
		return Math.abs(fromFloor-currentFloor);
	}
}
