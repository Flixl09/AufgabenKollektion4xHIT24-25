package com.flixl.dependencyinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class Panel extends JPanel {
    private JButton button;
    private JLabel label;

    public Panel() {
        this.setLayout(new FlowLayout());
        this.label = new JLabel("");
        this.add(this.label);
        this.button = new JButton();
        this.button.setText("+");
        this.add(this.button);
    }

    public void setController(Control control) {
        this.button.setActionCommand("increment");
        this.button.addActionListener(control);
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }
}