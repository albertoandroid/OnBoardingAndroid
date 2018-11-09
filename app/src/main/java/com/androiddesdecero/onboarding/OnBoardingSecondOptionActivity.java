package com.androiddesdecero.onboarding;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoardingSecondOptionActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotLinearLayout;

    private TextView[] dots;

    private SliderAdapter sliderAdapter;
    private Button btBack;
    private Button btNext;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_second_option);
        dotLinearLayout = findViewById(R.id.dots);
        slideViewPager = findViewById(R.id.viewPager);
        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btNext);

        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);
        addDots();
        slideViewPager.addOnPageChangeListener(viewListener);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage == (sliderAdapter.getCount() - 1)){
                    Log.d("TAG1", "Hemos llegado al final, puedes lanzar una activity");
                }else {
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

    public void addDots(){
        dots = new TextView[3];
        for(int i = 0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(40);
            dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            dotLinearLayout.addView(dots[i]);
        }
    }

    public void addDots(int position){
        for(int i = 0; i<dots.length; i++){
            if(position == i){
                dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
            }else{
                dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDots(i);
            currentPage = i;
            if(currentPage == 0){
                btNext.setEnabled(true);
                btNext.setText("Next");
                btBack.setEnabled(false);
                btBack.setVisibility(View.GONE);
            }else if (i == dots.length -1){
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
        public void onPageScrollStateChanged(int i) {

        }
    };
}
