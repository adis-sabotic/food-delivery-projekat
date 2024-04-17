package service;

import java.util.List;

import enums.NarudzbaStatus;
import excp.KorisnikException;
import excp.NarudzbaException;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Narudzba;

@Dependent
public class NarudzbaService {

    @Inject
    private EntityManager em;

    @Transactional
    public Narudzba createNarudzba(Narudzba narudzba) throws NarudzbaException {

        List<Narudzba> narudzbe = getAllNarudzba();

        if(narudzbe.contains(narudzba)) {
			throw new NarudzbaException(NarudzbaStatus.EXISTS.getLabel());
		}
        
        return em.merge(narudzba);
    }

    @Transactional
    public List<Narudzba> getAllNarudzba() {
        return em.createNamedQuery(Narudzba.GET_ALL_NARUDZBA, Narudzba.class).getResultList();
    }

    @Transactional
    public Narudzba getNarudzbaById(Long id) {
        return em.find(Narudzba.class, id);
    }

    @Transactional
    public Narudzba updateNarudzba(Narudzba narudzba) throws NarudzbaException {
        Narudzba existingNarudzba = em.find(Narudzba.class, narudzba.getId());
        if (existingNarudzba == null) {
            throw new NarudzbaException("Narudzba ne postoji!");
        }
        return em.merge(narudzba);
    }

    @Transactional
    public void deleteNarudzba(Long id) throws NarudzbaException {
        Narudzba narudzba = em.find(Narudzba.class, id);
        if (narudzba == null) {
            throw new NarudzbaException("Narudzba ne postoji!");
        }
        em.remove(narudzba);
    }
}

