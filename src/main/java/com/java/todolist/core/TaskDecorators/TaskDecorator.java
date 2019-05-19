package com.java.todolist.core.TaskDecorators;

import com.java.todolist.core.PRIORITY;
import com.java.todolist.core.Task;

public abstract class TaskDecorator extends Task {

    private Task decoratedTask;

    public TaskDecorator(Task task) {
        super(task.getMessage(), task.getAddedDate());
        this.decoratedTask = task;
    }

    public abstract PRIORITY getPriority();
}
