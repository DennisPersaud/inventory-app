package com.example.dennispersaudinventoryapplication.Views;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.dennispersaudinventoryapplication.Adapters.RecyclerAdapter;
import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ViewModel.DataActivityViewModel;
import com.example.dennispersaudinventoryapplication.databinding.DataFragmentBinding;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class DataFragment extends Fragment implements RecyclerAdapter.FragmentCommunicator {
    DataFragmentBinding dataFragmentBinding;
    private DataActivityViewModel dataViewModel;
    private BottomSheetAddItemDialog btmSheetAdd;
    private BottomSheetUpdateItemDialog btmSheetUpdate;

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//        dataFragmentBinding = DataFragmentBinding.inflate(getLayoutInflater());
        dataFragmentBinding = DataFragmentBinding.inflate(inflater, container, false);

        // Disable title bar
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // Initialize data view model provider
        dataViewModel = new ViewModelProvider(this).get(DataActivityViewModel.class);

        // Set recycler view layout
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        dataFragmentBinding.recyclerView.setLayoutManager(layoutManager);

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
        dataFragmentBinding.fabAddItem.setOnClickListener(v -> btmSheetAdd.show(getChildFragmentManager(), "addItemSheet"));
        dataFragmentBinding.fabAddItem.setTooltipText("Add item");

        return dataFragmentBinding.getRoot();
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
                    new MessageFragment(), false);
        }
//        return super.onOptionsItemSelected(item);
        return false;
    }

    // Load all items from database into the recycler view
    private void showItemsOnListView() throws ExecutionException, InterruptedException {
        dataViewModel.loadAllItems().observe(requireActivity(), items -> {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(items, this);
            dataFragmentBinding.recyclerView.setAdapter(recyclerAdapter);
        });
    }

    @Override
    public void respond(int position, String clicked) {
        Bundle bundle = new Bundle();
        bundle.putString("itemName", clicked);
        btmSheetUpdate.setArguments(bundle);
        btmSheetUpdate.show(getChildFragmentManager(), "updateItemSheet");
    }
}
