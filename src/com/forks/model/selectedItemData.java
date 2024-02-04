
package com.forks.model;

import javax.swing.Icon;


public class selectedItemData {

    /**
     * @return the grandPrice
     */
    public String getGrandPrice() {
        return grandPrice;
    }

    /**
     * @param grandPrice the grandPrice to set
     */
    public void setGrandPrice(String grandPrice) {
        this.grandPrice = grandPrice;
    }

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
     * @return the customerIds
     */
    public String getCustomerIds() {
        return customerIds;
    }

    /**
     * @param customerIds the customerIds to set
     */
    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
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
     * @return the foodId
     */
    public String getFoodId() {
        return foodId;
    }

    /**
     * @param foodId the foodId to set
     */
    public void setFoodId(String foodId) {
        this.foodId = foodId;
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
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
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
     * @return the image
     */
    public Icon getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Icon image) {
        this.image = image;
    }
    
    private String grandPrice;
    private int totalPrice;
    private String customerIds;
    private int customerId;
    private String dineTake;
    private String foodId;
    private String foodName;
    private int price;
    private String size;
    private int quantity;
    private Icon image;
    
}
