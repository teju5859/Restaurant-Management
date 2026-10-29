
package com.rest;

import com.rest.CLI.OrderCLI;
import com.rest.services.MenuItemService;
import com.rest.services.OrderService;
import com.rest.services.TableService;

public class Main {
    public static void main(String[] args) {

        MenuItemService menuService = new MenuItemService();
        OrderService orderService = new OrderService();
        TableService tableService = new TableService();

        OrderCLI cli = new OrderCLI(
            menuService, orderService, tableService
        );

        cli.start();
    }
}
