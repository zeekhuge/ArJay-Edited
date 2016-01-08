package com.wordpress.zubeentolani.arjayv010;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;




public class Level2BaseActivity extends Activity implements ListView.OnItemClickListener, DrawerLayout.DrawerListener {

    DrawerLayout drawerLayout ;
    ListView navigationListView;
    FragmentManager fragmentManager;

    int currentFragment = 0; /* Default fragment that starts is 0th*/

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_base_activty);

        fragmentManager = getFragmentManager();
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationListView = (ListView) findViewById(R.id.navigation_listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                new String[]{
                getString(R.string.action1_in_nav),
                getString(R.string.action2_in_nav),
                getString(R.string.action3_in_nav)}
        );

        drawerLayout.setDrawerShadow(
                R.drawable.drawer_shadow,
                GravityCompat.START
        );


        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);

//        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setSubtitle("This is subtitle");


        navigationListView.setAdapter(adapter);
        navigationListView.setItemChecked(currentFragment, true);
        navigationListView.setOnItemClickListener(this);

        drawerLayout.setDrawerListener(this);

        placeTheFragment(currentFragment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level2_base_activty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        Log.i(getLocalClassName(),getActionBar().getSelectedNavigation);

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        navigationListView.setItemChecked(position, true);
        if (position != currentFragment) {
            placeTheFragment(position);
        }
        drawerLayout.closeDrawer(navigationListView);
    }





    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        getActionBar().setTitle(R.string.app_name);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        setTitleOfActionBarAsFragment();
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }




    /***********************************************************************************/
    /***********************************************************************************/



    /**
     *
     * User defined functions start from here :
     *
     **/



    /**
     * The function below, "placeTheFragment(int navItemPosition)", performs following:
     *
     *  -- it renames the action bar title
     *  -- it places the fragment into the content area of Level2BaseActivity
     *  -- changes the value of currentFragment as it is inserted
     *
     *  the argument paced to the above function is the number of the option
     *  in the navigationBar list (starting from 0) clicked.
     *
     **/

    void placeTheFragment (int navItemPosition){

        switch (navItemPosition){

            case 0:
                LocalMusicFragment localMusicFragment = new LocalMusicFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content,localMusicFragment)
                        .commit();
                break;

            case 1:
                OnlineDataFragment onlineDataFragment = new OnlineDataFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content,onlineDataFragment)
                        .commit();
                break;

            case 2:


                break;
        }
        currentFragment = navItemPosition;
        setTitleOfActionBarAsFragment();
    }




    /**
    *
    * The function setTitleOfActionBarAsFragment()
    * changes the title of the action bar based on the value of
    * "currentFragment" variable
    *
    **/

    void setTitleOfActionBarAsFragment(){
        switch (currentFragment){
            case 0:
                getActionBar().setTitle(R.string.action1_in_nav);
            break;

            case 1:
                getActionBar().setTitle(R.string.action2_in_nav);
            break;

            case 2:
                getActionBar().setTitle(R.string.action3_in_nav);
            break;
        }
    }


}
