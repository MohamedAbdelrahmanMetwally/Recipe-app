package com.example.finalone.core.database

import android.content.Context
import android.content.SharedPreferences

class SharedPref private constructor() {

    companion object {
        private var instance: SharedPreferences? = null

        private fun init(context: Context) {
            if (instance == null) {
                instance = context.getSharedPreferences("my_app", Context.MODE_PRIVATE)
            }
        }

        fun isFirstTime(context: Context): Boolean {
            init(context)
            return instance!!.getBoolean("isFirstTime", true)
        }

        fun setFirstTime(context: Context, isFirstTime: Boolean) {
            init(context)
            instance!!.edit().putBoolean("isFirstTime", isFirstTime).apply()
        }

        fun logout(context: Context) {
            init(context)
            instance!!.edit().putBoolean("isLoggedIn", false).apply()
        }

        fun isLoggedIn(context: Context): Boolean {
            init(context)
            return instance!!.getBoolean("isLoggedIn", false)
        }

        fun login(context: Context) {
            init(context)
            instance!!.edit().putBoolean("isLoggedIn", true).apply()
        }

        fun saveUser(context: Context, email: String, password: String) {
            init(context)
            instance!!.edit()
                .putString("username", email)
                .putString("password", password)
                .apply()
        }

        fun isRegistered(context: Context, email: String, password: String): Boolean {
            init(context)
            val savedEmail = instance!!.getString("username", null)
            val savedPassword = instance!!.getString("password", null)
            return savedEmail == email && savedPassword == password
        }
    }
}
