package me.samlss.bloom.particle;

import android.graphics.RectF;

import me.samlss.bloom.shape.ParticleShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The bloom particle
 */
public class BloomParticle {
    /**
     * The radius for particle.
     * */
    private float radius;

    /**
     * The index of all the particles, from 0.
     * */
    private int index;

    /**
     * The row index of particle.
     * */
    private int row;

    /**
     * The column index of particle.
     * */
    private int column;

    /**
     * The initial center x of particle.
     * */
    private float initialX;

    /**
     * The initial center y of particle.
     * */
    private float initialY;

    /**
     * The x coordinate used to draw the particle.
     * */
    private float drawX;

    /**
     * The y coordinate used to draw the particle.
     * */
    private float drawY;

    /**
     * The color of the particle.
     * */
    private int color;

    /**
     * The alpha of the particle[0-255].
     * */
    private int alpha;

    /**
     * The initial alpha of the particle's color
     * */
    private int initialAlpha;

    /**
     * The alpha of the particle[0-255].
     * */
    private float scale = 1;

    private float skew;

    /**
     * The rotation of the particle.
     * */
    private float rotation;

    /**
     * If this particle's alpha is 0
     * or (drawX, drawY) is out of the rect of the activity's content view,
     * or scale is 0.
     *
     * this flag will be false, otherwise is true.
     * */
    private boolean isActivated;

    /**
     * The shape of the particle.
     * */
    private ParticleShape shape;

    /**
     * The rect of the particle's source view.
     * */
    private RectF targetViewRect;

    public BloomParticle(){

    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public float getInitialX() {
        return initialX;
    }

    public void setInitialX(float initialX) {
        this.initialX = initialX;
    }

    public float getInitialY() {
        return initialY;
    }

    public void setInitialY(float initialY) {
        this.initialY = initialY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getDrawX() {
        return drawX;
    }

    public void setDrawX(float drawX) {
        this.drawX = drawX;
    }

    public float getDrawY() {
        return drawY;
    }

    public void setDrawY(float drawY) {
        this.drawY = drawY;
    }

    public int getIndex() {
        return index;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ParticleShape getShape() {
        return shape;
    }

    public void setShape(ParticleShape shape) {
        this.shape = shape;
    }

    public void setTargetViewRect(RectF targetViewRect) {
        this.targetViewRect = targetViewRect;
    }

    public RectF getTargetViewRect() {
        return targetViewRect;
    }

    public float getSkew() {
        return skew;
    }

    public void setSkew(float skew) {
        this.skew = skew;
    }

    public void setInitialAlpha(int initialAlpha) {
        this.initialAlpha = initialAlpha;
    }

    public int getInitialAlpha() {
        return initialAlpha;
    }
}
