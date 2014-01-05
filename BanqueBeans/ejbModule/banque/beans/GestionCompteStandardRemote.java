package banque.beans;

import javax.ejb.Remote;

import banque.entites.CompteStandard;

@Remote
public interface GestionCompteStandardRemote {
	public boolean effectuerRetrait(CompteStandard compteStandard, double montant);
}
