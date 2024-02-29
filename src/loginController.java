import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

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
  private Label loginMsg;

  @FXML
  private RadioButton operatorRadioButton;

  @FXML
  private PasswordField passwordPasswordField;

  @FXML
  private TextField usernameTextField;

  @FXML
  void loginbtnaction(ActionEvent event) {
    if (!usernameTextField.getText().isBlank() &&
        !passwordPasswordField.getText().isBlank()) {
      loginMsg.setText("Trying to login...");
      validateLogin();
    } else {
      loginMsg.setText("Please complete the form.");
    }
  }

  private void validateLogin() {
    Database connectNow = new Database();
    Connection connectDB = connectNow.getDBConnection();

    String username = usernameTextField.getText();
    String password = passwordPasswordField.getText();

    try {
      Statement statement = connectDB.createStatement();
      String query = "";
      if (AdministratorRadioButton.isSelected()) {
        query = "SELECT password FROM admin WHERE Username = '" +
            username +
            "'";
      } else if (operatorRadioButton.isSelected()) {
        query = "SELECT password FROM operator WHERE Username = '" +
            username +
            "'";
      }

      ResultSet queryOutput = statement.executeQuery(query);

      if (queryOutput.next()) {
        String hashedPassword = queryOutput.getString("password");
        if (BCrypt.checkpw(password, hashedPassword)) {
          loginMsg.setText("Valid login.");

          if (AdministratorRadioButton.isSelected()) {
            loadAdminScene();
          } else if (operatorRadioButton.isSelected()) {
            loadOperatorScene();
          }
        } else {
          loginMsg.setText("Invalid password.");
        }
      } else {
        loginMsg.setText("Invalid username.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void loadAdminScene() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
      Parent adminRoot = loader.load();

      Stage stage = (Stage) AdministratorRadioButton.getScene().getWindow();
      stage.setScene(new Scene(adminRoot));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void loadOperatorScene() {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("operator.fxml"));
      Parent operatorRoot = loader.load();

      Stage stage = (Stage) operatorRadioButton.getScene().getWindow();
      stage.setScene(new Scene(operatorRoot));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void initialize() {
    assert AdministratorRadioButton != null
        : "fx:id=\"AdministratorRadioButton\" was not injected: check your FXML file 'login1.fxml'.";
    assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login1.fxml'.";
    assert loginMsg != null : "fx:id=\"loginMsg\" was not injected: check your FXML file 'login1.fxml'.";
    assert operatorRadioButton != null
        : "fx:id=\"operatorRadioButton\" was not injected: check your FXML file 'login1.fxml'.";
    assert passwordPasswordField != null
        : "fx:id=\"passwordPasswordField\" was not injected: check your FXML file 'login1.fxml'.";
    assert usernameTextField != null
        : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'login1.fxml'.";
  }
}
