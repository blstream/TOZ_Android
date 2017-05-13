package com.intive.toz.common.view.circular_text_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * The type Circular text view.
 */
public class CircularTextView extends AppCompatTextView {
    private float strokeWidth;
    private int strokeColor, solidColor;

    /**
     * Instantiates a new Circular text view.
     *
     * @param context the context
     */
    public CircularTextView(final Context context) {
        super(context);
    }

    /**
     * Instantiates a new Circular text view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CircularTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Circular text view.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public CircularTextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void draw(final Canvas canvas) {

        Paint circlePaint = new Paint();
        circlePaint.setColor(solidColor);
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        Paint strokePaint = new Paint();
        strokePaint.setColor(strokeColor);
        strokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        int h = this.getHeight();
        int w = this.getWidth();

        int diameter = ((h > w) ? h : w);
        int radius = diameter / 2;

        this.setHeight(diameter);
        this.setWidth(diameter);

        canvas.drawCircle(diameter / 2, diameter / 2, radius, strokePaint);

        canvas.drawCircle(diameter / 2, diameter / 2, radius - strokeWidth, circlePaint);

        super.draw(canvas);
    }

    /**
     * Sets stroke width.
     *
     * @param dp the dp
     */
    public void setStrokeWidth(final int dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        strokeWidth = dp * scale;

    }

    /**
     * Sets stroke color.
     *
     * @param color the color
     */
    public void setStrokeColor(final int color) {
        strokeColor = getResources().getColor(color);
    }

    /**
     * Sets solid color.
     *
     * @param color the color
     */
    public void setSolidColor(final int color) {
        solidColor = getResources().getColor(color);
    }
}