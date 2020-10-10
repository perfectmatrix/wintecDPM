package com.wintec.degreemap.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.wintec.degreemap.R;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private CardView networkCard, webCard, databasedCard, softwareCard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        networkCard = v.findViewById(R.id.card_network);
        webCard = v.findViewById(R.id.card_web);
        databasedCard = v.findViewById(R.id.card_database);
        softwareCard = v.findViewById(R.id.card_software);

        networkCard.setOnClickListener(this);
        webCard.setOnClickListener(this);
        databasedCard.setOnClickListener(this);
        softwareCard.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(v);
        navController.navigate(R.id.action_dashboardFragment_to_courseFragment);

        // TODO: Pass args to display courseFragment with the selected pathway configuration
    }
}
