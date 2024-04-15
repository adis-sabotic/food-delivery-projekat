package excp;

public class KorisnikException extends Exception {

	private String message;

	public KorisnikException(String message) {
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

