import java.util.ArrayList;

public class PwdCommand implements Command{
    @Override
    public int execute(ArrayList<String> args) {
        if (CommandProcessor.currentDirectory.length() == 0) {
            System.out.println("Error: Incorrect type of directory");
            return 1;
        } else {
            System.out.println("Current direcroty: " + CommandProcessor.currentDirectory);
            return 0;
        }
    }
}
