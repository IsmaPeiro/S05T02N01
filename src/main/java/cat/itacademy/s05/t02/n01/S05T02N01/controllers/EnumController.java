package cat.itacademy.s05.t02.n01.S05T02N01.controllers;

import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.Accessory;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetColor;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetLocation;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetType;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enum")
@Hidden
public class EnumController {
    
    @GetMapping("/petColors")
    @PreAuthorize("hasRole('USER')")
    public List<String> getPetColors() {
        return Arrays.stream(PetColor.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/petTypes")
    @PreAuthorize("hasRole('USER')")
    public List<String> getPetTypes() {
        return Arrays.stream(PetType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/petLocations")
    @PreAuthorize("hasRole('USER')")
    public List<String> getPetLocations() {
        return Arrays.stream(PetLocation.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/petAccessories")
    @PreAuthorize("hasRole('USER')")
    public List<String> getPetAccessories() {
        return Arrays.stream(Accessory.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
