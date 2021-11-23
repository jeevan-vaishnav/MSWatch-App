package com.example.msw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    // declare variable
    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");

        }
        runtTime();
    }

    //onsave instance
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
    }

    //start button
    public void onClickStart(View view){
        running = true;
    }
    // stop button
    public void onClickStop(View view){
        running = false;
    }

    //reset button
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }


    // this method is actuly containg the code control the entire stop watch

    private void runtTime(){
        final TextView timeView = (TextView) findViewById(R.id.id_time_view);
        //create variable for H M S
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running){
                    seconds++;

                }

                handler.postDelayed(this,1000);
            }
        });

    }

}