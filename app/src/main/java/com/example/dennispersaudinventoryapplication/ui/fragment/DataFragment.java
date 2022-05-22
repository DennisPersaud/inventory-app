package com.example.dennispersaudinventoryapplication.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.adapters.RecyclerAdapter;
import com.example.dennispersaudinventoryapplication.data.item.Item;
import com.example.dennispersaudinventoryapplication.databinding.DataFragmentBinding;
import com.example.dennispersaudinventoryapplication.ui.NavigationHost;
import com.example.dennispersaudinventoryapplication.ui.dialog.BottomSheetAddItemDialog;
import com.example.dennispersaudinventoryapplication.ui.dialog.BottomSheetUpdateItemDialog;
import com.example.dennispersaudinventoryapplication.vm.DataActivityViewModel;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class DataFragment extends Fragment implements RecyclerAdapter.FragmentCommunicator {

    BottomSheetAddItemDialog bottomSheetAddItemDialog;
    BottomSheetUpdateItemDialog bottomSheetUpdateItemDialog;
    DataActivityViewModel dataActivityViewModel;
    DataFragmentBinding dataFragmentBinding;

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        dataFragmentBinding = DataFragmentBinding.inflate(inflater, container, false);

        // Disable title bar
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // Initialize data view model provider
//        getDataViewModel();

        // Set recycler view layout
        GridLayoutManager layoutManager = getLayoutManager();
        dataFragmentBinding.recyclerView.setLayoutManager(layoutManager);

        // Load all items from database into the grid
        try {
            showItemsOnListView();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Initialize bottom sheet fragments
        bottomSheetAddItemDialog.requireActivity();
        bottomSheetUpdateItemDialog.requireActivity();

        // Floating action button listener
        dataFragmentBinding.fabAddItem.setOnClickListener(v -> bottomSheetAddItemDialog.show(
                getChildFragmentManager(), "addItemSheet"));
        dataFragmentBinding.fabAddItem.setTooltipText("Add item");

        return dataFragmentBinding.getRoot();
    }

    private GridLayoutManager getLayoutManager() {
        return new GridLayoutManager(requireActivity(), 3);
    }

    private static RecyclerAdapter getRecyclerAdapter(List<Item> dataSet, RecyclerAdapter.FragmentCommunicator fragmentCommunicator) {
        return new RecyclerAdapter(dataSet, fragmentCommunicator);
    }

    // Load all items from database into the recycler view
    private void showItemsOnListView() throws ExecutionException, InterruptedException {
        dataActivityViewModel.loadAllItems().observe(requireActivity(), items -> dataFragmentBinding.
                recyclerView.setAdapter(getRecyclerAdapter(items, this)));
    }

    @Override
    public void onDestroyView() {
        if (dataFragmentBinding != null) {
            dataFragmentBinding.getRoot().removeAllViews();
        }
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_data, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = requireActivity().getMenuInflater();
//        inflater.inflate(R.menu.menu_data, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            // Start MessageActivity on button click
            Log.d("MESSAGE: ", "THIS CODE WAS EXECUTED");
            ((NavigationHost) requireActivity()).navigateTo(
                    getMessageFragment(), true);
        }
//        return super.onOptionsItemSelected(item);
        return false;
    }

    private MessageFragment getMessageFragment() {
        return new MessageFragment();
    }

    @Override
    public void respond(int position, String clicked) {
        getBundle().putString("itemName", clicked);
        bottomSheetUpdateItemDialog.setArguments(getBundle());
        bottomSheetUpdateItemDialog.show(getChildFragmentManager(), "updateItemSheet");
    }

    private Bundle getBundle() {
        return new Bundle();
    }
}
