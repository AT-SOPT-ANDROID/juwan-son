package org.sopt.at.presentaion.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.at.data.remote.remote.ServicePool
import org.sopt.at.data.remote.remote.dto.nicknamesearch.nicknamesearchresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {


    private val _nicknameList = MutableLiveData<List<String>>()
    val nicknameList: LiveData<List<String>> get() = _nicknameList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    fun searchNickname(keyword: String) {
        viewModelScope.launch {

            ServicePool.nicknameSearchApi.searchservice(keyword).enqueue(object :
                Callback<nicknamesearchresponse> {
                override fun onResponse(call: Call<nicknamesearchresponse>, response: Response<nicknamesearchresponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val responseBody = response.body()!!

                        if (responseBody.success) {
                            _nicknameList.value = responseBody.data?.nicknameList
                        } else {
                            _errorMessage.value = responseBody.message
                        }
                    } else {
                        _errorMessage.value = "검색에 실패했습니다."
                    }
                }

                override fun onFailure(call: Call<nicknamesearchresponse>, t: Throwable) {
                    _errorMessage.value = "네트워크 오류: ${t.localizedMessage}"
                }
            })
        }
    }
}