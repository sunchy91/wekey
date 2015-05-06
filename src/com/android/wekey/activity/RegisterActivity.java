package com.android.wekey.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.wekey.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class RegisterActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.back)
	private Button btnBack;
	@ViewInject(R.id.btn_get_valid_code)
	private Button btnGetValidCode;
	@ViewInject(R.id.mobile)
	private EditText etMobile;
	@ViewInject(R.id.region_code)
	private TextView tvRegionCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		ViewUtils.inject(this);
		initViews();
	}
	
	private void initViews(){
		btnBack.setOnClickListener(this);
		btnGetValidCode.setOnClickListener(this);
	}
	private SpannableString getColorRegisterDescription(String string,int start,int end,String color){
		 SpannableString ss = new SpannableString(string);
		 ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	    return ss;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.back:
			finish();
			break;
		case R.id.btn_get_valid_code:
			String mobileNumber = etMobile.getText().toString().trim();
			String regionCode=tvRegionCode.getText().toString().trim();
			Intent intentSetPwd = new Intent(this,SetPasswordActivity.class);
			intentSetPwd.putExtra("mobileNumber", mobileNumber);
			intentSetPwd.putExtra("regionCode", regionCode);
			startActivity(intentSetPwd);
			break;
		}
		
	}
}
