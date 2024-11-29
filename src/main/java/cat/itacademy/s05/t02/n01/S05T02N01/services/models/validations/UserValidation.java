package cat.itacademy.s05.t02.n01.S05T02N01.services.models.validations;

import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.AuthUserDTO;
import cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos.ResponseDTO;

public class UserValidation {
    
    public ResponseDTO validate(AuthUserDTO user) {
        ResponseDTO response = new ResponseDTO();
        
        response.setNumOfErrors(0);
        
        if (
                user.getPassword() == null ||
                        !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")
        ) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("La contraseña debe tener entre 8 y 16 caracteres, al menos un número, una minúscula y una mayúscula.");
        }
        
        return response;
    }
}