package kr.co.tjeit.yogiyocopy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.co.tjeit.yogiyocopy.fragment.MyProfileFragment;
import kr.co.tjeit.yogiyocopy.fragment.OrderListFragment2;
import kr.co.tjeit.yogiyocopy.fragment.StoreListFragment;

public class ViewPagetTest2Activity extends AppCompatActivity {

    private ViewPager mainViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_paget_test2);
        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        mainViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    }


    // inner class -> 클래스 안의 클래스

    class MainPagerAdapter extends FragmentStatePagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0 ) {
                // 0번 페이지, 첫째 장이 뭔가?
                return new StoreListFragment();
            }
            else if (position == 1) {
                return new OrderListFragment2();
            }
            else {
                return new MyProfileFragment();
            }
        }
    }

}





