package com.flixl.dependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
@Component
public class DependencyInjectionApplication implements CommandLineRunner {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ConfigurableApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);

        DependencyInjectionApplication app = context.getBean(DependencyInjectionApplication.class);
        app.run(args);
    }

    @Override
    public void run(String[] args) {
        System.out.println("Hallo CommandLineRunner");
    }
}
