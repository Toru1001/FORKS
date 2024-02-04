
package com.forks.component;

import com.forks.model.foodItemModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;


public class foodItem extends javax.swing.JPanel {
    private foodItemModel data;

    public foodItemModel getData() {
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
    
    public foodItem() {
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public void setData(foodItemModel data) {
        this.data = data;
        pic.setImage(data.getImage());
        lbFoodName.setText(data.getFoodName());
        DecimalFormat df = new DecimalFormat("PHP #,##0.00");
        lbPrice.setText(df.format(data.getPrice()));
        lbSize.setText(data.getSize());
        lbFoodId.setText(data.getFoodId());
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

        pic = new com.forks.model.pictureBox();
        lbFoodName = new javax.swing.JLabel();
        size = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbSize = new javax.swing.JLabel();
        lbFoodId = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/C1.1.png"))); // NOI18N

        lbFoodName.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        lbFoodName.setForeground(new java.awt.Color(51, 51, 51));
        lbFoodName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFoodName.setText("Food Name");

        size.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        size.setForeground(new java.awt.Color(51, 51, 51));
        size.setText("Sizes:");

        lbPrice.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(51, 51, 51));
        lbPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPrice.setText("₱ 0.00");

        lbSize.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        lbSize.setForeground(new java.awt.Color(51, 51, 51));
        lbSize.setText("Regular, L, XL");

        lbFoodId.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        lbFoodId.setForeground(new java.awt.Color(51, 51, 51));
        lbFoodId.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFoodName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(size)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbFoodId, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFoodId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbFoodName)
                .addGap(18, 18, 18)
                .addComponent(lbPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(size)
                    .addComponent(lbSize))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbFoodId;
    private javax.swing.JLabel lbFoodName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbSize;
    private com.forks.model.pictureBox pic;
    private javax.swing.JLabel size;
    // End of variables declaration//GEN-END:variables
}
