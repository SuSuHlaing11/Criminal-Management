import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.TextField;

public class AddCrimeController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public void initialize() {
    }

    @FXML
    private Label registerSuccess;

    @FXML
    private TextField address;

    @FXML
    private TextField cscene;

    @FXML
    private TextField name;

    @FXML
    private TextField type;

    @FXML
    private TextField nrc;

    @FXML
    private ImageView crimeImage1;

    @FXML
    private ChoiceBox<String> genderText1;

    private String[] gender = { "Male", "Female" };

    public class imgId {
        public static Integer imgid;
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        genderText1.getItems().addAll(gender);

        imgId.imgid = getLatestIdFromDatabase();
    }

    private int getLatestIdFromDatabase() {
        Database connectNow = new Database();
        Connection connectDB = connectNow.getDBConnection();

        String query = "SELECT MAX(ID) FROM criminals";
        try (PreparedStatement preparedStatement = connectDB.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @FXML
    void importImg1(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            File renamedFile = new File(selectedFile.getParent(), imgId.imgid + ".jpg");

            File selectedFolder = new File("src\\image");
            selectedFolder.mkdirs();

            File newLocation = new File(selectedFolder, renamedFile.getName());
            selectedFile.renameTo(newLocation);

            Image image = new Image(newLocation.toURI().toString());
            crimeImage1.setImage(image);
        }
    }

    @FXML
    void addData(MouseEvent event) {
        String nameTem = name.getText();
        String typeTem = type.getText();
        String csceneTem = cscene.getText();
        String addressTem = address.getText();
        String nrcTem = nrc.getText();
        String genderTem = genderText1.getValue();

        Database connectNow = new Database();
        Connection connectDB = connectNow.getDBConnection();

        String insertQuery = "INSERT INTO criminals (Name, Type, Address, Crime_Scene, Gender, NRC) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connectDB.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nameTem);
            preparedStatement.setString(2, typeTem);
            preparedStatement.setString(3, addressTem);
            preparedStatement.setString(4, csceneTem);
            preparedStatement.setString(5, genderTem);
            preparedStatement.setString(6, nrcTem);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }

            name.setText("");
            type.setText("");
            address.setText("");
            cscene.setText("");
            nrc.setText("");
            genderText1.setValue("");

            registerSuccess.setText("Registration successful.");

        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

}
