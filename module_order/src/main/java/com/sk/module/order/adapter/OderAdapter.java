package com.sk.module.order.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.sk.module.order.R;
import com.sk.module.order.R2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import butterknife.BindView;
import butterknife.OnClick;
import sk.SKAdapter;
import sk.SKHolder;
import sk.livedata.SKLoadMoreCallBack;

/**
 * @author sky
 * @date Created on 2019-02-12 4:02 PM
 * @version 1.0
 * @Description OderAdapter - 描述
 */
public class OderAdapter extends SKAdapter<OderAdapter.Model, OderAdapter.ItemHolder> {

	public OderAdapter(@NonNull SKLoadMoreCallBack skLoadMoreCallBac) {
		super(new DiffUtil.ItemCallback<Model>() {

			@Override public boolean areItemsTheSame(@NonNull Model oldItem, @NonNull Model newItem) {
				return oldItem.getClass() == newItem.getClass();
			}

			@Override public boolean areContentsTheSame(@NonNull Model oldItem, @NonNull Model newItem) {
				return oldItem.id.equals(newItem.id);
			}
		}, skLoadMoreCallBac);// DiffUtil.ItemCallback自定义数据比较器
	}

	@Override public int layoutID(int position) {
		return R.layout.item_oder_item;
	}

	@Override public ItemHolder newHolder(int viewType, View view, Context context) {
		return new ItemHolder(view);
	}

	public class ItemHolder extends SKHolder<Model> {

		@BindView(R2.id.iv_item) ImageView ivItem;

		public ItemHolder(View itemView) {
			super(itemView);
		}

		@Override public void bindData(Model model, int position) {
			Glide.with(ivItem.getContext()).load(getItem(position).img).into(ivItem);
		}

		@OnClick(R2.id.iv_item) public void onViewClicked() {}

	}

	public static class Model {

		public String	img;

		public String	id;

	}
}