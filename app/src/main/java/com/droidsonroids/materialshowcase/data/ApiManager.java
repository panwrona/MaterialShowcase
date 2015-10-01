package com.droidsonroids.materialshowcase.data;

import com.droidsonroids.materialshowcase.data.entities.GithubRepo;
import com.droidsonroids.materialshowcase.data.entities.OwnerResponse;
import com.droidsonroids.materialshowcase.utils.Constants;
import java.util.List;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.android.AndroidLog;

public class ApiManager implements RestAdapter {

	private RestAdapter restAdapter;
	private RequestInterceptor requestInterceptor;

	public ApiManager() {
		requestInterceptor = request -> request.addHeader(Constants.USER_AGENT, "DroidsOnRoids");
		retrofit.RestAdapter restAdapter = initRestAdapter();
		this.restAdapter = restAdapter.create(RestAdapter.class);
	}

	private retrofit.RestAdapter initRestAdapter() {
		return new retrofit.RestAdapter.Builder().
			setLogLevel(retrofit.RestAdapter.LogLevel.FULL).
			setLog(new AndroidLog("TEST")).
			setRequestInterceptor(requestInterceptor).
			setEndpoint(Constants.REST_ENDPOINT).
			build();
	}

	@Override
	public void getGithubRepos(Callback<List<GithubRepo>> callback) {
		restAdapter.getGithubRepos(callback);
	}

	@Override
	public void getOwner(Callback<OwnerResponse> callback) {
		restAdapter.getOwner(callback);
	}
}
