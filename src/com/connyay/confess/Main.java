package com.connyay.confess;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.connyay.confess.adapters.ResultsAdapter;
import com.connyay.confess.gson.ConfessionsListing;
import com.connyay.confess.gson.GsonTransformer;
import com.connyay.confess.gson.Results;
import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

public class Main extends SherlockListActivity {
    ListView mainListView;
    ResultsAdapter adapter;
    private AQuery aq;

    int limit = 15;
    int offset = 0;
    int totalCount = 0;
    boolean currentlyLoading;
    // cache for 30 min
    long expire = 30 * 60 * 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	mainListView = getListView();
	adapter = new ResultsAdapter(this);

	mainListView.setAdapter(new SlideExpandableListAdapter(adapter,
		R.id.expandable_toggle_button, R.id.expandable));
	buildResults();

	mainListView.setOnScrollListener(new OnScrollListener() {
	    @Override
	    public void onScrollStateChanged(AbsListView view, int scrollState) {
	    }

	    @Override
	    public void onScroll(AbsListView view, int firstVisibleItem,
		    int visibleItemCount, int totalItemCount) {
		int lastInScreen = firstVisibleItem + visibleItemCount;
		if (((lastInScreen > totalItemCount - 2) && !(currentlyLoading))
			&& totalItemCount <= totalCount) {
		    offset += 15;
		    buildResults();
		}
	    }
	});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getSupportMenuInflater();
	inflater.inflate(R.menu.main, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case R.id.menu_license:
	    Toast.makeText(getApplicationContext(), "License Clicked",
		    Toast.LENGTH_SHORT).show();
	    return true;
	case R.id.menu_refresh:
	    offset = 0;
	    expire = -1;
	    adapter.clear();
	    adapter.notifyDataSetChanged();
	    buildResults();
	    return true;
	}

	return super.onOptionsItemSelected(item);
    }

    public void buildResults() {
	aq = new AQuery(this);
	currentlyLoading = true;
	String url = "http://confess.io/api/confessions/limit/"
		+ Integer.toString(limit) + "/" + Integer.toString(offset);
	GsonTransformer t = new GsonTransformer();

	aq.transformer(t).ajax(url, Results.class, expire,
		new AjaxCallback<Results>() {
		    public void callback(String url, Results results,
			    AjaxStatus status) {

			if (expire == -1)
			    expire = 30 * 60 * 1000;

			if (results.getSuccess()) {
			    totalCount = Integer.parseInt(results.getData()
				    .getTotalCount());
			    if (results.getData().getConfessions().length > 0) {
				updateList(results);
				adapter.notifyDataSetChanged();
			    }
			}
		    }
		});
    }

    public void updateList(Results results) {
	ConfessionsListing[] confessions = results.getData().getConfessions();
	for (int i = 0; i < confessions.length; i++) {
	    adapter.add(confessions[i]);
	}
	currentlyLoading = false;
    }

}
