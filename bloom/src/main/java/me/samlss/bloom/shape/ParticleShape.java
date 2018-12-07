package me.samlss.bloom.shape;

import android.graphics.Path;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The base class for shape of particle.
 */
public abstract class ParticleShape {
    private float mRadius;
    private float mCenterX;
    private float mCenterY;
    private Path mPath;

    /**
     * Construct the shape of particle.
     *
     * @param centerX The center x coordinate of the particle.
     * @param centerY The center y coordinate of the particle.
     * @param radius The radius of the particle.
     * */
    public ParticleShape(float centerX, float centerY, float radius){
        mCenterX = centerX;
        mCenterY = centerY;
        mRadius  = radius;
        mPath = new Path();
    }

    /**
     * Return the radius of the particle.
     * */
    public float getRadius() {
        return mRadius;
    }

    /**
     * Return the center x coordinate of the particle.
     * */
    public float getCenterX() {
        return mCenterX;
    }

    /**
     * Return the center y coordinate of the particle.
     * */
    public float getCenterY() {
        return mCenterY;
    }

    /**
     * Need to implement this method to generate shape.
     * You only need to implement this method, you don't have to deal with the call timing.
     *
     * 只需实现该接口即可，无需在意它何时被调用
     *
     * @param path The path you need to handle.
     * */
    public abstract void generateShape(Path path);

    /**
     * Get the path shape for particle.
     * */
    public Path getShapePath(){
        return mPath;
    }
}
