package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

//Class extending JPanel and hosting a JList for display a list of strings.
//The list serves as log of events.  The list is updated through the controller.
public class CenterPanel extends JPanel {
    private JList<String> list;//data has type Object[]
    DefaultListModel<String> listModel = new DefaultListModel<>(); //for adding single elements
    private Controller controller;
    private int width, height;

    public CenterPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        setBorder(BorderFactory.createTitledBorder("List of courses"));

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        setBorder(new CompoundBorder(border, margin));

        list = new JList<>(listModel);  //data of singlel elements

        list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        Font font = new Font("Courier New", Font.PLAIN, 14);
        list.setFont(font);

        JScrollPane s = new JScrollPane(list);
        //extra saker
        s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        s.setPreferredSize(new Dimension(width - 60, height - 12));

        //s.setLocation(12,12);
        addListener();
        add(s);
    }

    //When an item is highlighted on the list at run-time, this method returns the index of that item.
    //If you want to handle this event, i.e. to know which element is selected, use this method
    public int getListIndex() {
        return list.getSelectedIndex();
    }

    //This is methods updates the entire content of the list, clearing the items before
    //updating.
    public void updateProductList(String[] stringList) {
        list.setListData(stringList);
    }

    //This method appends a new string at the end of the list
    public void updateProductList(String stringInfo) {
        if (stringInfo == "")
            listModel.removeAllElements();
        else
            listModel.addElement(stringInfo);
    }

    //This method is automatically executed when an item on the list is selected.
    //The method calls an event-handler method in the controller (not used in this
    //assignment).
    public void addListener() {
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                int index = list.getSelectedIndex();
                if (index > -1) {
                    // controller.statusListBoxChanged(index);
                }
            }
        });
    }


}
