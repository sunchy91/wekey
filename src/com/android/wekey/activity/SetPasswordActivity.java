package com.android.wekey.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.wekey.R;
import com.android.wekey.WeKeyApplication;
import com.android.wekey.response.BaseResponse;
import com.android.wekey.utils.ToastUtils;
import com.android.wekey.utils.WeKeyUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SetPasswordActivity extends Activity implements OnClickListener {
	private static final int UPDATE_TIME = 0;
	@ViewInject(R.id.back)
	private Button btnBack;
	@ViewInject(R.id.btn_register)
	private Button btnRegister;
	@ViewInject(R.id.btn_resend_valid_code)
	private Button btnResendValidCode;
	@ViewInject(R.id.avatar)
	private ImageView avatar;
	@ViewInject(R.id.valid_code)
	private EditText etValidCode;
	@ViewInject(R.id.passwrod)
	private EditText etPassword;
	@ViewInject(R.id.nickname)
	private EditText etNickname;
	protected long startTime;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String timerTemplate = getString(R.string.btn_valid_code_countdown);
			long currentTime = System.currentTimeMillis();
			int count = (int) ((currentTime - startTime) / 1000);
			if (count < 10) {
				String formatedStr = String.format(timerTemplate, count);
				btnResendValidCode.setText(formatedStr);
				handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
			} else {
				btnResendValidCode.setEnabled(true);
				btnResendValidCode.setText(R.string.btn_resend_valid_code);
			}

		}
	};
	private String mobileNumber;
	private String regionCode;
	private HttpUtils http;
	private String avatarUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_password);
		mobileNumber = getIntent().getStringExtra("mobileNumber");
		regionCode = getIntent().getStringExtra("regionCode");
		http = WeKeyApplication.getInstance().getHttpUtils();
		ViewUtils.inject(this);
		initViews();
	}

	private void initViews() {
		btnBack.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		btnResendValidCode.setOnClickListener(this);
		btnResendValidCode.setEnabled(false);
		startCount();
	}

	private void startCount() {
		startTime = System.currentTimeMillis();
		handler.sendEmptyMessage(UPDATE_TIME);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.btn_resend_valid_code:
			sendValidCode(mobileNumber, regionCode);
			break;
		case R.id.btn_register:
			register();
			break;
		}
	}

	private void sendValidCode(String mobileNumber, String regionCode) {
		String url = "/common/mobile/send";
		RequestParams params = new RequestParams();
		params.addBodyParameter("phone_no", mobileNumber);
		params.addBodyParameter("check_type", "REGIST");
		http.send(HttpMethod.GET, WeKeyApplication.HOST + url,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {

						ToastUtils.showToast(SetPasswordActivity.this,
								R.drawable.ic_launcher,
								R.string.btn_resend_valid_code,
								R.string.btn_resend_valid_code);
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						parseValidCodeResult(response.result);
					}

					private void parseValidCodeResult(String result) {
						Gson gson = new Gson();
						BaseResponse response=gson.fromJson(result, new TypeToken<BaseResponse>(){}.getType());
					    if(response.error==0){
					    	startCount();
					    }
						
					}
				});
	}

	private void register() {
		String validCode = etValidCode.getText().toString().trim();
		String password = etPassword.getText().toString().trim();
		String nickname = etNickname.getText().toString().trim();
		String url = "/user/register";
		RequestParams params = new RequestParams();
		params.addBodyParameter("deviceId", WeKeyUtils.getDeviceId(this));
		params.addBodyParameter("mobile", mobileNumber);
		params.addBodyParameter("mobileCode", validCode);
		params.addBodyParameter("userName", nickname);
		params.addBodyParameter("headImage", avatarUrl);
		params.addBodyParameter("password", password);
		params.addBodyParameter("mobile", mobileNumber);
		http.send(HttpMethod.GET, WeKeyApplication.HOST + url,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {

						ToastUtils.showToast(SetPasswordActivity.this,
								R.drawable.ic_launcher,
								R.string.register_failed_title,
								R.string.register_failed);
					}

					@Override
					public void onSuccess(ResponseInfo<String> response) {
						parseValidCodeResult(response.result);
					}

					private void parseValidCodeResult(String result) {
						Gson gson = new Gson();
						BaseResponse response=gson.fromJson(result, new TypeToken<BaseResponse>(){}.getType());
						
					}
				});
	}
}
