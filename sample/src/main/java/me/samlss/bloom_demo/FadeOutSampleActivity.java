package me.samlss.bloom_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import me.samlss.bloom.Bloom;
import me.samlss.bloom.effector.BloomEffector;
import me.samlss.bloom.shape.distributor.StarShapeDistributor;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The anchor sample.
 */
public class FadeOutSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_out_sample);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_anchor_google:
                Bloom.with(this)
                        .setParticleRadius(10)
                        .setShapeDistributor(new StarShapeDistributor())
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(2000)
                                .setRotationSpeedRange(0.01f, 0.05f)
                                .setSpeedRange(0.1f, 0.5f)
                                .setAcceleration(0.00025f, 90)
                                .setAnchor(view.getWidth() / 2, view.getHeight())
                                .setFadeOut(500, new AccelerateInterpolator())
                                .build())
                        .boom(view);
                break;
        }
    }
}
