import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class loginController {

  @FXML
  private Button loginbtn;

  @FXML
  private Label loginMsg;

  @FXML
  private PasswordField passwordtext;

  @FXML
  private TextField usernametext;

  @FXML
  void loginbtnAction(ActionEvent event) {
    String username = usernametext.getText();
    String password = passwordtext.getText();

    // Connect to the database
    try (
        Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/crime_manage",
            "root",
            "")) {
      String query = "SELECT * FROM users WHERE Username = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        String hashedPassword = resultSet.getString("Password");
        String userType = resultSet.getString("Type");

        if (BCrypt.checkpw(password, hashedPassword)) {
          if (userType.equals("Admin")) {
            loadAdminScene();
          } else if (userType.equals("Operator")) {
            loadOperatorScene();
          } else {
            showAlert(
                AlertType.ERROR,
                "Invalid User Type",
                "User type is not recognized.");
          }
        } else {
          showAlert(
              AlertType.ERROR,
              "Invalid Password",
              "The password is incorrect.");
        }
      } else {
        showAlert(AlertType.ERROR, "Invalid Username", "Username not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      showAlert(
          AlertType.ERROR,
          "Database Error",
          "Error connecting to the database.");
    }
  }

  private void loadAdminScene() {
    try {
      Parent adminRoot = FXMLLoader.load(getClass().getResource("Admin.fxml"));
      Stage stage = (Stage) loginbtn.getScene().getWindow();
      stage.setScene(new Scene(adminRoot));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadOperatorScene() {
    try {
      Parent operatorRoot = FXMLLoader.load(
          getClass().getResource("operator.fxml"));
      Stage stage = (Stage) loginbtn.getScene().getWindow();
      stage.setScene(new Scene(operatorRoot));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void showAlert(AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
  }

  @FXML
  void initialize() {
    assert loginbtn != null : "fx:id=\"loginbtn\" was not injected: check your FXML file 'login.fxml'.";
    assert loginMsg != null : "fx:id=\"loginMsg\" was not injected: check your FXML file 'login.fxml'.";
    assert passwordtext != null : "fx:id=\"passwordtext\" was not injected: check your FXML file 'login.fxml'.";
    assert usernametext != null : "fx:id=\"usernametext\" was not injected: check your FXML file 'login.fxml'.";
  }
}
