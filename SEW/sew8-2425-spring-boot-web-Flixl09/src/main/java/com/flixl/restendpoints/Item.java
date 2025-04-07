package com.flixl.restendpoints;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Nonnull
    private String name;

    @Column(name = "amount")
    private int amount;

    @Column(name = "collected")
    private boolean collected = false;

}