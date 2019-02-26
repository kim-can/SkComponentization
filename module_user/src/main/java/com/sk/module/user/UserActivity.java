package com.sk.module.user;

import butterknife.BindView;
import butterknife.OnClick;
import sk.SKHelper;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.sk.api.MainApi;
import com.sk.api.UserApi;

import sk.SKActivity;
import sk.SKActivityBuilder;
import sky.OpenMethod;

/**
 * @author sky
 * @date Created on 2019-02-25 3:40 PM
 * @version 1.0
 * @Description UserActivity - 描述
 */
public class UserActivity extends SKActivity<UserBiz> {

	@OpenMethod(UserApi.intentUser) public static final void intent(String name, String pwd, int age) {
		Bundle bundle = new Bundle();
		bundle.putString("name", name);
		bundle.putString("pwd", pwd);
		bundle.putInt("age", age);
		SKHelper.display().intent(UserActivity.class, bundle);
	}

	@BindView(R2.id.tv_name) TextView		tvName;

	@BindView(R2.id.tv_password) TextView	tvPassword;

	@BindView(R2.id.tv_age) TextView		tvAge;

	@BindView(R2.id.btn_login) Button		btnLogin;

	@Override protected SKActivityBuilder build(SKActivityBuilder skBuilder) {
		skBuilder.layoutId(R.layout.user_activity_user);
		return skBuilder;
	}

	@Override protected void initData(Bundle savedInstanceState) {
		tvName.setText("用户名:" + savedInstanceState.getString("name"));
		tvPassword.setText("密码:" + savedInstanceState.getString("pwd"));
		tvAge.setText("年龄:" + savedInstanceState.getInt("age"));
	}

	@OnClick(R2.id.btn_login) public void onViewClicked() {
		//交给main 方法处理
		SKHelper.moduleBiz(MainApi.showTip).run(tvName.getText().toString());
	}
}