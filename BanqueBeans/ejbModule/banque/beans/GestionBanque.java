package banque.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.Banque;
import banque.entites.Client;

/**
 * Session Bean implementation class GestionBanque
 */
@Stateless
public class GestionBanque implements GestionBanqueRemote, GestionBanqueLocal {
	@PersistenceContext
	EntityManager manager;
    /**
     * Default constructor. 
     */
    public GestionBanque() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Banque ajouterBanque(Banque c) {
		manager.persist(c);
		return c;
	}

	@Override
	public List<Banque> getListeBanque() {
		return manager.createQuery("Select c from Banque c").getResultList();
	
	}

	@Override
	public void supprimerBanque(Banque banque) {
		banque=manager.find(Banque.class,banque.getId());
		manager.remove(banque);
	}

	@Override
	public Banque findBanque(int id) {
		return manager.find(Banque.class,id);
	}

	@Override
	public void modifierBanque(Banque banque) throws BanqueInconnu {
		banque=manager.find(Banque.class,banque.getId());
		if (banque!=null)
			manager.merge(banque);
		else throw new BanqueInconnu();
	}

}
