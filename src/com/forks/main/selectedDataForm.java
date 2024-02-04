package com.forks.main;

import com.forks.model.foodItemModel;
import com.forks.model.selectedItemData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class selectedDataForm extends javax.swing.JFrame {
    forksUI main = new forksUI();
    selectedItemData select = new selectedItemData();
    String next;
    
    public selectedDataForm() {
        initComponents();
        getData();
        quantityText.setEditable(false);
    }
    
    void getData(){
        main.info(next);
        select.setFoodId(main.foodId);
        try {
                main.prep = main.connect.prepareStatement("SELECT * FROM foodproducts where food_id = '"+ select.getFoodId() +"'");
                main.rst = main.prep.executeQuery();
                while (main.rst.next()){
                   String foodName = main.rst.getString("food_name");
                   String foodId = main.rst.getString("food_id");
                   DecimalFormat df = new DecimalFormat("PHP #,##0.00");
                   int price = Integer.parseInt(main.rst.getString("price"));
                   String img = main.rst.getString("icon");
                   select.setPrice(price);
                   select.setTotalPrice(select.getPrice());
                   
                   selectedId.setText(foodId);
                   selectedProduct.setText(foodName);
                   selectedPrice.setText(df.format(price));
                   selectedPic.setImage(new javax.swing.ImageIcon(getClass().getResource(img)));
                   
                   
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    void pushData(){            // pushes selected data to database
        main.info(next);
        select.setFoodId(main.foodId);
        select.setCustomerIds(main.customerIds);
        select.setDineTake(main.dineInTakes);
          try {
                main.prep = main.connect.prepareStatement("SELECT * FROM foodproducts where food_id = '"+ select.getFoodId() +"'");
                main.rst = main.prep.executeQuery();
                while (main.rst.next()){
                   String foodName = main.rst.getString("food_name");
                   String foodId = main.rst.getString("food_id");
                   String price = main.rst.getString("price");
                   String img = main.rst.getString("icon");
                   String quantity = quantityText.getText();
                   String totalPrice = Integer.toString(select.getTotalPrice());
                   
                    main.prep = main.connect.prepareStatement("INSERT INTO ordered_list(status,customer_id,foodName,orderId,price,quantity,icon, total_price) VALUES(?,?,?,?,?,?,?,?)");
                    main.prep.setString(1, select.getDineTake());
                    main.prep.setString(2, select.getCustomerIds());
                    main.prep.setString(3, foodName);
                    main.prep.setString(4, foodId);
                    main.prep.setString(5, price);
                    main.prep.setString(6, quantity);
                    main.prep.setString(7, img);
                    main.prep.setString(8, totalPrice);
                    main.prep.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Order Added");
                    this.dispose();
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        selectedPic = new com.forks.model.pictureBox();
        selectedProduct = new javax.swing.JLabel();
        selectedId = new javax.swing.JLabel();
        quantityText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        minusButton = new javax.swing.JLabel();
        addButton = new javax.swing.JLabel();
        selectedPrice = new javax.swing.JLabel();
        addOrderButton = new javax.swing.JButton();
        addOrderButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 102, 0));
        jPanel3.setForeground(new java.awt.Color(255, 102, 0));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        selectedPic.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/B1.png"))); // NOI18N

        selectedProduct.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        selectedProduct.setForeground(new java.awt.Color(51, 51, 51));
        selectedProduct.setText("Item Name");

        selectedId.setFont(new java.awt.Font("Inter SemiBold", 1, 36)); // NOI18N
        selectedId.setForeground(new java.awt.Color(51, 51, 51));
        selectedId.setText("ID");

        quantityText.setBackground(new java.awt.Color(204, 204, 204));
        quantityText.setFont(new java.awt.Font("Inter Medium", 0, 24)); // NOI18N
        quantityText.setForeground(new java.awt.Color(51, 51, 51));
        quantityText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        quantityText.setText("1");
        quantityText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTextActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Quantity");

        minusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_minus_45px.png"))); // NOI18N
        minusButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minusButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                minusButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                minusButtonMouseReleased(evt);
            }
        });

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_add_45px.png"))); // NOI18N
        addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addButtonMouseReleased(evt);
            }
        });

        selectedPrice.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        selectedPrice.setForeground(new java.awt.Color(51, 51, 51));
        selectedPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        selectedPrice.setText("₱0.00");

        addOrderButton.setBackground(new java.awt.Color(255, 102, 51));
        addOrderButton.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        addOrderButton.setForeground(new java.awt.Color(204, 204, 204));
        addOrderButton.setText("Add Order");
        addOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderButtonActionPerformed(evt);
            }
        });

        addOrderButton1.setBackground(new java.awt.Color(255, 102, 51));
        addOrderButton1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        addOrderButton1.setForeground(new java.awt.Color(204, 204, 204));
        addOrderButton1.setText("Cancel");
        addOrderButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(minusButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityText, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                        .addComponent(selectedPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(selectedPic, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectedProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(selectedId, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(addOrderButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(addOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(selectedId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(selectedPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantityText, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minusButton)
                    .addComponent(addButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addOrderButton1)
                    .addComponent(addOrderButton))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void quantityTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityTextActionPerformed

    private void addButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMousePressed
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_add_45px_1.png")));
    }//GEN-LAST:event_addButtonMousePressed

    private void addButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseReleased
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_add_45px.png")));
    }//GEN-LAST:event_addButtonMouseReleased

    private void minusButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusButtonMousePressed
        minusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_minus_45px_1.png")));
    }//GEN-LAST:event_minusButtonMousePressed

    private void minusButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusButtonMouseReleased
        minusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_minus_45px.png")));
    }//GEN-LAST:event_minusButtonMouseReleased

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        int quan = Integer.parseInt(quantityText.getText());
        int added = quan + 1 ;
        int price = select.getPrice();
        select.setTotalPrice(select.getPrice());
        int addedPrice = price * added;
        selectedPrice.setText("PHP "+addedPrice+".00");
        quantityText.setText(Integer.toString(added));
        select.setTotalPrice(addedPrice);
        
    }//GEN-LAST:event_addButtonMouseClicked

    private void minusButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusButtonMouseClicked
        
        int quan = Integer.parseInt(quantityText.getText());
        if (quan > 1 ){
        int added = quan - 1 ;
        int price = select.getPrice();
        select.setTotalPrice(select.getPrice()); if(quan>1){
        int addedPrice = price * added;
        selectedPrice.setText("PHP "+addedPrice+".00");
        quantityText.setText(Integer.toString(added));
        select.setTotalPrice(addedPrice);
        }
        }
    }//GEN-LAST:event_minusButtonMouseClicked

    private void addOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderButtonActionPerformed
        pushData();
    }//GEN-LAST:event_addOrderButtonActionPerformed

    private void addOrderButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderButton1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_addOrderButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(selectedDataForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(selectedDataForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(selectedDataForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(selectedDataForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new selectedDataForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addButton;
    public javax.swing.JButton addOrderButton;
    private javax.swing.JButton addOrderButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel minusButton;
    private javax.swing.JTextField quantityText;
    private javax.swing.JLabel selectedId;
    private com.forks.model.pictureBox selectedPic;
    private javax.swing.JLabel selectedPrice;
    private javax.swing.JLabel selectedProduct;
    // End of variables declaration//GEN-END:variables
}
