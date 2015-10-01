package com.droidsonroids.materialshowcase.data.entities;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class GithubRepo implements Parcelable {
	@SerializedName("name") private String name;
	@SerializedName("stargazers_count") private int stargazersCount;
	@SerializedName("watchers_count") private int watchersCount;
	@SerializedName("forks_count") private int forksCount;

	protected GithubRepo(Parcel in) {
		name = in.readString();
		stargazersCount = in.readInt();
		watchersCount = in.readInt();
		forksCount = in.readInt();
	}

	public static final Creator<GithubRepo> CREATOR = new Creator<GithubRepo>() {
		@Override
		public GithubRepo createFromParcel(Parcel in) {
			return new GithubRepo(in);
		}

		@Override
		public GithubRepo[] newArray(int size) {
			return new GithubRepo[size];
		}
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStargazersCount(int stargazersCount) {
		this.stargazersCount = stargazersCount;
	}

	public void setWatchersCount(int watchersCount) {
		this.watchersCount = watchersCount;
	}

	public void setForksCount(int forksCount) {
		this.forksCount = forksCount;
	}

	public int getStargazersCount() {
		return stargazersCount;
	}

	public int getWatchersCount() {
		return watchersCount;
	}

	public int getForksCount() {
		return forksCount;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(stargazersCount);
		dest.writeInt(watchersCount);
		dest.writeInt(forksCount);
	}
}
