package com.example.dennispersaudinventoryapplication.Adapters;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dennispersaudinventoryapplication.Models.Item;
import com.example.dennispersaudinventoryapplication.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final List<Item> itemDataSet;
    private final FragmentCommunicator mFragmentCommunicator;

    /* Initialize the dataset of the Adapter.
     *
     * @param dataset List<Item> containing the data to populate views to be used
     * by RecyclerView.
     */
    public RecyclerAdapter(List<Item> dataSet, FragmentCommunicator fragmentCommunicator) {
        this.itemDataSet = dataSet;
        this.mFragmentCommunicator = fragmentCommunicator;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row, parent, false);
        return new ViewHolder(view, mFragmentCommunicator);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace
        // contents of the view with that element
        holder.tv_gridItemName.setText(String.valueOf(itemDataSet.get(position).getItemName()));
        holder.tv_gridItemCount.setText(String.valueOf(itemDataSet.get(position).getItemCount()));
        holder.tv_gridItemPrice.setText(String.valueOf(itemDataSet.get(position).getItemPrice()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemDataSet.size();
    }


    // Interface to communicate data from view holder to fragments
    public interface FragmentCommunicator {
        boolean onCreateOptionsMenu(Menu menu);

        void respond(int position, String name);
    }


    /*
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        FragmentCommunicator fragmentCommunicator;
        TextView tv_gridItemName;
        TextView tv_gridItemCount;
        TextView tv_gridItemPrice;

        public ViewHolder(@NonNull View view, FragmentCommunicator fragmentCommunicator) {
            super(view);
            this.fragmentCommunicator = fragmentCommunicator;
            tv_gridItemName = view.findViewById(R.id.gridItemName);
            tv_gridItemCount = view.findViewById(R.id.gridItemCount);
            tv_gridItemPrice = view.findViewById(R.id.gridItemPrice);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fragmentCommunicator.respond(getBindingAdapterPosition(), tv_gridItemName.getText().toString());
        }
    }
}
