
public class Building extends AbstractBuilding {

	private EventBarrier[] UpCalls;
	private EventBarrier[] DownCalls;
	private EventBarrier[][] ExitBarriers;

	
	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		// TODO Auto-generated constructor stub
		UpCalls = new EventBarrier[numFloors];
		DownCalls = new EventBarrier[numFloors];
		ExitBarriers = new EventBarrier[numFloors][numElevators];
		
		for (int i = 0; i<numFloors; i++){
			UpCalls[i] = new EventBarrier();
			DownCalls[i] = new EventBarrier();
			for (int j = 0; j<numElevators; j++){

				ExitBarriers[i][j] = new EventBarrier();
			}
		}
		
	}

	@Override
	public void CallUp(int fromFloor) {
		UpCalls[fromFloor].arrive();
		//after returns, 

	}

	@Override
	public void CallDown(int fromFloor) {
		DownCalls[fromFloor].arrive();
	}
	
	public void setBarriers(EventBarrier[] Up, EventBarrier[] Down, EventBarrier[][] Exits){
		UpCalls = Up;
		DownCalls = Down;
		ExitBarriers = Exits;
	}


}
