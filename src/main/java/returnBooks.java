
import ConnectionProvider.ConnectionProvider;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class returnBooks extends javax.swing.JFrame {

    public returnBooks() {
        initComponents();
        setupReturnedBookListener();
        
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
    
    private void setupReturnedBookListener() {
    // Add listener for StudentID
    MemberID.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            // Use SwingUtilities.invokeLater to avoid mutation during notification
            SwingUtilities.invokeLater(() -> fetchReturnedBook());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> fetchReturnedBook());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> fetchReturnedBook());
        }
    });

    // Add listener for BookID
    BookID.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> fetchReturnedBook());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> fetchReturnedBook());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            SwingUtilities.invokeLater(() -> fetchReturnedBook());
        }
    });
}
    
    private void fetchReturnedBook() {
        
    // Debug prints to check the actual content of text fields
    System.out.println("Raw MemberID text: '" + MemberID2.getText() + "'");
    System.out.println("Raw BookID text: '" + BookID.getText() + "'");
    
    String memberID = MemberID2.getText().trim();
    String bookID = BookID.getText().trim();
    
     System.out.println("After trim - Student ID: '" + memberID + "', Book ID: '" + bookID + "'");
    
    if (memberID.isEmpty() || bookID.isEmpty()) {
        SwingUtilities.invokeLater(() -> clearFields());
        return;
    }
    
    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }
        
        // Modified query to show if any records exist
        String checkSql = "SELECT COUNT(*) as count FROM issued_books WHERE member_id = ? AND book_id = ?";
        PreparedStatement checkPst = con.prepareStatement(checkSql);
        checkPst.setString(1, memberID);
        checkPst.setString(2, bookID);
        ResultSet checkRs = checkPst.executeQuery();
        if (checkRs.next()) {
            System.out.println("Number of matching records: " + checkRs.getInt("count")); // Debug print
        }
        
        String sql = "SELECT member_name, book_name, issued_date, due_date " +
                     "FROM issued_books " +
                     "WHERE member_id = ? AND book_id = ?";
                    
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, memberID);
        pst.setString(2, bookID);
        
        System.out.println("Executing query: " + sql); // Debug print
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            String memberName = rs.getString("member_name");
            String bookName = rs.getString("book_name");
            
            System.out.println("Found record - Member: " + memberName + ", Book: " + bookName); // Debug print
            
            java.sql.Date issuedDate = rs.getDate("issued_date");
            java.sql.Date dueDate = rs.getDate("due_date");
            
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            
            String formattedIssuedDate = issuedDate != null ? dateFormat.format(issuedDate) : "";
            String formattedDueDate = dueDate != null ? dateFormat.format(dueDate) : "";
            
            SwingUtilities.invokeLater(() -> {
                MemberName.setText(memberName);
                BookTitle.setText(bookName);
                IssueDate.setText(formattedIssuedDate);
                DueDate.setText(formattedDueDate);
            });
        } else {
            System.out.println("No records found for the given IDs"); // Debug print
            SwingUtilities.invokeLater(() -> clearFields());
            JOptionPane.showMessageDialog(this, 
                "No issued book found for Student ID: " + memberID + 
                " and Book ID: " + bookID);
        }
        
        checkRs.close();
        checkPst.close();
        rs.close();
        pst.close();
        con.close();
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
        e.printStackTrace(); // Added stack trace for more detailed error info
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
}

private void clearFields() {
    MemberID2.setText("");
    BookID.setText("");
    MemberName.setText("");
    BookTitle.setText("");
    IssueDate.setText("");
    DueDate.setText("");
    ReturnedDate.setDate(null);
}
    
    private void fetchAndDisplayReturnedBooks() {
    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        String sql = "SELECT member_id, member_name, book_id, book_name, " +
                     "issue_date, due_date, return_date " +  // Changed issue_date to issued_date
                     "FROM returned_books ORDER BY return_date DESC";
        
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        // Create a DefaultTableModel with column names
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Member ID", "Member Name", "Book ID", "Book Name", 
                "Issue Date", "Due Date", "Return Date"
            }
        ) {
            // Make cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Set the new model to the table
        TableReturedBooks.setModel(model);

        // Format dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Add rows to the table model
        while (rs.next()) {
            // Get values from ResultSet
            String memberId = rs.getString("member_id");
            String memberName = rs.getString("member_name");
            String bookId = rs.getString("book_id");
            String bookName = rs.getString("book_name");
            
            // Format dates
            java.sql.Date issueDate = rs.getDate("issue_date");
            java.sql.Date dueDate = rs.getDate("due_date");
            java.sql.Date returnDate = rs.getDate("return_date");
            
            String formattedIssueDate = issueDate != null ? dateFormat.format(issueDate) : "";
            String formattedDueDate = dueDate != null ? dateFormat.format(dueDate) : "";
            String formattedReturnDate = returnDate != null ? dateFormat.format(returnDate) : "";
            
            // Add row to table model
            model.addRow(new Object[]{
                memberId, memberName, bookId, bookName,
                formattedIssueDate, formattedDueDate, formattedReturnDate
            });
        }

        // Close resources
        rs.close();
        pst.close();
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
        fetchAndDisplayReturnedBooks();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MemberID = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BookID = new javax.swing.JTextField();
        MemberName = new javax.swing.JTextField();
        BookTitle = new javax.swing.JTextField();
        IssueDate = new javax.swing.JTextField();
        DueDate = new javax.swing.JTextField();
        ReturnedDate = new com.toedter.calendar.JDateChooser();
        Save = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        SeachIssuedBooks = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        ShowAll = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableReturedBooks = new javax.swing.JTable();
        MemberID2 = new javax.swing.JTextField();

        MemberID.setBackground(new java.awt.Color(255, 255, 255));
        MemberID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        MemberID.setForeground(new java.awt.Color(255, 51, 51));
        MemberID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        MemberID.setCaretColor(new java.awt.Color(255, 51, 51));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("Returned Books");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(260, 260, 260))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(21, 21, 21))
        );

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Issue Date");

        jLabel12.setBackground(new java.awt.Color(255, 51, 51));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Retrun Date");

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Due date");

        jLabel5.setBackground(new java.awt.Color(255, 51, 51));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Book ID");

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Book Title");

        jLabel8.setBackground(new java.awt.Color(255, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Member Name");

        jLabel7.setBackground(new java.awt.Color(255, 51, 51));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Member ID");

        BookID.setBackground(new java.awt.Color(255, 255, 255));
        BookID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookID.setForeground(new java.awt.Color(0, 0, 0));
        BookID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        BookID.setCaretColor(new java.awt.Color(255, 51, 51));
        BookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookIDActionPerformed(evt);
            }
        });

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

        IssueDate.setBackground(new java.awt.Color(255, 255, 255));
        IssueDate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        IssueDate.setForeground(new java.awt.Color(0, 0, 0));
        IssueDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        IssueDate.setCaretColor(new java.awt.Color(255, 51, 51));
        IssueDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssueDateActionPerformed(evt);
            }
        });

        DueDate.setBackground(new java.awt.Color(255, 255, 255));
        DueDate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        DueDate.setForeground(new java.awt.Color(0, 0, 0));
        DueDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        DueDate.setCaretColor(new java.awt.Color(255, 51, 51));
        DueDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DueDateActionPerformed(evt);
            }
        });

        ReturnedDate.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ReturnedDateAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
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

        SeachIssuedBooks.setBackground(new java.awt.Color(255, 255, 255));
        SeachIssuedBooks.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SeachIssuedBooks.setForeground(new java.awt.Color(255, 51, 51));
        SeachIssuedBooks.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        SeachIssuedBooks.setCaretColor(new java.awt.Color(255, 51, 51));

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

        TableReturedBooks.setBackground(new java.awt.Color(255, 255, 255));
        TableReturedBooks.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        TableReturedBooks.setForeground(new java.awt.Color(255, 51, 51));
        TableReturedBooks.setModel(new javax.swing.table.DefaultTableModel(
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
        TableReturedBooks.setGridColor(new java.awt.Color(255, 51, 51));
        TableReturedBooks.setSelectionBackground(new java.awt.Color(255, 51, 51));
        TableReturedBooks.setSelectionForeground(new java.awt.Color(255, 255, 255));
        TableReturedBooks.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TableReturedBooksAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(TableReturedBooks);

        MemberID2.setBackground(new java.awt.Color(255, 255, 255));
        MemberID2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        MemberID2.setForeground(new java.awt.Color(0, 0, 0));
        MemberID2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        MemberID2.setCaretColor(new java.awt.Color(255, 51, 51));
        MemberID2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MemberID2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BookID)
                    .addComponent(MemberName)
                    .addComponent(BookTitle)
                    .addComponent(IssueDate)
                    .addComponent(DueDate)
                    .addComponent(ReturnedDate, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MemberID2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SeachIssuedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(MemberID2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BookID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MemberName))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(BookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(IssueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(DueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(ReturnedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SeachIssuedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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

    private void BookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookIDActionPerformed

    private void MemberNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MemberNameActionPerformed

    private void BookTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookTitleActionPerformed

    private void IssueDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssueDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IssueDateActionPerformed

    private void DueDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DueDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DueDateActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
       try {
        // Get all values from fields
        String memberID = MemberID2.getText().trim();
        String memberName = MemberName.getText().trim();
        String bookID = BookID.getText().trim();
        String bookName = BookTitle.getText().trim();
        
        // Validate input dates
        if (IssueDate.getText().trim().equals("") || 
            DueDate.getText().trim().equals("") || 
            ReturnedDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please ensure all date fields are properly filled!");
            return;
        }

        // Parse and format dates properly
        SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        // Convert string dates to java.sql.Date
        java.sql.Date issueDate = null;
        java.sql.Date dueDate = null;
        java.sql.Date returnDate = null;
        
        try {
            java.util.Date parsedIssueDate = displayFormat.parse(IssueDate.getText().trim());
            java.util.Date parsedDueDate = displayFormat.parse(DueDate.getText().trim());
            
            issueDate = new java.sql.Date(parsedIssueDate.getTime());
            dueDate = new java.sql.Date(parsedDueDate.getTime());
            returnDate = new java.sql.Date(ReturnedDate.getDate().getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd format");
            return;
        }

        // Check if connection is successful
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        // Prepare SQL statement for insertion
        String sql = "INSERT INTO returned_books (member_id, member_name, book_id, book_name, " +
                    "issue_date, due_date, return_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
        
        // Set values in prepared statement
        pst.setString(1, memberID);
        pst.setString(2, memberName);
        pst.setString(3, bookID);
        pst.setString(4, bookName);
        pst.setDate(5, issueDate);    // Changed to setDate instead of setString
        pst.setDate(6, dueDate);      // Changed to setDate instead of setString
        pst.setDate(7, returnDate);   // Changed to setDate instead of setString

        // Execute the insert
        pst.executeUpdate();
        
        // Show success message
        JOptionPane.showMessageDialog(this, "Book return recorded successfully!");
        
        // Clear all fields
        clearFields();
        
        // Refresh the table
        fetchAndDisplayReturnedBooks();
        
        // Close the prepared statement
        pst.close();
        con.close();
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_SaveActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new home().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
       String searchTerm = SeachIssuedBooks.getText().trim();

    if (searchTerm.isEmpty()) {
        // If search field is empty, show all records
        fetchAndDisplayReturnedBooks();
        return;
    }

    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        // Fixed SQL query to search in returned_books table and match the table structure
        String sql = "SELECT member_id, member_name, book_id, book_name, " +
                     "issue_date, due_date, return_date " +
                     "FROM returned_books " +
                     "WHERE member_id LIKE ? OR member_name LIKE ? OR book_id LIKE ? OR book_name LIKE ?";

        PreparedStatement pst = con.prepareStatement(sql);

        // Set search parameters with wildcards for partial matching
        String searchPattern = "%" + searchTerm + "%";
        pst.setString(1, searchPattern);
        pst.setString(2, searchPattern);
        pst.setString(3, searchPattern);
        pst.setString(4, searchPattern);

        ResultSet rs = pst.executeQuery();

        // Get the table model and clear existing rows
        DefaultTableModel model = (DefaultTableModel) TableReturedBooks.getModel();
        model.setRowCount(0);

        // Format dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        boolean found = false;
        while (rs.next()) {
            found = true;
            
            // Format dates properly
            java.sql.Date issueDate = rs.getDate("issue_date");
            java.sql.Date dueDate = rs.getDate("due_date");
            java.sql.Date returnDate = rs.getDate("return_date");
            
            String formattedIssueDate = issueDate != null ? dateFormat.format(issueDate) : "";
            String formattedDueDate = dueDate != null ? dateFormat.format(dueDate) : "";
            String formattedReturnDate = returnDate != null ? dateFormat.format(returnDate) : "";

            // Add row with all the correct columns
            Object[] row = {
                rs.getString("member_id"),
                rs.getString("member_name"),
                rs.getString("book_id"),
                rs.getString("book_name"),
                formattedIssueDate,
                formattedDueDate,
                formattedReturnDate,

            };
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No records found for: " + searchTerm);
            // Optionally show all records after showing no results message
            fetchAndDisplayReturnedBooks();
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

    private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed
        clearSearch();
    }//GEN-LAST:event_ShowAllActionPerformed

    private void TableReturedBooksAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TableReturedBooksAncestorAdded
        fetchAndDisplayReturnedBooks();
    }//GEN-LAST:event_TableReturedBooksAncestorAdded

    private void ReturnedDateAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ReturnedDateAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_ReturnedDateAncestorAdded

    private void MemberID2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberID2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MemberID2ActionPerformed

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
            java.util.logging.Logger.getLogger(returnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(returnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(returnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(returnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new returnBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BookID;
    private javax.swing.JTextField BookTitle;
    private javax.swing.JButton Close;
    private javax.swing.JTextField DueDate;
    private javax.swing.JTextField IssueDate;
    private javax.swing.JTextField MemberID;
    private javax.swing.JTextField MemberID2;
    private javax.swing.JTextField MemberName;
    private com.toedter.calendar.JDateChooser ReturnedDate;
    private javax.swing.JButton Save;
    private javax.swing.JTextField SeachIssuedBooks;
    private javax.swing.JButton Search;
    private javax.swing.JButton ShowAll;
    private javax.swing.JTable TableReturedBooks;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
