package com.sk.module.user.publicbiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.sk.api.UserApi;
import com.sk.module.user.model.UserModel;

import sk.SKHelper;
import sk.SKPublicBiz;
import sky.OpenMethod;

/**
 * @author sky
 * @version 1.0 on 2019-02-26 10:45 AM
 * @see UserPublicBiz
 */
public class UserPublicBiz extends SKPublicBiz {

	public static final String KEY_LOGIN_STATE = "LOGIN_STATE";

	@OpenMethod(UserApi.checkLogin) public boolean checkLogin() {
		SharedPreferences sharedPreferences = SKHelper.getInstance().getSharedPreferences(KEY_LOGIN_STATE, Context.MODE_PRIVATE);
		String userModelString = sharedPreferences.getString(KEY_LOGIN_STATE, "");
		if (Strings.isNullOrEmpty(userModelString)) {
			return false;
		}
		Gson gson = new Gson();
		UserModel userModel = gson.fromJson(userModelString, UserModel.class);

		if (userModel == null) {
			return false;
		}

		if (Strings.isNullOrEmpty(userModel.token)) {
			return false;
		}
		return true;
	}

}
