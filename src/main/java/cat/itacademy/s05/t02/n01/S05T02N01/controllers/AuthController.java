package cat.itacademy.s05.t02.n01.S05T02N01.controllers;

import cat.itacademy.s05.t02.n01.S05T02N01.services.IAuthService;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.AuthUserDTO;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;
    
//    @PostMapping("/register")
//    private ResponseEntity<ResponseDTO> register (@RequestBody AuthUserDTO user) throws Exception {
//        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
//    }
    
//    @PostMapping("/login")
//    private ResponseEntity<HashMap<String, String>> login (@RequestBody AuthUserDTO loginRequest) throws Exception {
//        HashMap<String, String> login = authService.login(loginRequest);
//        if (login.containsKey("jwt")) {
//            return new ResponseEntity<>(login, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(login, HttpStatus.UNAUTHORIZED);
//        }
//    }
    
    @PostMapping("/register")
    private ResponseEntity<String> register (@RequestBody AuthUserDTO user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    private ResponseEntity<String> login (@RequestBody AuthUserDTO loginRequest) throws Exception {
        String login = authService.login(loginRequest);
        return new ResponseEntity<>(login, HttpStatus.OK);
        
    }
}
