package banque.beans;

import java.util.List;

import javax.ejb.Remote;

import banque.entites.Compte;
import banque.entites.Operation;

@Remote
public interface GestionOperationsRemote {

	public void choisirCompte(Compte c);

	public Operation ajouterOperation(Operation o); // throws CompteInconnuException;
		
//	Modifiable ??
//	public void modifierOperation(Operation o);
	
	public void supprimerOperation(Operation o);
	
	public Operation findOperation(int id);

	public List<Operation> getOperations();
	public List<Operation> getOperations(Compte compte);

}
