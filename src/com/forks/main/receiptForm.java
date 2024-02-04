package com.forks.main;
import com.forks.model.ScrollBar;
import com.forks.model.selectedItemData;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receiptForm extends javax.swing.JFrame {
    forksUI main = new forksUI();
    static selectedItemData select = new selectedItemData();
    String next;
    public receiptForm() {
        initComponents();
        showReceipt();
        receiptScrollBar.setVerticalScrollBar(new ScrollBar());
    }

    void showReceipt(){
        main.info(next);
        select.setCustomerIds(main.customerIds);
        LocalDateTime localDateTime = LocalDateTime.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String localdate = localDateTime.format(formatter);
        DecimalFormat df = new DecimalFormat("#, ###");
        try {
            main.prep = main.connect.prepareStatement("SELECT * FROM placed_order where customer_id = '"+select.getCustomerIds()+"' AND date = '"+ localdate +"'");
            main.rst = main.prep.executeQuery();
            if(main.rst.first()){
                String username = main.rst.getString("username");
                String status = main.rst.getString("status");
            receiptPanel.setText("=================*Tasty Temptation*=================\n ");
            receiptPanel.setText(receiptPanel.getText() + "                      Matina, Davao City Davao Del Sur\n ");
            receiptPanel.setText(receiptPanel.getText() + "                          Contact no:+63 93054784626\n ");
            receiptPanel.setText(receiptPanel.getText() + "                             Official Receipt#00324234\n ");
            receiptPanel.setText(receiptPanel.getText() + "-------------------------------------------------------------------------------\n ");
            receiptPanel.setText(receiptPanel.getText() + "Order Number/ Name: "+username+"\n ");
            receiptPanel.setText(receiptPanel.getText()+ "Status :  "+status+ "\n");
            receiptPanel.setText(receiptPanel.getText() + "-------------THIS SERVES AS YOUR SALES INVOICE-------------\n ");
            receiptPanel.setText(receiptPanel.getText()+ "Item\t \t \t Id \tQty \tPrice " + "\n");
            receiptPanel.setText(receiptPanel.getText() + "-------------------------------------------------------------------------------\n ");
            }
            main.prep = main.connect.prepareStatement("SELECT * FROM placed_order where customer_id = '"+select.getCustomerIds()+"'");
            main.rst = main.prep.executeQuery();
            while(main.rst.next()){
                String item = main.rst.getString("food_name");
                String totalPrice = main.rst.getString("total_price");
                String quantity = main.rst.getString("quantity");
                String itemId = main.rst.getString("food_id");
                receiptPanel.setText(receiptPanel.getText() + item +"\t\t" +itemId+ "\t" + quantity + "\t" + totalPrice + "\n");
            }
            main.prep = main.connect.prepareStatement("SELECT * FROM placed_order where customer_id = '"+select.getCustomerIds()+"'");
            main.rst = main.prep.executeQuery();
            if(main.rst.last()){
             String grandPrice = main.rst.getString("grand_price");
             int finalGrandPrice = Integer.parseInt(grandPrice);
             String date = main.rst.getString("date");
             String time = main.rst.getString("time");
             receiptPanel.setText(receiptPanel.getText() + "-------------------------------------------------------------------------------\n ");
             receiptPanel.setText(receiptPanel.getText()+ " SubTotal: " + "PHP " + df.format(finalGrandPrice) + "\n");
             receiptPanel.setText(receiptPanel.getText()+ "  Date/ Time: "+ date + " " + time +"\n");
             receiptPanel.setText(receiptPanel.getText() + "-------------------------------------------------------------------------------\n ");
             receiptPanel.setText(receiptPanel.getText() + "                  Thankyou for eating at Tasty Temptation   \n ");
             receiptPanel.setText(receiptPanel.getText() + "                                Please Come Again !!!  \n ");
             receiptPanel.setText(receiptPanel.getText() + "------------------------------------------------------------------------------- ");
            }
            }catch (Exception e){
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        receiptScrollBar = new javax.swing.JScrollPane();
        receiptPanel = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 102, 51));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        receiptPanel.setEditable(false);
        receiptPanel.setBackground(new java.awt.Color(255, 255, 255));
        receiptPanel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        receiptScrollBar.setViewportView(receiptPanel);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Receipt");

        returnButton.setBackground(new java.awt.Color(255, 102, 51));
        returnButton.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        returnButton.setForeground(new java.awt.Color(204, 204, 204));
        returnButton.setText("THANK YOU!");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(receiptScrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(receiptScrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(returnButton)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        
    }//GEN-LAST:event_returnButtonActionPerformed

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
            java.util.logging.Logger.getLogger(receiptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(receiptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(receiptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(receiptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new receiptForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextPane receiptPanel;
    private javax.swing.JScrollPane receiptScrollBar;
    private javax.swing.JButton returnButton;
    // End of variables declaration//GEN-END:variables
}
