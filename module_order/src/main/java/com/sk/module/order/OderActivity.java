package com.sk.module.order;

import androidx.paging.PagedList;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import sk.SKHelper;

import android.os.Bundle;

import com.sk.api.OderApi;
import com.sk.module.order.adapter.OderAdapter;

import java.util.ArrayList;

import sk.SKActivity;
import sk.SKActivityBuilder;
import sk.livedata.SKViewState;
import sky.OpenMethod;

/**
 * @author sky
 * @version 1.0
 * @date Created on 2019-02-12 4:00 PM
 * @Description OderActivity - 描述
 */
public class OderActivity extends SKActivity<OderBiz> {

	@OpenMethod(OderApi.intentOder) public static final void intent() {
		SKHelper.display().intent(OderActivity.class);
	}

	@BindView(R2.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;

	@Override protected SKActivityBuilder build(SKActivityBuilder skBuilder) {
		skBuilder.layoutId(R.layout.activity_oder);
		skBuilder.recyclerviewId(R.id.rv_oder, new OderAdapter((action, objects) -> biz().reload()));
		return skBuilder;
	}

	@Override protected void initData(Bundle savedInstanceState) {
		swipeRefreshLayout.setOnRefreshListener(() -> biz().refresh());

		biz().getSkDataPaged().observe(this, new SKViewObserver<PagedList<ArrayList<OderAdapter.Model>>>() {

			@Override public void onAction(SKViewState state) {
				super.onAction(state);
				if (state == SKViewState.CLOSE_LOADING) {
					swipeRefreshLayout.setRefreshing(false);
				}
			}

			@Override public void onChanged(PagedList<ArrayList<OderAdapter.Model>> arrayLists) {
				adapter().setItems(arrayLists);
			}
		});
	}

}