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

import java.util.concurrent.ExecutionException;

public class BottomSheetUpdateItemDialog extends BottomSheetDialogFragment {

    private Button updateButton, deleteButton;
    private TextView updateItemPrice, updateItemCount;
    private DataActivityViewModel dataViewModel;
    private View dataActivityView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout_update_item, container, false);
        initViews(v);

        updateButton.setOnClickListener(v1 -> {

            // TODO: Fix update item logic

            if (!getUpdateItemPrice().isEmpty() && !getUpdateItemCount().isEmpty()) {
                try {
                    Item updateItem = dataViewModel.getItemByName(getGridItemName());
                    updateItem.setItemCount(Integer.parseInt(getUpdateItemCount()));
                    updateItem.setItemPrice(Integer.parseInt(getUpdateItemPrice()));
                    dataViewModel.updateItem(updateItem);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Snackbar.make(dataActivityView, "Please enter all the fields.", Snackbar.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(v1 -> {
            try {
                Item deleteItem = dataViewModel.getItemByName(getGridItemName());
                dataViewModel.deleteItem(deleteItem);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        return v;
    }

    public void initViews(View v) {
        dataActivityView = v.findViewById(R.id.dataActivity);
        dataViewModel = new ViewModelProvider(requireActivity()).get(DataActivityViewModel.class);
        updateButton = v.findViewById(R.id.buttonUpdate);
        deleteButton = v.findViewById(R.id.buttonDelete);
        updateItemPrice = v.findViewById(R.id.editTextUpdateItemPrice);
        updateItemCount = v.findViewById(R.id.editTextUpdateItemQty);
    }

    public String getGridItemName() {
        return DataActivity.getClickedItem();
    }

    public String getUpdateItemPrice() {
        return updateItemPrice.getText().toString();
    }

    public String getUpdateItemCount() {
        return updateItemCount.getText().toString();
    }

}
