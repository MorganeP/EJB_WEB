package controler;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banque.beans.BanqueInconnu;
import banque.beans.CompteInconnuException;
import banque.beans.ConseillerInconnu;
import banque.beans.GestionBanqueRemote;
import banque.beans.GestionClientsRemote;
import banque.beans.GestionComptesRemote;
import banque.beans.GestionConseillerRemote;
import banque.beans.GestionOperationsRemote;
import banque.beans.PersonneInconnu;
import banque.entites.Banque;
import banque.entites.Client;
import banque.entites.Compte;
import banque.entites.CompteEpargne;
import banque.entites.ComptePlatine;
import banque.entites.CompteStandard;
import banque.entites.Conseiller;
import banque.entites.Operation;

/**
 * Servlet implementation class admincontroler
 */
@WebServlet("/admincontroler")
public class admincontroler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	GestionClientsRemote gestionClientsRemote;
	@EJB
	GestionComptesRemote gestionComptesRemote;

	@EJB
	GestionConseillerRemote gestionConseillersRemote;
	@EJB
	GestionBanqueRemote gestionBanquesRemote;
	@EJB
	GestionOperationsRemote gestionOperationsRemote;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admincontroler() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action=request.getParameter("action");

	String action2=request.getParameter("action2");
	String origine_action2=request.getParameter("origine_action2");
	String vueFinale="accueil_admin.jsp";
	
	if(action!=null){
		if(action.equals("lco")){
			int id=Integer.parseInt(request.getParameter("id"));
			Banque a=gestionBanquesRemote.findBanque(id);
			request.setAttribute("action", action);
			Set<Conseiller> conseillers=a.getConseillers();
			request.getSession().setAttribute("conseillers_banque",conseillers);	
		}
		else if(action.equals("lcl")){
			int id=Integer.parseInt(request.getParameter("id"));
			Banque a=gestionBanquesRemote.findBanque(id);
			Set<Client> clients=a.getClients();
			request.setAttribute("action", action);
			request.getSession().setAttribute("banque_selec",a);
			request.getSession().setAttribute("clients_banque",clients);
			//TODO apparemment liste clients d'une banque nulle
			
		}
		else if(action.equals("lcom")){
			int id=Integer.parseInt(request.getParameter("id"));
			Banque a=gestionBanquesRemote.findBanque(id);
			request.setAttribute("action", action);
			Set<Client> clients=a.getClients();
			Set<Compte> comptes_banque = new HashSet<Compte>();
			for(Client client:clients){
				for(Compte compte:client.getComptes()){
					comptes_banque.add(compte);
				}
			}
			request.getSession().setAttribute("comptes_banque",comptes_banque);
		}
		else if(action.equals("editer")){
			
			int id=Integer.parseInt(request.getParameter("id"));
			String type=request.getParameter("type");
			request.setAttribute("page_origine","admin");
			request.setAttribute("type",type);
			List<Conseiller> conseillers=gestionConseillersRemote.getListeConseiller();
			request.getSession().setAttribute("conseillers", conseillers);
			List<Client> clients=gestionClientsRemote.getListeClients();
			System.out.println("recuperation de la liste");
			request.getSession().setAttribute("liste_clients", clients);
			vueFinale="modification.jsp";
			
			if(type.equals("client")){
				Client c=gestionClientsRemote.findClient(id);
				
				request.setAttribute("a_modif", c);
			}
			else if(type.equals("banque")){
				Banque c=gestionBanquesRemote.findBanque(id);
				request.setAttribute("a_modif", c);
			}
			else if(type.equals("compte")){
				Compte c=gestionComptesRemote.recupererCompte(id);
				request.setAttribute("a_modif", c);
			}
			else if(type.equals("conseiller")){
				Conseiller c=gestionConseillersRemote.findConseiller(id);
				request.setAttribute("a_modif", c);
			}
		}
		else if(action.equals("valider modification")){
			String type=request.getParameter("type");
			System.out.println("request.getParameter(id):"+request.getParameter("id"));
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("id:"+id);
			
			if(type.equals("client")){
				String nom=request.getParameter("nom");
				String prenom=request.getParameter("prenom");
				int id_conseiller=Integer.parseInt(request.getParameter("conseiller"));
				String login=request.getParameter("login");
				String mdp=request.getParameter("mdp");
				Client c=gestionClientsRemote.findClient(id);
				c.setNom(nom);
				c.setPrenom(prenom);
				c.setLogin(login);
				c.setPassword(mdp);
				Conseiller conseiller=gestionConseillersRemote.findConseiller(id_conseiller);
				Banque banque=conseiller.getBanque();
				c.setConseiller(conseiller);
				c.setBanque(banque);
				try {
					gestionClientsRemote.modifierClient(c);
				} catch (PersonneInconnu e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if(type.equals("banque")){
				 Banque a=gestionBanquesRemote.findBanque(id);
				 a.setNom(request.getParameter("nom"));
				 try {
					gestionBanquesRemote.modifierBanque(a);
				} catch (BanqueInconnu e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<Banque> banques=gestionBanquesRemote.getListeBanque();
				request.getSession().setAttribute("banque",banques);
			}
			else if(type.equals("compte")){

				Compte c = gestionComptesRemote.recupererCompte(id);
//				System.out.println("############################################  c:"+c);
				
				//String type_compte=request.getParameter("type_compte");
//				if(type_compte.equals("epargne")){
//				 c =new CompteEpargne();
//				 c.setId(id);
//				}
//					
//				else if(type_compte.equals("standard")){
//					 c=new CompteStandard();
//					 c.setId(id);
//				}
//					
//				else if(type_compte.equals("platinium")){
//					 c=new ComptePlatine();
//					 c.setId(id);
//				}

				c.setNumeroCompte(request.getParameter("numero_compte"));
				int id_client=Integer.parseInt(request.getParameter("client"));
				Client client=gestionClientsRemote.findClient(id_client);
				c.setTitulaire(client);

				try {
					gestionComptesRemote.modifierCompte(c);
				} catch (CompteInconnuException e) {
					e.printStackTrace();
				}
//				
			}
			else if(type.equals("conseiller")){
				int id_banque=Integer.parseInt(request.getParameter("banque"));
				Banque banque=gestionBanquesRemote.findBanque(id_banque);
				Conseiller a=gestionConseillersRemote.findConseiller(id);
				String nom=request.getParameter("nom");
				String mdp=request.getParameter("mdp");
				a.setBanque(banque);
				a.setNom(nom);
				a.setMdp(mdp);
				try {
					gestionConseillersRemote.modifierConseiller(a);
				} catch (ConseillerInconnu e) {
					e.printStackTrace();
				}
				
				
			}
		}
	}
	if(action2!=null){
	
		if(action2.equals("lcom")){
			if(origine_action2.equals("client")){
				int id_client=Integer.parseInt(request.getParameter("id_client"));
				Client client=gestionClientsRemote.findClient(id_client);
				Set<Compte> comptes_client=client.getComptes();
				request.setAttribute("action2", action2);
				request.setAttribute("origine_action2", origine_action2);
				request.getSession().setAttribute("comptes_client", comptes_client);	
			}
	
		}

	}
	if(action!=null){
	 if(action.startsWith("historique")){
		//TODO operation en double alors que pas de requete EJBQL
		//int id_compte=Integer.parseInt(action.substring(11));
		 int id_compte=Integer.parseInt(request.getParameter("id"));
		Compte a=gestionComptesRemote.recupererCompte(id_compte);
		List <Operation> operations=gestionOperationsRemote.getOperations(a);
		request.getSession().setAttribute("compte_historique",a);
		request.getSession().setAttribute("compte_operation",operations);
		request.setAttribute("page_origine","accueil_admin");
		vueFinale = "historique_compte.jsp";
	}
	
	 else if(action.equals("supprimer")){
		 String type=request.getParameter("type");
		 int id=Integer.parseInt(request.getParameter("id"));
		 
		 if(type.equals("client")){
			 Client a=gestionClientsRemote.findClient(id);
			 a.setBanque(null);
			 a.setConseiller(null);
			 try {
				gestionClientsRemote.modifierClient(a);
			} catch (PersonneInconnu e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 gestionClientsRemote.retirerClient(a);
		 }
		 else if(type.equals("banque")){
			 Banque b=gestionBanquesRemote.findBanque(id);
			 gestionBanquesRemote.supprimerBanque(b);
			 List<Banque> banques=gestionBanquesRemote.getListeBanque();
			request.getSession().setAttribute("banque",banques);
		 }
		 else if(type.equals("conseiller")){
			 Conseiller c=gestionConseillersRemote.findConseiller(id);
			 c.setBanque(null);
			 //on modifie tous les clients associès à ce conseiller
			 Set<Client> clients=c.getClients();
			 for(Client client:clients){
				 client.setConseiller(null);
				 try {
					gestionClientsRemote.modifierClient(client);
				} catch (PersonneInconnu e) {
					e.printStackTrace();
				}
			 }
			 //on modifie le client dans la base avant de le supprimer
			 try {
				gestionConseillersRemote.modifierConseiller(c);
			} catch (ConseillerInconnu e) {

				e.printStackTrace();
			}
			 gestionConseillersRemote.supprimerConseiller(c);
		 }
		 else if(type.equals("compte")){
			 
			 Compte compte=gestionComptesRemote.recupererCompte(id);
			 compte.setTitulaire(null);
			 try {
				gestionComptesRemote.modifierCompte(compte);
			} catch (CompteInconnuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 gestionComptesRemote.supprimerCompte(compte);
		 }
	 }
	 if(action.equals("Ajouter")){
		 String type_ajout=request.getParameter("type_ajout");

		 if(type_ajout.equals("banque")){
			 Banque a=new Banque();
			 a.setNom(request.getParameter("nom"));
			 gestionBanquesRemote.ajouterBanque(a);
				List<Banque> banques=gestionBanquesRemote.getListeBanque();
				request.getSession().setAttribute("banque",banques);
		 }
		 else if(type_ajout.equals("client")){
			 String nom=request.getParameter("nom");
			 String prenom=request.getParameter("prenom");
			 int conseiller=Integer.parseInt(request.getParameter("conseiller"));
			 String login=request.getParameter("login");
			 String mdp=request.getParameter("mdp");
			 Client client=new Client();
			 client.setLogin(login);
			 client.setNom(nom);
			 client.setPassword(mdp);
			 client.setPrenom(prenom);
			 Conseiller b=gestionConseillersRemote.findConseiller(conseiller);
			 client.setConseiller(b);
			 client.setBanque(b.getBanque());
			 gestionClientsRemote.ajouterClient(client);
		 }
		 else if(type_ajout.equals("compte")){
			 
				String type_compte=request.getParameter("type_compte");
				String num_compte=request.getParameter("numero_compte");
				int id_client=Integer.parseInt(request.getParameter("client"));
				Compte compte = null;
				if(type_compte.equals("epargne")){
					 compte=new CompteEpargne();
				}
				else if(type_compte.equals("standard")){
					 compte=new CompteStandard();
				}
				else if(type_compte.equals("platinium")){
					 compte=new ComptePlatine();
				}
				
				compte.setNumeroCompte(num_compte);
				Client client=gestionClientsRemote.findClient(id_client);
				compte.setTitulaire(client);
				gestionComptesRemote.ajouterCompte(compte);
		 }
		 else if(type_ajout.equals("conseiller")){
			 String nom=request.getParameter("nom");
			 String mdp=request.getParameter("mdp");
			 int id_banque=Integer.parseInt(request.getParameter("banque"));
			 
			 Conseiller conseiller=new Conseiller();
			 conseiller.setNom(nom);
			 conseiller.setMdp(mdp);
			 Banque a=gestionBanquesRemote.findBanque(id_banque);
			 conseiller.setBanque(a);
			 gestionConseillersRemote.ajouterConseiller(conseiller);
		 }
		 
	 }
	 if(action.startsWith("Ajouter")){
		if(action.endsWith("client")){
			request.setAttribute("type_ajout","client");
			List<Client> clients=gestionClientsRemote.getListeClients();
			System.out.println("recuperation de la liste");
			request.getSession().setAttribute("liste_clients", clients);
			List<Conseiller> conseillers=gestionConseillersRemote.getListeConseiller();
			request.getSession().setAttribute("conseillers", conseillers);
			System.out.println("ajout de la liste");
		}
		else if(action.endsWith("conseiller")){
			request.setAttribute("type_ajout","conseiller");
			List<Conseiller> conseillers=gestionConseillersRemote.getListeConseiller();
			request.getSession().setAttribute("conseillers", conseillers);
		}
		else if(action.endsWith("banque")){
			request.setAttribute("type_ajout","banque");
		}
		else if(action.endsWith("compte")){
			List<Client> clients=gestionClientsRemote.getListeClients();
			request.getSession().setAttribute("liste_clients", clients);
			request.setAttribute("type_ajout","compte");
		}
		
	 }
	}
	request.getRequestDispatcher(vueFinale).forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
