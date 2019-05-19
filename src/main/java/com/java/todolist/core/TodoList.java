package com.java.todolist.core;

import com.java.todolist.core.TaskDecorators.TaskDecorator;

import java.util.Iterator;
import java.util.List;

public interface TodoList extends Iterable<TaskDecorator> {

    void moveUp(int i);
    void moveDown(int i);
    void add(TaskDecorator item);
    void removeAt(int i);
    int getSize();
    TaskDecorator getElementAt(int i);
    Iterator<TaskDecorator> iterator();
    List<TaskDecorator> getList();
}
