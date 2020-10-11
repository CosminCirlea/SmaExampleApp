package com.example.smaexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListExampleAdapter extends RecyclerView.Adapter<ListExampleViewHolder> {
    private List<ListExampleModel> choicesList;
    private Context context;

    @NonNull
    @Override
    public ListExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.row_example_list, parent, false);
        ListExampleViewHolder viewHolder = new ListExampleViewHolder(contactView);
        return viewHolder;
    }

    public ListExampleAdapter(List<ListExampleModel> waterGlassesList) {
        this.choicesList = waterGlassesList;
    }

    @Override
    public void onBindViewHolder(@NonNull ListExampleViewHolder holder, int position) {
        final ListExampleModel glassModel = choicesList.get(position);
        holder.setValues(glassModel.getFirstname(), glassModel.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return choicesList.size();
    }
}
