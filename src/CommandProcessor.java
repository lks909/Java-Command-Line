import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class CommandProcessor {

    static final String[] AVAILABLE_COMMANDS = {"dir", "cd", "pwd", "!", "exit"};
    static String currentDirectory = "C:\\Users\\Alexey";

    public static String parseConfigXml(String commandName) throws IOException {
        Properties p = new Properties();
        try (FileInputStream fis = new FileInputStream("config.xml"))
        {
            p.loadFromXML(fis);
            String value = p.getProperty(commandName);
            return value;
        }

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        System.out.println(">Java Command Line");
        String command = "";
        while (true) {
            System.out.println("Enter command:");
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextLine();
            ParsedLogicalCommand plc = new ParsedLogicalCommand(command);
            if (!Arrays.asList(AVAILABLE_COMMANDS).contains(plc.getFirstCommand().getCommand())
                    && !Arrays.asList(AVAILABLE_COMMANDS).contains(plc.getSecondCommand().getCommand())) {
                System.out.println("Error: Unknown command.");
                continue;
            }
            if (plc.getFirstCommand().getCommand().equals("exit")) {
                break;
            }
            int commandExitCode = plc.run();
            System.out.println("---------------------------");
        }
    }



}
