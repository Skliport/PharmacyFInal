package IU;
import Transacciones.TOrder;
import Entities.Order;
import Transacciones.TProduct;
import Transacciones.TUser;
import java.util.ArrayList;
import Entities.Product;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class vista_venta extends javax.swing.JFrame {
private ArrayList<Order> LOrder;
private ArrayList<Product> Lprod;
private ArrayList<Product> LProdPed;
private TOrder to;
private TProduct tp;
private Order od;
private Product pd;
private DefaultTableModel modelo;
private DefaultTableModel modelo2;
private int fila;
    public vista_venta() {
        initComponents();
        setLocationRelativeTo(null);
        to = new TOrder();
        tp= new TProduct();
        modelo = (DefaultTableModel) this.tablaProductos.getModel();
        modelo2 = (DefaultTableModel) this.tablaDetalle.getModel();
        LProdPed= new ArrayList<>();
        verificacionPermisos();
        LoadProductos();
    }
    private void LoadProductos(){
        Lprod = tp.GetProductView();
        Object[] productos = new Object[5];
        for (int i = 0; i < Lprod.size(); i++) {
            productos[0] = Lprod.get(i).product_id;
            productos[1] = Lprod.get(i).product_name;
            productos[2] = Lprod.get(i).product_brand;
            productos[3] = Lprod.get(i).unit_price;
            productos[4] = Lprod.get(i).units_in_stock;
            modelo.addRow(productos);
        }
        this.tablaProductos.setModel(modelo);
    }
     public void verificacionPermisos(){
        if (TUser.user.admin_access==0) {
             this.btnAdmin.setEnabled(false);
        }
       // if (TUser.user.vendor_access==0) {
        //     this.btnRegistrarVenta.setEnabled(false);
      //   }
        if (TUser.user.inventory_access==0) {
            this.btnAdmProd.setEnabled(false);
         } 
        if (TUser.user.order_access==0) {
            this.btnHistorialVenta.setEnabled(false);
         }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        btnRegistrarVenta = new javax.swing.JButton();
        btnAdmProd = new javax.swing.JButton();
        btnHistorialVenta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCloseSession = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblVenta = new javax.swing.JLabel();
        lblNombreProducto = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        btnAgregarVenta = new javax.swing.JButton();
        lblCantidad = new javax.swing.JLabel();
        lblPrecioU = new javax.swing.JLabel();
        spnDescuento = new javax.swing.JSpinner();
        txtPrecioU = new javax.swing.JTextField();
        txtNombrepProducto = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        lblDetalleVenta = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnRealizarVenta = new javax.swing.JButton();
        spnCantidad1 = new javax.swing.JSpinner();
        lblCantidad1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblProductos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();

        jToggleButton1.setText("jToggleButton1");

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

        btnHistorialVenta.setText("Historial Ventas");
        btnHistorialVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialVentaActionPerformed(evt);
            }
        });

        jLabel1.setText("OPCIONES:");

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
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdmProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHistorialVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCloseSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnAdmin)
                .addGap(24, 24, 24)
                .addComponent(btnRegistrarVenta)
                .addGap(31, 31, 31)
                .addComponent(btnAdmProd)
                .addGap(29, 29, 29)
                .addComponent(btnHistorialVenta)
                .addGap(26, 26, 26)
                .addComponent(btnCloseSession)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblVenta.setText("VENTA");

        lblNombreProducto.setText("Nombre del producto:");

        lblMarca.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMarca.setText("Marca:");

        btnAgregarVenta.setText("Agregar");
        btnAgregarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVentaActionPerformed(evt);
            }
        });

        lblCantidad.setText("Cantidad:");

        lblPrecioU.setText("Precio Unitario:");

        spnDescuento.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        txtPrecioU.setEditable(false);
        txtPrecioU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioUActionPerformed(evt);
            }
        });

        txtNombrepProducto.setEditable(false);

        txtMarca.setEditable(false);
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });

        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Marca", "Precio", "Cantidad", "Descuento"
            }
        ));
        jScrollPane2.setViewportView(tablaDetalle);

        lblDetalleVenta.setText("Detalles de Venta:");

        lblSubtotal.setText("Sub total:");

        txtSubtotal.setEditable(false);
        txtSubtotal.setText("0");

        lblTotal.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setText("0");

        btnRealizarVenta.setText("Realizar Venta");
        btnRealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarVentaActionPerformed(evt);
            }
        });

        spnCantidad1.setModel(new javax.swing.SpinnerNumberModel(1, null, 50, 1));

        lblCantidad1.setText("Descuento:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRealizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDetalleVenta)
                                    .addComponent(lblCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPrecioU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrecioU, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(txtNombrepProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(spnCantidad1))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSubtotal)
                .addGap(18, 18, 18)
                .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotal)
                .addGap(18, 18, 18)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(lblVenta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreProducto)
                    .addComponent(txtNombrepProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarca))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecioU)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPrecioU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidad)
                            .addComponent(spnCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDetalleVenta)
                    .addComponent(btnAgregarVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubtotal)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRealizarVenta)
                .addGap(54, 54, 54))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProductos.setText("PRODUCTOS");

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Marca", "Precio ", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(lblProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 137, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProductos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        JOptionPane.showMessageDialog(null, "Ya se encuentra en la ventana de venta de productoss", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btnRegistrarVentaActionPerformed

    private void btnAdmProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmProdActionPerformed
        vista_producto frmProduct = new vista_producto();
        frmProduct.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAdmProdActionPerformed

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

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        // TODO add your handling code here:
        fila = this.tablaProductos.getSelectedRow();
        pd=Lprod.get(fila);
        this.txtMarca.setText(pd.product_brand);
        this.txtNombrepProducto.setText(pd.product_name);
        this.txtPrecioU.setText(Double.toString(pd.unit_price));
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void btnAgregarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVentaActionPerformed
        // TODO add your handling code here:
        if (pd==null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto");
            return;
        }
        pd.units_in_stock=(int)this.spnCantidad1.getValue();
        pd.descuento=((int)this.spnDescuento.getValue());
        pd.descuento/=100;
        pd.descuento= pd.descuento*(pd.unit_price*pd.units_in_stock);
        LProdPed.add(pd);
        Object[] datos = new Object[5];
        datos[0]=pd.product_name;
        datos[1]=pd.product_brand;
        datos[2]=pd.unit_price;
        datos[3]=pd.units_in_stock;
        datos[4]=pd.descuento;
        modelo2.addRow(datos);
        this.tablaDetalle.setModel(modelo2);
        double subtotal =Double.parseDouble(this.txtSubtotal.getText());
        subtotal+=(pd.unit_price*pd.units_in_stock);
        this.txtSubtotal.setText(Double.toString(subtotal));
        double total= Double.parseDouble(this.txtTotal.getText());
        total+=((pd.unit_price*pd.units_in_stock)-pd.descuento);
        this.txtTotal.setText(Double.toString(total));
        this.txtMarca.setText("");
        this.txtNombrepProducto.setText("");
        this.txtPrecioU.setText("");
        this.spnCantidad1.setValue(0);
        this.spnDescuento.setValue(0);
        pd=null;
        
    }//GEN-LAST:event_btnAgregarVentaActionPerformed

    private void btnRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarVentaActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < LProdPed.size(); i++) {
            to.insertIntoTempOrderDetail(LProdPed.get(i).product_id, LProdPed.get(i).unit_price, LProdPed.get(i).units_in_stock, LProdPed.get(i).descuento);
            tp.DUnits_Stock(LProdPed.get(i).product_id, LProdPed.get(i).units_in_stock);
        }
        to.InsertNewOrder();
        this.txtSubtotal.setText("0");
        this.txtTotal.setText(Double.toString(0));
        this.txtMarca.setText("");
        this.txtNombrepProducto.setText("");
        this.txtPrecioU.setText("");
        this.spnCantidad1.setValue(0);
        this.spnDescuento.setValue(0);
        modelo2.setRowCount(0);
        this.tablaDetalle.setModel(modelo2);
    }//GEN-LAST:event_btnRealizarVentaActionPerformed

    private void txtPrecioUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioUActionPerformed

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
            java.util.logging.Logger.getLogger(vista_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vista_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vista_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista_venta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmProd;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnAgregarVenta;
    private javax.swing.JButton btnCloseSession;
    private javax.swing.JButton btnHistorialVenta;
    private javax.swing.JButton btnRealizarVenta;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCantidad1;
    private javax.swing.JLabel lblDetalleVenta;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblPrecioU;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVenta;
    private javax.swing.JSpinner spnCantidad1;
    private javax.swing.JSpinner spnDescuento;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombrepProducto;
    private javax.swing.JTextField txtPrecioU;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
