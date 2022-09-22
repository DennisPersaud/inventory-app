package com.example.dennispersaudinventoryapplication.feature_inventory.domain.use_case.item;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.repository.ItemRepository;

public class GetAllItems {
    private final ItemRepository repo;

    public GetAllItems(ItemRepository repository) {
        this.repo = repository;
    }

    public void invoke() {
        repo.loadAllItems();
    }


}
