package banque.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.Banque;
import banque.entites.Conseiller;

/**
 * Session Bean implementation class GestionConseiller
 */
@Stateless
public class GestionConseiller implements GestionConseillerRemote, GestionConseillerLocal {
	@PersistenceContext
	EntityManager manager;
    /**
     * Default constructor. 
     */
    public GestionConseiller() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Conseiller ajouterConseiller(Conseiller c) {
		manager.persist(c);
		return c;
	}

	@Override
	public List<Conseiller> getListeConseiller() {
		return manager.createQuery("Select c from Conseiller c").getResultList();
	}

	@Override
	public void supprimerConseiller(Conseiller conseiller) {
		conseiller=manager.find(Conseiller.class,conseiller.getId());
		manager.remove(conseiller);
	}

	@Override
	public void modifierConseiller(Conseiller conseiller) throws ConseillerInconnu {
		conseiller=manager.find(Conseiller.class,conseiller.getId());
		if (conseiller!=null)
			manager.merge(conseiller);
		else throw new ConseillerInconnu();
	}

}
