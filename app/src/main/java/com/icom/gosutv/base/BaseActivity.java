package com.icom.gosutv.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.icom.gosutv.R;
import com.icom.gosutv.base.event.ChangedFragmentEvent;
import de.greenrobot.event.EventBus;

//import android.support.v7.graphics.Palette;


public abstract class BaseActivity<T> extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.cover);

        BaseFragment defaultFragment = getDefaultFragment();
        if (defaultFragment != null)
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, defaultFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public void onEventMainThread(ChangedFragmentEvent event)
    {
        BaseFragment baseFragment = event.getBaseFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.enter, R.anim.exit);
        fragmentTransaction.replace(R.id.container, baseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 1)
        {
            manager.popBackStack();

        }
        else
        {
            AlertDialog dialog = new AlertDialog.Builder(this, 0)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface arg0, int arg1)
                        {
                            moveTaskToBack(true);
                        }
                    }).show();
        }
    }


    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        MenuItem searchItem = menu.findItem(R.id.menu_search);
//        SearchView searchView = null;
//        if (searchItem != null)
//        {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        if (searchView != null)
//        {
//            TextView searchText = (TextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
//            searchText.setHintTextColor(getResources().getColor(R.color.white));
//            searchText.setTypeface(Typeface.MONOSPACE);
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        int id = item.getItemId();
//        if (id == R.id.menu_search)
//        {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    public void setStatusBarColor(View statusBar, int color)
//    {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//        {
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //status bar height
//            int actionBarHeight = getActionBarHeight();
//            int statusBarHeight = getStatusBarHeight();
//            //action bar height
//            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
//            statusBar.setBackgroundColor(color);
//        }
//    }

    public abstract BaseFragment getDefaultFragment();
}
