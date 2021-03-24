package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import static ui.Prompts.*


// Virtual study buddy application
// NOTE: StudyBuddyApp(), runBuddy(), initialize(), and displayInputOptions() are taken and modified from Teller app
// Includes code taken and modified from C3-LectureLabStarter, Oracle ListDemo
public class StudyBuddyApp extends JFrame {
    private static final String JSON_STORE = "./data/todolist.json";
    private static final String TODO_TIP = "Tip: split larger tasks into multiple smaller ones to make them more manageable";
    private ToDoList todaysTodos;
    private Scanner input; // !!!
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JLabel promptLabel;
    private JLabel tipLabel;
    private TaskListGUI taskListArea;
    private JTextField textField;
    private JList jList;
    //= new JList(todaysTodos.getAllTaskNames().toArray(new String[0]));

    //EFFECTS: runs the study buddy application
    public StudyBuddyApp() {
        super("Study Buddy UI");
        todaysTodos = new ToDoList();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        tipLabel = new JLabel(TODO_TIP);
        add(tipLabel, BorderLayout.NORTH);

        AddTaskPanel addTaskPanel = new AddTaskPanel(this);
        add(addTaskPanel, BorderLayout.WEST);
        addTaskPanel.setVisible(true);
//        promptLabel = new JLabel("prompt label is visible");
//        add(promptLabel, BorderLayout.WEST);

        //createTaskList();

        //jList = new JList(todaysTodos.getToDos().toArray());
        //add(jList, BorderLayout.EAST);
        //jList.set


//        JButton enterButton = new JButton("enter");
//        textField = new JTextField(10);
//        enterButton.setActionCommand("enter");
//        enterButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                textField.
//            }
//        });

        jList = new JList(viewAllTasksNumbered().toArray());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setSelectedIndex(0);
        //jList.addListSelectionListener();
        jList.setVisibleRowCount(10);
        JScrollPane scrollPane = new JScrollPane(jList);
        add(scrollPane, BorderLayout.EAST);


        JButton loadButton = new JButton("load");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadToDoList();
            }
        });

        JButton saveButton = new JButton("save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToDoList();
            }
        });

        JButton removeButton = new JButton("remove");
        removeButton.setActionCommand("remove");
        removeButton.setBackground(Color.red);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        textField = new JTextField(20);
        textField.setSize(30,10);
        textField.setBorder(BorderFactory.createLineBorder(Color.green));
        //add(textField, BorderLayout.CENTER);

        JButton addButton = new JButton("add");
        addButton.setActionCommand("add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                todaysTodos.addTask(textField.getText(), 0, false); // !!!
                updateListView();
                //createTask();
            }
        });

        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));
        bottomPane.add(loadButton);
        bottomPane.add(saveButton);
        bottomPane.add(removeButton);
        bottomPane.add(textField);
        bottomPane.add(addButton);

        add(bottomPane, BorderLayout.PAGE_END);

        //add(loadButton, BorderLayout.SOUTH);

        //add other components

        pack();
        setVisible(true);


        //runBuddy(); // !!!

    }

//    private void createTaskList() {
//        todaysTodos = new ToDoList(); // !!!
//        taskListArea = new TaskListGUI(this);
//        add(taskListArea, BorderLayout.EAST);
//
//    }


    // modified from Teller app
    // MODIFIES: this
    // EFFECTS: processes user input
//    private void runBuddy() {
//        boolean continueOn = true;
//        String command;
//
//        initialize();
//        introDisplay();
//
//        while (continueOn) {
//            displayInputOptions();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("7")) {
//                promptToSave();
//                continueOn = false;
//            } else {
//                processCommand(command);
//            }
//
//        }
//
//        System.out.println();
//        System.out.println("Great work! Hope to see you again soon:)");
//
//    }

    // MODIFIES: this
    // EFFECTS: instantiates new to do list and scanner
    private void initialize() {
        todaysTodos = new ToDoList();
        //input = new Scanner(System.in);// !!!
    }

    // EFFECTS: displays prompt for setting up to do list
    private void introDisplay() {
        //change or initialize prompt label
        System.out.println("Hey, nice to see you!");
        System.out.println("(respond or press enter to begin session)");
        input.nextLine();

        System.out.println("What would you like to accomplish today?");
        System.out.println("(Tip: split larger tasks into multiple smaller ones to make them more manageable)");
    }

    // EFFECTS: saves current to do list to file if user says yes to prompt
    public void promptToSave() {
        // pop up
        System.out.println("Before you leave, would you like to save your current to do list for later?");
        System.out.println("1 -> yes");
        System.out.println("2 -> no");
        String toSave = input.next();
        if (yesNoToBoolean(toSave)) {
            saveToDoList();
        }
    }

    // EFFECTS: displays menu of possible actions/inputs to user
    private void displayInputOptions() {
        //menu bar
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

    public void updateListView() {
        jList.setListData(viewAllTasksNumbered().toArray());
    }

    private int processTimeInput() {
        boolean shouldRepeat = true;
        int time = 0;
        while (shouldRepeat) {
            if (input.hasNextInt()) {
                time = input.nextInt();
            } else {
                System.out.println("Please input an integer");
                input.next();
                continue;
            }
            shouldRepeat = false;

        }
        return time;

    }

    // MODIFIES: this, todaysTodos
    // EFFECTS: removes task in given position from to do list
    private void removeTask() {
        //promptLabel.setText("removing current selection...");

        int num = jList.getSelectedIndex();

        todaysTodos.removeTask(num);

        updateListView();




        //popup, label at top with question, view of list, number of indexes at bottom?
        // or just like in listDemo
//        if (todaysTodos.getLength() == 0) {
//            System.out.println();
//            System.out.println("(psst, there's no tasks to remove)");
//            return;
//        }
//
//        viewAllTasksNumbered();
//        System.out.println();
//        System.out.println("Which number task would you like to remove?");
//
//        int num = input.nextInt();
//
//        printTaskAddedOrRemoved(num - 1, "removed");


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

    // REQUIRES: "1" or "2"
    // EFFECTS: returns true if input was 1, false if 2
    private boolean yesNoToBoolean(String yesNo) {
        String response = yesNo.toLowerCase();

        return response.equals("1");

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


}
