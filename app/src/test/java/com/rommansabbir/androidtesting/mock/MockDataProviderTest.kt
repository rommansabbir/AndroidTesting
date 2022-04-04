package com.rommansabbir.androidtesting.mock

import org.junit.Test


class MockDataProviderTest {
    private val mockDataProvider = MockDataProvider()

    /*Check if the mock provider provide more than 10 data*/
    @Test
    fun checkIfMockProviderProvideMoreThanTenData() {
        val list = mockDataProvider.generateMockDataList()
        assert((list.size >= 10))
    }
}