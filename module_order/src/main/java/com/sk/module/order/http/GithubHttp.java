package com.sk.module.order.http;

import com.sk.module.order.adapter.OderAdapter;

import java.util.List;

import retrofit2.SKCall;
import retrofit2.http.GET;

/**
 * @author sky
 * @version 1.0 on 2017-11-03 上午9:48
 * @see GithubHttp github 公开接口
 */
public interface GithubHttp {

	@GET("rate_limit") SKCall<List<OderAdapter.Model>> rateLimit();

}
