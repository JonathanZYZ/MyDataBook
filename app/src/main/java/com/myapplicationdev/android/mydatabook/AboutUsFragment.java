package com.myapplicationdev.android.mydatabook;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class AboutUsFragment extends Fragment {


    ImageView iv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.aboutusfragment, container, false);

        iv = view.findViewById(R.id.iv);

        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/80/Republic_Polytechnic_Logo.jpg";
        
        // .placeholder to hold the loader image when the imageUrl is still loading.
        Picasso.with(getActivity()).load(imageUrl).placeholder(R.raw.ajax_loader).into(iv);

        Picasso.Builder builder = new Picasso.Builder(getActivity());
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                // when the image fails to load. It will show an error image.
                Picasso.with(getActivity()).load(imageUrl).error(R.drawable.error).into(iv);
                exception.printStackTrace();
            }
        });



        return view;
    }
}