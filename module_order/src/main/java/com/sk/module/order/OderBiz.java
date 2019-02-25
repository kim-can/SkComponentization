package com.sk.module.order;

import androidx.paging.PagedList;
import sk.SKBiz;
import sk.livedata.SKData;
import sky.SKInput;

import android.os.Bundle;

import com.sk.module.order.adapter.OderAdapter;
import com.sk.module.order.repository.OderRepository;

import java.util.ArrayList;

/**
 * @author sky
 * @date Created on 2019-02-12 4:00 PM
 * @version 1.0
 * @Description OderBiz - 描述
 */
public class OderBiz extends SKBiz {

	@SKInput OderRepository oderRepository;

	public SKData<ArrayList<OderAdapter.Model>> getSkData() {
		return skData;
	}

	public SKData<PagedList<ArrayList<OderAdapter.Model>>> getSkDataPaged() {
		return skDataPaged;
	}

	SKData<ArrayList<OderAdapter.Model>>			skData;

	SKData<PagedList<ArrayList<OderAdapter.Model>>>	skDataPaged;

	@Override public void initBiz(Bundle bundle) {
//		skData = oderRepository.oderList();
		skDataPaged = oderRepository.oderListPaged();
	}

	public void reload() {
		skDataPaged.retry();
	}

	public void refresh() {
		skDataPaged.getValue().getDataSource().invalidate();
	}
}