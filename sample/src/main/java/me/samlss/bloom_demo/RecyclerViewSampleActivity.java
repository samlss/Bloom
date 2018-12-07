package me.samlss.bloom_demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.samlss.bloom.Bloom;
import me.samlss.bloom.effector.BloomEffector;
import me.samlss.bloom.shape.distributor.CircleShapeDistributor;
import me.samlss.bloom.shape.distributor.ParticleShapeDistributor;
import me.samlss.bloom.shape.distributor.RectShapeDistributor;
import me.samlss.bloom.shape.distributor.StarShapeDistributor;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description
 */
public class RecyclerViewSampleActivity extends AppCompatActivity {
    public final static int[] PICTURES = new int[]{
            R.drawable.pic_1,
            R.drawable.pic_2,
            R.drawable.pic_3,
            R.drawable.pic_4,
            R.drawable.pic_5,
            R.drawable.pic_6,
            R.drawable.pic_7,
    };

    public final static List<Integer> sPictureList = new ArrayList<>();
    static {
        for (int i = 0; i < 20; i++){
            sPictureList.add(PICTURES[i % PICTURES.length]);
        }
    }

    private Random mRandom;
    private RecyclerView mRecyclerView;
    private Bloom mBloom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_sample);

        mRandom = new Random();
        initListView();
    }


    private void initListView(){
        mRecyclerView = findViewById(R.id.recyclerview);
        Adapter adapter = new Adapter();
        adapter.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boom(view);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void boom(View view){
        ParticleShapeDistributor shapeDistributor = null;
        switch (mRandom.nextInt(3)){
            case 0:
                shapeDistributor = new CircleShapeDistributor();
                break;

            case 1:
                shapeDistributor = new RectShapeDistributor();
                break;

            default:
                shapeDistributor = new StarShapeDistributor();
        }

        if (mBloom != null){
            mBloom.cancel();
        }

        mBloom = Bloom.with(this)
                .setParticleRadius(15)
                .setShapeDistributor(shapeDistributor)
                .setEffector(new BloomEffector.Builder()
                        .setDuration(2000)
                        .setScaleRange(0.5f, 1.5f)
                        .setRotationSpeedRange(0.01f, 0.05f)
                        .setSpeedRange(0.1f, 0.5f)
                        .setAcceleration(0.00025f, 90)
                        .setAnchor(view.getWidth() / 2, view.getHeight())
                        .setFadeOut(500, new AccelerateInterpolator())
                        .build());

        mBloom.boom(view);
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder>{
        private AdapterView.OnItemClickListener itemClickListener;
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.layout_item_list, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            viewHolder.imageView.setImageResource(sPictureList.get(i));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(null, v, i, v.getId());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return sPictureList.size();
        }

        public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }
}
