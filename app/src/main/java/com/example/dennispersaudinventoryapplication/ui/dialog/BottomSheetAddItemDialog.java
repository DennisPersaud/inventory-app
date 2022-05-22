package com.example.dennispersaudinventoryapplication.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dennispersaudinventoryapplication.data.item.Item;
import com.example.dennispersaudinventoryapplication.utils.StandardMessages;
import com.example.dennispersaudinventoryapplication.utils.Validator;
import com.example.dennispersaudinventoryapplication.vm.DataActivityViewModel;
import com.example.dennispersaudinventoryapplication.databinding.BottomSheetLayoutAddItemBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

public class BottomSheetAddItemDialog extends BottomSheetDialogFragment {

    BottomSheetLayoutAddItemBinding bottomSheetLayoutAddItemBinding;
    DataActivityViewModel dataActivityViewModel;
    Item item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottomSheetLayoutAddItemBinding = BottomSheetLayoutAddItemBinding.inflate(inflater, container, false);

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

    private String getAddItemName() {
        return bottomSheetLayoutAddItemBinding.editTextAddItemName.getText().toString();
    }

    private String getAddItemCount() {
        return bottomSheetLayoutAddItemBinding.editTextAddItemQty.getText().toString();
    }

    private String getAddItemPrice() {
        return bottomSheetLayoutAddItemBinding.editTextAddItemPrice.getText().toString();
    }
}