package ru.vshilin;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import ru.yandex.yandexmapkit.*;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;

/**
 * BalloonOverlay.java
 *
 * This file is a part of the Yandex Map Kit.
 *
 * Version for Android © 2012 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://legal.yandex.ru/mapkit/
 *
 */
public class MapActivity extends Activity  {
    /** Called when the activity is first created. */
    MapController mMapController;
    OverlayManager mOverlayManager;
    ShavermaPatrolApp myApp;

    Firebase mRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myApp = (ShavermaPatrolApp) getApplicationContext();

        setContentView(R.layout.sample);

        final MapView mapView = (MapView) findViewById(R.id.map);
        mapView.showBuiltInScreenButtons(true);

        mMapController = mapView.getMapController();

        mOverlayManager = mMapController.getOverlayManager();

    }

    @Override
    protected void onStart(){
        super.onStart();

        mRef = new Firebase("https://shaverma-patrol.firebaseio.com/shava");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot shava : snapshot.getChildren()) {
                    myApp.AllShavas.put(shava.getKey(),new Shaverma(shava));
                    showObject(myApp.AllShavas.get(shava.getKey()));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void showObject(Shaverma shava){
        // Load required resources
        Resources res = getResources();
        // Create a layer of objects for the map
        Overlay overlay = new Overlay(mMapController);

        // Create an object for the layer
        Drawable pic = res.getDrawable(R.drawable.shop1);
        pic.setBounds(0,0,30,30);

        Bitmap b = ((BitmapDrawable)pic).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 35, 35, false);
        pic = new BitmapDrawable(getResources(), bitmapResized);

        OverlayItem shavaOverlay = new OverlayItem(new GeoPoint(shava.getGeoPointA() , shava.getGeoPointB()), pic);
        // Create a balloon model for the object
        ImageBalloonItem balloonShava = new ImageBalloonItem(this,shavaOverlay.getGeoPoint(), shava);
//
        balloonShava.setOnViewClickListener();
//        // Add the balloon model to the object
        shavaOverlay.setBalloonItem(balloonShava);
        // Add the object to the layer
        overlay.addOverlayItem(shavaOverlay);


        // Add the layer to the map
        mOverlayManager.addOverlay(overlay);

    }

}