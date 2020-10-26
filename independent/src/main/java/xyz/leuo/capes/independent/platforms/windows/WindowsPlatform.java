package xyz.leuo.capes.independent.platforms.windows;

import xyz.leuo.capes.independent.platforms.Platform;

import java.io.File;
import java.io.FileWriter;

public class WindowsPlatform extends Platform {

    public WindowsPlatform(Runtime runtime) {
        this.name = "Windows";
        this.runtime = runtime;
    }

    public void addEntry() {
        try {
            runtime.exec("C:\\Windows\\System32\\Elevate.exe");
            File file = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append("\ntest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeEntry() {

    }
}
