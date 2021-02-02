package com.awd;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static com.awd.Constants.*;

public class App extends IoClass {
    public App() {
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        output.println(path);
    }

    public void run() {
        output.printf(WELCOME, APP_NAME, VERSION);
        output.println(INSTRUCT);
        CommandProcessor cp = new CommandProcessor();
        cp.readCommands();
        output.printf(GOOD_BYE, APP_NAME, VERSION);

    }
}
