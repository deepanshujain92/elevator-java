import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.*;

public class ElevatorTester {

	private static String inputFileName;
	// use for logging errors, maybe when elevators leave floors and with how
	// many riders?
	private static Logger logger = Logger.getLogger("Elevator");

	public ElevatorTester(String filename) {
		inputFileName = filename;
	}

	public static void main(String args[]) {

		// take input file and create elevator/rider threads out of it
		logger.info("Writing to a log file, nbd");
		
		new ElevatorTester("src/elevatorTestInput.txt");
		
		Path path = Paths.get(inputFileName);
		try (Scanner scanner = new Scanner(path)) {
			String firstLine = scanner.nextLine();
			String[] BuildingParams = firstLine.split(" ");
			Building building = new Building(
					Integer.parseInt(BuildingParams[0]),
					Integer.parseInt(BuildingParams[1]));
			for (int i = 0; i < Integer.parseInt(BuildingParams[1]); i++) {
				Elevator elevator = new Elevator(
						Integer.parseInt(BuildingParams[0]), i,
						Integer.parseInt(BuildingParams[3]));
				new Thread(elevator).start();
			}
			while (scanner.hasNextLine()) {
				String info = scanner.nextLine();
				String[] riderParams = info.split(" ");
				Rider rider = new Rider(building,
						Integer.parseInt(riderParams[0]),
						Integer.parseInt(riderParams[1]),
						Integer.parseInt(riderParams[2]));
				new Thread(rider).start();
			}
			
			// at this point, building AND elevators AND riders are set up
			
		} catch (IOException e) {
			logger.info("File not found");
			e.printStackTrace();
		}

	}

}
