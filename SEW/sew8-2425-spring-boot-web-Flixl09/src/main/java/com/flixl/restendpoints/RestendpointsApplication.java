package com.flixl.restendpoints;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.swing.text.html.HTML;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class RestendpointsApplication {

    @Autowired
    private ItemService itemService;

    public static void main(String[] args) {
        SpringApplication.run(RestendpointsApplication.class, args);
    }

    @GetMapping("/grocery")
    public Iterable<ItemDTO> getItems() {
        List<ItemDTO> items = itemService.getItems();
        return items;
    }

    @PostMapping("/grocery")
    public ResponseEntity<ItemDTO> addItem(@Valid @RequestBody ItemDTO item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.addItem(item));
    }

    @PutMapping("/grocery")
    public ItemDTO updateItem(@Valid @RequestBody ItemDTO item) {
        return itemService.updateItem(item);
    }


    @GetMapping("/grocery/{id}")
    public ItemDTO getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }

    @DeleteMapping({"/grocery", "/grocery/{id}"})
    public String deleteItem(@PathVariable(required = false) Optional<Long> id) {
        itemService.deleteItem(id.get());
        return "deleted";
    }

    @PatchMapping("/grocery/{id}")
    public ItemDTO patchItem(@PathVariable Long id, @RequestParam boolean collected) {
        return itemService.patchItem(id, collected);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOriginPatterns("http://localhost:[*]", "https://projekte.tgm.ac.at")
                        .exposedHeaders("Access-Control-Allow-Origin");
            }
        };
    }

}


