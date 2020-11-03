package com.wintec.degreemap.ui.student.student_dashboard;

import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.KEY_SELECTED_PATHWAY;
import static com.wintec.degreemap.util.Constants.PATHWAY_DATABASE_ARCHITECTURE;
import static com.wintec.degreemap.util.Constants.PATHWAY_NETWORK_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_SOFTWARE_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

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
        // set network as default pathway
        String pathway = PATHWAY_NETWORK_ENGINEERING;

        switch (v.getId()) {
            case R.id.card_network:
                pathway = PATHWAY_NETWORK_ENGINEERING;
                break;
            case R.id.card_web:
                pathway = PATHWAY_WEB_DEVELOPMENT;
                break;
            case R.id.card_database:
                pathway = PATHWAY_DATABASE_ARCHITECTURE;
                break;
            case R.id.card_software:
                pathway = PATHWAY_SOFTWARE_ENGINEERING;
                break;
        }

        // Save selectedPathway on SharedPreferences
        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_SELECTED_PATHWAY, pathway);
        editor.apply();

        // navigate to course fragment
        NavController navController = Navigation.findNavController(v);
        navController.navigate(R.id.action_dashboardFragment_to_courseFragment);
    }
}
