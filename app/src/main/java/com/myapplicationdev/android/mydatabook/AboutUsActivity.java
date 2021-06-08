package com.myapplicationdev.android.mydatabook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

public class AboutUsActivity extends AppCompatActivity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        iv = findViewById(R.id.iv);

        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/80/Republic_Polytechnic_Logo.jpg";

        // .placeholder to hold the loader image when the imageUrl is still loading.
        // . error to display error.png if the image is fails to load.
        Glide.with(AboutUsActivity.this)
                .load(imageUrl)
                .apply(new RequestOptions().placeholder(R.drawable.ajax_loader).error(R.drawable.error))
                .into(iv);


    }
}