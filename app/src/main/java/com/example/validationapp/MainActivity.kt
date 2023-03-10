package com.example.validationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.validationapp.model.FormDat
import com.example.validationapp.model.FormData
import com.example.validationapp.model.ValidationResults
import java.time.Year

class MainActivity : AppCompatActivity() {

    lateinit var edtStudentID:EditText
    lateinit var spnYear: Spinner
    lateinit var spnSemester: Spinner
    lateinit var cbAgree: CheckBox
    lateinit var btnSubmit:Button
    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtStudentID= findViewById(R.id.edtStudentID)
        spnYear= findViewById(R.id.spnYear)
        spnSemester= findViewById(R.id.spnSemester)
        cbAgree= findViewById(R.id.cbAgree)
        btnSubmit= findViewById(R.id.btnSubmit)

    }


    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
        }

        val dialog = builder.create()
        dialog.show() }

    override fun onResume() {
        super.onResume()

        btnSubmit.setOnClickListener(View.OnClickListener {

            val myForm=FormDat(edtStudentID.text.toString(),
                spnYear.selectedItem.toString(),
                spnSemester.selectedItem.toString(),
                cbAgree.isChecked)

            val studentIDValidation= myForm.validateStudent()
            val yearValidation= myForm.validateYear()
            val semesterValidation= myForm.validateSemester()
            val agreementValidation= myForm.validateAgreement()

            when(studentIDValidation){
                is ValidationResults.Valid ->{
                    count++
                }

                is ValidationResults.Invalid ->{
                    edtStudentID.error=studentIDValidation.errorMessage
                }

                is ValidationResults.Empty ->{
                    edtStudentID.error=studentIDValidation.errorMessage
                }
            }

            when(yearValidation){
                is ValidationResults.Valid ->{
                    count++
                }

                is ValidationResults.Invalid ->{
                    val tv:TextView=spnYear.selectedView as TextView
                    tv.error=""
                    tv.text=yearValidation.errorMessage
                }

                is ValidationResults.Empty ->{
                    val tv:TextView=spnYear.selectedView as TextView
                    tv.error=""
                    tv.text=yearValidation.errorMessage

                }
            }

            when(semesterValidation){
                is ValidationResults.Valid ->{
                    count++
                }

                is ValidationResults.Invalid ->{
                    val tv:TextView=spnSemester.selectedView as TextView
                    tv.error=""
                    tv.text=semesterValidation.errorMessage
                }

                is ValidationResults.Empty ->{
                    val tv:TextView=spnSemester.selectedView as TextView
                    tv.error=""
                    tv.text=semesterValidation.errorMessage
                }
            }

            when(agreementValidation){
                is ValidationResults.Valid ->{
                    count++
                }

                is ValidationResults.Invalid ->{
                    displayAlert("Error", agreementValidation.errorMessage)
                }

                is ValidationResults.Empty -> {

                }
            }

            if (count==4){
                displayAlert("Success", "You have successfully Registered")

                FormData(edtStudentID.text.toString(),
                        Integer.parseInt(spnYear.selectedItem.toString()),
                        spnSemester.selectedItem.toString(),
                        cbAgree.isChecked)
            }
        })
    }
}