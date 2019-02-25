package com.sk.api;

import sky.AutoID;
import sky.AutoExplain;
/**
 * 1.@AutoID注释类,会自动生成属性ID
 * 2.ID由架构统一管理,你可以定义任何属性不需要添加(public,static,final)..
 * 3.@AutoExplain 描述和参数
 */
@AutoID(301769508)
public final class MainApi {
  @AutoExplain(describe = "")
  public static final int showTip = 1895333050;
}
