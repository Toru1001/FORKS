
package com.forks.model;

import javax.swing.Icon;

public class orderItemModel {

    /**
     * @return the totalPrice
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the dineTake
     */
    public String getDineTake() {
        return dineTake;
    }

    /**
     * @param dineTake the dineTake to set
     */
    public void setDineTake(String dineTake) {
        this.dineTake = dineTake;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the foodName
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * @param foodName the foodName to set
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the img
     */
    public Icon getImg() {
        return image;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Icon img) {
        this.image = image;
    }
    
    public orderItemModel(String dineTake,int customerId, String foodName,String orderId,int price, int quantity, Icon image, int totalPrice){
        this.totalPrice = totalPrice;
        this.dineTake = dineTake;
        this.customerId = customerId;
        this.foodName = foodName;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }
    
    private int totalPrice;
    private String dineTake;
    private int customerId;
    private String orderId;
    private String foodName;
    private int price;
    private int quantity;
    private Icon image;
}
