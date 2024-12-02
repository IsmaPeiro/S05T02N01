package cat.itacademy.s05.t02.n01.S05T02N01.controllers;

import cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities.UserEntity;
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
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IUserService userService;
    
    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    
    @GetMapping("/getAllPets")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PetResponseDTO>> getAllPets() {
        return new ResponseEntity<>(userService.getAllPets(), HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@RequestParam String nickname) {
        userService.deleteUser(nickname);
        return new ResponseEntity<>("User deleted.", HttpStatus.OK);
    }
    
//    @PostMapping("/createPet")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public ResponseEntity<UserEntity> createPet(@RequestBody String nickname, PetRequestDTO petRequestDTO) {
//        return new ResponseEntity<>(userService.createPet(userService.getOneUser(nickname), petRequestDTO), HttpStatus.OK);
//    }
    
    @PostMapping("/createPet")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserEntity> createPet(@RequestBody  String nickname, String petName, PetColor color, PetType type) {
        return new ResponseEntity<>(userService.createPet(userService.getOneUser(nickname), petName, color, type), HttpStatus.OK);
    }
    
    @PostMapping("/deletePet")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserEntity> deletePet(@RequestParam String nickname, String petName) {
        UserEntity user=userService.getOneUser(nickname);
        return new ResponseEntity<>(userService.deletePet(user, petName), HttpStatus.OK);
    }
}
