
public class Building extends AbstractBuilding {

	private EventBarrier[][] UpCalls;
	private EventBarrier[][] DownCalls;
	private EventBarrier[][] ExitBarriers;
	
	public Building(int numFloors, int numElevators) {
		super(numFloors, numElevators);
		// TODO Auto-generated constructor stub
		UpCalls = new EventBarrier[numFloors][numElevators];
		DownCalls = new EventBarrier[numFloors][numElevators];
		ExitBarriers = new EventBarrier[numFloors][numElevators];
		
		for (int i = 0; i<numFloors; i++){
			for (int j = 0; j<numElevators; j++){
				UpCalls[i][j] = new EventBarrier();
				DownCalls[i][j] = new EventBarrier();
				ExitBarriers[i][j] = new EventBarrier();
			}
		}
		
	}

	@Override
	public void CallUp(int fromFloor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void CallDown(int fromFloor) {
		// TODO Auto-generated method stub

	}

}
