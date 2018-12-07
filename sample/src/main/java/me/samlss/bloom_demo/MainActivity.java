package me.samlss.bloom_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void startAnchorSample(View view) {
        startActivity(new Intent(this, AnchorSampleActivity.class));
    }

    public void startScaleSample(View view) {
        startActivity(new Intent(this, ScaleSampleActivity.class));
    }

    public void startSkewSample(View view) {
        startActivity(new Intent(this, SkewSampleActivity.class));
    }

    public void startShapeSample(View view) {
        startActivity(new Intent(this, ShapeSampleActivity.class));
    }

    public void startAccelerationSample(View view) {
        startActivity(new Intent(this, AccelerationSampleActivity.class));
    }

    public void startRotationSample(View view) {
        startActivity(new Intent(this, RotationSampleActivity.class));
    }

    public void startFadeOutSample(View view) {
        startActivity(new Intent(this, FadeOutSampleActivity.class));
    }

    public void startRecyclerViewSample(View view) {
        startActivity(new Intent(this, RecyclerViewSampleActivity.class));
    }
}
