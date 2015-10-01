package com.droidsonroids.materialshowcase.screen_details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class DetailsFragment extends Fragment implements DetailsFragmentView {

	private DetailsFragmentPresenter presenter;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		presenter = new DetailsFragmentPresenterImpl();
	}
}
