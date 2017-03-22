package client;

import java.util.Observable;
import java.util.Observer;

import fr.ensta.fx.boatmonitoring.FXHomeUI;
import fr.ensta.fx.boatmonitoring.user.BoatMonitorTab;
import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.stage.Stage;



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
			   		this.setAccountName(accountName);
			   		
		    		rep = client.myClient.stringTransmitOnly(password);
		    		
			    	if(rep.equals("OK"))
			    	{
			   			this.setLoginStatus(true);
			   			
			  			final BoatMonitorTab boatMonitor = this.createAndAddMonitor();
		                boatMonitor.setBoatName(thread.getDataModel().getBoatName());
		                this.setAccountName(client.myClient.stringTransmitOnly("demandeNom"));
		                thread.getDataModel().addObserver(this);
		                
		                this.addLogList(client.myClient.stringTransmitOnly("demandeAdresse"));
		                this.addLogList(client.myClient.stringTransmitOnly("demandeTel"));
		                this.addLogList(client.myClient.stringTransmitOnly("demandeEmail"));
		                this.addLogList(client.myClient.stringTransmitOnly("demandeDate"));
		                this.addLogList(client.myClient.stringTransmitOnly("demandeAmis"));
		                
		                
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
        try
        {
        	this.client.myClient.disconnectFromServer();
        	this.cleanLogList();
        }
        catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
    
    @Override
    public void update( Observable observable , Object o ) {
    		for (Tab  aTab : this.getTabs() ) {
				if (aTab instanceof BoatMonitorTab )  
					Platform.runLater(
							() -> {
								((BoatMonitorTab) aTab).setBoatName("Test");
							}
					);
			}
    }
    
    
    
    @Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setOnCloseRequest(event -> {cleanClose();});
		super.start(primaryStage);
	}
    
    public void cleanClose()
    {
    	try
    	{
    		client.myClient.disconnectFromServer();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

	public static void main( String[] args ) {
    	
    	launch(args);
    	
    }

}