package com.connyay.confess;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.connyay.confess.gson.Comments;
import com.connyay.confess.gson.ConfessionsListing;
import com.connyay.confess.gson.GsonTransformer;
import com.connyay.confess.gson.Result;
import com.connyay.confess.gson.Votes;
import com.connyay.confess.utils.Common;

public class SingleView extends SherlockActivity {
    private AQuery aq = new AQuery(this);
    String confession_id;
    // TextView confessionBody, linkTV, dateTV, hugsTV, shrugsTV, commentTV;
    Votes[] votes;
    Comments[] comments;
    String hugCount, shrugCount;
    int commentCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.view);
	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	Intent intent = getIntent();
	confession_id = intent.getStringExtra("confession_id");

	buildResults();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getSupportMenuInflater();
	inflater.inflate(R.menu.single_view, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case android.R.id.home:
	    finish();
	    return true;
	case R.id.menu_browser:
	    String url = "http://" + getSupportActionBar().getTitle();
	    Intent i = new Intent(Intent.ACTION_VIEW);
	    i.setData(Uri.parse(url));
	    startActivity(i);
	    return true;
	case R.id.menu_refresh:
	    Toast.makeText(getApplicationContext(), "Refresh Clicked",
		    Toast.LENGTH_SHORT).show();
	    return true;

	}

	return super.onOptionsItemSelected(item);
    }

    public void buildResults() {

	String url = "http://confess.io/api/confessions/" + confession_id;

	GsonTransformer t = new GsonTransformer();
	// cache for 15 min
	long expire = 15 * 60 * 1000;
	aq.progress(R.id.progress).transformer(t)
		.ajax(url, Result.class, expire, new AjaxCallback<Result>() {
		    public void callback(String url, Result result,
			    AjaxStatus status) {
			if (result.getSuccess()) {
			    ConfessionsListing singleConfession = result
				    .getData().getConfessions();
			    aq.id(R.id.single_confession).text(
				    singleConfession.getConfession());

			    getSupportActionBar().setTitle(
				    getTitle() + singleConfession.getLink());
			    aq.id(R.id.single_link).text(
				    "[" + singleConfession.getLink() + "]");

			    votes = singleConfession.getVotes();

			    if (votes != null) {
				hugCount = getVoteCount("1");
				shrugCount = getVoteCount("-1");
			    } else {
				hugCount = "0";
				shrugCount = "0";
			    }

			    comments = singleConfession.getComments();
			    commentCount = (comments != null) ? comments.length
				    : 0;

			    String formattedDate = Common
				    .dateFormat(singleConfession.getDate());

			    aq.id(R.id.single_date).text(formattedDate);
			    String hugString, shrugString, commentString;

			    hugString = (hugCount.equals("1")) ? "1 Hug"
				    : hugCount + " Hugs";
			    aq.id(R.id.single_hugs).text(hugString);

			    shrugString = (shrugCount.equals("1")) ? "1 Shrug"
				    : shrugCount + " Shrugs";
			    aq.id(R.id.single_shrugs).text(shrugString);

			    commentString = (commentCount == 1) ? "1 comment"
				    : Integer.toString(commentCount)
					    + " comments";
			    aq.id(R.id.single_comment_count)
				    .text(commentString);

			    if (commentCount > 0) {
				buildComments();
			    }
			}
		    }
		});
    }

    public String getVoteCount(String vote) {
	int count = 0;
	if (votes.length > 0) {
	    for (int i = 0; i < votes.length; i++) {
		if (votes[i].getVote().equals(vote))
		    count++;
	    }
	    return Integer.toString(count);
	} else
	    return Integer.toString(count);

    }

    public void buildComments() {

	for (int i = 0; i < comments.length; i++) {
	    LayoutInflater vi = (LayoutInflater) getApplicationContext()
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View v = vi.inflate(R.layout.comment, null);

	    String formattedDate = Common.dateFormat(comments[i].getDate());
	    TextView comment_date = (TextView) v
		    .findViewById(R.id.comment_date);
	    comment_date.setText(formattedDate);

	    TextView comment_body = (TextView) v
		    .findViewById(R.id.comment_body);
	    comment_body.setText(Integer.toString(i + 1) + ". "
		    + comments[i].getText().trim());

	    LinearLayout insertPoint = (LinearLayout) findViewById(R.id.listElement);
	    insertPoint.addView(v, i + 4);
	}
    }

}
