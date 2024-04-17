package service;

import java.util.Date;
import java.util.List;

import enums.KorisnikStatus;
import excp.KorisnikException;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.IPLog;
import model.Korisnik;
import model.Telefon;

@Dependent
public class KorisnikService {

	@Inject
	private EntityManager em;

	@Transactional
	public Korisnik createKorisnik(Korisnik k, IPLog ipLog) throws KorisnikException {

		List<Korisnik> korisnici = getAllKorisnik();

		if (korisnici.contains(k)) {
			throw new KorisnikException(KorisnikStatus.EXISTS.getLabel());
		}
		ipLog.setCreatedDate(new Date());
		k.setIplog(ipLog);
		return em.merge(k);
	}

	@Transactional
	public List<Korisnik> getAllKorisnik() {
		List <Korisnik> korisnici = em.createNamedQuery(Korisnik.GET_ALL_KORISNIK, Korisnik.class).getResultList();

//		for (Korisnik korisnik : korisnici) {
//			List<Telefon> telefoni = getAllForKorisnik(korisnik);
//			korisnik.setTelefoni
//		}
		
		return korisnici;
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

	@Transactional
	public List<Telefon> getAllForKorisnik(Korisnik k) {
		return em.createNamedQuery(Telefon.GET_ALL_FOR_KORISNIK, Telefon.class).setParameter("id", k.getId())
				.getResultList();
	}

}
