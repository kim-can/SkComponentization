package com.sk.cpt.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.OnClick;
import sk.SKCommonView;
import sk.SKHolder;
import sk.livedata.SKLoadMoreHolder;
import sk.livedata.SKNetworkState;

import static sk.livedata.SKNetworkState.FAILED;
import static sk.livedata.SKNetworkState.RUNNING;

/**
 * @author sky
 * @version 1.0 on 2019-02-13 1:15 PM
 * @see DemoCommonView
 */
public class DemoCommonView implements SKCommonView {

	@Override public int layoutLoading() {
		return R.layout.layout_loading;
	}

	@Override public int layoutEmpty() {
		return R.layout.layout_empty;
	}

	@Override public int layoutError() {
		return R.layout.layout_error;
	}

	@Override public SKLoadMoreHolder adapterLoadMore(LayoutInflater layoutInflater, ViewGroup viewGroup, int viewType) {
		View view = layoutInflater.inflate(R.layout.layout_loadmore, viewGroup, false);
		return new LoadMore(view);
	}

	@Override public SKHolder adapterUnknownType(LayoutInflater layoutInflater, ViewGroup viewGroup, int viewType) {
		return null;
	}

	public class LoadMore extends SKLoadMoreHolder {

		@BindView(R2.id.error_msg) TextView			errorMsg;

		@BindView(R2.id.progress_bar) ProgressBar	progressBar;

		@BindView(R2.id.retry_button) Button			retry;

		public LoadMore(View itemView) {
			super(itemView);
		}

		@Override public void bindData(SKNetworkState state, int position) {
			progressBar.setVisibility(toVisiblity(state == RUNNING));
			retry.setVisibility(toVisiblity(state == FAILED));
			errorMsg.setVisibility(toVisiblity(state.Message != null));
			errorMsg.setText(state.Message);
		}

		private int toVisiblity(boolean is) {
			return is ? View.VISIBLE : View.GONE;
		}

		@OnClick(R2.id.retry_button) public void onReload() {
			callBack.onActoin(0);
		}

	}
}
