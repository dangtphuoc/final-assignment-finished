package springdata.jpa.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import springdata.jpa.common.ErrorType;
import springdata.jpa.dto.ResponseBean;
import springdata.jpa.exception.ValidationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends
		ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { ValidationException.class, 
								IllegalArgumentException.class,
								IllegalStateException.class })
	@ResponseStatus(value=HttpStatus.CONFLICT)
	@ResponseBody
	protected ResponseBean handleException(RuntimeException ex) {
		return new ResponseBean(ErrorType.FAIL);
	}
}