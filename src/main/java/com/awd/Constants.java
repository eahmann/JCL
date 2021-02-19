package com.awd;

public class Constants {
    private Constants() {
    }

    static final String VERSION = "v1";
    static final String APP_NAME = "Java Command Line";
    static final String WELCOME = "Welcome to %s %s\n";
    static final String COMMANDS_AVAIL = "Available commands: ";
    static final String[] COMMANDS = {"LIST - Directory listing", "\nCHDIR - Change directory",
            "\nRUN - Execute file", "\nREMOVE - delete file or folder", "\nRENAME - rename file or folder",
            "\nHELP - display this message", "\nQUIT - exit program"};
    static final String INSTRUCT = "To begin enter command at prompt. Type HELP to see commands\n";
    static final String GOOD_BYE = "\nThank you for using %s %s\n";
    static final String CMD_EXEC = "is executing...";
    static final String PATH_ERROR = "Error file or folder does not exist. Check input and try again";
}

