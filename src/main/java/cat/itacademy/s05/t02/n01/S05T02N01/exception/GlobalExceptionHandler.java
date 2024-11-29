package cat.itacademy.s05.t02.n01.S05T02N01.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(NotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(PetActionsException.class)
    public ResponseEntity<ErrorResponse> handleUserPetActionsException(PetActionsException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(AlreadyExistsException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleUserAuthException (AuthException e) {
        return buildErrorResponse(e, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationDeniedException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Access Denied", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception e, HttpStatus status) {
        log.error("Error: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), status);
        return new ResponseEntity<>(errorResponse, status);
    }
}