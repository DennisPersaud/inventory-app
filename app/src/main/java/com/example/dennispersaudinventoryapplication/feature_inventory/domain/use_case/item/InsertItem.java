package com.example.dennispersaudinventoryapplication.feature_inventory.domain.use_case.item;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.ItemRepository;

public class InsertItem {
    private final ItemRepository repo;

    public InsertItem(ItemRepository repository) {
        this.repo = repository;
    }

    public void invoke(Item item) {
        repo.insertItem(item);
    }
}
