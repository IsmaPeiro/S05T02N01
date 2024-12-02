package cat.itacademy.s05.t02.n01.S05T02N01.services.impl;

import cat.itacademy.s05.t02.n01.S05T02N01.exception.NotFoundException;
import cat.itacademy.s05.t02.n01.S05T02N01.exception.AlreadyExistsException;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities.Pet;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities.UserEntity;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.Accessory;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetColor;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetLocation;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetType;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.repositories.UserRepository;
import cat.itacademy.s05.t02.n01.S05T02N01.services.IUserService;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.PetRequestDTO;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.PetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    
    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public UserEntity getOneUser(String nickname) {
        return userRepository.findByNickname(nickname).
                orElseThrow(() -> new NotFoundException("User not found"));
    }
    
    @Override
    public void deleteUser(String nickname) {
        UserEntity user=userRepository.findByNickname(nickname).
                orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }
    
//    @Override
//    public UserEntity createPet(UserEntity userEntity, PetRequestDTO petRequestDTO) {
//        boolean petExists = userEntity.getPets().stream()
//                .anyMatch(pet -> pet.getName().equalsIgnoreCase(petRequestDTO.getName()));
//
//        if (petExists) {
//            throw new AlreadyExistsException("Pet already exists");
//        }
//
//        Pet newPet = Pet.builder()
//                .name(petRequestDTO.getName())
//                .type(petRequestDTO.getType())
//                .color(petRequestDTO.getColor())
//                .build();
//
//        userEntity.getPets().add(newPet);
//        return userRepository.save(userEntity);
//    }
    
    @Override
    public UserEntity createPet(UserEntity userEntity, String petName, PetColor color, PetType type) {
        boolean petExists = userEntity.getPets().stream()
                .anyMatch(pet -> pet.getName().equalsIgnoreCase(petName));
        
        if (petExists) {
            throw new AlreadyExistsException("Pet already exists");
        }
        
        Pet newPet = Pet.builder()
                .name(petName)
                .type(type)
                .color(color)
                .build();
        
        userEntity.getPets().add(newPet);
        return userRepository.save(userEntity);
    }
    
    @Override
    public UserEntity deletePet(UserEntity userEntity, String petName) {
        Pet foundPet = userEntity.getPets().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Pet not found"));
        userEntity.getPets().remove(foundPet);
        return userRepository.save(userEntity);
    }
    
    @Override
    public List<PetResponseDTO> getAllPetsOneUser(UserEntity userEntity) {
        if (userEntity.getPets() == null || userEntity.getPets().isEmpty()) {
            throw new NotFoundException("The list of pets is empty or null");
        }
        
        return userEntity.getPets().stream()
                .map(pet -> PetResponseDTO
                        .builder()
                        .owner(userEntity.getNickname())
                        .name(pet.getName())
                        .type(pet.getType())
                        .color(pet.getColor())
                        .energy(pet.getEnergy())
                        .hunger(pet.getHunger())
                        .happiness(pet.getHappiness())
                        .petMood(pet.getPetMood())
                        .petLocation(pet.getPetLocation())
                        .accessories(pet.getAccessories())
                        .lastInteraction(pet.getLastInteraction())
                        .build())
                .collect(Collectors.toList());
    }
    
    @Override
    public PetResponseDTO getOneUserPet(UserEntity userEntity, String petName) {
        
        Pet petFound = userEntity.getPets().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst().orElseThrow(() -> new NotFoundException("Pet not found."));
                
        return PetResponseDTO
                .builder()
                .owner(userEntity.getNickname())
                .name(petFound.getName())
                .type(petFound.getType())
                .color(petFound.getColor())
                .energy(petFound.getEnergy())
                .hunger(petFound.getHunger())
                .happiness(petFound.getHappiness())
                .petMood(petFound.getPetMood())
                .petLocation(petFound.getPetLocation())
                .accessories(petFound.getAccessories())
                .lastInteraction(petFound.getLastInteraction())
                .build();
        
    }
    
    public List<PetResponseDTO> getAllPets () {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .flatMap(user -> user.getPets().stream()
                        .map(pet -> PetResponseDTO.builder()
                                .owner(user.getNickname())
                                .name(pet.getName())
                                .type(pet.getType())
                                .color(pet.getColor())
                                .energy(pet.getEnergy())
                                .hunger(pet.getHunger())
                                .happiness(pet.getHappiness())
                                .petMood(pet.getPetMood())
                                .petLocation(pet.getPetLocation())
                                .accessories(pet.getAccessories())
                                .lastInteraction(pet.getLastInteraction())
                                .build()))
                .collect(Collectors.toList());
    }
    
    @Override
    public UserEntity feedPet(UserEntity userEntity, String petName) {
        userEntity.getPets().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst()
                .ifPresentOrElse(
                        Pet::feed,
                        () -> {
                            throw new NotFoundException("Pet not found");
                        }
                );
        
        return userRepository.save(userEntity);
    }
    
    @Override
    public UserEntity playPet(UserEntity userEntity, String petName) {
        userEntity.getPets().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst()
                .ifPresentOrElse(
                        Pet::play,
                        () -> {
                            throw new NotFoundException("Pet not found");
                        }
                );
        return userRepository.save(userEntity);
    }
    
    @Override
    public UserEntity petPet(UserEntity userEntity, String petName) {
        userEntity.getPets().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst()
                .ifPresentOrElse(
                        Pet::petPet,
                        () -> {
                            throw new NotFoundException("Pet not found");
                        }
                );
        return userRepository.save(userEntity);
    }
    
    @Override
    public UserEntity sleepPet(UserEntity userEntity, String petName) {
        userEntity.getPets().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst()
                .ifPresentOrElse(
                        Pet::sleepPet,
                        () -> {
                            throw new NotFoundException("Pet not found");
                        }
                );
        return userRepository.save(userEntity);
    }
    
    @Override
    public UserEntity changeLocation(UserEntity userEntity, String petName, PetLocation newLocation) {
        userEntity.getPets().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst()
                .ifPresentOrElse(
                        pet->pet.changeLocation(newLocation),
                        () -> {
                            throw new NotFoundException("Pet not found");
                        }
                );
        return userRepository.save(userEntity);
    }
    
    @Scheduled(initialDelay = 5000, fixedRate = 120000)
    @Transactional
    public void updateActivePet() {
        List<UserEntity> users = userRepository.findAll();
        users.forEach(user -> user.getPets().forEach(pet -> {
            long time = Duration.between(pet.getLastScheduledUpdate(), LocalDateTime.now()).toMinutes();
            if (time>0) {
                pet.setEnergy(pet.getHunger()-(int)time);
                pet.setHunger(pet.getHunger()+(int)time);
                pet.setHappiness(pet.getHappiness()-(int)time);
                pet.setLastScheduledUpdate(LocalDateTime.now());
                pet.update("scheduledInteraction");
            }
        }));
        userRepository.saveAll(users);
    }
    
    @Override
    public UserEntity updateAccessory(UserEntity userEntity, String petName, Accessory accessory) {
        userEntity.getPets().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .findFirst()
                .ifPresentOrElse(
                        pet->{
                            if (pet.getAccessories().contains(accessory)) {
                                pet.removeAccessory(accessory);
                            } else {
                                pet.addAccessory(accessory);
                            }
                        },
                        () -> {
                            throw new NotFoundException("Pet not found");
                        }
                );
        return userRepository.save(userEntity);
    }
}
