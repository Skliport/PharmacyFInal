package IU;

import javax.swing.table.DefaultTableModel;
import Entities.Order;
import Entities.Order_detail;
import Entities.Product;
import Transacciones.TOrder;
import Transacciones.TProduct;
import Transacciones.TUser;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class vista_historia_venta extends javax.swing.JFrame {

    private ArrayList<Order_detail> LOrder;
    private ArrayList<Order_detail> LOrderById;
    private ArrayList<Product> LGet_details_view_by_id;
    private DefaultTableModel HistorialVenta;
    private DefaultTableModel HistorialVentaById;
    private DefaultTableModel DetalleVentaPorId;
    private TOrder to;
    private Order_detail Order;
    private int fila;

    public vista_historia_venta() {
        initComponents();
        setLocationRelativeTo(null);
        to = new TOrder();
        verificacionPermisos();
        HistorialVenta = (DefaultTableModel) this.tablaHistorialVenta.getModel();
        HistorialVentaById = (DefaultTableModel) this.tablaHistorialVenta.getModel();
        DetalleVentaPorId = (DefaultTableModel) this.tablaDetalleVenta.getModel();
        LoadHistorialVenta();

    }
     public void verificacionPermisos(){
        if (TUser.user.admin_access==0) {
             this.btnAdmin.setEnabled(false);
        }
        if (TUser.user.vendor_access==0) {
            this.btnRegistrarVenta.setEnabled(false);
        }
        if (TUser.user.inventory_access==0) {
            this.btnAdmProd.setEnabled(false);
        } 
      //  if (TUser.user.order_access==0) {
           // this.btnHistorialVenta.setEnabled(false);
       //  }
    }
    private void LoadHistorialVenta() {
        LOrder = to.GetOrders();
        Object[] ventas = new Object[3];
        for (int i = 0; i < LOrder.size(); i++) {
            ventas[0] = LOrder.get(i).order_id;
            ventas[1] = LOrder.get(i).date;
            ventas[2] = LOrder.get(i).total;
            HistorialVenta.addRow(ventas);
        }
        this.tablaHistorialVenta.setModel(HistorialVenta);
    }

    private void LoadHistorialVentaPorId(int order_find) {
        LOrderById = to.FiltrarOrder(order_find);
        Object[] ventasVistasPorId = new Object[3];
        for (int i = 0; i < LOrderById.size(); i++) {
            ventasVistasPorId[0] = LOrderById.get(i).order_id;
            ventasVistasPorId[1] = LOrderById.get(i).date;
            ventasVistasPorId[2] = LOrderById.get(i).total;
            HistorialVentaById.addRow(ventasVistasPorId);
        }
        this.tablaHistorialVenta.setModel(HistorialVentaById);
    }

    private void LoadHistorialVentaDetallePorId(int order_find) {
        DetalleVentaPorId.setRowCount(0);
        LGet_details_view_by_id = to.FiltrarOrdenId(order_find);
        Object[] ventasDetail = new Object[7];
        for (int i = 0; i < LGet_details_view_by_id.size(); i++) {
            ventasDetail[0] = LGet_details_view_by_id.get(i).order_id;
            ventasDetail[1] = LGet_details_view_by_id.get(i).order_detail_id;
            ventasDetail[2] = LGet_details_view_by_id.get(i).product_name;
            ventasDetail[3] = LGet_details_view_by_id.get(i).product_brand;
            ventasDetail[4] = LGet_details_view_by_id.get(i).unit_price;
            ventasDetail[5] = LGet_details_view_by_id.get(i).quantity;
            ventasDetail[6] = LGet_details_view_by_id.get(i).discount;
            DetalleVentaPorId.addRow(ventasDetail);
        }
        this.tablaDetalleVenta.setModel(DetalleVentaPorId);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        btnRegistrarVenta = new javax.swing.JButton();
        btnAdmProd = new javax.swing.JButton();
        btnHistorialVen = new javax.swing.JButton();
        lblOpciones = new javax.swing.JLabel();
        btnCloseSession = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblHistorialVenta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorialVenta = new javax.swing.JTable();
        lblBusq = new javax.swing.JLabel();
        txtIdOrdenBusq = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnReiniciarBusq = new javax.swing.JButton();
        btnAnularVenta = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblDetalleVenta = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalleVenta = new javax.swing.JTable();

        jToolBar1.setRollover(true);

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnRegistrarVenta.setText("Registrar Venta");
        btnRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVentaActionPerformed(evt);
            }
        });

        btnAdmProd.setText("Administrar Productos");
        btnAdmProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmProdActionPerformed(evt);
            }
        });

        btnHistorialVen.setText("Historial Ventas");
        btnHistorialVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialVenActionPerformed(evt);
            }
        });

        lblOpciones.setText("OPCIONES:");

        btnCloseSession.setText("Cerrar Sesion");
        btnCloseSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseSessionActionPerformed(evt);
            }
        });

        btnAdmin.setText("Administrar Usuarios");
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(lblOpciones))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdmProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHistorialVen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCloseSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblOpciones)
                .addGap(18, 18, 18)
                .addComponent(btnAdmin)
                .addGap(30, 30, 30)
                .addComponent(btnRegistrarVenta)
                .addGap(31, 31, 31)
                .addComponent(btnAdmProd)
                .addGap(29, 29, 29)
                .addComponent(btnHistorialVen)
                .addGap(26, 26, 26)
                .addComponent(btnCloseSession)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblHistorialVenta.setText("HISTORIAL VENTAS");

        tablaHistorialVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha y hora", "Total"
            }
        ));
        tablaHistorialVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHistorialVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaHistorialVenta);

        lblBusq.setText("Búsqueda por ID de Orden:");

        txtIdOrdenBusq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdOrdenBusqActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnReiniciarBusq.setText("Reiniciar búsqueda");
        btnReiniciarBusq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarBusqActionPerformed(evt);
            }
        });

        btnAnularVenta.setText("Anular Venta");
        btnAnularVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblHistorialVenta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblBusq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdOrdenBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReiniciarBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnularVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBusq)
                    .addComponent(txtIdOrdenBusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnReiniciarBusq)
                    .addComponent(btnAnularVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHistorialVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDetalleVenta.setText("DETALLE DE VENTA");

        tablaDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID orden", "ID detalle orden", "Producto", "Marca", "Precio unitario", "Cantidad", "Descuento"
            }
        ));
        jScrollPane2.setViewportView(tablaDetalleVenta);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblDetalleVenta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDetalleVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        vista_venta frmVenta = new vista_venta();
        frmVenta.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegistrarVentaActionPerformed

    private void btnAdmProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmProdActionPerformed
        vista_producto frmProduct = new vista_producto();
        frmProduct.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAdmProdActionPerformed

    private void btnHistorialVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialVenActionPerformed
        JOptionPane.showMessageDialog(null, "Ya se encuentra en la ventana de historial de ventas", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btnHistorialVenActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        vista_admin frmAdmin = new vista_admin();
        frmAdmin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnReiniciarBusqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarBusqActionPerformed
        // TODO add your handling code here:
        this.txtIdOrdenBusq.setText("");
        this.HistorialVenta.setRowCount(0);
        this.DetalleVentaPorId.setRowCount(0);
        LoadHistorialVenta();
    }//GEN-LAST:event_btnReiniciarBusqActionPerformed

    //Anular venta
    private void btnAnularVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularVentaActionPerformed
        // TODO add your handling code here:
        fila = this.tablaHistorialVenta.getSelectedRow();
        int id = (int)this.tablaHistorialVenta.getValueAt(fila, 0);
        
        to.DeleteOrder(id);
        this.HistorialVenta.setRowCount(0);
        this.DetalleVentaPorId.setRowCount(0);
        LoadHistorialVenta();

    }//GEN-LAST:event_btnAnularVentaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Order_detail Order2 = null;
        fila = Integer.parseInt(this.txtIdOrdenBusq.getText());
        for (int i = 0; i < LOrder.size(); i++) {
            if (fila == LOrder.get(i).order_id) {
                Order2 = LOrder.get(i);
                i = LOrder.size() + 1;
            }
        }
        if (Order2 == null) {
            JOptionPane.showMessageDialog(null, "El ID que ingreso no se encuentra en el sistema o puede que este anulado", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            return;
        }
        this.HistorialVenta.setRowCount(0);
        this.DetalleVentaPorId.setRowCount(0);
        this.txtIdOrdenBusq.setText("");
        LoadHistorialVentaPorId(fila);

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtIdOrdenBusqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdOrdenBusqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdOrdenBusqActionPerformed

    //Evento para mostrar los detalles de orden al seleccionar una orden especifica
    private void tablaHistorialVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHistorialVentaMouseClicked
        // TODO add your handling code here:
         fila = this.tablaHistorialVenta.getSelectedRow();
        int id = (int)this.tablaHistorialVenta.getValueAt(fila, 0);
        LoadHistorialVentaDetallePorId(id);
    }//GEN-LAST:event_tablaHistorialVentaMouseClicked

    private void btnCloseSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseSessionActionPerformed
        // TODO add your handling code here:
        vista_login  frmLogin = new vista_login();
        frmLogin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseSessionActionPerformed

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
            java.util.logging.Logger.getLogger(vista_historia_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vista_historia_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vista_historia_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista_historia_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista_historia_venta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmProd;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnAnularVenta;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCloseSession;
    private javax.swing.JButton btnHistorialVen;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JButton btnReiniciarBusq;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblBusq;
    private javax.swing.JLabel lblDetalleVenta;
    private javax.swing.JLabel lblHistorialVenta;
    private javax.swing.JLabel lblOpciones;
    private javax.swing.JTable tablaDetalleVenta;
    private javax.swing.JTable tablaHistorialVenta;
    private javax.swing.JTextField txtIdOrdenBusq;
    // End of variables declaration//GEN-END:variables
}
