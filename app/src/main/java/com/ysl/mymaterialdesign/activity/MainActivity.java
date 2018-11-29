package com.ysl.mymaterialdesign.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ysl.mymaterialdesign.R;
import com.ysl.mymaterialdesign.fragment.DashboardFragment;
import com.ysl.mymaterialdesign.fragment.HomeFragment;
import com.ysl.mymaterialdesign.fragment.NotificationFragment;
import com.ysl.mymaterialdesign.fragment.PersonalFragment;

public class MainActivity extends AppCompatActivity {
    protected Fragment currentFragment;
    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private NotificationFragment notificationFragment;
    private PersonalFragment personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
//        bottom_navigation.setItemIconTintList(null);
        initData();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    clickHomeItem();
                    return true;
                case R.id.navigation_dashboard:
                    clickDashboardItem();
                    return true;
                case R.id.navigation_notifications:
                    clickNotificationItem();
                    return true;
                case R.id.navigation_person:
                    clickPersonalItem();
                    return true;
            }
            return false;
        }
    };

    private void initData() {
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance("","");
        }

        if (!homeFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_content, homeFragment).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().show(homeFragment).commitAllowingStateLoss();
        }

        currentFragment = homeFragment;
    }

    protected void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment){
            return;
        }

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.fl_content, fragment).commitAllowingStateLoss();
        } else {
            transaction.hide(currentFragment).show(fragment).commitAllowingStateLoss();
        }

        currentFragment = fragment;
    }
    private void clickHomeItem() {
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance("","");
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), homeFragment);
    }
    private void clickDashboardItem() {
        if (dashboardFragment == null) {
            dashboardFragment = DashboardFragment.newInstance("","");
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), dashboardFragment);
    }
    private void clickNotificationItem() {
        if (notificationFragment == null) {
            notificationFragment = NotificationFragment.newInstance("","");
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), notificationFragment);
    }
    private void clickPersonalItem() {
        if (personalFragment == null) {
            personalFragment = PersonalFragment.newInstance("","");
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), personalFragment);
    }
}
