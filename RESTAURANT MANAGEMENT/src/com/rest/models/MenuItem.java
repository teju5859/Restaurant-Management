
package com.rest.models;

public class MenuItem {

    private int menuItemId;
    private String name;
    private String description;
    private double price;
    private boolean available;

    public MenuItem(int menuItemId, String name,
                    String description, double price) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = true;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                "Price cannot be negative");
        }
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "MenuItem{id=" + menuItemId
            + ", name='" + name + '\''
            + ", price=" + price
            + ", available=" + available + "}";
    }
}
