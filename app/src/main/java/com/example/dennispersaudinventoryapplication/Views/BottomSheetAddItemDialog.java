package com.example.dennispersaudinventoryapplication.Views;

import android.os.Bundle;
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
    private TextView addItemName, addItemCount, addItemPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout_add_item, container, false);

        initViews(v);

        addButton.setOnClickListener(v1 -> {
            if (!getAddItemName().isEmpty() && !getAddItemPrice().isEmpty() && !getAddItemCount().isEmpty()) {
                Item newItem = new Item(getAddItemName(), Integer.parseInt(getAddItemPrice()), Integer.parseInt(getAddItemCount()));
                dataViewModel.insertItem(newItem);
            } else {
                Snackbar.make(dataActivity, "Please enter all fields.", Snackbar.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private void initViews(View v) {
        dataActivity = v.findViewById(R.id.dataActivity);
        dataViewModel = new ViewModelProvider(this).get(DataActivityViewModel.class);
        addButton = v.findViewById(R.id.buttonAdd);
        addItemName = v.findViewById(R.id.editTextAddItemName);
        addItemPrice = v.findViewById(R.id.editTextAddItemPrice);
        addItemCount =  v.findViewById(R.id.editTextAddItemQty);

    }

    public String getAddItemName() {
        return addItemName.getText().toString();
    }

    public String getAddItemCount() {
        return addItemCount.getText().toString();
    }

    public String getAddItemPrice() {
        return addItemPrice.getText().toString();
    }

}
