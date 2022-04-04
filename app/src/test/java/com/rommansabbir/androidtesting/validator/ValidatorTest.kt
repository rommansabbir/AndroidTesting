package com.rommansabbir.androidtesting.validator

import org.junit.Test


class ValidatorTest {
    private val validator = Validator()
    @Test
    fun testValidEmailAddress() {
        assert(validator.isEmailValid("test@test.com"))
    }

    @Test
    fun testInvalidEmail(){
        assert(!validator.isEmailValid("test"))
    }

}