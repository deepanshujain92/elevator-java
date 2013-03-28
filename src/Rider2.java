import java.util.logging.Level;
import java.util.logging.Logger;


public class Rider2 implements Runnable {

	private int riderID;
	private int startFloor;
	private int endFloor;
	private Building building;
	private Logger logger;
	
	public Rider2(Building b, int ID, int start, int end){
		riderID = ID;
		startFloor = start;
		endFloor = end;
		building = b;
	}
	@Override
	public void run() {
		Elevator2 e = null; 
		if (endFloor > startFloor) {
			e = building.CallUp(startFloor);
			logger.log(Level.INFO, "Rider calling up");
		}
		else {
			e = building.CallDown(startFloor);
			logger.log(Level.INFO, "Rider calling down");
		}
		// does not return until elevator is at the startFloor
		if (!e.doorsOpen) { // on elevator now
			logger.log(Level.INFO, "Rider requesting floor");
			e.RequestFloor(endFloor); // does not return until at endfloor
			logger.log(Level.INFO, "Rider has reached requested floor");
			e.Exit();
		}

	}

}
