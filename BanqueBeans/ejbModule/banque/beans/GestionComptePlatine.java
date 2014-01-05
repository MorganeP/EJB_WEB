package banque.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.ComptePlatine;
import banque.entites.Operation;

/**
 * Session Bean implementation class GestionComptePlatine
 */
@Stateless
public class GestionComptePlatine implements GestionComptePlatineRemote, GestionComptePlatineLocal {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GestionComptePlatine() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean effectuerRetrait(ComptePlatine comptePlatine, double montant) {
		// TODO Auto-generated method stub
		boolean result=false;
		ComptePlatine c=em.find(ComptePlatine.class, comptePlatine.getId());
		if(c!=null){
			double solde=c.getSolde()-montant;
			if(solde>=-c.getDecouvert()){
				c.setSolde(solde);
				Operation o=new Operation();
				o.setCompte(c);
				o.setDate(new Date());
				o.setMontant(montant);
				o.setRetrait(true);
				List<Operation> historique=c.getOperations();
				historique.add(o);
				c.setOperations(historique);
				em.persist(c);
				result=true;
			}
		}
		return result;
	}

}
