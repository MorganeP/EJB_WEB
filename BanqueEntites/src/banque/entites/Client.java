package banque.entites;
import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;


/**
 * Entity implementation class for Entity: Client
 */
@Entity
public class Client implements Serializable {
  
	@Id
	@GeneratedValue
	private int id;   
	private String nom;
	private String prenom;
	private String login;   
	private String password;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="titulaire",fetch=EAGER, cascade = ALL) 
	private Set<Compte> comptes;
	@ManyToOne
	private Conseiller conseiller;
	
	@ManyToOne
	private Banque banque;
	
	public Client() {
		super();
	}   
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return this.login;
	}
	public void setLogin(String login) {
		this.login = login;
	}   
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}
//	public void addCompte(Compte c){
//		comptes.add(c);
//	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}
   
}
