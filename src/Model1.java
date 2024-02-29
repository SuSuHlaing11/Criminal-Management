public class Model1 {
    String Name, Username, Email, Password;

    public Model1(String Name, String Username, String Email, String Password) {
        this.Name = Name;
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
    }

    public String getName() {
        return Name;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getPw() {
        return Password;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPw(String password) {
        this.Password = password;
    }
}
