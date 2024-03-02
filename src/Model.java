import java.time.LocalDate;

public class Model {

    Integer ID, Age;
    String Name, Type, Address, Crime_scene, Gender, NRC;
    LocalDate Date;

    public Model(Integer ID, String Name, Integer Age, String Type, String Address, LocalDate Date, String Crime_scene,
            String Gender,
            String NRC) {
        this.ID = ID;
        this.Name = Name;
        this.Age = Age;
        this.Type = Type;
        this.Address = Address;
        this.Date = Date;
        this.Crime_scene = Crime_scene;
        this.Gender = Gender;
        this.NRC = NRC;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public Integer getAge() {
        return Age;
    }

    public String getType() {
        return Type;
    }

    public LocalDate getDate() {
        return Date;
    }

    public String getAddress() {
        return Address;
    }

    public String getCrime_scene() {
        return Crime_scene;
    }

    public String getGender() {
        return Gender;
    }

    public String getNRC() {
        return NRC;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAge(Integer Age) {
        this.Age = Age;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public void setCrime_scene(String Crime_scene) {
        this.Crime_scene = Crime_scene;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }
}
