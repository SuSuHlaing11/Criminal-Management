import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class operatorController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public void initialize() {
    }

    @FXML
    private TextField genderText1;

    @FXML
    private TextField nameText1;

    @FXML
    private TextField addressText1;

    @FXML
    private TextField nrcText1;

    @FXML
    private TextField ageText1;

    @FXML
    private TextField cdateText1;

    @FXML
    private TextField searchField1;

    @FXML
    private TextField typeText1;

    @FXML
    private TextField csceneText1;

    @FXML
    private TableView<Model> criminalTable1;

    @FXML
    private TableColumn<Model, Integer> cage1;

    @FXML
    private TableColumn<Model, String> caddress1;

    @FXML
    private TableColumn<Model, String> cgender1;

    @FXML
    private TableColumn<Model, Integer> cid1;

    @FXML
    private TableColumn<Model, String> cname1;

    @FXML
    private TableColumn<Model, String> cnrc1;

    @FXML
    private TableColumn<Model, String> cscene1;

    @FXML
    private TableColumn<Model, String> ctype1;

    @FXML
    private TableColumn<Model, LocalDate> cdate1;

    @FXML
    private ImageView crimeImage1;

    @FXML
    private MenuButton filterBtn1;

    @FXML
    private Button logout1;

    @FXML
    void logoutScene(MouseEvent event) throws IOException {
        Stage stage = (Stage) logout1.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void searchAll1(ActionEvent event) {
        filterBtn1.setText("All");
        filterValue.fValue = "all";
        searchField1.setText("");
    }

    @FXML
    void searchID1(ActionEvent event) {
        filterBtn1.setText("ID");
        filterValue.fValue = "id";
        searchField1.setText("");
    }

    @FXML
    void searchAddress1(ActionEvent event) {
        filterBtn1.setText("Address");
        filterValue.fValue = "address";
        searchField1.setText("");
    }

    @FXML
    void searchCscene1(ActionEvent event) {
        filterBtn1.setText("Crime Scene");
        filterValue.fValue = "cscene";
        searchField1.setText("");
    }

    @FXML
    void searchNRC1(ActionEvent event) {
        filterBtn1.setText("NRC");
        filterValue.fValue = "nrc";
        searchField1.setText("");
    }

    @FXML
    void searchName1(ActionEvent event) {
        filterBtn1.setText("Name");
        filterValue.fValue = "name";
        searchField1.setText("");
    }

    @FXML
    void searchType1(ActionEvent event) {
        filterBtn1.setText("Type");
        filterValue.fValue = "type";
        searchField1.setText("");
    }

    @FXML
    void searchAge1(ActionEvent event) {
        filterBtn1.setText("Age");
        filterValue.fValue = "age";
        searchField1.setText("");
    }

    @FXML
    void searchGender1(ActionEvent event) {
        filterBtn1.setText("Gender");
        filterValue.fValue = "gender";
        searchField1.setText("");
    }

    @FXML
    void searchCdate1(ActionEvent event) {
        filterBtn1.setText("Date");
        filterValue.fValue = "date";
        searchField1.setText("");
    }

    public class filterValue {
        public static String fValue;
    }

    ObservableList<Model> ModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        filterValue.fValue = "all";

        ModelObservableList = FXCollections.observableArrayList(); // Initialize the ObservableList

        Database connectNow = new Database();
        Connection connectDB = connectNow.getDBConnection();

        String crimeViewQuery = "Select ID, Name, Age, Type, Address, Date, Crime_Scene, Gender, NRC FROM criminals";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(crimeViewQuery);

            while (queryOutput.next()) {

                Integer queryID = queryOutput.getInt("ID");
                String queryName = queryOutput.getString("Name");
                Integer queryAge = queryOutput.getInt("Age");
                String queryType = queryOutput.getString("Type");
                String queryAddress = queryOutput.getString("Address");
                String queryCrime_scene = queryOutput.getString("Crime_scene");
                String queryGender = queryOutput.getString("Gender");
                String queryNRC = queryOutput.getString("NRC");

                java.sql.Date sqlDate = queryOutput.getDate("Date");
                LocalDate queryDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                ModelObservableList
                        .add(new Model(queryID, queryName, queryAge, queryType, queryAddress, queryDate,
                                queryCrime_scene,
                                queryGender, queryNRC));
            }

            cid1.setCellValueFactory(new PropertyValueFactory<>("ID"));
            cname1.setCellValueFactory(new PropertyValueFactory<>("Name"));
            cage1.setCellValueFactory(new PropertyValueFactory<>("Age"));
            ctype1.setCellValueFactory(new PropertyValueFactory<>("Type"));
            caddress1.setCellValueFactory(new PropertyValueFactory<>("Address"));
            cdate1.setCellValueFactory(new PropertyValueFactory<>("Date"));
            cscene1.setCellValueFactory(new PropertyValueFactory<>("Crime_scene"));
            cgender1.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            cnrc1.setCellValueFactory(new PropertyValueFactory<>("NRC"));

            cname1.setCellFactory(column -> new WrappingTextTableCell<>());
            ctype1.setCellFactory(column -> new WrappingTextTableCell<>());
            cscene1.setCellFactory(column -> new WrappingTextTableCell<>());
            caddress1.setCellFactory(column -> new WrappingTextTableCell<>());
            cnrc1.setCellFactory(column -> new WrappingTextTableCell<>());
            cgender1.setCellFactory(column -> new WrappingTextTableCell<>());

            criminalTable1.setItems(ModelObservableList);

            FilteredList<Model> filteredData = new FilteredList<>(ModelObservableList, b -> true);
            searchField1.textProperty().addListener((observable, oldValue, newValue) -> {

                filteredData.setPredicate(Model -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (filterValue.fValue == "all") {
                        if (Model.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (String.valueOf(Model.getAge()).toLowerCase().contains(searchKeyword.toLowerCase())) {
                            return true;
                        } else if (Model.getType().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (Model.getDate().toString().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (Model.getCrime_scene().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (Model.getAddress().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (Model.getID().toString().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (Model.getNRC().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else if (Model.getGender().toLowerCase().indexOf(searchKeyword) > -1) {
                            return true;
                        } else
                            return false;
                    }

                    if (filterValue.fValue == "name" && Model.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "age" && Model.getAge().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "type"
                            && Model.getType().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "date" && Model.getDate().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "cscene"
                            && Model.getCrime_scene().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "address"
                            && Model.getAddress().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "id" && Model.getID().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "nrc"
                            && Model.getNRC().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (filterValue.fValue == "gender"
                            && Model.getGender().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;

                });
            });

            SortedList<Model> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(criminalTable1.comparatorProperty());
            criminalTable1.setItems(sortedData);

        }

        catch (SQLException e) {

            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    @FXML
    void displayInfo1(MouseEvent event) {
        Model selectedModel = criminalTable1.getSelectionModel().getSelectedItem();

        if (selectedModel == null) {
            nameText1.setText(" ");
        } else {
            Integer id = selectedModel.getID();
            String name = selectedModel.getName();
            Integer age = selectedModel.getAge();
            String type = selectedModel.getType();
            String address = selectedModel.getAddress();
            LocalDate date = selectedModel.getDate();
            String crimeScene = selectedModel.getCrime_scene();
            String gender = selectedModel.getGender();
            String nrc = selectedModel.getNRC();

            nameText1.setText(name);
            ageText1.setText(Integer.toString(age));
            typeText1.setText(type);
            addressText1.setText(address);
            cdateText1.setText(date.toString());
            csceneText1.setText(crimeScene);
            genderText1.setText(gender);
            nrcText1.setText(nrc);

            File file = new File("src\\image\\" + id + ".jpg");
            Image crimeImg = new Image(file.toURI().toString());
            crimeImage1.setImage(crimeImg);
        }
    }

}
