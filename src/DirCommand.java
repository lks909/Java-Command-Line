import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DirCommand implements Command {
    @Override
    public int execute(ArrayList<String> args) {
        try {
            File dir = new File(CommandProcessor.currentDirectory);
            File[] arrFiles = dir.listFiles();
            for (File file : arrFiles) {
                System.out.println(file.getName());
            }
            return 0;
        } catch(NullPointerException ex) {
            System.out.println("Error: " + ex);
            return 1;
        } catch(SecurityException ex) {
            System.out.println("Error: " + ex);
            return 1;
        }
    }
}
