
package IU;

import javax.swing.table.DefaultTableModel;
import Entities.Product;
import Transacciones.TProduct;
import Transacciones.TUser;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class vista_producto extends javax.swing.JFrame {
private DefaultTableModel modelo;
private ArrayList<Product> Lprod;
private Product prod;
private TProduct tp;
int fila;

    public vista_producto() {
        initComponents();
        setLocationRelativeTo(null);
        modelo = (DefaultTableModel) this.tablaProducto.getModel();
        this.btnAgrProducto.setEnabled(false);
        verificacionPermisos();
        tp= new TProduct();
        LoadData();
    }
    private void LoadData(){
        modelo.setRowCount(0);
        Lprod= tp.GetProductoCompletoView();
        Object[] productos = new Object[5];
        for (int i = 0; i < Lprod.size(); i++) {
            productos[0] = Lprod.get(i).product_id;
            productos[1] = Lprod.get(i).product_name;
            productos[2] = Lprod.get(i).product_brand;
            productos[3] = Lprod.get(i).unit_price;
            productos[4] = Lprod.get(i).units_in_stock;
            modelo.addRow(productos);
        }
        this.tablaProducto.setModel(modelo);
    }
    public void verificacionPermisos(){
        if (TUser.user.admin_access==0) {
             this.btnAdmin.setEnabled(false);
        }
        if (TUser.user.vendor_access==0) {
             this.btnRegistrarVenta.setEnabled(false);
         }
       // if (TUser.user.inventory_access==0) {
        //    this.btnAdmProducto.setEnabled(false);
       //  } 
        if (TUser.user.order_access==0) {
            this.btnHistorialVenta.setEnabled(false);
         }
    }
    
    public void habilitarBoton(){
        if (!txtNombreProducto.getText().isEmpty () && !txtPrecioU.getText().isEmpty() &&  !txtMarca.getText().isEmpty()) {
             this.btnAgrProducto.setEnabled(true);
        }else {
             this.btnAgrProducto.setEnabled(false);
        }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        btnRegistrarVenta = new javax.swing.JButton();
        btnAdmProducto = new javax.swing.JButton();
        btnHistorialVenta = new javax.swing.JButton();
        lblOpciones = new javax.swing.JLabel();
        btnCloseSession = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblAdmProducto = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtPrecioU = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        lblNombreProducto = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        lblPrecioUnitario = new javax.swing.JLabel();
        btnAgrProducto = new javax.swing.JButton();
        spnCantidad = new javax.swing.JSpinner();
        btnGuardarCambios = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblProducto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnRegistrarVenta.setText("Registrar Venta");
        btnRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVentaActionPerformed(evt);
            }
        });

        btnAdmProducto.setText("Administrar Productos");
        btnAdmProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmProductoActionPerformed(evt);
            }
        });

        btnHistorialVenta.setText("Historial Ventas");
        btnHistorialVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialVentaActionPerformed(evt);
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
                        .addGap(69, 69, 69)
                        .addComponent(lblOpciones))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdmProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHistorialVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCloseSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(46, Short.MAX_VALUE))
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
                .addComponent(btnAdmProducto)
                .addGap(29, 29, 29)
                .addComponent(btnHistorialVenta)
                .addGap(26, 26, 26)
                .addComponent(btnCloseSession)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblAdmProducto.setText("ADMINISTRAR PRODUCTOS");

        lblCantidad.setText("Cantidad:");

        txtPrecioU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioUActionPerformed(evt);
            }
        });
        txtPrecioU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioUKeyReleased(evt);
            }
        });

        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyReleased(evt);
            }
        });

        lblNombreProducto.setText("Nombre:");

        lblMarca.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMarca.setText("Marca:");

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
        });

        lblPrecioUnitario.setText("Precio Unitario:");

        btnAgrProducto.setText("Agregar Producto");
        btnAgrProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgrProductoActionPerformed(evt);
            }
        });

        btnGuardarCambios.setText("Guardar Cambios");
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar campos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCantidad))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPrecioUnitario)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(235, 235, 235))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAgrProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAdmProducto))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdmProducto)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreProducto)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioUnitario))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgrProducto)
                    .addComponent(btnGuardarCambios)
                    .addComponent(btnLimpiar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProducto.setText("PRODUCTOS");

        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Marca", "Precio Unitario", "Cantidad"
            }
        ));
        tablaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProducto);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProducto)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioUActionPerformed
        
    }//GEN-LAST:event_txtPrecioUActionPerformed

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        vista_venta frmVenta = new vista_venta();
        frmVenta.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegistrarVentaActionPerformed

    private void btnAdmProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmProductoActionPerformed
         JOptionPane.showMessageDialog(null, "Ya se encuentra en la ventana de administración de productos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btnAdmProductoActionPerformed

    private void btnHistorialVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialVentaActionPerformed
        vista_historia_venta frmHistorySell = new vista_historia_venta();
        frmHistorySell.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_btnHistorialVentaActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        vista_admin frmAdmin = new vista_admin();
        frmAdmin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAdminActionPerformed

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
        // TODO add your handling code here:
        this.btnAgrProducto.setEnabled(false);
        fila = this.tablaProducto.getSelectedRow();
        prod = Lprod.get(fila);
        this.txtMarca.setText(prod.product_brand);
        this.txtNombreProducto.setText(prod.product_name);
        this.txtPrecioU.setText(Double.toString(prod.unit_price));
        this.spnCantidad.setValue(prod.units_in_stock);
    }//GEN-LAST:event_tablaProductoMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed
    private void Limpiar(){
        this.btnAgrProducto.setEnabled(true);
        this.txtMarca.setText("");
        this.txtNombreProducto.setText("");
        this.txtPrecioU.setText("");
        this.spnCantidad.setValue(0);
    }
    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        // TODO add your handling code here:
        prod.product_brand=this.txtMarca.getText();
        prod.product_name=this.txtNombreProducto.getText();
        prod.unit_price = Double.parseDouble(this.txtPrecioU.getText());
        prod.units_in_stock = (int)this.spnCantidad.getValue();
        tp.UpdateProduct(prod.product_id,prod.product_name, prod.product_brand, prod.unit_price, prod.units_in_stock);
        LoadData();
        Limpiar();
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnAgrProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgrProductoActionPerformed
        // TODO add your handling code here:
        String nombre,marca;
        double precio;
        int cantidad;
        nombre = this.txtNombreProducto.getText();
        marca = this.txtMarca.getText();
        precio = Double.parseDouble(this.txtPrecioU.getText());
        cantidad = (int) this.spnCantidad.getValue();
        tp.InsertIntoProduct(nombre, marca, precio, cantidad);
        LoadData();
        Limpiar();
    }//GEN-LAST:event_btnAgrProductoActionPerformed

    private void btnCloseSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseSessionActionPerformed
        // TODO add your handling code here:
        vista_login  frmLogin = new vista_login();
        frmLogin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseSessionActionPerformed

    private void txtNombreProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyReleased
        // TODO add your handling code here:
        habilitarBoton();
    }//GEN-LAST:event_txtNombreProductoKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        // TODO add your handling code here:
         habilitarBoton();
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtPrecioUKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUKeyReleased
        // TODO add your handling code here:
         habilitarBoton();
    }//GEN-LAST:event_txtPrecioUKeyReleased

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
            java.util.logging.Logger.getLogger(vista_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vista_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vista_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista_producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmProducto;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnAgrProducto;
    private javax.swing.JButton btnCloseSession;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnHistorialVenta;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAdmProducto;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblOpciones;
    private javax.swing.JLabel lblPrecioUnitario;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioU;
    // End of variables declaration//GEN-END:variables
}
