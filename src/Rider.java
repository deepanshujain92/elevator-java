import java.util.logging.Level;
import java.util.logging.Logger;


public class Rider implements Runnable {

	private int riderID;
	private int startFloor;
	private int endFloor;
	private Building building;
	private Logger logger;
	
	public Rider(Building b, int ID, int start, int end){
		riderID = ID;
		startFloor = start;
		endFloor = end;
		building = b;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Elevator myRide;
		if (startFloor > endFloor){
			logger.log(Level.INFO, "Rider {0} is calling down", riderID);
			myRide = building.CallDown(startFloor);
			logger.log(Level.INFO, "Rider {0} has called down", riderID);
		}
		else{
			logger.log(Level.INFO, "Rider {0} is calling up", riderID);
			myRide = building.CallUp(startFloor);
			logger.log(Level.INFO, "Rider {0} has called up", riderID);

		}
		
		//call enter() on elevator
		myRide.Enter();
		myRide.RequestFloor(endFloor);
 
		building.riderFinished();
	}
	
	public void setLogger(Logger l) {
		logger = l;
	}
	

}
