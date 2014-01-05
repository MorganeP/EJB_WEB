package banque.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.CompteStandard;
import banque.entites.Operation;
import banque.entites.TypeOperation;

/**
 * Session Bean implementation class GestionCompteStandard
 */
@Stateless
public class GestionCompteStandard implements GestionCompteStandardRemote, GestionCompteStandardLocal {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GestionCompteStandard() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean effectuerRetrait(CompteStandard compteStandard,
			double montant) {
		// TODO Auto-generated method stub
		boolean result=false;
		CompteStandard c=em.find(CompteStandard.class, compteStandard.getId());
		if(c!=null){
			double solde=c.getSolde()-montant-c.getPenalite();
			//si compte non débiteur
			if(solde>=0){
				c.setSolde(solde+c.getPenalite());
			}
			//si compte débiteur
			else if(solde<0){
				c.setSolde(solde);	
			}
			
			Operation o=new Operation();
			o.setCompte(c);
			o.setDate(new Date());
			o.setMontant(montant);
			o.setType(TypeOperation.Retrait);
			List<Operation> historique=c.getOperations();
			historique.add(o);
			c.setOperations(historique);
			em.persist(c);
			result=true;
			}

		
		return result;
	}

}
