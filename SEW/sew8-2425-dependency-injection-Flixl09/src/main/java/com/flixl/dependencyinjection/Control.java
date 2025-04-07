package com.flixl.dependencyinjection;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Controller
@Scope("prototype")
public class Control implements ActionListener {

    private Model model;

    @Autowired
    @Lazy
    private Window window;

    private Panel panel;

    @Autowired
    public Control(Model model) {
        this.model = model;
    }

    @Autowired
    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    @PostConstruct
    public void init() {
        if (isInjected()) {
            // This has to be manually be called to prevent circular dependency, see https://www.baeldung.com/circular-dependencies-in-spring
            this.panel.setController(this);
            this.panel.setLabel(String.valueOf(this.model.getCounter()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isInjected()) {
            String command = e.getActionCommand();
            if (command.equals("increment")) {
                this.model.setCounter(this.model.getCounter() + 1);
                this.panel.setLabel(String.valueOf(this.model.getCounter()));
            }
        }
    }

    private boolean isInjected() {
        boolean injected = true;
        if (this.model == null) {
            System.err.println("model in control is not injected");
            injected = false;
        }
        if (this.window == null) {
            System.err.println("window in control is not injected");
            injected = false;
        }
        if (this.panel == null) {
            System.err.println("panel in control is not injected");
            injected = false;
        }
        return injected;
    }
}