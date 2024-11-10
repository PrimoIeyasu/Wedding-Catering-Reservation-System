/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package simple.crud;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 *
 * @author Jon Ken Vergara
 */
public class CEvent extends javax.swing.JFrame {
    
    private DefaultComboBoxModel<String> customerComboBoxModel = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> customerModel;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/java_users_db";
    private static final String user = "root";
    private static final String password = "";
    /**
     * Creates new form CEvent
     */
    
     public CEvent() {
        
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("/simple/crud/logoh&h.jpeg"));  
        setIconImage(icon.getImage());
        
        
         // Set the minimum selectable date for jDateChooser1 to the current date
        jDateChooser1.setDate(new Date()); 
        jDateChooser1.setMinSelectableDate(new Date());
        
        // Set the minimum selectable date for jDateChooser2 to the current date
        jDateChooser2.setDate(new Date());
        jDateChooser2.setMinSelectableDate(new Date());
    }
      private void CEActionPerformed(java.awt.event.ActionEvent evt) {                                   
        // Open the CEvent window
        CEvent cEvent = new CEvent();
        cEvent.setVisible(true);
    } 
    
    private int getCustomerId() {
    // Retrieve the selected item (customer ID) from the combo box
    String selectedCustomerId = (String) customerid.getSelectedItem();

    // Convert the selected customer ID to an integer
    try {
        return Integer.parseInt(selectedCustomerId);
    } catch (NumberFormatException e) {
        // Handle the case where the selected item is not a valid integer
        e.printStackTrace();
        return -1; // or return a default value
    }
}
    
    

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {
    int customerId = getCustomerId(); // Call the getCustomerId method here
    String eventPlace = ePlace.getText();
    LocalDate orderdate = LocalDate.ofInstant(jDateChooser1.getDate().toInstant(), ZoneId.systemDefault());
    LocalDate eventdate = LocalDate.ofInstant(jDateChooser2.getDate().toInstant(), ZoneId.systemDefault());
    String totalAmount = totalA.getText();
    String status = getStatus();
    
   

    try (Connection connection = createConnection()) {
        // Create SQL query
        String query = "INSERT INTO event (customerId, eplace, orderdate, eventdate, total, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set parameters
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, eventPlace);
            preparedStatement.setDate(3, java.sql.Date.valueOf(orderdate));
            preparedStatement.setDate(4, java.sql.Date.valueOf(eventdate));
            preparedStatement.setString(5, totalAmount);
            preparedStatement.setString(6, status);
            
             removeSelectedCustomerId();
            preparedStatement.executeUpdate();
           
            System.out.println("Event inserted successfully!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
  private void populateCustomerIds() {
    try (Connection connection = createConnection()) {
        String query = "SELECT id FROM user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                customerid.removeAllItems(); // Clear existing items
                
                while (resultSet.next()) {
                    int userId = resultSet.getInt("id");
                    customerid.addItem(String.valueOf(userId));
                }
                
                // Remove the selected item from the JComboBox
                String selectedItem = String.valueOf(getCustomerId());
                customerid.removeItem(selectedItem);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   private void removeItemFromComboBox(JComboBox<String> comboBox, String itemToRemove) {
    for (int i = 1; i < comboBox.getItemCount(); i++) {
        if (comboBox.getItemAt(i).equals(itemToRemove)) {
            comboBox.removeItemAt(i);
            break;
        }
    }
}

    // Remove the selected ID from the JComboBox
    

   private void removeSelectedCustomerId() {
    Object selectedId = customerid.getSelectedItem();
    if (selectedId != null) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) customerid.getModel();
        model.removeElement(selectedId);
    }
}
    private Connection createConnection() throws SQLException {
    return DriverManager.getConnection(JDBC_URL, user, password);
}

     private String getStatus() {
        if (jRadioButton1.isSelected()) {
            return "Pending";
        } else if (jRadioButton2.isSelected()) {
            return "Confirmed";
        } else if (jRadioButton3.isSelected()) {
            return "Completed";
        } else if (jRadioButton4.isSelected()) {
            return "Canceled";
        } else {
            return "";
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusGroup = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ePlace = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        totalA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        customerid = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(619, 550));
        setPreferredSize(new java.awt.Dimension(619, 550));
        getContentPane().setLayout(null);

        jLabel6.setBackground(new java.awt.Color(61, 52, 21));
        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("EVENTS MANAGER");
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(130, 20, 340, 50);

        jLabel2.setBackground(new java.awt.Color(61, 52, 21));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Event Place");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 80, 70, 20);

        ePlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ePlaceActionPerformed(evt);
            }
        });
        getContentPane().add(ePlace);
        ePlace.setBounds(130, 102, 338, 39);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Order Date");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(130, 147, 70, 20);

        jLabel4.setBackground(new java.awt.Color(61, 52, 21));
        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Event Date");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(130, 214, 70, 16);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total Amount");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 281, 80, 16);

        totalA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAActionPerformed(evt);
            }
        });
        getContentPane().add(totalA);
        totalA.setBounds(130, 303, 338, 37);

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Status");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(130, 350, 50, 16);

        send.setBackground(new java.awt.Color(61, 52, 21));
        send.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        send.setForeground(new java.awt.Color(255, 255, 255));
        send.setText("Submit");
        send.setText("Submit");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        getContentPane().add(send);
        send.setBounds(250, 420, 90, 39);
        getContentPane().add(jDateChooser1);
        jDateChooser1.setBounds(130, 170, 338, 39);
        getContentPane().add(jDateChooser2);
        jDateChooser2.setBounds(130, 240, 338, 39);

        statusGroup.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jRadioButton1.setText("Pending");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(100, 370, 98, 21);

        statusGroup.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jRadioButton2.setText("Confirmed");
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(200, 370, 98, 21);

        statusGroup.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jRadioButton3.setText("Completed");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3);
        jRadioButton3.setBounds(300, 370, 98, 21);

        statusGroup.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jRadioButton4.setText("Canceled");
        getContentPane().add(jRadioButton4);
        jRadioButton4.setBounds(400, 370, 98, 21);

        customerid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        populateCustomerIds();
        customerid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customeridActionPerformed(evt);
            }
        });
        getContentPane().add(customerid);
        customerid.setBounds(490, 210, 82, 39);

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Customers ID");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(490, 190, 80, 16);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/simple/crud/cebg.jpg"))); // NOI18N
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(-240, 0, 1360, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ePlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ePlaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ePlaceActionPerformed

    private void totalAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void customeridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customeridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customeridActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CEvent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> customerid;
    private javax.swing.JTextField ePlace;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JButton send;
    private javax.swing.ButtonGroup statusGroup;
    private javax.swing.JTextField totalA;
    // End of variables declaration//GEN-END:variables
}
