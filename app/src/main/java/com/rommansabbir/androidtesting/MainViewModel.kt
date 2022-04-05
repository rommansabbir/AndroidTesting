package com.rommansabbir.androidtesting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rommansabbir.androidtesting.mock.MockData
import com.rommansabbir.androidtesting.mock.MockDataProvider

class MainViewModel constructor(private val dataSource: MockDataProvider) : ViewModel() {
    private var _list: MutableLiveData<MutableList<MockData>> = MutableLiveData()
    val list: LiveData<MutableList<MockData>>
        get() = _list

    fun getMockData() {
        _list.value = dataSource.generateMockDataList()
    }
}