package com.example.validationapp.model

class FormDat (private var studentID: String,
               private var year: String,
               private var semester:String,
               private var agreement:Boolean){
    fun validateStudent():ValidationResults{
        return if(studentID.isEmpty()){
            ValidationResults.Empty("Student ID is Empty!")
        }

        else if(studentID.startsWith("IT")){
            ValidationResults.Invalid("Should be starting with IT")
        }

        else if (studentID.length<10){
            ValidationResults.Invalid("Student ID must have 10 characters")
        }

        else if (studentID.length>10){
            ValidationResults.Invalid("Student ID must have 10 characters")
        }

        else{
            ValidationResults.Valid
        }
    }

    fun validateYear():ValidationResults{
        return if (year.isEmpty()){
            ValidationResults.Empty("Year is Empty")
        }

        else{
            ValidationResults.Valid
        }
    }

    fun validateSemester():ValidationResults{
        return if (semester.isEmpty()){
            ValidationResults.Empty("Semester is Empty")
        }

        else{
            ValidationResults.Valid
        }
    }

    fun validateAgreement():ValidationResults{
        return if (!agreement){
            ValidationResults.Empty("You must agree for the terms and conditions")
        }

        else{
            ValidationResults.Valid
        }
    }
}