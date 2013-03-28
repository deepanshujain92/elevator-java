import java.util.ArrayList;

public class Building extends AbstractBuilding {

	private EventBarrier[] UpCalls;
	private EventBarrier[] DownCalls;
	private EventBarrier[][] ExitBarriers;

	private ArrayList<Elevator> myElevators;

	private ArrayList<ArrayList<Rider>> Riders;

	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		UpCalls = new EventBarrier[numFloors];
		DownCalls = new EventBarrier[numFloors];
		ExitBarriers = new EventBarrier[numFloors][numElevators];
		Riders = new ArrayList<>();
		myElevators = new ArrayList<Elevator>();

		for (int i = 0; i < numFloors; i++) {
			UpCalls[i] = new EventBarrier();
			DownCalls[i] = new EventBarrier();

			Riders.add(new ArrayList<Rider>());
			for (int j = 0; j < numElevators; j++) {

				ExitBarriers[i][j] = new EventBarrier();
			}
		}
	}

	@Override
	public Elevator CallUp(int fromFloor) {
		// returns elevator that calling rider will get on
		UpCalls[fromFloor].arrive();
		return closestElevator(fromFloor);

	}

	private Elevator closestElevator(int fromFloor) {
		// int[] dists = new int[myElevators.size()];
		int i = Integer.MAX_VALUE;
		int minDist = 0;
		for (Elevator e : myElevators) {
			int dist = e.distFromFloor(fromFloor);
			minDist = Math.min(dist, i);
		}
		for (Elevator elevator : myElevators) {
			if (elevator.distFromFloor(fromFloor) == minDist) {
				return elevator;
			}
		}
		return null;
	}

	@Override
	public Elevator CallDown(int fromFloor) {
		DownCalls[fromFloor].arrive();
		return closestElevator(fromFloor);
	}

	public void setBarriers(EventBarrier[] Up, EventBarrier[] Down,
			EventBarrier[][] Exits) {
		UpCalls = Up;
		DownCalls = Down;
		ExitBarriers = Exits;
	}

	public void shareBarriers(Elevator elevator) {
		elevator.setBarriers(UpCalls, DownCalls, ExitBarriers);
	}

	public ArrayList<Elevator> getElevators() {
		return myElevators;
	}

	public void elevatorArrive(Elevator elevator, int floor) {

	}
}
