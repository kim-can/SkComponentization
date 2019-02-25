package com.sk.module.main;

import android.os.Bundle;
import android.widget.Button;

import com.sk.api.OderApi;

import butterknife.BindView;
import butterknife.OnClick;
import sk.SKActivity;
import sk.SKActivityBuilder;
import sk.SKHelper;

/**
 * @author sky
 * @version 1.0
 * @date Created on 2019-02-11 10:25 AM
 * @Description MainActivity - 描述
 */
public class MainActivity extends SKActivity<MainBiz> {

	@BindView(R2.id.btn_oder) Button	btnOder;

	@BindView(R2.id.btn_user) Button	btnUser;

	@BindView(R2.id.btn_login) Button	btnLogin;

	public static final void intent() {
		SKHelper.display().intent(MainActivity.class);
	}

	@Override protected SKActivityBuilder build(SKActivityBuilder skBuilder) {
		skBuilder.layoutId(R.layout.activity_main);
		return skBuilder;
	}

	@Override protected void initData(Bundle savedInstanceState) {

	}

	@OnClick(R2.id.btn_oder) public void onBtnOderClicked() {
		SKHelper.moduleBiz(OderApi.intentOder).run();
	}

	@OnClick(R2.id.btn_user) public void onBtnUserClicked() {}

	@OnClick(R2.id.btn_login) public void onBtnLoginClicked() {}
}