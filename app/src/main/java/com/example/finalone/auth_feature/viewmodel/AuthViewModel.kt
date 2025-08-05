package com.example.finalone.auth_feature.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalone.core.database.SharedPref
class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn
    private val _isFirstTime = MutableLiveData<Boolean>()
    val isFirstTime: LiveData<Boolean> get() = _isFirstTime
    init {
        _isLoggedIn.value = SharedPref.isLoggedIn(context)
        _isFirstTime.value = SharedPref.isFirstTime(context)
    }
    fun login() {
        SharedPref.login(context)
        _isLoggedIn.value = true
    }
    fun logout() {
        SharedPref.logout(context)
        _isLoggedIn.value = false
    }
    fun setNotFirstTime() {
        SharedPref.setFirstTime(context, false)
        _isFirstTime.value = false
    }
    fun register(email: String, password: String) {
        SharedPref.saveUser(context, email, password)
        _isLoggedIn.value = true
        _isFirstTime.value = false
    }
    fun canLogin(username: String, password: String): Boolean {
        return SharedPref.isRegistered(context, username, password)
    }
    fun isLoggedIn(): Boolean {
        return SharedPref.isLoggedIn(context)
    }
}