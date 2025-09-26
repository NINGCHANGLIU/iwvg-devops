package es.upm.miw.devops.rest;

import es.upm.miw.devops.rest.exceptionshandler.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorMessageTest {

    @Test
    void gettersShouldExposeValues() {
        ErrorMessage msg = new ErrorMessage("Any Error", "Any Message", 418);
        assertThat(msg.getError()).isEqualTo("Any Error");
        assertThat(msg.getMessage()).isEqualTo("Any Message");
        assertThat(msg.getCode()).isEqualTo(418);
        assertThat(msg.toString()).contains("Any Error");
    }
}

