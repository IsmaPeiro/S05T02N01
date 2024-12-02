package cat.itacademy.s05.t02.n01.S05T02N01.controllers;

import cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities.UserEntity;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.Accessory;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetColor;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetLocation;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetType;
import cat.itacademy.s05.t02.n01.S05T02N01.services.IUserService;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.PetRequestDTO;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.PetResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    
    @PostMapping("/createPet")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<UserEntity> createPet (@RequestBody String petName, PetType type, PetColor color) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.createPet(userEntity, petName, color, type), HttpStatus.OK);
    }

    @PostMapping("/deletePet")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserEntity> deletePet (@RequestParam String petName) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.deletePet(userEntity, petName), HttpStatus.OK);
    }

    @GetMapping("/getAllUserPets")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<PetResponseDTO>> getAllUserPets () {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.getAllPetsOneUser(userEntity), HttpStatus.OK);
    }
    
    @GetMapping("/getOneUserPet")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PetResponseDTO> getOneUserPet (@Parameter String petName) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.getOneUserPet(userEntity, petName), HttpStatus.OK);
    }
    
    @PostMapping("/feedPet")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserEntity> feedPet (@RequestParam String petName) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.feedPet(userEntity, petName), HttpStatus.OK);
    }
    
    @PostMapping("/playPet")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserEntity> playPet (@RequestParam String petName) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.playPet(userEntity, petName), HttpStatus.OK);
    }
    
    @PostMapping("/petPet")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserEntity> petPet (@RequestParam String petName) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.petPet(userEntity, petName), HttpStatus.OK);
    }
    
    @PostMapping("/sleepPet")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserEntity> sleepPet (@RequestParam String petName) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.sleepPet(userEntity, petName), HttpStatus.OK);
    }
    
    @PostMapping("/changeLocation")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserEntity> changeLocation (@RequestParam String petName, PetLocation newLocation) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.changeLocation(userEntity, petName, newLocation), HttpStatus.OK);
    }
    
    @PostMapping("/updateAccessory")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserEntity> updateAccessory (@RequestParam String petName, Accessory accessory) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity=userService.getOneUser(authentication.getName());
        return new ResponseEntity<>(userService.updateAccessory(userEntity, petName, accessory), HttpStatus.OK);
    }
}
