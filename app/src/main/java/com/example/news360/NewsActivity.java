package com.example.news360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.news360.databinding.ActivityNewsBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class NewsActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    FragAdapter fragAdapter;
    FragmentManager fragmentManager;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    SearchView searchView;
    ActivityNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        binding.toolbar.setTitle("");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawer, R.string.app_name, R.string.app_name);
        binding.drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.navigationView.setItemIconTintList(null);

        if (binding.navigationView != null) {
            binding.navigationView.setNavigationItemSelectedListener(this);
        }

        fragmentManager = getSupportFragmentManager();
        fragAdapter = new FragAdapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(fragAdapter);


        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home1:
                //close navigation drawer
                binding.drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.bookmarksNews: {
                Intent intent = new Intent(NewsActivity.this, Bookmarks.class);
                startActivity(intent);
                break;
            }

            case R.id.settingsNews:
                break;
            case R.id.about: {
                Intent intent = new Intent(NewsActivity.this, About.class);
                startActivity(intent);
                break;
            }
            case R.id.shareNews:
                inviteFriends();
                break;
            case R.id.rateUs:
                rateUs();
                break;
        }
        return false;
    }

    private void inviteFriends() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey what's up? read latest news. Download it from here... \n https://play.google.com/store/apps/details?id=" + getPackageName());
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, "Share a link");
        startActivity(shareIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchitem, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                //    UserFeedback.show( "SearchOnQueryTextSubmit: " + query);
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        return true;
    }

    private void rateUs() {
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " Unable to find source market app! try again", Toast.LENGTH_LONG).show();

        }
    }
}