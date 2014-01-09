import java.util.Hashtable;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;

import banque.beans.ClientNullException;
import banque.beans.CompteNullException;
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


public class Main {
	public static void main(String[] args) {
		Main client = new Main();
		client.test1();
	}

	public Main() {
	}

	private void test1() {

		try {
			System.out.println("Go !");
			
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put(Context.SECURITY_PRINCIPAL,"greg");
		    jndiProperties.put(Context.SECURITY_CREDENTIALS, "azerty");
		    jndiProperties.put("jboss.naming.client.ejb.context", true);
			
			//Context context = new InitialContext();
			//context va nous permettre de récuperer les sessionsbeans
			Context context = new InitialContext(jndiProperties);
			
			GestionClientsRemote gestionClientsRemote = (GestionClientsRemote)context.lookup("Banque/BanqueBeans/GestionClients!banque.beans.GestionClientsRemote");
			GestionComptesRemote gestionComptesRemote = (GestionComptesRemote)context.lookup("Banque/BanqueBeans/GestionComptes!banque.beans.GestionComptesRemote");
			GestionOperationsRemote gestionOperationsRemote = (GestionOperationsRemote)context.lookup("Banque/BanqueBeans/GestionOperations!banque.beans.GestionOperationsRemote");
			GestionCompteStandardRemote gestionComptesStandardRemote=(GestionCompteStandardRemote)context.lookup("Banque/BanqueBeans/GestionCompteStandard!banque.beans.GestionCompteStandardRemote");
			GestionBanqueRemote gestionBanqueRemote=(GestionBanqueRemote)context.lookup("Banque/BanqueBeans/GestionBanque!banque.beans.GestionBanqueRemote");
			GestionConseillerRemote gestionConseillersRemote=(GestionConseillerRemote)context.lookup("Banque/BanqueBeans/GestionConseiller!banque.beans.GestionConseillerRemote");
			
			Banque banque=new Banque();
			banque.setNom("LCL");
			banque=gestionBanqueRemote.ajouterBanque(banque);//ajout à la BDD
			
			Conseiller conseiller=new Conseiller();
			conseiller.setBanque(banque);
			conseiller.setNom("Jacque");
			conseiller.setMdp("Jacque");
			conseiller=gestionConseillersRemote.ajouterConseiller(conseiller);
			
			Client client1 = new Client();
			client1.setLogin("Morgane");
			client1.setPassword("morgane");
			client1.setNom("PICARISI");
			client1.setPrenom("Morgane");
			client1.setBanque(banque);
			client1.setConseiller(conseiller);
			
			client1 = gestionClientsRemote.ajouterClient(client1);
			
			;
			Client client2 = new Client();
			client2.setLogin("Zlatan");
			client2.setPassword("zlatan");
			client2.setNom("Zlatan");
			client2.setPrenom("Ibrahimovic");
			client2.setConseiller(conseiller);
			
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
			gestionComptesRemote.choisirClient(client1);
			compteA = (CompteStandard) gestionComptesRemote.ajouterCompte(compteA);
			compteB = (ComptePlatine) gestionComptesRemote.ajouterCompte(compteB);
			}
			catch( ClientNullException e){
				e.printStackTrace();}
			
			try {
			gestionComptesRemote.choisirClient(client2);
			compteC = (CompteEpargne) gestionComptesRemote.ajouterCompte(compteC);
			}
			catch( ClientNullException e){
				e.printStackTrace();}
			
			//operation sur un comtpe standard
			gestionComptesStandardRemote.effectuerRetrait(compteA, 10);
			gestionComptesRemote.effectuerDepot(compteA, 200);
			
//			banque.entites.Operation op1 = new banque.entites.Operation();
//			op1.setRetrait(false);
//			op1.setMontant(300);
//			
//			banque.entites.Operation op2 = new banque.entites.Operation();
//			op2.setRetrait(true);
//			op2.setMontant(50.2);
//
////			try {
//			gestionOperationsRemote.choisirCompte(compteA);
//			op1 = gestionOperationsRemote.ajouterOperation(op1);
//			op2 = gestionOperationsRemote.ajouterOperation(op2);
//			}
//			catch( CompteNullException e){e.printStackTrace();}
			
			
			System.out.println("Done !");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}