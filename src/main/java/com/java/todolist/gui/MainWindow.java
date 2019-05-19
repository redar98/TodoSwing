package com.java.todolist.gui;

import com.java.todolist.core.PRIORITY;
import com.java.todolist.core.Task;
import com.java.todolist.core.TaskDecorators.TaskDecorator;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createVerticalStrut;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel mainContentPane;
    private JPanel newTaskControls;
    private JButton addTaskButton;
    private JTextField newTaskField;
    private JComboBox<PRIORITY> taskPriorityCombobox;
    private JScrollPane taskListScrollPane;
    private JPanel taskListControls;
    private JButton upButton;
    private JButton deleteButton;
    private JButton downButton;
    private JList<Task> taskList;
    private JLabel statusBar;

    private TodoListModel todoListModel;

    public MainWindow() {
        this.todoListModel = TodoListModel.getInstance();

        this.setContentPane(this.getMainContentPane());
        this.setTitle("Unique Todo List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(320, 270));

        this.pack();
    }

    private Container getMainContentPane() {
        if (mainContentPane == null) {
            this.mainContentPane = new JPanel();
            this.mainContentPane.setLayout(new BorderLayout());

            this.mainContentPane.add(getNewTaskControls(), BorderLayout.NORTH);
            this.mainContentPane.add(getTasksListScrollPane(), BorderLayout.CENTER);
            this.mainContentPane.add(getTasksListControls(), BorderLayout.EAST);
            this.mainContentPane.add(getStatusBar(), BorderLayout.SOUTH);

        }
        return this.mainContentPane;
    }

    private Component getNewTaskControls() {
        if (this.newTaskControls == null) {
            this.newTaskControls = new JPanel();

            BorderLayout layout = new BorderLayout();
            this.newTaskControls.setLayout(layout);
            layout.setHgap(5);
            this.newTaskControls.setBorder(createEmptyBorder(10, 0, 10, 5));

            this.newTaskControls.add(getNewTaskField(), BorderLayout.CENTER);
            this.newTaskControls.add(getTaskPriorityField(), BorderLayout.WEST);
            this.newTaskControls.add(getAddTaskButton(), BorderLayout.EAST);
        }

        return this.newTaskControls;
    }

    private JTextField getNewTaskField() {
        if (this.newTaskField == null) {
            this.newTaskField = new JTextField();
        }
        return this.newTaskField;
    }

    private JComboBox<PRIORITY> getTaskPriorityField() {
        if (this.taskPriorityCombobox == null) {
            this.taskPriorityCombobox = new JComboBox<>(PRIORITY.values());
            this.taskPriorityCombobox.setSelectedIndex(1);
        }
        return this.taskPriorityCombobox;
    }

    private JButton getAddTaskButton() {
        if (this.addTaskButton == null) {
            this.addTaskButton = new JButton("      Add");
            this.addTaskButton.setIcon(createIcon("diary.png"));

            this.addTaskButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (getNewTaskField().getText().length() > 0) {
                        todoListModel.add(getNewTaskField().getText().trim(), (PRIORITY) getTaskPriorityField().getSelectedItem());

                        getNewTaskField().setText("");
                        getTaskPriorityField().setSelectedIndex(1);

                        getTaskList().setSelectedIndex(getTaskList().getModel().getSize() - 1);
                    }
                }
            });
        }

        return this.addTaskButton;
    }

    private Component getTasksListScrollPane() {
        if (this.taskListScrollPane == null) {
            this.taskListScrollPane = new JScrollPane(getTaskList());
        }

        return this.taskListScrollPane;
    }

    private JList<Task> getTaskList() {
        if (this.taskList == null) {
            this.taskList = new JList<>();
            this.taskList.setModel(this.todoListModel);

            this.taskList.setCellRenderer(getCellRenderer());
        }

        return this.taskList;
    }

    private ListCellRenderer<Object> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof TaskDecorator) {
                    TaskDecorator nextTask = (TaskDecorator) value;
                    setText(nextTask.getMessage());
                    switch (nextTask.getPriority()) {
                        case HIGH:
                            setBackground(Color.getHSBColor(360, 50, 100));
                            break;
                        case NORMAL:
                            setBackground(Color.getHSBColor(74, 5, 34));
                            break;
                        case LOW:
                            setBackground(Color.getHSBColor(217, 0, 100));
                            break;
                    }
                    if (isSelected) setBackground(getBackground().darker());
                }
                return c;
            }
        };
    }

    private Component getTasksListControls() {
        if (this.taskListControls == null) {
            this.taskListControls = new JPanel();

            BoxLayout layout = new BoxLayout(this.taskListControls, BoxLayout.Y_AXIS);
            this.taskListControls.setLayout(layout);
            this.taskListControls.setBorder(createEmptyBorder(0, 5, 5, 5));

            JButton button = getUpButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.taskListControls.add(button);

            this.taskListControls.add(createVerticalStrut(10));

            button = getDeleteButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.taskListControls.add(button);

            this.taskListControls.add(createVerticalStrut(10));

            button = getDownButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.taskListControls.add(button);
        }

        return this.taskListControls;
    }

    private JButton getUpButton() {
        if (this.upButton == null) {
            this.upButton = new JButton("       Up");
            this.upButton.setIcon(createIcon("up.png"));

            this.upButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int pos = getTaskList().getSelectedIndex();
                    todoListModel.moveUp(pos);

                    getTaskList().setSelectedIndex(max(0, pos - 1));
                }
            });
        }

        return this.upButton;
    }

    private JButton getDeleteButton() {
        if (this.deleteButton == null) {
            this.deleteButton = new JButton(" Delete");
            this.deleteButton.setIcon(createIcon("bin.png"));

            this.deleteButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    todoListModel.removeAt(getTaskList().getSelectedIndex());
                }
            });
        }

        return this.deleteButton;
    }

    private JButton getDownButton() {
        if (this.downButton == null) {
            this.downButton = new JButton("  Down");
            this.downButton.setIcon(createIcon("down.png"));

            this.downButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int pos = getTaskList().getSelectedIndex();
                    todoListModel.moveDown(pos);

                    getTaskList().setSelectedIndex(
                            min(getTaskList().getModel().getSize() - 1, pos + 1));
                }
            });
        }

        return this.downButton;
    }

    private JLabel getStatusBar() {
        if (this.statusBar == null) {
            this.statusBar = new JLabel("Number of tasks: 0 | High priority: 0");
            this.todoListModel.addListDataListener(new ListDataListener() {
                @Override
                public void contentsChanged(ListDataEvent e) {
                    updateLabel(e);
                }

                private void updateLabel(ListDataEvent e) {
                    getStatusBar().setText("Number of tasks: " + ((TodoListModel) e.getSource()).getSize() + " | "
                            + "High priority: " + todoListModel.getHighPriorityTasksNumber());
                }

                @Override
                public void intervalRemoved(ListDataEvent e) {
                }

                @Override
                public void intervalAdded(ListDataEvent e) {
                }
            });
        }

        return this.statusBar;
    }

    private Icon createIcon(String iconfilename) {
        return new ImageIcon(
                getClass().
                        getResource("/" + iconfilename));
    }
}
