package inicioarchivos;
/**
 *
 * @author markb
 */

import java.awt.*;
import javax.swing.*;
import inicioarchivos.Ventana;
public class ModificarVen extends JFrame
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
        pnlTODO = new javax.swing.JPanel();
        pnlData = new javax.swing.JPanel();
        pnlA = new javax.swing.JPanel();
        lblA = new javax.swing.JLabel();
        txtDatoA = new javax.swing.JTextField();
        pnlB = new javax.swing.JPanel();
        lblB = new javax.swing.JLabel();
        txtDatoB = new javax.swing.JTextField();
        pnlC = new javax.swing.JPanel();
        lblC = new javax.swing.JLabel();
        txtDatoC = new javax.swing.JTextField();
        pnlOpciones = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar");
        setResizable(false);
        getContentPane().add(filler1, java.awt.BorderLayout.WEST);
        getContentPane().add(filler3, java.awt.BorderLayout.SOUTH);

        pnlTODO.setLayout(new java.awt.BorderLayout(10, 0));

        pnlData.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlA.setAlignmentX(0.0F);
        pnlA.setAlignmentY(0.0F);
        pnlA.setLayout(new javax.swing.BoxLayout(pnlA, javax.swing.BoxLayout.PAGE_AXIS));

        lblA.setText("jLabel2");
        lblA.setAlignmentY(0.0F);
        pnlA.add(lblA);

        txtDatoA.setColumns(8);
        txtDatoA.setText("jTextField1");
        txtDatoA.setAlignmentX(0.0F);
        txtDatoA.setAlignmentY(0.0F);
        pnlA.add(txtDatoA);

        pnlData.add(pnlA);

        pnlB.setLayout(new javax.swing.BoxLayout(pnlB, javax.swing.BoxLayout.PAGE_AXIS));

        lblB.setText("jLabel2");
        lblB.setAlignmentY(0.0F);
        pnlB.add(lblB);

        txtDatoB.setColumns(35);
        txtDatoB.setText("jTextField1");
        txtDatoB.setAlignmentX(0.0F);
        txtDatoB.setAlignmentY(0.0F);
        pnlB.add(txtDatoB);

        pnlData.add(pnlB);

        pnlC.setLayout(new javax.swing.BoxLayout(pnlC, javax.swing.BoxLayout.PAGE_AXIS));

        lblC.setText("jLabel2");
        lblC.setAlignmentY(0.0F);
        pnlC.add(lblC);

        txtDatoC.setColumns(2);
        txtDatoC.setText("jTextField1");
        txtDatoC.setAlignmentX(0.0F);
        txtDatoC.setAlignmentY(0.0F);
        pnlC.add(txtDatoC);

        pnlData.add(pnlC);

        pnlTODO.add(pnlData, java.awt.BorderLayout.CENTER);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar(evt);
            }
        });
        pnlOpciones.add(btnCancelar);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificar(evt);
            }
        });
        pnlOpciones.add(btnModificar);

        pnlTODO.add(pnlOpciones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlTODO, java.awt.BorderLayout.CENTER);
        getContentPane().add(filler2, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void moreComponents(){

    }
    private void cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar
        dispose();
    }//GEN-LAST:event_cancelar

    private void modificar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificar
        String text;
        text=String.format("%8s | %-40s | %2d", txtDatoA.getText(), txtDatoB.getText(), Byte.valueOf(txtDatoC.getText()));
        JOptionPane.showMessageDialog(rootPane, "Todavía no está listo\n"+text);
    }//GEN-LAST:event_modificar

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
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblC;
    private javax.swing.JPanel pnlA;
    private javax.swing.JPanel pnlB;
    private javax.swing.JPanel pnlC;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JPanel pnlTODO;
    private javax.swing.JTextField txtDatoA;
    private javax.swing.JTextField txtDatoB;
    private javax.swing.JTextField txtDatoC;
    // End of variables declaration//GEN-END:variables
}
