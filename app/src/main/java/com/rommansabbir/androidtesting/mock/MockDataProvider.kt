package com.rommansabbir.androidtesting.mock

class MockDataProvider {
    /*Provide a list, contain 10 MockData*/
    fun generateMockDataList(): MutableList<MockData> {
        val list = mutableListOf<MockData>()
        for (item in 0..10) {
            list.add(MockData())
        }
        return list
    }
}