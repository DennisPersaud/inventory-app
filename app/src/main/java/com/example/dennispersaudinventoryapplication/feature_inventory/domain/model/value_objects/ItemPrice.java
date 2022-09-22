package com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects;

import java.math.BigDecimal;

public final class ItemPrice {
    private final BigDecimal itemPrice;

    public ItemPrice(final BigDecimal price) {
        this.itemPrice = price;
    }
}
