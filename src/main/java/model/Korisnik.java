package model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@NamedQueries({ @NamedQuery(name = Korisnik.GET_ALL_KORISNIK, query = "Select k from Korisnik k"),
		@NamedQuery(name = Korisnik.GET_KORISNICI_BY_NAME, query = "Select k from Korisnik k where k.name = :name") })
public class Korisnik {

	public static final String GET_ALL_KORISNIK = "getAllKorisnik";
	public static final String GET_KORISNICI_BY_NAME = "getKorisniciByName";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "korisnik_seq")
	private Long id;
	private String name;
	private String surname;
	private String address;
	private String fileName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "korisnik_id")
	private Set<Telefon> telefoni;

	@OneToMany(mappedBy = "korisnik")
	private Set<Narudzba> narudzbe;

	@OneToOne(cascade = CascadeType.ALL)
	private IPLog iplog;

	public IPLog getIplog() {
		return iplog;
	}

	public void setIplog(IPLog iplog) {
		this.iplog = iplog;
	}

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
	
	public Set<Telefon> getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(Set<Telefon> telefoni) {
        this.telefoni = telefoni;
    }
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Korisnik other = (Korisnik) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
