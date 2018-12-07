package me.samlss.bloom.shape;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The rect with rebound corners shape of particle.
 */
public class ParticleRectShape extends ParticleShape {
    private float mXRadius = 10;
    private float mYRadius = 10;

    /**
     * Construct the shape of particle.
     *
     * @param centerX The center x coordinate of the particle.
     * @param centerY The center y coordinate of the particle.
     * @param radius The radius of the particle.
     * */
    public ParticleRectShape(float centerX, float centerY, float radius) {
        super(centerX, centerY, radius);
    }

    /**
     * Construct the shape of particle.
     *
     * @param rx The x-radius of the oval used to round the corners
     * @param ry The y-radius of the oval used to round the corners
     * @param centerX The center x coordinate of the particle.
     * @param centerY The center y coordinate of the particle.
     * @param radius The radius of the particle.
     * */
    public ParticleRectShape(float rx, float ry, float centerX, float centerY, float radius) {
        super(centerX, centerY, radius);
        mXRadius = rx;
        mYRadius = ry;
    }

    @Override
    public void generateShape(Path path) {
        path.addRoundRect(new RectF(getCenterX() - getRadius(), getCenterY() - getRadius(), getCenterX() + getRadius(),
                getCenterY() + getRadius()), mXRadius, mYRadius, Path.Direction.CW);
    }
}
