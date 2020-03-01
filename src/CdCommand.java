import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CdCommand implements Command {
    @Override
    public int execute(ArrayList<String> args) {
        try {
            Path path = Paths.get(args.get(0));
            if (Files.exists(path)) {
                CommandProcessor.currentDirectory = args.get(0);
                return 0;
            } else {
                System.out.println("Error: Incorrect path");
                return 1;
            }
        } catch(IndexOutOfBoundsException ex) {
            System.out.println("Error: "  + ex);
            return 1;
        }
    }
}
