/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;
import java.util.Random;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity implements  View.OnClickListener {

    private static final String TAG = "AndroidMeActivity TAG: ";

    private BodyPartFragment headPartFragment;
    private BodyPartFragment chestPartFragment;
    private BodyPartFragment legPartFragment;
    private Button alterFragment;
    private CheckBox cbHead;
    private CheckBox cbChest;
    private CheckBox cbLeg;

    private List<Integer> headList = AndroidImageAssets.getHeads();
    private List<Integer> chestList = AndroidImageAssets.getBodies();
    private List<Integer> legList = AndroidImageAssets.getLegs();
    private int headIndex;
    private int chestIndex;
    private int legIndex;

    private onAlterImage mHeadAlter;
    private onAlterImage mChestAlter;
    private onAlterImage mLegAlter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        if(getSupportActionBar() != null) {
          getSupportActionBar().hide();
        }

        headPartFragment = new BodyPartFragment();
        chestPartFragment = new BodyPartFragment();
        legPartFragment = new BodyPartFragment();
        alterFragment =(Button) findViewById(R.id.alterFragment);

        cbHead = (CheckBox)findViewById( R.id.cbHead );
        cbChest = (CheckBox)findViewById( R.id.cbChest );
        cbLeg = (CheckBox)findViewById( R.id.cbLeg );

        cbHead.setOnClickListener(this);
        cbChest.setOnClickListener(this);
        cbLeg.setOnClickListener(this);

        headIndex=  5;
        chestIndex=5;
        legIndex = 6;


        headPartFragment.setImageList( headList );
        chestPartFragment.setImageList( chestList );
        legPartFragment.setImageList( legList );

        headPartFragment.setImageIndex( headIndex );
        chestPartFragment.setImageIndex( chestIndex );
        legPartFragment.setImageIndex( legIndex );

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.headPartContainer,headPartFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.chestPartContainer,chestPartFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.legPartContainer,legPartFragment)
                .commit();

        //Getting height of the screen.
        Context context;
        WindowManager windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        if(windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            int height = display.getHeight();
            int width = display.getWidth();

            int requiredHeight = height/3;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cbHead:
                Log.d(TAG,"Clicked on CheckBox Head");
                cbHead.setChecked( true );
                cbChest.setChecked( false );
                cbLeg.setChecked( false );
                alterFragment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        mHeadAlter = headPartFragment;
                        mHeadAlter.alterImage();
                    }
                });
                break;

            case R.id.cbChest:
                cbHead.setChecked( false );
                cbChest.setChecked( true );
                cbLeg.setChecked( false );
                alterFragment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        mChestAlter = chestPartFragment;
                        mChestAlter.alterImage();
                    }
                });
                break;

            case R.id.cbLeg:
                cbHead.setChecked( false );
                cbChest.setChecked( false );
                cbLeg.setChecked( true );
                alterFragment.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        mLegAlter = legPartFragment;
                        mLegAlter.alterImage();
                    }
                });
                break;

            default:
                Log.d(TAG,"Error click: id "+view.getId());
                break;

        }
    }
}
