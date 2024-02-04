package com.forks.main;

import com.forks.component.foodItem;
import com.forks.component.orderedItem;
import static com.forks.main.checkOrderForm.select;
import com.forks.model.ScrollBar;
import com.forks.model.foodItemModel;
import com.forks.model.orderItemModel;
import com.forks.model.selectedItemData;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static javax.swing.UIManager.get;

public class forksUI extends javax.swing.JFrame {
    Connection connect;
    PreparedStatement prep;
    ResultSet rst;
    static selectedItemData select = new selectedItemData();
    static String foodId, customerIds, dineInTakes;
    
    public forksUI() {
        initComponents();
        Connect();
        itemScrollBar.setVerticalScrollBar(new ScrollBar());
        orderScrollBar.setVerticalScrollBar(new ScrollBar());
    }
    
    public void Connect(){      //secure a connection to database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/forks_data", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(forksUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(forksUI.class.getName()).log(Level.SEVERE, null, ex);
        }  
}
    
    public void changeCard(Component Card) {    // Changes to selected panel
        cardPanel.removeAll();
        cardPanel.add(Card);
        cardPanel.repaint();
        cardPanel.revalidate();
    }
    
    public void changeCardFoodCat(Component Card) {    // Changes to selected panel
        foodCategory.removeAll();
        foodCategory.add(Card);
        foodCategory.repaint();
        foodCategory.revalidate();
    }
    
    public void addItem(foodItemModel data){        //  method for gaining data that was encapsulated and is used for adding the products to itemPanel
        foodItem item = new foodItem();
        item.setData(data);
        item.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if (SwingUtilities.isLeftMouseButton(me)){
                    select.setFoodId(data.getFoodId());
                    select.setFoodName(data.getFoodName());
                    select.setPrice(data.getPrice());
                    select.setQuantity(data.getQuantity());
                    select.setImage(data.getImage());
                    select.setSize(data.getSize());
                    new selectedDataForm().setVisible(true);
                }
            } 
        });
        panelItem1.add(item);
        panelItem1.repaint();
        panelItem1.revalidate();
    }
    
    public void addOrderItem(orderItemModel data){        //  method for gaining data that was encapsulated and is used for adding the products to itemPanel
        orderedItem item = new orderedItem();
        item.setData(data);
        item.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if (SwingUtilities.isLeftMouseButton(me)){
                    
                }
            } 
        });
        panelItem2.add(item);
        panelItem2.repaint();
        panelItem2.revalidate();
    }
    
    public void showOrderedItems(){
        try {
                prep = connect.prepareStatement("SELECT * FROM ordered_list where customer_id = '"+customerIds+"'");
                rst = prep.executeQuery();
                while (rst.next()){
                   String status = rst.getString("status");
                   String foodName = rst.getString("foodName");
                   String foodId = rst.getString("orderId");
                   int price = Integer.parseInt(rst.getString("price"));
                   int quantity = Integer.parseInt(rst.getString("quantity"));
                   String customerId = rst.getString("customer_id");
                   int totalPrice = Integer.parseInt(rst.getString("total_price"));
                   String img = rst.getString("icon");
                   addOrderItem(new orderItemModel(status,Integer.parseInt(customerId),foodName, foodId, price, quantity, new ImageIcon(getClass().getResource(img)),totalPrice));
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }
    
    public void customerId(){
        int customerId = select.getCustomerId();
        if(customerId>0){
            select.setCustomerId(customerId + 1);
            select.setCustomerIds(Integer.toString(customerId));
        } else if (customerId == 0){
            select.setCustomerId(1);
            select.setCustomerIds(Integer.toString(customerId));
        }
    }
    
    public void clearAllProducts(){     //  clears the products that is previously added to the itemPanel
        panelItem1.removeAll();
        panelItem1.repaint();
        panelItem1.revalidate();
        panelItem2.removeAll();
        panelItem2.repaint();
        panelItem2.revalidate();
    }
    
    public void showBurgerItems(){  //displays burger items to itempanel from database
            try {
                prep = connect.prepareStatement("SELECT * FROM foodproducts where food_type = 'burger'");
                rst = prep.executeQuery();
                while (rst.next()){
                   String foodName = rst.getString("food_name");
                   String foodId = rst.getString("food_id");
                   int price = Integer.parseInt(rst.getString("price"));
                   String img = rst.getString("icon");
                    addItem(new foodItemModel(foodName,foodId, price, "Solo", 1, new ImageIcon(getClass().getResource(img)) ));
                    
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void showChickenItems(){  //displays chicken items to itempanel from database
            try {
                prep = connect.prepareStatement("SELECT * FROM foodproducts where food_type = 'Chicken'");
                rst = prep.executeQuery();
                while (rst.next()){
                   String foodName = rst.getString("food_name");
                   String foodId = rst.getString("food_id");
                   int price = Integer.parseInt(rst.getString("price"));
                   String img = rst.getString("icon");
                    addItem(new foodItemModel(foodName,foodId, price, "Solo", 1, new ImageIcon(getClass().getResource(img)) ));
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void showDrinksItems(){  //displays drink items to itempanel from database
            try {
                prep = connect.prepareStatement("SELECT * FROM foodproducts where food_type = 'Drink'");
                rst = prep.executeQuery();
                while (rst.next()){
                   String foodName = rst.getString("food_name");
                   String foodId = rst.getString("food_id");
                   int price = Integer.parseInt(rst.getString("price"));
                   String img = rst.getString("icon");
                    addItem(new foodItemModel(foodName,foodId, price, "Solo", 1, new ImageIcon(getClass().getResource(img)) ));
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void showSidesItems(){  //displays sides items to itempanel from database
        for(int i = 0; i==0; i++){
            try {
                prep = connect.prepareStatement("SELECT * FROM foodproducts where food_type = 'Sides'");
                rst = prep.executeQuery();
                while (rst.next()){
                   String foodName = rst.getString("food_name");
                   String foodId = rst.getString("food_id");
                   int price = Integer.parseInt(rst.getString("price"));
                   String img = rst.getString("icon");
                    addItem(new foodItemModel(foodName,foodId, price, "Solo", 1, new ImageIcon(getClass().getResource(""+img+"")) ));
                }   } catch (Exception ex) {}
        }
    }
    
    public void showPastaItems(){  //displays pasta items to itempanel from database
            try {
                prep = connect.prepareStatement("SELECT * FROM foodproducts where food_type = 'Pasta'");
                rst = prep.executeQuery();
                while (rst.next()){
                   String foodName = rst.getString("food_name");
                   String foodId = rst.getString("food_id");
                   int price = Integer.parseInt(rst.getString("price"));
                   String img = rst.getString("icon");
                    addItem(new foodItemModel(foodName,foodId, price, "Solo", 1, new ImageIcon(getClass().getResource(img)) ));
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public static String info(String a){        // shares data to other classes
        String next = "";
        foodId = select.getFoodId();
        customerIds = Integer.toString(select.getCustomerId());
        dineInTakes = select.getDineTake();
        return next;
    }
    
    public void returnMenu(){
        try {
            prep = connect.prepareStatement("DELETE FROM ordered_list WHERE customer_id = '"+ select.getCustomerId()+"'");
                prep.executeUpdate();
                select.setCustomerId(select.getCustomerId()-1);
        } catch (SQLException ex) {
            Logger.getLogger(forksUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getGrandPrice(){
        ArrayList<Integer> prices = new ArrayList();
        DecimalFormat df = new DecimalFormat("#, ###");
        grandTotal.setText("PHP 0.00");
        try{
            prep = connect.prepareStatement("SELECT * FROM ordered_list where customer_id = '"+ customerIds +"'");
            rst = prep.executeQuery();
            
            while(rst.next()){
                int price;
                price = Integer.parseInt(rst.getString("total_price"));
                if(price>10){
                 int prevValue;
                 prices.add(price);
                 prevValue = prices.get(0);
                 if(prices.size() < 0){
                     grandTotal.setText("PHP 0.00");
                 } else if (prices.size() == 1){
                     grandTotal.setText("PHP " + df.format(prevValue) + ".00");
                     select.setGrandPrice(Integer.toString(prevValue));
                 }else if (prices.size() > 1){
                
                for(int i = 1; i<prices.size();i++){
                int price1 = prevValue + prices.get(i);
               prevValue = price1;
               grandTotal.setText("PHP " + df.format(price1) + ".00");
               select.setGrandPrice(Integer.toString(price1));
           } }
                }
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(forksUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void placeOrder(){
        LocalDateTime localDateTime = LocalDateTime.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm a");
        try {
                prep = connect.prepareStatement("SELECT * FROM ordered_list where customer_id = '"+ customerIds +"'");
                rst = prep.executeQuery();
                while (rst.next()){
                   String status = rst.getString("status");
                   String foodName = rst.getString("foodName");
                   String orderId = rst.getString("orderId");
                   String price = rst.getString("price");
                   String quantity = rst.getString("quantity");
                   String customerId = rst.getString("customer_id");
                   String totalPrice = rst.getString("total_price");
                   String grandPrice = select.getGrandPrice();
                   String username = usernameText.getText();
                   String date = localDateTime.format(formatter);
                   String time = localDateTime.format(timeformatter);
                   
                   prep = connect.prepareStatement("INSERT INTO placed_order(status,customer_id,food_name,food_id,price,quantity,total_price,grand_price,username,date,time) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                   prep.setString(1, status);
                   prep.setString(2, customerId);
                   prep.setString(3, foodName);
                   prep.setString(4, orderId);
                   prep.setString(5, price);
                   prep.setString(6, quantity);
                   prep.setString(7, totalPrice);
                   prep.setString(8, grandPrice);
                   prep.setString(9, username);
                   prep.setString(10,date);
                   prep.setString(11,time);
                   prep.executeUpdate();
                   
                   prep = connect.prepareStatement("DELETE FROM ordered_list WHERE customer_id = '"+ customerIds +"'");
                   prep.executeUpdate();
                   
                   
                }   } catch (SQLException ex) {
                Logger.getLogger(flashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JLabel();
        cardPanel = new javax.swing.JPanel();
        dineTake = new javax.swing.JPanel();
        takeOutButton = new javax.swing.JPanel();
        pictureBox2 = new com.forks.model.pictureBox();
        jLabel1 = new javax.swing.JLabel();
        dinInButton = new javax.swing.JPanel();
        pictureBox3 = new com.forks.model.pictureBox();
        jLabel2 = new javax.swing.JLabel();
        cancelOrderButton1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cancelOrderIcon1 = new javax.swing.JLabel();
        orderMenu = new javax.swing.JPanel();
        foodCategory = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        burgerButton2 = new javax.swing.JPanel();
        pictureBox10 = new com.forks.model.pictureBox();
        jLabel10 = new javax.swing.JLabel();
        chickenButton2 = new javax.swing.JPanel();
        pictureBox11 = new com.forks.model.pictureBox();
        jLabel11 = new javax.swing.JLabel();
        pastaButton2 = new javax.swing.JPanel();
        pictureBox12 = new com.forks.model.pictureBox();
        jLabel12 = new javax.swing.JLabel();
        drinkButton2 = new javax.swing.JPanel();
        pictureBox13 = new com.forks.model.pictureBox();
        jLabel13 = new javax.swing.JLabel();
        friesButton2 = new javax.swing.JPanel();
        pictureBox14 = new com.forks.model.pictureBox();
        jLabel14 = new javax.swing.JLabel();
        productPanel = new javax.swing.JPanel();
        itemScrollBar = new javax.swing.JScrollPane();
        panelItem1 = new com.forks.model.PanelItem();
        sidePanel = new javax.swing.JPanel();
        chickenButton = new javax.swing.JPanel();
        pictureBox4 = new com.forks.model.pictureBox();
        jLabel3 = new javax.swing.JLabel();
        burgerButton1 = new javax.swing.JPanel();
        pictureBox5 = new com.forks.model.pictureBox();
        jLabel4 = new javax.swing.JLabel();
        drinkButton = new javax.swing.JPanel();
        pictureBox6 = new com.forks.model.pictureBox();
        jLabel5 = new javax.swing.JLabel();
        friesButton = new javax.swing.JPanel();
        pictureBox7 = new com.forks.model.pictureBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pictureBox8 = new com.forks.model.pictureBox();
        spagButton = new javax.swing.JPanel();
        pictureBox9 = new com.forks.model.pictureBox();
        jLabel9 = new javax.swing.JLabel();
        orderOption = new javax.swing.JPanel();
        placeOrderButton = new javax.swing.JButton();
        placeOrderButton1 = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        categoryText = new javax.swing.JLabel();
        cancelOrderButton = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cancelOrderIcon = new javax.swing.JLabel();
        orderDetailsPanel = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        placeOrderButton2 = new javax.swing.JButton();
        placeOrderButton3 = new javax.swing.JButton();
        orderScrollBar = new javax.swing.JScrollPane();
        panelItem2 = new com.forks.model.PanelItem();
        pictureBox1 = new com.forks.model.pictureBox();
        jLabel16 = new javax.swing.JLabel();
        grandTotal = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pictureBox15 = new com.forks.model.pictureBox();
        usernameText = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titlePanel.setBackground(new java.awt.Color(152, 69, 17));
        titlePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_close_30px.png"))); // NOI18N
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButtonMouseReleased(evt);
            }
        });
        titlePanel.add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 0, 30, 40));

        getContentPane().add(titlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1450, 40));

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setLayout(new java.awt.CardLayout());

        dineTake.setBackground(new java.awt.Color(255, 153, 51));

        takeOutButton.setBackground(new java.awt.Color(204, 204, 204));
        takeOutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        takeOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                takeOutButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                takeOutButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                takeOutButtonMouseExited(evt);
            }
        });

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/Away_takeaway_icon-removebg-preview.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Take - Out");

        javax.swing.GroupLayout takeOutButtonLayout = new javax.swing.GroupLayout(takeOutButton);
        takeOutButton.setLayout(takeOutButtonLayout);
        takeOutButtonLayout.setHorizontalGroup(
            takeOutButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(takeOutButtonLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(takeOutButtonLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        takeOutButtonLayout.setVerticalGroup(
            takeOutButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(takeOutButtonLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        dinInButton.setBackground(new java.awt.Color(204, 204, 204));
        dinInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dinInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dinInButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dinInButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dinInButtonMouseExited(evt);
            }
        });

        pictureBox3.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/Cutlery_free_vector_icons_designed_by_Freepik-removebg-preview (1).png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Dine - In");

        javax.swing.GroupLayout dinInButtonLayout = new javax.swing.GroupLayout(dinInButton);
        dinInButton.setLayout(dinInButtonLayout);
        dinInButtonLayout.setHorizontalGroup(
            dinInButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dinInButtonLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(pictureBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(dinInButtonLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dinInButtonLayout.setVerticalGroup(
            dinInButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dinInButtonLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(pictureBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        cancelOrderButton1.setBackground(new java.awt.Color(255, 153, 51));
        cancelOrderButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelOrderButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelOrderButton1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelOrderButton1MouseReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Cancel Order");

        cancelOrderIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_Back_Arrow_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout cancelOrderButton1Layout = new javax.swing.GroupLayout(cancelOrderButton1);
        cancelOrderButton1.setLayout(cancelOrderButton1Layout);
        cancelOrderButton1Layout.setHorizontalGroup(
            cancelOrderButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelOrderButton1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelOrderIcon1)
                .addGap(27, 27, 27))
        );
        cancelOrderButton1Layout.setVerticalGroup(
            cancelOrderButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelOrderButton1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(cancelOrderButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelOrderIcon1))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dineTakeLayout = new javax.swing.GroupLayout(dineTake);
        dineTake.setLayout(dineTakeLayout);
        dineTakeLayout.setHorizontalGroup(
            dineTakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dineTakeLayout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addGroup(dineTakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dineTakeLayout.createSequentialGroup()
                        .addComponent(takeOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168)
                        .addComponent(dinInButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(248, 248, 248))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dineTakeLayout.createSequentialGroup()
                        .addComponent(cancelOrderButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        dineTakeLayout.setVerticalGroup(
            dineTakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dineTakeLayout.createSequentialGroup()
                .addComponent(cancelOrderButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addGroup(dineTakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(takeOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dinInButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(273, Short.MAX_VALUE))
        );

        cardPanel.add(dineTake, "card2");

        orderMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foodCategory.setBackground(new java.awt.Color(255, 255, 255));
        foodCategory.setForeground(new java.awt.Color(255, 51, 102));
        foodCategory.setLayout(new java.awt.CardLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        burgerButton2.setBackground(new java.awt.Color(204, 204, 204));
        burgerButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        burgerButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                burgerButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                burgerButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                burgerButton2MouseExited(evt);
            }
        });

        pictureBox10.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/B1.png"))); // NOI18N

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Inter Light", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Burgers");

        javax.swing.GroupLayout burgerButton2Layout = new javax.swing.GroupLayout(burgerButton2);
        burgerButton2.setLayout(burgerButton2Layout);
        burgerButton2Layout.setHorizontalGroup(
            burgerButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(burgerButton2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, burgerButton2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pictureBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        burgerButton2Layout.setVerticalGroup(
            burgerButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(burgerButton2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pictureBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        chickenButton2.setBackground(new java.awt.Color(204, 204, 204));
        chickenButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chickenButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chickenButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chickenButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chickenButton2MouseExited(evt);
            }
        });

        pictureBox11.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/C1.1.png"))); // NOI18N

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Inter Light", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Fried Chicken");

        javax.swing.GroupLayout chickenButton2Layout = new javax.swing.GroupLayout(chickenButton2);
        chickenButton2.setLayout(chickenButton2Layout);
        chickenButton2Layout.setHorizontalGroup(
            chickenButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chickenButton2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chickenButton2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pictureBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        chickenButton2Layout.setVerticalGroup(
            chickenButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chickenButton2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pictureBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pastaButton2.setBackground(new java.awt.Color(204, 204, 204));
        pastaButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pastaButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pastaButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pastaButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pastaButton2MouseExited(evt);
            }
        });

        pictureBox12.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/S1.png"))); // NOI18N

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Inter Light", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Pasta");

        javax.swing.GroupLayout pastaButton2Layout = new javax.swing.GroupLayout(pastaButton2);
        pastaButton2.setLayout(pastaButton2Layout);
        pastaButton2Layout.setHorizontalGroup(
            pastaButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pastaButton2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pastaButton2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pictureBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        pastaButton2Layout.setVerticalGroup(
            pastaButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pastaButton2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pictureBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        drinkButton2.setBackground(new java.awt.Color(204, 204, 204));
        drinkButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        drinkButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drinkButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                drinkButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                drinkButton2MouseExited(evt);
            }
        });

        pictureBox13.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/D1.png"))); // NOI18N

        jLabel13.setBackground(new java.awt.Color(51, 51, 51));
        jLabel13.setFont(new java.awt.Font("Inter Light", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Drinks");

        javax.swing.GroupLayout drinkButton2Layout = new javax.swing.GroupLayout(drinkButton2);
        drinkButton2.setLayout(drinkButton2Layout);
        drinkButton2Layout.setHorizontalGroup(
            drinkButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drinkButton2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(drinkButton2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(pictureBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        drinkButton2Layout.setVerticalGroup(
            drinkButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drinkButton2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pictureBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        friesButton2.setBackground(new java.awt.Color(204, 204, 204));
        friesButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        friesButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                friesButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                friesButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                friesButton2MouseExited(evt);
            }
        });

        pictureBox14.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/SIDES.png"))); // NOI18N

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Inter Light", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Sundaes & Fries");

        javax.swing.GroupLayout friesButton2Layout = new javax.swing.GroupLayout(friesButton2);
        friesButton2.setLayout(friesButton2Layout);
        friesButton2Layout.setHorizontalGroup(
            friesButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friesButton2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(friesButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(pictureBox14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        friesButton2Layout.setVerticalGroup(
            friesButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friesButton2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pictureBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(burgerButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(chickenButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(drinkButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(friesButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pastaButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(55, 55, 55))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(friesButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drinkButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pastaButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chickenButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(burgerButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(369, Short.MAX_VALUE))
        );

        foodCategory.add(mainPanel, "card2");

        productPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        itemScrollBar.setViewportView(panelItem1);

        productPanel.add(itemScrollBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 720));

        foodCategory.add(productPanel, "card3");

        orderMenu.add(foodCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 1230, 720));

        sidePanel.setBackground(new java.awt.Color(204, 204, 204));

        chickenButton.setBackground(new java.awt.Color(204, 204, 204));
        chickenButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chickenButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chickenButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chickenButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chickenButtonMouseExited(evt);
            }
        });

        pictureBox4.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/C1.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Inter Light", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fried Chicken");

        javax.swing.GroupLayout chickenButtonLayout = new javax.swing.GroupLayout(chickenButton);
        chickenButton.setLayout(chickenButtonLayout);
        chickenButtonLayout.setHorizontalGroup(
            chickenButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(chickenButtonLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pictureBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chickenButtonLayout.setVerticalGroup(
            chickenButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chickenButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pictureBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(32, 32, 32))
        );

        burgerButton1.setBackground(new java.awt.Color(204, 204, 204));
        burgerButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        burgerButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                burgerButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                burgerButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                burgerButton1MouseExited(evt);
            }
        });

        pictureBox5.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/B1.png"))); // NOI18N

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Inter Light", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Burgers");

        javax.swing.GroupLayout burgerButton1Layout = new javax.swing.GroupLayout(burgerButton1);
        burgerButton1.setLayout(burgerButton1Layout);
        burgerButton1Layout.setHorizontalGroup(
            burgerButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(burgerButton1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(pictureBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(burgerButton1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        burgerButton1Layout.setVerticalGroup(
            burgerButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(burgerButton1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        drinkButton.setBackground(new java.awt.Color(204, 204, 204));
        drinkButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        drinkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drinkButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                drinkButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                drinkButtonMouseExited(evt);
            }
        });

        pictureBox6.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/D1.png"))); // NOI18N

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Inter Light", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Drinks");

        javax.swing.GroupLayout drinkButtonLayout = new javax.swing.GroupLayout(drinkButton);
        drinkButton.setLayout(drinkButtonLayout);
        drinkButtonLayout.setHorizontalGroup(
            drinkButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(drinkButtonLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(pictureBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        drinkButtonLayout.setVerticalGroup(
            drinkButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drinkButtonLayout.createSequentialGroup()
                .addComponent(pictureBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        friesButton.setBackground(new java.awt.Color(204, 204, 204));
        friesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        friesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                friesButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                friesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                friesButtonMouseExited(evt);
            }
        });

        pictureBox7.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/SIDES.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Inter Light", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Sundaes & Fries");

        javax.swing.GroupLayout friesButtonLayout = new javax.swing.GroupLayout(friesButton);
        friesButton.setLayout(friesButtonLayout);
        friesButtonLayout.setHorizontalGroup(
            friesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(friesButtonLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(pictureBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        friesButtonLayout.setVerticalGroup(
            friesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friesButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(228, 132, 36));

        pictureBox8.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/images/Tempting Temptations12.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pictureBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox8, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addContainerGap())
        );

        spagButton.setBackground(new java.awt.Color(204, 204, 204));
        spagButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        spagButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spagButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                spagButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                spagButtonMouseExited(evt);
            }
        });

        pictureBox9.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/products/S1.png"))); // NOI18N

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Inter Light", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Pasta");

        javax.swing.GroupLayout spagButtonLayout = new javax.swing.GroupLayout(spagButton);
        spagButton.setLayout(spagButtonLayout);
        spagButtonLayout.setHorizontalGroup(
            spagButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
            .addGroup(spagButtonLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(pictureBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        spagButtonLayout.setVerticalGroup(
            spagButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spagButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(friesButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chickenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(drinkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spagButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sidePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(burgerButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(chickenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drinkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spagButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
            .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sidePanelLayout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(burgerButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(632, Short.MAX_VALUE)))
        );

        orderMenu.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 870));

        orderOption.setBackground(new java.awt.Color(255, 153, 51));

        placeOrderButton.setBackground(new java.awt.Color(255, 102, 51));
        placeOrderButton.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        placeOrderButton.setForeground(new java.awt.Color(204, 204, 204));
        placeOrderButton.setText("Check Order");
        placeOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOrderButtonActionPerformed(evt);
            }
        });

        placeOrderButton1.setBackground(new java.awt.Color(255, 102, 51));
        placeOrderButton1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        placeOrderButton1.setForeground(new java.awt.Color(204, 204, 204));
        placeOrderButton1.setText("Place Order");
        placeOrderButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOrderButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout orderOptionLayout = new javax.swing.GroupLayout(orderOption);
        orderOption.setLayout(orderOptionLayout);
        orderOptionLayout.setHorizontalGroup(
            orderOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderOptionLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(placeOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(720, Short.MAX_VALUE))
            .addGroup(orderOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderOptionLayout.createSequentialGroup()
                    .addContainerGap(613, Short.MAX_VALUE)
                    .addComponent(placeOrderButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(237, 237, 237)))
        );
        orderOptionLayout.setVerticalGroup(
            orderOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(placeOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(orderOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderOptionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(placeOrderButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE)))
        );

        orderMenu.add(orderOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 780, 1230, 90));

        header.setBackground(new java.awt.Color(255, 255, 255));

        categoryText.setFont(new java.awt.Font("Inter SemiBold", 0, 36)); // NOI18N
        categoryText.setForeground(new java.awt.Color(51, 51, 51));
        categoryText.setText("Foods & Drinks");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(categoryText, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(479, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categoryText)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        orderMenu.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 60));

        cancelOrderButton.setBackground(new java.awt.Color(255, 255, 255));
        cancelOrderButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelOrderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelOrderButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelOrderButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelOrderButtonMouseReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Cancel Order");

        cancelOrderIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_Back_Arrow_30px.png"))); // NOI18N

        javax.swing.GroupLayout cancelOrderButtonLayout = new javax.swing.GroupLayout(cancelOrderButton);
        cancelOrderButton.setLayout(cancelOrderButtonLayout);
        cancelOrderButtonLayout.setHorizontalGroup(
            cancelOrderButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelOrderButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelOrderIcon)
                .addGap(27, 27, 27))
        );
        cancelOrderButtonLayout.setVerticalGroup(
            cancelOrderButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelOrderButtonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(cancelOrderButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelOrderIcon))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        orderMenu.add(cancelOrderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 0, 190, 60));

        cardPanel.add(orderMenu, "card3");

        orderDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header1.setBackground(new java.awt.Color(204, 204, 204));
        header1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Order Details ");

        jLabel18.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("---------------------------------------------------------------------------------------------------------------------------");

        jLabel19.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("---------------------------------------------------------------------------------------------------------------------------");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 1438, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, header1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        orderDetailsPanel.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1450, 120));

        body.setBackground(new java.awt.Color(204, 204, 204));
        body.setForeground(new java.awt.Color(204, 204, 204));

        placeOrderButton2.setBackground(new java.awt.Color(255, 102, 51));
        placeOrderButton2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        placeOrderButton2.setForeground(new java.awt.Color(204, 204, 204));
        placeOrderButton2.setText("Back");
        placeOrderButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOrderButton2ActionPerformed(evt);
            }
        });

        placeOrderButton3.setBackground(new java.awt.Color(255, 102, 51));
        placeOrderButton3.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        placeOrderButton3.setForeground(new java.awt.Color(204, 204, 204));
        placeOrderButton3.setText("Print Reciept");
        placeOrderButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOrderButton3ActionPerformed(evt);
            }
        });

        orderScrollBar.setViewportView(panelItem2);

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/images/Tempting Temptations12.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Total Price:");

        grandTotal.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        grandTotal.setForeground(new java.awt.Color(51, 51, 51));
        grandTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grandTotal.setText("PHP");

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Pay at the nearest Tasty Temptations Cashier");

        pictureBox15.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_info_25px.png"))); // NOI18N

        usernameText.setBackground(new java.awt.Color(204, 204, 204));
        usernameText.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        usernameText.setForeground(new java.awt.Color(51, 51, 51));
        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Input your name:");

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("(Optional)");

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(placeOrderButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(placeOrderButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(272, 272, 272))
            .addGroup(bodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderScrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130))
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(grandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(pictureBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bodyLayout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21))))
                            .addGroup(bodyLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(52, Short.MAX_VALUE))))
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orderScrollBar)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(grandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pictureBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(8, 8, 8)))))
                .addGap(18, 18, 18)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placeOrderButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(placeOrderButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        orderDetailsPanel.add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1450, 750));

        cardPanel.add(orderDetailsPanel, "card4");

        getContentPane().add(cardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1450, 870));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        returnMenu();
        this.dispose();
        new openScreen().setVisible(true);
    }//GEN-LAST:event_closeButtonMouseClicked

    private void closeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMousePressed
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_close_30px_1.png")));
    }//GEN-LAST:event_closeButtonMousePressed

    private void closeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseReleased
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_close_30px.png")));
    }//GEN-LAST:event_closeButtonMouseReleased

    private void takeOutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_takeOutButtonMouseClicked
        changeCard(orderMenu);
        select.setDineTake("Take-Out");
        customerId();
    }//GEN-LAST:event_takeOutButtonMouseClicked

    private void dinInButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dinInButtonMouseClicked
        changeCard(orderMenu);
        select.setDineTake("Dine-In");
        customerId();
    }//GEN-LAST:event_dinInButtonMouseClicked

    private void placeOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderButtonActionPerformed
        new checkOrderForm().setVisible(true);
    }//GEN-LAST:event_placeOrderButtonActionPerformed

    private void placeOrderButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderButton1ActionPerformed
        changeCard(orderDetailsPanel);
        showOrderedItems();
        getGrandPrice();
    }//GEN-LAST:event_placeOrderButton1ActionPerformed

    private void cancelOrderButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderButtonMousePressed
      cancelOrderIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_Back_Arrow_30px_1.png")));
      int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel order? ", "FORKS" ,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
      if(input == 0){
          returnMenu();
          this.dispose();
          new openScreen().setVisible(true);
      }

    }//GEN-LAST:event_cancelOrderButtonMousePressed

    private void cancelOrderButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderButtonMouseReleased
      cancelOrderIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_Back_Arrow_30px.png")));
    }//GEN-LAST:event_cancelOrderButtonMouseReleased

    private void cancelOrderButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderButton1MousePressed
        cancelOrderIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_Back_Arrow_30px.png")));
          int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel order? ", "FORKS" ,JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
          if(input == 0){
          this.dispose();
          new openScreen().setVisible(true);
      }
    }//GEN-LAST:event_cancelOrderButton1MousePressed

    private void cancelOrderButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderButton1MouseReleased
        cancelOrderIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/forks/mainUtilities/icons8_Back_Arrow_30px_1.png")));
    }//GEN-LAST:event_cancelOrderButton1MouseReleased

    private void burgerButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burgerButton1MouseEntered
        burgerButton1.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_burgerButton1MouseEntered

    private void burgerButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burgerButton1MouseExited
        burgerButton1.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_burgerButton1MouseExited

    private void chickenButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chickenButtonMouseEntered
        chickenButton.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_chickenButtonMouseEntered

    private void chickenButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chickenButtonMouseExited
        chickenButton.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_chickenButtonMouseExited

    private void drinkButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkButtonMouseEntered
        drinkButton.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_drinkButtonMouseEntered

    private void drinkButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkButtonMouseExited
        drinkButton.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_drinkButtonMouseExited

    private void friesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friesButtonMouseEntered
        friesButton.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_friesButtonMouseEntered

    private void friesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friesButtonMouseExited
        friesButton.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_friesButtonMouseExited

    private void spagButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spagButtonMouseEntered
        spagButton.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_spagButtonMouseEntered

    private void spagButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spagButtonMouseExited
        spagButton.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_spagButtonMouseExited

    private void burgerButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burgerButton2MouseEntered
        burgerButton2.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_burgerButton2MouseEntered

    private void burgerButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burgerButton2MouseExited
        burgerButton2.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_burgerButton2MouseExited

    private void chickenButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chickenButton2MouseEntered
        chickenButton2.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_chickenButton2MouseEntered

    private void chickenButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chickenButton2MouseExited
        chickenButton2.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_chickenButton2MouseExited

    private void pastaButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pastaButton2MouseEntered
        pastaButton2.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_pastaButton2MouseEntered

    private void pastaButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pastaButton2MouseExited
        pastaButton2.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_pastaButton2MouseExited

    private void drinkButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkButton2MouseEntered
        drinkButton2.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_drinkButton2MouseEntered

    private void drinkButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkButton2MouseExited
        drinkButton2.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_drinkButton2MouseExited

    private void friesButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friesButton2MouseEntered
        friesButton2.setBackground(new java.awt.Color(153,153,153));
    }//GEN-LAST:event_friesButton2MouseEntered

    private void friesButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friesButton2MouseExited
        friesButton2.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_friesButton2MouseExited

    private void burgerButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burgerButton1MouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showBurgerItems();
    }//GEN-LAST:event_burgerButton1MouseClicked

    private void burgerButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burgerButton2MouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showBurgerItems();
    }//GEN-LAST:event_burgerButton2MouseClicked

    private void chickenButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chickenButtonMouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showChickenItems();
    }//GEN-LAST:event_chickenButtonMouseClicked

    private void drinkButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkButtonMouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showDrinksItems();
    }//GEN-LAST:event_drinkButtonMouseClicked

    private void drinkButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drinkButton2MouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showDrinksItems();
    }//GEN-LAST:event_drinkButton2MouseClicked

    private void friesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friesButtonMouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showSidesItems();
    }//GEN-LAST:event_friesButtonMouseClicked

    private void friesButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friesButton2MouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showSidesItems();
    }//GEN-LAST:event_friesButton2MouseClicked

    private void spagButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spagButtonMouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showPastaItems();
    }//GEN-LAST:event_spagButtonMouseClicked

    private void pastaButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pastaButton2MouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showPastaItems();
    }//GEN-LAST:event_pastaButton2MouseClicked

    private void dinInButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dinInButtonMouseEntered
        dinInButton.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_dinInButtonMouseEntered

    private void dinInButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dinInButtonMouseExited
        dinInButton.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_dinInButtonMouseExited

    private void takeOutButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_takeOutButtonMouseEntered
        takeOutButton.setBackground(new java.awt.Color(255,255,255));
    }//GEN-LAST:event_takeOutButtonMouseEntered

    private void takeOutButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_takeOutButtonMouseExited
        takeOutButton.setBackground(new java.awt.Color(204,204,204));
    }//GEN-LAST:event_takeOutButtonMouseExited

    private void chickenButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chickenButton2MouseClicked
        changeCardFoodCat(productPanel);
        clearAllProducts();
        showChickenItems();
    }//GEN-LAST:event_chickenButton2MouseClicked

    private void cancelOrderButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelOrderButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelOrderButtonMouseClicked

    private void placeOrderButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderButton2ActionPerformed
        changeCard(orderMenu);
        changeCardFoodCat(mainPanel);
        clearAllProducts();
    }//GEN-LAST:event_placeOrderButton2ActionPerformed

    private void placeOrderButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderButton3ActionPerformed
        placeOrder();
        new receiptForm().setVisible(true);
    }//GEN-LAST:event_placeOrderButton3ActionPerformed

    private void usernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextActionPerformed

    
    
    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new forksUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JPanel burgerButton1;
    private javax.swing.JPanel burgerButton2;
    private javax.swing.JPanel cancelOrderButton;
    private javax.swing.JPanel cancelOrderButton1;
    private javax.swing.JLabel cancelOrderIcon;
    private javax.swing.JLabel cancelOrderIcon1;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JLabel categoryText;
    private javax.swing.JPanel chickenButton;
    private javax.swing.JPanel chickenButton2;
    private javax.swing.JLabel closeButton;
    private javax.swing.JPanel dinInButton;
    private javax.swing.JPanel dineTake;
    private javax.swing.JPanel drinkButton;
    private javax.swing.JPanel drinkButton2;
    private javax.swing.JPanel foodCategory;
    private javax.swing.JPanel friesButton;
    private javax.swing.JPanel friesButton2;
    private javax.swing.JLabel grandTotal;
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JScrollPane itemScrollBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel orderDetailsPanel;
    private javax.swing.JPanel orderMenu;
    private javax.swing.JPanel orderOption;
    private javax.swing.JScrollPane orderScrollBar;
    private com.forks.model.PanelItem panelItem1;
    private com.forks.model.PanelItem panelItem2;
    private javax.swing.JPanel pastaButton2;
    private com.forks.model.pictureBox pictureBox1;
    private com.forks.model.pictureBox pictureBox10;
    private com.forks.model.pictureBox pictureBox11;
    private com.forks.model.pictureBox pictureBox12;
    private com.forks.model.pictureBox pictureBox13;
    private com.forks.model.pictureBox pictureBox14;
    private com.forks.model.pictureBox pictureBox15;
    private com.forks.model.pictureBox pictureBox2;
    private com.forks.model.pictureBox pictureBox3;
    private com.forks.model.pictureBox pictureBox4;
    private com.forks.model.pictureBox pictureBox5;
    private com.forks.model.pictureBox pictureBox6;
    private com.forks.model.pictureBox pictureBox7;
    private com.forks.model.pictureBox pictureBox8;
    private com.forks.model.pictureBox pictureBox9;
    private javax.swing.JButton placeOrderButton;
    private javax.swing.JButton placeOrderButton1;
    private javax.swing.JButton placeOrderButton2;
    private javax.swing.JButton placeOrderButton3;
    private javax.swing.JPanel productPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel spagButton;
    private javax.swing.JPanel takeOutButton;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables
}
