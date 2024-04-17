package enums;

public enum KorisnikStatus {

	EXISTS("Korisnik vec postoji");

	private String label;

	private KorisnikStatus(String label) {
			this.label = label;
		}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
