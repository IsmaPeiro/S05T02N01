package cat.itacademy.s05.t02.n01.S05T02N01.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}

