import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class loginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton AdministratorRadioButton;

    @FXML
    private Button loginButton;

    @FXML
    private RadioButton operatorRadioButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void initialize() {
        assert AdministratorRadioButton != null : "fx:id=\"AdministratorRadioButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert operatorRadioButton != null : "fx:id=\"operatorRadioButton\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordPasswordField != null : "fx:id=\"passwordPasswordField\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'login.fxml'.";

    }

}
