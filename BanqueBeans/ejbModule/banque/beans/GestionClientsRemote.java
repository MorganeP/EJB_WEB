package banque.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import banque.entites.Client;
import banque.entites.Compte;

@Remote
public interface GestionClientsRemote {

    public Client ajouterClient(Client c);

	public List<Client> getListeClients();

	public void retirerClient(Client client);

	public Client findClient(int id_client);

	public void modifierClient(Client client) throws PersonneInconnu;
    
	public Client verifierClient(String login,String password) throws PersonneInconnu;
	
	public List<Compte> listeComptes(Client client);

}
