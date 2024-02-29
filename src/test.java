import org.mindrot.jbcrypt.BCrypt;

public class test {

  public static void main(String[] args) {
    // Plain text password to hash
    String plaintextPassword = "operatorpassword2";

    // Hash the plain text password
    String hashedPassword = BCrypt.hashpw(plaintextPassword, BCrypt.gensalt());

    System.out.println(
      "BCrypt Hash for 'operatorpassword2': " + hashedPassword
    );

    // Verify the hashed password
    if (BCrypt.checkpw("operatorpassword2", hashedPassword)) {
      System.out.println("Passwords match!");
    } else {
      System.out.println("Passwords do not match!");
    }
  }
}
