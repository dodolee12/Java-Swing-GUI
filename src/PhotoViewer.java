import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class PhotoViewer {
    // instance field to help with gui
    private PhotoContainer imageLibrary;
    JFrame frame;
    ArrayList<JPanel> panels;
    ArrayList<JPanel> ratingPanels;
    ArrayList<JButton> nextButtons;
    ArrayList<JButton> backButtons;
    ArrayList<JLabel> list;
    ArrayList<JRadioButton> radios;

    // constructor for photoviewer
    public PhotoViewer() {
        // initialize the Swing items
        this.frame = new JFrame();
        this.panels = new ArrayList<JPanel>();
        this.nextButtons = new ArrayList<JButton>();
        this.backButtons = new ArrayList<JButton>();
        this.radios = new ArrayList<JRadioButton>();
        this.ratingPanels = new ArrayList<JPanel>();
    }

    // show gui method
    public void showGUI() {
        // set size of frame
        frame.setSize(1600, 800);

        // create 5 panels for each picture
        for (int i = 0; i < 5; i++) {
            JPanel panel = new JPanel();
            this.panels.add(panel);
        }

        // create 5 next buttons
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("Next");
            this.nextButtons.add(button);

        }

        // create 5 back buttons
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("Back");
            this.backButtons.add(button);
        }

        // create radio buttons
        for (int i = 0; i < 5; i++) {
            JPanel ratingPanel = new JPanel(); // create new panels for ratings
            ButtonGroup ratingbuttons = new ButtonGroup(); // create button group so only one of the radio buttons can be selected
            for (int j = 1; j <= 5; j++) {
                if (this.imageLibrary.getPhotos().get(i).getRating() != j) { // check if it should automatically be selected
                    JRadioButton button = new JRadioButton("" + j);// textfield next to radio
                    ratingbuttons.add(button); // add to the button group
                    ratingPanel.add(button); // add to panel
                } else { // same thing except the button is autoselected as this is the correct rating.
                    JRadioButton button = new JRadioButton("" + j, true);
                    ratingbuttons.add(button);
                    ratingPanel.add(button);
                }

            }
            this.ratingPanels.add(ratingPanel);
        }

        // set layout for every panel
        GridBagLayout layout = new GridBagLayout(); // gridbag api
        for (int i = 0; i < 5; i++) {
            this.panels.get(i).setLayout(layout); // set gridbag layout to each panel
        }

        // create Arraylist of JLabels for later use
        this.list = new ArrayList<JLabel>();
        // add all 5 images to arraylist
        for (int i = 0; i < 5; i++) {
            JLabel img = new JLabel(new ImageIcon(this.imageLibrary.getPhotos().get(i).getImageData().getScaledInstance(1000,450,Image.SCALE_SMOOTH))); // jlabel api
            list.add(img); // add image to the list of images
        }

        GridBagConstraints grid1 = new GridBagConstraints(); // layout gridbag api

        // GRIDBAG FORMAT
        // I set all the gridy to 1 so it is in the middle of the gui
        // gridx defines the column
        // gridweightx and gridweighty are small for buttons so picture can take up most of the space
        // left column
        grid1.gridx = 0;
        grid1.gridy = 1;
        grid1.weightx = 0.5;
        grid1.weighty = 0.5;
        // add back button to each panel
        for (int i = 0; i < 5; i++) {
            this.panels.get(i).add(this.backButtons.get(i), grid1);
        }

        // middle column
        grid1.gridx = 1;
        grid1.gridy = 1;
        grid1.weightx = 3;
        grid1.weighty = 3;
        // add picture to each panel
        for (int i = 0; i < 5; i++) {
            this.panels.get(i).add(list.get(i), grid1);
        }

        // right column
        grid1.gridx = 2;
        grid1.gridy = 1;
        grid1.weightx = 0.5;
        grid1.weighty = 0.5;
        // add next button to each panel
        for (int i = 0; i < 5; i++) {
            this.panels.get(i).add(this.nextButtons.get(i), grid1);
        }

        ArrayList<JPanel> panelList = this.panels; // create new variables so it can be used inside anonymous class
        ArrayList<JPanel> ratingList = this.ratingPanels; // ^^

        Container c = frame.getContentPane(); // create container for pane so I can have 2 panels inside main frame

        // using borderlayout for the main fram
        c.add(panelList.get(0), BorderLayout.CENTER);
        c.add(ratingList.get(0), BorderLayout.SOUTH);

        // actionlistener on buttons
        for (int i = 0; i < 5; i++) {
            // create variable to use inside anonymous class
            int index = i;
            // add actionlistener on buttons
            this.backButtons.get(i).addActionListener(new ActionListener() {
                /**
                 * when button is pressed, remove everything from the container and add the panels that should be shown based on
                 * which button is pressed and validate and repaint the container
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index == 0) {
                        c.removeAll();
                        c.add(panelList.get(4), BorderLayout.CENTER);
                        c.add(ratingList.get(4), BorderLayout.SOUTH);
                        c.validate();
                        c.repaint();
                    } else {
                        c.removeAll();
                        c.add(panelList.get(index - 1), BorderLayout.CENTER);
                        c.add(ratingList.get(index - 1), BorderLayout.SOUTH);
                        c.validate();
                        c.repaint();
                    }
                }
            });
        }

        // actionlistener on buttons
        for (int i = 0; i < 5; i++) {
            int index = i;
            this.nextButtons.get(i).addActionListener(new ActionListener() {
                /**
                 * Same as above except this is for next buttons instead of back button
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index == 4) {
                        c.removeAll();
                        c.add(panelList.get(0), BorderLayout.CENTER);
                        c.add(ratingList.get(0), BorderLayout.SOUTH);
                        c.validate();
                        c.repaint();
                    } else {
                        c.removeAll();
                        c.add(panelList.get(index + 1), BorderLayout.CENTER);
                        c.add(ratingList.get(index + 1), BorderLayout.SOUTH);
                        c.validate();
                        c.repaint();
                    }
                }
            });
        }

        frame.setVisible(true); // set frame visible
    }

    /**
     * setter for image library
     * 
     * @param imageLibrary the image library to set
     */
    public void setImageLibrary(PhotoContainer imageLibrary) {
        this.imageLibrary = imageLibrary;

    }

    /**
     * getter for image library
     * 
     * @return the image library
     */
    public PhotoContainer getImageLibrary() {
        return this.imageLibrary;
    }

    // main method
    public static void main(String[] args) {
        PhotoViewer gui = new PhotoViewer();

        // image directory
        String dir = "images\\";

        // int array of ratings
        int[] rating = { 2, 3, 5, 1, 4 };

        // put all the photos in a library
        Album container = new Album("gui photos");
        for (int i = 1; i <= 5; i++) {
            Photo p = new Photo(dir + "img" + i + ".jfif", "caption", "2020-03-18", rating[i - 1]); // create photos
            p.loadImageData(p.getFilename()); // create buffered image object
            container.getPhotos().add(p); // add to container
        }
        gui.setImageLibrary(container); // set image library so it can be used in gui

        gui.showGUI();

    }

}
