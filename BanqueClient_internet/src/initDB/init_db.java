package initDB;

import java.io.IOException;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banque.beans.ClientNullException;
import banque.beans.GestionBanqueRemote;
import banque.beans.GestionClientsRemote;
import banque.beans.GestionCompteStandardRemote;
import banque.beans.GestionComptesRemote;
import banque.beans.GestionConseillerRemote;
import banque.beans.GestionOperationsRemote;
import banque.entites.Banque;
import banque.entites.Client;
import banque.entites.CompteEpargne;
import banque.entites.ComptePlatine;
import banque.entites.CompteStandard;
import banque.entites.Conseiller;

/**
 * Servlet implementation class init_db
 */
@WebServlet("/init_db")
public class init_db extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	GestionClientsRemote gestionClientsRemote;
	@EJB
	GestionComptesRemote gestionComptesRemote;
	@EJB
	GestionCompteStandardRemote gestionComptesStandardRemote;
	@EJB
	GestionConseillerRemote gestionConseillersRemote;
	@EJB
	GestionBanqueRemote gestionBanquesRemote;
	@EJB
	GestionOperationsRemote gestionOperationsRemote;  
    /**
    /**
     * @see HttpServlet#HttpServlet()
     */
    public init_db() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String vueFinale="index.jsp";
		if(action.equals("init")){
			init_DB();
		}
		request.setAttribute("initialisation", true);
		request.getRequestDispatcher(vueFinale).forward(request, response);	
	}

	public void init_DB(){
		Banque banque=new Banque();
		banque.setNom("LCL");
		banque=gestionBanquesRemote.ajouterBanque(banque);//ajout à la BDD
		
		Banque banqueb=new Banque();
		banqueb.setNom("BNP");
		banqueb=gestionBanquesRemote.ajouterBanque(banqueb);//ajout à la BDD
		
		Conseiller conseiller=new Conseiller();
		conseiller.setBanque(banque);
		conseiller.setNom("Jacque");
		conseiller.setMdp("Jacque");
		conseiller=gestionConseillersRemote.ajouterConseiller(conseiller);
		
		Conseiller conseillerb=new Conseiller();
		conseillerb.setBanque(banqueb);
		conseillerb.setNom("Tahiti Bob");
		conseillerb.setMdp("Tahiti");
		conseillerb=gestionConseillersRemote.ajouterConseiller(conseillerb);
		
		Client client1 = new Client();
		client1.setLogin("Morgane");
		client1.setPassword("morgane");
		client1.setNom("PICARISI");
		client1.setPrenom("Morgane");
		client1.setBanque(banque);
		client1.setConseiller(conseiller);
		
		client1 = gestionClientsRemote.ajouterClient(client1);
		
		
		Client client2 = new Client();
		client2.setBanque(banqueb);
		client2.setLogin("Zlatan");
		client2.setPassword("zlatan");
		client2.setNom("Zlatan");
		client2.setPrenom("Ibrahimovic");
		client2.setConseiller(conseillerb);
		
		client2 = gestionClientsRemote.ajouterClient(client2);
		
		
		

		CompteStandard compteA = new CompteStandard();
		Random r = new Random();
		int valeur = 1000 + r.nextInt(100000 - 1000);
		compteA.setNumeroCompte("0000001"+valeur);
		compteA.setPenalite(5);
		compteA.setSolde(1000);
		
		ComptePlatine compteB = new ComptePlatine();
		r=new Random();
		valeur = 1000 + r.nextInt(100000 - 1000);
		compteB.setNumeroCompte("0000002"+valeur);
		compteB.setDecouvert(500);
		compteB.setSolde(1000);	
		
		CompteEpargne compteC = new CompteEpargne();
		r=new Random();
		valeur = 1000 + r.nextInt(100000 - 1000);
		compteC.setNumeroCompte("0000003"+valeur);
		compteC.setSolde(1000);
		compteC.setTaux(0.1);
		
		try {
		compteA = (CompteStandard) gestionComptesRemote.ajouterCompte(compteA,client1);
		compteB = (ComptePlatine) gestionComptesRemote.ajouterCompte(compteB,client1);
		}
		catch( ClientNullException e){
			e.printStackTrace();}
		
		try {
		compteC = (CompteEpargne) gestionComptesRemote.ajouterCompte(compteC,client2);
		}
		
		catch( ClientNullException e){
			e.printStackTrace();
		}
		
		//operation sur un comtpe standard
		gestionComptesStandardRemote.effectuerRetrait(compteA, 10);
		gestionComptesRemote.effectuerDepot(compteA, 200);
		

		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
