import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class operator extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("operator.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Crime Record System(Operator)");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
