
package com.rest.CLI;

import com.rest.models.MenuItem;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.models.table;
import com.rest.services.MenuItemService;
import com.rest.services.OrderService;
import com.rest.services.TableService;

import java.util.List;
import java.util.Scanner;

public class OrderCLI {

    private final Scanner scanner = new Scanner(System.in);
    private final MenuItemService menuService;
    private final OrderService orderService;
    private final TableService tableService;

    private int nextMenuId = 1;
    private int nextOrderId = 1;
    private int nextOrderItemId = 1;

    public OrderCLI(MenuItemService menuService,
                    OrderService orderService,
                    TableService tableService) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.tableService = tableService;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== RESTAURANT MANAGEMENT ===");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View Menu");
            System.out.println("3. View Tables");
            System.out.println("4. Create Order");
            System.out.println("5. View Orders");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    viewMenu();
                    break;
                case 3:
                    viewTables();
                    break;
                case 4:
                    createOrder();
                    break;
                case 5:
                    viewOrders();
                    break;
                case 0:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addMenuItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        if (price < 0) {
            System.out.println("Price cannot be negative");
            return;
        }

        MenuItem item = new MenuItem(
            nextMenuId++, name, description, price
        );

        menuService.addMenuItem(item);
        System.out.println("Menu item added successfully!");
    }

    private void viewMenu() {
        List<MenuItem> items = menuService.getAllMenuItems();

        if (items.isEmpty()) {
            System.out.println("Menu is empty");
            return;
        }

        for (MenuItem item : items) {
            System.out.println(item);
        }
    }

    private void viewTables() {
        for (table t : tableService.getAllTables()) {
            System.out.println(t);
        }
    }

    private void createOrder() {
        viewTables();

        System.out.print("Enter table ID: ");
        int tableId = Integer.parseInt(scanner.nextLine());

        table selectedTable = null;

        for (table t : tableService.getAllTables()) {
            if (t.getId() == tableId) {
                selectedTable = t;
                break;
            }
        }

        if (selectedTable == null) {
            System.out.println("Table not found");
            return;
        }

        if (selectedTable.getStatus() == table.AVAILABILITY.OCCUPIED) {
            System.out.println("Table is already occupied");
            return;
        }

        if (menuService.getAllMenuItems().isEmpty()) {
            System.out.println("Add menu items before creating an order");
            return;
        }

        Order order = new Order(nextOrderId++, selectedTable);

        while (true) {
            viewMenu();

            System.out.print("Enter menu item ID (0 to finish): ");
            int itemId = Integer.parseInt(scanner.nextLine());

            if (itemId == 0) {
                break;
            }

            MenuItem item = menuService.getMenuItemById(itemId);

            if (item == null) {
                System.out.println("Menu item not found");
                continue;
            }

            if (!item.isAvailable()) {
                System.out.println("Item is not available");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (quantity <= 0) {
                System.out.println("Quantity must be positive");
                continue;
            }

            OrderItem orderItem = new OrderItem(
                nextOrderItemId++, item, quantity
            );

            order.addItem(orderItem);
            System.out.println("Item added to order");
        }

        if (order.getItems().isEmpty()) {
            System.out.println("No items added. Order not saved.");
            return;
        }

        orderService.createOrder(order);
        selectedTable.occupyTable();

        System.out.println("\n=== ORDER CREATED ===");
        System.out.println(order);
        System.out.println("Total: Rs. " + order.getTotal());
    }

    private void viewOrders() {
        List<Order> orders = orderService.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found");
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
