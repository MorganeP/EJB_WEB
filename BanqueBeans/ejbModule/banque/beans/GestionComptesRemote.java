package banque.beans;
import java.util.List;

import javax.ejb.Remote;

import banque.entites.Client;
import banque.entites.Compte;

@Remote
public interface GestionComptesRemote {

	public void choisirClient(Client c);

	public Compte ajouterCompte(Compte c) throws ClientNullException;

	public void modifierCompte(Compte compte) throws CompteInconnuException;
	
	public void supprimerCompte(Compte c);
	public Compte recupererCompte(int id);

	public List<Compte> getComptes();
	public boolean effectuerRetrait(Compte compte, double montant);
	public boolean effectuerDepot(Compte compte, double montant);
	
}
