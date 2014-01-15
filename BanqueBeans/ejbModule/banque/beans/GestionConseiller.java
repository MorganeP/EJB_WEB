package banque.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.Banque;
import banque.entites.Client;
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
	public Conseiller findConseiller(int id) {
		return manager.find(Conseiller.class,id);
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
		Conseiller conseilold=manager.find(Conseiller.class,conseiller.getId());
		if (conseilold!=null)
			manager.merge(conseiller);
		else throw new ConseillerInconnu();
	}

	@Override
	public List<Client> getListeClient(Conseiller conseiller) {
		
		return null;
	}
	@Override
	public Conseiller verifierConseiller(String nom, String password)throws PersonneInconnu {
		Conseiller conseiller = null;
		if( nom!=null){
			List<Conseiller> liste = getListeConseiller();
			for( Conseiller c: liste){
				if(c.getNom().equals(nom)&&c.getMdp().equals(password)){
					conseiller = c;
				}
			}
			if(conseiller==null)
				throw new PersonneInconnu();
		}
		return conseiller;
	}


}
