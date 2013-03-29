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
			myRide = building.CallDown(startFloor);
			logger.log(Level.INFO, "Rider %d has called down", riderID);
		}
		else{
			myRide = building.CallUp(startFloor);
			logger.log(Level.INFO, "Rider %d has called up", riderID);
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
