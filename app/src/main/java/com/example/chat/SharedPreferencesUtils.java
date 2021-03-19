package com.example.chat;

import android.app.Activity;
import android.content.SharedPreferences;


/**
 * Created by zh on 2017/6/19.
 */
//代理模式（proxy-pattern）&单例模式（singleton-pattern）
public class SharedPreferencesUtils {
    private SharedPreferences sharedPreferences;
    private static SharedPreferencesUtils sharedPreferencesUtils=new SharedPreferencesUtils();
    private SharedPreferences.Editor editor;
    private SharedPreferencesUtils(){
        sharedPreferences= BaseApplication.getAppInstance().getSharedPreferences("config", Activity.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }



    public static SharedPreferencesUtils getInstance(){
        return sharedPreferencesUtils;
    }

    public void setBoolean(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key
     *            The name of the preference to retrieve.
     * @param defValue
     *            Value to return if this preference does not exist.
     * @return the preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a boolean.
     * @throws ClassCastException
     * */
    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * Set a float value in the preferences editor, to be written back once
     * commit is called.
     *
     * @param key
     *            The name of the preference to modify.
     * @param value
     *            The new value for the preference.
     * @return a reference to the same Editor object, so you can chain put calls
     *         together.
     * */
    public void setFloat(String key, float value) {
        editor.putFloat(key, value).commit();
    }

    /**
     * Retrieve a float value from the preferences.
     *
     * @param key
     *            The name of the preference to retrieve.
     * @param defValue
     *            Value to return if this preference does not exist.
     * @return the preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a boolean.
     * @throws ClassCastException
     * */
    public float getFloat(String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    /**
     * Set a int value in the preferences editor, to be written back once commit
     * is called.
     *
     * @param key
     *            The name of the preference to modify.
     * @param value
     *            The new value for the preference.
     * @return a reference to the same Editor object, so you can chain put calls
     *         together.
     * */
    public void setInt(String key, int value) {
        editor.putInt(key, value).commit();
    }

    /**
     * Retrieve a int value from the preferences.
     *
     * @param key
     *            The name of the preference to retrieve.
     * @param defValue
     *            Value to return if this preference does not exist.
     * @return the preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a boolean.
     * @throws ClassCastException
     * */
    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * Set a long value in the preferences editor, to be written back once
     * commit is called.
     *
     * @param key
     *            The name of the preference to modify.
     * @param value
     *            The new value for the preference.
     * @return a reference to the same Editor object, so you can chain put calls
     *         together.
     * */
    public void setLong(String key, long value) {
        editor.putLong(key, value).commit();
    }

    /**
     * Retrieve a long value from the preferences.
     *
     * @param key
     *            The name of the preference to retrieve.
     * @param defValue
     *            Value to return if this preference does not exist.
     * @return the preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a boolean.
     * @throws ClassCastException
     * */
    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    /**
     * Set a String value in the preferences editor, to be written back once
     * commit is called.
     *
     * @param key
     *            The name of the preference to modify.
     * @param value
     *            The new value for the preference.
     * @return a reference to the same Editor object, so you can chain put calls
     *         together.
     * */
    public void setString(String key, String value) {
        editor.putString(key, value).commit();
    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key
     *            The name of the preference to retrieve.
     * @param defValue
     *            Value to return if this preference does not exist.
     * @return the preference value if it exists, or defValue. Throws
     *         ClassCastException if there is a preference with this name that
     *         is not a boolean.
     * @throws ClassCastException
     * */
    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }
}
