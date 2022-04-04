package com.rommansabbir.androidtesting.validator

import java.util.regex.Pattern

class Validator {
    fun isEmailValid(input: String): Boolean = emailAddressPattern.matcher(input).matches()

    private val emailAddressPattern: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
}