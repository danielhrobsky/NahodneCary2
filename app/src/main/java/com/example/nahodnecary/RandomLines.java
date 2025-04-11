package com.example.nahodnecary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RandomLines extends View {
    public RandomLines(Context context)
    {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(40);

        for(int i=0;i<100;i++){
            canvas.drawLine(
                    (float)(Math.random()*getMeasuredWidth()),
                    (float)(Math.random()*getMeasuredHeight()),
                    (float)(Math.random()*getMeasuredWidth()),
                    (float)(Math.random()*getMeasuredHeight()),
                    paint
            );
        }
    }
}
