package com.rommansabbir.androidtesting

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RetrofitClientTesting : TestCase() {
    /*We have added this rule to swap the background executor. And this new executor will work synchronously.*/
    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        /*Return the application instance*/
        val context = ApplicationProvider.getApplicationContext<Context>()
        println(context.toString())
        /*Initialize ViewModel and DataSource*/
    }

    /*To verify that login response from login api is not null*/
    @Test
    fun testLoginMockResponse() {
        val loginResponse = RetrofitClient.apiService.login().execute().body()
        assert(loginResponse != null)
    }

/*    *//*To verify that login response from login api is not null*//*
    @Test
    fun testLoginMockLoginResponse() {
        val response = RetrofitClient.apiService.login().execute()
        val body = response.raw().message
        val pojo = Gson().fromJson(body, LoginResponse::class.java)
        assert(pojo.name == "rommansabbir")
    }*/

}