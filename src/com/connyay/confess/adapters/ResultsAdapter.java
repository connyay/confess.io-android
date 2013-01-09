package com.connyay.confess.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.connyay.confess.R;
import com.connyay.confess.SingleView;
import com.connyay.confess.gson.Comments;
import com.connyay.confess.gson.ConfessionsListing;
import com.connyay.confess.gson.Votes;
import com.connyay.confess.utils.Common;

public class ResultsAdapter extends ArrayAdapter<ConfessionsListing> {
    AQuery listAq = new AQuery(this.getContext());
    Votes[] votes;
    Comments[] comments;
    String hugCount, shrugCount;
    int commentCount;

    public ResultsAdapter(Context context) {
	super(context, 0);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
	if (convertView == null) {
	    convertView = LayoutInflater.from(getContext()).inflate(
		    R.layout.row, null);
	}
	AQuery aq = listAq.recycle(convertView);

	ConfessionsListing confession = getItem(position);
	votes = confession.getVotes();
	comments = confession.getComments();
	if (votes != null) {
	    hugCount = getVoteCount("1");
	    shrugCount = getVoteCount("-1");
	} else {
	    hugCount = "0";
	    shrugCount = "0";
	}
	commentCount = (comments != null) ? comments.length : 0;
	String confessionBody = confession.getConfession().trim();
	if (confessionBody.length() > 350) {
	    confessionBody = confessionBody.substring(0, 350);
	    confessionBody += "... \n\n <br><br><em><u>Continue Reading >></u></em>";
	    Spanned sp = Html.fromHtml(confessionBody);
	    aq.id(R.id.row_body).text(sp);
	} else {
	    aq.id(R.id.row_body).text(confessionBody);
	}

	aq.id(R.id.row_link).text("[" + confession.getLink() + "]");

	String formattedDate = Common.dateFormat(confession.getDate());
	aq.id(R.id.row_date).text(formattedDate);

	if (hugCount.equals("1"))
	    aq.id(R.id.row_hugs).text("1 Hug");
	else
	    aq.id(R.id.row_hugs).text(hugCount + " Hugs");

	if (shrugCount.equals("1"))
	    aq.id(R.id.row_shrugs).text("1 Shrug");
	else
	    aq.id(R.id.row_shrugs).text(shrugCount + " Shrugs");

	if (commentCount == 1)
	    aq.id(R.id.row_comment_count).text("1 comment");
	else
	    aq.id(R.id.row_comment_count).text(
		    Integer.toString(commentCount) + " comments");

	convertView.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {

		ConfessionsListing singleConfession = getItem(position);
		Intent viewIntent = new Intent(getContext(), SingleView.class);
		viewIntent.putExtra("confession_id", singleConfession.getId());

		v.getContext().startActivity(viewIntent);
	    }
	});
	return convertView;
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

}
