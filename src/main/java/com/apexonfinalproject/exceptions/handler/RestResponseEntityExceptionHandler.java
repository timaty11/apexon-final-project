package com.apexonfinalproject.exceptions.handler;

import com.apexonfinalproject.exceptions.UserNotFoundException;
import com.apexonfinalproject.exceptions.model.ApiExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiExceptionModel handleUserNotFoundException(RuntimeException exception, WebRequest request) {
        return new ApiExceptionModel(exception.getMessage(),
                LocalDateTime.now(),
                ((ServletWebRequest) request).getRequest().getRequestURI());
    }

}
