
package com.rest.services;

import com.rest.models.MenuItem;
import com.rest.repository.MenuItemRepository;
import java.util.List;

public class MenuItemService {

    private final MenuItemRepository repository =
            new MenuItemRepository();

    public void addMenuItem(MenuItem item) {
        repository.save(item);
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public MenuItem getMenuItemById(int id) {
        return repository.findById(id);
    }
}
