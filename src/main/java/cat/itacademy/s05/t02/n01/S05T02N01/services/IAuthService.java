package cat.itacademy.s05.t02.n01.S05T02N01.services;

import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.AuthUserDTO;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {
//    public HashMap<String, String> login (AuthUserDTO login) throws Exception;
    public String login (AuthUserDTO login) throws Exception;
//    public ResponseDTO register(AuthUserDTO user) throws Exception;
public String register(AuthUserDTO user) throws Exception;
}
