package com.android.wekey.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.wekey.R;
import com.android.wekey.fragment.TopicFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity implements OnClickListener {
	@ViewInject(R.id.viewpager)
	private ViewPager pager;
	@ViewInject(R.id.et_search)
	private EditText etSearch;
	@ViewInject(R.id.btn_login)
	private Button btnLogin;
	@ViewInject(R.id.btn_register)
	private Button btnRegister;
	@ViewInject(R.id.indicator)
	private TabPageIndicator indicator ;
	private String[] titles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		titles = getResources().getStringArray(R.array.tabs);
		//ViewPager的adapter
		FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
        
        //如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				Toast.makeText(getApplicationContext(), titles[arg0], Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
	}

	
	/**
	 * ViewPager适配器
	 * @author len
	 *
	 */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	//新建一个Fragment来展示ViewPager item的内容，并传递参数
        	Fragment fragment = new TopicFragment();  
            Bundle args = new Bundle();  
            args.putString("arg", titles[position]);  
            fragment.setArguments(args);  
        	
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position % titles.length];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_register:
			Intent intentRegister = new Intent(this,RegisterActivity.class);
			startActivity(intentRegister);
			break;
		case R.id.btn_login:
			Intent intentLogin = new Intent(this,LoginActivity.class);
			startActivity(intentLogin);
			break;
			
		}
	}

}
