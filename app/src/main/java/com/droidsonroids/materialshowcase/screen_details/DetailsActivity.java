package com.droidsonroids.materialshowcase.screen_details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
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
	@Bind(R.id.activity_details_cv_layout) CardView mCvLayout;

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
		setupEnterAnimation();
		setupExitAnimation();
	}

	private void initViews() {
		new Handler(Looper.getMainLooper()).post(() -> {
			mVwCircle.setVisibility(View.INVISIBLE);
			mCvLayout.setVisibility(View.VISIBLE);
			mTvTitle.setVisibility(View.VISIBLE);
			AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
			animation.setDuration(500);
			mTvTitle.startAnimation(animation);
			mCvLayout.startAnimation(animation);
			mTvTitle.animate().alpha(1).setDuration(600).start();
			mCvLayout.animate().alpha(1).setDuration(600).start();
			mTvTitle.setText(githubRepo.getName());
			mTvStars.setText(mStars + " " + githubRepo.getStargazersCount());
			mTvWatchers.setText(mFollowers + " " + githubRepo.getWatchersCount());
			mTvForks.setText(mForks + " " + githubRepo.getForksCount());
		});
	}

	@Override
	public void onBackPressed() {
		GUIUtils.animateRevealHide(this, mToolbar, R.color.accent_color, mVwCircle.getWidth() / 2,
			new OnRevealAnimationListener() {
				@Override
				public void onRevealHide() {
					mVwCircle.setVisibility(View.VISIBLE);
					backPressed();
				}

				@Override
				public void onRevealShow() {

				}
			});
	}

	private void backPressed() {
		super.onBackPressed();
	}

	private void setupExitAnimation() {
		Fade fade = new Fade();
		getWindow().setReturnTransition(fade);
		fade.setDuration(getResources().getInteger(R.integer.animation_duration));
		fade.addListener(new Transition.TransitionListener() {
			@Override
			public void onTransitionStart(Transition transition) {
			}

			@Override
			public void onTransitionEnd(Transition transition) {
			}

			@Override
			public void onTransitionCancel(Transition transition) {
			}

			@Override
			public void onTransitionPause(Transition transition) {
			}

			@Override
			public void onTransitionResume(Transition transition) {
			}
		});
	}

	private void setupEnterAnimation() {
		Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
		getWindow().setSharedElementEnterTransition(transition);
		transition.addListener(new Transition.TransitionListener() {
			@Override
			public void onTransitionStart(Transition transition) {

			}

			@Override
			public void onTransitionEnd(Transition transition) {
				transition.removeListener(this);
				animateRevealShow(mToolbar);
			}

			@Override
			public void onTransitionCancel(Transition transition) {

			}

			@Override
			public void onTransitionPause(Transition transition) {

			}

			@Override
			public void onTransitionResume(Transition transition) {

			}
		});
	}

	private void animateRevealShow(final View viewRoot) {
		int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
		int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
		GUIUtils.animateRevealShow(this, mToolbar, mVwCircle.getWidth() / 2, R.color.accent_color,
			cx, cy, new OnRevealAnimationListener() {
				@Override
				public void onRevealHide() {

				}

				@Override
				public void onRevealShow() {
					initViews();
				}
			});
	}
}
