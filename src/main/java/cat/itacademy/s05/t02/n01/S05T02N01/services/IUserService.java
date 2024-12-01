package cat.itacademy.s05.t02.n01.S05T02N01.services;

import cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities.UserEntity;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.Accessory;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetColor;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetLocation;
import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.PetType;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.PetRequestDTO;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.PetResponseDTO;

import java.util.List;

public interface IUserService {
    public List<UserEntity> getAllUsers();
    public UserEntity getOneUser(String nickname);
    public void deleteUser(String nickname);
//    public UserEntity createPet (UserEntity userEntity, PetRequestDTO petRequestDTO);
    public UserEntity createPet(UserEntity userEntity, String petName, PetColor color, PetType type);
    public UserEntity deletePet (UserEntity userEntity, String petName);
    public List<PetResponseDTO> getAllPetsOneUser(UserEntity userEntity);
    public UserEntity feedPet (UserEntity userEntity, String petName);
    public PetResponseDTO getOneUserPet (UserEntity userEntity, String petName);
    public List<PetResponseDTO> getAllPets ();
    public UserEntity playPet(UserEntity userEntity, String petName);
    public UserEntity petPet(UserEntity userEntity, String petName);
    public UserEntity sleepPet(UserEntity userEntity, String petName);
    public UserEntity changeLocation(UserEntity userEntity, String petName, PetLocation newLocation);
    public UserEntity updateAccessory (UserEntity userEntity, String petName, Accessory accessory);
}
