package com.java.todolist;

import com.java.todolist.gui.MainWindow;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class TodoApp {

    public static void main(String[] args) {
        configureLookAndFeel();

        MainWindow window = new MainWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private static void configureLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break; //preferred!
                }
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception ignored) {
        }
    }
}
