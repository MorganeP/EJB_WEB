package banque.beans;

import java.util.List;

import javax.ejb.Remote;

import banque.entites.Banque;
import banque.entites.Client;

@Remote
public interface GestionBanqueRemote {

	   public Banque ajouterBanque(Banque c);

		public List<Banque> getListeBanque();

		public void retirerBanque(Banque banque);

		public Banque findBanque(String login);

		public void modifierBanque(Banque banque) ;
	    

}
