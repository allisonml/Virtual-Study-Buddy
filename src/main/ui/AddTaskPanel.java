package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskPanel extends JPanel {
    private JTextField taskNameField;
    private JSpinner timeField;
    private JComboBox priority;
    private StudyBuddyApp studyBuddyApp;

    public AddTaskPanel(StudyBuddyApp studyBuddyApp) {
        this.studyBuddyApp = studyBuddyApp;

        JPanel taskInfoPane = getTaskInfoPanel();

        // taskInfoPane.add(priorityPanel, BoxLayout.X_AXIS);

//        JButton cancelButton = new JButton("cancel");
//        cancelButton.setActionCommand("cancel");
//        cancelButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // !!!
//            }
//        });

        JButton addTaskButton = new JButton("Add task");
        addTaskButton.setActionCommand("Add task");
        addTaskButton.setBackground(Color.yellow);
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                addNewTask();
            }
        });

        JPanel addTaskPanel = new JPanel();
        addTaskPanel.setLayout(new BorderLayout());
        addTaskPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addTaskPanel.setAlignmentY(studyBuddyApp.CENTER_ALIGNMENT);
        addTaskPanel.add(taskInfoPane, BorderLayout.NORTH);
        addTaskPanel.add(addTaskButton, BorderLayout.SOUTH);

        addTaskPanel.setSize(40,40);
        addTaskPanel.setVisible(true);
        studyBuddyApp.add(addTaskPanel);

    }

    private JPanel getTaskInfoPanel() {
        JPanel namePanel = getInsertNamePanel();

        JPanel timePanel = getInsertTimePanel();

        JPanel priorityPanel = getInputPriorityPanel();

        JPanel taskInfoPane = new JPanel();
        taskInfoPane.setLayout(new BoxLayout(taskInfoPane, BoxLayout.Y_AXIS));
        taskInfoPane.add(priorityPanel, BoxLayout.X_AXIS);
        taskInfoPane.add(timePanel, BoxLayout.X_AXIS);
        taskInfoPane.add(namePanel, BoxLayout.X_AXIS);
        return taskInfoPane;
    }

    private JPanel getInputPriorityPanel() {
        priority = new JComboBox(yesNo);
        priority.setSelectedIndex(0);
        JLabel priorityPrompt = new JLabel("Is it one of your main priorities for today?");
        JPanel priorityPanel = new JPanel();
        priorityPanel.setLayout(new BoxLayout(priorityPanel, BoxLayout.X_AXIS));
        priorityPanel.add(priority,BoxLayout.X_AXIS);
        priorityPanel.add(priorityPrompt, FlowLayout.LEFT);
        priorityPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return priorityPanel;
    }

    private JPanel getInsertTimePanel() {
        timeField = new JSpinner();
        JLabel timePrompt = new JLabel("How long should it take?");
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        timePanel.add(timeField,BoxLayout.X_AXIS);
        timePanel.add(timePrompt, FlowLayout.LEFT);
        timePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return timePanel;
    }


    private JPanel getInsertNamePanel() {
        taskNameField = new JTextField(20);
        taskNameField.setSize(30, 10);
        taskNameField.setBorder(BorderFactory.createLineBorder(Color.green));
        JLabel inputNamePrompt = new JLabel("Task name:");
        inputNamePrompt.setVisible(true);// !!!
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(taskNameField,BoxLayout.X_AXIS);
        namePanel.add(inputNamePrompt, FlowLayout.LEFT);
        namePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return namePanel;
    }

    String[] yesNo = {"yes", "no"};

    public void addNewTask() {
        String name = taskNameField.getText();
        int time = (int) timeField.getValue();
        boolean isPriority = false;
        if (priority.getSelectedItem() == "yes") {
            isPriority = true;
        }

        studyBuddyApp.getToDoList().addTask(name, time, isPriority);

//        String scribbleSoundFile = "scribbleSound.mp3";
//        Media scribbleSound;
//        scribbleSound = new Media("./data/scribbleSound.mp3");
//        MediaPlayer mediaPlayer = new MediaPlayer(scribbleSound);
//        mediaPlayer.play();

        studyBuddyApp.updateListView();

        setVisible(false);


    }


}
