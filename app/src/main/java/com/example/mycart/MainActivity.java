package com.example.mycart;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{

    BottomNavigationView bottomNavigationView;


    FrameLayout frameLayout;


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs)
    {
       // drawerLayout= (DrawerLayout) getLayoutInflater().inflate(R.layout.fragment_home,new HomeFragment())
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.search:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        frameLayout=findViewById(R.id.frameLayout2);


       // getLayoutInflater().inflate(R.layout.fragment_home,);


        //navigationView.setNavigationItemSelectedListener(MainActivity.this);
        getSupportFragmentManager().beginTransaction().add(R.id.main_page, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_page, new HomeFragment()).commit();
                       break;
                    case R.id.category:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_page, new CategoryFragment()).commit();
                        break;
                    case R.id.cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_page, new CartFragment()).commit();
                       break;
                    case R.id.person:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_page, new ProfileFragment()).commit();
                        break;
                }
                return true;
            }
        });


        /*viewPager=findViewById(R.id.view_pager);
        //viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(pager_number );
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                if(previewMenu!=null)
                {
                    previewMenu.setChecked(false);

                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                 previewMenu=bottomNavigationView.getMenu().getItem(position);
                 if (viewPager.getCurrentItem()==0)
                 {
                      toolbar.setTitle("Home");
                 } else if (viewPager.getCurrentItem()==1)
                 {
                     toolbar.setTitle("Category");
                 }
                 else if (viewPager.getCurrentItem()==2)
                 {
                     toolbar.setTitle("Offer");
                 }
                 else if (viewPager.getCurrentItem()==3)
                 {
                     toolbar.setTitle("Cart");
                 }
                 else if (viewPager.getCurrentItem()==4 )
                 {
                     toolbar.setTitle("Profile");
                 }

            }
            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });*/
    }
    /*public class MyAdapter extends FragmentPagerAdapter
    {

        public MyAdapter(@NonNull FragmentManager fm)
        {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new CategoryFragment();
                case 2:
                    return new OfferFragment();
                case 3:
                    return new CartFragment();
                case 4:
                    return new ProfileFragment();
            }
            return null;
        }
        @Override
        public int getCount()
        {
            return pager_number;
        }
    }*/

}
