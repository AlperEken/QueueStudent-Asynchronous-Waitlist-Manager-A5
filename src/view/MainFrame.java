package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;

public class MainFrame extends JFrame {
    private int width = 1200;
    private int height = 600;

    Controller controller;
    MainPanel panel;

    public MainFrame(Controller controller) {
        this.controller = controller;
        setupFrame();

    }

    //standard settings
    public void setupFrame() {
        final int offsetX = width / 5;
        final int offsetY = height / 5;

        //setSize(width, height);
        setPreferredSize(new Dimension(width + 20, height + 20));
        setTitle("Course Registration System by <your name>");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(offsetX, offsetY);

        panel = new MainPanel(controller, width, height);
        setContentPane(panel);
        pack();
        setResizable(false);
        //center to screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateStatusItemsList(String[] stringList, boolean clearList) {
        panel.getPnlEast().updateStatusList(stringList, clearList);
     }
    public void updateStatusItemsList(String text) {
        panel.getPnlEast().updateStatusList(text);
    }
    public void errMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void updateEventLog(String[] stringList) {
        panel.getPnlEast().updateStatusList(stringList, false);
    }

    public void updateEventLog(String stringInfo) {
        panel.getPnlCenter().updateProductList(stringInfo);
    }

    public int getListIndex() {
        return panel.getPnlCenter().getListIndex();
    }
}
