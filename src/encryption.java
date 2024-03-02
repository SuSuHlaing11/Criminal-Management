import org.mindrot.jbcrypt.BCrypt;

public class encryption {

  public static void main(String[] args) {
    String plainPassword = "operatorpswd2";

    // Generate a bcrypt hash code for the password
    String hashedPassword = hashPassword(plainPassword);

    // Output the generated hash code
    System.out.println("Generated Hash Code: " + hashedPassword);
  }

  public static String hashPassword(String plainPassword) {
    // Generate a bcrypt hash code with default cost factor (12)
    return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
  }
}
// admin11
// adminpassword1
// operator11
// operatorpassword1
// Su Su Hlaing
// sshadmin123
// Eaint Pyae Phyo
// eppadmin12334
// operator2
// operatorpswd2
