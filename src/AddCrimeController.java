import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;

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
    private Label invalidNRC;

    @FXML
    private Label invalidAge;

    @FXML
    private Label invalidDate;

    @FXML
    private TextField address;

    @FXML
    private TextField cscene;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    private TextField nrc;

    @FXML
    private TextField age;

    @FXML
    private DatePicker cdate;

    @FXML
    private ImageView crimeImage1;

    @FXML
    private RadioButton male1;

    @FXML
    private RadioButton female1;

    @FXML
    private RadioButton others1;

    @FXML
    private ToggleGroup toggleGp;

    private String[] type = { "Murder", "Robbery", "Smuggling Weapons", "Smuggling Drug", "Fraud", "Others" };

    public class imgId {
        public static Integer imgid;
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        typeBox.getItems().addAll(type);

        toggleGp = new ToggleGroup();
        male1.setToggleGroup(toggleGp);
        female1.setToggleGroup(toggleGp);
        others1.setToggleGroup(toggleGp);

        imgId.imgid = getLatestIdFromDatabase();

        cdate.setDayCellFactory(new RestrictFutureDateCellFactory());

        cdate.setConverter(new StringConverter<LocalDate>() {
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
        int ageTem = Integer.parseInt(age.getText());
        String nrcTem = nrc.getText();
        String typeTem = typeBox.getValue();
        LocalDate dateTem = cdate.getValue();
        String csceneTem = cscene.getText();
        String addressTem = address.getText();
        String genderTem;

        if (male1.isSelected()) {
            genderTem = "Male";
        } else if (female1.isSelected()) {
            genderTem = "Female";
        } else {
            genderTem = "Others";
        }

        LocalDate currentDate = LocalDate.now();

        String nrcPattern = "^([0-9]{1,2})\\/([A-Z])([A-Z])([A-Z])\\([N,P,E]\\)[0-9]{6}$";

        if (ageTem < 100) {

            if (nrcTem.matches(nrcPattern)) {

                if (dateTem.isBefore(currentDate)) {

                    LocalDate selectedDate = cdate.getValue();
                    String cdateTem = selectedDate != null ? selectedDate.toString() : null;

                    Database connectNow = new Database();
                    Connection connectDB = connectNow.getDBConnection();

                    String insertQuery = "INSERT INTO criminals (Name, Age, Type, Address, Date, Crime_Scene, Gender, NRC) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement preparedStatement = connectDB.prepareStatement(insertQuery)) {
                        preparedStatement.setString(1, nameTem);
                        preparedStatement.setInt(2, ageTem);
                        preparedStatement.setString(3, typeTem);
                        preparedStatement.setString(4, addressTem);
                        preparedStatement.setString(5, cdateTem);
                        preparedStatement.setString(6, csceneTem);
                        preparedStatement.setString(7, genderTem);
                        preparedStatement.setString(8, nrcTem);

                        int affectedRows = preparedStatement.executeUpdate();

                        connectDB.setAutoCommit(true);

                        if (affectedRows > 0) {
                            System.out.println("Data inserted successfully!");
                        } else {
                            System.out.println("Failed to insert data.");
                        }

                        name.setText("");
                        age.setText("");
                        typeBox.setValue("");
                        address.setText("");
                        cscene.setText("");
                        nrc.setText("");
                        toggleGp.selectToggle(null);
                        cdate.setValue(null);
                        invalidNRC.setText("");
                        invalidAge.setText("");
                        invalidDate.setText("");

                        Image defaultImage = new Image(getClass().getResourceAsStream("/image/DefultDescription.jpg"));
                        crimeImage1.setImage(defaultImage);

                        registerSuccess.setText("Registration successful.");

                    } catch (SQLException e) {
                        Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
                        e.printStackTrace();
                    }

                } else {
                    invalidDate.setText("Invalid Date");
                    invalidNRC.setText("");
                    invalidAge.setText("");
                }

            } else {
                invalidNRC.setText("Invalid NRC Format.");
                invalidDate.setText("");
                invalidAge.setText("");
            }

        } else {
            invalidAge.setText("Invaid Age");
            invalidNRC.setText("");
            invalidDate.setText("");
        }
    }

}
