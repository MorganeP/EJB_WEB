package banque.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.CompteEpargne;
import banque.entites.CompteStandard;
import banque.entites.Operation;
import banque.entites.TypeOperation;

/**
 * Session Bean implementation class GestionCompteEpargne
 */
@Stateless
public class GestionCompteEpargne implements GestionCompteEpargneRemote, GestionCompteEpargneLocal {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GestionCompteEpargne() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void ajouterInteret(CompteEpargne compteEpargne) {
		CompteEpargne c=em.find(CompteEpargne.class, compteEpargne.getId());
		double interets = (c.getTaux()*c.getSolde());
		Operation o=new Operation();
		o.setCompte(c);
		o.setDate(new Date());
		o.setMontant(interets);
		o.setType(TypeOperation.Intérêt);
		List<Operation> historique=c.getOperations();
		historique.add(o);
		c.setOperations(historique);
		em.persist(c);
		
		
	}

}
