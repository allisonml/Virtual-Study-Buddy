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
import java.util.ArrayList;
import java.util.List;


// Virtual study buddy application
// NOTE: StudyBuddyApp(), runBuddy(), initialize(), and displayInputOptions() are taken and modified from Teller app
// Includes code taken and modified from C3-LectureLabStarter, Oracle ListDemo
public class StudyBuddyApp extends JFrame {
    private static final String JSON_STORE = "./data/todolist.json";
    private static final String TODO_TIP = "Tip: split a large task into smaller ones to make them more manageable";
    private ToDoList todaysTodos;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JLabel promptLabel;
    private JLabel tipLabel;
    //private JTextField textField;
    private JList jlist;

    //EFFECTS: runs the study buddy application
    public StudyBuddyApp() {
        //super("Study Buddy UI");

        //should be able to just call runBuddy, which calls initialize and setUpDisplay

        initialize();


        tipLabel = new JLabel(TODO_TIP);
        tipLabel.setFont(new Font("Serif", Font.BOLD, 15));
        tipLabel.setAlignmentX(10);
        tipLabel.setAlignmentY(10);
        add(tipLabel, BorderLayout.NORTH);

        AddTaskPanel addTaskPanel = new AddTaskPanel(this);
//        addTaskPanel.setAlignmentX(10);
//        addTaskPanel.setAlignmentY(10);
        //add(addTaskPanel, BorderLayout.WEST);
        addTaskPanel.setVisible(true);
//        promptLabel = new JLabel("prompt label is visible");
//        add(promptLabel, BorderLayout.WEST);



        jlist = new JList(viewAllTasksNumbered().toArray());
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setSelectedIndex(0);
        jlist.setVisibleRowCount(10);
        jlist.setFixedCellWidth(this.getWidth() / 2);

        JScrollPane scrollPane = new JScrollPane(jlist);
        add(scrollPane, BorderLayout.EAST);


        JPanel bottomPane = setUpButtonPane();
        //bottomPane.add(textField);
        //bottomPane.add(addButton);

        add(bottomPane, BorderLayout.PAGE_END);

        pack();
        setVisible(true);

        //runBuddy(); // !!!
    }

    private JPanel setUpButtonPane() {

        JButton loadButton = setUpButton("load", Color.white);
        loadButton.addActionListener(e -> loadToDoList());

        JButton saveButton = setUpButton("save", Color.white);
        saveButton.addActionListener(e -> saveToDoList());

        JButton removeButton = setUpButton("remove", Color.red);
        removeButton.addActionListener(e -> removeTask());

        //        textField = new JTextField(20);
//        textField.setSize(30,10);
//        textField.setBorder(BorderFactory.createLineBorder(Color.green));
        //add(textField, BorderLayout.CENTER);

//        JButton addButton = new JButton("add");
//        addButton.setActionCommand("add");
//        addButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Task task = new Task(textField.getText());
//                todaysTodos.addTask(task); // !!!
//                updateListView();
//                //createTask();
//            }
//        });

        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new FlowLayout());
        bottomPane.add(loadButton);
        bottomPane.add(saveButton);
        bottomPane.add(removeButton);
        return bottomPane;
    }


    // modified from Teller app
    // MODIFIES: this
    // EFFECTS: processes user input
//    private void runBuddy() {
//        initialize();
//        introDisplay();
//
// put quit button

//        System.out.println();
//        System.out.println("Great work! Hope to see you again soon:)");
//    }

    // MODIFIES: this
    // EFFECTS: instantiates new to do list and scanner
    private void initialize() {
        todaysTodos = new ToDoList();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays prompt for setting up to do list
    private void introDisplay() {
        //change or initialize prompt label
        System.out.println("Hey, nice to see you!");
        System.out.println("(respond or press enter to begin session)");
        //input.nextLine();

        System.out.println("What would you like to accomplish today?");
        System.out.println("(Tip: split larger tasks into multiple smaller ones to make them more manageable)");
    }

    // EFFECTS: saves current to do list to file if user says yes to prompt
    public String promptToSaveString() {
        // pop up
        //can make yes/no button
        return "Before you leave, would you like to save your current to do list for later?";

//        if (yesNoToBoolean(toSave)) {
//            saveToDoList();
//        }
    }

    // EFFECTS: displays menu of possible actions/inputs to user
    private void displayInputOptions() {
        //menu bar / help screen
        System.out.println();
        System.out.println("Select from:");
        System.out.println("1 -> load previously saved todos from file");
        System.out.println("2 -> save current todos to file");
        System.out.println("3 -> add a task");
        System.out.println("4 -> remove a task");
        System.out.println("5 -> view all todos");
        System.out.println("6 -> filter todos to view only priority items");
        System.out.println("7 -> bye! (leave session)");
    }


    // MODIFIES: this, todaysTodos
    // EFFECTS: prompts user to input task information and creates the task
    private void createTask() {
        //maybe do in a pop up, prompts and 3 text fields in one pop up panel

        promptLabel.setVisible(false);
        AddTaskPanel addTaskPanel = new AddTaskPanel(this);
        this.add(addTaskPanel, BorderLayout.CENTER);
        addTaskPanel.setVisible(true);

        updateListView();


    }

    public JButton setUpButton(String name, Color colour) {
        JButton button = new JButton(name);
        button.setActionCommand(name);
        button.setBackground(colour);
        return button;
    }


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

    // EFFECTS: displays all tasks in to do list numbered
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

    // EFFECTS: displays all tasks with bullets in the given list with the given title
    private void viewListBulleted(List<Task> tasks, String name) {
        System.out.println();


        System.out.println(name);

        for (Task t : tasks) {
            System.out.println("> " + t.getTaskView());
        }

    }

    // EFFECTS: displays task list filtered to priority
    private void viewPrioritiesOnly() {
        ToDoList priorities = todaysTodos.getPrioritiesOnly();
        List<Task> priorityTasks = priorities.getToDos();

        viewListBulleted(priorityTasks, "TOP TODOS:");

    }

    // EFFECTS: displays task list
    private void viewAllTasks() {
        viewListBulleted(todaysTodos.getToDos(), "TO DO:");

    }

    // EFFECTS: print that the task of the given index is added or removed
    private void printTaskAddedOrRemoved(int index, String action) {
        Task task = todaysTodos.getToDos().get(index);
        String view = task.getTaskView();

        System.out.println();
        System.out.println("\"" + view + "\" was " + action + "!");
    }


    // EFFECTS: saves the to do list to file
    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(todaysTodos);
            jsonWriter.close();
            System.out.println("Saved current todos to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this, todaysTodos
    // EFFECTS: loads to do list from file
    private void loadToDoList() {
        try {
            todaysTodos = jsonReader.read();
            System.out.println("Loaded current todos from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        updateListView();
    }

    public ToDoList getToDoList() {
        return this.todaysTodos;
    }

    public void playSound(String fileName) {
        try {
            File f = new File("./data/" + fileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
