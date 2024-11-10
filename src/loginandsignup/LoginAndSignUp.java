
package loginandsignup;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import splashscreen.SplashScreen;




  public class LoginAndSignUp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Login loginFrame = new Login();
                loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginFrame.setVisible(true);
                loginFrame.pack();
                loginFrame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

    
