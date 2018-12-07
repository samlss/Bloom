package me.samlss.bloom.shape.distributor;

import me.samlss.bloom.particle.BloomParticle;
import me.samlss.bloom.shape.ParticleRectShape;
import me.samlss.bloom.shape.ParticleShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The rect shape distributor without rebound corners for all particles.
 */
public class RectShapeDistributor extends ParticleShapeDistributor {

    @Override
    public ParticleShape getShape(BloomParticle particle) {
        return new ParticleRectShape(particle.getInitialX(), particle.getInitialY(), particle.getRadius());
    }
}
