package me.samlss.bloom_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Random;

import me.samlss.bloom.Bloom;
import me.samlss.bloom.effector.BloomEffector;
import me.samlss.bloom.particle.BloomParticle;
import me.samlss.bloom.shape.ParticleCircleShape;
import me.samlss.bloom.shape.ParticleRectShape;
import me.samlss.bloom.shape.ParticleShape;
import me.samlss.bloom.shape.ParticleStarShape;
import me.samlss.bloom.shape.distributor.ParticleShapeDistributor;
import me.samlss.bloom.shape.distributor.RectShapeDistributor;
import me.samlss.bloom.shape.distributor.StarShapeDistributor;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The shape sample.
 */
public class ShapeSampleActivity extends AppCompatActivity {
    private Random mRandom = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_sample);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_anchor_google_map:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setShapeDistributor(new RectShapeDistributor())
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(800)
                                .setAnchor(view.getWidth() / 2, view.getHeight() / 2)
                                .build())
                        .setBloomListener(new MyBloomListener(view))
                        .boom(view);
                break;

            case R.id.iv_anchor_google_browser:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setShapeDistributor(new StarShapeDistributor())
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(800)
                                .setAnchor(view.getWidth() / 2, 0)
                                .build())
                        .setBloomListener(new MyBloomListener(view))
                        .boom(view);
                break;

            case R.id.iv_anchor_google:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setShapeDistributor(new ParticleShapeDistributor() {
                            @Override
                            public ParticleShape getShape(BloomParticle particle) {
                                switch (mRandom.nextInt(3)){
                                    case 0:
                                        return new ParticleCircleShape(particle.getInitialX(), particle.getInitialY(), particle.getRadius());
                                    case 1:
                                        return new ParticleRectShape(2, 2, particle.getInitialX(), particle.getInitialY(), particle.getRadius());
                                    case 2:
                                        return new ParticleStarShape(particle.getInitialX(), particle.getInitialY(), particle.getRadius());
                                }
                                return new ParticleCircleShape(particle.getInitialX(), particle.getInitialY(), particle.getRadius());
                            }
                        })
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(800)
                                .setAnchor(view.getWidth() / 2, view.getHeight())
                                .build())
                        .setBloomListener(new MyBloomListener(view))
                        .boom(view);
                break;
        }
    }
}
