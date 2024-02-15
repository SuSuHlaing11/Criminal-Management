// Must have the application package
// Add vmarg to launch.json for java runtime {name: admin} {type: java}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Add extends Application {

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(
      getClass().getClassLoader().getResource("Add.fxml")
    );
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);

    primaryStage.setTitle("Add Page");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
