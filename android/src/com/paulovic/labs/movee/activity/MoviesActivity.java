package com.paulovic.labs.movee.activity;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.paulovic.labs.movee.R;
import com.paulovic.labs.movee.adapter.MoviesAdapter;
import com.paulovic.labs.movee.model.MovieData;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MoviesActivity extends SherlockListActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        initUIElements();
        
        ActionBar actionBar = getSupportActionBar();

        List<MovieData> entries = new ArrayList<MovieData>();

        //Add fake entries
        entries.add(new MovieData("Love & Death", "Woody Allen",
                        "In czarist Russia, a neurotic soldier and his distant cousin formulate a plot to assassinate Napoleon.",
                        R.drawable.boris_grush_screen));

        entries.add(new MovieData("8 1/2", "Federico Fellini",
                "A harried movie director retreats into his memories and fantasies.",
                R.drawable.oito_meio_screen));
        
        entries.add(new MovieData("Ice Age", "Chris Wedge & Carlos Saldanha",
                "Set during the Ice Age, a sabertooth tiger, a sloth, and a wooly mammoth find a lost human infant, and they try to return him to his tribe.",
                R.drawable.ice_age_screen));

        entries.add(new MovieData("Little Miss Sunsine", "Jonathan Dayton & Valerie Faris",
                "A family determined to get their young daughter into the finals of a beauty pageant take a cross-country trip in their VW bus.",
                R.drawable.little_miss_scene));

        entries.add(new MovieData("On the road", "Walter Salles",
                "Dean and Sal are the portrait of the Beat Generation. Their search for \"It\" results in a fast paced, energetic roller coaster ride with highs and lows throughout the U.S.",
                R.drawable.on_the_road_screen));

        entries.add(new MovieData("Marley & Me", "David Frankel",
                "A family learns important life lessons from their adorable, but naughty and neurotic dog.",
                R.drawable.marley_scene));
        
        entries.add(new MovieData("Trainspotting", "Danny Boyle",
                "Renton, deeply immersed in the Edinburgh drug scene, tries to clean up and get out, despite the allure of the drugs and influence of friends.",
                R.drawable.trainspotting_scene));


        MoviesAdapter adapter = new MoviesAdapter(entries, this);
        mListView.setAdapter(adapter);
    }

    private void initUIElements(){
        mListView = getListView();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        MovieData entry = (MovieData) getListAdapter().getItem(position);
        Toast.makeText(this, "Let's watch " + entry.getTitle(), Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, 1, 1, "Refresh")
            .setIcon(R.drawable.ic_navigation_refresh)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }
}
