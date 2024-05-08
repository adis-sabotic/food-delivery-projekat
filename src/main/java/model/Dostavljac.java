package model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = Dostavljac.GET_ALL_DOSTAVLJAC, query = "SELECT d FROM Dostavljac d")
})
public class Dostavljac {

	public static final String GET_ALL_DOSTAVLJAC = "getAllDostavljac";
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dostavljac_seq")
    private Long id;
    private String name;
    private String surname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Telefon> telefoni;

	@OneToMany(mappedBy = "dostavljac")
	private Set<Narudzba> narudzbe;
	
    
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

    public Set<Telefon> getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(Set<Telefon> telefoni) {
        this.telefoni = telefoni;
    }
}
