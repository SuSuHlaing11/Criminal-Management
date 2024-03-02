import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
  private TableColumn<Model, Integer> cage;

  @FXML
  private TableColumn<Model, LocalDate> cdate;

  @FXML
  private TextField searchField;

  @FXML
  private TextField nameText;

  @FXML
  private TextField ageText;

  @FXML
  private ChoiceBox<String> typeText;

  @FXML
  private DatePicker cdateText;

  @FXML
  private TextField csceneText;

  @FXML
  private TextField addressText;

  @FXML
  private TextField nrcText;

  @FXML
  private ImageView crimeImage;

  @FXML
  private Button importBtn;

  @FXML
  private Button updateBtn;

  @FXML
  private MenuButton filterBtn;

  @FXML
  private Label notice;

  @FXML
  private Label invalidNRC;

  @FXML
  private Label invalidAge;

  @FXML
  private Label invalidDate;

  @FXML
  private RadioButton male;

  @FXML
  private RadioButton female;

  @FXML
  private RadioButton others;

  @FXML
  private ToggleGroup toggleGp;

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

  @FXML
  void searchAge(ActionEvent event) {
    filterBtn.setText("Age");
    filterValue.fValue = "age";
    searchField.setText("");
  }

  @FXML
  void searchDate(ActionEvent event) {
    filterBtn.setText("Date");
    filterValue.fValue = "date";
    searchField.setText("");
  }

  @FXML
  void searchGender(ActionEvent event) {
    filterBtn.setText("Gender");
    filterValue.fValue = "gender";
    searchField.setText("");
  }

  public class filterValue {
    public static String fValue;
    public static Integer img;
  }

  ObservableList<Model> ModelObservableList = FXCollections.observableArrayList();

  @Override
  public void initialize(URL url, ResourceBundle resource) {

    typeText.getItems().addAll(type);

    cdateText.setDayCellFactory(new RestrictFutureDateCellFactory());

    cdateText.setConverter(new StringConverter<LocalDate>() {
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

      @Override
      public String toString(LocalDate date) {
        return (date != null) ? dateFormatter.format(date) : "";
      }

      @Override
      public LocalDate fromString(String string) {
        return (string != null && !string.isEmpty()) ? LocalDate.parse(string, dateFormatter) : null;
      }
    });

    toggleGp = new ToggleGroup();
    male.setToggleGroup(toggleGp);
    female.setToggleGroup(toggleGp);
    others.setToggleGroup(toggleGp);

    filterValue.fValue = "all";

    ModelObservableList = FXCollections.observableArrayList();

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
            .add(new Model(queryID, queryName, queryAge, queryType, queryAddress, queryDate, queryCrime_scene,
                queryGender, queryNRC));
      }

      cid.setCellValueFactory(new PropertyValueFactory<>("ID"));
      cname.setCellValueFactory(new PropertyValueFactory<>("Name"));
      cage.setCellValueFactory(new PropertyValueFactory<>("Age"));
      ctype.setCellValueFactory(new PropertyValueFactory<>("Type"));
      caddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
      cdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
      cscene.setCellValueFactory(new PropertyValueFactory<>("Crime_scene"));
      cgender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
      cnrc.setCellValueFactory(new PropertyValueFactory<>("NRC"));

      cname.setCellFactory(column -> new WrappingTextTableCell<>());
      ctype.setCellFactory(column -> new WrappingTextTableCell<>());
      cscene.setCellFactory(column -> new WrappingTextTableCell<>());
      caddress.setCellFactory(column -> new WrappingTextTableCell<>());
      cnrc.setCellFactory(column -> new WrappingTextTableCell<>());
      cgender.setCellFactory(column -> new WrappingTextTableCell<>());

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
          } else if (filterValue.fValue == "type" && Model.getType().toLowerCase().indexOf(searchKeyword) > -1) {
            return true;
          } else if (filterValue.fValue == "date" && Model.getDate().toString().indexOf(searchKeyword) > -1) {
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
          } else if (filterValue.fValue == "gender"
              && Model.getGender().toLowerCase().indexOf(searchKeyword) > -1) {
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

  private static class RestrictFutureDateCellFactory implements Callback<DatePicker, DateCell> {
    @Override
    public DateCell call(final DatePicker datePicker) {
      return new DateCell() {
        @Override
        public void updateItem(LocalDate item, boolean empty) {
          super.updateItem(item, empty);

          // Disable future dates
          if (item.isAfter(LocalDate.now())) {
            setDisable(true);
            setStyle("-fx-background-color: #bfbfbf;");
          }
        }
      };
    }
  }

  private String[] type = { "Murder", "Robbery", "Smuggling Weapons", "Smuggling Drug", "Fraud", "Others" };

  private void disableInputComponents(boolean disable) {
    nameText.setDisable(disable);
    typeText.setDisable(disable);
    ageText.setDisable(disable);
    cdateText.setDisable(disable);
    csceneText.setDisable(disable);
    addressText.setDisable(disable);
    nrcText.setDisable(disable);
    male.setDisable(disable);
    female.setDisable(disable);
    others.setDisable(disable);
    importBtn.setDisable(disable);
    updateBtn.setDisable(disable);
  }

  @FXML
  void displayInfo(MouseEvent event) {
    Model selectedModel = criminalTable.getSelectionModel().getSelectedItem();

    if (selectedModel == null) {
      nameText.setText(" ");
      disableInputComponents(true);
    } else {
      Integer id = selectedModel.getID();
      String name = selectedModel.getName();
      Integer age = selectedModel.getAge();
      String type = selectedModel.getType();
      String address = selectedModel.getAddress();
      LocalDate date = selectedModel.getDate();
      String crimeScene = selectedModel.getCrime_scene();
      String nrc = selectedModel.getNRC();
      String gender = selectedModel.getGender();

      if ("Male".equals(gender)) {
        toggleGp.selectToggle(male);
      } else if ("Female".equals(gender)) {
        toggleGp.selectToggle(female);
      } else {
        toggleGp.selectToggle(others);
      }

      nameText.setText(name);
      ageText.setText(String.valueOf(age));
      typeText.setValue(type);
      addressText.setText(address);
      cdateText.setValue(date);
      csceneText.setText(crimeScene);
      nrcText.setText(nrc);

      filterValue.img = id;

      File file = new File("src\\image\\" + id + ".jpg");
      Image crimeImg = new Image(file.toURI().toString());
      crimeImage.setImage(crimeImg);

      disableInputComponents(false);

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
    int updatedAge = selectedModel.getAge();
    String updatedType = typeText.getValue();
    String updatedAddress = addressText.getText();
    LocalDate updatedDate = cdateText.getValue();
    String updatedCrimeScene = csceneText.getText();
    String updatedNRC = nrcText.getText();
    String updatedGender;

    if (male.isSelected()) {
      updatedGender = "Male";
    } else if (female.isSelected()) {
      updatedGender = "Female";
    } else {
      updatedGender = "Others";
    }

    selectedModel.setName(updatedName);
    selectedModel.setAge(updatedAge);
    selectedModel.setType(updatedType);
    selectedModel.setAddress(updatedAddress);
    selectedModel.setDate(updatedDate);
    selectedModel.setCrime_scene(updatedCrimeScene);
    selectedModel.setGender(updatedGender);
    selectedModel.setNRC(updatedNRC);

    LocalDate currentDate = LocalDate.now();

    String nrcPattern = "^([0-9]{1,2})\\/([A-Z])([A-Z])([A-Z])\\([N,P,E]\\)[0-9]{6}$";

    if (updatedAge > 100) {

      if (updatedNRC.matches(nrcPattern)) {

        if (updatedDate.isAfter(currentDate)) {

          Database connectNow = new Database();
          Connection connectDB = connectNow.getDBConnection();

          String updateQuery = "UPDATE criminals SET Name=?, Age=?, Type=?, Address=?, Date=?, Crime_scene=?, Gender=?, NRC=? WHERE ID=?";

          try (PreparedStatement preparedStatement = connectDB.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, updatedName);
            preparedStatement.setInt(2, updatedAge);
            preparedStatement.setString(3, updatedType);
            preparedStatement.setString(4, updatedAddress);
            preparedStatement.setObject(5, updatedDate);
            preparedStatement.setString(6, updatedCrimeScene);
            preparedStatement.setString(7, updatedGender);
            preparedStatement.setString(8, updatedNRC);
            preparedStatement.setInt(9, id);

            notice.setText("Update Successful!");

            Image defaultImage = new Image(getClass().getResourceAsStream("/image/DefultDescription.jpg"));
            crimeImage.setImage(defaultImage);

            nameText.setText("");
            ageText.setText("");
            nrcText.setText("");
            toggleGp.selectToggle(null);
            addressText.setText("");
            cdateText.setValue(null);
            csceneText.setText("");
            typeText.setValue("");
            invalidNRC.setText("");
            invalidAge.setText("");
            invalidDate.setText("");

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
              System.out.println("Update successful!");
            } else {
              System.out.println("Update failed!");
            }

          } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(ManageCrimeController.class.getName()).log(Level.SEVERE, null, e);
          }
        } else
          invalidDate.setText("Invalid Date");
        invalidNRC.setText("");
        invalidAge.setText("");

      } else
        invalidNRC.setText("Invalid NRC Format.");
      invalidDate.setText("");
      invalidAge.setText("");

    } else
      invalidAge.setText("Invaid Age");
    invalidNRC.setText("");
    invalidDate.setText("");
  }

  @FXML
  void showDes(MouseEvent event) {
    showFullImage();
  }

  private void showFullImage() {

    Stage fullImageStage = new Stage();

    ImageView fullImageView = new ImageView(crimeImage.getImage());

    fullImageStage.setScene(new javafx.scene.Scene(new javafx.scene.layout.StackPane(fullImageView)));

    fullImageStage.setTitle("Criminal Description");
    fullImageStage.setWidth(600);
    fullImageStage.setHeight(600);

    fullImageStage.show();
  }

}
