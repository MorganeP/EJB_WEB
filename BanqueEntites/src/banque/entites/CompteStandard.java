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
public class CompteStandard extends Compte implements Serializable {

	double penalite;  // pénalité en cas de retrait
	
	private static final long serialVersionUID = 1L;
	
	public CompteStandard() {
		super();
	}

	public double getPenalite() {
		return penalite;
	}
	public void setPenalite(double penalite) {
		this.penalite = penalite;
	}
	
	public boolean retrait(double montant){
		double nouveauSolde = solde - montant - penalite;
		if(nouveauSolde>(double)0){
			solde = nouveauSolde;
			return true;
		}
		else return false;
	}
	
   
}
