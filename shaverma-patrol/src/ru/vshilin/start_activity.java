package ru.vshilin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class start_activity extends Activity {

    public final String MAPKIT = "yandex.intent.category.MAPKIT";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_screen);

        //display the logo during 5 secondes,
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                Intent intent = new Intent();
                intent.setClassName("ru.vshilin","ru.vshilin.BalloonOverlayActivity");
                startActivity(intent);

                finish();
            }
        }.start();

    }


}