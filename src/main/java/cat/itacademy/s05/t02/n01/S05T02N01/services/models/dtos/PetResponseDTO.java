package cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos;

import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDTO {
    private String owner;
    private String name;
    private PetType type;
    private PetColor color;
    private int energy;
    private int hunger;
    private int happiness;
    private PetMood petMood;
    private PetLocation petLocation;
    private List<Accessory> accessories;
    private LocalDateTime lastInteraction;
}
