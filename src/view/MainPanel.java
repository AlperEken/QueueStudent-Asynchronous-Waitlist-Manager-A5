package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class MainPanel extends JPanel {
    Controller controller;
    SouthPanel pnlSouth;
    BorderLayout layout;
    CenterPanel pnlCenter;
    EastPanel pnlEast;
    int width, height;

    public MainPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;
        setupPanel();
    }

    private void setupPanel() {
        layout = new BorderLayout();
        setLayout(layout);

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(12, 12, 12, 12);
        setBorder(new CompoundBorder(border, margin));

        //createInfoTextAreaAtEast();  //output at right
        //Buttons at south
        pnlSouth = new SouthPanel(controller);
        add(pnlSouth, layout.SOUTH);

        pnlCenter = new CenterPanel(controller, width / 2, height - 130);
        add(pnlCenter, BorderLayout.CENTER);

        pnlEast = new EastPanel(controller, width / 2, height - 130);

        add(pnlEast, BorderLayout.EAST);

    }

    public EastPanel getPnlEast() {return pnlEast;}

    public SouthPanel getPnlSouth() {
        return pnlSouth;
    }

    public CenterPanel getPnlCenter() {return pnlCenter;}
    //public int getListIndex(){return pnlEast.getListIndex();}

}

