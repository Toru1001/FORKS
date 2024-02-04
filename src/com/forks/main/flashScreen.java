/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.forks.main;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.util.Random;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jmira
 */
public class flashScreen extends javax.swing.JFrame {

    /**
     * Creates new form flashScreen
     */
    public flashScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressBar = new javax.swing.JProgressBar();
        loadingText = new javax.swing.JLabel();
        pictureBox1 = new com.forks.model.pictureBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(821, 461));
        setMinimumSize(new java.awt.Dimension(821, 461));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(821, 461));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(progressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 290, 20));

        loadingText.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        loadingText.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(loadingText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 240, 20));

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/images/Untitled (2).png"))); // NOI18N
        getContentPane().add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 470));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   static void progressBarAction() {
        flashScreen FS = new flashScreen();
        FS.setVisible(true);
       /* ImageIcon img = new ImageIcon("");
        FS.setIconImage(img.getImage()); */
        UIManager.put("ProgressBar.selectionBackground", Color.black);
        UIManager.put("ProgressBar.selectionForeground", Color.white);
        UIManager.put("ProgressBar.foreground", new Color(49, 65, 95));
        for (float i = 0.00f; i < 1.00f; i += 0.01f) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            FS.setOpacity(i);
        }

        for (int i = 0; i <= 100; i++) {
            try {
                Random random = new Random();
                int number = random.nextInt(10) + 1;
                Thread.sleep(number);
                FS.progressBar.setValue(i);
                if(i <=100){
                    loadingText.setText("Loading in progress... " + i + "%");
                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        FS.dispose();
        new openScreen().setVisible(true);
    }
   
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(flashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       progressBarAction();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel loadingText;
    private com.forks.model.pictureBox pictureBox1;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
