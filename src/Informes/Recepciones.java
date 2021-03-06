/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Informes;

import Conexion.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Isi
 */
public class Recepciones extends javax.swing.JFrame {

    /**
     * Creates new form Recepciones
     */
    public Recepciones() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Informes - Recepciones");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Desde");

        jLabel3.setText("Hasta");

        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                String hoy;
                hoy = DBConnection.getFecha_ddMMMyyyy();
                Date date = formatter.parse(hoy);
                jDateChooser1.setDate(date);
                jDateChooser2.setDate(date);
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println(ex);
            }

        } catch (ParseException e) {
            System.err.println(e);
        }


    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Connection conexion = DBConnection.getConnection();
        try {

            SimpleDateFormat yyyyMMdd = new SimpleDateFormat("dd-MM-yyyy");
            String dia1 = yyyyMMdd.format(jDateChooser1.getDate());
            String dia2 = yyyyMMdd.format(jDateChooser2.getDate());

            Statement stAuxiliar = conexion.createStatement();
            stAuxiliar.executeUpdate("truncate table reporte_recepcion");
            Statement stRC = conexion.createStatement();
            int id = 0;

//            String sqlArqueo = "select * from cuenta inner join cliente on cliente.id_cliente = cuenta.id_cliente where (fecha >= '"+dia+" 00:00:00' or fecha >= '"+dia+"') and (fecha <= '"+dia+" 24:59:59') and (cuenta.obs <> 'SI' OR cuenta.obs is null) and cuenta.id_comercio = '" + index.id_comercio + "' and cuenta.id_estado != '9'";
            String sqlArqueo = "select recepcion.id_recepcion, recepcion.fecha, cliente.nombre as cliente_nombre, camion.chapa as camion_chapa, \n"
                    + "carreta.chapa as carreta_chapa, chofer.nombre as chofer_nombre, recepcion.comp_pesaje_bruto, \n"
                    + "recepcion.peso_bruto, recepcion.comp_pesaje_tara, recepcion.peso_tara, recepcion.peso_neto\n"
                    + "from recepcion\n"
                    + "\n"
                    + "inner join recepcion_detalle on recepcion.id_recepcion = recepcion_detalle.id_recepcion\n"
                    + "inner join cliente on cliente.id_cliente = recepcion_detalle.id_cliente\n"
                    + "inner join camion on camion.id_camion = recepcion_detalle.id_camion\n"
                    + "inner join carreta on carreta.id_carreta = recepcion_detalle.id_carreta\n"
                    + "inner join chofer on chofer.id_chofer = recepcion_detalle.id_chofer\n"
                    + "\n"
                    + "where (fecha >= '" + dia1 + " 00:00:00') and (fecha <= '" + dia2 + " 24:59:59') ";
            Statement stArqueo = conexion.createStatement();
            ResultSet rsArqueo = stArqueo.executeQuery(sqlArqueo);
            while (rsArqueo.next()) {

                ResultSet resultRC = stRC.executeQuery("SELECT MAX(id_reporte_recepcion) FROM reporte_recepcion");
                if (resultRC.next()) {
                    id = resultRC.getInt(1) + 1;
                }

                PreparedStatement stReporteCaja = conexion.prepareStatement("INSERT INTO reporte_recepcion VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
                stReporteCaja.setInt(1, id);
                stReporteCaja.setInt(2, rsArqueo.getInt("id_recepcion"));
                stReporteCaja.setString(3, rsArqueo.getString("fecha").trim());
                stReporteCaja.setString(4, rsArqueo.getString("cliente_nombre").trim());
                stReporteCaja.setString(5, rsArqueo.getString("camion_chapa").trim());
                stReporteCaja.setString(6, rsArqueo.getString("carreta_chapa").trim());
                stReporteCaja.setString(7, rsArqueo.getString("chofer_nombre").trim());
                stReporteCaja.setInt(8, rsArqueo.getInt("comp_pesaje_bruto"));
                stReporteCaja.setLong(9, rsArqueo.getLong("peso_bruto"));
                stReporteCaja.setInt(10, rsArqueo.getInt("comp_pesaje_tara"));
                stReporteCaja.setLong(11, rsArqueo.getLong("peso_tara"));
                stReporteCaja.setLong(12, rsArqueo.getLong("peso_neto"));
                stReporteCaja.executeUpdate();

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        String path = "C:\\Sistema\\reporte_recepcion.jasper";
        JasperReport jr = null;

        try {
            jr = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conexion);

            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (JRException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Recepciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recepciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recepciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recepciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recepciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
