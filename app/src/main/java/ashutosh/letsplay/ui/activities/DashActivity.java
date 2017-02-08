package ashutosh.letsplay.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import ashutosh.letsplay.R;
import ashutosh.letsplay.application.BaseActivity;
import ashutosh.letsplay.ui.adapter.ViewPagerAdapter;
import ashutosh.letsplay.ui.fragments.InstituteFragment;
import ashutosh.letsplay.ui.fragments.SongFragment;
import ashutosh.letsplay.util.CustomViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ashutosh on 20/1/17.
 */

public class DashActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    CustomViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.iv_header)
    ImageView headerImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        ButterKnife.bind(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SongFragment(), getString(R.string.tab_n_chord));
        adapter.addFragment(new InstituteFragment(), getString(R.string.nearest_places));
        viewPager.setAdapter(adapter);
    }
}
