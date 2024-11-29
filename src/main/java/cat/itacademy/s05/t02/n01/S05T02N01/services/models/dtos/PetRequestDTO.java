package cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos;

import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetColor;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetType;
import lombok.Data;

@Data
public class PetRequestDTO {
    private String name;
    private PetType type;
    private PetColor color;
}
