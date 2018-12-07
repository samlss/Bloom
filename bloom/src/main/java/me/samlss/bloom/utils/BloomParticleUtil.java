package me.samlss.bloom.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.samlss.bloom.particle.BloomParticle;
import me.samlss.bloom.shape.ParticleCircleShape;
import me.samlss.bloom.shape.ParticleShape;
import me.samlss.bloom.shape.distributor.ParticleShapeDistributor;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description util of particle
 */
public class BloomParticleUtil {
    private BloomParticleUtil(){
        throw new UnsupportedOperationException("Can not be instantiated.");
    }

    /**
     * Get the rect of particle's [drawX, drawY] of the content view.
     * */
    public static RectF getParticleRealDrawRect(BloomParticle particle){
        float realRadius = particle.getRadius() * particle.getScale();

        RectF rectF = new RectF(particle.getDrawX() - realRadius, particle.getDrawY() - realRadius,
                particle.getDrawX() + realRadius, particle.getDrawY() + realRadius);

        rectF.left   += particle.getTargetViewRect().left;
        rectF.right  += particle.getTargetViewRect().left;
        rectF.top    += particle.getTargetViewRect().top;
        rectF.bottom += particle.getTargetViewRect().top;

        return rectF;
    }

    /**
     * Check if the rect contains particles.
     *
     * @param rectF The rect.
     * @param particle The particle.
     * */
    public static boolean isRectContainsParticle(RectF rectF, BloomParticle particle){
        if (particle == null
                || rectF == null
                || rectF.isEmpty()
                || particle.getTargetViewRect() == null
                || particle.getTargetViewRect().isEmpty()){
            return false;
        }

        RectF particleDrawRect = getParticleRealDrawRect(particle);

        if (particleDrawRect.right < rectF.left
                || particleDrawRect.left > rectF.right
                || particleDrawRect.bottom < rectF.top
                || particleDrawRect.top > rectF.bottom){
            return false;
        }

        return true;
    }

    /**
     * Check the activated state of the particle.
     *
     * @param boundRect The bound rect for the particle.
     * @param bloomParticle The particle.
     * */
    public static boolean isParticleActivated(RectF boundRect, BloomParticle bloomParticle){
        return isRectContainsParticle(boundRect, bloomParticle)
//                && bloomParticle.getAlpha() > 0
                && bloomParticle.getColor() != Color.TRANSPARENT
                && bloomParticle.getScale() > 0
                && bloomParticle.getRadius() > 0;
    }

    /**
     * Generate particles based on bitmap.
     *
     * @param bitmap The bitmap.
     * @param rowCount The row count of the particles.
     * @param columnCount The column count of the particles.
     * @param targetViewRect The rect of the particle's source view.
     * @param boundRect The bound rect of the particles.
     * @param radius The radius of the particles.
     * @param distributor The shape of particle.
     * */
    public static List<BloomParticle> generateParticles(Bitmap bitmap, int rowCount, int columnCount,
                                                        RectF targetViewRect, RectF boundRect,
                                                        float radius, ParticleShapeDistributor distributor){
        if (bitmap == null
                || boundRect == null
                || boundRect.isEmpty()){
            return null;
        }
        List<BloomParticle> particles = new ArrayList<>();

        int indexCounter = 0;
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++){
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++){
                BloomParticle bloomParticle = new BloomParticle();
                bloomParticle.setIndex(indexCounter++);
                bloomParticle.setRadius(radius);
                bloomParticle.setRow(rowIndex);
                bloomParticle.setColumn(columnIndex);
                bloomParticle.setInitialX((rowIndex * 2 + 1) * radius);
                bloomParticle.setInitialY((columnIndex * 2 + 1) * radius);
                bloomParticle.setDrawX(bloomParticle.getInitialX());
                bloomParticle.setDrawY(bloomParticle.getInitialY());
                bloomParticle.setTargetViewRect(targetViewRect);
                bloomParticle.setColor(bitmap.getPixel((int)bloomParticle.getInitialX(), (int)bloomParticle.getInitialY()));
                bloomParticle.setInitialAlpha(Color.alpha(bloomParticle.getColor()));
                bloomParticle.setAlpha(bloomParticle.getInitialAlpha());
                bloomParticle.setActivated(isParticleActivated(boundRect, bloomParticle));
                //Here will execute the particle shape generation code
                //这里会执行粒子形状生成代码
                ParticleShape particleShape = distributor.getShape(bloomParticle);
                if (particleShape == null) {
                    particleShape = new ParticleCircleShape(bloomParticle.getInitialX(), bloomParticle.getInitialY(),
                            bloomParticle.getRadius());
                }

                particleShape.generateShape(particleShape.getShapePath());
                bloomParticle.setShape(particleShape);
                particles.add(bloomParticle);
            }
        }

        return particles;
    }
}
