package banque.beans;

import javax.ejb.Remote;

import banque.entites.ComptePlatine;

@Remote
public interface GestionComptePlatineRemote {
	public boolean effectuerRetrait(ComptePlatine comptePlatine, double montant);
}
