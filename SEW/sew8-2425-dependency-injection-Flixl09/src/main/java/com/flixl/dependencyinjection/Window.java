package com.flixl.dependencyinjection;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class Window extends JFrame {

    @Autowired
    public Window(Panel panel) {
        this.add(panel);
        this.setTitle("Zähler");
        this.setSize(240, 80);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @PostConstruct
    public void init() {
        this.setVisible(true);
    }
}