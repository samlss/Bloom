package me.samlss.bloom.shape.distributor;

import me.samlss.bloom.particle.BloomParticle;
import me.samlss.bloom.shape.ParticleCircleShape;
import me.samlss.bloom.shape.ParticleShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The circle shape distributor for all particles.
 */
public class CircleShapeDistributor extends ParticleShapeDistributor {
    @Override
    public ParticleShape getShape(BloomParticle particle) {
        return new ParticleCircleShape(particle.getInitialX(), particle.getInitialY(), particle.getRadius());
    }
}
