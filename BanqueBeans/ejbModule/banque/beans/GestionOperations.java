package banque.beans;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.Compte;
import banque.entites.Operation;
import banque.interfaceBeans.GestionOperationsLocal;
import banque.interfaceBeans.GestionOperationsRemote;

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

	public Operation ajouterOperation(Operation o){  // throws CompteInconnuException {
//		if(compte!=null){
//			//ancienne version 
//			//Compte old=manager.find(Compte.class,compte.getNumeroCompte());
//			Compte old=manager.find(Compte.class,compte.getId());
//			if (old!=null){
//				
//				Class classe=old.getClass();
//				if(classe.getName().equals("CompteEpargne")){
//					
//				}
//				//si operation est un retrait
//				if(o.isRetrait()) {
//					if(compte.retrait(o.getMontant())){  // pas de sauvegarde si le retrait n'est pas possible
//						manager.merge(compte);
//						o.setCompte(compte);
//						manager.persist(o);
//					};
//				}
//				//autrement depot
//				else{
//					compte.depot(o.getMontant());
//					manager.merge(compte);
//					o.setCompte(compte);
//					manager.persist(o);
//				}
//			}
			return o;
//		}
//		else{
//			return null;
////			throw new CompteInconnuException();
//		}
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

}
