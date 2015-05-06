package com.android.wekey;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.android.wekey.entity.UserInfo;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class WeKeyApplication extends Application {
	private static WeKeyApplication instance;
	private HttpUtils httpUtils;
	private UserInfo loginUser;
	public static final String HOST="http://www.cimiword.com";
	@Override
	public void onCreate() {
		super.onCreate();
		instance=this;
		httpUtils = new HttpUtils();
		initImageLoader(getApplicationContext());
	}
	public static synchronized WeKeyApplication getInstance(){
		return instance;
	}
	
	public synchronized HttpUtils getHttpUtils(){
		return httpUtils;
	}
	public UserInfo getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(UserInfo loginUser) {
		this.loginUser = loginUser;
	}
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024) // 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
		
		
	}
	
}
