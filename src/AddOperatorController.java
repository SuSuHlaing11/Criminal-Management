import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddOperatorController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public void initialize() {
    }

    @FXML
    private TextField nameText1;

    @FXML
    private TextField userText1;

    @FXML
    private PasswordField cpwText1;

    @FXML
    private TextField emailText1;

    @FXML
    private PasswordField pwText1;

    @FXML
    private ImageView operatorID;

    @FXML
    private Label invalidPw;

    @FXML
    private Label invalidPw1;

    @FXML
    private Label success;

    @FXML
    private Label invalidUser;

    public class userImg {
        public static String img;
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {

    }

    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @FXML
    void uploadID(MouseEvent event) {

        userImg.img = userText1.getText();

        if (userImg.img.isEmpty()) {

            invalidUser.setText("Please enter username first.");

        } else {

            invalidUser.setText("");

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.jpg"));

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                File renamedFile = new File(selectedFile.getParent(), userImg.img + ".jpg");

                File selectedFolder = new File("src\\image");
                selectedFolder.mkdirs();

                File newLocation = new File(selectedFolder, renamedFile.getName());
                selectedFile.renameTo(newLocation);

                Image image = new Image(newLocation.toURI().toString());
                operatorID.setImage(image);
            }
        }
    }

    @FXML
    void addData1(MouseEvent event) {
        String nameTem = nameText1.getText();
        String userTem = userText1.getText();
        String emailTem = emailText1.getText();
        String typeTem = "Operator";
        String pwTem = pwText1.getText();
        String cpwTem = cpwText1.getText();
        String usernameflag = "true";

        Database connectNow = new Database();
        Connection connectDB = connectNow.getDBConnection();

        String query = "SELECT * FROM users WHERE Username = ?";
        try (PreparedStatement preparedStatement1 = connectDB.prepareStatement(query)) {
            preparedStatement1.setString(1, userTem);

            try (ResultSet resultSet = preparedStatement1.executeQuery()) {
                if (resultSet.next()) {
                    usernameflag = "false";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (usernameflag == "true") {

            if (!pwTem.isEmpty()) {

                if (pwTem.equals(cpwTem)) {

                    String hashedPassword = hashPassword(pwTem);

                    String insertQuery = "INSERT INTO users (Name, Username, Password, Email_Address, Type) VALUES (?, ?, ?, ?, ?)";

                    try (PreparedStatement preparedStatement = connectDB.prepareStatement(insertQuery)) {
                        preparedStatement.setString(1, nameTem);
                        preparedStatement.setString(2, userTem);
                        preparedStatement.setString(3, hashedPassword);
                        preparedStatement.setString(4, emailTem);
                        preparedStatement.setString(5, typeTem);

                        int affectedRows = preparedStatement.executeUpdate();

                        if (affectedRows > 0) {
                            System.out.println("Data inserted successfully!");
                        } else {
                            System.out.println("Failed to insert data.");
                        }

                    } catch (SQLException e) {
                        Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
                        e.printStackTrace();
                    }

                    try {
                        // Sleep for 2 seconds (2000 milliseconds)
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    success.setText("Registration successful.");

                    nameText1.setText("");
                    userText1.setText("");
                    emailText1.setText("");
                    pwText1.setText("");
                    cpwText1.setText("");
                    invalidPw.setText("");
                    invalidUser.setText("");

                    Image defaultImage = new Image(getClass().getResourceAsStream("/image/DefultID.jpg"));
                    operatorID.setImage(defaultImage);

                } else {
                    invalidPw.setText("Password doesn't match.");
                    invalidUser.setText("");
                    invalidPw1.setText("");
                }
            } else {
                invalidPw1.setText("Please enter password.");
                invalidUser.setText("");
                invalidPw.setText("");
            }

        } else {
            invalidUser.setText("Username already exists.");
            invalidPw.setText("");
            invalidPw1.setText("");
        }
    }

}
