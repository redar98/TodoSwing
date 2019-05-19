package com.java.todolist.gui;

import com.java.todolist.core.PRIORITY;
import com.java.todolist.core.Task;
import com.java.todolist.core.TaskDecorators.HighPriorityTask;
import com.java.todolist.core.TaskDecorators.LowPriorityTask;
import com.java.todolist.core.TaskDecorators.NormalPriorityTask;
import com.java.todolist.core.TaskDecorators.TaskDecorator;
import com.java.todolist.core.TodoList;
import com.java.todolist.core.TodoListProxy;

import javax.swing.*;
import java.util.Calendar;

public class TodoListModel extends AbstractListModel<Task> {
    private static final long serialVersionUID = 1L;
    private static final TodoListModel instance = new TodoListModel();

    private TodoList todoList;

    private TodoListModel() {
        this.todoList = new TodoListProxy();
    }

    public static TodoListModel getInstance() {
        return instance;
    }

    public void moveUp(int i) {
        this.todoList.moveUp(i);
        this.fireContentsChanged(this, i - 1, i);
    }

    public void moveDown(int i) {
        this.todoList.moveDown(i);
        this.fireContentsChanged(this, i, i + 1);
    }

    public void removeAt(int i) {
        this.todoList.removeAt(i);
        this.fireContentsChanged(this, i, i);
    }

    public void add(String taskMessage, PRIORITY priority) {
        Task builtTask = new Task.Builder(taskMessage)
                .setAddedDate(Calendar.getInstance().getTime())
                .build();

        TaskDecorator newTask = null;

        switch (priority) {
            case HIGH:
                newTask = new HighPriorityTask(builtTask);
                break;
            case NORMAL:
                newTask = new NormalPriorityTask(builtTask);
                break;
            case LOW:
                newTask = new LowPriorityTask(builtTask);
                break;
        }

        this.todoList.add(newTask);
        this.fireContentsChanged(this,
                this.todoList.getSize() - 1, this.todoList.getSize() - 1);
    }

    public long getHighPriorityTasksNumber() {
        return todoList.getList().stream().filter(t -> t.getPriority() == PRIORITY.HIGH).count();
    }

    @Override
    public int getSize() {
        return this.todoList.getSize();
    }

    @Override
    public Task getElementAt(int index) {
        return this.todoList.getElementAt(index);
    }
}
