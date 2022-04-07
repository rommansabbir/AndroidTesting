package com.rommansabbir.androidtesting.data

import com.rommansabbir.androidtesting.usecase.Either
import com.rommansabbir.androidtesting.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

interface AuthenticationRepository {
    suspend fun loginUser(username: String, password: String): Either<Exception, String>
    suspend fun forgotPassword(username: String): Either<Exception, String>
}

class AuthenticationRepositoryImpl : AuthenticationRepository {
    override suspend fun loginUser(username: String, password: String): Either<Exception, String> {
        return try {
            delay(2000)
            if (username == "admin" && password == "1234567") {
                Either.Right("Success")
            } else {
                Either.Left(Exception("Auth error"))
            }
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    override suspend fun forgotPassword(username: String): Either<Exception, String> {
        return try {
            delay(2000)
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

class LoginUseCase(private val repository: AuthenticationRepository) :
    UseCase<String, UseCase.None>() {
    override suspend fun run(params: None): Either<Exception, String> =
        repository.loginUser("admin", "12345655")

    companion object {
        fun execute(
            useCase: LoginUseCase,
            scope: CoroutineScope,
            onSuccess: (String) -> Unit,
            onError: (Exception) -> Unit
        ) {
            useCase(scope, None()) { it.either(onError, onSuccess) }
        }
    }
}