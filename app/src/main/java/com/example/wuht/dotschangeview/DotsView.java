package com.example.wuht.dotschangeview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wuht on 2016/9/28.
 */

public class DotsView extends View {

    private Paint
            mPaint = new Paint();
    private int mWidth, mHeight;
    private float alpha, delta, gamma;
    private ValueAnimator mValueAnimator;
    private PathMeasure mPathMeasureHeight;
    private PathMeasure mPathMeasureLow;

    public DotsView(Context context) {
        this(context, null);
        init();
    }

    public DotsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public DotsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint(mPaint, Color.RED, true, Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        initPath();


        initAnimator();

    }

    private void initAnimator() {
        mValueAnimator = ValueAnimator.ofFloat(0, 1f).setDuration(800);
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                alpha = (float) mValueAnimator.getAnimatedValue();
                invalidate();
            }
        });
    }

    private void initPath() {
        Path path1 = new Path();
        float x, y;
        x = 0.25f;
        y = 0.275f;
        delta = 500;
        RectF rectF = new RectF((x - 0.375f) * delta, (y - 0.375f) * delta, (x + 0.375f) * delta, (y + 0.375f) * delta);
        path1.addArc(rectF, -46.395f, -87.21f);

        Path path2 = new Path();
        x = 0.25f;
        y = -0.275f;
        RectF rectF1 = new RectF((x - 0.375f) * delta, (y - 0.375f) * delta, (x + 0.375f) * delta, (y + 0.375f) * delta);
        path2.addArc(rectF1, 46.395f, 87.21f);

        Path pathPoint = new Path();
        pathPoint.moveTo(0, 0);
        pathPoint.lineTo(0.5f * delta, 0);

        mPathMeasureHeight = new PathMeasure(path1, false);
        mPathMeasureLow = new PathMeasure(path2, false);

        PathMeasure pathMeasurePointLength = new PathMeasure(pathPoint, false);
        gamma = pathMeasurePointLength.getLength() / 5f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        if (alpha < 0.5) {
            Path path = new Path();
            mPathMeasureHeight.getSegment((-2 * alpha + 1) * mPathMeasureHeight.getLength(), (-2 * alpha + 1) * mPathMeasureHeight.getLength() + 1, path, true);
            canvas.drawPath(path, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 1) * gamma + 4 * gamma, 0, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 1) * gamma + 3 * gamma, 0, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 1) * gamma + 2 * gamma, 0, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 1) * gamma + 1 * gamma, 0, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 1) * gamma, 0, mPaint);
        } else {
            Path path = new Path();
            mPathMeasureLow.getSegment((-2 * alpha + 2) * mPathMeasureHeight.getLength(), (-2 * alpha + 2) * mPathMeasureHeight.getLength() + 1, path, true);
            canvas.drawPath(path, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 4 * alpha) * gamma + 4 * gamma, 0, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 4 * alpha) * gamma + 3 * gamma, 0, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 4 * alpha) * gamma + 2 * gamma, 0, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 4 * alpha) * gamma + 1 * gamma, 0, mPaint);
            canvas.drawPoint((-4 * alpha * alpha + 4 * alpha) * gamma, 0, mPaint);
        }
    }


    private void initPaint(Paint paint, int red, boolean isAntiAlias, Paint.Style style) {
        paint.setColor(red);
        paint.setAntiAlias(isAntiAlias);
        paint.setStyle(style);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void start() {
        mValueAnimator.start();
    }
}
