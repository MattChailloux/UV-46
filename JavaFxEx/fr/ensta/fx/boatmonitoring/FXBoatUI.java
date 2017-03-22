package fr.ensta.fx.boatmonitoring;

import fr.ensta.fx.boatmonitoring.generic.FXGenericUI;
import fr.ensta.fx.boatmonitoring.user.BoatMonitorTab;
import fr.ensta.fx.boatmonitoring.user.FXUserUI;
import javafx.scene.Scene;
import javafx.stage.Stage;


/** <p>The HMI to be used aboard.</p>
 *
 * <p>The boat monitor can be retrieved via the
 * {@link #getBoatMonitor()} method.</p>
 *
 * @see fr.ensta.fx.boatmonitoring
 * @see FXUserUI
 * @see FXGenericUI
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class FXBoatUI extends FXUserUI {

    private BoatMonitorTab boatMonitor;

    /**
     * @return the {@link fr.ensta.fx.boatmonitoring.user.BoatMonitorTab monitor} of the boat providing this UI
     */
    public BoatMonitorTab getBoatMonitor() {
        return boatMonitor;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);

        boatMonitor = new BoatMonitorTab(this);

        getTabs().add(boatMonitor);

        final Scene scene = new Scene(getRootPane(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Boat Monitoring: Boat UI");
        primaryStage.show();
    }

    @Override
    public void login(String accountName, String password) {
        this.displayWarning("Login", "Unsupported operation");
    }

    @Override
    public void logout() {
        this.displayWarning("Logout", "Unsupported operation");
    }

    @Override
    public void createAccount(String accountName, String password) {
        this.displayWarning("Create Account", "Unsupported operation");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        this.displayWarning("Change Password", "Unsupported operation");
    }

    @Override
    public void toggleMonitor(BoatMonitorTab boatMonitor, String password) {
        this.displayWarning("Boat Monitor", "Unsupported operation");
    }
}
