package me.samlss.bloom.effector;

import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.samlss.bloom.particle.BloomParticle;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The bloom effector.
 */
public class BloomEffector {
    private Map<BloomParticle, ParticleFiled> mParticleFieldMap;
    private float mMinSpeed = 0.1f;
    private float mMaxSpeed = 0.5f;
    private float mMinScale;
    private float mMaxScale;

    private float mMinSkew;
    private float mMaxSkew;

    private float mMinAcceleration;
    private float mMaxAcceleration;

    private int mMinAccelerationAngel;
    private int mMaxAccelerationAngel;

    private float mMinRotationSpeed;
    private float mMaxRotationSpeed;

    private Random mRandom;
    private float mAnchorX;
    private float mAnchorY;

    private long mFadeOutStartTime;
    private TimeInterpolator mFadeOutInterpolator;

    /**
     * Effect execution time, the default is 800ms
     * */
    private long mDuration = 300;

    /**
     * Effect execution interpolator, the default is {@link AccelerateDecelerateInterpolator}
     * */
    private TimeInterpolator mEffectInterpolator = new AccelerateDecelerateInterpolator();


    private BloomEffector() {
        mRandom = new Random();
        mParticleFieldMap = new HashMap<>();
    }

    /**
     * Get the duration of the bloom effect animation.
     * */
    public long getDuration() {
        return mDuration;
    }

    /**
     * Set the duration of the bloom effect animation.
     * */
    public void setDuration(long duration) {
        this.mDuration = duration;
    }

    /**
     * Get the interpolator of the bloom effect animation.
     * */
    public TimeInterpolator getInterpolator() {
        return mEffectInterpolator;
    }

    /**
     * Set the interpolator of the bloom effect animation.
     * */
    public void setInterpolator(TimeInterpolator interpolator) {
        this.mEffectInterpolator = interpolator;
    }

    /**
     * This is used to handle the animation of particles.
     * When you need to customize the animation effect, you can extend this class and implement this method.
     *
     * @param milliSecond The animated value, [0, {@link #getDuration()}].
     * @param particle The particle.
     * */
    public void apply(long milliSecond, BloomParticle particle) {
        if (particle == null){
            return;
        }

        ParticleFiled particleFiled = mParticleFieldMap.get(particle);

        if (particleFiled == null){
            particleFiled = createParticleFiled(particle);
        }

        particle.setRotation(particle.getRotation() + milliSecond * particleFiled.rotationSpeed);

        particle.setDrawX(particle.getInitialX()+particleFiled.speedX * milliSecond + particleFiled.accelerationX*milliSecond*milliSecond);
        particle.setDrawY(particle.getInitialY()+particleFiled.speedY * milliSecond + particleFiled.accelerationY*milliSecond*milliSecond);

        updateFadeOut(milliSecond, particle);
    }

    private void updateFadeOut(long milliSecond, BloomParticle particle){
        if (milliSecond < mFadeOutStartTime
                || mFadeOutInterpolator == null
                || particle.getInitialAlpha() == 0) {
            return;
        }

        float fadeOutInterpolatorValue = mFadeOutInterpolator.getInterpolation((milliSecond- mFadeOutStartTime)* 1f / (mDuration - mFadeOutStartTime));

        int alpha = (int) (particle.getInitialAlpha() - particle.getInitialAlpha()*fadeOutInterpolatorValue);
        particle.setAlpha(alpha);
    }

    private ParticleFiled createParticleFiled(BloomParticle particle){
        ParticleFiled particleFiled = new ParticleFiled();

        float randomSpeed = mRandom.nextFloat()*(mMaxSpeed-mMinSpeed) + mMinSpeed;
        double angleInRads = getRadian(particle.getInitialX(), particle.getInitialY(), mAnchorX, mAnchorY);

        particleFiled.speedX = (float) (randomSpeed * Math.cos(angleInRads));
        particleFiled.speedY = (float) (randomSpeed * Math.sin(angleInRads));

        float angle = mMinAccelerationAngel;
        if (mMaxAccelerationAngel != mMinAccelerationAngel) {
            angle = mRandom.nextInt(mMaxAccelerationAngel - mMinAccelerationAngel) + mMinAccelerationAngel;
        }

        float angleInRadsAcc = (float) (angle*Math.PI / 180f);
        float value = mRandom.nextFloat()*(mMaxAcceleration-mMinAcceleration)+mMinAcceleration;
        particleFiled.accelerationX = (float) (value * Math.cos(angleInRadsAcc));
        particleFiled.accelerationY = (float) (value * Math.sin(angleInRadsAcc));

        if (mMinScale >= 0
                && mMaxScale > mMinScale) {
            particle.setScale(mRandom.nextFloat() * (mMaxScale - mMinScale) + mMinScale);
        }

        if (mMinSkew >= 0
                && mMaxSkew > mMinSkew) {
            particle.setSkew(mRandom.nextFloat() * (mMaxSkew - mMinSkew) + mMinSkew);
        }

        particleFiled.rotationSpeed = mRandom.nextFloat()*(mMaxRotationSpeed-mMinRotationSpeed) + mMinRotationSpeed;
        mParticleFieldMap.put(particle, particleFiled);

        return particleFiled;
    }

    public void destroy() {
        mParticleFieldMap.clear();
    }

    /**
     * Get the radian from two points.
     *
     * @param x1 The x coordinate of point1.
     * @param x2 The y coordinate of point1.
     * @param y1 The x coordinate of point2.
     * @param y2 The y coordinate of point2.
     * */
    public static float getRadian(float x1, float y1, float x2, float y2) {
        float lenX = x1 - x2;
        float lenY = y1 - y2;
        float lenXY = (float) Math.sqrt((double) (lenX * lenX + lenY * lenY));
        return (float) (Math.acos(lenX / lenXY) * (y1 < y2 ? -1 : 1));
    }

    /**
     * The filed of particle.
     * */
    private class ParticleFiled{
        float speedX;
        float speedY;

        float accelerationX;
        float accelerationY;

        float rotationSpeed;
    }

    /**
     * To build a {@link BloomEffector}
     * */
    public static class Builder{
        private BloomEffector mBloomEffector;

        public Builder(){
            mBloomEffector = new BloomEffector();
        }

        /**
         * Sets the length of the bloom effect animation in milliseconds.
         *
         * @param duration The length of the animation, in milliseconds. This value cannot be negative.
         * */
        public Builder setDuration(long duration){
            mBloomEffector.setDuration(duration);
            return this;
        }

        /**
         * Set the interpolator of the bloom effect animation.
         * the default is {@link AccelerateDecelerateInterpolator}.
         *
         * @param interpolator The animation interpolator.
         * */
        public Builder setInterpolator(TimeInterpolator interpolator) {
            mBloomEffector.setInterpolator(interpolator);
            return this;
        }

        /**
         * Set anchor points for all the particles.
         *
         * @param anchorX The bloom anchor x coordinate.
         * @param anchorY The bloom anchor y coordinate.
         * */
        public Builder setAnchor(float anchorX, float anchorY){
            mBloomEffector.mAnchorX = anchorX;
            mBloomEffector.mAnchorY = anchorY;
            return this;
        }

        /**
         * Set the speed range for the particles.
         *
         * @param minSpeed The minimum speed value, the default is 0.1.
         * @param maxSpeed The maximum speed value, the default is 0.5.
         * */
        public Builder setSpeedRange(float minSpeed, float maxSpeed){
            mBloomEffector.mMinSpeed = minSpeed;
            mBloomEffector.mMaxSpeed = maxSpeed;
            return this;
        }

        /**
         * Set the scale range for the particles.
         *
         * @param minScale The minimum scale value.
         * @param maxScale The maximum scale value.
         * */
        public Builder setScaleRange(float minScale, float maxScale){
            mBloomEffector.mMinScale = minScale;
            mBloomEffector.mMaxScale = maxScale;
            return this;
        }

        /**
         * Set the skew range for the particles.
         *
         * @param minSkew The minimum speed value.
         * @param maxSkew The maximum speed value.
         * */
        public Builder setSkewRange(float minSkew, float maxSkew){
            mBloomEffector.mMinSkew = minSkew;
            mBloomEffector.mMaxSkew = maxSkew;
            return this;
        }

        /**
         * Set the rotation speed range for the particles.
         *
         * @param minRotationSpeedRange The minimum speed value.
         * @param maxRotationSpeedRange The maximum speed value.
         * */
        public Builder setRotationSpeedRange(float minRotationSpeedRange, float maxRotationSpeedRange){
            mBloomEffector.mMinRotationSpeed = minRotationSpeedRange;
            mBloomEffector.mMaxRotationSpeed = maxRotationSpeedRange;
            return this;
        }

        /**
         * Set particle acceleration, the acceleration is measured in pixels per square millisecond.
         * The angel controls the acceleration direction.
         *
         * Calculated as follows:
         *
         * float angelInRadsAcc = (float) (accelerationAngle*Math.PI / 180f)
         *
         * The final x axis acceleration:  accelerationX = (float) (value * Math.cos(angleInRadsAcc));
         * The final y axis acceleration:  accelerationY = (float) (value * Math.sin(angleInRadsAcc));
         *
         * @param acceleration The acceleration value.
         * @param accelerationAngle The acceleration angele [0-360].
         * */
        public Builder setAcceleration(float acceleration, int accelerationAngle) {
            return setAccelerationRange(acceleration, acceleration, accelerationAngle, accelerationAngle);
        }

        /**
         * This method takes random acceleration and acceleration angles from the range of acceleration and acceleration angles you set.
         *
         * Set particle acceleration, the acceleration is measured in pixels per square millisecond.
         * The angel controls the acceleration direction.
         *
         * Calculated as follows:
         *
         * float angelInRadsAcc = (float) (accelerationAngle*Math.PI / 180f)
         *
         * The final x axis acceleration:  accelerationX = (float) (value * Math.cos(angleInRadsAcc));
         * The final y axis acceleration:  accelerationY = (float) (value * Math.sin(angleInRadsAcc));
         *
         * @param minAcceleration The minimum acceleration.
         * @param maxAcceleration The maximum acceleration.
         * @param minAccelerationAngel The minimum acceleration angel[0-360].
         * @param maxAccelerationAngel The maximum acceleration angel[0-360].
         * */
        public Builder setAccelerationRange(float minAcceleration, float maxAcceleration, int minAccelerationAngel,
                                       int maxAccelerationAngel) {
            mBloomEffector.mMinAcceleration = minAcceleration;
            mBloomEffector.mMaxAcceleration = maxAcceleration;
            mBloomEffector.mMinAccelerationAngel = minAccelerationAngel;
            mBloomEffector.mMaxAccelerationAngel = maxAccelerationAngel;
            return this;
        }

        /**
         * Set the fade out effect for all particle.
         *
         * @param startTime Start time that relative of {@link #setDuration(long)}. For example, if you set the duration to 800,
         *                  then you can set the fadeout start time to 600, that is, start the fade out effect when the particle animation is executed to 600.
         * @param interpolator The interpolator of the fade out effect.
         * */
        public Builder setFadeOut(long startTime, TimeInterpolator interpolator){
            mBloomEffector.mFadeOutStartTime = startTime;
            mBloomEffector.mFadeOutInterpolator = interpolator;
            return this;
        }

        /**
         * Set the fade out effect for all particle.
         *
         * @param startTime Start time that relative of {@link #setDuration(long)}. For example, if you set the duration to 800,
         *                  then you can set the fadeout start time to 600, that is, start the fade out effect when the particle animation is executed to 600.
         * */
        public Builder setFadeOut(long startTime){
            return setFadeOut(startTime, new LinearInterpolator());
        }

        public BloomEffector build(){
            return mBloomEffector;
        }
    }
}
