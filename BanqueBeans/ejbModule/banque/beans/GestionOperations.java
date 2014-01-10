package banque.beans;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import banque.entites.Compte;
import banque.entites.Operation;

/**
 * Session Bean implementation class GestionOperations
 */
@Stateful
@LocalBean
public class GestionOperations implements GestionOperationsRemote, GestionOperationsLocal {

	@PersistenceContext
	EntityManager manager;
	Compte compte=null;
	List<Operation> operations = new ArrayList<Operation>();
	
    /**
     * Default constructor. 
     */
    public GestionOperations() {
    }

    public void choisirCompte(Compte c) {
		compte = c;
	}

	public Operation ajouterOperation(Operation o,Compte c){  // throws CompteInconnuException {

			return o;

	}

//	Modifiable ??
//	public void modifierOperation(Operation o) throws OperationInconnue {
//		o=manager.find(Operation.class,o.getId());
//		if (o!=null)
//		manager.merge(o);
//		else throw new OperationInconnue();
//	}
	
	public void supprimerOperation(Operation o) {
		o = manager.find(Operation.class,o.getId());
		manager.remove(o);
	}
	
	public Operation findOperation(int id) {
		return manager.find(Operation.class,id);
	}

	public List<Operation> getOperations() {
		operations = manager.createQuery("Select o from Operation o").getResultList();
		return operations;
	}

	@Override
	public List<Operation> getOperations(Compte compte) {
		Query query = manager.createQuery("SELECT operation FROM Operation AS operation , Compte AS compte WHERE operation.compte.id = :id_compte");
		query.setParameter("id_compte",compte.getId());
		List<Operation> comptes=query.getResultList();
		return  comptes;
	}

}
