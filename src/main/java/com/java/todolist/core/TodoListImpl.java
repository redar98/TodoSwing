package com.java.todolist.core;

import com.java.todolist.core.TaskDecorators.TaskDecorator;
import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TodoListImpl implements TodoList {

    @Getter
    private List<TaskDecorator> list = new LinkedList<>();

    public void moveUp(int i) {
        if (i > 0) {
            TaskDecorator swap = this.list.get(i - 1);
            this.list.set(i - 1, this.list.get(i));
            this.list.set(i, swap);
        }
    }

    public void moveDown(int i) {
        if (i < this.list.size() - 1) {
            TaskDecorator swap = this.list.get(i + 1);
            this.list.set(i + 1, this.list.get(i));
            this.list.set(i, swap);
        }
    }

    public void add(TaskDecorator item) {
        this.list.add(item);
    }

    public void editAt(int i, TaskDecorator newValue) {
        if (i >= 0 && i < this.list.size()) {
            this.list.set(i, newValue);
        }
    }

    public void removeAt(int i) {
        if (i >= 0 && i < this.list.size()) {
            this.list.remove(i);
        }
    }

    public int getSize() {
        return list.size();
    }

    public TaskDecorator getElementAt(int i) {
        return list.get(i);
    }

    @Override
    public Iterator<TaskDecorator> iterator() {
        return list.iterator();
    }
}