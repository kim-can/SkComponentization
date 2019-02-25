#sky--------------------------------------------------------------------------------------------------------
-keep class jc.sky.** { *; }
-dontwarn jc.sky.**
-keep @jc.sky.core.Impl class * {*;}
-keepclasseswithmembers class * {
    <init> ();
}
-keep class  sky.** { *; }
-dontwarn sky.**

-dontnote android.net.http.*
-dontnote org.apache.http.**

-dontnote okhttp3.**
-dontnote retrofit2.**
-dontnote butterknife.**
-dontnote org.apache.commons.io.**

# 不通知
-dontnote android.**
-dontnote dalvik.**
-dontnote com.android.**
-dontnote google.**
-dontnote com.google.**
-dontnote java.**
-dontnote javax.**
-dontnote junit.**
-dontnote org.apache.**
-dontnote org.json.**
-dontnote org.w3c.dom.**
-dontnote org.xml.sax.**
-dontnote org.xmlpull.v1.**
-dontnote sun.misc.Unsafe
-dontnote okhttp3.**
-dontnote retrofit2.**
-dontnote butterknife.**
-dontnote okio.**