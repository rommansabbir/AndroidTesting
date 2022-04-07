package com.rommansabbir.androidtesting.data

import com.rommansabbir.androidtesting.usecase.Either
import com.rommansabbir.androidtesting.usecase.UseCase
import kotlinx.coroutines.CoroutineScope

interface AuthenticationRepository {
    suspend fun loginUser(username: String, password: String): Either<Exception, String>
    suspend fun forgotPassword(username: String): Either<Exception, String>
}

class AuthenticationRepositoryImpl : AuthenticationRepository {
    override suspend fun loginUser(username: String, password: String): Either<Exception, String> {
        return try {
            if (username == "admin" && password == "1234567") {
                Either.Right("Success")
            } else {
                Either.Left(LoginException())
            }
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    override suspend fun forgotPassword(username: String): Either<Exception, String> {
        return try {
            if (username == "admin") {
                Either.Right("Success")
            } else {
                Either.Left(Exception("Auth error"))
            }
        } catch (e: Exception) {
            Either.Left(e)
        }
    }
}

class LoginModel(val username: String, val password: String)

class LoginUseCase(private val repository: AuthenticationRepository) :
    UseCase<String, LoginModel>() {
    override suspend fun run(params: LoginModel): Either<Exception, String> =
        repository.loginUser(params.username, params.password)

    companion object {
        fun execute(
            useCase: LoginUseCase,
            scope: CoroutineScope,
            loginModel: LoginModel,
            onSuccess: (String) -> Unit,
            onError: (Exception) -> Unit
        ) {
            useCase(scope, loginModel) { it.either(onError, onSuccess) }
        }
    }
}

class LoginException(override val message: String = "Failed to Login") : Exception(message)