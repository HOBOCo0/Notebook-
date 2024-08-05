package com.example.thenotebook

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thenotebook.models.UserRequest
import com.example.thenotebook.models.UserResponse
import com.example.thenotebook.repository.UserRepository
import com.example.thenotebook.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel(){
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepository.userResponseLiveData
    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.registerUser(userRequest)
        }
    }

    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }

    fun validatecredentials(username:String,emailAddress: String, password: String, isLogin:Boolean): Pair<Boolean,String>{
        var result = Pair(true,"")
        if(!isLogin && TextUtils.isEmpty(username)|| TextUtils.isEmpty(password)){
            result = Pair(false,"Please provide credentials")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            result = Pair(false,"Please provide valid email")
        }
        else if(password.length <= 5){
            result = Pair(false,"Password length should be greater than 5")
        }
        return result
    }
}