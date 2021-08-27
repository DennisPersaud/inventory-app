package com.example.dennispersaudinventoryapplication.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dennispersaudinventoryapplication.Models.Item;
import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ViewModel.DataActivityViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class BottomSheetUpdateItemDialog extends BottomSheetDialogFragment {

    private Button updateButton, deleteButton;
    private TextView gridItemName, itemPrice, itemCount;
    private DataActivityViewModel dataViewModel;
    private View dataActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout_update_item, container, false);
        initViews(v);

        updateButton.setOnClickListener(v1 -> {

            // TODO: Fix update item logic

            if (!getItemName().isEmpty() && !getItemPrice().isEmpty() && !getItemCount().isEmpty()) {
                Log.d("TEST", "Update Button Clicked!");
                // search db for item id by item name
                // get item from item clicked on grid
                try {
                    Item updateItem = dataViewModel.getItemByName(getItemName());
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                // update item in db by id
    //            dataViewModel.updateItem(updateItem);
    //            dataViewModel.getItemByName();
            } else {
                Snackbar.make(dataActivity, "Please enter all the fields.", Snackbar.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(v1 -> {

            //TODO: FIx delete item logic

            if (!getItemName().isEmpty() && !getItemPrice().isEmpty() && !getItemCount().isEmpty()) {
                Log.d("TEST", "Delete Button Clicked!");
                try {
                    // search db for item id by item name
                    // Get item from item clicked on grid
                    Item deleteItem = dataViewModel.getItemByName(getItemName());
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                    // delete item in db by id

            } else {
                Snackbar.make(dataActivity, "Please enter all the fields.", Snackbar.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public void initViews(View v) {
        dataActivity = v.findViewById(R.id.dataActivity);
        updateButton = v.findViewById(R.id.buttonUpdate);
        deleteButton = v.findViewById(R.id.buttonDelete);
        gridItemName = v.findViewById(R.id.gridItemName);
        itemPrice = v.findViewById(R.id.editTextItemPrice);
        itemCount = v.findViewById(R.id.editTextItemQty);
    }

    public String getItemName() {
        return gridItemName.getText().toString();
    }

    public String getItemPrice() {
        return itemPrice.getText().toString();
    }

    public String getItemCount() {
        return itemCount.getText().toString();
    }
}
