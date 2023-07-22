package com.example.mycart.Adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mycart.Fragment.KidsFragment;
import com.example.mycart.Fragment.MensFragment;
import com.example.mycart.Fragment.WomenFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter
{
    public ViewPagerAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment=null;
        if(position==0)
        {
            fragment=new MensFragment();
        }
        else if (position==1)
        {
            fragment=new KidsFragment();
        }
        else if (position==2)
        {
          fragment=new WomenFragment();
        }
        return fragment;
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        String title=null;
        if (position==0)
        {
            title="Mens";
        }
        else if (position==1)
        {
        title="Kids";
        }
        else if (position==2)
        {
            title="Women";
        }
        return title;
    }
}
