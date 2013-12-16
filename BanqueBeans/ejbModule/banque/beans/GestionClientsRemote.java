package banque.beans;

import java.util.List;

import javax.ejb.Remote;

import banque.entites.Client;

@Remote
public interface GestionClientsRemote {

    public Client ajouterClient(Client c);

	public List<Client> getListeClients();

	public void retirerClient(Client client);

	public Client findClient(String login);

	public void modifierClient(Client client) throws ClientInconnu;
    
	public Client verifierClient(String login,String password) throws ClientInconnu;

}
