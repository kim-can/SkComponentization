package com.sk.api.manage;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sk.api.UserApi;
import com.sk.cpt.views.dialog.LoadingDialogFragment;

import java.lang.reflect.Method;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sk.ISK;
import sk.L;
import sk.SKActivity;
import sk.SKDefaultManager;
import sk.SKErrorEnum;
import sk.SKHelper;
import sk.SKInterceptor;
import sk.livedata.SKData;
import sk.plugins.SKActivityInterceptor;
import sk.plugins.SKDisplayEndInterceptor;
import sk.plugins.SKDisplayStartInterceptor;
import sk.plugins.SKErrorInterceptor;

/**
 * @author sky
 * @version 1.0 on 2019-02-13 11:54 AM
 * @see DemoSK
 */
public class DemoSK implements ISK {

	@Override public boolean isLogOpen() {
		return true;
	}

	@Override public Retrofit.Builder httpAdapter(Retrofit.Builder builder) {
		builder.baseUrl("https://api.github.com");

		Gson gson = new GsonBuilder().setLenient().create();
		builder.addConverterFactory(GsonConverterFactory.create(gson));
		return builder;
	}

	@Override public SKInterceptor.Builder pluginInterceptor(SKInterceptor.Builder builder) {
		builder.addErrorIntercepor(new SKErrorInterceptor() {

			@Override public void interceptorError(Method method, Object clazz, Object[] objects, int interceptor, SKErrorEnum skErrorEnum) {
				L.e(method.getName() + ":" + clazz + ":" + objects + "：：" + interceptor);
				for (Object obj : objects) {
					if (obj instanceof SKData) {
						SKData skData = (SKData) obj;
						switch (skErrorEnum) {
							case HTTP:
								skData.layoutError();
								skData.closeLoading();
								break;
							case LOGIC:
								skData.layoutError();
								skData.closeLoading();
								break;
						}
						break;
					}
				}

			}
		});

		builder.setDisplayStartInterceptor(new SKDisplayStartInterceptor() {

			@Override public boolean interceptStart(String clazzName, Bundle bundle) {
				L.i("执行开始 ::::" + clazzName + ": " + bundle);
				if (clazzName.indexOf("LoginActivity") != -1) {
					SKHelper.toast().show("未登录状态,请先登录");
					return false;
				}
				boolean isLogin = SKHelper.moduleBiz(UserApi.checkLogin).run();

				if (!isLogin) {
					SKHelper.toast().show("未登录状态,请先登录");
				}
				return !isLogin;
			}
		});
		builder.setDisplayEndInterceptor(new SKDisplayEndInterceptor() {

			@Override public void interceptEnd(String clazzName, Bundle bundle, Object backgroundResult) {
				L.i("执行结束 ::::" + clazzName + ": " + bundle + ": " + backgroundResult);
			}
		});

		builder.setActivityInterceptor(new SKActivityInterceptor.AdapterInterceptor() {

			@Override public void onShowLoading(SKActivity skyActivity) {
				super.onShowLoading(skyActivity);
				skyActivity.getSupportFragmentManager().beginTransaction().add(LoadingDialogFragment.getInstance(), "loading_dialog").commitAllowingStateLoss();
				skyActivity.getSupportFragmentManager().executePendingTransactions();
			}

			@Override public void onCloseLoading(SKActivity skyActivity) {
				super.onCloseLoading(skyActivity);
				LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) skyActivity.getSupportFragmentManager().findFragmentByTag("loading_dialog");
				if (loadingDialogFragment != null) {
					loadingDialogFragment.dismissAllowingStateLoss();
					skyActivity.getSupportFragmentManager().executePendingTransactions();
				}
			}
		});

		return builder;
	}

	@Override public SKDefaultManager manage() {
		return new SKDefaultManager();
	}
}
