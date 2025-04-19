package com.example.nahodnecary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RotatingLineView extends View {
    private Paint paint;
    private float angle = 0f;
    private boolean isVisible = true;
    private Handler handler;
    private Runnable updater;

    public RotatingLineView(Context context) {
        super(context);
        init();
    }

    public RotatingLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5f);
        paint.setAntiAlias(true);

        handler = new Handler();
        updater = new Runnable() {
            @Override
            public void run() {
                angle += 5;
                if (angle >= 360) angle = 0;
                invalidate();
                handler.postDelayed(this, 50);
            }
        };
        handler.post(updater);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && isVisible) {
                    float touchX = event.getX();
                    float touchY = event.getY();

                    int centerX = getWidth() / 2;
                    int centerY = getHeight() / 2;
                    int length = 200;

                    float x1 = (float) (centerX + length * Math.cos(Math.toRadians(angle)));
                    float y1 = (float) (centerY + length * Math.sin(Math.toRadians(angle)));
                    float x2 = (float) (centerX - length * Math.cos(Math.toRadians(angle)));
                    float y2 = (float) (centerY - length * Math.sin(Math.toRadians(angle)));

                    if (isTouchNearLine(touchX, touchY, x1, y1, x2, y2)) {
                        isVisible = false;
                        invalidate();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isVisible) return;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int length = 200;

        float x1 = (float) (centerX + length * Math.cos(Math.toRadians(angle)));
        float y1 = (float) (centerY + length * Math.sin(Math.toRadians(angle)));
        float x2 = (float) (centerX - length * Math.cos(Math.toRadians(angle)));
        float y2 = (float) (centerY - length * Math.sin(Math.toRadians(angle)));

        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    private boolean isTouchNearLine(float touchX, float touchY, float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float lengthSquared = dx * dx + dy * dy;
        if (lengthSquared == 0) return false; // Úsečka má nulovou délku

        float t = ((touchX - x1) * dx + (touchY - y1) * dy) / lengthSquared;
        t = Math.max(0, Math.min(1, t));
        float projectionX = x1 + t * dx;
        float projectionY = y1 + t * dy;

        float distance = (float) Math.hypot(touchX - projectionX, touchY - projectionY);
        return distance <= 20; // 20 pixelů jako práh pro detekci dotyku
    }

    public void startRotation() {
        handler.post(updater);
    }

    public void stopRotation() {
        handler.removeCallbacks(updater);
    }
}

