package com.myapplicationdev.android.mydatabook;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

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
        Picasso.with(AboutUsActivity.this).load(imageUrl).placeholder(R.raw.ajax_loader).into(iv);

        Picasso.Builder builder = new Picasso.Builder(AboutUsActivity.this);
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                // when the image fails to load. It will show an error image.
                Picasso.with(AboutUsActivity.this).load(imageUrl).error(R.drawable.error).into(iv);
                exception.printStackTrace();
            }
        });
    }
}