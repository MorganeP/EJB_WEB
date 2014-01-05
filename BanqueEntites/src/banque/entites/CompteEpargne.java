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
public class CompteEpargne extends Compte implements Serializable {

	double taux;  // taux d'intérets
	
	private static final long serialVersionUID = 1L;
	
	public CompteEpargne() {
		super();
	}

	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	
//	public void crediterInterets(){
//		double interets = (taux*solde);
//		solde = solde + interets;
//	}
   
}
