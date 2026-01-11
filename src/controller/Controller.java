package controller;

import view.*;
import model.*;


import javax.swing.*;

public class Controller {
    MainFrame view;
    private RegistrationService registrationService;

    public Controller() {
        view = new MainFrame(this);  //view
        registrationService = new RegistrationService(this);
        DisplayInfo();
    }
    //You can delete this method
    private void DisplayInfo()
    {
        updateView("Here comes a list of courses.");
        updateView("The listbox is modelled in the view- CenterPanel.");
        updateView(" ");

        updateView("The Start button is activated in the, view > SouthPanel");
        updateView("It calls a controller method where you can start your simulation." );
        updateView("The Stop button is disabled as it is not used.");

        updateStatusList(new String[]{"This listbox serves as a notfication log.", "It is modelled in EastPanel"}, true);
        updateStatusList("Line 3"); //overleaded method called
    }

    /*
      The view triggers this method when one of the buttons on view are
      clicked.  Handle each event
     */
    public void buttonPressed(ButtonType button) {
        int index = 0;
        switch (button) {
            case Start:
                updateView("");  //clear contents
                updateView("Courses available for registration. . .");
                updateStatusList(new String[]{"Starting simulation . . ."}, true);
                updateStatusList("To be updated as simulation proceeds!");
                registrationService.startSimulation();
                //call your method to start simulation
                //use the listboxes to log notifications
                break;

            case Stop:
                //not implemented
                String [] testStrings = {"Line 1", "Line 2", "Line 3"};
                updateStatusList(testStrings, true);
                break;
        }
    }


    //To clear the lest, stringInfo should be "".
    public void updateView(String stringInfo) {
        SwingUtilities.invokeLater(() -> view.updateEventLog(stringInfo));
    }

    //Update the status litbox with an array of strings.
    public void updateStatusList(String[] itemsToAdd, boolean clearList) {
        SwingUtilities.invokeLater(() -> view.updateStatusItemsList(itemsToAdd, clearList));
    }

    //Update the status litbox with a single line of text.
    public void updateStatusList(String text) {
        SwingUtilities.invokeLater(() -> view.updateStatusItemsList(text));
    }





}

