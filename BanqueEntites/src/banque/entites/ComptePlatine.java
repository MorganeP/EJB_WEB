package banque.entites;

import banque.entites.Compte;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CompteStandard
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ComptePlatine extends Compte implements Serializable {

	double decouvert;	// autorisation de découvert
	
	private static final long serialVersionUID = 1L;
	
	public ComptePlatine() {
		super();
	}

	public double getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	public void depot(double montant){
		solde = solde + montant;
	}
	public boolean retrait(double montant){
		double nouveauSolde = solde - montant;
		if(nouveauSolde>(((double)0)-decouvert)){
			solde = nouveauSolde;
			return true;
		}
		else return false;
	}
	
   
}
