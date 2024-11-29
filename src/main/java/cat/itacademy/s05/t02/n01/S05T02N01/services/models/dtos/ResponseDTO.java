package cat.itacademy.s05.t02.n01.S05T02N01.services.models.dtos;

import lombok.Data;

@Data
public class ResponseDTO {
    private int numOfErrors;
    private String message;
}
