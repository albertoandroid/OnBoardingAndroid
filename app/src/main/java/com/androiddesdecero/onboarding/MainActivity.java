package com.androiddesdecero.onboarding;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private TabLayout dotsTabLayout;

    private SliderAdapter sliderAdapter;
    private Button btBack;
    private Button btNext;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotsTabLayout = findViewById(R.id.dots);
        slideViewPager = findViewById(R.id.viewPager);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btNext);

        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);
        dotsTabLayout.setupWithViewPager(slideViewPager);
        setUpDots();

        slideViewPager.addOnPageChangeListener(viewListener);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage == (sliderAdapter.getCount()-1)){
                    Log.d("TAG1", "Hemos llegado al final, lanza tu Activitiy de inicio");
                }else{
                    slideViewPager.setCurrentItem(currentPage + 1);
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(currentPage - 1);
            }
        });
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
            if(currentPage == 0){
                btNext.setEnabled(true);
                btNext.setText("Next");
                btBack.setEnabled(false);
                btBack.setVisibility(View.GONE);
            }else if (position == sliderAdapter.getCount() -1){
                btNext.setEnabled(true);
                btNext.setText("Finish");
                btBack.setEnabled(true);
                btBack.setText("Back");
                btBack.setVisibility(View.VISIBLE);
            }else{
                btNext.setEnabled(true);
                btNext.setText("Next");
                btBack.setEnabled(true);
                btBack.setText("Back");
                btBack.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private void setUpDots(){
        ViewGroup tabStrip = (ViewGroup) dotsTabLayout.getChildAt(0);
        for (int i = 0; i<tabStrip.getChildCount(); i++){
            View tabView = tabStrip.getChildAt(i);
            if(tabView != null){
                int paddingStart = tabView.getPaddingStart();
                int paddingTop = tabView.getPaddingTop();
                int paddingEnd = tabView.getPaddingEnd();
                int paddingBottom = tabView.getPaddingBottom();
                ViewCompat.setBackground(tabView, AppCompatResources.getDrawable(tabView.getContext(), R.drawable.tab_color));
                ViewCompat.setPaddingRelative(tabView, paddingStart, paddingTop, paddingEnd, paddingBottom);
            }
        }
    }
}
