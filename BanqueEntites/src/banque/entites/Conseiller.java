package banque.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Conseiller
 *
 */
@Entity

public class Conseiller implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;
	private String nom;
	private String mdp;
	
	@ManyToOne
	private Banque banque;	
	
	@OneToMany(mappedBy="conseiller",fetch=FetchType.EAGER)
	private List<Client> clients = new ArrayList<Client>();
	
	private static final long serialVersionUID = 1L;

	public Conseiller() {
		super();
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public int getId() {
		return id;
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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}   
		   
}
