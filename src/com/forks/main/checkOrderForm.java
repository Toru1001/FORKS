
package com.forks.main;

import com.forks.component.orderedItem;
import static com.forks.main.forksUI.select;
import com.forks.model.ScrollBar;
import com.forks.model.foodItemModel;
import com.forks.model.orderItemModel;
import com.forks.model.selectedItemData;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class checkOrderForm extends javax.swing.JFrame {
    forksUI main = new forksUI();
    static selectedItemData select = new selectedItemData();
    static String foodId;
    String next;

    public checkOrderForm() {
        initComponents();
        clearAllProducts();
        showOrderedItems();
        getGrandPrice();
        itemScrollBar.setVerticalScrollBar(new ScrollBar());
    }
    
    public void addItems(orderItemModel data){        //  method for gaining data that was encapsulated and is used for adding the products to itemPanel
        orderedItem item = new orderedItem();
        item.setData(data);
        item.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if (SwingUtilities.isLeftMouseButton(me)){
                    select.setFoodName(data.getFoodName());
                    select.setFoodId(data.getOrderId());
                    select.setPrice(data.getPrice());
                    select.setQuantity(data.getQuantity());
                    close();
                    new editDataForm().setVisible(true);
                    
                }
            } 
        });
        panelItem.add(item);
        panelItem.repaint();
        panelItem.revalidate();
    }
    
    void close(){
        this.dispose();
    }
    
    public void clearAllProducts(){     //  clears the products that is previously added to the itemPanel
        panelItem.removeAll();
        panelItem.repaint();
        panelItem.revalidate();
    }
    
    public static String info(String a){        // shares data to other classes
        String next = "";
        foodId = select.getFoodId();
        return next;
    }
    
        public void showOrderedItems(){
        main.info(next);
        select.setCustomerIds(main.customerIds);
        try {
                main.prep = main.connect.prepareStatement("SELECT * FROM ordered_list where customer_id = '"+select.getCustomerIds()+"'");
                main.rst = main.prep.executeQuery();
                while (main.rst.next()){
                   String status = main.rst.getString("status");
                   String foodName = main.rst.getString("foodName");
                   String foodId = main.rst.getString("orderId");
                   int price = Integer.parseInt(main.rst.getString("price"));
                   int quantity = Integer.parseInt(main.rst.getString("quantity"));
                   String customerId = main.rst.getString("customer_id");
                   int totalPrice = Integer.parseInt(main.rst.getString("total_price"));
                   String img = main.rst.getString("icon");
                   addItems(new orderItemModel(status,Integer.parseInt(customerId),foodName, foodId, price, quantity, new ImageIcon(getClass().getResource(img)),totalPrice));
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }
    
        public void getGrandPrice(){
        ArrayList<Integer> prices = new ArrayList();
        DecimalFormat df = new DecimalFormat("#, ###");
        grandTotal.setText("PHP 0.00");
        try{
            main.prep = main.connect.prepareStatement("SELECT * FROM ordered_list where customer_id = '"+ select.getCustomerIds() +"'");
            main.rst = main.prep.executeQuery();
            
            while(main.rst.next()){
                int price;
                price = Integer.parseInt(main.rst.getString("total_price"));
                if(price>10){
                 int prevValue;
                 prices.add(price);
                 prevValue = prices.get(0);
                    
                 
                 if(prices.size() < 0){
                     grandTotal.setText("PHP 0.00");
                 } else if (prices.size() == 1){
                     grandTotal.setText("PHP " + df.format(prevValue) + ".00");
                 }else if (prices.size() > 1){
                
                for(int i = 1; i<prices.size();i++){
               int price1 = prevValue + prices.get(i);
               prevValue = price1;
               grandTotal.setText("PHP " + df.format(price1) + ".00");
           } }
                }
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(forksUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        itemScrollBar = new javax.swing.JScrollPane();
        panelItem = new com.forks.model.PanelItem();
        placeOrderButton = new javax.swing.JButton();
        grandTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Inter SemiBold", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Order Details ");

        jLabel19.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("-------------------------------");

        jLabel20.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("-------------------------------");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel18))
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addContainerGap(555, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 6, 790, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        itemScrollBar.setViewportView(panelItem);

        jPanel4.add(itemScrollBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 380));

        placeOrderButton.setBackground(new java.awt.Color(255, 102, 51));
        placeOrderButton.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        placeOrderButton.setForeground(new java.awt.Color(204, 204, 204));
        placeOrderButton.setText("Close");
        placeOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOrderButtonActionPerformed(evt);
            }
        });
        jPanel4.add(placeOrderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, -1, -1));

        grandTotal.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        grandTotal.setForeground(new java.awt.Color(51, 51, 51));
        grandTotal.setText("PHP 0.00");
        jPanel4.add(grandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 210, -1));

        jLabel2.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Total Price: ");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 66, 790, 450));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void placeOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_placeOrderButtonActionPerformed

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
            java.util.logging.Logger.getLogger(checkOrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(checkOrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(checkOrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(checkOrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new checkOrderForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel grandTotal;
    private javax.swing.JScrollPane itemScrollBar;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private com.forks.model.PanelItem panelItem;
    private javax.swing.JButton placeOrderButton;
    // End of variables declaration//GEN-END:variables
}
