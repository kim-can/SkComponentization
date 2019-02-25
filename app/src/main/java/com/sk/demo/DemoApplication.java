package com.sk.demo;

import android.app.Application;
import android.content.Context;

import com.sk.api.manage.DemoSK;
import com.sk.cpt.views.DemoCommonView;
import com.sk.module.order.OderLibrary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.multidex.MultiDex;
import sk.SKDI;
import sk.SKDefaultLibrary;
import sk.SKHelper;
import sky.SKDIApp;

/**
 * @author sky
 * @version 1.0 on 2019-02-11 10:29 AM
 * @see DemoApplication
 */
@SKDIApp({SKDefaultLibrary.class, OderLibrary.class})
public class DemoApplication extends Application {

	@Override public void onCreate() {
		super.onCreate();
		SKDI.builder().setApplication(this).setISK(new DemoSK()).setSKCommonView(new DemoCommonView()).build();
		SKHelper.moduleManage().init();
		closeAndroidPDialog();
	}

	@Override protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		// dex突破65535的限制
		MultiDex.install(this);
	}

	private void closeAndroidPDialog(){
		try {
			Class aClass = Class.forName("android.content.pm.PackageParser$Package");
			Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
			declaredConstructor.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class cls = Class.forName("android.app.ActivityThread");
			Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
			declaredMethod.setAccessible(true);
			Object activityThread = declaredMethod.invoke(null);
			Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
			mHiddenApiWarningShown.setAccessible(true);
			mHiddenApiWarningShown.setBoolean(activityThread, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
