package controler;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banque.beans.GestionClientsRemote;
import banque.beans.GestionComptesRemote;
import banque.beans.GestionConseillerRemote;
import banque.beans.GestionOperationsRemote;
import banque.entites.Client;
import banque.entites.Compte;
import banque.entites.Operation;

/**
 * Servlet implementation class client
 */
@WebServlet("/client")
public class client extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	GestionClientsRemote gestionClientsRemote;
	@EJB
	GestionComptesRemote gestionComptesRemote;

	@EJB
	GestionConseillerRemote gestionConseillersRemote;
	@EJB
	GestionOperationsRemote gestionOperationsRemote;
    public client() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String type_client = request.getParameter("type");
		String vueFinale="index.jsp";//par défaut on renvoie sur la page d'accueil
		 if(action.startsWith("historique")){
			//TODO operation en double alors que pas de requete EJBQL
			int id_compte=Integer.parseInt(action.substring(11));
			Compte a=gestionComptesRemote.recupererCompte(id_compte);
			List <Operation> operations=gestionOperationsRemote.getOperations(a);
			request.getSession().setAttribute("compte_historique",a);
			request.getSession().setAttribute("compte_operation",operations);
			vueFinale = "historique_compte.jsp";
		}
		 else if(action.equals("effectuer opération")){
			 String montant=request.getParameter("montant");
			 String id_compte=request.getParameter("numero_compte");
			 String operation=request.getParameter("operation");
			 //TODO verifier que montant chiffre
			 Compte b=gestionComptesRemote.recupererCompte(Integer.parseInt(id_compte));
			 boolean a;
			 if(operation.equals("retrait")){
				  a=gestionComptesRemote.effectuerRetrait(b,Integer.parseInt(montant));

			 }
			 else{
				 a=gestionComptesRemote.effectuerDepot(b,Integer.parseInt(montant));
			 }
			  if(a){
				  vueFinale="operation_conf.jsp";
				  Client c = (Client) request.getSession().getAttribute("client");
				  List <Compte> comptes=gestionClientsRemote.listeComptes(c);
				  //on reset l'attribut comptes suite à la modif
				  request.getSession().setAttribute("comptes",comptes);
			  }
			  else{
				  vueFinale="erreur.jsp";
			  }
			 
		 }
		 else if(action.equals("retour")){
			 vueFinale="accueil_client.jsp";
		 }
		request.getRequestDispatcher(vueFinale).forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
