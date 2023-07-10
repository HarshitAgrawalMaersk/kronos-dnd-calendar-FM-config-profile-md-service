package net.apmoller.kronos.services.md.config.calendar.exceptions;

import net.apmoller.kronos.services.commons.exceptions.ApiError;
import net.apmoller.kronos.services.commons.exceptions.ExceptionHandlers;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * This class is used to give our own handleMethodArgumentNotValidException definition
 * over the one coming in from ExceptionHandlers of kronos.services.commons
 */
@Component
public class GlobalExceptionHandler extends ExceptionHandlers {

    @Override
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, ServletWebRequest servletWebRequest) {

        CustomException customException = new CustomException(methodArgumentNotValidException.getParameter(), methodArgumentNotValidException.getBindingResult());
        return super.handleMethodArgumentNotValidException(customException, servletWebRequest);
    }
}

/**
 * This class extends MethodArgumentNotValidException to provide our own definition for getLocalizedMessage
 */
class CustomException extends MethodArgumentNotValidException {
    public CustomException(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }

    @Override
    public String getLocalizedMessage() {
        return "MethodArgument Not Valid";
    }
}
