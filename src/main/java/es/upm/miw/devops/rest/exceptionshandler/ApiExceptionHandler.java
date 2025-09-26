package es.upm.miw.devops.rest.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NoResourceFoundException.class,
            ResponseStatusException.class

    })
    @ResponseBody
    public ErrorMessage noResourceFoundRequest(Exception exception) {
        return new ErrorMessage(
                "Not Found",
                "Resource not found. Try: /actuator/info, /swagger-ui.html, /v3/api-docs, /v3/api-docs.yaml",
                HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        return new ErrorMessage(
                "Internal Server Error",
                "Unexpected error",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
