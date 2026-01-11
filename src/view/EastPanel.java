package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

//Class extending JPanel and hosting a JList for display a list of strings.
//The list contains information on items that are loaned and available
// products.  The list is updated through the controller.
public class EastPanel extends JPanel {
    private Controller controller;
    private JList<String> list;
    private int width, height;
    DefaultListModel<String> listModel = new DefaultListModel<>(); //for adding single elements

    public EastPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        setBorder(BorderFactory.createTitledBorder(" Status "));

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        setBorder(new CompoundBorder(border, margin));

        //String[] data = addTestValue();
        list = new JList(listModel);  //data of singlel elements; //data has type Object[]

        Font font = new Font("Courier New", Font.PLAIN, 14);
        list.setFont(font);

        JScrollPane s = new JScrollPane(list);
        //extra saker
        s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        s.setPreferredSize(new Dimension(width - 60, height - 12));
        //s.setLocation(12,12);

        list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        // addListener();
        add(s);
    }

    //This method fills the JList with values contained in a list of strings.
    //If the value of clearList is true, the setListData method is called and
    //this method automatically clears the contents.
    public void updateStatusList(String[] stringList, boolean clearList) {

        if (clearList) {
            //list.setListData(stringList);  //setListData clears the list first
            listModel.removeAllElements();
        }
        for (int i = 0; i < stringList.length; i++) {
            listModel.addElement(stringList[i]);
        }

    }

    public void updateStatusList(String text) {
        listModel.addElement(text);
    }

}
