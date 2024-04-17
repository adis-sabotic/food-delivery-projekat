package enums;

public enum NarudzbaStatus {
	EXISTS("Narudzba vec postoji");

	private String label;

	private NarudzbaStatus(String label) {
			this.label = label;
		}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
