
public class EventBarrierTester {

	private static int numWorkers;
	private static boolean gate;
	
	public static void main(String args[]) {
		
		numWorkers = 10;
		EventBarrier bridge = new EventBarrier ();
		
		// set up minstrel threads
		for (int i = 0; i < numWorkers; i++) {
			Minstrel minstrel = new Minstrel(bridge);
			new Thread(minstrel).start();
		}
		System.out.println("Finished making minstrel threads");
		
		// set up gatekeeper thread
		GateKeeper gatekeeper = new GateKeeper(bridge);
		new Thread(gatekeeper).start();
		System.out.println("Finished making gatekeeper thread");
		
		
	}
	
}
