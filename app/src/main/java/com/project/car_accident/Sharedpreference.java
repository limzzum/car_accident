package com.project.car_accident;

import android.content.Context;
import android.content.SharedPreferences;


public class Sharedpreference {

    final private static String PREFNAME_CAR = "car_accident";


    final private static String PREFKEY_EMAIL = "email";
    final private static String PREFKEY_NAME = "name";



    public static void setSharedPrefEmail(Context context, String value) {
        SharedPreferences pref = context.getSharedPreferences(PREFNAME_CAR, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putString(PREFKEY_EMAIL, value);
        prefEditor.commit();
    }

    public static String getSharedPrefEmail(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFNAME_CAR, Context.MODE_PRIVATE);
        return pref.getString(PREFKEY_EMAIL, "");
    }


    public static void setSharedPrefName(Context context, String value) {
        SharedPreferences pref = context.getSharedPreferences(PREFNAME_CAR, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putString(PREFKEY_NAME, value);
        prefEditor.commit();
    }

    public static String getSharedPrefName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFNAME_CAR, Context.MODE_PRIVATE);
        return pref.getString(PREFKEY_NAME, "");
    }


}
