package cat.itacademy.s05.t02.n01.S05T02N01.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

