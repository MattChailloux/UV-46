package client;


import Info.Client;
import fr.ensta.fx.boatmonitoring.user.FXUserUI;

public class Instance extends Thread {
	
	private FXUserUI userInterface;
	private Client dataModel;
	
	public Instance(FXUserUI userInterface) {
        this.userInterface = userInterface;
        
        this.dataModel = new Client();
        
    }

	
	public FXUserUI getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(FXUserUI userInterface) {
		this.userInterface = userInterface;
	}

	public Client getDataModel() {
		return dataModel;
	}

	public void setDataModel(Client dataModel) {
		this.dataModel = dataModel;
	}


    @Override
    public void run() {
        super.run();
    }
}
