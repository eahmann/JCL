package com.awd;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
        } else if (command[0].matches("(?i)^RUN ")) {
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

    private void execute(String[] command) {
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec(command[0], null, path);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                output.println(s);
            p.waitFor();
            p.destroy();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void helpCommand() {
        // Could rework to allow more details from commands like "HELP RUN" to give RUN specific help
        // Maybe use HashMap (dictionary) ??
        output.println(COMMANDS_AVAIL);
        for (String commands : COMMANDS) {
            output.print(commands + " ");
        }
        output.println();
    }

    private void listCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        command[0] = "ls -l";
        execute(command);


    }

    private void chdirCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        System.setProperty("user.dir", command[1]);
        path = new File(System.getProperty("user.dir"));
        output.println("New Working Directory: " + path.getAbsolutePath());
    }

    private void runCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        command[0] = "ls -l";
        execute(command);
    }

    private void removeCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        command[0] = "ls -l";
        execute(command);
    }

    private void renameCommand(String[] command) {
        output.println(command[0].toUpperCase() + " " + CMD_EXEC);
        command[0] = "ls -l";
        execute(command);
    }
}
