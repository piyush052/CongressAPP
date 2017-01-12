package hexa.webandrioz.com.congress.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hexa.webandrioz.com.congress.fragments.legislators.favourites.FavBillsFragment;
import hexa.webandrioz.com.congress.fragments.legislators.favourites.LegCommFragment;
import hexa.webandrioz.com.congress.fragments.legislators.favourites.LegFavFragment;
import hexa.webandrioz.com.congress.fragments.legislators.legislators.ByStatesFragment;
import hexa.webandrioz.com.congress.fragments.legislators.legislators.HouseFragment;
import hexa.webandrioz.com.congress.fragments.legislators.legislators.SeneteFragment;

/**
 * Created by gipsy_danger on 25/11/16.
 */

public class FavouritePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public FavouritePagerAdapter (FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
               LegFavFragment tab1 = new LegFavFragment();
                return tab1;
            case 1:
               FavBillsFragment tab2 = new FavBillsFragment();
                return tab2;
            case 2:
                LegCommFragment tab3 = new LegCommFragment();
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
