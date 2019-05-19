package com.java.todolist.core;

import com.java.todolist.core.TaskDecorators.TaskDecorator;

import java.util.Iterator;
import java.util.List;

public class TodoListProxy implements TodoList {

    private TodoListImpl todoList;

    public TodoListProxy() {
        this.todoList = new TodoListImpl();
    }

    @Override
    public void moveUp(int i) {
        todoList.moveUp(i);
    }

    @Override
    public void moveDown(int i) {
        todoList.moveDown(i);
    }

    @Override
    public void add(TaskDecorator item) {
        todoList.add(item);
        System.out.println(item.getPriority() + " priority task '" + item.getMessage() + "' added on " + item.getAddedDate());
    }

    @Override
    public void removeAt(int i) {
        todoList.removeAt(i);
    }

    @Override
    public int getSize() {
        return todoList.getSize();
    }

    @Override
    public TaskDecorator getElementAt(int i) {
        return todoList.getElementAt(i);
    }

    @Override
    public Iterator<TaskDecorator> iterator() {
        return todoList.iterator();
    }

    @Override
    public List<TaskDecorator> getList() {
        return todoList.getList();
    }
}
