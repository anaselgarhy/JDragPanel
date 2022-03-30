package test;

import com.anas.jdragpanel.DragPanel;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        DragPanel dragPanel = new DragPanel();

        dragPanel.setLayout(null);

        JButton button = new JButton("Button");
        JButton button2 = new JButton("Button2");
        button.setBounds(10, 10, 100, 100);
        button2.setBounds(10, 50, 100, 100);

        dragPanel.add(button);
        dragPanel.add(button2);

        dragPanel.setBounds(0, 0, 500, 500);

        add(dragPanel);

        setTitle("Test");
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
