package service;

import java.util.List;

import excp.KorisnikException;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Korisnik;

@Dependent
public class KorisnikService {

	@Inject
	private EntityManager em;

	@Transactional
	public Korisnik createKorisnik(Korisnik k)throws KorisnikException {
		
		List<Korisnik> korisnici = getAllKorisnik();
		
		if(korisnici.contains(k)) {
			throw new KorisnikException("Korisnik vec postoji!");
		}
		
		return em.merge(k);
	}
	
	@Transactional
	public List<Korisnik> getAllKorisnik(){
		return em.createNamedQuery(Korisnik.GET_ALL_KORISNIK, Korisnik.class).getResultList();
	}
	
	@Transactional
	public Korisnik getKorisnikById(Long id) {
	    return em.find(Korisnik.class, id);
	}

	@Transactional
	public Korisnik updateKorisnik(Korisnik k) throws KorisnikException {
	    Korisnik existingKorisnik = em.find(Korisnik.class, k.getId());
	    if (existingKorisnik == null) {
	        throw new KorisnikException("Korisnik ne postoji!");
	    }
	    return em.merge(k);
	}

	@Transactional
	public void deleteKorisnik(Long id) throws KorisnikException {
	    Korisnik korisnik = em.find(Korisnik.class, id);
	    if (korisnik == null) {
	        throw new KorisnikException("Korisnik ne postoji!");
	    }
	    em.remove(korisnik);
	}
}
