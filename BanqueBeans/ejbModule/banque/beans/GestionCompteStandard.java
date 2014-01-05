package banque.beans;

import javax.ejb.Stateless;

import banque.entites.CompteStandard;

/**
 * Session Bean implementation class GestionCompteStandard
 */
@Stateless
public class GestionCompteStandard implements GestionCompteStandardRemote, GestionCompteStandardLocal {

    /**
     * Default constructor. 
     */
    public GestionCompteStandard() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean effectuerRetrait(CompteStandard compteStandard,
			double montant) {
		// TODO Auto-generated method stub
		return false;
	}

}
