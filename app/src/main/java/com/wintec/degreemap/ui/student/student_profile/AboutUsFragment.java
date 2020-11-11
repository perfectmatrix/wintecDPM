package com.wintec.degreemap.ui.student.student_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.wintec.degreemap.R;

public class AboutUsFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        view.findViewById(R.id.btn_km_bachelor_it).setOnClickListener(this);
        view.findViewById(R.id.btn_km_gallagher).setOnClickListener(this);
        view.findViewById(R.id.btn_km_design_factory).setOnClickListener(this);

        return view;
    }

    // Method for each [Know More] button to jump to website
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.btn_km_bachelor_it:
                String url1 = "https://www.wintec.ac.nz/study-at-wintec/courses/information-technology/bachelor-of-applied-information-technology";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url1));
                startActivity(i);
                break;
            case R.id.btn_km_gallagher:
                String url2 = "https://www.wintec.ac.nz/about-wintec/news/article/2018/12/02/internship-a-win-win-for-gallagher-and-wintec-it-student";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url2));
                startActivity(i);
                break;
            case R.id.btn_km_design_factory:
                String url3 = "https://www.wintec.ac.nz/designfactory";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url3));
                startActivity(i);
                break;
        }
    }
}