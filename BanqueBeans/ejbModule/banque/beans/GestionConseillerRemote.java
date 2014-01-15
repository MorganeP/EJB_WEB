package banque.beans;

import java.util.List;

import javax.ejb.Remote;

import banque.entites.Banque;
import banque.entites.Client;
import banque.entites.Conseiller;

@Remote
public interface GestionConseillerRemote {
	   public Conseiller ajouterConseiller(Conseiller c);

		public List<Conseiller> getListeConseiller();

		public void supprimerConseiller(Conseiller conseiller);

		public void modifierConseiller(Conseiller conseiller) throws ConseillerInconnu ;
	    
		public List<Client> getListeClient(Conseiller conseiller);
		
		public Conseiller findConseiller(int id);

		Conseiller verifierConseiller(String nom, String password) throws PersonneInconnu;

}
