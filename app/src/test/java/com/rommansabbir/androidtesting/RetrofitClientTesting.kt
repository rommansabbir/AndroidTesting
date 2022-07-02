package com.rommansabbir.androidtesting

import com.google.gson.Gson
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RetrofitClientTesting : TestCase() {


    @Before
    public override fun setUp() {
        /*Initialize ViewModel and DataSource*/
    }

    /*To verify that login response from login api is not null*/
    @Test
    fun testLoginMockResponse() {
        val loginResponse = RetrofitClient.apiService.login().execute().body()
        assert(loginResponse != null)
    }
}