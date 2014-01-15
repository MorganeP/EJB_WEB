package controler;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banque.beans.GestionBanqueRemote;
import banque.beans.PersonneInconnu;
import banque.beans.GestionClients;
import banque.beans.GestionClientsRemote;
import banque.beans.GestionComptesRemote;
import banque.beans.GestionConseillerRemote;
import banque.beans.GestionOperationsRemote;
import banque.entites.Banque;
import banque.entites.Client;
import banque.entites.Compte;
import banque.entites.Conseiller;
import banque.entites.Operation;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
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
    public index() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//TODO sdsd
		String action = request.getParameter("action");
		String type_client = request.getParameter("type");
		String vueFinale="index.jsp";//par défaut on renvoie sur la page d'accueil

		if ("identification".equals(action)) {
			String password=request.getParameter("password");
			String login = request.getParameter("login");
			if("client".equals(type_client)){
			
			//TODO faire methode Post plutôt que get
			
			
			try {
				Client c=gestionClientsRemote.verifierClient(login,password);
				List <Compte> comptes=gestionClientsRemote.listeComptes(c);
				request.getSession().setAttribute("client",c);
				request.getSession().setAttribute("comptes",comptes);
				 vueFinale = "accueil_client.jsp";
				 
			} catch (PersonneInconnu e) {
				vueFinale="erreur.jsp";
			}
			}
			else if("conseiller".equals(type_client)){
				try {
					Conseiller c=gestionConseillersRemote.verifierConseiller(login,password);
					Set <Client> clients_conseiller= c.getClients();
					request.getSession().setAttribute("conseiller",c);
					request.getSession().setAttribute("clients_conseiller",clients_conseiller);
					//TODO comment afficher comptes de chaque client sur une jsp sans déclancher lazilyexception
					 vueFinale = "accueil_conseiller.jsp";
					 
				} catch (PersonneInconnu e) {
					vueFinale="erreur.jsp";
				}
			}
			else if("admin".equals(type_client)){
				if("admin".equals(login)&"admin".equals(password)){
					vueFinale = "accueil_admin.jsp";
					List<Banque> banques=gestionBanquesRemote.getListeBanque();
					request.getSession().setAttribute("banque",banques);
				}
				else{
					vueFinale="erreur.jsp";

				}
			}
					
		}
		else if(action.startsWith("historique")){
			//TODO operation en double alors que pas de requete EJBQL
//			int id_compte=Integer.parseInt(action.substring(11));
			int id_compte=Integer.parseInt(request.getParameter("id"));
			request.setAttribute("page_origine", "conseiller");
			Compte a=gestionComptesRemote.recupererCompte(id_compte);
			List <Operation> operations=gestionOperationsRemote.getOperations(a);
			request.getSession().setAttribute("compte_historique",a);
			request.getSession().setAttribute("compte_operation",operations);
			vueFinale = "historique_compte.jsp";
		}
		else if(action.equals("retour")){
			vueFinale = "accueil_conseiller.jsp";
		}
		request.getRequestDispatcher(vueFinale).forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
