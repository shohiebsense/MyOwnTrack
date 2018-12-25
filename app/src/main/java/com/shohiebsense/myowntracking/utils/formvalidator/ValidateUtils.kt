package com.shohiebsense.myowntracking.utils.formvalidator

import android.content.Context
import android.widget.EditText
import com.shohiebsense.myowntracking.R

object ValidateUtils {

    fun getGotErrorEditText(editTexts : ArrayList<EditText>, hashCode: Int) : EditText?{
        editTexts.forEach {
            if(it.editableText.hashCode() == hashCode){
                return it
            }
        }
        return null
    }

    fun getErrorMessage(context : Context, formType : Int) : String{
        when(formType){
            Validator.FORM_TYPE_BASIC -> {
                return context.getString(R.string.error_validate_empty)
            }
            Validator.FORM_TYPE_EMAIL -> {
                return context.getString(R.string.error_validate_email)
            }
            Validator.FORM_TYPE_PHONE -> {
                return context.getString(R.string.error_validate_phone)
            }
        }
        return ""
    }

    fun generateErrorMessage(context: Context, editTexts: ArrayList<EditText>, formType: Int, hashCode: Int){
        var editText = getGotErrorEditText(editTexts,hashCode)!!
        editText.error = getErrorMessage(context, formType)
    }
}