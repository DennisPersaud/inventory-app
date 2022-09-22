package com.example.dennispersaudinventoryapplication.feature_inventory.domain.use_case.item;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.ItemRepository;

public class DeleteItem {
    private final ItemRepository repo;

    public DeleteItem(ItemRepository repository) {
        this.repo = repository;
    }

    public void invoke(Item item) {
        repo.deleteItem(item);
    }
}
