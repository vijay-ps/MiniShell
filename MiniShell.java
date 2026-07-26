package MiniShell;
import java.io.*;
import java.util.*;

class AnsiColors {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
}

public class MiniShell {

    private static File currentWorkingDirectory = new File(System.getProperty("user.dir"));

    private static List<String> history = new ArrayList<>();
    private static List<String> help = new ArrayList<>(Arrays.asList(
            "1. cd <directory>: Change the current working directory to the specified directory.",
            "2. clear or cls: Clear the console screen.",
            "3. history: Display the command history.",
            "4. help: Display this help message.",
            "5. exit: Exit the MiniShell."));

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String user = System.getProperty("user.name");

        while (true) {

            System.out.print("\n" +
                    AnsiColors.GREEN + user +
                    AnsiColors.RESET +
                    "@" +
                    AnsiColors.BLUE + "MiniShell" +
                    AnsiColors.RESET +
                    ":" +
                    AnsiColors.YELLOW +
                    currentWorkingDirectory +
                    AnsiColors.RESET +
                    "> ");

            String command = reader.readLine().trim();

            history.add(command);

            if (command.isEmpty()) {
                continue;
            }

            else if (command.equalsIgnoreCase("exit")) {
                break;
            }

            else if (command.startsWith("cd ") || command.equals("cd")) {
                changeDir(command);
                continue;
            }

            else if (command.equalsIgnoreCase("clear") || command.equalsIgnoreCase("cls")) {
                clearScreen();
                continue;
            }

            else if (command.equalsIgnoreCase("history")) {
                printHistory(history);
                continue;
            } else if (command.equalsIgnoreCase("help")) {
                helpCommand(help);
                continue;
            }

            else {
                runCommand(command);
            }

        }

    }

    private static void runCommand(String command) {
        try {
            ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", command);
            pb.directory(currentWorkingDirectory);
            Process process = pb.start();
            process.waitFor();
            String res = read(process);
            System.out.println(res);
        } catch (IOException e) {
            System.out.println("The term '" + command + "' is invalid or could not be initialized.");
        } catch (InterruptedException e) {
            System.out.println("The command execution was interrupted.");
        }
    }

    private static String read(Process process) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            String res = sb.toString();
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void changeDir(String command) {
        if (command.equals("cd"))
            return;

        // cd" " to take count 0 1 2 after that command
        String path = command.substring(3).trim();

        File targetDir;

        if (path.startsWith("/") || path.matches("^[a-zA-z]:\\\\.*") || path.matches("^[a-zA-Z]:/.*")) {
            targetDir = new File(path);
            // for changin like C:\folder_name\vijay or F:/
        } else {
            targetDir = new File(currentWorkingDirectory, path);
            // like folder_name or .. etc
        }

        try {
            // check folder existing or not
            File canonicalDir = new File(targetDir.getCanonicalPath());
            if (canonicalDir.exists() && canonicalDir.isDirectory()) {
                currentWorkingDirectory = canonicalDir;
                System.out
                        .println(currentWorkingDirectory.getCanonicalPath());
            } else {
                System.out.println("cd: " + path + ": No such file or directory");
            }
        } catch (Exception e) {
            System.out.println("No such file or directory");
        }
    }

    private static void clearScreen() {
        try {

            System.out.print("\033[H\033[2J");
            // \033[H moves the cursor to the top-left corner of the screen, and \033[2J
            // clears the entire screen.
            System.out.flush();

        } catch (Exception e) {
            for (int i = 0; i < 30; i++) {
                System.out.println();
            }
        }
    }

    private static void printHistory(List<String> history) {
        for (int i = 0; i < history.size() - 1; i++) {
            System.out.println(AnsiColors.YELLOW + history.get(i) + AnsiColors.RESET);
        }
    }

    private static void helpCommand(List<String> help) {
        for (String x : help) {
            System.out.println(AnsiColors.PURPLE + x + AnsiColors.RESET);
        }
    }
}
