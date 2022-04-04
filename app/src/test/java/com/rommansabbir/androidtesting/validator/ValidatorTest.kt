package com.rommansabbir.androidtesting.validator

import org.junit.Test
import org.junit.jupiter.api.assertThrows


class ValidatorTest {
    private val validator = Validator()

    /*Check if an given email address is valid*/
    @Test
    fun testValidEmailAddress() {
        assert(validator.isEmailValid("test@test.com"))
    }

    /*Check if a given email address is invalid*/
    @Test
    fun testInvalidEmail() {
        assert(!validator.isEmailValid("test"))
    }

    /*Check if Validator.validateNullObject throw Exception if the given object is null*/
    @Test
    fun testNullObjectThrowException() {
        assertThrows<Exception> { validator.validateNullObject(null) }
    }

    /*Check if a given object is not null*/
    @Test
    fun checkGivenObjectIsValid() {
        assert(validator.validateNullObject(""))
    }

}