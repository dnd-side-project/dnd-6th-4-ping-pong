package com.dnd.sixth.lmsservice.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.dnd.sixth.lmsservice.data.network.base.NetworkCommons

//SharedPreference
/**
 * 데이터 저장 및 로드 클래스
 */
class PreferenceManager(
    private val context: Context,
) {

    companion object {
        const val PREFERENCES_NAME = "PreferenceName"
        private const val DEFAULT_VALUE_STRING = ""
        private const val DEFAULT_VALUE_BOOLEAN = false
        private const val DEFAULT_VALUE_INT = -1
        private const val DEFAULT_VALUE_LONG = -1L
        private const val DEFAULT_VALUE_FLOAT = -1f

        const val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"
        const val KEY_REFRESH_TOKEN = "REFRESH_TOKEN"
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    //preference 객체는 koin을 통해 주입 받음.
    private val prefs by lazy { getPreferences(context) }

    //에디터
    private val editor by lazy { prefs.edit() }

    /**
     * String 값 저장
     * @param context
     * @param key
     * @param value
     */
    fun setString(key: String?, value: String?) {
        editor.putString(key, value)
        editor.commit()
    }

    /**
     * boolean 값 저장
     * @param context
     * @param key
     * @param value
     */
    fun setBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * int 값 저장
     * @param context
     * @param key
     * @param value
     */
    fun setInt(key: String?, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * long 값 저장
     * @param context
     * @param key
     * @param value
     */
    fun setLong(key: String?, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * float 값 저장
     * @param context
     * @param key
     * @param value
     */
    fun setFloat(key: String?, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    /**
     * String 값 로드
     * @param context
     * @param key
     * @return
     */
    fun getString(key: String?): String? {
        return prefs.getString(key, DEFAULT_VALUE_STRING)
    }

    /**
     * boolean 값 로드
     * @param context
     * @param key
     * @return
     */
    fun getBoolean(key: String?): Boolean {
        return prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN)
    }

    /**
     * int 값 로드
     * @param context
     * @param key
     * @return
     */
    fun getInt(key: String?): Int {
        return prefs.getInt(key, DEFAULT_VALUE_INT)
    }

    /**
     * long 값 로드
     * @param context
     * @param key
     * @return
     */
    fun getLong(key: String?): Long {
        return prefs.getLong(key, DEFAULT_VALUE_LONG)
    }

    /**
     * float 값 로드
     * @param context
     * @param key
     * @return
     */
    fun getFloat(key: String?): Float {
        return prefs.getFloat(key, DEFAULT_VALUE_FLOAT)
    }

    /**
     * 키 값 삭제
     * @param context
     * @param key
     */
    fun removeKey(key: String?) {
        editor.remove(key)
        editor.apply()
    }

    /**
     * 모든 저장 데이터 삭제
     * @param context
     */
    fun clear() {
        editor.clear()
        editor.apply()
    }

    fun putAccessToken(accessToken: String) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(): String {
        // 인증 타입
        // Authorization: <type> <credentials>
        val credentialsType = NetworkCommons.CREDENTIAL_TYPE
        return "$credentialsType ${prefs.getString(KEY_ACCESS_TOKEN, null)}"
    }

    fun putRefreshToken(refreshToken: String) {
        editor.putString(KEY_REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    fun getRefreshToken(): String {
        val credentialsType = NetworkCommons.CREDENTIAL_TYPE
        return "$credentialsType ${prefs.getString(KEY_REFRESH_TOKEN, null)}"
    }

    fun removedToken() {
        editor.putString(KEY_ACCESS_TOKEN, null)
        editor.apply()
    }
}