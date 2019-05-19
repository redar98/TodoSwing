package com.java.todolist.core.TaskDecorators;

import com.java.todolist.core.PRIORITY;
import com.java.todolist.core.Task;
import lombok.Getter;

public class LowPriorityTask extends TaskDecorator {
    @Getter
    private PRIORITY priority;

    public LowPriorityTask(Task task){
        super(task);
        this.priority = PRIORITY.LOW;
    }
}
