package view;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import control.NoteControl;
import entity.SQLiteConnection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.util.Date;
import model.Note;
import model.tablemodel.TableModelNote;
import util.DateUtil;
import util.NoteTray;
import java.sql.Connection;

public class Main extends javax.swing.JFrame {

    private NoteControl noteCtr;
    private DateUtil dateUtil;
    private TableModelNote model;
    private Note newNote;
    private NoteTray noteTray;
    private SQLiteConnection sqliteConnection;
    
    public Main() {
        initComponents();
        
        SQLiteConnection sqliteConnection = new SQLiteConnection("C:/AFDeveloper/DBnote.db");
        Connection connection = sqliteConnection.getConnection();
        
        this.noteCtr = new NoteControl();
        this.dateUtil = new DateUtil();
        this.model = (TableModelNote) tnNotes.getModel();
        this.noteTray = new NoteTray(this, connection);
        this.sqliteConnection = new SQLiteConnection("C:/AFDeveloper/DBnote.db"); 
        
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        noteTray.createNoteTray();
        
        txDate.setDate(new Date());
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setTableData();
    }
    
    private void setTableData(){
        model.setNotes(noteCtr.getNotes());
    }
    
    private Note getNoteScreen(){
        Note note = new Note();
        note.setName(txName.getText());
        note.setDescription(txaDescription.getText());
        note.setAlarm(cbActivateAlarm.isSelected() ? 1 : 0);
        note.setDateTimeAlarm(new Timestamp(dateUtil.convertToDate(txDate.getDate(), txHour.getText()).getTime()));
        
        return note;
    }

    private void clean() {
        txName.setText("");
        txaDescription.setText("");
        cbActivateAlarm.setSelected(false);
        txDate.setDate(new Date());
        txHour.setText("00:00");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        cbActivateAlarm = new javax.swing.JCheckBox();
        btInclude = new javax.swing.JButton();
        txDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txHour = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tnNotes = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btEdit = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nova nota"));

        jLabel1.setText("Nome:");

        jLabel2.setText("Descrição:");

        txaDescription.setColumns(20);
        txaDescription.setRows(5);
        jScrollPane1.setViewportView(txaDescription);

        cbActivateAlarm.setText("Ativar Alarme");

        btInclude.setText("Incluir");
        btInclude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncludeActionPerformed(evt);
            }
        });

        txDate.setDateFormatString("dd/MM/yyyy");

        jLabel3.setText("Data Agendamento");

        try {
            txHour.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txHour.setText("00:00  ");

        jLabel4.setText("Hora Agendamento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btInclude, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txName, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 454, Short.MAX_VALUE)
                                .addComponent(cbActivateAlarm))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txHour, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(txDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(txHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cbActivateAlarm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btInclude))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Notas cadastradas"));
        jPanel3.setLayout(new java.awt.BorderLayout());

        tnNotes.setModel(new TableModelNote());
        tnNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tnNotesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tnNotes);

        jPanel5.setPreferredSize(new java.awt.Dimension(75, 483));

        btEdit.setText("Editar");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        btDelete.setText("Excluir");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        jButton1.setText("Refresh");
        jButton1.setPreferredSize(new java.awt.Dimension(72, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btDelete)
                .addGap(18, 18, 18)
                .addComponent(btEdit)
                .addContainerGap(372, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btIncludeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncludeActionPerformed
        Note note = getNoteScreen();
        if(newNote == null) {
            noteCtr.save(note);
            JOptionPane.showMessageDialog(null, "Nota incluida com sucesso");
            model.addNote(note);
            clean();
        }else{
            note.setId(newNote.getId());
            noteCtr.update(note);
            JOptionPane.showMessageDialog(null, "Nota atualizada com sucesso");
            model.updateNote(note);
            newNote = null;
        }
    }//GEN-LAST:event_btIncludeActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        int rowSelected = tnNotes.getSelectedRow();
        if(rowSelected != -1) {
            int option = JOptionPane.showInternalConfirmDialog(null, "Tem certeza que deseja excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION);
            if(option == 0){
                Note note = model.getNote(rowSelected);
                noteCtr.delete(note);
                model.deleteNote(rowSelected);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, selecionar um registro");
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        int rowSelected = tnNotes.getSelectedRow();
        
        if(rowSelected != -1) {
            newNote = model.getNote(rowSelected);
            loadData(newNote);
        }
    }//GEN-LAST:event_btEditActionPerformed

    private void tnNotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tnNotesMouseClicked
        if(evt.getClickCount() == 2){
            btEditActionPerformed(null);
        }
    }//GEN-LAST:event_tnNotesMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        noteTray.minimize();
        sqliteConnection.closeConnection();
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        model.reflashNote();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void loadData(Note newNote){
        txName.setText(newNote.getName());
        cbActivateAlarm.setSelected(newNote.getAlarm() == 1);
        txDate.setDate(newNote.getDateTimeAlarm());
        txHour.setText(dateUtil.getTimeFormatted(newNote.getDateTimeAlarm()));
        txaDescription.setText(newNote.getDescription());
    }
    

    
    
    
    public static void main(String args[]) {
        
        try {
            FlatArcDarkIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btInclude;
    private javax.swing.JCheckBox cbActivateAlarm;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tnNotes;
    private com.toedter.calendar.JDateChooser txDate;
    private javax.swing.JFormattedTextField txHour;
    private javax.swing.JTextField txName;
    private javax.swing.JTextArea txaDescription;
    // End of variables declaration//GEN-END:variables
}
