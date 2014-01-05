package controler;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banque.beans.ClientInconnu;
import banque.beans.GestionClients;
import banque.beans.GestionClientsRemote;
import banque.beans.GestionComptesRemote;
import banque.beans.GestionOperationsRemote;
import banque.entites.Client;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	GestionClientsRemote gestionClientsRemote;
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
		
		
		//recupere le gestionclient et on en ajoute
		//gestionClientsRemote=new GestionClients();
//		Client client1 = new Client();
//		client1.setLogin("Morgane");
//		client1.setPassword("morgane");
//		client1.setNom("PICASARazerRI");
//		client1.setPrenom("Morgane");
//		client1 = gestionClientsRemote.ajouterClient(client1);
//		
//		Client client2 = new Client();
//		client2.setLogin("Zlatan");
//		client2.setPassword("zlatan");
//		client2.setNom("Zlatan");
//		client2.setPrenom("Ibrahimovic");
//		client2 = gestionClientsRemote.ajouterClient(client2);
		
		String action = request.getParameter("action");
		String vueFinale="index.jsp";//par défaut on renvoie sur la page d'accueil
		if ("identi".equals(action)) {
			String password=request.getParameter("password");
			String login = request.getParameter("login");
			//TODO verification que le client existe
			//TODO faire methode Post plutôt que get
			
			try {
				Client a=gestionClientsRemote.verifierClient(login,password);
				 vueFinale = "accueil_client.jsp";
			} catch (ClientInconnu e) {
				
			}
			
			request.getRequestDispatcher(vueFinale).forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
