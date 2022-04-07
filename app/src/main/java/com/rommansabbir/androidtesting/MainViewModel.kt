package com.rommansabbir.androidtesting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rommansabbir.androidtesting.data.LoginUseCase
import com.rommansabbir.androidtesting.mock.MockData
import com.rommansabbir.androidtesting.mock.MockDataProvider

class MainViewModel constructor(
    private val dataSource: MockDataProvider,
    public val useCase: LoginUseCase
) : ViewModel() {
    private var _list: MutableLiveData<MutableList<MockData>> = MutableLiveData()
    val list: LiveData<MutableList<MockData>>
        get() = _list

    fun getMockData() {
        _list.value = dataSource.generateMockDataList()
    }

/*    fun login(onSuccess: (String) -> Unit, onError: (Exception) -> Unit) {
        LoginUseCase.execute(useCase, viewModelScope, onSuccess, onError)
    }*/
}