package com.sk.module.order.repository;

import com.sk.module.order.adapter.OderAdapter;
import com.sk.module.order.http.GithubHttp;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PagedList;
import sk.L;
import sk.SKRepository;
import sk.livedata.SKData;
import sk.livedata.SKItemSourceFactory;
import sk.livedata.SKPaged;
import sk.livedata.SKPagedBuilder;
import sk.livedata.SKSourceState;
import sky.SKHTTP;
import sky.SKInput;
import sky.SKProvider;
import sky.SKSingleton;

/**
 * @author sky
 * @version 1.0 on 2019-02-12 4:10 PM
 * @see OderRepository
 */
@SKProvider
@SKSingleton
public class OderRepository extends SKRepository<OderRepository> {

	@SKInput SKPaged skPaged;

	public SKData<ArrayList<OderAdapter.Model>> oderList() {
		SKData<ArrayList<OderAdapter.Model>> skData = new SKData<>();
		repository.oderListHttp(skData);
		return skData;
	}

	public SKData<PagedList<ArrayList<OderAdapter.Model>>> oderListPaged() {
		SKPagedBuilder skPagedBuilder = skPaged.pagedBuilder();
		skPagedBuilder.setPageSie(25);
		skPagedBuilder.setSource(new SKItemSourceFactory<String, OderAdapter.Model>() {

			@Override public void init(@NonNull ItemKeyedDataSource.LoadInitialParams<String> params, @NonNull ItemKeyedDataSource.LoadInitialCallback<OderAdapter.Model> callback) {
				layoutLoading();
				ArrayList<OderAdapter.Model> arrayList = new ArrayList<>();
				for (int i = 0; i < 31; i++) {
					OderAdapter.Model model = new OderAdapter.Model();
					model.id = "初始化数据---" + (params.requestedLoadSize + i) + " ::----" + params.requestedLoadSize;
					model.img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509016656952&di=7ba1379ee3ea1983fe347b71bd46477e&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fac345982b2b7d0a223890680c1ef76094b369a6e.jpg";
					arrayList.add(model);
				}
				callback.onResult(arrayList);
				layoutContent();
				closeLoading();
			}

			@Override public void before(@NonNull ItemKeyedDataSource.LoadParams<String> params, @NonNull ItemKeyedDataSource.LoadCallback<OderAdapter.Model> callback) {

			}

			@Override public void after(@NonNull ItemKeyedDataSource.LoadParams<String> params, @NonNull ItemKeyedDataSource.LoadCallback<OderAdapter.Model> callback) {
				netWorkRunning();
				List<OderAdapter.Model> list = http(GithubHttp.class).rateLimit().get();

				for (int i = 0; i < list.size(); i++) {
					list.get(i).id = "追加数据---" + (params.requestedLoadSize + i) + " ::----" + params.requestedLoadSize;
					list.get(
							i).img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509016656952&di=7ba1379ee3ea1983fe347b71bd46477e&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fac345982b2b7d0a223890680c1ef76094b369a6e.jpg";
				}
				callback.onResult(list);
				netWorkSuccess();
			}

			@Override public void error(@NonNull SKSourceState skSourceState) {

				switch (skSourceState) {
					case INIT:
						layoutError();
						break;
					case AFTER:
						netWorkFailed("加载失败了");
						break;
					default:
						break;
				}
			}

			@Override public String key(@NonNull OderAdapter.Model item) {
				return item.id;
			}

		});

		return skPagedBuilder.build();
	}

	@SKHTTP public void oderListHttp(SKData<ArrayList<OderAdapter.Model>> skData) {
		skData.layoutLoading();
		List<OderAdapter.Model> limitModel = http(GithubHttp.class).rateLimit().get();

		L.i("网络数据:" + limitModel);

		ArrayList<OderAdapter.Model> arrayList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			OderAdapter.Model model = new OderAdapter.Model();
			model.img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509016656952&di=7ba1379ee3ea1983fe347b71bd46477e&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fac345982b2b7d0a223890680c1ef76094b369a6e.jpg";
			arrayList.add(model);
		}
		skData.postValue(arrayList);
		skData.layoutContent();
	}

}
