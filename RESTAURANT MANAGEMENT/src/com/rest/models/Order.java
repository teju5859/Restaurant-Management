
package com.rest.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    public enum STATUS {
        PLACED,
        PREPARING,
        SERVED,
        CANCELLED
    }

    private int orderId;
    private table restaurantTable;
    private List<OrderItem> items;
    private STATUS status;

    public Order(int orderId, table restaurantTable) {
        if (restaurantTable == null) {
            throw new IllegalArgumentException(
                "Table cannot be null");
        }

        this.orderId = orderId;
        this.restaurantTable = restaurantTable;
        this.items = new ArrayList<>();
        this.status = STATUS.PLACED;
    }

    public int getOrderId() {
        return orderId;
    }

    public table getRestaurantTable() {
        return restaurantTable;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public STATUS getStatus() {
        return status;
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException(
                "Order item cannot be null");
        }
        if (status == STATUS.CANCELLED) {
            throw new IllegalStateException(
                "Cannot add items to a cancelled order");
        }

        items.add(item);
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public void setStatus(STATUS status) {
        if (status == null) {
            throw new IllegalArgumentException(
                "Status cannot be null");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{id=" + orderId
            + ", tableId=" + restaurantTable.getId()
            + ", items=" + items
            + ", total=" + getTotal()
            + ", status=" + status + "}";
    }
}
