package com.nba.exception.handler;
import com.nba.exception.InvalidArgumentsException;
import com.nba.exception.InvalidStaffDataException;
import com.nba.exception.StaffNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidStaffDataException.class)
    public ResponseEntity<String> handleInvalidData(InvalidStaffDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(StaffNotFoundException.class)
    public ResponseEntity<String> handleNotFound(StaffNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidArgumentsException.class)
    public ResponseEntity<String> handleInvalidArgs(InvalidArgumentsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
