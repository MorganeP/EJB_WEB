package banque.beans;

import javax.ejb.Remote;

import banque.entites.CompteEpargne;

@Remote
public interface GestionCompteEpargneRemote {
	public void ajouterInteret(CompteEpargne compteEpargne);
}
