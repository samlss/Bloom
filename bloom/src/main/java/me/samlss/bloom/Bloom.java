package me.samlss.bloom;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import me.samlss.bloom.effector.BloomEffector;
import me.samlss.bloom.listener.BloomListener;
import me.samlss.bloom.shape.distributor.ParticleShapeDistributor;
import me.samlss.bloom.view.BloomView;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description An android library that display bloom effect for view ❀❀❀❀❀❀❀❀.
 */
public class Bloom {
    private BloomView mBloomView;

    private Bloom(Activity activity) {
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        mBloomView = new BloomView(activity);

        viewGroup.addView(mBloomView,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * Will bloom on the activity's content view.
     *
     * @param activity The activity which will be bloom.
     */
    public static Bloom with(Activity activity) {
        return new Bloom(activity);
    }

    /**
     * Set the radius of each particle in pixel.
     * The default is 10 pixels.
     *
     * @param radius The radius value.
     */
    public Bloom setParticleRadius(float radius) {
        mBloomView.setParticleRadius(radius);
        return this;
    }

    /**
     * Set the bloom listener.
     *
     * @param bloomListener The listener.
     * */
    public Bloom setBloomListener(BloomListener bloomListener) {
        mBloomView.setBloomListener(bloomListener);
        return this;
    }

    /**
     * Set the bloom animator(effect) for particles.
     * */
    public Bloom setEffector(BloomEffector bloomEffector) {
        mBloomView.setEffector(bloomEffector);
        return this;
    }

    /**
     * Set the bloom animator(effect) for particles.
     * */
    public Bloom setShapeDistributor(ParticleShapeDistributor shapeDistributor) {
        mBloomView.setBloomShapeDistributor(shapeDistributor);
        return this;
    }

    /**
     * Now boom -> bang bang🔫🔫🔫~  da da da da da da da🚀🚀🚀~
     */
    public void boom(View view) {
        mBloomView.boom(view);
    }

    /**
     * Can bloom...
     * */
    public void cancel(){
        mBloomView.cancel();
    }
}
