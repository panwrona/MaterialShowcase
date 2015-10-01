package com.droidsonroids.materialshowcase.screen_details.screen_commits;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.droidsonroids.materialshowcase.R;

public class CommitsFragment extends Fragment implements CommitsFragmentView {

	private CommitsPresenter presenter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_commits, container, false);
		ButterKnife.bind(view);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		presenter = new CommitsPresenterImpl();
	}
}
