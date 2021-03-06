# Your Virtual Study Buddy

## About

**Main features:**
- list of tasks to complete
   - can filter to shorter priority list to feel less overwhelmed
- interval timer to stay focused (classic pomodoro 25/5, 15min long break, potentially animedoro also)
- cute study buddy character with words of encouragement and reminders to help build good study habits

This app is meant to keep you focused and accountable for your independent studies. 
The timer and built-in task list helps to reduce procrastination while the cute interface motivates users to use it 
to stay on task and not disappoint their study buddy. The intended audience is high school and university students, though it should be usable for students as young as 13
and adult workers alike (maybe working from home!).  

I came up with this idea because I wanted something that helps keep me on task (hence the timer), 
motivates me to keep working (There's nothing like the satisfaction of crossing of each little task off a list!), 
and looks cute (because studying shouldn't be rigid and boring)! The addition of the character's messages to remind you, 
for example, to drink water or sit up straight help to encourage users to maintain healthy habits
while they may be studying for long periods of time. I may also add a feature to customize habit reminders during
break sessions (eg. do some stretches, tidy desk area, stream of consciousness) to allow users to make use of their 
few minutes of break time each day and be able to see its impact over time. Students have to study to succeed, but it 
shouldn't be the only thing they do. This app helps students to make sure to take care of themselves as a whole
while they study, helping them to stay healthy (unlike me getting lost in making this app and forgetting to eat dinner 
until 8pm), not burn out, and maybe have just a little more fun along the way.

If you look online, there are many "study with me" livestreams or videos with thousands or even millions of views.
This would be like an application of that where instead they have a cute virtual study buddy character/animal to study 
with, who will be available at any time, whether you have internet or not, and not only keeps you accountable for your
work, but also your health and habits.

Some potential additional features:
- Customizable intervals
- Choose your study buddy avatar
- Snack Recipe and/or Study Tips tab that becomes available when not on "focus" mode
- Customizable habits/reminder messages

## User Stories

Phase 1:
- As a user, I want to be able to add multiple tasks to a todo list
- As a user, I want to be able to delete a task from my todo list (based on its position in the list)
- As a user, I want to be able to view my todo list with their names and time required
- As a user, I want to be able to view my todo list filtered based on priority

Phase 2:
- As a user, I want to be able to save my current todo list to file
- As a user, I want to be able to load the most previously saved todo list from file
- As a user, I want to be prompted to save my current todos or not before I end the session and be able to do so if desired

Phase 4: Task 2
- Test and design a class in your model package that is robust.  You must have at least one method that throws a 
checked exception. You must have one test for the case where the exception is expected and another where the 
exception is not expected.
    - Class: ToDoList
    - Methods: addTask(), removeTask()
        - InvalidTaskNameException, InvalidTaskTimeException caught in addNewTask() in AddTaskPanel class
        - ArrayIndexOutOfBoundsException caught in removeTask() in StudyBuddyApp class
        
Phase 4: Task 3
- Make ToDoList implement Iterable < Task >
- Separate StudyBuddyApp into more classes to increase cohesion
    - eg. one class for the list scrollpane and remove button, one for buttonPane(to be load/save dropdown menu)
        - scrollpane observes ToDoList
    - StudyBuddyApp just puts panels on frame, sets up ToDoList and persistence related methods
- Reduce semantic coupling in StudyBuddyApp and AddTaskPanel 
    - Make an abstract GenericPanel class to be extended by each of the main panel classes(AddTaskPanel and others 
    extracted from  StudyBuddyApp), with all the common formatting methods and a protected StudyBuddyApp field
(- if any methods are still long with multiple operations, extract to delegate tasks to helper methods)