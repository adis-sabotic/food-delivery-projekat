package model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = Korisnik.GET_ALL_KORISNIK, query = "Select k from Korisnik k")
})
public class Korisnik {
	
	public static final String GET_ALL_KORISNIK = "getAllKorisnik";

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "korisnik_seq")
	private Long id;
	private String name;
	private String surname;
	private String address;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik_id")
	private Set<Telefon> telefoni;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
