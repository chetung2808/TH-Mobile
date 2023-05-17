package com.example.thbuoi3;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Graphic extends Activity {

    private Context ctext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GraphicView(this));
        MediaPlayer mPlayer = MediaPlayer.create(ctext, R.raw.Phineas);
        mPlayer.start();
    }
}