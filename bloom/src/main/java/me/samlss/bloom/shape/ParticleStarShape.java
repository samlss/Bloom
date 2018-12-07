package me.samlss.bloom.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The star shape of particle.
 */
public class ParticleStarShape extends ParticleShape{
    /**
     * Construct the shape of particle.
     *
     * @param centerX The center x coordinate of the particle.
     * @param centerY The center y coordinate of the particle.
     * @param radius  The radius of the particle.
     */
    public ParticleStarShape(float centerX, float centerY, float radius) {
        super(centerX, centerY, radius);
    }

    @Override
    public void generateShape(Path path) {
        float R = getRadius() * 2;
        float r = getRadius();

        List<PointF> starPoints = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            double outerDoc = (18 + 72 * i) / 180d * Math.PI;
            PointF pointF1 = new PointF((float) (Math.cos(outerDoc) * R) ,
                    - (float) (Math.sin(outerDoc) * R));

            double innerDoc = (54 + 72 * i) / 180d * Math.PI;
            PointF pointF2 = new PointF((float)(Math.cos(innerDoc) * r) ,
                    -(float) (Math.sin(innerDoc) * r));

            starPoints.add(pointF1);
            starPoints.add(pointF2);
        }

        path.moveTo(starPoints.get(0).x, starPoints.get(0).y);
        for (int i = 1; i < starPoints.size(); i++){
            path.lineTo(starPoints.get(i).x, starPoints.get(i).y);
        }
        path.lineTo(starPoints.get(0).x, starPoints.get(0).y);

        Matrix matrix = new Matrix();
        matrix.postTranslate(getCenterX(), getCenterY());
        path.transform(matrix);
    }
}
