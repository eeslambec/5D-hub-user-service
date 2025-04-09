package uz.fivedhub.userservice.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.fivedhub.userservice.dto.CustomResponseEntity;
import uz.fivedhub.userservice.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public CustomResponseEntity<?> handleNotFoundException(NotFoundException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
}
