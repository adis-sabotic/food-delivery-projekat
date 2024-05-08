package service;

import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import excp.DostavljacException;
import model.Dostavljac;

@Dependent
public class DostavljacService {

    @Inject
    private EntityManager em;

    @Transactional
    public Dostavljac createDostavljac(Dostavljac dostavljac) throws DostavljacException {
        try {
            return em.merge(dostavljac);
        } catch (Exception e) {
            throw new DostavljacException("Error creating Dostavljac: " + e.getMessage());
        }
    }

    @Transactional
	public List<Dostavljac> getAllDostavljac() {
        return em.createNamedQuery(Dostavljac.GET_ALL_DOSTAVLJAC, Dostavljac.class).getResultList();
    }

    @Transactional
    public Dostavljac getDostavljacById(Long id) {
        return em.find(Dostavljac.class, id);
    }

    @Transactional
    public Dostavljac updateDostavljac(Dostavljac dostavljac) throws DostavljacException {
        Dostavljac existingDostavljac = em.find(Dostavljac.class, dostavljac.getId());
        if (existingDostavljac == null) {
            throw new DostavljacException("Dostavljac not found!");
        }
        try {
            return em.merge(dostavljac);
        } catch (Exception e) {
            throw new DostavljacException("Error updating Dostavljac: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteDostavljac(Long id) throws DostavljacException {
        Dostavljac dostavljac = em.find(Dostavljac.class, id);
        if (dostavljac == null) {
            throw new DostavljacException("Dostavljac not found!");
        }
        try {
            em.remove(dostavljac);
        } catch (Exception e) {
            throw new DostavljacException("Error deleting Dostavljac: " + e.getMessage());
        }
    }
}
