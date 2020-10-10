package com.wintec.degreemap.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.wintec.degreemap.R;
import com.wintec.degreemap.Student_Pathway_Database;
import com.wintec.degreemap.Student_Pathway_Network;
import com.wintec.degreemap.Student_Pathway_Software;
import com.wintec.degreemap.Student_Pathway_Web;

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
        Intent i = null;
        switch (v.getId()) {
            case R.id.card_network:
                i = new Intent(getActivity(), Student_Pathway_Network.class);
                startActivity(i);
                break;
            case R.id.card_web:
                i = new Intent(getActivity(), Student_Pathway_Web.class);
                startActivity(i);
                break;
            case R.id.card_database:
                i = new Intent(getActivity(), Student_Pathway_Database.class);
                startActivity(i);
                break;
            case R.id.card_software:
                i = new Intent(getActivity(), Student_Pathway_Software.class);
                startActivity(i);
                break;
        }
    }
}
