package cat.itacademy.s05.t02.n01.S05T02N01.persistence.repositories;

import cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
  
    Optional<UserEntity> findByNickname (String nickname);
    boolean existsByNickname(String nickname);
}
