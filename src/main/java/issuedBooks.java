
import ConnectionProvider.ConnectionProvider;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class issuedBooks extends javax.swing.JFrame {

    public issuedBooks() {
        initComponents();
        setupStudentNameListener();
        setupBookTitleListener();
        fetchAndDisplayIssuedBooks();
       
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
    
    private void setupStudentNameListener() {
        MemberID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fetchStudentName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fetchStudentName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fetchStudentName();
            }
        });
    }
    
    private void setupBookTitleListener() {
        BookID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fetchBookTitle();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fetchBookTitle();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fetchBookTitle();
            }
        });
    }
    
private void fetchStudentName() {
        String studentID = MemberID.getText();
        try {
            // Check if connection is successful
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!");
                return;
            }

            // Prepare SQL statement to fetch member name
            String sql = "SELECT member_name FROM members WHERE member_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, studentID);

            // Execute the query
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String studentName = rs.getString("member_name");
                MemberName.setText(studentName);
                
            } else {
                MemberName.setText("");
            }

            // Close the prepared statement and result set
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

private void fetchBookTitle() {
        String bookID = BookID.getText();
        try {
            // Check if connection is successful
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!");
                return;
            }

            // Prepare SQL statement to fetch student name
            String sql = "SELECT book_title FROM books WHERE book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookID);

            // Execute the query
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String bookTitle = rs.getString("book_title");

                BookTitle.setText(bookTitle);

            } else {
                BookTitle.setText("");
            }

            // Close the prepared statement and result set
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

    private void fetchAndDisplayIssuedBooks() {
    try {
        // Check if connection is successful
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        // Prepare SQL statement to fetch specific columns, excluding timestamp
        String sql = "SELECT issued_id, member_id, member_name, book_id, book_name, issued_date, due_date FROM issued_books";
        PreparedStatement pst = con.prepareStatement(sql);

        // Execute the query
        ResultSet rs = pst.executeQuery();

        // Create a default table model
        DefaultTableModel model = (DefaultTableModel) TableIssuedBooks.getModel();
        // Clear existing rows
        model.setRowCount(0);

        // Column names (without timestamp)
        String[] columnNames = {"Issue ID", "Member ID", "Member Name", "Book ID", "Book Name", "Issue Date", "Due Date"};
        model.setColumnIdentifiers(columnNames);
            
        // Populate the table
        while (rs.next()) {
            String issuedID = rs.getString("issued_id");
            String studentID = rs.getString("member_id");
            String studentName = rs.getString("member_name");
            String bookID = rs.getString("book_id");
            String bookName = rs.getString("book_name");
            String issuedDate = rs.getString("issued_date");
            String dueDate = rs.getString("due_date");

            // Add row to the table
            Object[] row = {issuedID, studentID, studentName, bookID, bookName, issuedDate, dueDate};
            model.addRow(row);
        }

        // Close the prepared statement and result set
        pst.close();
        rs.close();
        con.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
}
    
     private void clearSearch() {
        SeachIssuedBooks.setText("");
        fetchAndDisplayIssuedBooks();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BookID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        MemberID = new javax.swing.JTextField();
        MemberName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BookTitle = new javax.swing.JTextField();
        Save = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableIssuedBooks = new javax.swing.JTable();
        Close = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        ShowAll = new javax.swing.JButton();
        Search = new javax.swing.JButton();
        SeachIssuedBooks = new javax.swing.JTextField();
        IssueDate = new com.toedter.calendar.JDateChooser();
        DueDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        BookID.setBackground(new java.awt.Color(255, 255, 255));
        BookID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookID.setForeground(new java.awt.Color(0, 0, 0));
        BookID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        BookID.setCaretColor(new java.awt.Color(255, 51, 51));

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Book Title");

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

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Due date");

        jLabel5.setBackground(new java.awt.Color(255, 51, 51));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Member Name");

        BookTitle.setBackground(new java.awt.Color(255, 255, 255));
        BookTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookTitle.setForeground(new java.awt.Color(0, 0, 0));
        BookTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        BookTitle.setCaretColor(new java.awt.Color(255, 51, 51));
        BookTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookTitleActionPerformed(evt);
            }
        });

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

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Issue Date");

        TableIssuedBooks.setBackground(new java.awt.Color(255, 255, 255));
        TableIssuedBooks.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        TableIssuedBooks.setForeground(new java.awt.Color(255, 51, 51));
        TableIssuedBooks.setModel(new javax.swing.table.DefaultTableModel(
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
        TableIssuedBooks.setGridColor(new java.awt.Color(255, 51, 51));
        TableIssuedBooks.setSelectionBackground(new java.awt.Color(255, 51, 51));
        TableIssuedBooks.setSelectionForeground(new java.awt.Color(255, 255, 255));
        TableIssuedBooks.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TableIssuedBooksAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(TableIssuedBooks);

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

        jLabel7.setBackground(new java.awt.Color(255, 51, 51));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Member ID");

        jLabel8.setBackground(new java.awt.Color(255, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Book ID");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("Issued Books");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        ShowAll.setBackground(new java.awt.Color(0, 0, 0));
        ShowAll.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ShowAll.setForeground(new java.awt.Color(255, 255, 255));
        ShowAll.setText("Show All");
        ShowAll.setBorder(null);
        ShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllActionPerformed(evt);
            }
        });

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

        SeachIssuedBooks.setBackground(new java.awt.Color(255, 255, 255));
        SeachIssuedBooks.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SeachIssuedBooks.setForeground(new java.awt.Color(255, 51, 51));
        SeachIssuedBooks.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        SeachIssuedBooks.setCaretColor(new java.awt.Color(255, 51, 51));

        IssueDate.setBackground(new java.awt.Color(255, 255, 255));
        IssueDate.setForeground(new java.awt.Color(255, 51, 51));

        DueDate.setBackground(new java.awt.Color(255, 255, 255));
        DueDate.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MemberName)
                    .addComponent(MemberID, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BookID)
                    .addComponent(BookTitle, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Save, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IssueDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(DueDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeachIssuedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SeachIssuedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MemberName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(IssueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(DueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
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

    private void MemberNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MemberNameActionPerformed

    private void BookTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookTitleActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new home().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

    private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed
         clearSearch();
    }//GEN-LAST:event_ShowAllActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        try {
            // Get values from form fields

            String memberID = MemberID.getText();
            String memberName = MemberName.getText();
            String bookID = BookID.getText();
            String bookTitle = BookTitle.getText();
            Date issuedDate = IssueDate.getDate();
            Date dueDate = DueDate.getDate();

            // Basic validation
            if (memberID.isEmpty() || memberName.isEmpty() || bookID.isEmpty() || bookTitle.isEmpty() || issuedDate == null || dueDate == null) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            // Check if connection is successful
            Connection con = ConnectionProvider.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!");
                return;
            }

            try {
                // Prepare SQL statement
                String sql = "INSERT INTO issued_books (member_id, member_name, book_id, book_name, issued_date, due_date) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);

                // Set values in prepared statement
                pst.setString(1, memberID);
                pst.setString(2, memberName);
                pst.setString(3, bookID);
                pst.setString(4, bookTitle);
                pst.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(issuedDate));
                pst.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(dueDate));

                // Debug print the SQL
               System.out.println("Executing SQL: " + pst.toString());
        
                int result = pst.executeUpdate();
                System.out.println("Rows affected: " + result);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Book Issued Successfully!");
                    // Clear fields without triggering any additional events
                    MemberID.setText("");
                    MemberName.setText("");
                    BookID.setText("");
                    BookTitle.setText("");
                    IssueDate.setDate(null);
                    DueDate.setDate(null);
                    
                    // Refresh the table
                   fetchAndDisplayIssuedBooks();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to issue book!");
                }

                // Close the prepared statement
                pst.close();
                con.close();

            } finally {
                // Always close the connection
                try {
                    if (con != null && !con.isClosed()) {
                        con.close();
                    }
                } catch (SQLException e) {
                    // Just log the error but don't show to user since data was saved
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void TableIssuedBooksAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TableIssuedBooksAncestorAdded
         fetchAndDisplayIssuedBooks();
    }//GEN-LAST:event_TableIssuedBooksAncestorAdded

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        String searchTerm = SeachIssuedBooks.getText().trim();  // Changed from SearchField to SearchField1 to match your UI

    if (searchTerm.isEmpty()) {
        // If search field is empty, show all records
        fetchAndDisplayIssuedBooks();
        return;
    }

    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        // Modified SQL to search in issued_books table and search across multiple fields
        String sql = "SELECT issued_id, member_id, member_name, book_id, book_name, issued_date, due_date " +
                    "FROM issued_books " +
                    "WHERE member_id LIKE ? OR member_name LIKE ?";
                    
        PreparedStatement pst = con.prepareStatement(sql);
        
        // Set search parameters with wildcards for partial matching
        String searchPattern = "%" + searchTerm + "%";
        pst.setString(1, searchPattern);
        pst.setString(2, searchPattern);
        
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) TableIssuedBooks.getModel();
        model.setRowCount(0); // Clear existing rows

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = {
                rs.getString("issued_id"),
                rs.getString("member_id"),
                rs.getString("member_name"),
                rs.getString("book_id"),
                rs.getString("book_name"),
                rs.getString("issued_date"),
                rs.getString("due_date")
            };
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No records found for: " + searchTerm);
            // After showing error, optionally show all records
            fetchAndDisplayIssuedBooks();
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
            java.util.logging.Logger.getLogger(issuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new issuedBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BookID;
    private javax.swing.JTextField BookTitle;
    private javax.swing.JButton Close;
    private com.toedter.calendar.JDateChooser DueDate;
    private com.toedter.calendar.JDateChooser IssueDate;
    private javax.swing.JTextField MemberID;
    private javax.swing.JTextField MemberName;
    private javax.swing.JButton Save;
    private javax.swing.JTextField SeachIssuedBooks;
    private javax.swing.JButton Search;
    private javax.swing.JButton ShowAll;
    private javax.swing.JTable TableIssuedBooks;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
