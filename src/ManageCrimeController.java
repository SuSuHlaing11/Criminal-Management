import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.TextField;

public class ManageCrimeController implements Initializable {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  public void initialize() {
  }

  @FXML
  private TableView<Model> criminalTable;

  @FXML
  private TableColumn<Model, String> caddress;

  @FXML
  private TableColumn<Model, String> cgender;

  @FXML
  private TableColumn<Model, Integer> cid;

  @FXML
  private TableColumn<Model, String> cname;

  @FXML
  private TableColumn<Model, String> cnrc;

  @FXML
  private TableColumn<Model, String> cscene;

  @FXML
  private TableColumn<Model, String> ctype;

  @FXML
  private TextField searchField;

  @FXML
  private TextField nameText;

  @FXML
  private TextField typeText;

  @FXML
  private TextField csceneText;

  @FXML
  private TextField addressText;

  @FXML
  private TextField nrcText;

  @FXML
  private ImageView crimeImage;

  @FXML
  private ChoiceBox<String> genderText;

  @FXML
  private Button importBtn;

  @FXML
  private Button updateBtn;

  @FXML
  private MenuButton filterBtn;

  @FXML
  void searchAll(ActionEvent event) {
    filterBtn.setText("All");
    filterValue.fValue = "all";
    searchField.setText("");
  }

  @FXML
  void searchID(ActionEvent event) {
    filterBtn.setText("ID");
    filterValue.fValue = "id";
    searchField.setText("");
  }

  @FXML
  void searchAddress(ActionEvent event) {
    filterBtn.setText("Address");
    filterValue.fValue = "address";
    searchField.setText("");
  }

  @FXML
  void searchCscene(ActionEvent event) {
    filterBtn.setText("Crime Scene");
    filterValue.fValue = "cscene";
    searchField.setText("");
  }

  @FXML
  void searchNRC(ActionEvent event) {
    filterBtn.setText("NRC");
    filterValue.fValue = "nrc";
    searchField.setText("");
  }

  @FXML
  void searchName(ActionEvent event) {
    filterBtn.setText("Name");
    filterValue.fValue = "name";
    searchField.setText("");
  }

  @FXML
  void searchType(ActionEvent event) {
    filterBtn.setText("Type");
    filterValue.fValue = "type";
    searchField.setText("");
  }

  ObservableList<Model> ModelObservableList = FXCollections.observableArrayList();

  @Override
  public void initialize(URL url, ResourceBundle resource) {

    cname.setCellFactory(column -> new WrappingTextTableCell<>());
    ctype.setCellFactory(column -> new WrappingTextTableCell<>());
    cscene.setCellFactory(column -> new WrappingTextTableCell<>());
    caddress.setCellFactory(column -> new WrappingTextTableCell<>());
    cnrc.setCellFactory(column -> new WrappingTextTableCell<>());
    cgender.setCellFactory(column -> new WrappingTextTableCell<>());

    filterValue.fValue = "all";
    ModelObservableList = FXCollections.observableArrayList(); // Initialize the ObservableList

    genderText.getItems().addAll(gender);

    Database connectNow = new Database();
    Connection connectDB = connectNow.getDBConnection();

    String crimeViewQuery = "Select ID, Name, Type, Address, Crime_Scene, Gender, NRC FROM criminals";

    try {

      Statement statement = connectDB.createStatement();
      ResultSet queryOutput = statement.executeQuery(crimeViewQuery);

      while (queryOutput.next()) {

        Integer queryID = queryOutput.getInt("ID");
        String queryName = queryOutput.getString("Name");
        String queryType = queryOutput.getString("Type");
        String queryAddress = queryOutput.getString("Address");
        String queryCrime_scene = queryOutput.getString("Crime_scene");
        String queryGender = queryOutput.getString("Gender");
        String queryNRC = queryOutput.getString("NRC");

        ModelObservableList
            .add(new Model(queryID, queryName, queryType, queryAddress, queryCrime_scene, queryGender, queryNRC));
      }

      cid.setCellValueFactory(new PropertyValueFactory<>("ID"));
      cname.setCellValueFactory(new PropertyValueFactory<>("Name"));
      ctype.setCellValueFactory(new PropertyValueFactory<>("Type"));
      caddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
      cscene.setCellValueFactory(new PropertyValueFactory<>("Crime_scene"));
      cgender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
      cnrc.setCellValueFactory(new PropertyValueFactory<>("NRC"));

      criminalTable.setItems(ModelObservableList);

      FilteredList<Model> filteredData = new FilteredList<>(ModelObservableList, b -> true);
      searchField.textProperty().addListener((observable, oldValue, newValue) -> {

        filteredData.setPredicate(Model -> {

          if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
            return true;
          }

          String searchKeyword = newValue.toLowerCase();

          if (filterValue.fValue == "all") {
            if (Model.getName().toLowerCase().indexOf(searchKeyword) > -1) {
              return true;
            } else if (Model.getType().toLowerCase().indexOf(searchKeyword) > -1) {
              return true;
            } else if (Model.getCrime_scene().toLowerCase().indexOf(searchKeyword) > -1) {
              return true;
            } else if (Model.getAddress().toLowerCase().indexOf(searchKeyword) > -1) {
              return true;
            } else if (Model.getID().toString().indexOf(searchKeyword) > -1) {
              return true;
            } else if (Model.getNRC().toLowerCase().indexOf(searchKeyword) > -1) {
              return true;
            } else
              return false;
          }

          if (filterValue.fValue == "name" && Model.getName().toLowerCase().indexOf(searchKeyword) > -1) {
            return true;
          } else if (filterValue.fValue == "type" && Model.getType().toLowerCase().indexOf(searchKeyword) > -1) {
            return true;
          } else if (filterValue.fValue == "cscene"
              && Model.getCrime_scene().toLowerCase().indexOf(searchKeyword) > -1) {
            return true;
          } else if (filterValue.fValue == "address" && Model.getAddress().toLowerCase().indexOf(searchKeyword) > -1) {
            return true;
          } else if (filterValue.fValue == "id" && Model.getID().toString().indexOf(searchKeyword) > -1) {
            return true;
          } else if (filterValue.fValue == "nrc" && Model.getNRC().toLowerCase().indexOf(searchKeyword) > -1) {
            return true;
          } else
            return false;

        });
      });

      SortedList<Model> sortedData = new SortedList<>(filteredData);
      sortedData.comparatorProperty().bind(criminalTable.comparatorProperty());
      criminalTable.setItems(sortedData);

    }

    catch (SQLException e) {

      Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
      e.printStackTrace();
    }

  }

  public class filterValue {
    public static String fValue;
    public static Integer img;
  }

  private String[] gender = { "Male", "Female" };

  @FXML
  void displayInfo(MouseEvent event) {
    Model Name = criminalTable.getSelectionModel().getSelectedItem();

    if (Name == null) {
      nameText.setText(" ");
      importBtn.setDisable(true);
    } else {
      Integer id = Name.getID();
      String name = Name.getName();
      String type = Name.getType();
      String address = Name.getAddress();
      String crimeScene = Name.getCrime_scene();
      String gender = Name.getGender();
      String nrc = Name.getNRC();
      nameText.setText(name);
      typeText.setText(type);
      addressText.setText(address);
      csceneText.setText(crimeScene);
      genderText.setValue(gender);
      nrcText.setText(nrc);

      File file = new File("src\\image\\" + id + ".jpg");
      Image crimeImg = new Image(file.toURI().toString());
      crimeImage.setImage(crimeImg);

      filterValue.img = id;

      importBtn.setDisable(false);
      updateBtn.setDisable(false);
    }
  }

  @FXML
  void importImg(MouseEvent event) throws MalformedURLException {

    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Image Files", "*.jpg"));

    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {

      File oldImageFile = new File("src\\image\\" + filterValue.img + ".jpg");
      if (oldImageFile.exists()) {
        oldImageFile.delete();
      }

      File renamedFile = new File(selectedFile.getParent(), filterValue.img + ".jpg");

      File selectedFolder = new File("src\\image");
      selectedFolder.mkdirs();

      File newLocation = new File(selectedFolder, renamedFile.getName());
      selectedFile.renameTo(newLocation);

      Image crimeImg = new Image(newLocation.toURI().toString());
      crimeImage.setImage(crimeImg);
    }
  }

  @FXML
  void updateInfo(MouseEvent event) {

    Model selectedModel = criminalTable.getSelectionModel().getSelectedItem();

    int id = selectedModel.getID();
    String updatedName = nameText.getText();
    String updatedType = typeText.getText();
    String updatedAddress = addressText.getText();
    String updatedCrimeScene = csceneText.getText();
    String updatedGender = genderText.getValue();
    String updatedNRC = nrcText.getText();

    selectedModel.setName(updatedName);
    selectedModel.setType(updatedType);
    selectedModel.setAddress(updatedAddress);
    selectedModel.setCrime_scene(updatedCrimeScene);
    selectedModel.setGender(updatedGender);
    selectedModel.setNRC(updatedNRC);

    Database connectNow = new Database();
    Connection connectDB = connectNow.getDBConnection();

    String updateQuery = "UPDATE criminals SET Name=?, Type=?, Address=?, Crime_scene=?, Gender=?, NRC=? WHERE ID=?";

    try (PreparedStatement preparedStatement = connectDB.prepareStatement(updateQuery)) {
      preparedStatement.setString(1, updatedName);
      preparedStatement.setString(2, updatedType);
      preparedStatement.setString(3, updatedAddress);
      preparedStatement.setString(4, updatedCrimeScene);
      preparedStatement.setString(5, updatedGender);
      preparedStatement.setString(6, updatedNRC);
      preparedStatement.setInt(7, id);

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

  }
}
