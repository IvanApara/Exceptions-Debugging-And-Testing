import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final Marker INPUT_HISTORY_MARKER = MarkerManager.getMarker("INPUT_HISTORY");
    private static final Marker ERRORS_HISTORY_MARKER = MarkerManager.getMarker("ERRORS_HISTORY");
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();


        while (true) {
            String command = scanner.nextLine();
            try {

                String[] tokens = command.split("\\s+", 2);

                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                    LOGGER.info(INPUT_HISTORY_MARKER, "User add " + tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                    LOGGER.info(INPUT_HISTORY_MARKER, "User requested a list");
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                    LOGGER.info(INPUT_HISTORY_MARKER, "User remove " + tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                    LOGGER.info(INPUT_HISTORY_MARKER, "User requested a list");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                    LOGGER.info(INPUT_HISTORY_MARKER, "User requested a help");
                } else {
                    System.out.println(COMMAND_ERROR);
                    LOGGER.info(INPUT_HISTORY_MARKER, "User entered the wrong command");
                }
            } catch (RuntimeException exception){
                LOGGER.error(ERRORS_HISTORY_MARKER, exception.getMessage());
                System.out.println(exception.getMessage());
            }
        }
    }
}
