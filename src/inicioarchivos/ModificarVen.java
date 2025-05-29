package inicioarchivos;
/**
 *
 * @author markb
 */

import inicioarchivos.Ventana;
public class ModificarVen extends javax.swing.JFrame 
{
    public ModificarVen() 
    {
        initComponents();
        moreComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblA = new javax.swing.JLabel();
        txtDatoA = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblB = new javax.swing.JLabel();
        txtDatoA1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDatoA2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(filler1, java.awt.BorderLayout.WEST);
        getContentPane().add(filler3, java.awt.BorderLayout.SOUTH);

        jPanel1.setLayout(new java.awt.BorderLayout(10, 0));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Modificar Alumno");
        jPanel4.add(jLabel1);

        jPanel1.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel5.setAlignmentX(0.0F);
        jPanel5.setAlignmentY(0.0F);
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        lblA.setAlignmentY(0.0F);
        jPanel5.add(lblA);

        txtDatoA.setColumns(8);
        txtDatoA.setAlignmentX(0.0F);
        txtDatoA.setAlignmentY(0.0F);
        txtDatoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatoAActionPerformed(evt);
            }
        });
        jPanel5.add(txtDatoA);

        jPanel3.add(jPanel5);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.PAGE_AXIS));

        lblB.setAlignmentY(0.0F);
        jPanel6.add(lblB);

        txtDatoA1.setColumns(35);
        txtDatoA1.setAlignmentX(0.0F);
        txtDatoA1.setAlignmentY(0.0F);
        txtDatoA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatoA1ActionPerformed(evt);
            }
        });
        jPanel6.add(txtDatoA1);

        jPanel3.add(jPanel6);

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel4.setAlignmentY(0.0F);
        jPanel7.add(jLabel4);

        txtDatoA2.setColumns(2);
        txtDatoA2.setAlignmentX(0.0F);
        txtDatoA2.setAlignmentY(0.0F);
        txtDatoA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatoA2ActionPerformed(evt);
            }
        });
        jPanel7.add(txtDatoA2);

        jPanel3.add(jPanel7);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        btnCancelar.setText("Cancelar");
        jPanel2.add(btnCancelar);

        btnModificar.setText("Modificar");
        jPanel2.add(btnModificar);

        jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        getContentPane().add(filler2, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void moreComponents(){
        
    }
    private void txtDatoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatoAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoAActionPerformed

    private void txtDatoA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatoA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoA1ActionPerformed

    private void txtDatoA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatoA2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoA2ActionPerformed

    public static void main(String args[]) {
        ModificarVen mv = new ModificarVen();
        mv.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblB;
    private javax.swing.JTextField txtDatoA;
    private javax.swing.JTextField txtDatoA1;
    private javax.swing.JTextField txtDatoA2;
    // End of variables declaration//GEN-END:variables
}
