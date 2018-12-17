package com.cris.nvh.rxandroidsample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cris.nvh.rxandroidsample.R;
import com.cris.nvh.rxandroidsample.model.GithubItems;
import com.cris.nvh.rxandroidsample.model.GithubUser;

import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.ViewHolder> {
	private static GithubUser sGithubUser;
	private ClickItemListener mListener;

	public GithubUserAdapter(GithubUser githubUser, ClickItemListener listener) {
		mListener = listener;
		sGithubUser = githubUser;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater
			.from(viewGroup.getContext())
			.inflate(R.layout.view_holder, viewGroup, false);
		return new ViewHolder(view, mListener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
		viewHolder.bindData(sGithubUser, i);
	}

	@Override
	public int getItemCount() {
		List<GithubItems> githubItems = sGithubUser.getGithubItems();
		return githubItems == null ? 0 : githubItems.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView mTextScore;
		private TextView mTextId;
		private ClickItemListener mListener;

		public ViewHolder(@NonNull View itemView, ClickItemListener listener) {
			super(itemView);
			mListener = listener;
			mTextId = itemView.findViewById(R.id.text_id);
			mTextScore = itemView.findViewById(R.id.text_score);
			itemView.setOnClickListener(this);
		}

		public void bindData(GithubUser githubUser, int i) {
			GithubItems item = githubUser.getGithubItems().get(i);
			mTextScore.setText(String.valueOf(item.getScore()));
			mTextId.setText(String.valueOf(item.getId()));
		}

		@Override
		public void onClick(View view) {
			mListener.onClickItem(sGithubUser.getGithubItems().get(getAdapterPosition()));
		}
	}

	public interface ClickItemListener {
		void onClickItem(GithubItems item);
	}
}
