import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

public class ElevatorTester {

	// use for logging errors, maybe when elevators leave floors and with how
	// many riders?
	private Logger logger;

	public static void main(String args[]) {
		new ElevatorTester("src/elevatorTestInput.txt");
	}

	public ElevatorTester(String filename) {
		Path path = Paths.get(filename);
		logger = Logger.getLogger("Elevator");
		try {
			FileHandler fh = new FileHandler("src/Elevator.log");
			logger.addHandler(fh);
			SimpleFormatter sf = new SimpleFormatter();
			fh.setFormatter(sf);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// take input file and create elevator/rider threads out of it
		try (Scanner scanner = new Scanner(path)) {
			String firstLine = scanner.nextLine();
			String[] BuildingParams = firstLine.split(" ");
			// first line is # floors, # elevators, total # of riders (?), max #
			// riders in an elevator
			Building building = new Building(
					Integer.parseInt(BuildingParams[0]),
					Integer.parseInt(BuildingParams[1]));
			building.setLogger(logger);
			
			logger.info("building made");
			
			//Make Ending boolean
			boolean areRidersDone = false;
			building.setAreRidersDone(areRidersDone);
			
			//make elevators
			for (int i = 0; i < Integer.parseInt(BuildingParams[1]); i++) {
				Elevator elevator = new Elevator(
						Integer.parseInt(BuildingParams[0]), i,
						Integer.parseInt(BuildingParams[3]));
				elevator.setLogger(logger);
				ArrayList<Elevator> e = building.getElevators();
				e.add(elevator);
				building.shareBarriers(elevator);
			    elevator.setAreRidersDone(areRidersDone);
				new Thread(elevator).start();
			}
			logger.info("elevators made and started");
			
			// line is riderID, floor it's on, floor it wants to go to
			ArrayList<Rider> riders = new ArrayList<Rider>();
			int totalRiders = 0;
			while (scanner.hasNextLine()) {
				String info = scanner.nextLine();
				String[] riderParams = info.split(" ");
				Rider rider = new Rider(building,
						Integer.parseInt(riderParams[0]),
						Integer.parseInt(riderParams[1]),
						Integer.parseInt(riderParams[2]));
				rider.setLogger(logger);
				totalRiders++;
				riders.add(rider);
			}
			building.setTotalRiders(totalRiders);
			for (Rider rider : riders) {
				new Thread(rider).start();
			}
			logger.info("riders made and started");

		} catch (IOException e) {
			logger.info("File not found");
			e.printStackTrace();
		}
	}	
}


