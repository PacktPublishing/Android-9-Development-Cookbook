package com.packtpub.slideshow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SlideFragment extends Fragment {

    private int mImageResourceID;

    public SlideFragment() {
    }

    public void setImage(int resourceID) {
        mImageResourceID = resourceID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_slide, container, false);
        AppCompatImageView imageView = rootView.findViewById(R.id.imageView);
        imageView.setImageResource(mImageResourceID);
        return rootView;
    }
}
