package com.java.todolist.core.TaskDecorators;

import com.java.todolist.core.PRIORITY;
import com.java.todolist.core.Task;
import lombok.Getter;

public class NormalPriorityTask extends TaskDecorator {
    @Getter
    private PRIORITY priority;

    public NormalPriorityTask(Task task){
        super(task);
        this.priority = PRIORITY.NORMAL;
    }
}
