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
 * @description The scale sample.
 */
public class ScaleSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_sample);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_anchor_google_map:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(800)
                                .setScaleRange(0.2f, 1.5f)
                                .setAnchor(view.getWidth() / 2, view.getHeight() / 2)
                                .build())
                        .setBloomListener(new MyBloomListener(view))
                        .boom(view);
                break;

            case R.id.iv_anchor_google_browser:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(800)
                                .setScaleRange(0.2f, 1.5f)
                                .setAnchor(view.getWidth() / 2, 0)
                                .build())
                        .setBloomListener(new MyBloomListener(view))
                        .boom(view);
                break;

            case R.id.iv_anchor_google:
                Bloom.with(this)
                        .setParticleRadius(5)
                        .setEffector(new BloomEffector.Builder()
                                .setDuration(800)
                                .setScaleRange(0.2f, 1.5f)
                                .setAnchor(view.getWidth() / 2, view.getHeight())
                                .build())
                        .setBloomListener(new MyBloomListener(view))
                        .boom(view);
                break;
        }
    }
}
