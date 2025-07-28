
import ConnectionProvider.ConnectionProvider;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class editMembers extends javax.swing.JFrame {

    public editMembers() {
        initComponents();
        setupStudentEditListener();
        
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = 900;
    int height = 600;
    
    // Calculate the position to center the window
    int x = (screenSize.width - width) / 2;
    int y = (screenSize.height - height) / 2;
    
    // Set window bounds (position and size)
    setBounds(x, y, width, height);
    
    // Make window non-resizable (optional)
    setResizable(false);
    }
    
     private void setupStudentEditListener() {
        MemberID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fetchStudentEdit();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fetchStudentEdit();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fetchStudentEdit();
            }
        });
    }
     
    private void fetchStudentEdit() {
    String studentID = MemberID.getText();
    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        String sql = "SELECT member_name, tp_number, address, email FROM members WHERE member_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, studentID);
        
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String memberName = rs.getString("member_name");
            String tpNumber = rs.getString("tp_number");
            String address = rs.getString("address");
            String email = rs.getString("email");
            

            MemberName.setText(memberName);
            TPNumber.setText(tpNumber);
            Address.setText(address);
            EMail.setText(email);
        } else {
            MemberName.setText("");
            TPNumber.setText("");
            Address.setText("");
            EMail.setText("");
        }

        pst.close();
        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Save = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        Address = new javax.swing.JTextField();
        TPNumber = new javax.swing.JTextField();
        MemberID = new javax.swing.JTextField();
        MemberName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EMail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        Save.setBackground(new java.awt.Color(0, 0, 0));
        Save.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Save.setForeground(new java.awt.Color(255, 255, 255));
        Save.setText("Save");
        Save.setBorder(null);
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Close.setBackground(new java.awt.Color(0, 0, 0));
        Close.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Close.setForeground(new java.awt.Color(255, 255, 255));
        Close.setText("Close");
        Close.setBorder(null);
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        Address.setBackground(new java.awt.Color(255, 255, 255));
        Address.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Address.setForeground(new java.awt.Color(0, 0, 0));
        Address.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        Address.setCaretColor(new java.awt.Color(255, 51, 51));
        Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddressActionPerformed(evt);
            }
        });

        TPNumber.setBackground(new java.awt.Color(255, 255, 255));
        TPNumber.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TPNumber.setForeground(new java.awt.Color(0, 0, 0));
        TPNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        TPNumber.setCaretColor(new java.awt.Color(255, 51, 51));

        MemberID.setBackground(new java.awt.Color(255, 255, 255));
        MemberID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        MemberID.setForeground(new java.awt.Color(0, 0, 0));
        MemberID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        MemberID.setCaretColor(new java.awt.Color(255, 51, 51));

        MemberName.setBackground(new java.awt.Color(255, 255, 255));
        MemberName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        MemberName.setForeground(new java.awt.Color(0, 0, 0));
        MemberName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        MemberName.setCaretColor(new java.awt.Color(255, 51, 51));
        MemberName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MemberNameActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 51, 51));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Member Name");

        EMail.setBackground(new java.awt.Color(255, 255, 255));
        EMail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        EMail.setForeground(new java.awt.Color(0, 0, 0));
        EMail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        EMail.setCaretColor(new java.awt.Color(255, 51, 51));
        EMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMailActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Address");

        jLabel7.setBackground(new java.awt.Color(255, 51, 51));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Registration number");

        jLabel8.setBackground(new java.awt.Color(255, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("T.P Number");

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("E Mail");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 153, 255));
        jLabel12.setText("Edit Members");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel12)
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(Save, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(Address)
                    .addComponent(EMail)
                    .addComponent(TPNumber)
                    .addComponent(MemberName)
                    .addComponent(MemberID))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(MemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(MemberName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(TPNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new addMember().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

    private void AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddressActionPerformed

    private void MemberNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MemberNameActionPerformed

    private void EMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMailActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
         try {
        // Get values and print them for debugging
        String memberId = MemberID.getText();
        String memberName = MemberName.getText();
        String address = Address.getText();
        String tpNumber = TPNumber.getText();
        String email = EMail.getText();
        
        // Debug print
        System.out.println("Attempting to save: " + memberId + ", " + memberName + ", " + tpNumber);

        // Check if connection is successful
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }
        System.out.println("Database connected successfully");

        // Correct SQL for updating an existing record
        String sql = "UPDATE members SET member_name = ?, tp_number = ?, address = ?, email = ? WHERE member_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setString(1, memberName);
        pst.setString(2, tpNumber);
        pst.setString(3, address);
        pst.setString(4, email);
        pst.setString(5, memberId);
        
        // Debug print the SQL
        System.out.println("Executing SQL: " + pst.toString());
        
        int result = pst.executeUpdate();
        System.out.println("Rows affected: " + result);
        
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Member Updated Successfully!");
            // Clear fields
            MemberID.setText("");
            MemberName.setText("");
            Address.setText("");
            TPNumber.setText("");
            EMail.setText("");
            
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update member!");
        }
        
        // Close connections
        pst.close();
        con.close();
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "SQL Error: " + e.getMessage());
        e.printStackTrace();
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_SaveActionPerformed

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
            java.util.logging.Logger.getLogger(editMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editMembers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Address;
    private javax.swing.JButton Close;
    private javax.swing.JTextField EMail;
    private javax.swing.JTextField MemberID;
    private javax.swing.JTextField MemberName;
    private javax.swing.JButton Save;
    private javax.swing.JTextField TPNumber;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
