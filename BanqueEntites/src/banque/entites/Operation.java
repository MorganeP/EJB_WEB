package banque.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Operation
 *
 */
@Entity
public class Operation implements Serializable {

	@Id
	@GeneratedValue
	private int id; 
	
	@ManyToOne
	private Compte compte;
	private Date date;
	private TypeOperation type;
	private double montant;
	private static final long serialVersionUID = 1L;

	
	public Operation() {
		super();
	}  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

//	public boolean isRetrait() {
//		return retrait;
//	}
//	public void setRetrait(boolean retrait) {
//		this.retrait = retrait;
//	}   
	
	public double getMontant() {
		return this.montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TypeOperation getType() {
		return type;
	}

	public void setType(TypeOperation type) {
		this.type = type;
	}
   
}
