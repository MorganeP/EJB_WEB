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
   
}
