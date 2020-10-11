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
import com.wintec.degreemap.data.model.Module;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    public static final String BUNDLE_PATHWAY = "BundlePathway";
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
        // set network as default pathway
        int pathway = Module.PATHWAY_NETWORK_ENGINEERING;

        switch (v.getId()) {
            case R.id.card_network:
                pathway = Module.PATHWAY_NETWORK_ENGINEERING;
                break;
            case R.id.card_web:
                pathway = Module.PATHWAY_WEB_DEVELOPMENT;
                break;
            case R.id.card_database:
                pathway = Module.PATHWAY_DATABASE_ARCHITECTURE;
                break;
            case R.id.card_software:
                pathway = Module.PATHWAY_SOFTWARE_ENGINEERING;
                break;
        }

        // set pathway to selected value
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_PATHWAY, pathway);

        // navigate to course fragment
        NavController navController = Navigation.findNavController(v);
        navController.navigate(R.id.action_dashboardFragment_to_courseFragment, bundle);
    }
}
