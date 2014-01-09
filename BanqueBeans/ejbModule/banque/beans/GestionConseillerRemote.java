package banque.beans;

import java.util.List;

import javax.ejb.Remote;

import banque.entites.Banque;
import banque.entites.Conseiller;

@Remote
public interface GestionConseillerRemote {
	   public Conseiller ajouterConseiller(Conseiller c);

		public List<Conseiller> getListeConseiller();

		public void supprimerConseiller(Conseiller conseiller);

		public void modifierConseiller(Conseiller conseiller) throws ConseillerInconnu ;
	    

}
