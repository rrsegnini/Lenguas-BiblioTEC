/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.rtrn;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import loan.AudiovisualLoan;
import loan.BookLoan;
import loan.Loan;

/**
 *
 * @author danielalvarado
 */
public class BookReturnForm extends javax.swing.JFrame {

    /**
     * Creates new form BookReturnForm
     */
    public BookReturnForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        studentIDbox = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        loanedMaterialTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        returnBookBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        studentIDbox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        studentIDbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studentIDboxKeyReleased(evt);
            }
        });

        loanedMaterialTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Author", "Year", "ISBN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(loanedMaterialTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Student ID:");

        returnBookBTN.setText("Return");
        returnBookBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnBookBTNMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(studentIDbox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(returnBookBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentIDbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnBookBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentIDboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentIDboxKeyReleased
        try{
            //loanedBooksTable.setModel(null);
            studentRegistration.Student student = null;
            student = file.StudentFile.getInstance().
                    getStudentByID(Integer.parseInt(studentIDbox.getText()));
            //loanedBooksTable
                  
            
        DefaultTableModel model = new DefaultTableModel();
        loanedMaterialTable.setModel(model);
        model.addColumn("Material ID"); 
        model.addColumn("Type"); 
        model.addColumn("Name"); 
        model.addColumn("Date");
        model.addColumn("ISBN/SerialNumber"); 

       
        /*List<loan.Loan> loans = domain.Library.getInstance()
                .getLoansByStudentID(student.getID());*/
       List<loan.Loan> loans = 
               domain.Library.getInstance().getLoansByStudentID(student.getID());
       /*loans = 
               domain.Library.getInstance().getLoansFromFile();*/
        System.out.println(loans.size());
        
        
        
        for (int i = 0; i < loans.size(); i++){ 
            if (loans.get(i) instanceof loan.BookLoan ){
                if (loans.get(i).getState()){

                    BookLoan bloan;
                    bloan = (BookLoan)loans.get(i);
                    materialRegistration.Book book = bloan.getBookLoaned();


                    model.addRow(new Object[]{loans.get(i).getID(),
                        "Book", book.getName() + " by "
                            + book.getAuthor(), bloan.getDate(), book.getISBN()});

                }
            }else if (loans.get(i) instanceof loan.AudiovisualLoan && loans.get(i).getState())
                {

                    loan.AudiovisualLoan AVloan;
                    AVloan = (loan.AudiovisualLoan)loans.get(i);
                    materialRegistration.Audiovisual AV = 
                            AVloan.getAudioVisualLoaned();

                    model.addRow(new Object[]{loans.get(i).getID(),
                        "Audiovisual", AV.getBrand() + " "
                            + AV.getModel(), AVloan.getDate(), 
                            AV.getSerialNumber()});

                }/*else{
                    System.out.println("Diay no");
                }*/
            }
        loanedMaterialTable.setModel(model);
        

        
        
        }catch(Exception e){
            System.err.println(e.toString());
            System.out.println("Not yet");
        }
    }//GEN-LAST:event_studentIDboxKeyReleased

    private void returnBookBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnBookBTNMouseClicked
        try{
        Date today = new Date();
        int rowSelected = this.loanedMaterialTable.getSelectedRow();
        
           if (rowSelected != -1) {
               int ID = 
                        Integer.parseInt(loanedMaterialTable.getValueAt
                                (loanedMaterialTable.getSelectedRow(), 0)
                                .toString());
               
                String type = 
                        loanedMaterialTable.getValueAt
                                (loanedMaterialTable.getSelectedRow(), 1)
                                .toString();
                String ISBN_Serial = 
                        loanedMaterialTable.getValueAt
                                (loanedMaterialTable.getSelectedRow(), 4)
                                .toString();
                
                List<Loan> loans = 
                        domain.Library.getInstance()
                .getLoansByStudentID(Integer.parseInt(studentIDbox.getText()));
                
                studentRegistration.Student student = file.StudentFile.getInstance().
                    getStudentByID(Integer.parseInt(studentIDbox.getText()));
                

                for (int i = 0; i < loans.size(); i++){
                    if (loans.get(i).getID() == ID && loans.get(i) instanceof loan.BookLoan){
                        materialRegistration.Book b = ((BookLoan)loans.get(i)).getBookLoaned();
                        loans.get(i).setState(false);
                        
                        file.LoanFile f = new file.LoanFile(loans);
                        
                        f.saveData();
                        
                        loan.BookReturn bookRet = 
                                new loan.BookReturn(ID, student,today, b);
                        
                    }else if (loans.get(i).getID() == ID && loans.get(i) instanceof loan.AudiovisualLoan)
                    {
                        materialRegistration.Audiovisual av = ((AudiovisualLoan)loans.get(i)).getAudioVisualLoaned();
                        loans.get(i).setState(false);
                        
                        file.LoanFile f = new file.LoanFile(loans);
                        
                        f.saveData();
                        
                        loan.AudiovisualReturn bookRet = 
                                new loan.AudiovisualReturn(ID, student,today, av);
                        System.out.println("Listo");
                    }
                }
    
            
        }
        }catch(Exception e){
        }
    }//GEN-LAST:event_returnBookBTNMouseClicked

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
            java.util.logging.Logger.getLogger(BookReturnForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookReturnForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookReturnForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookReturnForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookReturnForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable loanedMaterialTable;
    private javax.swing.JButton returnBookBTN;
    private javax.swing.JTextField studentIDbox;
    // End of variables declaration//GEN-END:variables
}
