-dontwarn com.weibo.sdk.Android.WeiboDialog
-dontwarn android.NET.http.SslError
-dontwarn android.webkit.WebViewClient

-dontnote com.sina.weibo.sdk.net.DownloadService

-keep class com.sina.weibo.sdk.** { *; }
-dontwarn com.sina.weibo.sdk.**


-keep public class android.Net.http.SslError{
     *;
}
-keep public class android.webkit.WebViewClient{
    *;
}
-keep public class android.webkit.WebChromeClient{
    *;
}
-keep public interface android.webkit.WebChromeClient$CustomViewCallback {
    *;
}
-keep public interface android.webkit.ValueCallback {
    *;
}
-keep class * implements android.webkit.WebChromeClient {
    *;
}

-keep class com.sina.weibo.sdk.api.* { *; }