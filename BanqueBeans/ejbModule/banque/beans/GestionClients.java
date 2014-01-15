package banque.beans;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import banque.entites.Client;
import banque.entites.Compte;

/**
 * Session Bean implementation class GestionClients
 */
@Stateless  
@LocalBean
public class GestionClients implements GestionClientsRemote, GestionClientsLocal {

	@PersistenceContext
	EntityManager manager;//l'entityManager va permettre au client de faire des opérations sur la BDD
	
    /**
     * Default constructor. 
     */
    public GestionClients() {
    }

    public Client ajouterClient(Client c) {
		manager.persist(c);
		return c;
	}

	public List<Client> getListeClients() {
		return manager.createQuery("Select c from Client c").getResultList();
	}

	public void retirerClient(Client client) {
		client=manager.find(Client.class,client.getId());
		manager.remove(client);
	}

	public Client findClient(int id) {
		return manager.find(Client.class,id);
	}
	// faire une requete pour trouver client en fonction de son login
	public Client findClient(String login) {  
		Client client = null;
		if( login!=null){
			List<Client> liste = getListeClients();
			for( Client c: liste){
				if(c.getLogin().equals(login)){
					client = c;
				}
			}
		}
		return client;
	}

	public void modifierClient(Client client) throws PersonneInconnu {
		Client cli=manager.find(Client.class,client.getId());
		if (cli!=null)
			manager.merge(client);
		else throw new PersonneInconnu();
	}

	@Override
	public Client verifierClient(String login, String password)throws PersonneInconnu {
		Client client = null;
		if( login!=null){
			List<Client> liste = getListeClients();
			for( Client c: liste){
				if(c.getLogin().equals(login)&&c.getPassword().equals(password)){
					client = c;
				}
			}
			if(client==null)
				throw new PersonneInconnu();
		}
		return client;
	}

	@Override
	public List<Compte> listeComptes(Client client) {
		//Query query = manager.createQuery("select c from Compte c where c.titulaire_id=:id_client");
		Query query = manager.createQuery("SELECT DISTINCT compte FROM Compte AS compte , Client AS client WHERE compte.titulaire.id = :id_client");
		query.setParameter("id_client",client.getId());
		List<Compte> comptes=query.getResultList();
		return  comptes;
	}
    

}
