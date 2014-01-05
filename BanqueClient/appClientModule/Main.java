import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import banque.beans.ClientNullException;
import banque.beans.CompteNullException;
import banque.entites.Client;
import banque.entites.CompteEpargne;
import banque.entites.ComptePlatine;
import banque.entites.CompteStandard;
import banque.interfaceBeans.GestionClientsRemote;
import banque.interfaceBeans.GestionCompteStandardRemote;
import banque.interfaceBeans.GestionComptesRemote;
import banque.interfaceBeans.GestionOperationsRemote;


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
			Client client1 = new Client();
			client1.setLogin("Morgane");
			client1.setPassword("morgane");
			client1.setNom("PICARISI");
			client1.setPrenom("Morgane");
			client1 = gestionClientsRemote.ajouterClient(client1);
			
			Client client2 = new Client();
			client2.setLogin("Zlatan");
			client2.setPassword("zlatan");
			client2.setNom("Zlatan");
			client2.setPrenom("Ibrahimovic");
			client2 = gestionClientsRemote.ajouterClient(client2);
			
			
			
	
			CompteStandard compteA = new CompteStandard();
			compteA.setNumeroCompte("0000001");
			compteA.setPenalite(5);
			compteA.setSolde(1000);
			
			ComptePlatine compteB = new ComptePlatine();
			compteB.setNumeroCompte("0000002");
			compteB.setDecouvert(500);
			compteB.setSolde(1000);	
			
			CompteEpargne compteC = new CompteEpargne();
			compteC.setNumeroCompte("0000003");
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