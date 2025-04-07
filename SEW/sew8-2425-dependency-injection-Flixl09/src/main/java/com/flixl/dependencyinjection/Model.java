package com.flixl.dependencyinjection;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class Model {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}