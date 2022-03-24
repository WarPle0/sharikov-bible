package com.example.bible;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingPreferences {
    static final String KEY_NOTIFY_BIBLE = "notify_bible";
    static final String KEY_NOTIFY_BIBLE_ISSET = "notify_bible_isset";
    static final String KEY_NOTIFY_PRAYER = "notify_prayer";
    static final String KEY_LANGUAGE = "code_lang";

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setNotifyBible(Context context, boolean notify) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(KEY_NOTIFY_BIBLE, notify);
        editor.apply();
    }

    public static boolean getNotifyBible(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_NOTIFY_BIBLE, true);
    }

    public static void setNotifyBibleIsSet(Context context, boolean isSet) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(KEY_NOTIFY_BIBLE_ISSET, isSet);
        editor.apply();
    }

    public static boolean getNotifyBibleIsSet(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_NOTIFY_BIBLE_ISSET, true);
    }

    public static void setNotifyPrayer(Context context, boolean notify) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(KEY_NOTIFY_PRAYER, notify);
        editor.apply();
    }

    public static boolean getNotifyPrayer(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_NOTIFY_PRAYER, true);
    }

    public static void setCodeLanguage(Context context, String code_lang) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_LANGUAGE, code_lang);
        editor.apply();
    }

    public static String getCodeLanguage(Context context) {
        return getSharedPreferences(context).getString(KEY_LANGUAGE, "en");
    }
}
