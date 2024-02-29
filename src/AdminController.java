import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private BorderPane borderpane;

  @FXML
  private Button scene1;

  @FXML
  private Button scene2;

  @FXML
  private Button scene3;

  @FXML
  private Button scene31;

  @FXML
  private Button logout;

  @FXML
  void btnscene1(ActionEvent event) throws IOException {
    AnchorPane view = FXMLLoader.load(getClass().getResource("AddCrime.fxml"));
    borderpane.setCenter(view);
  }

  @FXML
  void btnscene2(ActionEvent event) throws IOException {
    AnchorPane view = FXMLLoader.load(getClass().getResource("ManageCrime.fxml"));
    borderpane.setCenter(view);
  }

  @FXML
  void btnscene3(ActionEvent event) throws IOException {
    AnchorPane view = FXMLLoader.load(getClass().getResource("AddOperator.fxml"));
    borderpane.setCenter(view);
  }

  @FXML
  void btnscene4(ActionEvent event) throws IOException {
    AnchorPane view = FXMLLoader.load(getClass().getResource("ManageOperator.fxml"));
    borderpane.setCenter(view);
  }

  @FXML
  void loginscene(MouseEvent event) throws IOException {
    Stage stage = (Stage) logout.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  @FXML
  void initialize() {
    assert borderpane != null : "fx:id=\"borderpane\" was not injected: check your FXML file 'Main.fxml'.";
    assert scene1 != null : "fx:id=\"scene1\" was not injected: check your FXML file 'Main.fxml'.";
    assert scene2 != null : "fx:id=\"scene2\" was not injected: check your FXML file 'Main.fxml'.";
    assert scene3 != null : "fx:id=\"scene3\" was not injected: check your FXML file 'Main.fxml'.";
    assert scene31 != null : "fx:id=\"scene31\" was not injected: check your FXML file 'Main.fxml'.";
  }
}
