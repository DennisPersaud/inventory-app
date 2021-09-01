package com.example.dennispersaudinventoryapplication.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.dennispersaudinventoryapplication.Models.Item;
import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ViewModel.DataActivityViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

public class BottomSheetAddItemDialog extends BottomSheetDialogFragment {


    private DataActivityViewModel dataViewModel;
    private View dataActivity;
    private Button addButton;
    private TextView itemName, itemCount, itemPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout_add_item, container, false);

        initViews(v);

        addButton.setOnClickListener(v1 -> {
            if (!getItemName().isEmpty() && !getItemPrice().isEmpty() && !getItemCount().isEmpty()) {
                Item newItem = new Item(getItemName(), Integer.parseInt(getItemPrice()), Integer.parseInt(getItemCount())); dataViewModel.insertItem(newItem); } else { Snackbar.make(dataActivity, "Please enter all fields.", Snackbar.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private void initViews(View v) {
        dataActivity = v.findViewById(R.id.dataActivity);
        dataViewModel = new ViewModelProvider(this).get(DataActivityViewModel.class);
        addButton = v.findViewById(R.id.buttonAdd);
        itemName = v.findViewById(R.id.editTextItemName);
        itemPrice = v.findViewById(R.id.editTextItemPrice);
        itemCount =  v.findViewById(R.id.editTextItemQty);

    }

    public String getItemName() {
        return itemName.getText().toString();
    }

    public String getItemCount() {
        return itemCount.getText().toString();
    }

    public String getItemPrice() {
        return itemPrice.getText().toString();
    }

}
