package com.icom.gosutv;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.icom.gosutv.base.BaseActivity;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.ui.fragment.*;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.onesignal.OneSignal;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity
{

    public static final String LEFT_MENU_OPTION = "com.csform.android.uiapptemplate.LeftMenusActivity";
    public static final String LEFT_MENU_OPTION_1 = "Universal Left Menu";
    public static final String LEFT_MENU_OPTION_2 = "Universal 2 Left Menu";

    AdView mAdView;
//    private ListView mDrawerList;
//    private List<DrawerItem> mDrawerItems;
//    private DrawerLayout mDrawerLayout;
//    private ActionBarDrawerToggle mDrawerToggle;

    //    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        OneSignal.init(this, "45022017469", "21ebf694-3c08-11e5-b9da-af326eef5f98", new ExampleNotificationOpenedHandler());
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Universal");
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mTitle = mDrawerTitle = getTitle();
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerList = (ListView) findViewById(R.id.list_view);
//
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
//                GravityCompat.START);
//        prepareNavigationDrawerItems();
//        setAdapter();
//        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
//                R.string.drawer_open, R.string.drawer_close)
//        {
//            public void onDrawerClosed(View view)
//            {
//                getSupportActionBar().setTitle(mTitle);
//                invalidateOptionsMenu();
//            }
//
//            public void onDrawerOpened(View drawerView)
//            {
//                getSupportActionBar().setTitle(mDrawerTitle);
//                invalidateOptionsMenu();
//            }
//        };
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        mDrawerLayout.closeDrawer(mDrawerList);
//        if (savedInstanceState == null)
//        {
//            mDrawerLayout.openDrawer(mDrawerList);
//        }

        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(R.layout.demo_custom_tab_icons2, tab, false));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        final LayoutInflater inflater = LayoutInflater.from(this);
        final Resources res = viewPagerTab.getContext().getResources();
        viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider()
        {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter)
            {
                ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon2, container, false);
                switch (position)
                {
                    case 0:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_home_white_24dp));
                        break;
                    case 1:
                        icon.setImageDrawable(res.getDrawable(R.drawable.view_grid
                        ));
                        break;
                    case 2:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_stream));
                        break;
                    case 3:
                        icon.setImageDrawable(res.getDrawable(R.drawable.settings));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }
                return icon;
            }
        });
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("HOME", HomeFragment.class));
        pages.add(FragmentPagerItem.of("CATEGORY", CategoryFragment.class));
        pages.add(FragmentPagerItem.of("PHOTO", PhotoFragment.class));
        pages.add(FragmentPagerItem.of("VIDEO", SettingFragment.class));


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public BaseFragment getDefaultFragment()
    {
        return new HomeFragment();
    }

//    private void setAdapter()
//    {
//        String option = LEFT_MENU_OPTION_1;
//        Bundle extras = getIntent().getExtras();
//        if (extras != null && extras.containsKey(LEFT_MENU_OPTION))
//        {
//            option = extras.getString(LEFT_MENU_OPTION, LEFT_MENU_OPTION_1);
//        }
//
//        boolean isFirstType = true;
//
//        View headerView = null;
//        if (option.equals(LEFT_MENU_OPTION_1))
//        {
//            headerView = prepareHeaderView(R.layout.header_navigation_drawer_1,
//                    "http://pengaja.com/uiapptemplate/newphotos/profileimages/0.jpg",
//                    "dev@csform.com");
//        }
//        else if (option.equals(LEFT_MENU_OPTION_2))
//        {
//            headerView = prepareHeaderView(R.layout.header_navigation_drawer_2,
//                    "http://pengaja.com/uiapptemplate/newphotos/profileimages/0.jpg",
//                    "dev@csform.com");
//            isFirstType = false;
//        }

//        BaseAdapter adapter = new DrawerAdapter(this, mDrawerItems, isFirstType);
//
//        mDrawerList.addHeaderView(headerView);// Add header before adapter (for
//        pre-KitKat)
//        mDrawerList.setAdapter(adapter);
//    }


//    private View prepareHeaderView(int layoutRes, String url, String email)
//    {
//        View headerView = getLayoutInflater().inflate(layoutRes, mDrawerList,
//                false);
//        ImageView iv = (ImageView) headerView.findViewById(R.id.image);
//        TextView tv = (TextView) headerView.findViewById(R.id.email);
//
//        ImageUtil.displayRoundImage(iv, url, null);
//        tv.setText(email);
//
//        return headerView;
//    }

//    private void prepareNavigationDrawerItems()
//    {
//        mDrawerItems = new ArrayList<>();
//        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_home_in,
//                R.string.drawer_title_home,
//                DrawerItem.DRAWER_ITEM_TAG_LINKED_IN));
//        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_dota2,
//                R.string.drawer_title_dota, DrawerItem.DRAWER_ITEM_TAG_BLOG));
//        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_lol,
//                R.string.drawer_title_lol,
//                DrawerItem.DRAWER_ITEM_TAG_GIT_HUB));
//        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_video,
//                R.string.drawer_title_video,
//                DrawerItem.DRAWER_ITEM_TAG_INSTAGRAM));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
//        if (mDrawerToggle.onOptionsItemSelected(item))
//        {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id)
        {
            selectItem(position);
        }

    }

    private void selectItem(int position/* , int drawerTag */)
    {
        // minus 1 because we have header that has 0 position
        if (position < 1)
        { // because we have header, we skip clicking on it
            return;
        }
//        String drawerTitle = getString(mDrawerItems.get(position - 1)
//                .getTitle());
//        Toast.makeText(this,
//                "You selected " + drawerTitle + " at position: " + position,
//                Toast.LENGTH_SHORT).show();
//
//        mDrawerList.setItemChecked(position, true);
//        setTitle(mDrawerItems.get(position - 1).getTitle());
//        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(int titleId)
    {
        setTitle(getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title)
    {
        mTitle = title;
//        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        OneSignal.onResumed();
        // Resume the AdView.
        mAdView.resume();
    }

    @Override
    public void onPause()
    {
        // Pause the AdView.
        mAdView.pause();
        OneSignal.onPaused();
        super.onPause();
    }

    @Override
    public void onDestroy()
    {
        // Destroy the AdView.
        mAdView.destroy();

        super.onDestroy();
    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler
    {
        /**
         * Callback to implement in your app to handle when a notification is opened from the Android status bar or
         * a new one comes in while the app is running.
         * This method is located in this activity as an example, you may have any class you wish implement NotificationOpenedHandler and define this method.
         *
         * @param message        The message string the user seen/should see in the Android status bar.
         * @param additionalData The additionalData key value pair section you entered in on onesignal.com.
         * @param isActive       Was the app in the foreground when the notification was received.
         */
        @Override
        public void notificationOpened(String message, JSONObject additionalData, boolean isActive)
        {
            String messageTitle = "OneSignal Example", messageBody = message;

            try
            {
                if (additionalData != null)
                {
                    if (additionalData.has("title"))
                    {
                        messageTitle = additionalData.getString("title");
                    }
                    if (additionalData.has("actionSelected"))
                    {
                        messageBody += "\nPressed ButtonID: " + additionalData.getString("actionSelected");
                    }

                    messageBody = message + "\n\nFull additionalData:\n" + additionalData.toString();
                }
            }
            catch (JSONException e)
            {
            }

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(messageTitle)
                    .setMessage(messageBody)
                    .setCancelable(true)
                    .setPositiveButton("OK", null)
                    .create().show();
        }
    }

    // activity_main.xml defines the link to this method from the button.
    public void sendTag(View view)
    {
        OneSignal.sendTag("key", "valueAndroid");
    }

    // activity_main.xml defines the link to this method from the button.
    public void getIds(View view)
    {
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler()
        {
            @Override
            public void idsAvailable(String userId, String registrationId)
            {
//                TextView textView = (TextView) findViewById(R.id.textViewIds);
                String labelStr = "UserId: " + userId + "\n\nRegistrationId: ";
                if (registrationId != null)
                {
                    labelStr += registrationId;
                }
                else
                {
                    labelStr += "Did not register. See Android LogCat for errors.";
                }
//                textView.setText(labelStr);
                Log.i("OneSignalExample", labelStr + "\n");
            }
        });
    }
}

