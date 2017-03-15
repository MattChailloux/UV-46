package client;

import java.util.Observable;
import java.util.Observer;

import fr.ensta.fx.boatmonitoring.FXHomeUI;
import fr.ensta.fx.boatmonitoring.user.BoatMonitorTab;
import javafx.application.Platform;
import javafx.scene.control.Tab;

import Info.Client;


public class ClientUI extends FXHomeUI implements Observer {

    /** We will need access to our dataModel later, so we need it as a field of this class */
//    private TotoHomeModel dataModel;

	private Instance thread;
	private MainClient client;

    public ClientUI() {
        // Do not forget to call the constructor from its super class
        super();
        client = new MainClient();
        // Thread initialization with Data model 
        thread = new Instance(this);
        thread.start();
    }

    /**
     * Overrides the login method and makes it functional,
     * testing the provided parameter correctness and updating the UI as necessary.
     *
     * @see fr.ensta.fx.boatmonitoring.user.FXUserUI#login FXUserUI.login
     */
    @Override
    public void login(String accountName, String password) {
    	String rep = "";
    	
    	try
    	{
    		if(this.client.myClient.connectToServer() == true)
    		{
	    		rep = client.myClient.stringTransmitOnly(accountName);
			   	if(rep.equals("OK"))
			   	{
			   		thread.getDataModel().setAccountName(accountName);
			   		
		    		rep = client.myClient.stringTransmitOnly(password);
		    		
			    	if(rep.equals("OK"))
			    	{
			   			this.setLoginStatus(true);
			   			
			  			final BoatMonitorTab boatMonitor = this.createAndAddMonitor();
		                boatMonitor.setBoatName(thread.getDataModel().getBoatName());
		                thread.getDataModel().addObserver(this);
		            }
			    	else
			   		{
			   			this.displayWarning("Login", "Password incorrect");
			   			client.myClient.disconnectFromServer();
			   		}
			   	}
		    	else
		    	{
			   		this.displayWarning("Login", "The login " + accountName + " does not exist.");
			   		client.myClient.disconnectFromServer();
			   	}
    		}
    	}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }

    /** Similar to {@link #login}. */
    @Override
    public void logout() {
        this.removeAllMonitors();
        this.setLoginStatus(false);
        this.setAccountName(null);
    }
    
    @Override
    public void update( Observable observable , Object o ) {
    		for (Tab  aTab : this.getTabs() ) {
				if (aTab instanceof BoatMonitorTab )  
					Platform.runLater(
							() -> {
								((BoatMonitorTab) aTab).setBoatName( ((Client) observable).getBoatName() );
							}
					);
			}
    }
    
    public static void main( String[] args ) {
    	
    	launch(args);
    	
    }

}