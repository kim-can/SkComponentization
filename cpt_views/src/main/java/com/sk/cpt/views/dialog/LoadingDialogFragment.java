package com.sk.cpt.views.dialog;

import android.os.Bundle;

import com.sk.cpt.views.R;

import sk.SKDialogFragment;
import sk.SKDialogFragmentBuilder;

/**
 * @author sky
 * @date Created on 2019-02-13 10:57 AM
 * @version 1.0
 * @Description LoadingDialogFragment - 描述
 */
public class LoadingDialogFragment extends SKDialogFragment<LoadingBiz> {

	public static final LoadingDialogFragment getInstance() {
		LoadingDialogFragment loadingdialogfragment = new LoadingDialogFragment();
		Bundle bundle = new Bundle();
		loadingdialogfragment.setArguments(bundle);
		return loadingdialogfragment;
	}

	@Override protected SKDialogFragmentBuilder build(SKDialogFragmentBuilder skBuilder) {
		skBuilder.layoutId(R.layout.dialog_fragment_loading);
		return skBuilder;
	}

	@Override protected void initData(Bundle savedInstanceState) {

	}

	@Override protected int style() {
		return R.style.Dialog_Fullscreen;
	}

}