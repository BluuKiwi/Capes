package xyz.leuo.capes.independent.platforms.windows;

import xyz.leuo.capes.independent.CapesIndependent;
import xyz.leuo.capes.independent.platforms.Platform;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class WindowsPlatform extends Platform {

    public WindowsPlatform(Runtime runtime) {
        this.name = "Windows";
        this.runtime = runtime;
    }

    public boolean addEntry() {
        AtomicBoolean b = new AtomicBoolean(true);
        try {
            CapesIndependent.instance.getRootExecutor().run(() -> {
                try {
                    File file = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    file.setWritable(true);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    for (int x = 0; x < reader.lines().count(); x++) {
                        if (reader.readLine().contains("api.capes.pw")) {
                            b.set(false);
                        }
                    }

                    if (b.get()) {
                        writer.append("api.capes.pw s.optifine.net");
                        writer.close();
                    }

                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            b.set(false);
        }

        return b.get();
    }

    public boolean removeEntry() {
        try {
            CapesIndependent.instance.getRootExecutor().run(() -> {
                try {
                    File file = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
                    file.setWritable(true);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    for (int x = 0; x < reader.lines().count(); x++) {
                        if (reader.readLine().contains("api.capes.pw")) {
                            writer.write(x);
                        }
                    }

                    writer.close();
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
