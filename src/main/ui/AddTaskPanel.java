package ui;

import model.Task;
import model.ToDoList;

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


        taskNameField = new JTextField(20);
        taskNameField.setSize(30, 10);
        taskNameField.setBorder(BorderFactory.createLineBorder(Color.green));
        JLabel inputNamePrompt = new JLabel("Task name:");
        inputNamePrompt.setVisible(true);// !!!
        JPanel namePanel = new JPanel();
        namePanel.add(taskNameField, BorderLayout.EAST);
        namePanel.add(inputNamePrompt, BorderLayout.WEST);

        timeField = new JSpinner();
        JLabel timePrompt = new JLabel("How long should it take?");
        JPanel timePanel = new JPanel();
        timePanel.add(timeField, BorderLayout.EAST);
        timePanel.add(timePrompt, BorderLayout.WEST);

        priority = new JComboBox(yesNo);
        priority.setSelectedIndex(0);
        JLabel priorityPrompt = new JLabel("Is it one of your main priorities for today?");
        JPanel priorityPanel = new JPanel();
        priorityPanel.add(priority, BorderLayout.EAST);
        priorityPanel.add(priorityPrompt, BorderLayout.WEST);

        JPanel taskInfoPane = new JPanel();
        taskInfoPane.add(namePanel, BorderLayout.NORTH);
        taskInfoPane.add(timePanel, BorderLayout.CENTER);
        taskInfoPane.add(priorityPanel, BorderLayout.SOUTH);

//        JButton cancelButton = new JButton("cancel");
//        cancelButton.setActionCommand("cancel");
//        cancelButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // !!!
//            }
//        });

        JButton addTaskButton = new JButton("Add task");
        addTaskButton.setActionCommand("Add task");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewTask();
            }
        });

        JPanel addTaskPanel = new JPanel();
        addTaskPanel.add(taskInfoPane, BorderLayout.NORTH);
        addTaskPanel.add(addTaskButton, BorderLayout.SOUTH);

        addTaskPanel.setVisible(true);

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
        studyBuddyApp.updateListView();

        setVisible(false);


    }


}
