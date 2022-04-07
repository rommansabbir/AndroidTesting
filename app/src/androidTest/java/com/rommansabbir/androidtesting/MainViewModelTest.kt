package com.rommansabbir.androidtesting

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rommansabbir.androidtesting.data.AuthenticationRepository
import com.rommansabbir.androidtesting.data.AuthenticationRepositoryImpl
import com.rommansabbir.androidtesting.data.LoginUseCase
import com.rommansabbir.androidtesting.mock.MockDataProvider
import com.rommansabbir.androidtesting.usecase.UseCase
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MainViewModelTest : TestCase() {
    private lateinit var viewModel: MainViewModel
    private lateinit var dataSource: MockDataProvider
    private lateinit var repository: AuthenticationRepository
    private lateinit var useCase: LoginUseCase

    /*We have added this rule to swap the background executor. And this new executor will work synchronously.*/
    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        /*Return the application instance*/
        val context = ApplicationProvider.getApplicationContext<Context>()
        println(context.toString())
        /*Initialize ViewModel and DataSource*/
        dataSource = MockDataProvider()
        repository = AuthenticationRepositoryImpl()
        useCase = LoginUseCase(repository)
        viewModel = MainViewModel(dataSource, useCase)
    }

    @Test
    fun verifyLogin() {
        println("Msg: Verify Login")
        viewModel.useCase(viewModel.viewModelScope, UseCase.None()) {
            println("Msg: $it")
            assert(it.isRight)
        }
    }

    /*   @Test
       fun getListOfData() {
           viewModel.getMockData()
           assert(viewModel.list.value?.find { it.msg == "Test" } == null)
       }*/
}