package me.spring.security.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import me.spring.security.exceptions.customException.PasswordsDoNotMatchException;
import me.spring.security.exceptions.customException.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> BadCredentialsException(BadCredentialsException ex) {
        log.error("Bad credentials exception: {}", ex.getMessage(), ex);
        AppError error = new AppError(
                HttpStatus.UNAUTHORIZED.value(),
                "Неверный логин или пароль");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public void expiredJwtException(ExpiredJwtException ex) {
        log.error("the token's lifetime has expired: {}", ex.getMessage(), ex);
    }

    @ExceptionHandler(SignatureException.class)
    public void signatureException(ExpiredJwtException ex) {
        log.error("неправильная подпись токена: {}", ex.getMessage(), ex);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<?> passwordsDoNotMatchException(PasswordsDoNotMatchException ex) {
        log.error("passwords do not match: {}", ex.getMessage(), ex);

        AppError error = new AppError(
                HttpStatus.BAD_REQUEST.value(),
                "Пароли не совпадают");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error("User already exists: {}", ex.getMessage(), ex);
        AppError error = new AppError(
                HttpStatus.CONFLICT.value(),
                "Пользователь с таким именем уже существует");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        AppError error = new AppError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Внутренняя ошибка сервера");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}


