package me.samlss.bloom_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.samlss.bloom.Bloom;
import me.samlss.bloom.effector.BloomEffector;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The acceleration sample.
 */
public class AccelerationSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceleration_sample);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_anchor_google_map:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(1000)
                                .setAnchor(view.getWidth() / 2, view.getHeight() / 2)
                                .setAccelerationRange(0.0008f, 0.0016f, 90, 180)
                                .build())
                        .boom(view);
                break;

            case R.id.iv_anchor_google_browser:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(800)
                                .setAnchor(view.getWidth() / 2, view.getHeight() / 2)
                                .setAcceleration(0.001f, 90)
                                .build())
                        .boom(view);
                break;

            case R.id.iv_anchor_google:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(800)
                                .setAnchor(view.getWidth() / 2, view.getHeight() / 2)
                                .setAcceleration(0.001f, 270)
                                .build())
                        .boom(view);
                break;
        }
    }
}
