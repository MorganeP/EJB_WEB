package banque.entites;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

/**
 * Entity implementation class for Entity: Compte
 */
@Entity
public class Compte implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String numeroCompte;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Client titulaire;
	
	@OneToMany(mappedBy="compte", cascade = ALL, fetch = EAGER) 
	private List<Operation> operations;
	
	double solde;
	
	public Compte() {
		super();
	}   
	public String getNumeroCompte() {
		return this.numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
   	
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public Client getTitulaire() {
		return titulaire;
	}
	public void setTitulaire(Client titulaire) {
		this.titulaire = titulaire;
	}
	
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public void addOperation(Operation operation) {
		this.operations.add(operation);
	}
	
	public void depot(double montant){
		solde = solde + montant;
	}
	public boolean retrait(double montant){
		double nouveauSolde = solde - montant;
		if(nouveauSolde>(double)0){
			solde = nouveauSolde;
			return true;
		}
		else return false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
