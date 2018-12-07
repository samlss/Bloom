package me.samlss.bloom.shape.distributor;

import me.samlss.bloom.particle.BloomParticle;
import me.samlss.bloom.shape.ParticleShape;
import me.samlss.bloom.shape.ParticleStarShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The star shape distributor for all particles.
 */
public class StarShapeDistributor extends ParticleShapeDistributor {
    @Override
    public ParticleShape getShape(BloomParticle particle) {
        return new ParticleStarShape(particle.getInitialX(), particle.getInitialY(), particle.getRadius());
    }
}
