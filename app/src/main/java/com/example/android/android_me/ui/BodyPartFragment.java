package com.example.android.android_me.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.IInterface;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class BodyPartFragment extends Fragment implements onAlterImage {
    private static final String TAG = "BodyPartFragment TAG: ";

    private ImageView imageView;
    private List<Integer> imageList;
    private int imageIndex ;

    //Solution-1: Fragment should have an empty constructor.
    public BodyPartFragment() {

    }

    //Solution-2: Fragment is created on onCreatedView()

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_body_part, container, false );
        imageView = (ImageView) view.findViewById( R.id.image );

        if(imageList.size() > 0) {
            imageView.setImageResource( imageList.get(imageIndex) );
        }
        else Log.v(TAG,"Imagelist is null");
        return view;
    }

    public void setImageList(List<Integer> imageList) {
        this.imageList = imageList;
    }


    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }


    @Override
    public void alterImage() {
        if (imageIndex < imageList.size() - 1) {
            imageIndex++;
            Log.v( TAG, "imageIndex increaed: " + imageIndex );
        } else {
            imageIndex = 0;
        }
        imageView.setImageResource( imageList.get( imageIndex ) );
    }
}
