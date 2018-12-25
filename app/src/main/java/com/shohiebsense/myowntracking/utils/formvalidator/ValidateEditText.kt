package com.shohiebsense.myowntracking.utils.formvalidator

import android.widget.EditText
import com.shohiebsense.myowntracking.R

class ValidateEditText(val editText: EditText) {

    constructor(editText: EditText, formType : Int) : this(editText){
        this.formType = formType
    }

    companion object {
        var FORM_TYPE_EMAIL = 0
        var FORM_TYPE_PHONE = 1
        var FORM_TYPE_BASIC = -1
    }



    val ERROR_VALIDATE_EMAIL = editText.context.getString(R.string.error_validate_email)
    val ERROR_VALIDATE_EMPTY = editText.context.getString(R.string.error_validate_empty)
    val ERROR_VALIDATE_PHONE = editText.context.getString(R.string.error_validate_phone)

    var passed = false
    var error = ""

    var formType = FORM_TYPE_BASIC

    init {
        formType = FORM_TYPE_BASIC
        error = ERROR_VALIDATE_EMPTY
    }

    fun setEmailType() : ValidateEditText{
        formType = FORM_TYPE_EMAIL
        error = ERROR_VALIDATE_EMAIL
        return this
    }

    fun setPhoneType() : ValidateEditText {
        formType = FORM_TYPE_PHONE
        error = ERROR_VALIDATE_PHONE
        return this
    }




}