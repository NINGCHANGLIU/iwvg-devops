package es.upm.miw.devops.rest;

import es.upm.miw.devops.rest.exceptionshandler.ApiExceptionHandler;
import es.upm.miw.devops.rest.exceptionshandler.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;

class ApiExceptionHandlerTest {

    private final ApiExceptionHandler handler = new ApiExceptionHandler();

    @Test
    void shouldReturnNotFoundMessageSafely() {
        ErrorMessage msg = handler.noResourceFoundRequest(new ResponseStatusException(HttpStatus.NOT_FOUND));
        assertThat(msg.getCode()).isEqualTo(404);
        assertThat(msg.getError()).isEqualTo("Not Found");
        assertThat(msg.getMessage()).contains("Resource not found");
    }

    @Test
    void shouldReturnInternalServerErrorSafely() {
        ErrorMessage msg = handler.exception(new Exception("details"));
        assertThat(msg.getCode()).isEqualTo(500);
        assertThat(msg.getError()).isEqualTo("Internal Server Error");
        assertThat(msg.getMessage()).isEqualTo("Unexpected error");
    }
}

