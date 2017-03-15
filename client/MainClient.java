package client;

import java.util.*;

public class MainClient {
	
	String myS;
	Scanner aSC;
	TCPClient myClient;
	
	public MainClient()
	{
		this.myS = null;
		this.aSC = new Scanner(System.in);
		this.myClient = new TCPClient("localhost", 6666);
	}

	public static void main(String[] args) {
		
		MainClient MainC = new MainClient();
		String rep = new String("");
		Boolean continuer = true;

		try {
			if (MainC.myClient.connectToServer() == true) {

				System.out.print("Identifiant:  ");
				MainC.myS = MainC.aSC.nextLine();
				rep = MainC.myClient.stringTransmitOnly(MainC.myS);
				
				if(rep.equals(new String("OK")))
				{
					System.out.print("Password:  ");
					MainC.myS = MainC.aSC.nextLine();
					rep = MainC.myClient.stringTransmitOnly(MainC.myS);
					
					if(!rep.equals(new String("OK")))
						continuer = false;
				}
				else
				{
					continuer = false;
				}
				
				

				//aSC.close();
				
				while(continuer)
				{
					System.out.print("Commande:  ");
					MainC.myS = MainC.aSC.nextLine();
					rep = MainC.myClient.stringTransmitOnly(MainC.myS);
					if(rep.equals(new String("quit")))
						break;
					else
						System.out.println("Answer: "+ rep);
				}
				
				MainC.aSC.close();

				MainC.myClient.disconnectFromServer();
			} else {
				System.err.println("Connexion echouee");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
