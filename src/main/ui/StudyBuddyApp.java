package ui;

import model.Task;
import model.ToDoList;

import java.util.List;
import java.util.Scanner;

// Virtual study buddy application
// NOTE: StudyBuddyApp(), runBuddy(), initialize(), and displayInputOptions() are taken and modified from Teller app
public class StudyBuddyApp {
    private ToDoList todaysTodos;
    private Scanner input;

    //EFFECTS: runs the study buddy application
    public StudyBuddyApp() {
        runBuddy();

    }

    // modified from Teller app
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBuddy() {
        boolean continueOn = true;
        String command;

        initialize();
        introDisplay();

        while (continueOn) {
            displayInputOptions();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("bye")) {
                continueOn = false;
            } else {
                processCommand(command);
            }

        }

        System.out.println();
        System.out.println("Great work! Hope to see you again soon:)");

    }

    // MODIFIES: this
    // EFFECTS: instantiates new to do list and scanner
    private void initialize() {
        todaysTodos = new ToDoList();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays prompt for setting up to do list
    private void introDisplay() {
        System.out.println("Hey, nice to see you!");
        System.out.println("(respond or press enter to begin session)");
        input.nextLine();

        System.out.println("What would you like to accomplish today?");
        System.out.println("(Tip: split larger tasks into multiple smaller ones to make them more manageable)");
    }

    // EFFECTS: displays menu of possible actions/inputs to user
    private void displayInputOptions() {
        System.out.println();
        System.out.println("Select from:");
        System.out.println("add -> add a task");
        System.out.println("remove -> remove a task");
        System.out.println("view -> view all todos");
        System.out.println("filter -> view only priority items");
        System.out.println("bye -> leave session");
    }

    // EFFECTS: processes user input
    private void processCommand(String command) {
        switch (command) {
            case "add":
                createTask();
                break;
            case "remove":
                removeTask();
                break;
            case "view":
                viewAllTasks();
                break;
            case "filter":
                viewPrioritiesOnly();
                break;
            default:
                System.out.println("Please choose from one of the options below");
                break;
        }
    }

    // MODIFIES: this, todaysTodos
    // EFFECTS: prompts user to input task information and creates the task
    private void createTask() {
        System.out.println();
        System.out.println("insert task name");
        input.nextLine();
        String name = input.nextLine();

        System.out.println("How long should it take? (in minutes)");

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
       // int time = input.nextInt();

        System.out.println("Is it one of your main priorities for today? (yes/no)");
        String yesNo = input.next();
        boolean isPriority = yesNoToBoolean(yesNo);

        todaysTodos.addTask(name, time, isPriority);

        int index = todaysTodos.getLength() - 1;
        printTaskAddedOrRemoved(index, "added");

    }

    // MODIFIES: this, todaysTodos
    // EFFECTS: removes task in given position from to do list
    private void removeTask() {

        if (todaysTodos.getLength() == 0) {
            System.out.println();
            System.out.println("(psst, there's no tasks to remove)");
            return;
        }

        viewAllTasksNumbered();
        System.out.println();
        System.out.println("Which number task would you like to remove?");

        int num = input.nextInt();

        printTaskAddedOrRemoved(num - 1, "removed");
        todaysTodos.removeTask(num);

    }

    // EFFECTS: displays all tasks in to do list numbered
    private void viewAllTasksNumbered() {
        System.out.println();
        System.out.println("TO DO:");
        int i = 1;

        for (Task t : todaysTodos.getToDos()) {

            System.out.println(i + ". " + t.getTaskView());
            i++;
        }

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

    // REQUIRES: "yes" or "no" in any letter casing
    // EFFECTS: returns true if input was yes, false if no
    private boolean yesNoToBoolean(String yesNo) {
        String response = yesNo.toLowerCase();

        return response.equals("yes");

    }


}
