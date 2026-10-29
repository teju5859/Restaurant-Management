
package com.rest.repository;

import com.rest.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository {

    private final List<MenuItem> menuItems = new ArrayList<>();

    public void save(MenuItem item) {
        menuItems.add(item);
    }

    public List<MenuItem> findAll() {
        return menuItems;
    }

    public MenuItem findById(int id) {
        for (MenuItem item : menuItems) {
            if (item.getMenuItemId() == id) {
                return item;
            }
        }
        return null;
    }
}
