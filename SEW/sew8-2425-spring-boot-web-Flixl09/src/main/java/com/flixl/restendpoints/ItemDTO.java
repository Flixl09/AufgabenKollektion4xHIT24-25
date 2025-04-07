package com.flixl.restendpoints;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;

    @NotNull(message = "Item name must not be null")
    @NotBlank(message = "Item name must not be blank")
    @Size(max = 255, message = "Item name must not be longer than 255 characters")
    private String name;

    private int amount;

    private boolean collected;

    public void correctErrors() {
        if (this.amount < 0) this.amount *= -1;
        if (this.amount == 0) this.amount = 1;
        this.name = this.name.trim();
    }
}
