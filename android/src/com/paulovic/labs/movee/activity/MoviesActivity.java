package com.paulovic.labs.movee.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.paulovic.labs.movee.R;
import com.paulovic.labs.movee.adapter.MoviesAdapter;
import com.paulovic.labs.movee.model.MovieData;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MoviesActivity extends SherlockListActivity {
    private MoviesAdapter mAdapter; 

    private final int MENU_REFRESH = 0;

    public final static int EVENT_DELETE_ITEM = 0;

    private List<MovieData> mEntries = new ArrayList<MovieData>();
    private EventHandler mHandler = new EventHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        getSupportActionBar().setTitle(getString(R.string.app_name_title));

        TextView emptyView = (TextView) findViewById(R.id.textEmpty);
        getListView().setEmptyView(emptyView);

        createFakeEntries();

        mAdapter = new MoviesAdapter(mEntries, this, mHandler);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        MovieData entry = (MovieData) getListAdapter().getItem(position);
        Toast.makeText(this, getString(R.string.lets_watch) + " " + entry.getTitle() + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, MENU_REFRESH, MENU_REFRESH, R.string.menu_refresh)
            .setIcon(R.drawable.ic_navigation_refresh)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case MENU_REFRESH:
            fakeRefreshItems();
            break;

        default:
            break;
        }
        return true;
    }

    private void createFakeEntries() {
        //Add fake entries
        mEntries.add(new MovieData("Love & Death", "Woody Allen",
                        "In czarist Russia, a neurotic soldier and his distant cousin formulate a plot to assassinate Napoleon.",
                        R.drawable.boris_grush_screen));

        mEntries.add(new MovieData("8 1/2", "Federico Fellini",
                "A harried movie director retreats into his memories and fantasies.",
                R.drawable.oito_meio_screen));
        
        mEntries.add(new MovieData("Ice Age", "Chris Wedge & Carlos Saldanha",
                "Set during the Ice Age, a sabertooth tiger, a sloth, and a wooly mammoth find a lost human infant, and they try to return him to his tribe.",
                R.drawable.ice_age_screen));

        mEntries.add(new MovieData("Little Miss Sunsine", "Jonathan Dayton & Valerie Faris",
                "A family determined to get their young daughter into the finals of a beauty pageant take a cross-country trip in their VW bus.",
                R.drawable.little_miss_scene));

        mEntries.add(new MovieData("On the road", "Walter Salles",
                "Dean and Sal are the portrait of the Beat Generation. Their search for \"It\" results in a fast paced, energetic roller coaster ride with highs and lows throughout the U.S.",
                R.drawable.on_the_road_screen));

        mEntries.add(new MovieData("Marley & Me", "David Frankel",
                "A family learns important life lessons from their adorable, but naughty and neurotic dog.",
                R.drawable.marley_scene));
        
        mEntries.add(new MovieData("Trainspotting", "Danny Boyle",
                "Renton, deeply immersed in the Edinburgh drug scene, tries to clean up and get out, despite the allure of the drugs and influence of friends.",
                R.drawable.trainspotting_scene));
    }
    
    private void fakeRefreshItems() {
        if (mEntries.size() == 0) {
            createFakeEntries();
        }

        Collections.sort(mEntries, new Comparator<MovieData>() {

            @Override
            public int compare(MovieData lhs, MovieData rhs) {
                Random random = new Random();
                return random.nextInt();
            }
            
        });

        mAdapter.updateItems(mEntries);
    }

    /**
     * Communicator handler. It should be used in order to link list item views and this acitivty.
     */
    private class EventHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case EVENT_DELETE_ITEM:
            {
                int id = msg.arg1;
                mAdapter.removeItem(id);
            }
            }
        }
        
    }
}

