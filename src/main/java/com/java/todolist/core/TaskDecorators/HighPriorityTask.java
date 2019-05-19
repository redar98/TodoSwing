package com.java.todolist.core.TaskDecorators;

import com.java.todolist.core.PRIORITY;
import com.java.todolist.core.Task;
import lombok.Getter;

public class HighPriorityTask extends TaskDecorator {
    @Getter
    private PRIORITY priority;

    public HighPriorityTask(Task task){
        super(task);
        this.priority = PRIORITY.HIGH;
    }

    @Override
    public String getMessage(){
        return super.getMessage() + "*";
    }
}
