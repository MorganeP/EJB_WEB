package banque.beans;

import javax.ejb.Stateless;

import banque.entites.CompteEpargne;

/**
 * Session Bean implementation class GestionCompteEpargne
 */
@Stateless
public class GestionCompteEpargne implements GestionCompteEpargneRemote, GestionCompteEpargneLocal {

    /**
     * Default constructor. 
     */
    public GestionCompteEpargne() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void ajouterInteret(CompteEpargne compteEpargne) {
		// TODO Auto-generated method stub
		
	}

}
