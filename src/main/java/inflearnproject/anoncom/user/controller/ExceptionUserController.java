package inflearnproject.anoncom.user.controller;

import inflearnproject.anoncom.error.ErrorDTO;
import inflearnproject.anoncom.user.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static inflearnproject.anoncom.user.exception.ExceptionMessage.BE_RIGHT_LENGTH;

@RestControllerAdvice(assignableTypes = UserController.class)
public class ExceptionUserController {

    @ExceptionHandler(SameInfoUserEntityException.class)
    public ResponseEntity<ErrorDTO> handleSameInfoUserEntityException(SameInfoUserEntityException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(exception.getMessage()));
    }

    @ExceptionHandler(NoUserEntityException.class)
    public ResponseEntity<ErrorDTO> handleNoNoUserEntityException(NoUserEntityException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(BE_RIGHT_LENGTH));
    }

    @ExceptionHandler(NotActiveUser.class)
    public ResponseEntity<ErrorDTO> handleNotActiveUser(NotActiveUser exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(exception.getMessage()));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorDTO> handleWrongPasswordException(WrongPasswordException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(exception.getMessage()));
    }

    @ExceptionHandler(BlockedUserException.class)
    public ResponseEntity<ErrorDTO> handleBlockedUserException(BlockedUserException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(exception.getMessage()));
    }

    @ExceptionHandler(FailImageLoadingException.class)
    public ResponseEntity<ErrorDTO> handleFailImageLoadingException(FailImageLoadingException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(exception.getMessage()));
    }
}
