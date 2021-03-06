package ui;

import exceptions.InvalidTaskNameException;
import exceptions.InvalidTaskTimeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.StudyBuddyApp.FONT_BOLDED;
import static ui.StudyBuddyApp.FONT_UNBOLDED;

// Represents a panel for adding tasks to to do list
public class AddTaskPanel extends JPanel {
    private static final Color COLOUR_SCHEME = new Color(242, 245, 95);
    private JTextField taskNameField;
    private JSpinner timeField;
    private JCheckBox priority;
    private final StudyBuddyApp studyBuddyApp;

    // EFFECTS: constructs an Add Task panel with the given StudyBuddyApp
    public AddTaskPanel(StudyBuddyApp studyBuddyApp) {
        this.studyBuddyApp = studyBuddyApp;
        setPanel();

    }

    // MODIFIES: this
    // EFFECTS: adds components and formats this
    private void setPanel() {
        //create components
        JPanel taskInfoPane = getTaskInfoPanel();
        JButton addTaskButton = setButton();

        //format panel
        setLayout(new BorderLayout());
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setAlignmentY(studyBuddyApp.CENTER_ALIGNMENT);
        setBorder(BorderFactory.createLineBorder(Color.lightGray));
        add(taskInfoPane, BorderLayout.CENTER);
        add(addTaskButton, BorderLayout.SOUTH);

        setSize(40, 40);
        setVisible(true);
    }

    // EFFECTS: creates a button for adding a new task to list
    private JButton setButton() {
        JButton addTaskButton = new JButton("Add task");
        addTaskButton.setActionCommand("Add task");
        addTaskButton.setBackground(COLOUR_SCHEME);
        addTaskButton.setFont(FONT_BOLDED);
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewTask();
            }
        });
        return addTaskButton;
    }

    // EFFECTS: constructs and formats panel for task field input
    private JPanel getTaskInfoPanel() {
        JPanel namePanel = getInsertNamePanel();
        JPanel timePanel = getInsertTimePanel();
        JPanel priorityPanel = getInputPriorityPanel();
        reset();

        JPanel taskInfoPane = new JPanel();
        taskInfoPane.setLayout(new BoxLayout(taskInfoPane, BoxLayout.Y_AXIS));
        taskInfoPane.add(priorityPanel, BoxLayout.X_AXIS);
        taskInfoPane.add(timePanel, BoxLayout.X_AXIS);
        taskInfoPane.add(namePanel, BoxLayout.X_AXIS);
        return taskInfoPane;
    }

    // MODIFIES: this
    // EFFECTS: constructs a panel with text field and prompt to insert task name
    private JPanel getInsertNamePanel() {
        taskNameField = new JTextField(20);
        taskNameField.setSize(30, 10);
        //taskNameField.setMaximumSize(new Dimension(100, 100));
        JPanel namePanel = createFieldInputPanel(taskNameField, "Task name:");

        return namePanel;
    }

    // MODIFIES: this
    // EFFECTS: constructs a panel with spinner and prompt to input task minutesNeeded
    private JPanel getInsertTimePanel() {
        timeField = new JSpinner();
        JPanel timePanel = createFieldInputPanel(timeField, "How long should it take?");
        return timePanel;
    }

    // MODIFIES: this
    // EFFECTS: constructs a panel with combo box and prompt to input priority
    private JPanel getInputPriorityPanel() {
        priority = new JCheckBox();
        priority.setBackground(COLOUR_SCHEME);
        JPanel priorityPanel = createFieldInputPanel(priority, "Is it one of your main priorities for today?");

        return priorityPanel;
    }

    // MODIFIES: component
    // EFFECTS: formats a panel with given prompt label and component
    private JPanel createFieldInputPanel(JComponent component, String prompt) {
        component.setBorder(BorderFactory.createLineBorder(COLOUR_SCHEME));
        component.setFont(FONT_UNBOLDED);
        JLabel promptLabel = new JLabel(" " + prompt + " ");
        promptLabel.setFont(FONT_UNBOLDED);

        JPanel fieldInputPanel = new JPanel();
        fieldInputPanel.setLayout(new BoxLayout(fieldInputPanel, BoxLayout.X_AXIS));
        fieldInputPanel.add(component, BoxLayout.X_AXIS);
        fieldInputPanel.add(promptLabel, FlowLayout.LEFT);
        fieldInputPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldInputPanel.setMaximumSize(new Dimension(1000,100));
        return fieldInputPanel;
    }

    // MODIFIES: this
    // EFFECTS: if input is invalid, displays problem on JOptionPane,
    //          otherwise creates new task from input and adds task to list
    private void addNewTask() {
        try {
            String name = taskNameField.getText();
            int time = (int) timeField.getValue();
            boolean isPriority = priority.isSelected();

            studyBuddyApp.getToDoList().addTask(name, time, isPriority);

            studyBuddyApp.playSound("scribbleSound.wav");

            java.util.Timer timer = new java.util.Timer();
            timer.schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            studyBuddyApp.updateListView();
                            reset();
                            timer.cancel();
                        }
                    },
                    2300
            );
            //setVisible(false);
        } catch (InvalidTaskNameException e) {
            JOptionPane.showMessageDialog(this, "Please input a task name");

        } catch (InvalidTaskTimeException e) {
            JOptionPane.showMessageDialog(this, "Time required must be greater than 0");
        }

    }

    // MODIFIES: this
    // EFFECTS: resets input components
    public void reset() {
        taskNameField.setText("");
        timeField.setValue(30);
        priority.setSelected(false);
    }


}
