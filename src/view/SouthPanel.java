package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class is a JPanel subclass, placed at the bottom of the
// MainPanel.  It hosts two buttons, Start and Stop.
//The Listener method signals the controller class when the
//each of the buttons is clicked at run-time.
public class SouthPanel extends JPanel {
    private Controller controller;
    private ButtonGroup btnGroup;
    JButton btnOK, btnStop;

    public SouthPanel(Controller controller) {
        this.controller = controller;
        btnGroup = new ButtonGroup();
        GridLayout layout = new GridLayout(2, 1, 2, 2);  //fungerar inte!!
        JPanel pnlButtons = new JPanel();

        pnlButtons.setBorder(BorderFactory.createTitledBorder(""));

        btnOK = new JButton("Start Simulation");
        Dimension dim = new Dimension(160, 40);
        //btnOK.setSize(dim);
        btnOK.setPreferredSize(dim);

        btnStop = new JButton("Stop");
        // btnStop.setSize(dim);
        btnStop.setPreferredSize(dim);
        btnStop.setEnabled(false);

        pnlButtons.add(btnOK);
        pnlButtons.add(btnStop);

        add(pnlButtons);
        addListeners();
    }

    //This method connects the buttons to the event-handler
    //method receiving notification when the buttons are clicked
    //at run-time
    private void addListeners() {
        ActionListener listener = new ButtonActionListeners();
        btnOK.addActionListener(listener);
        btnStop.addActionListener(listener);
    }

    //Listener method connected to the buttons.
    //This method serves as the event-handler method for the
    //click event of the buttons. It is a call-back method
    //that will run automativcally when a button is clicked.
    //It informs the Controller class about the button that
    //is clicked so the controller can process an action.
    class ButtonActionListeners implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnOK)
                controller.buttonPressed(ButtonType.Start);
            else if (e.getSource() == btnStop) {
                controller.buttonPressed(ButtonType.Stop);
            }
        }
    }
}
