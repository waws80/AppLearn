package thanatos.cardviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.container)
    LinearLayout container;
    private List<Integer> integerList = Arrays.asList(R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four, R.drawable.five);
    private List<Fragment> imageViews = new ArrayList<>();

    @InjectView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App app = (App) getApplication();
        app.addActivity(this);

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        imageViews.add(ItemFragment.getInstance(integerList.get(0)));
        imageViews.add(ItemFragment1.getInstance(integerList.get(1)));
        imageViews.add(ItemFragment2.getInstance(integerList.get(2)));
        imageViews.add(ItemFragment3.getInstance(integerList.get(3)));
        imageViews.add(ItemFragment4.getInstance(integerList.get(4)));

        // 将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return pager.dispatchTouchEvent(event);
            }
        });
        pager.setPageMargin(10);
        pager.setOffscreenPageLimit(3);
        pager.setPageTransformer(false, new ScaleTransformer());
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
//        pager.setCurrentItem(2);

    }

    class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return imageViews.get(position);
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }
    }
}
