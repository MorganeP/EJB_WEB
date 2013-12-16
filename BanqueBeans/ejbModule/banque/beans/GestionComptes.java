package banque.beans;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.Client;
import banque.entites.Compte;

/**
 * Session Bean implementation class GestionComptes
 */
@Stateful
@LocalBean
public class GestionComptes implements GestionComptesRemote, GestionComptesLocal {

	@PersistenceContext
	EntityManager manager;
	Client client=null;
	List<Compte> comptes = new ArrayList<Compte>();
	
    /**
     * Default constructor. 
     */
    public GestionComptes() {
    }

    public void choisirClient(Client c) {
		client = c;
	}

	public Compte ajouterCompte(Compte c) throws ClientNullException {
		if(client!=null){
			c.setTitulaire(client);
			manager.persist(c);
			return c;
		}
		else{
			throw new ClientNullException();
		}
	}

	public void modifierCompte(Compte compte) throws CompteInconnuException {
		compte=manager.find(Compte.class,compte.getNumeroCompte());
		if (compte!=null)
		manager.merge(compte);
		else throw new CompteInconnuException();
	}	
	
	public void supprimerCompte(Compte c) {
		c = manager.find(Compte.class,c.getNumeroCompte());
		manager.remove(c);
	}

	public List<Compte> getComptes() {
		comptes = manager.createQuery("Select c from Compte c").getResultList();
		return comptes;
	}
	
	

}
