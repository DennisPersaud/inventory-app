package com.example.dennispersaudinventoryapplication.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dennispersaudinventoryapplication.Models.Item;
import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ViewModel.DataActivityViewModel;
import com.example.dennispersaudinventoryapplication.Utils.GridAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class DataActivity extends AppCompatActivity {

    // Initialize variables
    private TextView itemName, itemPrice, itemCount;
    private Button btnAdd, btnDelete, btnUpdate, btnNotify;
    private View dataActivity;
    private GridView grid;
    private FloatingActionButton fabAddItem;
    private DataActivityViewModel dataViewModel;
    private BottomSheetAddItemDialog btmSheetAdd;
    private BottomSheetUpdateItemDialog btmSheetUpdate;
    Intent intent;
    Item itemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        // Disable title bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // Initialize views
        initViews();

        // Load all items from database into the grid
        try {
            showItemsOnListView();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Floating action button listener
        fabAddItem.setOnClickListener(v -> {
            btmSheetAdd.show(getSupportFragmentManager(), "additemsheet");
        });

        grid.setOnItemClickListener((parent, view, position, id) ->
                btmSheetUpdate.show(getSupportFragmentManager(), "updateitemsheet"));
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

    // Load all items from database into the grid
    private void showItemsOnListView() throws ExecutionException, InterruptedException {

        // TODO: Replace dataViewModel.getAllItems() with dataViewModel.loadAllItems()

        GridAdapter gridAdapter = new GridAdapter(DataActivity.this, dataViewModel.getAllItems());
        grid.setAdapter(gridAdapter);
    }


    // Initialize views
    private void initViews() {

        dataActivity = findViewById(R.id.dataActivity);
        fabAddItem = findViewById(R.id.fab_addItem);
        grid = findViewById(R.id.gridView);

        dataViewModel = new ViewModelProvider(this).get(DataActivityViewModel.class);
        btmSheetAdd = new BottomSheetAddItemDialog();
        btmSheetUpdate = new BottomSheetUpdateItemDialog();
    }

    public String getItemName() {
        return itemName.getText().toString();
    }

    public String getItemPrice() {
        return itemPrice.getText().toString();
    }

    public String getItemCount() {
        return itemCount.getText().toString();
    }
}