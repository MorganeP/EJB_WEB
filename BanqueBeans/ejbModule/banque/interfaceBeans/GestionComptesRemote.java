package banque.interfaceBeans;
import java.util.List;

import javax.ejb.Remote;

import banque.beans.ClientNullException;
import banque.beans.CompteInconnuException;
import banque.entites.Client;
import banque.entites.Compte;

@Remote
public interface GestionComptesRemote {

	public void choisirClient(Client c);

	public Compte ajouterCompte(Compte c) throws ClientNullException;

	public void modifierCompte(Compte compte) throws CompteInconnuException;
	
	public void supprimerCompte(Compte c);

	public List<Compte> getComptes();
	public boolean effectuerRetrait(Compte compte, double montant);
	public boolean effectuerDepot(Compte compte, double montant);
	
}
