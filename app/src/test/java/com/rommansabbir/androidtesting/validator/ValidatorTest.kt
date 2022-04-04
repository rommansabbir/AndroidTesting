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
}