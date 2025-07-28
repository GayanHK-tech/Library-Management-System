
import ConnectionProvider.ConnectionProvider;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class addBooks extends javax.swing.JFrame {

    public addBooks() {
        initComponents();
        setupTable();
        fetchAndDisplayBooks();
        
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
    String[] columnNames = {"Book ID", "Book Title", "Author", "Language", "Copies Available", "Genre", "Format"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    TableBooks.setModel(model);
}

    // Add this method to fetch and display data
    private void fetchAndDisplayBooks() {
    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        String sql = "SELECT book_id, book_title, author, language, copies_available, genre, format FROM books";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        // Get the table model and clear existing rows
        DefaultTableModel model = (DefaultTableModel) TableBooks.getModel();
        model.setRowCount(0);

        // Add rows to table
        while (rs.next()) {
            Object[] row = {
                rs.getString("book_id"),
                rs.getString("book_title"),
                rs.getString("author"),
        //rs.getString("publication_year"),
                rs.getString("language"),
                rs.getString("copies_available"),
                rs.getString("genre"),
                rs.getString("format")
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
    
     private void clearSearch() {
        SeachBooks.setText("");
        fetchAndDisplayBooks();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Save = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        SeachBooks = new javax.swing.JTextField();
        Language = new javax.swing.JTextField();
        BookTitle = new javax.swing.JTextField();
        Author = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CopiesAvailable = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableBooks = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Search = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Genre = new javax.swing.JComboBox<>();
        Format = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        ShowAll = new javax.swing.JButton();

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

        SeachBooks.setBackground(new java.awt.Color(255, 255, 255));
        SeachBooks.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SeachBooks.setForeground(new java.awt.Color(255, 51, 51));
        SeachBooks.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        SeachBooks.setCaretColor(new java.awt.Color(255, 51, 51));
        SeachBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeachBooksActionPerformed(evt);
            }
        });

        Language.setBackground(new java.awt.Color(255, 255, 255));
        Language.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Language.setForeground(new java.awt.Color(0, 0, 0));
        Language.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        Language.setCaretColor(new java.awt.Color(255, 51, 51));
        Language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LanguageActionPerformed(evt);
            }
        });

        BookTitle.setBackground(new java.awt.Color(255, 255, 255));
        BookTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BookTitle.setForeground(new java.awt.Color(0, 0, 0));
        BookTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        BookTitle.setCaretColor(new java.awt.Color(255, 51, 51));

        Author.setBackground(new java.awt.Color(255, 255, 255));
        Author.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Author.setForeground(new java.awt.Color(0, 0, 0));
        Author.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        Author.setCaretColor(new java.awt.Color(255, 51, 51));
        Author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AuthorActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 51, 51));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Author");

        CopiesAvailable.setBackground(new java.awt.Color(255, 255, 255));
        CopiesAvailable.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CopiesAvailable.setForeground(new java.awt.Color(0, 0, 0));
        CopiesAvailable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        CopiesAvailable.setCaretColor(new java.awt.Color(255, 51, 51));
        CopiesAvailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopiesAvailableActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 51, 51));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Genre");

        TableBooks.setBackground(new java.awt.Color(255, 255, 255));
        TableBooks.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        TableBooks.setForeground(new java.awt.Color(255, 51, 51));
        TableBooks.setModel(new javax.swing.table.DefaultTableModel(
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
        TableBooks.setGridColor(new java.awt.Color(255, 51, 51));
        TableBooks.setSelectionBackground(new java.awt.Color(255, 51, 51));
        TableBooks.setSelectionForeground(new java.awt.Color(255, 255, 255));
        TableBooks.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TableBooksAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(TableBooks);

        jLabel7.setBackground(new java.awt.Color(255, 51, 51));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Title");

        jLabel8.setBackground(new java.awt.Color(255, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Language");

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Copies Available");

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

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Format");

        Genre.setBackground(new java.awt.Color(255, 255, 255));
        Genre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Genre.setForeground(new java.awt.Color(255, 51, 51));
        Genre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Format.setBackground(new java.awt.Color(255, 255, 255));
        Format.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Format.setForeground(new java.awt.Color(255, 51, 51));
        Format.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("Add Books");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(313, 313, 313))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(20, 20, 20))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CopiesAvailable)
                    .addComponent(Author)
                    .addComponent(Language)
                    .addComponent(BookTitle)
                    .addComponent(Genre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Format, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SeachBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SeachBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Author, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CopiesAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Format, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(24, 24, 24)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
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

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        setVisible(false);
        new editBooks().setVisible(true);
    }//GEN-LAST:event_EditActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new home().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

    private void AuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AuthorActionPerformed
       
    }//GEN-LAST:event_AuthorActionPerformed

    private void CopiesAvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopiesAvailableActionPerformed
        
    }//GEN-LAST:event_CopiesAvailableActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
       try {
        // Get values from form fields
//        String bookId = BookID.getText();
        String bookTitle = BookTitle.getText();
        String author = Author.getText();
//        String publicationYear = PublicationYear.getText();
        String language = Language.getText();
        String copiesAvailable = CopiesAvailable.getText();
        String genre = Genre.getSelectedItem().toString();
        String format = Format.getSelectedItem().toString();
        
        // Basic validation
        if (bookTitle.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Book ID, Title and Author are required fields!");
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
            String sql = "INSERT INTO books (book_title, author, language, " +
                        "copies_available, genre, format) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            // Set values in prepared statement
//            pst.setString(1, bookId);
            pst.setString(1, bookTitle);
            pst.setString(2, author);
//            pst.setString(4, publicationYear);
            pst.setString(3, language);
            pst.setString(4, copiesAvailable);
            pst.setString(5, genre);
            pst.setString(6, format);
            
            // Debug print the SQL
            System.out.println("Executing SQL: " + pst.toString());
        
            int result = pst.executeUpdate();
            System.out.println("Rows affected: " + result);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Book Added Successfully!");
                // Clear fields without triggering any additional events
                
                // Refresh the table immediately after saving
                 fetchAndDisplayBooks();
            
//                BookID.setText("");
                BookTitle.setText("");
                Author.setText("");
//                PublicationYear.setText("");
                Language.setText("");
                CopiesAvailable.setText("");
                // Safely set combo box values
                try {
                    Genre.setSelectedIndex(0);
                    Format.setSelectedIndex(0);
                } catch (Exception e) {
                    // Ignore combo box errors
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add book!");
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

    private void SeachBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeachBooksActionPerformed
     
    }//GEN-LAST:event_SeachBooksActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
 String searchId = SeachBooks.getText().trim();

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

        String sql = "SELECT book_id, book_title, author, language, copies_available, genre, format FROM books WHERE book_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, searchId);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) TableBooks.getModel();
        model.setRowCount(0); // Clear existing rows

        boolean found = false;
        while (rs.next()) {
            found = true;
            Object[] row = {
                rs.getString("book_id"),
                rs.getString("book_title"),
                rs.getString("author"),
//                rs.getString("publication_year"),
                rs.getString("language"),
                rs.getString("copies_available"),
                rs.getString("genre"),
                rs.getString("format")
            };
            model.addRow(row);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No book found with ID: " + searchId);
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

    private void TableBooksAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TableBooksAncestorAdded
        fetchAndDisplayBooks();
    }//GEN-LAST:event_TableBooksAncestorAdded

    private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed
        clearSearch();
    }//GEN-LAST:event_ShowAllActionPerformed

    private void LanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LanguageActionPerformed

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
            java.util.logging.Logger.getLogger(addBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Author;
    private javax.swing.JTextField BookTitle;
    private javax.swing.JButton Close;
    private javax.swing.JTextField CopiesAvailable;
    private javax.swing.JButton Edit;
    private javax.swing.JComboBox<String> Format;
    private javax.swing.JComboBox<String> Genre;
    private javax.swing.JTextField Language;
    private javax.swing.JButton Save;
    private javax.swing.JTextField SeachBooks;
    private javax.swing.JButton Search;
    private javax.swing.JButton ShowAll;
    private javax.swing.JTable TableBooks;
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
