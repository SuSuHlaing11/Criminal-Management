import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private BorderPane borderpane;

  @FXML
  private Button crimemanage;

  @FXML
  private Button operatormanage;

  @FXML
  void crimemanagebtn(ActionEvent event) throws IOException {
    AnchorPane view = FXMLLoader.load(
      getClass().getResource("AddCrimeForm.fxml")
    );
    borderpane.setCenter(view);
  }

  @FXML
  void operatormanagebtn(ActionEvent event) throws IOException {
    AnchorPane view = FXMLLoader.load(
      getClass().getResource("AddOperatorForm.fxml")
    );
    borderpane.setCenter(view);
  }

  @FXML
  void initialize() {
    assert borderpane !=
    null : "fx:id=\"borderpane\" was not injected: check your FXML file 'Main.fxml'.";
    assert crimemanage !=
    null : "fx:id=\"crimemanage\" was not injected: check your FXML file 'Main.fxml'.";
    assert operatormanage !=
    null : "fx:id=\"operatormanage\" was not injected: check your FXML file 'Main.fxml'.";
  }
}
