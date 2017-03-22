package server;

import java.io.*;
import java.net.*;
import Info.*;


public class ConnectedClientThread extends Thread {

	private Socket clientSocket;
	private TCPServer myServer;
	int etat = 0;
	
	Client client;
	BufferedReader br;
	
	BufferedReader  is;
	PrintStream os;
	
	
	public ConnectedClientThread( Socket aClientSocket , TCPServer aServer ) {
		clientSocket = aClientSocket;
		myServer = aServer;
		
	}
	
	public int getEtat() {
		return etat;
	}
	
	public void run() {
    	String inputReq,outputReq;
    	Boolean accept = false;

		try {
			/* Ouverture des objets de type Stream sur la socket du client réseau  */
			//BufferedReader  is = new BufferedReader ( new InputStreamReader (clientSocket.getInputStream()));
			//PrintStream os = new PrintStream(clientSocket.getOutputStream());
			
			/* Ouverture des objets de type Stream sur la socket du client réseau  */
			this.is = new BufferedReader ( new InputStreamReader (clientSocket.getInputStream()));
			this.os = new PrintStream(clientSocket.getOutputStream());
			
			System.out.println( "Client Thread " );
			
			String Id = new String("");
			String line = null;
			String pass = new String("");
			
			FileReader fichier = null;
			
			try {	
				  Id = this.is.readLine();
					
			      // ouverture du fichier
				  String path = new File("").getAbsolutePath();
			      fichier = new FileReader (path+"\\fichiers\\"+Id+".txt");
			      
			      br = new BufferedReader(fichier);
			      
			      this.os.println("OK");
			      this.os.flush();
			      
			      line = br.readLine();
			      
			      pass = this.is.readLine();
			      
			      if(line.equals(pass))
			      {
			    	  this.os.println("OK");
			    	  this.os.flush();
			    	  accept = true;
			      }
			      else
			      {
			    	  this.os.println("FAIL");
			    	  this.os.flush();
			      }
			      
			      
				}
			catch (IOException e)
			{
				e.printStackTrace();
				this.os.println("FAIL");
		    	this.os.flush();
			}
			
			client = new Client();
			
			if(accept)
			{
				try 
				{
					client.abonne.setPatronyme(br.readLine());
					client.abonne.setAdresse(br.readLine());
					
					line = br.readLine();
					client.abonne.setEmail(line.split(","));
					client.abonne.setTelephone(br.readLine());
					client.abonne.setDate(br.readLine());
					line = br.readLine();
					client.abonne.setAmis(line.split(";"));
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				while ( (inputReq = this.is.readLine()) != null && etat != 3 ) 
				{
					System.out.println( " Msg 2 Recu " + inputReq );
					String chaines[] = inputReq.split( " " );
					
					
					/*
					Traitement des demandes
					*/
					if ( chaines[0].equals("demandeNom") ) { 
						line = client.abonne.getPatronyme();
						this.os.println(line);
						
					} else if ( chaines[0].equals("setNom") ) { 
						client.abonne.setPatronyme(chaines[1]);
						this.os.println(chaines[1]);
						
						
						
					} else if ( chaines[0].equals("demandeAdresse") ) {
						line = client.abonne.getAdresse();
						this.os.println(" Mon adresse est : " + line);
						
					} else if ( chaines[0].equals("setAdresse") ) {
						client.abonne.setAdresse(chaines[1]);
						this.os.println(" Mon adresse est maintenant : " + chaines[1]);
						
						
						
					} else if ( chaines[0].equals("demandeTel") ) { 
						line = client.abonne.getTelephone();
						this.os.println(" Mon numero de telephone est : " + line);
						
					} else if ( chaines[0].equals("setTel") ) { 
						client.abonne.setTelephone(chaines[1]);
						this.os.println(" Mon numero de telephone est maintenant : " + chaines[1]);
						
					} else if ( chaines[0].equals("demandeEmail") ) { 
						line = client.abonne.getEmail();
						this.os.println(" Mes adresses e-mails sont : " + line);
						
					} else if ( chaines[0].equals("setEmail") ) { 
						client.abonne.setEmail(chaines[1].split(","));
						this.os.println(" Les adresses e-mail sont : " + chaines[1]);
						
					} 
					
					else if ( chaines[0].equals("demandeDate") ) {
						line = client.abonne.getDate();
						this.os.println(" Ma date d'abonnement est : " + line);
						
					} else if ( chaines[0].equals("setDate") ) { 
						client.abonne.setDate(chaines[1]);
						this.os.println(" Ma date d'abonnement est maintenant : " + chaines[1]);
						
					} 
					else if ( chaines[0].equals("demandeAmis") ) {
						line = client.abonne.getAmis();
						this.os.println(" La liste de mes personnes de confiance est : " + line);
						
					} else if ( chaines[0].equals("setAmis") ) {
						client.abonne.setAmis(chaines[1].split(";"));
						this.os.println(" Vous venez de définir : " + chaines[1] + " comme personnes de confiance");
						
					}
					/*
					else if ( chaines[0].equals("demandeIdBateau") ) { 
						private String Id;
						Id = client.bateau().demandeIdBateau();
						System.out.println(" L'identifiant de mon bateau est : " + Id);
						
					} else if ( chaines[0].equals("demandeModele") ) { 
						private String Modele;
						Modele = client.bateau().demandeModele();
						System.out.println(" Mon bateau est un : " + Modele);
						
					} else if ( chaines[0].equals("demandeStationnement") ) { 
						private String Stationnement;
						Stationnement = client.bateau().demandeStationnement();
						System.out.println(" Mon bateau est ammaré à : " + Stationnement);
				
					} else if ( chaines[0].equals("demandePosition") ) { 
						private String Position;
						if (client.bateau().getEtat().equals("monitoring")) {
							Position = client.bateau().demandePosition();
							System.out.println(" Mon bateau est actuellement situé : " + Position);
						}
						else if (client.bateau().getEtat().equals("stolen")) {
							Position = client.bateau().demandePosition();
							System.out.println(" Mon bateau est actuellement situé : " + Position);
						}
						else if (client.bateau().getEtat().equals("no_monitoring")) {
							System.out.println(" Mon bateau n'est actuellement pas surveillé");
						}
					} else if ( chaines[0].equals("demandeEtat") ) {  
						private String Etat;
						Etat = client.bateau().getEtat();
						System.out.println(" Mon bateau est actuellement : " + Etat);
							
					} 
					*/
					else if ( chaines[0].equals("quit") ) {
//						etat = 3;
						this.os.println("quit");// + etat );
					}
					else
					{
						this.os.println("Commande non reconnue...");
					}
				}
			}
			
			clientSocket.close();
			this.os.close();
			this.is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

    	
    }
}
