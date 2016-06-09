package group.sisto.ambrose.price;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import group.sisto.ambrose.price.httpmanager.HttpManager;
import group.sisto.ambrose.price.jsonparser.ReturnString;

public class MainActivity extends AppCompatActivity {

    public static TextView networkStatus;
    public static String[] listData;
    public static ArrayList<String> finalData = new ArrayList<>();
    public static Context thisCtx;
    public static ListView listView;
    public static ProgressBar progressBar;
    static List<Items> itemsList;
    Items it;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    protected static void updateDisplay() {

        List<String> ltrs = new ArrayList<>();
        for (Items item : itemsList) {
            ltrs.add(item.getName() + "\t" + item.getPrice() + "\n" + item.getDate());
        }
        listView.setAdapter(new ArrayAdapter<>(thisCtx, android.R.layout.simple_list_item_1, ltrs.toArray()));

    }

    protected static void updateDisplay(String s) {
        networkStatus.append(s + "\n");
    }

    protected static void updateDisplay(String[] s) {
        if (s != null) {
            listView.setAdapter(new ArrayAdapter<String>(thisCtx, android.R.layout.simple_list_item_1, s));
        } else {
            networkStatus.setText("Service unreachable now...");
        }
    }

    public static void requestData(String url) {
        MyTaskManager taskManager = new MyTaskManager();
        taskManager.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        thisCtx = getApplicationContext();
        itemsList = new ArrayList<>();
        //listView = (ListView) findViewById(R.id.item_list);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            //start the about activity
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.action_exit) {
            System.exit(0);
        } else if (id == R.id.action_signin) {
            startActivity(new Intent(this, SignIn.class));
        } else if (id == R.id.action_posttest) {
            startActivity(new Intent(this, PostDemo.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Check for the availability of a network connection
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void postDataToServer(String baseURL) {
        MyTaskManager taskManager = new MyTaskManager();
        String encoded = null;
        try {
            encoded = URLEncoder.encode(baseURL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (encoded != null) {
            taskManager.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, baseURL);
        } else {
            System.out.println("Base url not encoding well");
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        private boolean isOnline() {
            ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * This is the override in which you will populate the individual
         * fragment for the individual sections.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            listView = (ListView) rootView.findViewById(R.id.item_list);
            networkStatus = (TextView) rootView.findViewById(R.id.network_status_display);
            progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.INVISIBLE);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            //update the display from here.
            //after handling the the http data and parsing them
            //display them on a list view acordingly for their sections
            //in the if statements identifying those sections.

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                //CHECK IF THERE IS A NETWORK CONNECTION THEN MAKE A REQUEST.
                if (isOnline()) {
                    requestData("http://ambroseogwang.com/shopitems.php");
                } else {
                    networkStatus.setText("Please enable data connection...");
                }

            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                //CHECK IF THERE IS A NETWORK CONNECTION THEN MAKE A NETWORK REQUST.
                if (isOnline()) {
                    requestData("http://ambroseogwang.com/groceries.php");
                } else {
                    networkStatus.setText("Please enable data connection...");
                }

            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                //CHECK IF THERE IS A NETWORK CONNECTION THEN MAKE A NETWORK REQUST.
                if (isOnline()) {
                    requestData("http://ambroseogwang.com/clothings.php");
                } else {
                    networkStatus.setText("Please enable data connection...");
                }

            }
            return rootView;
        }
    }

    private static class MyTaskManager extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            //return HttpManager.postData(params[0], "posts/1");
            return HttpManager.getData(params[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            //listData = ReturnString.parseData(s);
            updateDisplay(ReturnString.parseData(s));
            //itemsList = JSONParser.parseFeed(s);
            //updateDisplay();
            //updateDisplay(s);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return new PlaceholderFragment().newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Shop ITEMS";
                case 1:
                    return "Groceries";
                case 2:
                    return "Clothings";
            }
            return null;
        }
    }
}
