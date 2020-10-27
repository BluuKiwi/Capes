package xyz.leuo.capes.independent;

import dyorgio.runtime.run.as.root.RootExecutor;
import lombok.Getter;
import xyz.leuo.capes.independent.gui.listeners.ButtonListener;
import xyz.leuo.capes.independent.platforms.Platform;
import xyz.leuo.capes.independent.platforms.windows.WindowsPlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Locale;

public class CapesIndependent extends JFrame {

    public static CapesIndependent instance;

    private @Getter Runtime runtime;

    private @Getter Platform platform;
    private @Getter JPanel jPanel;
    private @Getter RootExecutor rootExecutor;

    private @Getter ButtonListener buttonListener;

    public CapesIndependent() throws IOException {
        this.runtime = Runtime.getRuntime();
        this.rootExecutor = new RootExecutor();

        String osType = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if(osType.contains("win")) {
            platform = new WindowsPlatform(runtime);
        } else {
            return;
        }

        this.buttonListener = new ButtonListener(this);

        this.setTitle("capes.pw");
        this.setSize(300, 150);

        jPanel = new JPanel();

        if(platform != null) {
//            JLabel label = new JLabel("With this tool, you can choose to either enable or disable our custom capes. We highly suggest you enable them. :)");
//            jPanel.add(label);

            JButton add = new JButton("Install Custom Capes");
            add.addActionListener(this.buttonListener);
            add.setActionCommand("install");

            JButton remove = new JButton("Remove Custom Capes");
            remove.addActionListener(this.buttonListener);
            remove.setActionCommand("uninstall");

            jPanel.add(add);
            jPanel.add(remove);
        } else {
            jPanel.add(new JLabel("Your platform is not supported at this time."));
        }

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().add(jPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String[] args) throws IOException {
        instance = new CapesIndependent();
    }
}
