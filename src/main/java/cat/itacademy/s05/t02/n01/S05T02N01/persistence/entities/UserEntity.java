package cat.itacademy.s05.t02.n01.S05T02N01.persistence.entities;

import cat.itacademy.s05.t02.n01.S05T02N01.persistence.enums.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class UserEntity {
    @Id
    private String id;
    private String nickname;
    private String password;
    @Builder.Default
    private List<Pet> pets=new ArrayList<>();
    @Builder.Default
    private Role role=Role.USER;
    
}
