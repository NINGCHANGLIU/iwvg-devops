package es.upm.miw.devops.rest.exceptionshandler;

/**
 * Safe error response model. It avoids leaking internal exception class names or details.
 */
public class ErrorMessage {

    private final String error;
    private final String message;
    private final Integer code;

    public ErrorMessage(String error, String message, Integer code) {
        this.error = error;
        this.message = message;
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
