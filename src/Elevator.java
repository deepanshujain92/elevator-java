
public class Elevator extends AbstractElevator {

	private EventBarrier[] UpCalls;
	private EventBarrier[] DownCalls;
	private EventBarrier[][] ExitBarriers;
	
	private int currentFloor;
	private int numFloors;
	private int maxOccupancy;
	private boolean goingUp;
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		// TODO Auto-generated constructor stub
		
		
		
	}

	@Override
	public void OpenDoors() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ClosedDoors() {
		// TODO Auto-generated method stub

	}

	@Override
	public void VisitFloor(int floor) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}
	
	public void setBarriers(EventBarrier[] Up, EventBarrier[] Down, EventBarrier[][] Exits){
		UpCalls = Up;
		DownCalls = Down;
		ExitBarriers = Exits;
	}

}
