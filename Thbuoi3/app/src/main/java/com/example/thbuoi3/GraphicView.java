package com.example.thbuoi3;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics. Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

//    public GraphicView(Context context) {
//        super(context);
//    }

public class GraphicView extends View {
    Bitmap[] frames = new Bitmap [16];
   int i=0;
    long last_tick=0;
    long period=200;
    Context ctext;
    MediaPlayer mPlayer;
    public GraphicView (Context context) {
        super(context);
        ctext=context;
        frames[0] = BitmapFactory.decodeResource(getResources(),R.drawable.h1);
        frames[1] = BitmapFactory.decodeResource(getResources(),R.drawable.h2);
        frames[2] = BitmapFactory.decodeResource(getResources(),R.drawable.h3);
        frames[3] = BitmapFactory.decodeResource(getResources(),R.drawable.h4);
        frames[4] = BitmapFactory.decodeResource(getResources(),R.drawable.h5);
        frames[5] = BitmapFactory.decodeResource(getResources(),R.drawable.h6);
        frames[6] = BitmapFactory.decodeResource(getResources(),R.drawable.h7);
        frames[7] = BitmapFactory.decodeResource(getResources(),R.drawable.h8);
    }


//cau2
//    @Override
//        protected void onDraw (Canvas canvas){
//
//            if (i < 16) {
//                canvas.drawBitmap(frames[i], 40, 40, new Paint());
//            }
//
//        else{
//            i = 0;
//        }
//        invalidate();
//    }

    protected void onDraw (Canvas canvas) {
// Drawing commands go here
        if (i<16) {
            long time = (System.currentTimeMillis() - last_tick);
            if (time >= period)
            //the delay time has passed. set next frame
            {
                last_tick = System.currentTimeMillis();
                canvas.drawBitmap(frames[i], 40, 40, new Paint());
                postInvalidate();
                i++;
            } else {
                canvas.drawBitmap(frames[i], 40, 40, new Paint());
                // Again call onDraw method
                postInvalidate();
            }
        }
            else{
                i=0;
                postInvalidate();
            }
            }
        @Override
        public boolean onTouchEvent (MotionEvent event) {
        // TODO Auto-generated method stub
            i++;
            return true;
        }
}