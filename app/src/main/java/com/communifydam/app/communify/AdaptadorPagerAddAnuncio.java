package com.communifydam.app.communify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 2912 on 06/02/2018.
 */

public class AdaptadorPagerAddAnuncio extends FragmentPagerAdapter {

        public AdaptadorPagerAddAnuncio(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public int getCount()
        {
            return 2;
        }
        @Override
        public Fragment getItem(int position)
        {
            Fragment f;
            switch(position)
            {
                case 0:
                    f = new FragmentAddAnuncio_1();
                    return f;
                case 1:
                    f = new FragmentAddAnuncio_2();
                    return f;
                //default : return FragmentAddAnuncio_3.newInstance("s");
                default : return new FragmentAddAnuncio_1();
            }
        }

}
