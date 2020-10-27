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

        boolean b;
        switch(e.getActionCommand().toLowerCase()) {
            case "install":
                b = platform.addEntry();
                if(b) {
                    JOptionPane.showMessageDialog(program, "Successfully installed/enabled capes.pw, you should now be able to see the capes upon relog.");
                } else {
                    JOptionPane.showConfirmDialog(program, "You already have capes.pw installed.");
                }
                break;
            case "uninstall":
                platform.removeEntry();
                JOptionPane.showMessageDialog(program, "Successfully removed/disabled capes.pw.");
                break;
        }

        System.exit(0);
    }
}
