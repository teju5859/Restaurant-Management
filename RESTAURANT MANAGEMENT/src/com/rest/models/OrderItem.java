
package com.rest.models;

public class OrderItem {

    private int orderItemId;
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(int orderItemId, MenuItem menuItem,
                     int quantity) {
        if (menuItem == null) {
            throw new IllegalArgumentException(
                "Menu item cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero");
        }

        this.orderItemId = orderItemId;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero");
        }
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return menuItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{id=" + orderItemId
            + ", item='" + menuItem.getName() + '\''
            + ", quantity=" + quantity
            + ", subtotal=" + getSubtotal() + "}";
    }
}
