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
			throw new KorisnikException("Student vec postoji!");
		}
		
		return em.merge(k);
	}
	
	@Transactional
	public List<Korisnik> getAllKorisnik(){
		return em.createNamedQuery(Korisnik.GET_ALL_KORISNIK, Korisnik.class).getResultList();
	}
}
