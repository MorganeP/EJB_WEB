package banque.beans;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import banque.entites.Client;

/**
 * Session Bean implementation class GestionClients
 */
@Stateless  
@LocalBean
public class GestionClients implements GestionClientsRemote, GestionClientsLocal {

	@PersistenceContext
	EntityManager manager;//l'entityManager va permettre au client de faire des op�rations sur la BDD
	
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

	public void modifierClient(Client client) throws ClientInconnu {
		client=manager.find(Client.class,client.getId());
		if (client!=null)
			manager.merge(client);
		else throw new ClientInconnu();
	}

	@Override
	public Client verifierClient(String login, String password)throws ClientInconnu {
		Client client = null;
		if( login!=null){
			List<Client> liste = getListeClients();
			for( Client c: liste){
				if(c.getLogin().equals(login)&&c.getPassword().equals(password)){
					client = c;
				}
			}
			if(client==null)
				throw new ClientInconnu();
		}
		return client;
	}
    

}