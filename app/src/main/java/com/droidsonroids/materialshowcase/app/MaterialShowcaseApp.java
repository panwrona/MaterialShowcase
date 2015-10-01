package com.droidsonroids.materialshowcase.app;

import android.app.Application;
import com.droidsonroids.materialshowcase.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MaterialShowcaseApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		CalligraphyConfig.initDefault(
			new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Light.ttf")
				.setFontAttrId(R.attr.fontPath)
				.build());
	}
}
