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

		Path path = Paths.get(inputFileName);
		try (Scanner scanner = new Scanner(path)) {
			String firstLine = scanner.nextLine();
			String[] BuildingParams = firstLine.split(" ");
			Building building = new Building(
					Integer.parseInt(BuildingParams[0]),
					Integer.parseInt(BuildingParams[1]));
			while (scanner.hasNextLine()) {
				String info = scanner.nextLine();
				String[] elevatorParams = info.split(" ");
				Elevator elevator = new Elevator(
						Integer.parseInt(elevatorParams[1]),
						Integer.parseInt(elevatorParams[2]),
						Integer.parseInt(elevatorParams[3]));
				new Thread(elevator).start();
			}
		} catch (IOException e) {
			logger.info("File not found");
			e.printStackTrace();
		}

	}

}
