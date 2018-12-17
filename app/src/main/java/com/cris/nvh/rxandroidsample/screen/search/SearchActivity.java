package com.cris.nvh.rxandroidsample.screen.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cris.nvh.rxandroidsample.R;
import com.cris.nvh.rxandroidsample.model.GithubUser;

import static com.cris.nvh.rxandroidsample.screen.listuser.ListUserActivity.getUserIntent;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, View.OnClickListener {

	public static final String EXTRA_USER =
		"com.cris.nvh.mvpdemo.extra.EXTRA_USER";
	private static final int EMPTY = 0;
	private SearchContract.Presenter mPresenter;
	private EditText mEditSearch;
	private Button mButtonSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		mEditSearch = findViewById(R.id.edit_search);
		mButtonSearch = findViewById(R.id.button_search);
		mPresenter = new SearchPresenter(this);
		mButtonSearch.setOnClickListener(this);
	}

	@Override
	public void onLoadDataSuccessful(GithubUser githubUser) {
		startActivity(getUserIntent(this, githubUser));
		finish();
	}

	@Override
	public void onLoadDataFailed(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onClick(View view) {
		if (mEditSearch.getText().length() > EMPTY) {
			String input = mEditSearch.getText().toString();
			mPresenter.getData(input);
			return;
		}
		Toast.makeText
			(this, getString(R.string.verify_empty), Toast.LENGTH_SHORT).show();
	}
}
