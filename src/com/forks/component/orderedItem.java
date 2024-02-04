
package com.forks.component;

import com.forks.model.orderItemModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;


public class orderedItem extends javax.swing.JPanel {

    public orderedItem() {
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
private orderItemModel data;

    public orderItemModel getData() {
        return data;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    private boolean selected;

    
    public void setData(orderItemModel data){
        this.data = data;
        lbPic.setImage(data.getImg());
        lbfoodName.setText(data.getFoodName());
        DecimalFormat df = new DecimalFormat("PHP #,##0.00");
        lbprice.setText(df.format(data.getTotalPrice()));
        lbfoodId.setText(data.getOrderId());
        lbquantity.setText(data.getQuantity() + "X");
    }
    
    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(204,204,204));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (selected) {
            g2.setColor(new Color(153,0,153));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        g2.dispose();
        super.paint(grphcs);
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbfoodId = new javax.swing.JLabel();
        lbquantity = new javax.swing.JLabel();
        lbfoodName = new javax.swing.JLabel();
        lbprice = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbPic = new com.forks.model.pictureBox();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbfoodId.setBackground(new java.awt.Color(51, 51, 51));
        lbfoodId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lbfoodId.setForeground(new java.awt.Color(51, 51, 51));
        lbfoodId.setText("ID");

        lbquantity.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lbquantity.setForeground(new java.awt.Color(51, 51, 51));
        lbquantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbquantity.setText("1X");

        lbfoodName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lbfoodName.setForeground(new java.awt.Color(51, 51, 51));
        lbfoodName.setText("Food Name");

        lbprice.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lbprice.setForeground(new java.awt.Color(51, 51, 51));
        lbprice.setText("PHP 0.00");

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Quantity");

        lbPic.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/B1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lbPic, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbprice, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbfoodName, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbfoodId, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(47, 47, 47))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbfoodId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbfoodName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbprice, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private com.forks.model.pictureBox lbPic;
    private javax.swing.JLabel lbfoodId;
    private javax.swing.JLabel lbfoodName;
    private javax.swing.JLabel lbprice;
    private javax.swing.JLabel lbquantity;
    // End of variables declaration//GEN-END:variables
}
