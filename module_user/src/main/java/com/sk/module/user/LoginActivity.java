package com.sk.module.user;

import butterknife.BindView;
import butterknife.OnClick;
import sk.SKHelper;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.sk.api.UserApi;

import sk.SKActivity;
import sk.SKActivityBuilder;
import sky.OpenMethod;

/**
 * @author sky
 * @date Created on 2019-02-25 2:59 PM
 * @version 1.0
 * @Description LoginActivity - 描述
 */
public class LoginActivity extends SKActivity<LoginBiz> {

	@OpenMethod(UserApi.intentLogin) public static final void intent() {
		SKHelper.display().intent(LoginActivity.class);
	}

	@BindView(R2.id.tv_tip) TextView	tvTip;

	@BindView(R2.id.btn_login) Button	btnLogin;

	@BindView(R2.id.btn_out) Button		btnOut;

	@BindView(R2.id.btn_close) Button	btnClose;

	@Override protected SKActivityBuilder build(SKActivityBuilder skBuilder) {
		skBuilder.layoutId(R.layout.user_activity_login);
		return skBuilder;
	}

	@Override protected void initData(Bundle savedInstanceState) {
		biz().getLoginState().observe(this, new SKViewObserver<Boolean>() {

			@Override public void onChanged(Boolean aBoolean) {
				tvTip.setText(aBoolean ? "登录状态" : "未登录状态");
			}
		});
	}

	@OnClick(R2.id.btn_login) public void onBtnLoginClicked() {
		biz().login();
	}

	@OnClick(R2.id.btn_out) public void onBtnOutClicked() {
		biz().out();
	}

	@OnClick(R2.id.btn_close) public void onBtnCloseClicked() {
		onKeyBack();
	}
}