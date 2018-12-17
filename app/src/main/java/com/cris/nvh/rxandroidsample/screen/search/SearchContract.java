package com.cris.nvh.rxandroidsample.screen.search;

import com.cris.nvh.rxandroidsample.model.GithubUser;

public class SearchContract {
	interface View {
		void onLoadDataSuccessful(GithubUser githubUser);

		void onLoadDataFailed(String message);
	}

	interface Presenter {
		void getData(String input);
	}
}
