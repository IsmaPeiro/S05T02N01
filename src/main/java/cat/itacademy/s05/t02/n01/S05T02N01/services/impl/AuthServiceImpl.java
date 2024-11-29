package cat.itacademy.s05.t02.n01.S05T02N01.services.impl;

import cat.itacademy.s05.t02.n01.S05T02N01.exception.AlreadyExistsException;
import cat.itacademy.s05.t02.n01.S05T02N01.exception.AuthException;
import cat.itacademy.s05.t02.n01.S05T02N01.exception.NotFoundException;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities.UserEntity;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.repositories.UserRepository;
import cat.itacademy.s05.t02.n01.S05T02N01.services.IAuthService;
import cat.itacademy.s05.t02.n01.S05T02N01.services.IJWTUtilityService;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.AuthUserDTO;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.ResponseDTO;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.validations.UserValidation;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    private final IJWTUtilityService jwtUtilityService;
    private final UserValidation userValidation;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12); // Reutilizar encoder
    
//    @Override
//    public HashMap<String, String> login(AuthUserDTO loginRequest) {
//        HashMap<String, String> response = new HashMap<>();
//
//        userRepository.findByNickname(loginRequest.getNickname())
//                .ifPresentOrElse(user -> {
//                    if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//                        String token = null;
//                        try {
//                            token = jwtUtilityService.generateJWT(user.getNickname(), user.getRole().name());
//                        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | JOSEException e) {
//                            throw new RuntimeException(e);
//                        }
//                        response.put("jwt", token);
//                    } else {
//                        response.put("error", "Authentication failed");
//                    }
//                }, () -> response.put("error", "User not registered!"));
//
//        return response;
//    }
    
    @Override
    public String login(AuthUserDTO loginRequest) {
        UserEntity user = userRepository.findByNickname(loginRequest.getNickname())
                .orElseThrow(() -> new NotFoundException("User not found"));
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthException("Authentication failed");
        }
        
        try {
            return jwtUtilityService.generateJWT(user.getNickname(), user.getRole().name());
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | JOSEException e) {
            throw new AuthException("Error generating JWT");
        }
    }
    
    
//    @Override
//    public ResponseDTO register(AuthUserDTO user) {
//        ResponseDTO response = userValidation.validate(user);
//
//        if (response.getNumOfErrors() > 0) {
//            return response;
//        }
//
//        if (userRepository.existsByNickname(user.getNickname())) {
//            response.setNumOfErrors(1);
//            response.setMessage("User already exists!");
//            return response;
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        UserEntity newUser = UserEntity.builder()
//                .nickname(user.getNickname())
//                .password(user.getPassword())
//                .build();
//        userRepository.save(newUser);
//
//        response.setMessage("User created successfully");
//        return response;
//    }
    
    @Override
    public String register(AuthUserDTO user) {
        // Validar usuario
        ResponseDTO validationResponse = userValidation.validate(user);
        if (validationResponse.getNumOfErrors() > 0) {
            throw new AuthException(validationResponse.getMessage());
        }
        
        // Verificar si ya existe el usuario
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new AlreadyExistsException("User already exists!");
        }
        
        // Codificar la contraseña y guardar el usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity newUser = UserEntity.builder()
                .nickname(user.getNickname())
                .password(user.getPassword())
                .build();
        userRepository.save(newUser);
        
        // Generar y devolver el token
        try {
            return jwtUtilityService.generateJWT(newUser.getNickname(), newUser.getRole().name());
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | JOSEException e) {
            throw new AuthException("Error generating JWT");
        }
    }
    
}

