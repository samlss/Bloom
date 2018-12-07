package me.samlss.bloom.shape;

import android.graphics.Path;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The circle shape of particle.
 */
public class ParticleCircleShape extends ParticleShape {

    public ParticleCircleShape(float centerX, float centerY, float radius) {
        super(centerX, centerY, radius);
    }

    @Override
    public void generateShape(Path path) {
        path.addCircle(getCenterX(), getCenterY(), getRadius(), Path.Direction.CW);
    }
}
