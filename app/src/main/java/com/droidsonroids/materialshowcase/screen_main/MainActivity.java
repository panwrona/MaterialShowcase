package com.droidsonroids.materialshowcase.screen_main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.droidsonroids.materialshowcase.R;
import com.droidsonroids.materialshowcase.data.entities.GithubRepo;
import java.util.List;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity implements MainView {

	private MainPresenter mainPresenter;
	private MainActivityRecyclerAdapter adapter;

	@Bind(R.id.sample_list) RecyclerView mRecyclerView;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		adapter = new MainActivityRecyclerAdapter(this);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(adapter);
		mainPresenter = new MainPresenterImpl(this);
		mainPresenter.subscribe();
		mainPresenter.loadData();
	}

	@Override
	protected void onDestroy() {
		mainPresenter.unsubscribe();
		ButterKnife.unbind(this);
		super.onDestroy();
	}

	@Override
	public void loadData(List<GithubRepo> githubRepoList) {
		adapter.setGithubRepoList(githubRepoList);
	}
}
