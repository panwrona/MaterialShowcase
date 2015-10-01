package com.droidsonroids.materialshowcase.screen_details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import com.droidsonroids.materialshowcase.R;
import com.droidsonroids.materialshowcase.data.entities.GithubRepo;
import com.droidsonroids.materialshowcase.utils.GUIUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailsActivity extends Activity implements DetailsView {
	private static final String ARGS_GITHUB_REPO = "args_github_repo";

	@Bind(R.id.activity_details_toolbar) Toolbar mToolbar;
	@Bind(R.id.activity_details_vw_circle) View mVwCircle;
	@Bind(R.id.activity_details_tv_title) TextView mTvTitle;
	@Bind(R.id.activity_details_tv_forks) TextView mTvForks;
	@Bind(R.id.activity_details_tv_stars) TextView mTvStars;
	@Bind(R.id.activity_details_tv_watchers) TextView mTvWatchers;

	@BindString(R.string.stars) String mStars;
	@BindString(R.string.followers) String mFollowers;
	@BindString(R.string.forks) String mForks;

	private DetailsActivityPresenter presenter;
	private GithubRepo githubRepo;

	public static void startActivity(Context ctx, GithubRepo repo) {
		Intent intent = new Intent(ctx, DetailsActivity.class);
		intent.putExtra(ARGS_GITHUB_REPO, repo);
		ctx.startActivity(intent);
	}

	public static Intent getIntent(Context ctx, GithubRepo repo) {
		Intent intent = new Intent(ctx, DetailsActivity.class);
		intent.putExtra(ARGS_GITHUB_REPO, repo);
		return intent;
	}

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		ButterKnife.bind(this);
		presenter = new DetailsActivityPresenterImpl();
		githubRepo = getIntent().getParcelableExtra(ARGS_GITHUB_REPO);
		initViews();
		//setupEnterAnimation();
		//setupExitAnimation();
		//TODO make delightful reveal transition on toolbar with title fade in
	}

	private void initViews() {
		new Handler(Looper.getMainLooper()).post(() -> {
			mTvTitle.setText(githubRepo.getName());
			mTvStars.setText(mStars + " " + githubRepo.getStargazersCount());
			mTvWatchers.setText(mFollowers + " " + githubRepo.getWatchersCount());
			mTvForks.setText(mForks + " " + githubRepo.getForksCount());
		});
	}
}
