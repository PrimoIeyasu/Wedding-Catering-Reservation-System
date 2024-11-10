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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Jon Ken Vergara
 */
public class EventsC extends javax.swing.JFrame {
//    private int customerId;
//    private String eplace;
//    private Date orderdate;
//    private Date eventdate;
//    private String total;
//    private String status;
    
    /**
     * Creates new form Events
     */
    public EventsC() {
        initComponents();
         displayEventData();
         initCustomComponents();
    }
    public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/java_users_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
    
   private void displayEventData() {
    try {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM event";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
         DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
            leftRenderer.setHorizontalAlignment(JLabel.LEFT);

            for (int i = 0; i < model.getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
            }

        while (resultSet.next()) {
            Object[] rowData = {
                resultSet.getInt("orderId"),
                resultSet.getInt("customerId"),
                resultSet.getString("eplace"),
                resultSet.getDate("orderdate"),
                resultSet.getDate("eventdate"),
                resultSet.getString("total"),
                resultSet.getString("status")
            };
            model.addRow(rowData);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    private void initCustomComponents() {
        CE = new javax.swing.JButton();
       
        // ... other button setup code ...

        // Add action listener to the "Create" button
        CE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCEventForm();
            }
        });
        
        
    }
  private void openCEventForm() {
    CEvent cEventForm = new CEvent();
    cEventForm.setLocationRelativeTo(this);
    cEventForm.setVisible(true);
}
   private void openUpdateEForm() {
      DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    View parentView = new View();  // Replace 'View' with the actual class name
    UpdateE updateEForm = new UpdateE(model, parentView);
    updateEForm.setLocationRelativeTo(this);
    updateEForm.setVisible(true);
}
  
//    public void setCustomerId(int customerId) {
//        // Implement the setter for customerId
//        this.customerId = customerId;
//    }
//
//    public void setEplace(String eplace) {
//        // Implement the setter for eplace
//        this.eplace = eplace;
//    }
//
//    public void setOrderdate(Date orderdate) {
//        // Implement the setter for orderdate
//        this.orderdate = orderdate;
//    }
//
//    public void setEventdate(Date eventdate) {
//        // Implement the setter for eventdate
//        this.eventdate = eventdate;
//    }
//
//    public void setTotal(String total) {
//        // Implement the setter for total
//        this.total = total;
//    }
//
//    public void setStatus(String status) {
//        // Implement the setter for status
//        this.status = status;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CE = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1240, 1240));
        getContentPane().setLayout(null);

        CE.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        CE.setText("Create");
        CE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CEActionPerformed(evt);
            }
        });
        getContentPane().add(CE);
        CE.setBounds(100, 80, 100, 50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Customer ID", "Event Place", "Order Date", "Event Date", "Total Amount", "OrderStatus"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setMaximumSize(new java.awt.Dimension(0, 0));
        jTable1.setMinimumSize(new java.awt.Dimension(500, 400));
        jTable1.setPreferredSize(new java.awt.Dimension(500, 400));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(100, 150, 800, 470);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/simple/crud/eventbg.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-380, -260, 1470, 1010);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEActionPerformed
        openCEventForm();        // TODO add your handling code here:
    }//GEN-LAST:event_CEActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
