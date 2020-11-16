package com.example.smaexample.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smaexample.Helpers.FirebaseHelper;
import com.example.smaexample.Models.ListExampleModel;
import com.example.smaexample.R;
import com.example.smaexample.adapters.ListExampleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationsFragment extends Fragment {

    private RecyclerView firebaseListRv;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        initializeViews(root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        FirebaseHelper.exampleDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ListExampleModel> list = new ArrayList<>();
                for (DataSnapshot item: snapshot.getChildren()) {
                    ListExampleModel listExampleModel = item.getValue(ListExampleModel.class);
                    list.add(listExampleModel);
                }
                setRecyclerView(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setRecyclerView(List<ListExampleModel> list) {
        ListExampleAdapter listExampleAdapter = new ListExampleAdapter(list);
        firebaseListRv.setLayoutManager(new LinearLayoutManager( getContext()));
        firebaseListRv.setAdapter(listExampleAdapter);
    }

    private void initializeViews(View root) {
        firebaseListRv = root.findViewById(R.id.rv_firebase_list);
    }
}