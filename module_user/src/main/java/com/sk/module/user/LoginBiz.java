package com.sk.module.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.sk.module.user.model.UserModel;
import com.sk.module.user.publicbiz.UserPublicBiz;

import sk.SKBiz;
import sk.SKHelper;
import sk.livedata.SKData;
import sky.SKWork;

import static com.sk.module.user.publicbiz.UserPublicBiz.KEY_LOGIN_STATE;

/**
 * @author sky
 * @date Created on 2019-02-25 2:59 PM
 * @version 1.0
 * @Description LoginBiz - 描述
 */
public class LoginBiz extends SKBiz {

	private SKData<Boolean> skData;

	@Override public void initBiz(Bundle bundle) {
		skData = new SKData<>();
		boolean isLogin = SKHelper.biz(UserPublicBiz.class).checkLogin();
		skData.setValue(isLogin);
	}

	@SKWork public void checkLoginState() {

	}

	@SKWork public void login() {
		UserModel userModel = new UserModel();
		userModel.token = "jincan";

		SharedPreferences.Editor editor = SKHelper.getInstance().getSharedPreferences(KEY_LOGIN_STATE, Context.MODE_PRIVATE).edit();

		Gson gson = new Gson();
		String jsonStr = gson.toJson(userModel);
		editor.putString(KEY_LOGIN_STATE, jsonStr);
		boolean isOK = editor.commit();
		if (isOK) {
			skData.postValue(true);
			SKHelper.toast().show("登录成功");
		} else {
			SKHelper.toast().show("登录失败");
		}
	}

	@SKWork public void out() {
		SharedPreferences.Editor editor = SKHelper.getInstance().getSharedPreferences(KEY_LOGIN_STATE, Context.MODE_PRIVATE).edit();
		editor.remove(KEY_LOGIN_STATE);
		boolean isOK = editor.commit();
		if (isOK) {
			skData.postValue(false);
			SKHelper.toast().show("退出成功");
		} else {
			SKHelper.toast().show("退出失败");
		}
	}

	public SKData<Boolean> getLoginState() {
		return skData;
	}
}