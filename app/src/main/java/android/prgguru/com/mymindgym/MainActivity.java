package android.prgguru.com.mymindgym;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.prgguru.com.mymindgym.Fragment.Alphabates;
import android.prgguru.com.mymindgym.Fragment.HomeFragment;
import android.prgguru.com.mymindgym.Fragment.NumberFragmnet;
import android.prgguru.com.mymindgym.Fragment.TableFragment;
import android.prgguru.com.mymindgym.Fragment.leftRight;
import android.prgguru.com.mymindgym.Fragment.leftrightmind;
import android.prgguru.com.mymindgym.commonsFiles.Constants;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.prgguru.com.mymindgym.commonsFiles.Constants.HOME_FRAGMENT;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,HomePageListener {
    String[] drawerMenuItems;
    boolean homeFragment = true;
    DrawerLayout drawer;
    ListView mDrawerList;
    AlertDialog dialog;
    private ActionBar actionBar;

    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        actionBar = getSupportActionBar();


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        Bundle pBundle = new Bundle();
        String keyVal = "EMPTY";
        String tag = "HOME";

        FragmentManager fm = getSupportFragmentManager();
        Fragment homeFragment = fm.findFragmentByTag(tag);

        if (homeFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            homeFragment = new HomeFragment();
            pBundle.putString("pooja", keyVal);
            homeFragment.setArguments(pBundle);
            ft.add(R.id.am_full_container, homeFragment, tag);
            ft.commit();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        int frag_id = 0;
        switch (id) {
            case R.id.alpha:
             //   Toast.makeText(MainActivity.this, "Alphabates", Toast.LENGTH_SHORT).show();
                frag_id = Constants.ALPHABATES;
                break;
            case R.id.table:
              //  Toast.makeText(MainActivity.this, "TableFragment", Toast.LENGTH_SHORT).show();
                frag_id = Constants.TABLES;
                break;
            case R.id.number:
               // Toast.makeText(MainActivity.this, "Number", Toast.LENGTH_SHORT).show();
                frag_id = Constants.NUMBERS;
                break;
            case R.id.mind:
                frag_id = Constants.MIND;
                break;


        }
        if (frag_id != 0)
            switchFragment(frag_id, null);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void switchFragment(int fragment_id, Bundle pBundle) {
        boolean addFragment = false;
        Fragment normFragment = null;
        FragmentManager fm = getSupportFragmentManager();

        String tag = "";
        String title = getString(R.string.app_name);

        // Default value for all fragments except home fragment
        homeFragment = false;

        switch (fragment_id) {
            case HOME_FRAGMENT:            // Home Fragment
                tag = "HOME";
                normFragment = fm.findFragmentByTag(tag);
                if (normFragment == null) {
                    normFragment = new HomeFragment();
                    addFragment = true;
                    Log.d("TAG","HOME FRAGEMNT");
                }
                homeFragment = true;
                break;
            case Constants.NUMBERS:
                tag="NUMBERS";
                normFragment=fm.findFragmentByTag(tag);
                if(normFragment==null)
                {
                    normFragment=new NumberFragmnet();
                    addFragment=true;
                }
                homeFragment=true;
                break;

            case Constants.ALPHABATES:
                tag="Alphabates";
                normFragment=fm.findFragmentByTag(tag);
                if(normFragment==null)
                {
                    normFragment=new Alphabates();
                    addFragment=true;
                }
                homeFragment=true;
                break;
            case Constants.TABLES:
                tag="TableFragment";
                normFragment=fm.findFragmentByTag(tag);
                if(normFragment==null)
                {
                    normFragment=new TableFragment();
                    addFragment=true;
                }
                homeFragment=true;
                break;
            case Constants.MIND:
                tag="TableFragment";
                normFragment=fm.findFragmentByTag(tag);
                if(normFragment==null)
                {
                    normFragment=new leftRight();
                    addFragment=true;
                }
                homeFragment=true;
                break;
                default:
                    homeFragment=true;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (normFragment != null) {
            if (addFragment) {
                if (pBundle != null) {
                    normFragment.setArguments(pBundle);
                    Log.d("VT", "Adding bundle");
                }
                ft.add(R.id.am_full_container, normFragment, tag);
                Log.d("VT", "Adding Fragment: " + tag);
            } else {
                ft.replace(R.id.am_full_container, normFragment, tag);
                Log.d("VT", "Replacing Fragment: " + tag);
            }
            ft.commit();
        }
        if (!homeFragment)
         resetHomePage();

        // set the toolbar title to the fragment title
//        actionBar.setTitle(title);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    @Override
    public void onBackPressed() {
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            toggle.setDrawerIndicatorEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            drawer.closeDrawer(GravityCompat.START);
//            return;
//        }
//        Log.d("VT", "Back Pressed");
//        if (homeFragment) {
//            toggle.setDrawerIndicatorEnabled(true);
//            callAlert();
//
//
//            //finish();
//            }
//        else {
//            Log.d("TAG","ELLSE PART");
//            toggle.setDrawerIndicatorEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            switchFragment(Constants.HOME_FRAGMENT, null);
//            toggle.syncState();
//
//            //Log.d("VT", "Backstack count in else:"+getSupportFragmentManager().getBackStackEntryCount());
//        }


        toggle.setDrawerIndicatorEnabled(true);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            if(homeFragment)
                callAlert();
            else {
                actionBar.setDisplayHomeAsUpEnabled(false);
                switchFragment(HOME_FRAGMENT,null);
                toggle.syncState();
            }
        }
    }
    public void resetHomePage()
    {
        homeFragment = false;
        toggle.setDrawerIndicatorEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void callAlert() {
        Log.d("db", "logout");
      //  Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_LONG).show();
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.alert_dialog, null);
        Button i_yes = (Button) mView.findViewById(R.id.dai_yes);
        Button i_no = (Button) mView.findViewById(R.id.dai_no);
        i_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                ;
            }
        });
        i_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.side_menu, menu);
        MenuItem item = (MenuItem) menu.findItem(R.id.sound);
        item.setActionView(R.layout.soundonoff);
        ToggleButton switchAB = item
                .getActionView().findViewById(R.id.togglesound);
        switchAB.setChecked(false);

        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplication(), "ON", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(getApplication(), "OFF", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        return true;
    }
}

