
import ConnectionProvider.ConnectionProvider;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class addMember extends javax.swing.JFrame {

    public addMember() {
        initComponents();
        setupTable();
        fetchAndDisplayMembers("active");
             
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
    
    private void setupTable() {
        String[] columnNames = {"Member ID", "Name", "T.P Number", "Email", "Address", "Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        TableMembers.setModel(model);
    }

    private void fetchAndDisplayMembers(String status) {
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!");
                return;
            }

            String sql = "SELECT member_id, member_name, tp_number, email, address, created_at, status FROM members WHERE status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, status);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) TableMembers.getModel();
            model.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Object[] row = {
                    rs.getString("member_id"),
                    rs.getString("member_name"),
                    rs.getString("tp_number"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("created_at"),
                    rs.getString("status")
                };
                model.addRow(row);
            }

            // Close connections
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateMemberStatus(String memberId, String newStatus) {
        try {
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!");
                return;
            }

            String sql = "UPDATE members SET status = ? WHERE member_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, newStatus);
            pst.setString(2, memberId);

            int result = pst.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Member status updated successfully!");
                fetchAndDisplayMembers(newStatus);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update member status!");
            }

            pst.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating member status: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
     private void clearSearch() {
        SeachMembers.setText("");
        fetchAndDisplayMembers("active");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Save = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        Address = new javax.swing.JTextField();
        TPNumber = new javax.swing.JTextField();
        MemberID = new javax.swing.JTextField();
        MemberName = new javax.swing.JTextField();
        EMail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableMembers = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        Search = new javax.swing.JButton();
        ShowActive = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        SeachMembers = new javax.swing.JTextField();
        StatusChange = new javax.swing.JButton();
        ShowInactive = new javax.swing.JButton();

        jLabel2.setBackground(new java.awt.Color(255, 51, 51));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Member Name");

        jLabel3.setBackground(new java.awt.Color(255, 51, 51));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Address");

        jLabel1.setBackground(new java.awt.Color(255, 51, 51));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Member ID");

        jLabel4.setBackground(new java.awt.Color(255, 51, 51));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("T.P Number");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 51));

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel5.setBackground(new java.awt.Color(255, 51, 51));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Member Name");

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Address");

        jLabel7.setBackground(new java.awt.Color(255, 51, 51));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Member ID");

        jLabel8.setBackground(new java.awt.Color(255, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("T.P Number");

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("E Mail");

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

        TableMembers.setBackground(new java.awt.Color(255, 255, 255));
        TableMembers.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        TableMembers.setForeground(new java.awt.Color(255, 51, 51));
        TableMembers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        TableMembers.setGridColor(new java.awt.Color(255, 51, 51));
        TableMembers.setSelectionBackground(new java.awt.Color(255, 51, 51));
        TableMembers.setSelectionForeground(new java.awt.Color(255, 255, 255));
        TableMembers.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TableMembersAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(TableMembers);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("Add Members");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(288, 288, 288))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(20, 20, 20))
        );

        Search.setBackground(new java.awt.Color(0, 0, 0));
        Search.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Search.setForeground(new java.awt.Color(255, 255, 255));
        Search.setText("Search");
        Search.setBorder(null);
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        ShowActive.setBackground(new java.awt.Color(0, 0, 0));
        ShowActive.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ShowActive.setForeground(new java.awt.Color(255, 255, 255));
        ShowActive.setText("Active");
        ShowActive.setBorder(null);
        ShowActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowActiveActionPerformed(evt);
            }
        });

        Edit.setBackground(new java.awt.Color(0, 0, 0));
        Edit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Edit.setForeground(new java.awt.Color(255, 255, 255));
        Edit.setText("Edit");
        Edit.setBorder(null);
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        SeachMembers.setBackground(new java.awt.Color(255, 255, 255));
        SeachMembers.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SeachMembers.setForeground(new java.awt.Color(255, 51, 51));
        SeachMembers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        SeachMembers.setCaretColor(new java.awt.Color(255, 51, 51));

        StatusChange.setBackground(new java.awt.Color(0, 0, 0));
        StatusChange.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        StatusChange.setForeground(new java.awt.Color(255, 255, 255));
        StatusChange.setText("SyncState");
        StatusChange.setBorder(null);
        StatusChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusChangeActionPerformed(evt);
            }
        });

        ShowInactive.setBackground(new java.awt.Color(0, 0, 0));
        ShowInactive.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ShowInactive.setForeground(new java.awt.Color(255, 255, 255));
        ShowInactive.setText("Inactive");
        ShowInactive.setBorder(null);
        ShowInactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowInactiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(Save, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(Address)
                    .addComponent(EMail)
                    .addComponent(TPNumber)
                    .addComponent(MemberName)
                    .addComponent(MemberID))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(SeachMembers, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ShowActive, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ShowInactive, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StatusChange, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(MemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel6)
                                .addGap(58, 58, 58))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StatusChange, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(MemberName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TPNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ShowActive, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SeachMembers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ShowInactive, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new home().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

    private void AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddressActionPerformed

    private void EMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMailActionPerformed

    private void MemberNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MemberNameActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        setVisible(false);
        new editMembers().setVisible(true);
    }//GEN-LAST:event_EditActionPerformed

    private void StatusChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusChangeActionPerformed
        int selectedRow = TableMembers.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a member to change status");
        return;
    }

    String memberId = TableMembers.getValueAt(selectedRow, 0).toString();
    String currentStatus = TableMembers.getValueAt(selectedRow, 6).toString();

    String newStatus;
    String confirmationMessage;

    if (currentStatus.equalsIgnoreCase("active")) {
        newStatus = "inactive";
        confirmationMessage = "Are you sure you want to inactivate member with ID: " + memberId + "?";
    } else {
        newStatus = "active";
        confirmationMessage = "Are you sure you want to activate member with ID: " + memberId + "?";
    }

    int confirm = JOptionPane.showConfirmDialog(this,
        confirmationMessage,
        "Confirm Status Change",
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        updateMemberStatus(memberId, newStatus);
    }
    }//GEN-LAST:event_StatusChangeActionPerformed

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

        String sql = "INSERT INTO members (member_id, member_name, tp_number, address, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setString(1, memberId);
        pst.setString(2, memberName);
        pst.setString(3, tpNumber);
        pst.setString(4, address);
        pst.setString(5, email);
        
        // Debug print the SQL
        System.out.println("Executing SQL: " + pst.toString());
        
        int result = pst.executeUpdate();
        System.out.println("Rows affected: " + result);
        
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "member Added Successfully!");
            // Clear fields
            MemberID.setText("");
            MemberName.setText("");
            Address.setText("");
            TPNumber.setText("");
            EMail.setText("");
            
            // Refresh the table
            fetchAndDisplayMembers("active");
            
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add member!");
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

    private void TableMembersAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TableMembersAncestorAdded
        fetchAndDisplayMembers("active");
    }//GEN-LAST:event_TableMembersAncestorAdded

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        String searchId = SeachMembers.getText().trim();
    
    if (searchId.isEmpty()) {
        // If search field is empty, use clearSearch to show all records
        clearSearch();
        return;
    }

    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        String sql = "SELECT member_id, member_name, tp_number, email, address, created_at, status FROM members WHERE member_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, searchId);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) TableMembers.getModel();
        model.setRowCount(0); // Clear existing rows

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = {
                rs.getString("member_id"),
                rs.getString("member_name"),
                rs.getString("tp_number"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("created_at"),
                rs.getString("status")
            };
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No member found with ID: " + searchId);
            // After showing error, clear search and show all records
            clearSearch();
        }

        // Close connections
        rs.close();
        pst.close();
        con.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error searching data: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_SearchActionPerformed

    private void ShowActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowActiveActionPerformed
        fetchAndDisplayMembers("active");
    }//GEN-LAST:event_ShowActiveActionPerformed

    private void ShowInactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowInactiveActionPerformed
        fetchAndDisplayMembers("inactive");
    }//GEN-LAST:event_ShowInactiveActionPerformed

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
            java.util.logging.Logger.getLogger(addMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addMember().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Address;
    private javax.swing.JButton Close;
    private javax.swing.JTextField EMail;
    private javax.swing.JButton Edit;
    private javax.swing.JTextField MemberID;
    private javax.swing.JTextField MemberName;
    private javax.swing.JButton Save;
    private javax.swing.JTextField SeachMembers;
    private javax.swing.JButton Search;
    private javax.swing.JButton ShowActive;
    private javax.swing.JButton ShowInactive;
    private javax.swing.JButton StatusChange;
    private javax.swing.JTextField TPNumber;
    private javax.swing.JTable TableMembers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
