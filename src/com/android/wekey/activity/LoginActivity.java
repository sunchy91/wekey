package com.android.wekey.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.wekey.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LoginActivity extends Activity implements OnClickListener {
	@ViewInject(R.id.et_username)
	private EditText etUsername;
	@ViewInject(R.id.et_password)
	private EditText etPassword;
	@ViewInject(R.id.btn_login)
	private Button btnLogin;
	@ViewInject(R.id.register_new_user)
	private TextView registerNewUser;
	@ViewInject(R.id.forget_password)
	private TextView forgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.login);
    	ViewUtils.inject(this);
    	btnLogin.setOnClickListener(this);
    	registerNewUser.setOnClickListener(this);
    	forgetPassword.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_login:
			login();
			break;
		case R.id.register_new_user:
			Intent intentRegister = new Intent(this,RegisterActivity.class);
			startActivity(intentRegister);
			break;
		case R.id.forget_password:
			Intent intentForgetPassword = new Intent(this,ForgetPasswordActivity.class);
			startActivity(intentForgetPassword);
			break;
		}
	}
	private void login() {
		// TODO Auto-generated method stub
		
	}
}
