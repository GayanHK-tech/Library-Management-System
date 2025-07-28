
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

public class editBooks extends javax.swing.JFrame {

    public editBooks() {
        initComponents();
        setupBookEditListener();
        
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
    
    private void setupBookEditListener() {
    BookID.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            fetchBookEdit();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            fetchBookEdit();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            fetchBookEdit();
        }
    });
}
    
    private void fetchBookEdit() {
    String bookId = BookID.getText();
    try {
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }
        
        String sql = "SELECT book_title, author, language, " +
                     "copies_available, genre, format FROM books WHERE book_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, bookId);
        
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            BookTitle.setText(rs.getString("book_title"));
            Author.setText(rs.getString("author"));
//            PublicationYear.setText(rs.getString("publication_year"));
            Language.setText(rs.getString("language"));
            CopiesAvailable.setText(rs.getString("copies_available"));
            
            // Set combo box values
            String genre = rs.getString("genre");
            String format = rs.getString("format");
            
            // Find and set the matching genre
            for (int i = 0; i < Genre.getItemCount(); i++) {
                if (Genre.getItemAt(i).equalsIgnoreCase(genre)) {
                    Genre.setSelectedIndex(i);
                    break;
                }
            }
            
            // Find and set the matching format
            for (int i = 0; i < Format.getItemCount(); i++) {
                if (Format.getItemAt(i).equalsIgnoreCase(format)) {
                    Format.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            // Clear fields if no book found
            BookTitle.setText("");
            Author.setText("");
//            PublicationYear.setText("");
            Language.setText("");
            CopiesAvailable.setText("");
            Genre.setSelectedIndex(0);
            Format.setSelectedIndex(0);
        }
        
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Language = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        BookTitle = new javax.swing.JTextField();
        Author = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CopiesAvailable = new javax.swing.JTextField();
        Genre = new javax.swing.JComboBox<>();
        Save = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Format = new javax.swing.JComboBox<>();
        Close = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        BookID = new javax.swing.JTextField();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 153, 255));
        jLabel13.setText("Edit Books");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(21, 21, 21))
        );

        Language.setBackground(new java.awt.Color(255, 255, 255));
        Language.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Language.setForeground(new java.awt.Color(0, 0, 0));
        Language.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        Language.setCaretColor(new java.awt.Color(255, 51, 51));

        jLabel9.setBackground(new java.awt.Color(255, 51, 51));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Copies Available");

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

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Format");

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

        Genre.setBackground(new java.awt.Color(255, 255, 255));
        Genre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Genre.setForeground(new java.awt.Color(255, 51, 51));
        Genre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        jLabel6.setText("Genre");

        Format.setBackground(new java.awt.Color(255, 255, 255));
        Format.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Format.setForeground(new java.awt.Color(255, 51, 51));
        Format.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        jLabel7.setText("Book Title");

        jLabel8.setBackground(new java.awt.Color(255, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Language");

        jLabel10.setBackground(new java.awt.Color(255, 51, 51));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Book ID");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Format, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Genre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CopiesAvailable)
                    .addComponent(Language)
                    .addComponent(BookTitle)
                    .addComponent(BookID)
                    .addComponent(Author, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(306, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
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

    private void AuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AuthorActionPerformed

    private void CopiesAvailableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopiesAvailableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CopiesAvailableActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        setVisible(false);
        new addBooks().setVisible(true);
    }//GEN-LAST:event_CloseActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
         try {
        // Get values from form fields
        String bookId = BookID.getText();
        String bookTitle = BookTitle.getText();
        String author = Author.getText();
//        String publicationYear = PublicationYear.getText();
        String language = Language.getText();
        String copiesAvailable = CopiesAvailable.getText();
        String genre = Genre.getSelectedItem().toString();
        String format = Format.getSelectedItem().toString();

        // Basic validation
        if (bookId.isEmpty() || bookTitle.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Book ID, Title and Author are required fields!");
            return;
        }

        // Check if connection is successful
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Database connection failed!");
            return;
        }

        // SQL for updating an existing record
        String sql = "UPDATE books SET book_title = ?, author = ?," +
                     "language = ?, copies_available = ?, genre = ?, format = ? WHERE book_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        // Set values in prepared statement
        pst.setString(1, bookTitle);
        pst.setString(2, author);
//        pst.setString(3, publicationYear);
        pst.setString(3, language);
        pst.setString(4, copiesAvailable);
        pst.setString(5, genre);
        pst.setString(6, format);
        pst.setString(7, bookId);

        // Execute the update
        int result = pst.executeUpdate();

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Book Updated Successfully!");
            // Clear fields
            BookID.setText("");
            BookTitle.setText("");
            Author.setText("");
//            PublicationYear.setText("");
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
            JOptionPane.showMessageDialog(this, "Failed to update book!");
        }

        // Close the prepared statement and connection
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

    private void BookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BookIDActionPerformed

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
            java.util.logging.Logger.getLogger(editBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Author;
    private javax.swing.JTextField BookID;
    private javax.swing.JTextField BookTitle;
    private javax.swing.JButton Close;
    private javax.swing.JTextField CopiesAvailable;
    private javax.swing.JComboBox<String> Format;
    private javax.swing.JComboBox<String> Genre;
    private javax.swing.JTextField Language;
    private javax.swing.JButton Save;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
