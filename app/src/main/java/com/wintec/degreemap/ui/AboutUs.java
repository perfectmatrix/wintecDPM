package com.wintec.degreemap.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.wintec.degreemap.R;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Play video: https://www.youtube.com/watch?v=3T0BeP1czcE
        VideoView videoView = findViewById(R.id.video_view);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_wintec_it;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    // Method for each [Know More] button to jump to website
    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.btn_km_bachelor_it:
                String url1 = "http://www.google.com";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url1));
                startActivity(i);
                break;
            case R.id.btn_km_gallagher:
                String url2 = "https://www.youtube.com/";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url2));
                startActivity(i);
                break;
            case R.id.btn_km_design_factory:
                String url3 = "https://github.com/";
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url3));
                startActivity(i);
                break;
        }
    }

}