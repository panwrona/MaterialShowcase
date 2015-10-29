package com.droidsonroids.materialshowcase.screen_main;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.droidsonroids.materialshowcase.R;
import com.droidsonroids.materialshowcase.data.entities.GithubRepo;
import com.droidsonroids.materialshowcase.screen_details.DetailsActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivityRecyclerAdapter extends RecyclerView.Adapter<GithubRepoViewHolder> {

	private List<GithubRepo> githubRepoList = new ArrayList<>();
	private Activity mMainActivity;

	public MainActivityRecyclerAdapter(Activity mMainActivity) {
		this.mMainActivity = mMainActivity;
	}

	@Override
	public GithubRepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.cardview_githubrepo, parent, false);
		return new GithubRepoViewHolder(view, onGithubRepoViewClickListenerMaterial);
	}

	@Override
	public void onBindViewHolder(GithubRepoViewHolder holder, int position) {
		holder.updateViews(githubRepoList.get(position), position);
	}

	@Override
	public int getItemCount() {
		return githubRepoList.size();
	}

	public void setGithubRepoList(List<GithubRepo> githubRepoList) {
		this.githubRepoList = githubRepoList;
		notifyDataSetChanged();
	}

	private OnGithubRepoViewClickListener onGithubRepoViewClickListener =
		(viewHolder, repoPosition) -> DetailsActivity.startActivity(mMainActivity,
			githubRepoList.get(repoPosition));

	@SuppressWarnings("unchecked") private OnGithubRepoViewClickListener
		onGithubRepoViewClickListenerMaterial = new OnGithubRepoViewClickListener() {
		@Override
		public void onItemClick(GithubRepoViewHolder viewHolder, int repoPosition) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				//Intent intent = DetailsActivity.getIntent(mMainActivity, githubRepoList.get(repoPosition));
				//ActivityOptionsCompat option =
				//	ActivityOptionsCompat.makeSceneTransitionAnimation(mMainActivity,
				//		new Pair<View, String>(viewHolder.getFab(), "reveal"));
				//ActivityCompat.startActivity(mMainActivity, intent, option.toBundle());
			}
		}
	};
}
