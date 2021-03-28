package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


// Virtual study buddy application
// Includes code taken and modified from Teller App, C3-LectureLabStarter, Oracle ListDemo
public class StudyBuddyApp extends JFrame {
    private static final String JSON_STORE = "./data/todolist.json";
    public static final Font FONT_BOLDED = new Font("SansSerif", Font.BOLD, 15);
    private static final String TODO_TIP = " Tip: split a large task into smaller ones to make them more manageable";

    private ToDoList todaysTodos;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JLabel tipLabel;
    private AddTaskPanel addTaskPanel;
    private JList jlist;
    private JPanel bottomButtonPane;
    //private JLabel promptLabel; // helpLabel
    //private JTextField textField;

    // EFFECTS: runs the study buddy application
    public StudyBuddyApp() {
        runBuddy();

    }

    // MODIFIES: this
    // EFFECTS: adds components and makes this visible
    private void setDisplay() {
        tipLabel = new JLabel(TODO_TIP);
        tipLabel.setFont(FONT_BOLDED);
        addTaskPanel = new AddTaskPanel(this);
        JScrollPane scrollPane = getScrollPane();
        JPanel bottomButtonPane = setUpButtonPane();
//        promptLabel = new JLabel("prompt label is visible");


        add(tipLabel, BorderLayout.NORTH);
        add(addTaskPanel, BorderLayout.WEST);
//        add(promptLabel, BorderLayout.WEST);
        add(scrollPane);
        add(bottomButtonPane, BorderLayout.PAGE_END);

        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: returns scroll pane with jlist of tasks
    private JScrollPane getScrollPane() {
        jlist = new JList(viewAllTasksNumbered().toArray());
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setSelectedIndex(0);
        jlist.setVisibleRowCount(10);
        jlist.setFixedCellWidth(this.getWidth() / 2);
        jlist.setFont(FONT_BOLDED);

        JScrollPane scrollPane = new JScrollPane(jlist);
        return scrollPane;
    }

    // MODIFIES: this
    // EFFECTS: returns a jpanel for load, save, remove buttons
    private JPanel setUpButtonPane() {

        JButton loadButton = setUpButton("load", Color.white);
        loadButton.addActionListener(e -> loadToDoList());

        JButton saveButton = setUpButton("save", Color.white);
        saveButton.addActionListener(e -> saveToDoList());

        JButton removeButton = setUpButton("remove", Color.red);
        removeButton.addActionListener(e -> removeTask());

        bottomButtonPane = new JPanel();
        bottomButtonPane.setLayout(new FlowLayout());
        bottomButtonPane.add(loadButton);
        bottomButtonPane.add(saveButton);
        bottomButtonPane.add(removeButton);
        //bottomButtonPane.add(textField);
        //bottomButtonPane.add(addButton);

        return bottomButtonPane;
    }


    // modified from Teller app
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBuddy() {
        initialize();
        setDisplay();
    }

    // MODIFIES: this
    // EFFECTS: instantiates new to do list and scanner
    private void initialize() {
        todaysTodos = new ToDoList();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // EFFECTS: returns a button with the given name and colour
    public JButton setUpButton(String name, Color colour) {
        JButton button = new JButton(name);
        button.setActionCommand(name);
        button.setBackground(colour);
        button.setFont(FONT_BOLDED);
        return button;
    }


    // EFFECTS: updates list data to the current todaysToDos
    public void updateListView() {
        jlist.setListData(viewAllTasksNumbered().toArray());
    }


    // MODIFIES: this, todaysTodos
    // EFFECTS: removes task in given position from to do list
    private void removeTask() {
        //promptLabel.setText("removing current selection...");
        //popup, label at top with question, view of list, number of indexes at bottom?
        // or just like in listDemo

        int num = jlist.getSelectedIndex();
        todaysTodos.removeTask(num);

        updateListView();

    }

    // EFFECTS: returns list of all tasks in to do list numbered
    private List<String> viewAllTasksNumbered() {
//        System.out.println();
//        System.out.println("TO DO:");
        List<String> taskViews = new ArrayList<>();
        int i = 1;

        for (Task t : todaysTodos.getToDos()) {

            taskViews.add(i + ". " + t.getTaskView());
            i++;
        }

        return taskViews;

    }


    // EFFECTS: saves the to do list to file
    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(todaysTodos);
            jsonWriter.close();
            //System.out.println("Saved current todos to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            tipLabel.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this, todaysTodos
    // EFFECTS: loads to do list from file
    private void loadToDoList() {
        try {
            todaysTodos = jsonReader.read();
            //System.out.println("Loaded current todos from " + JSON_STORE);
        } catch (IOException e) {
            tipLabel.setText("Unable to read from file: " + JSON_STORE);
        }
        updateListView();
    }

    // EFFECTS: returns todaysToDos
    public ToDoList getToDoList() {
        return this.todaysTodos;
    }

    // EFFECTS: plays the .wav file of the given name
    public void playSound(String fileName) {
        try {
            File soundFile = new File("./data/" + fileName);
            URL soundFileURL = soundFile.toURI().toURL();
            AudioInputStream soundEffect = AudioSystem.getAudioInputStream(soundFileURL);
            Clip clip = AudioSystem.getClip();
            clip.open(soundEffect);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
