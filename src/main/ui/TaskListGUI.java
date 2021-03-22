package ui;

import com.sun.javafx.fxml.LoadListener;
import model.ToDoList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// includes code taken and modified from C3-LectureLabStarter
public class TaskListGUI extends JPanel {
    private static final int VERTICAL_GAP = 15;
    private StudyBuddyApp studyBuddyApp;
    private ToDoList todaysTodos;
    private JTextField textField;
    private JButton saveButton;
    private JButton loadButton;
    private JButton addTaskButton;
    private JButton removeTaskButton;
    //private JList jList;



    public TaskListGUI(StudyBuddyApp studyBuddyApp) {
        this.studyBuddyApp = studyBuddyApp;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(VERTICAL_GAP));

        todaysTodos = new ToDoList();
        //jList = new JList(todaysTodos); // !!!


//        loadButton = new JButton("load");
//        LoadListListener loadListener = new LoadListListener(loadButton);
//        loadButton.setActionCommand("load");
//        loadButton.addActionListener(loadListener);
//
//        class LoadListListener implements ActionListener {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                studyBuddyApp.lo
//            }
//        }

   //     / MODIFIES: this, todaysTodos
        // EFFECTS: loads to do list from file
//        private void loadToDoList() {
//            try {
//                todaysTodos = jsonReader.read();
//                System.out.println("Loaded current todos from " + JSON_STORE);
//            } catch (IOException e) {
//                System.out.println("Unable to read from file: " + JSON_STORE);
//            }
//        }




    }
}
