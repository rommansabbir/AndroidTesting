package com.rommansabbir.androidtesting

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rommansabbir.androidtesting.data.*
import com.rommansabbir.androidtesting.mock.MockDataProvider
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

    /*We are using UseCase, which calls repository behind the scene
    and all operation is backed by coroutine*/
    @Test
    fun verifyLoginSuccessFromUseCase() {
        var isInvoked = false
        var isSuccess = false
        viewModel.login(
            LoginModel("admin", "1234567"),
            {
                isInvoked = true
                isSuccess = true
                println(it)
            },
            {
                isInvoked = true
                isSuccess = false
                it.printStackTrace()
            }
        )
        Thread.sleep(1000)
        print("Is invoked : $isInvoked")
        println("Is Success : $isSuccess")
        assert(isInvoked)
        assert(isSuccess)
    }

    /*We are using UseCase, which calls repository behind the scene
    and all operation is backed by coroutine*/
    @Test
    fun verifyLoginFailureFromUseCase() {
        var isInvoked = false
        var isSuccess = false
        viewModel.login(
            LoginModel("admin", "32423423"),
            {
                isInvoked = true
                isSuccess = true
                println(it)
            },
            {
                isInvoked = true
                isSuccess = false
                it.printStackTrace()
            }
        )
        Thread.sleep(1000)
        print("Is invoked : $isInvoked")
        println("Is Success : $isSuccess")
        assert(isInvoked)
        assert(!isSuccess)
    }

    /*To verify that the exception is LoginException is username or password is wrong*/
    @Test
    fun verifyLoginFailureFromUseCaseAsException() {
        var loginException: Exception? = null
        viewModel.login(
            LoginModel("admin", "32423423"),
            {
                println(it)
            },
            {
                loginException = it
                println()
            }
        )
        Thread.sleep(1000)
        assert(loginException is LoginException)
    }

    /*To verify that if an exception happen during the login execution should return
    an instance of Exception class*/
    @Test
    fun verifyLoginFailureFromUseCaseIsInstanceOfException() {
        var loginException: Exception? = null
        viewModel.login(
            LoginModel("admin", "32423423"),
            {
                println(it)
            },
            {
                loginException = it
                println()
            }
        )
        Thread.sleep(1000)
        assert(loginException is Exception)
    }

    /*Verify that when login is success it should return success message as a String*/
    @Test
    fun verifyLoginSuccessShouldReturnSuccessMessageAsAString() {
        var successMessage: String? = null
        viewModel.login(
            LoginModel("admin", "1234567"),
            {
                successMessage = it
                println(it)
            },
            {
                it.printStackTrace()
            }
        )
        Thread.sleep(1000)
        assert(successMessage == "Success")
    }

}