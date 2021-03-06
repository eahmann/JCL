package com.awd;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.awd.Constants.*;

public class CommandProcessor extends IoClass {
    // initialize working directory
    private File path = new File(System.getProperty("user.dir"));

    public void readCommands() {
        prompt();
        String command = scanner.nextLine();
        while (!command.equalsIgnoreCase("QUIT")) {
            processCommand(command);
            output.println();
            prompt();
            command = scanner.nextLine();
        }
    }

    private void processCommand(String input) {
        // Split command apart from arguments
        String[] command = input.split(" ", 2);
        // Match command using case-insensitive regex
        if (command[0].matches("(?i)^LIST")) {
            listCommand(command);
        } else if (command[0].matches("(?i)^CHDIR")) {
            chdirCommand(command);
        } else if (command[0].matches("(?i)^RUN")) {
            runCommand(command);
        } else if (command[0].matches("(?i)^REMOVE")) {
            removeCommand(command);
        } else if (command[0].matches("(?i)^RENAME")) {
            renameCommand(command);
        } else if (command[0].matches("(?i)^HELP")) {
            helpCommand();
        } else {
            output.println("Invalid command");
        }
    }

    private void prompt() {
        String currentDirectory = System.getProperty("user.dir");
        output.println("Current working directory " + currentDirectory);
        output.print("Enter Command:\n>> ");
    }

    // Convert array into string
    private String arrayConverter(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
            sb.append(str).append(' ');
        }
        return sb.substring(0, sb.length() - 1);
    }

    // Remove element from array at index
    private static String[] trimArray(String[] arr, int index) {
        if (arr == null
                || index < 0
                || index >= arr.length) {
            return arr;
        }
        String[] trimmedArray = new String[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            trimmedArray[k++] = arr[i];
        }
        return trimmedArray;
    }

    private void execute(String[] command) {
        try {
            Process p = Runtime.getRuntime().exec(arrayConverter(command), null, path);

            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error =
                    new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line = null;
            while ((line = input.readLine()) != null) {
                output.println(line);
            }
            while ((line = error.readLine()) != null) {
                output.println(line);
            }
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void helpCommand() {
        output.println(COMMANDS_AVAIL);
        for (String commands : COMMANDS) {
            output.print(commands + " ");
        }
        output.println();
    }

    private void listCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        command[0] = "ls -l";
        String filePath = "";
        if (command.length == 2) {
            filePath = command[1];
            File file = new File(filePath);
            if (file.exists() && file.isDirectory()) {
                execute(command);
            } else {
                output.println(PATH_ERROR);
            }
        } else {
            execute(command);
        }
    }

    private void chdirCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        System.setProperty("user.dir", command[1]);
        path = new File(System.getProperty("user.dir"));
        output.println("New Working Directory: " + path.getAbsolutePath());
    }

    private void runCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        // we don't need a base command, so we remove it
        command = trimArray(command, 0);
        execute(command);
    }

    private void removeCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        String filePath = "";
        command[0] = "rm";
        if (command.length == 2) {
            filePath = System.getProperty("user.dir") + "/" + command[1];
            File file = new File(filePath);
            if (file.exists()) {
                execute(command);
            } else {
                output.println(PATH_ERROR);
            }
        }
    }

    private void renameCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        String filePath = "";
        command[0] = "mv";
        if (command.length == 2) {
            String[] path = command[1].split(" ");
            filePath = System.getProperty("user.dir") + "/" + path[0];
            File file = new File(filePath);
            if (file.exists()) {
                execute(command);
            } else {
                output.println(PATH_ERROR);
            }
        }
    }
}
