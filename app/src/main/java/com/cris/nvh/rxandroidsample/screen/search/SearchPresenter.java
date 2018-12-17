package com.cris.nvh.rxandroidsample.screen.search;

import com.cris.nvh.rxandroidsample.GitHubClient;
import com.cris.nvh.rxandroidsample.model.GithubUser;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenter implements SearchContract.Presenter {
	private static final String BASE_URL = "https://api.github.com/";
	private SearchContract.View mView;

	public SearchPresenter(SearchContract.View view) {
		mView = view;
	}

	@Override
	public void getData(String input) {
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		Retrofit.Builder builder = new Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create());
		Retrofit retrofit = builder.client(httpClient.build()).build();
		GitHubClient client = retrofit.create(GitHubClient.class);
		client.githubUser(input)
			.subscribeOn(Schedulers.io())
			.observeOn(Schedulers.newThread())
			.distinct()
			.subscribeWith(getObserver());
	}

	private DisposableObserver<GithubUser> getObserver() {
		return new DisposableObserver<GithubUser>() {
			@Override
			public void onNext(GithubUser githubUser) {
				mView.onLoadDataSuccessful(githubUser);
			}

			@Override
			public void onError(Throwable e) {
				mView.onLoadDataFailed(e.getMessage());
			}

			@Override
			public void onComplete() {
			}
		};
	}
}
