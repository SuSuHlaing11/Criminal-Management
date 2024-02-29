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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddOperatorController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public void initialize() {
    }

    @FXML
    private TextField cpwText1;

    @FXML
    private TextField emailText1;

    @FXML
    private TextField nameText1;

    @FXML
    private TextField pwText1;

    @FXML
    private TextField userText1;

    @FXML
    private Label warning;

    @FXML
    private Label success;

    @FXML
    private Label invalidUser;

    @Override
    public void initialize(URL url, ResourceBundle resource) {

    }

    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @FXML
    void addData1(MouseEvent event) {
        String nameTem = nameText1.getText();
        String userTem = userText1.getText();
        String emailTem = emailText1.getText();
        String pwTem = pwText1.getText();
        String cpwTem = cpwText1.getText();
        String usernameflag = "";

        Database connectNow = new Database();
        Connection connectDB = connectNow.getDBConnection();

        String query = "SELECT * FROM operator WHERE Username = ?";
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

        if (usernameflag == "false") {
            invalidUser.setText("Username already exists.");
            warning.setText("");

        } else {
            if (pwTem.equals(cpwTem)) {

                String hashedPassword = hashPassword(pwTem);

                String insertQuery = "INSERT INTO operator (Name, Username, Password, Email_Address) VALUES (?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connectDB.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, nameTem);
                    preparedStatement.setString(2, userTem);
                    preparedStatement.setString(3, hashedPassword);
                    preparedStatement.setString(4, emailTem);

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
                warning.setText("");
                invalidUser.setText("");

            } else {
                warning.setText("Password doesn't match.");
                invalidUser.setText("");
            }

        }
    }

}
