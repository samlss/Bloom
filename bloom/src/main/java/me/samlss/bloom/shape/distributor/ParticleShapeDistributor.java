package me.samlss.bloom.shape.distributor;

import me.samlss.bloom.particle.BloomParticle;
import me.samlss.bloom.shape.ParticleShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The bloom shape distributor.
 */
public abstract class ParticleShapeDistributor {
    public abstract ParticleShape getShape(BloomParticle particle);
}
