package banque.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Banque
 *
 */
@Entity

public class Banque implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String nom;
	
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL},mappedBy="banque")
	private Set<Client> clients = new HashSet<Client>();
	
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL},mappedBy="banque")
	private Set<Conseiller> conseillers = new HashSet<Conseiller>();
	
	private static final long serialVersionUID = 1L;

	public Banque() {
		super();
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

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Set<Conseiller> getConseillers() {
		return conseillers;
	}

	public void setConseillers(Set<Conseiller> conseillers) {
		this.conseillers = conseillers;
	}
   
}
