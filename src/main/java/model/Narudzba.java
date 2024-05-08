package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = Narudzba.GET_ALL_NARUDZBA, query = "Select n from Narudzba n") })
public class Narudzba {

	public static final String GET_ALL_NARUDZBA = "getAllNarudzba";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String opis;

	@ManyToOne
	@JoinColumn(name= "korisnik_id")
	private Korisnik korisnik;

	@ManyToOne
	@JoinColumn(name= "dostavljac_id")
	private Dostavljac dostavljac;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Dostavljac getDostavljac() {
		return dostavljac;
	}

	public void setDostavljac(Dostavljac dostavljac) {
		this.dostavljac = dostavljac;
	}

}
