package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.databinding.BottomSheetLayoutAddItemBinding;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemCount;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemName;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemPrice;
import com.example.dennispersaudinventoryapplication.feature_inventory.utils.StandardMessages;
import com.example.dennispersaudinventoryapplication.feature_inventory.utils.Validator;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.vm.DataActivityViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetAddItemDialog extends BottomSheetDialogFragment {

    BottomSheetLayoutAddItemBinding bottomSheetLayoutAddItemBinding;
    private DataActivityViewModel dataActivityViewModel;
    Item item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottomSheetLayoutAddItemBinding = BottomSheetLayoutAddItemBinding.inflate(inflater, container, false);
        dataActivityViewModel = new ViewModelProvider(this).get(DataActivityViewModel.class);

        bottomSheetLayoutAddItemBinding.buttonAdd.setOnClickListener(v1 -> {
            boolean validateItem = Validator.validateItemInfo(getAddItemName(), getAddItemPrice(), getAddItemCount());
            if(validateItem) {
                item.setItemPrice(Integer.parseInt(getAddItemPrice()));
                item.setItemCount(Integer.parseInt(getAddItemCount()));
                dataActivityViewModel.insertItem(item.getInstance());
            } else {
                StandardMessages.displayToast(bottomSheetLayoutAddItemBinding.bottomSheetAddItemDialog, "Please enter all fields.");
            }
        });
        return bottomSheetLayoutAddItemBinding.getRoot();
    }

    private ItemName getAddItemName() {
        // TODO: Convert from String to ItemName
        return bottomSheetLayoutAddItemBinding.editTextAddItemName.getText().toString();
    }

    private ItemCount getAddItemCount() {
        // TODO: Convert from String to ItemCount
        return bottomSheetLayoutAddItemBinding.editTextAddItemQty.getText().toString();
    }

    private ItemPrice getAddItemPrice() {
        // TODO: Convert from String to ItemPrice
        return bottomSheetLayoutAddItemBinding.editTextAddItemPrice.getText().toString();
    }
}