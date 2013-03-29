import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Building extends AbstractBuilding {

	private EventBarrier[] UpCalls;
	private EventBarrier[] DownCalls;
	private EventBarrier[][] ExitBarriers;

	private ArrayList<Elevator> myElevators;
	private Logger logger;

	private ArrayList<ArrayList<Rider>> Riders;
	private int finishedRiders;
	private int totalRiders;
	private boolean areRidersDone;

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
		
		areRidersDone = false;
	}

	@Override
	public Elevator CallUp(int fromFloor) {
		// returns elevator that calling rider will get on
		UpCalls[fromFloor].arrive();
		return closestElevator(fromFloor);

	}
	
	@Override
	public Elevator CallDown(int fromFloor) {
		DownCalls[fromFloor].arrive();
		return closestElevator(fromFloor);
	}

	/**
	 * Returns the closest elevator to a given floor
	 * @param fromFloor 
	 * @return Returns the closest elevator to a given floor
	 */
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
	
	/**
	 * Let's the building know how many riders must go through the building
	 */
	public void setTotalRiders(int totalRiders){
		this.totalRiders = totalRiders;
	}
	
	/**
	 * Let the building know another rider has finished
	 */
	public synchronized void riderFinished(){
		finishedRiders++;
		logger.log(Level.INFO, "Number of finished riders is {0}", finishedRiders);
		if (finishedRiders == totalRiders) {
			areRidersDone = true;
			logger.log(Level.INFO, "Building says riders are finished");
			for (Elevator e : myElevators){
				e.setAreRidersDone(areRidersDone);
			}
		}
	}
	
	public void setLogger (Logger l) {
		logger = l;
	}

}
