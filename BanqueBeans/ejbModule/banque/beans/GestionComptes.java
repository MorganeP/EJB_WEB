package banque.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.Client;
import banque.entites.Compte;
import banque.entites.Operation;
import banque.entites.TypeOperation;

/**
 * Session Bean implementation class GestionComptes
 */
@Stateful
@LocalBean
public class GestionComptes implements GestionComptesRemote, GestionComptesLocal {

	@PersistenceContext
	EntityManager manager;

	List<Compte> comptes = new ArrayList<Compte>();
	
    /**
     * Default constructor. 
     */
    public GestionComptes() {
    }



	public Compte ajouterCompte(Compte c,Client client) throws ClientNullException {
		if(client!=null){
			c.setTitulaire(client);
			manager.persist(c);//enregistrement dans la BDD
			return c;
		}
		else{
			throw new ClientNullException();
		}
	}

	public void modifierCompte(Compte compte) throws CompteInconnuException {
		Compte ba=manager.find(Compte.class,compte.getId());
		if (ba!=null)
		manager.merge(compte);
		else throw new CompteInconnuException();
	}	
	
	public void supprimerCompte(Compte c) {
		c = manager.find(Compte.class,c.getId());
		manager.remove(c);
	}

	public List<Compte> getComptes() {
		comptes = manager.createQuery("Select c from Compte c").getResultList();
		return comptes;
	}


	@Override
	public boolean effectuerRetrait(Compte compte, double montant) {
		// TODO Auto-generated method stub
		boolean result=false;
		Compte c=manager.find(Compte.class, compte.getId());
		if(c!=null){
			double solde=c.getSolde()-montant;
			if(solde>=0){
				c.setSolde(solde);
				Operation m=new Operation();
				m.setCompte(c);
				m.setDate(new Date());
				m.setMontant(montant);
				m.setType(TypeOperation.Retrait);
				List<Operation> historique=c.getOperations();
				historique.add(m);
				c.setOperations(historique);
				manager.persist(c);
				result=true;
			}
		}
		return result;
	}

	@Override
	public boolean effectuerDepot(Compte compte, double montant) {
		// TODO Auto-generated method stub
		boolean result=false;
		Compte c=manager.find(Compte.class, compte.getId());
		if(c!=null){
			double solde=c.getSolde()+montant;
			c.setSolde(solde);
			Operation o=new Operation();
			o.setCompte(c);
			o.setDate(new Date());
			o.setMontant(montant);
			o.setType(TypeOperation.D�p�t);
			List<Operation> historique=c.getOperations();
			historique.add(o);
			c.setOperations(historique);
			manager.persist(c);
			result=true;
		}
		return result;
	}

	@Override
	public Compte recupererCompte(int id) {
	
		return manager.find(Compte.class, id);
	}



	@Override
	public Compte ajouterCompte(Compte c)   {
		manager.persist(c);
		return c;
	}
	
	

}
