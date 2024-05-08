package excp;

public class DostavljacException extends Exception {

    private String message;

    public DostavljacException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
