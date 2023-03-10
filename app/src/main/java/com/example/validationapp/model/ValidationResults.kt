package com.example.validationapp.model

sealed class ValidationResults {
    data class Empty(val errorMessage:String):ValidationResults()
    data class Invalid(val errorMessage: String):ValidationResults()
    object Valid:ValidationResults()
}