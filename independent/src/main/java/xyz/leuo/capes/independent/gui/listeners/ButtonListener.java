package xyz.leuo.capes.independent.gui.listeners;

import xyz.leuo.capes.independent.CapesIndependent;
import xyz.leuo.capes.independent.platforms.Platform;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    private CapesIndependent program;
    public ButtonListener(CapesIndependent program) {
        this.program = program;
    }

    public void actionPerformed(ActionEvent e) {
        Platform platform = program.getPlatform();

        switch(e.getActionCommand().toLowerCase()) {
            case "install":
                platform.addEntry();
                JOptionPane.showMessageDialog(program, "Successfully installed/enabled capes.pw, you should now be able to see the capes upon relog.");
                break;
            case "uninstall":
                platform.removeEntry();
                JOptionPane.showMessageDialog(program, "Successfully removed/disabled capes.pw.");
                break;
        }
    }
}
