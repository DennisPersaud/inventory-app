package com.example.dennispersaudinventoryapplication.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.dennispersaudinventoryapplication.Adapters.RecyclerAdapter;
import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ViewModel.DataActivityViewModel;
import com.example.dennispersaudinventoryapplication.databinding.ActivityDataBinding;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class DataActivity extends AppCompatActivity implements RecyclerAdapter.FragmentCommunicator {

    Intent intent;
    ActivityDataBinding activityDataBinding;
    private DataActivityViewModel dataViewModel;
    private BottomSheetAddItemDialog btmSheetAdd;
    private BottomSheetUpdateItemDialog btmSheetUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataBinding = ActivityDataBinding.inflate(getLayoutInflater());
        View dataView = activityDataBinding.getRoot();
        setContentView(dataView);

        // Disable title bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // Initialize data view model provider
        dataViewModel = new ViewModelProvider(this).get(DataActivityViewModel.class);

        // Set recycler view layout
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        activityDataBinding.recyclerView.setLayoutManager(layoutManager);

        // Load all items from database into the grid
        try {
            showItemsOnListView();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Initialize bottom sheet fragments
        btmSheetAdd = new BottomSheetAddItemDialog();
        btmSheetUpdate = new BottomSheetUpdateItemDialog();

        // Floating action button listener
        activityDataBinding.fabAddItem.setOnClickListener(v -> btmSheetAdd.show(getSupportFragmentManager(), "addItemSheet"));
        activityDataBinding.fabAddItem.setTooltipText("Add item");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            // Start MessageActivity on button click
            intent = new Intent(DataActivity.this, MessageActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // Load all items from database into the recycler view
    private void showItemsOnListView() throws ExecutionException, InterruptedException {
        dataViewModel.loadAllItems().observe(this, items -> {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(items, this);
            activityDataBinding.recyclerView.setAdapter(recyclerAdapter);
        });
    }

    @Override
    public void respond(int position, String clicked) {
        Bundle bundle = new Bundle();
        bundle.putString("itemName", clicked);
        btmSheetUpdate.setArguments(bundle);
        btmSheetUpdate.show(getSupportFragmentManager(), "updateItemSheet");
    }
}
