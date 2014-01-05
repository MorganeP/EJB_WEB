package banque.beans;

import javax.ejb.Stateless;

import banque.entites.ComptePlatine;

/**
 * Session Bean implementation class GestionComptePlatine
 */
@Stateless
public class GestionComptePlatine implements GestionComptePlatineRemote, GestionComptePlatineLocal {

    /**
     * Default constructor. 
     */
    public GestionComptePlatine() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean effectuerRetrait(ComptePlatine comptePlatine, double montant) {
		// TODO Auto-generated method stub
		return false;
	}

}
