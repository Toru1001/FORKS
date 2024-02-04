
package com.forks.main;

import java.awt.Color;


public class openScreen extends javax.swing.JFrame {

   
    public openScreen() {
        initComponents();
        this.setExtendedState(720);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        proceedButton = new javax.swing.JPanel();
        proceedText = new javax.swing.JLabel();
        pictureBox2 = new com.forks.model.pictureBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pictureBox3 = new com.forks.model.pictureBox();
        pictureBox1 = new com.forks.model.pictureBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frame");
        setExtendedState(1080);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proceedButton.setBackground(new java.awt.Color(230, 121, 28));
        proceedButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        proceedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                proceedButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                proceedButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                proceedButtonMouseExited(evt);
            }
        });

        proceedText.setFont(new java.awt.Font("Inter ExtraBold", 0, 48)); // NOI18N
        proceedText.setForeground(new java.awt.Color(204, 204, 204));
        proceedText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proceedText.setText("Proceed to Order");

        javax.swing.GroupLayout proceedButtonLayout = new javax.swing.GroupLayout(proceedButton);
        proceedButton.setLayout(proceedButtonLayout);
        proceedButtonLayout.setHorizontalGroup(
            proceedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proceedButtonLayout.createSequentialGroup()
                .addComponent(proceedText, javax.swing.GroupLayout.DEFAULT_SIZE, 1444, Short.MAX_VALUE)
                .addContainerGap())
        );
        proceedButtonLayout.setVerticalGroup(
            proceedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proceedButtonLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(proceedText, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        background.add(proceedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 730, 1450, 180));

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/images/7f27abe348c6d3ee014c7f8b6a04f264.jpg"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Sylfaen", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 0));
        jLabel3.setText("\"SATISFY YOUR ");
        pictureBox2.add(jLabel3);
        jLabel3.setBounds(50, 330, 200, 32);

        jLabel4.setFont(new java.awt.Font("Sylfaen", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 0));
        jLabel4.setText("TEMPTING CRAVINGS\"");
        pictureBox2.add(jLabel4);
        jLabel4.setBounds(50, 360, 290, 32);

        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/images/output-onlinepngtools (4).png"))); // NOI18N
        pictureBox2.add(pictureBox3);
        pictureBox3.setBounds(40, 20, 100, 110);

        background.add(pictureBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -10, 750, 750));

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/images/Black Fried Chicken Promo Instagram Post.png"))); // NOI18N
        background.add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 740, 730));

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1450, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void proceedButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proceedButtonMouseEntered
         proceedButton.setBackground(new Color(152,69,17));
    }//GEN-LAST:event_proceedButtonMouseEntered

    private void proceedButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proceedButtonMouseExited
        proceedButton.setBackground(new Color(230,121,28));
        
    }//GEN-LAST:event_proceedButtonMouseExited

    private void proceedButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proceedButtonMouseClicked
        this.setVisible(false);
        new forksUI().setVisible(true);
    }//GEN-LAST:event_proceedButtonMouseClicked

   
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
            java.util.logging.Logger.getLogger(openScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(openScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(openScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(openScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new openScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.forks.model.pictureBox pictureBox1;
    private com.forks.model.pictureBox pictureBox2;
    private com.forks.model.pictureBox pictureBox3;
    private javax.swing.JPanel proceedButton;
    private javax.swing.JLabel proceedText;
    // End of variables declaration//GEN-END:variables
}
