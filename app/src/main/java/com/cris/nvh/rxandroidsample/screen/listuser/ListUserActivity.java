package com.cris.nvh.rxandroidsample.screen.listuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cris.nvh.rxandroidsample.R;
import com.cris.nvh.rxandroidsample.adapter.GithubUserAdapter;
import com.cris.nvh.rxandroidsample.model.GithubItems;
import com.cris.nvh.rxandroidsample.model.GithubUser;

import static com.cris.nvh.rxandroidsample.screen.search.SearchActivity.EXTRA_USER;
import static com.cris.nvh.rxandroidsample.screen.userdetails.UserDetails.getUserDetailsIntent;

public class ListUserActivity extends AppCompatActivity implements
	GithubUserAdapter.ClickItemListener {

	private RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listuser);
		mRecyclerView = findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		GithubUserAdapter adapter = new GithubUserAdapter(getData(), this);
		mRecyclerView.setAdapter(adapter);
	}

	@Override
	public void onClickItem(GithubItems items) {
		startActivity(getUserDetailsIntent(this, items));
	}

	public static Intent getUserIntent(Context context, GithubUser githubUser) {
		Intent intent = new Intent(context, ListUserActivity.class);
		intent.putExtra(EXTRA_USER, githubUser);
		return intent;
	}

	private GithubUser getData() {
		return getIntent().getParcelableExtra(EXTRA_USER);
	}
}
