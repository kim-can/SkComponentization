package com.sk.api;

import sky.AutoID;
import sky.AutoExplain;
/**
 * 1.@AutoID注释类,会自动生成属性ID
 * 2.ID由架构统一管理,你可以定义任何属性不需要添加(public,static,final)..
 * 3.@AutoExplain 描述和参数
 */
@AutoID(-676385838)
public final class UserApi {
  @AutoExplain(describe = "")
  public static final int checkLogin = -1231221613;
  @AutoExplain(describe = "")
  public static final int intentLogin = -1259788517;
  @AutoExplain(describe = "")
  public static final int intentUser = 1899296217;
}
