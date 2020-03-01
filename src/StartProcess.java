import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StartProcess implements Command {

    public static class InputRepeater extends Thread {
        InputStream is;

        InputRepeater(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1) {
                    System.out.write(c);
                    System.out.flush();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    public static class OutputRepeater extends Thread {
        OutputStream os;

        OutputRepeater(OutputStream os) {
            this.os = os;
        }

        public void run() {
            int intval=0;
            try {
                while ((intval = System.in.read())!=-1) {
                    os.write(intval);
                    os.flush();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    @Override
    public int execute(ArrayList<String> args) {
        try {
            ProcessBuilder procBuilder = new ProcessBuilder(args);
            procBuilder.redirectErrorStream(true);
            Process process = procBuilder.start();
            OutputStream stdout = process.getOutputStream();
            InputStream stdin = process.getInputStream();
            InputRepeater inputRepeater = new InputRepeater(stdin);
            OutputRepeater outputRepeater = new OutputRepeater(stdout);
            inputRepeater.start();
            outputRepeater.start();
            inputRepeater.join();
            outputRepeater.interrupt();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("The program finished successfully");
            } else {
                System.out.println("Error: The called program has crashed");
            }
            return exitVal;
        } catch(IOException ex) {
            System.out.println("Error: " + ex);
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
