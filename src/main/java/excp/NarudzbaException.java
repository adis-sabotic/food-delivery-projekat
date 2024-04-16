package excp;

public class NarudzbaException extends Exception {

	private String message;

	public NarudzbaException(String message) {
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

