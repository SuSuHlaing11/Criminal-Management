import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

public class ManageOperatorController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public void initialize() {
    }

    @FXML
    private Label no;

    @FXML
    private Label error;

    @FXML
    private TableView<Model1> operatorTable;

    @FXML
    private TableColumn<Model1, String> cemail2;

    @FXML
    private TableColumn<Model1, String> cname2;

    @FXML
    private TableColumn<Model1, String> cuser2;

    @FXML
    private TextField pwText2;

    @FXML
    private TextField cpwText2;

    @FXML
    private TextField nameText2;

    @FXML
    private TextField userText2;

    @FXML
    private TextField emailText2;

    @FXML
    private TextField searchField2;

    @FXML
    private MenuButton filterBtn2;

    @FXML
    void searchAll2(ActionEvent event) {
        filterBtn2.setText("All");
        filterValue.fValue = "all";
        searchField2.setText("");
    }

    @FXML
    void searchName2(ActionEvent event) {
        filterBtn2.setText("Name");
        filterValue.fValue = "name";
        searchField2.setText("");
    }

    @FXML
    void searchUser2(ActionEvent event) {
        filterBtn2.setText("Username");
        filterValue.fValue = "username";
        searchField2.setText("");
    }

    @FXML
    void searchEmail2(ActionEvent event) {
        filterBtn2.setText("Email");
        filterValue.fValue = "email";
        searchField2.setText("");
    }

    public class filterValue {
        public static String fValue;
    }

    @FXML
    void displayInfo2(MouseEvent event) {
        Model1 Name = operatorTable.getSelectionModel().getSelectedItem();

        if (Name == null) {
            nameText2.setText(" ");
        } else {
            String name = Name.getName();
            String username = Name.getUsername();
            String email = Name.getEmail();
            nameText2.setText(name);
            userText2.setText(username);
            emailText2.setText(email);
        }
    }

    ObservableList<Model1> ModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        cname2.setCellFactory(column -> new WrappingTextTableCell<>());
        cuser2.setCellFactory(column -> new WrappingTextTableCell<>());
        cemail2.setCellFactory(column -> new WrappingTextTableCell<>());

        filterValue.fValue = "all";
        ModelObservableList = FXCollections.observableArrayList(); // Initialize the ObservableList

        Database connectNow = new Database();
        Connection connectDB = connectNow.getDBConnection();

        String crimeViewQuery = "Select Name, Username, Email_Address FROM operator";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(crimeViewQuery);

            while (queryOutput.next()) {

                String queryName = queryOutput.getString("Name");
                String queryUsername = queryOutput.getString("Username");
                String queryEmail = queryOutput.getString("Email_Address");
                String queryPw = queryOutput.getString("Email_Address");

                ModelObservableList.add(new Model1(queryName, queryUsername, queryEmail, queryPw));
            }

            cname2.setCellValueFactory(new PropertyValueFactory<>("Name"));
            cuser2.setCellValueFactory(new PropertyValueFactory<>("Username"));
            cemail2.setCellValueFactory(new PropertyValueFactory<>("Email"));

            operatorTable.setItems(ModelObservableList);

            FilteredList<Model1> filteredData = new FilteredList<>(ModelObservableList, b -> true);
            searchField2.textProperty().addListener((observable, oldValue, newValue) -> {

                filteredData.setPredicate(Model1 -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (filterValue.fValue == "all") {
                        if (Model1.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (Model1.getUsername().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (Model1.getEmail().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else
                            return false;
                    }

                    if (filterValue.fValue == "name" && Model1.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "username"
                            && Model1.getUsername().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "email"
                            && Model1.getEmail().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;

                });
            });

            SortedList<Model1> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(operatorTable.comparatorProperty());
            operatorTable.setItems(sortedData);

        }

        catch (SQLException e) {

            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @FXML
    void updateInfo1(MouseEvent event) {

        Model1 selectedModel = operatorTable.getSelectionModel().getSelectedItem();

        if (selectedModel != null) {
            String updatedName = nameText2.getText();
            String updatedUser = userText2.getText();
            String updatedEmail = emailText2.getText();
            String updatedpw = pwText2.getText();
            String updatedcpw = cpwText2.getText();

            selectedModel.setName(updatedName);
            selectedModel.setUsername(updatedUser);
            selectedModel.setEmail(updatedEmail);
            selectedModel.setPw(updatedpw);

            Database connectNow = new Database();
            Connection connectDB = connectNow.getDBConnection();

            if (updatedpw.equals(updatedcpw)) {

                String hashedPassword = hashPassword(updatedpw);

                try {
                    // Sleep for 2 seconds (2000 milliseconds)
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                no.setText("Update successful.");

                String updateQuery = "UPDATE operator SET Name=?, Password=?, Email_Address=? WHERE Username=?";

                try (PreparedStatement preparedStatement = connectDB.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, updatedName);
                    preparedStatement.setString(2, hashedPassword);
                    preparedStatement.setString(3, updatedEmail);
                    preparedStatement.setString(4, updatedUser);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Update successful!");
                    } else {
                        System.out.println("Update failed!");
                    }

                } catch (SQLException e) {
                    Logger.getLogger(ManageCrimeController.class.getName()).log(Level.SEVERE, null, e);
                    e.printStackTrace();
                }
            } else {
                error.setText("Password doesn't match.");
            }
        }

    }

}
