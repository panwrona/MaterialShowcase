package com.droidsonroids.materialshowcase.screen_main;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import com.droidsonroids.materialshowcase.R;
import com.droidsonroids.materialshowcase.data.entities.GithubRepo;

public class GithubRepoViewHolder extends RecyclerView.ViewHolder {

	@Bind(R.id.cardview_github_tv_header) TextView mTvHeader;
	@Bind(R.id.cardview_github_tv_stars) TextView mTvStars;
	@Bind(R.id.cardview_github_tv_watchers) TextView mTvWatchers;
	@Bind(R.id.cardview_github_tv_forks) TextView mTvForks;
	//@Bind(R.id.activity_details_vw_circle) View mFab;

	@BindString(R.string.stars) String mStars;
	@BindString(R.string.watchers) String mWatchers;
	@BindString(R.string.forks) String mForks;

	private int currentPosition;

	public GithubRepoViewHolder(View itemView, OnGithubRepoViewClickListener listener) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		//mFab.setOnClickListener(v -> listener.onItemClick(this, currentPosition));
	}

	public void updateViews(GithubRepo githubRepo, int position) {
		currentPosition = position;
		new Handler(Looper.getMainLooper()).post(() -> {
			mTvHeader.setText(githubRepo.getName());
			mTvStars.setText(mStars + " " + githubRepo.getStargazersCount());
			mTvWatchers.setText(mWatchers + " " + githubRepo.getWatchersCount());
			mTvForks.setText(mForks + " " + githubRepo.getForksCount());
		});
	}

	//public View getFab() {
	//	return mFab;
	//}

	public TextView getTitle() {
		return mTvHeader;
	}
}
