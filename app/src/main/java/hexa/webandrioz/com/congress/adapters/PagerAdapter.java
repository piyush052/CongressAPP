package hexa.webandrioz.com.congress.adapters;

/**
 * Created by gipsy_danger on 23/11/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hexa.webandrioz.com.congress.fragments.legislators.legislators.ByStatesFragment;
import hexa.webandrioz.com.congress.fragments.legislators.legislators.HouseFragment;
import hexa.webandrioz.com.congress.fragments.legislators.legislators.SeneteFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ByStatesFragment tab1 = new ByStatesFragment();
                return tab1;
            case 1:
                HouseFragment tab2 = new HouseFragment();
                return tab2;
            case 2:
                SeneteFragment tab3 = new SeneteFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}