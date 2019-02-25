-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class com.hna.hi.R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}