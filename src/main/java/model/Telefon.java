package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;



@Entity
@NamedQueries({
	@NamedQuery(name=Telefon.GET_ALL_FOR_KORISNIK, query = "Select t from Telefon t where t.korisnik.id = :id")
})
public class Telefon {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefon_seq")
	private Long id;
	
	public static final String GET_ALL_FOR_KORISNIK = "getAllForKorisnik";
	
	@ManyToOne
	@JsonIgnore
	public Korisnik korisnik;

	public String broj;

	public Telefon() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}


}
