package com.droidsonroids.materialshowcase.screen_main;

import com.droidsonroids.materialshowcase.data.entities.GithubRepo;
import java.util.List;

public interface MainView {
	void loadData(List<GithubRepo> githubRepoList);
}
