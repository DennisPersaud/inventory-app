package com.example.dennispersaudinventoryapplication.feature_inventory.domain.use_case.item;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.ItemRepository;

public class UpdateItem {
    private final ItemRepository repo;

    public UpdateItem(ItemRepository repository) {
        this.repo = repository;
    }

    public void invoke(Item item) {
        repo.updateItem(item);
    }

}
