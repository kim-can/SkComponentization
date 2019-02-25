package com.sk.module.main;

import sk.SKBiz;
import sk.SKHelper;
import sky.Background;
import sky.BackgroundType;
import sky.OpenMethod;

import android.os.Bundle;

import com.sk.api.MainApi;

/**
 * @author sky
 * @date Created on 2019-02-11 10:25 AM
 * @version 1.0
 * @Description MainBiz - 描述
 */
public class MainBiz extends SKBiz {

	@Override public void initBiz(Bundle bundle) {}

	@OpenMethod(MainApi.showTip) @Background(BackgroundType.HTTP) public void showTip(String value) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		SKHelper.toast().show("MainBiz:" + value);
	}
}