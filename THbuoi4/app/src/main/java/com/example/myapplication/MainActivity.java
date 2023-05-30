package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    static Question_1 question_1 = new Question_1();
    static Question_2 question_2 = new Question_2();
    static Question_3 question_3 = new Question_3();
    static Question_4 question_4 = new Question_4();
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.container);
        // Tạo adapter cho ViewPager
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


        // Đặt listener cho BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ques_1:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.ques_2:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.ques_3:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.ques_4:
                        viewPager.setCurrentItem(3);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private static class PagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 4;

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return question_1;
                case 1:
                    return question_2;
                case 2:
                    return question_3;
                case 3:
                    return question_4;
                default:
                    return null;
            }
        }
    }
}





