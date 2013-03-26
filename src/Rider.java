
public class Rider implements Runnable {

	private int riderID;
	private int startFloor;
	private int endFloor;
	private Building building;
	
	public Rider(Building building, int ID, int start, int end){
		riderID = ID;
		startFloor = start;
		endFloor = end;
		building = building;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (startFloor > endFloor){
			building.CallDown(startFloor);
		}
		else{
			building.CallUp(startFloor);
		}
		
		//call enter() on elevator
		//call requestFloor(endFloor)
		//call 

	}

}
