package com.praveenan.isaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphCardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NeumorphCardView add, visits, notes, resources;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (NeumorphCardView) findViewById(R.id.add);
        visits = (NeumorphCardView) findViewById(R.id.visits);
        notes = (NeumorphCardView) findViewById(R.id.notes);
        resources = (NeumorphCardView) findViewById(R.id.resources);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.img1));
        sliderItems.add(new SliderItem(R.drawable.img2));
        sliderItems.add(new SliderItem(R.drawable.img3));
        sliderItems.add(new SliderItem(R.drawable.img4));
        sliderItems.add(new SliderItem(R.drawable.img5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        add.setOnClickListener(this);
        visits.setOnClickListener(this);
        notes.setOnClickListener(this);
        resources.setOnClickListener(this);

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i ;

        switch (view.getId()) {
            case R.id.add : i = new Intent(this,AddVisitActivity.class);startActivity(i); break;
            case R.id.visits: i = new Intent(this,VisitActivity.class);startActivity(i); break;
            case R.id.notes: i = new Intent(this,NotesActivity.class);startActivity(i); break;
            case R.id.resources: i = new Intent(this,ResourcesActivity.class);startActivity(i); break;
            default:break;

        }
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() +1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,3000);
    }
}