package me.samlss.bloom.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.samlss.bloom.Bloom;
import me.samlss.bloom.effector.BloomEffector;
import me.samlss.bloom.listener.BloomListener;
import me.samlss.bloom.particle.BloomParticle;
import me.samlss.bloom.shape.distributor.CircleShapeDistributor;
import me.samlss.bloom.shape.distributor.ParticleShapeDistributor;
import me.samlss.bloom.utils.BloomParticleUtil;
import me.samlss.bloom.utils.ViewUtils;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The bloommmm view.
 */
public class BloomView extends View{
    private final static String TAG = "BloomView";
    private float mBloomParticleRadius = 10;
    private BloomListener mBloomListener;
    private List<BloomParticle> mBloomParticles;
    private Paint mDrawPaint;
    private Matrix mDrawMatrix;
    private Path mDrawPath;
    private RectF mTargetRect;
    private RectF mBoundRect; //the bound rect for particles.
    private BloomEffector mEffector;
    private ParticleShapeDistributor mShapeDistributor;
    private ValueAnimator mBloomAnimator;

    private boolean isBoomEnd;

    public BloomView(Context context) {
        super(context);

        mDrawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDrawMatrix = new Matrix();
        mTargetRect = new RectF();
        mBoundRect = new RectF();
        mDrawPath = new Path();
    }

    public void setParticleRadius(float radius) {
        mBloomParticleRadius = radius;
    }

    public void setBloomListener(BloomListener bloomListener) {
        this.mBloomListener = bloomListener;
    }

    public void setEffector(BloomEffector bloomEffector) {
        this.mEffector = bloomEffector;
    }

    public void setBloomShapeDistributor(ParticleShapeDistributor shapeDistributor) {
        mShapeDistributor = shapeDistributor;
    }

    public void boom(View view){
        if (isBoomEnd){
            Log.e(TAG, "This bloom obj has boomed, please use Bloom.with() to reboom it.");
            return;
        }

        if (view.getVisibility() != View.VISIBLE
                || view.getAlpha() == 0){
            return;
        }

        Bitmap bitmapFromView = ViewUtils.createBitmap(view);
        if (bitmapFromView == null
                || getContext() instanceof Activity == false){
            Log.w(TAG, "Cannot create from view, please check if the view is attached to the window.");
            return;
        }

        ViewGroup parent = (ViewGroup) getParent();
        mBoundRect.set(0, 0, parent.getWidth(), parent.getHeight());

        RectF rectF =  ViewUtils.getRectOnScreen(view);
        if (rectF == null
                || rectF.isEmpty()){
            Log.w(TAG, "Cannot get the rect from view,");
            return;
        }

        Activity activity = (Activity) this.getContext();
        rectF.top -= parent.getTop();
        rectF.bottom -= parent.getTop();

        if (ViewUtils.isStatusBarVisible(activity)) {
            rectF.top -= ViewUtils.getStatusBarHeight();
            rectF.bottom -= ViewUtils.getStatusBarHeight();
        }

        mTargetRect.set(rectF);

        int bitmapWidth = bitmapFromView.getWidth();
        int bitmapHeight = bitmapFromView.getHeight();

        int rowCount = (int) (bitmapWidth / (mBloomParticleRadius * 2));
        int columnCount = (int) (bitmapHeight / (mBloomParticleRadius * 2));

        if (mShapeDistributor == null){
            mShapeDistributor = new CircleShapeDistributor();
        }

        mBloomParticles = BloomParticleUtil.generateParticles(bitmapFromView, rowCount, columnCount, mTargetRect,
                mBoundRect, mBloomParticleRadius, mShapeDistributor);

        ViewUtils.recycleBitmap(bitmapFromView);
        if (mBloomParticles == null
                || mBloomParticles.isEmpty()){
            Log.w(TAG, "Generating particles failed.");
            return;
        }

        configureBloomEffector();
        boomNow();
    }

    private void configureBloomEffector(){
        if (mEffector == null){
            mEffector = new BloomEffector.Builder()
                    .setAnchor(mTargetRect.width()  / 2, mTargetRect.height() / 2)
                    .setSpeedRange(0.1f, 0.5f)
                    .build();
        }

        mBloomAnimator = ValueAnimator.ofInt(0, (int) mEffector.getDuration());
        mBloomAnimator.setDuration(mEffector.getDuration());
        mBloomAnimator.setInterpolator(mEffector.getInterpolator());
        mBloomAnimator.addUpdateListener(mAnimatorUpdateListener);
        mBloomAnimator.addListener(mAnimatorListener);
    }

    private void boomNow(){
        if (mBloomListener != null){
            mBloomListener.onBegin();
        }

        mBloomAnimator.start();
    }

    private void cancelAnimator(){
        if (mBloomAnimator != null){
            mBloomAnimator.removeListener(mAnimatorListener);
            mBloomAnimator.removeUpdateListener(mAnimatorUpdateListener);

            if (mBloomAnimator.isRunning()) {
                mBloomAnimator.cancel();
            }
            mBloomAnimator = null;
        }
    }

    /**
     * This method will be invoked when the animation ends or when you call {@link Bloom#cancel()} or {@link #onDetachedFromWindow()} is called.
     * */
    private void boomEnd(){
        if (isBoomEnd){
            return;
        }

        isBoomEnd = true;
        if (mBloomListener != null){
            mBloomListener.onEnd();
        }

        if (mEffector != null) {
            mEffector.destroy();
            mEffector = null;
        }

        ((ViewGroup) getParent()).removeView(this); //remove this view.
        cancelAnimator();
    }

    /**
     * Animation updates to refresh the state of particles.
     * */
    private void onAnimatorUpdate(int animatedValue){
        for (BloomParticle particle : mBloomParticles) {
            if (particle.isActivated()) {
                mEffector.apply(animatedValue, particle);
                particle.setActivated(BloomParticleUtil.isParticleActivated(mBoundRect, particle));
            }
        }

        postInvalidate();
    }

    private Paint paint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);

        if (mBloomParticles == null
                || mBloomParticles.isEmpty()){
            return;
        }

        for (BloomParticle particle : mBloomParticles){
            if (!particle.isActivated()){
                continue;
            }

            Path particlePath = particle.getShape().getShapePath();
            if (particlePath != null
                    && !particlePath.isEmpty()) {

                mDrawMatrix.reset();
                mDrawPath.reset();

                float deltaX = particle.getDrawX() - particle.getInitialX();
                float deltaY = particle.getDrawY() - particle.getInitialY();

                mDrawMatrix.postSkew(particle.getSkew(), particle.getSkew(), particle.getInitialX(),
                        particle.getInitialY());

                mDrawMatrix.postRotate(particle.getRotation(), particle.getInitialX(),
                        particle.getInitialY());

                mDrawMatrix.postScale(particle.getScale(), particle.getScale(), particle.getInitialX(),
                        particle.getInitialY());
                mDrawMatrix.postTranslate(mTargetRect.left + deltaX, mTargetRect.top+deltaY);

                particlePath.transform(mDrawMatrix, mDrawPath);

                mDrawPaint.setColor(particle.getColor());
                mDrawPaint.setAlpha(particle.getAlpha());
                canvas.drawPath(mDrawPath, mDrawPaint);
            }
        }
    }

    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            onAnimatorUpdate((Integer) animation.getAnimatedValue());
        }
    };

    private AnimatorListenerAdapter mAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            boomEnd();
        }
    };

    public void cancel() {
        boomEnd();
    }


}
