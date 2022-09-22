package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.Item;
import com.example.dennispersaudinventoryapplication.databinding.BottomSheetLayoutUpdateItemBinding;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemCount;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemName;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.ItemPrice;
import com.example.dennispersaudinventoryapplication.feature_inventory.utils.StandardMessages;
import com.example.dennispersaudinventoryapplication.feature_inventory.utils.Validator;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.vm.DataActivityViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.concurrent.ExecutionException;

public class BottomSheetUpdateItemDialog extends BottomSheetDialogFragment {

    BottomSheetLayoutUpdateItemBinding bottomSheetLayoutUpdateItemBinding;
    private DataActivityViewModel dataActivityViewModel;
    Item updateItem;
    Item deleteItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        bottomSheetLayoutUpdateItemBinding = BottomSheetLayoutUpdateItemBinding.inflate(inflater, container, false);
        dataActivityViewModel = new ViewModelProvider(this).get(DataActivityViewModel.class);

        bottomSheetLayoutUpdateItemBinding.buttonUpdate.setOnClickListener(v1 -> {
            try {
                boolean validateItemUpdate = Validator.validateUpdateItem(getUpdateItemPrice(), getUpdateItemCount());
                if (validateItemUpdate) {
                        updateItem = dataActivityViewModel.getItemByName(getGridItemName());
                        updateItem.setItemCount(Integer.parseInt(getUpdateItemCount()));
                        updateItem.setItemPrice(Integer.parseInt(getUpdateItemPrice()));
                        dataActivityViewModel.updateItem(updateItem);
                } else {
                    StandardMessages.displayToast(bottomSheetLayoutUpdateItemBinding.bottomSheetUpdateItemDialog, "Please enter all the fields.");
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        bottomSheetLayoutUpdateItemBinding.buttonDelete.setOnClickListener(v1 -> {
            try {
                deleteItem = dataActivityViewModel.getItemByName(getGridItemName());
                dataActivityViewModel.deleteItem(deleteItem);
                this.dismiss();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        return bottomSheetLayoutUpdateItemBinding.getRoot();
    }

    private ItemName getGridItemName() {
        assert this.getArguments() != null;
        return this.getArguments().getString("itemName");
    }

    private ItemPrice getUpdateItemPrice() {
        // TODO: Convert from String to ItemPrice
        return bottomSheetLayoutUpdateItemBinding.editTextUpdateItemPrice.getText().toString();
    }

    private ItemCount getUpdateItemCount() {
        // TODO: Convert from String to ItemCount
        return bottomSheetLayoutUpdateItemBinding.editTextUpdateItemQty.getText().toString();
    }
}